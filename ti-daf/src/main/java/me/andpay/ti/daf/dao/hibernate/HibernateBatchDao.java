package me.andpay.ti.daf.dao.hibernate;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import me.andpay.ti.daf.dao.BatchDao;
import me.andpay.ti.util.CloseUtil;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.jdbc.Work;

/**
 * 基于Hibernate实现的批处理Dao类。
 * 
 * @author seabao
 */
public class HibernateBatchDao<T, I extends Serializable, Q> extends HibernateGenericDao<T, I, Q> implements
		BatchDao<T, I, Q> {
	protected Log logger = LogFactory.getLog(getClass());

	/**
	 * Bean属性名称集
	 */
	protected Set<String> beanPropNames = new TreeSet<String>();

	/**
	 * 表名称
	 */
	protected String tableName;

	public HibernateBatchDao() {
		super();

		try {
			BeanInfo beanInfo = java.beans.Introspector.getBeanInfo(clazz);
			for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
				if (pd.getName().equals("class")) {
					continue;
				}

				beanPropNames.add(pd.getName());
			}
		} catch (IntrospectionException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		tableName = clazz.getSimpleName();
	}

	public int batchUpdate(final List<T> beanList, final String... property) {
		final String idProp = getSessionFactory().getClassMetadata(clazz).getIdentifierPropertyName();

		return getHibernateTemplate().doReturningWork(new ReturningWork<Integer>() {
			public Integer execute(Connection connection) throws SQLException {
				if (beanList.isEmpty()) {
					return 0;
				}

				StringBuffer sb = new StringBuffer();

				sb.append("update ");
				sb.append(tableName);
				sb.append(" set ");

				Set<String> set = new HashSet<String>();
				if (property.length == 0) {
					set.addAll(beanPropNames);
				} else {
					for (String prop : property) {
						set.add(prop);
					}
				}

				set.remove(idProp);

				boolean first = true;
				for (String propName : set) {
					if (first) {
						first = false;
					} else {
						sb.append(",");
					}

					sb.append(propName);
					sb.append("=?");
				}

				sb.append(" where ");
				sb.append(idProp);
				sb.append("=?");

				if (logger.isDebugEnabled()) {
					logger.debug("sql:" + sb.toString());
				}

				PreparedStatement pstmt = connection.prepareStatement(sb.toString());
				for (T bean : beanList) {
					JXPathContext xpathCtx = JXPathContext.newContext(bean);
					int i = 1;
					for (String prop : set) {
						pstmt.setObject(i++, xpathCtx.getValue(prop));
					}

					pstmt.setObject(i, xpathCtx.getValue(idProp));

					pstmt.addBatch();
				}

				try {
					int[] numUpdates = pstmt.executeBatch();
					int r = 0;
					for (int n : numUpdates) {
						r += n;
					}

					return r;
				} finally {
					CloseUtil.close(pstmt);
				}
			}

		});
	}

	public void batchAdd(final List<T> beanList) {
		getHibernateTemplate().doWork(new Work() {
			public void execute(Connection connection) throws SQLException {
				if (beanList.isEmpty()) {
					return;
				}

				StringBuffer sb = new StringBuffer();

				sb.append("insert into ");
				sb.append(tableName);
				sb.append("(");

				boolean first = true;
				for (String propName : beanPropNames) {
					if (first) {
						first = false;
					} else {
						sb.append(",");
					}

					sb.append(propName);
				}

				sb.append(") values");

				List<Object> paras = new ArrayList<Object>();
				boolean f = true;
				for (T bean : beanList) {
					if (f) {
						f = false;
					} else {
						sb.append(",");
					}

					sb.append("(");
					JXPathContext xpathCtx = JXPathContext.newContext(bean);
					first = true;
					for (String propName : beanPropNames) {
						if (first) {
							first = false;
						} else {
							sb.append(",");
						}

						sb.append("?");
						paras.add(xpathCtx.getValue(propName));
					}

					sb.append(")");
				}

				if (logger.isDebugEnabled()) {
					logger.debug("sql:" + sb.toString());
				}

				PreparedStatement pstmt = connection.prepareStatement(sb.toString());
				for (int i = 0; i < paras.size(); i++) {
					pstmt.setObject(i + 1, paras.get(i));
				}

				try {
					pstmt.executeUpdate();
				} finally {
					CloseUtil.close(pstmt);
				}
			}

		});
	}

}
