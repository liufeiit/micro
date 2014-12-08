package me.andpay.ti.daf.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import me.andpay.ti.daf.dao.CrudDao;

import org.apache.commons.jxpath.JXPathContext;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.metadata.ClassMetadata;

/**
 * Hibernate实现的Dao类。
 * 
 * @author seabao
 * 
 * @param <T>
 * @param <I>
 */
public class HibernateCrudDao<T, I extends Serializable> extends HibernateDaoSupport implements CrudDao<T, I> {
	protected Class<? extends T> clazz;

	private static LockOptions updateLockOptions = new LockOptions(LockMode.PESSIMISTIC_WRITE);
	
	@SuppressWarnings("unchecked")
	public HibernateCrudDao() {
		clazz = (Class<? extends T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void flush() {
		getHibernateTemplate().flush();
	}

	public void evict(T o) {
		getHibernateTemplate().evict(o);
	}

	@SuppressWarnings("unchecked")
	public I add(T o) {
		return (I) getHibernateTemplate().save(o);
	}

	public boolean delete(final I id) {
		return getHibernateTemplate().doReturningWork(new HibernateReturningWork<Boolean>() {
			public Boolean doInHibernate(Session session) {
				ClassMetadata clazzMetaData = session.getSessionFactory().getClassMetadata(clazz);
				StringBuffer sb = new StringBuffer("delete from ");
				sb.append(clazzMetaData.getEntityName());
				sb.append(" entity where ");
				sb.append("entity.");
				sb.append(clazzMetaData.getIdentifierPropertyName());
				sb.append("=?");
				Query query = session.createQuery(sb.toString());
				query.setParameter(0, id);
				return query.executeUpdate() == 1;
			}
		});
	}

	public boolean delete(T o) {
		return getHibernateTemplate().delete(o);
	}

	public T get(I id) {
		return (T) getHibernateTemplate().get(clazz, id);
	}

	public T load(I id) {
		return (T) getHibernateTemplate().load(clazz, id);
	}

	public void update(T o) {
		getHibernateTemplate().update(o);
	}

	public T getForUpdate(final I id) {
		HibernateTemplate ht = getHibernateTemplate();
		if ( ht.isInTransaction() == false ) {
			throw new RuntimeException("getForUpdate must be called in tx.");
		}
		
		ht.flush();
		return ht.get(clazz, id, updateLockOptions);
	}

	public T getForUpdateNoWait(final I id) {
		HibernateTemplate ht = getHibernateTemplate();
		if ( ht.isInTransaction() == false ) {
			throw new RuntimeException("getForUpdate must be called in tx.");
		}
		
		ht.flush();
		
		LockOptions lockOptions = new LockOptions(LockMode.UPGRADE_NOWAIT);
		return ht.get(clazz, id, lockOptions);
	}

	public T refresh(T o) {
		getHibernateTemplate().refresh(o);
		return o;
	}

	public T refreshForUpdate(final T o) {
		HibernateTemplate ht = getHibernateTemplate();
		if ( ht.isInTransaction() == false ) {
			throw new RuntimeException("getForUpdate must be called in tx.");
		}
		
		ht.flush();
		ht.refresh(o, LockOptions.UPGRADE);

		return o;
	}

	public T loadForUpdate(final I id) {
		HibernateTemplate ht = getHibernateTemplate();
		if ( ht.isInTransaction() == false ) {
			throw new RuntimeException("getForUpdate must be called in tx.");
		}
		
		ht.flush();
		return ht.load(clazz, id, updateLockOptions);
	}
	
	public boolean update(final T o, final String... propertyNames) {
		HibernateTemplate ht = getHibernateTemplate();
		return ht.doReturningWork(new HibernateReturningWork<Boolean>() {
			public Boolean doInHibernate(Session session) {
				StringBuffer sb = new StringBuffer();
				sb.append("update " + clazz.getName() + " entity set ");
				boolean first = true;
				List<Object> paras = new ArrayList<Object>();
				JXPathContext xpathCtx = JXPathContext.newContext(o);
				for ( String propertyName : propertyNames ) {
					if ( first ) {
						first = false;
					} else {
						sb.append(", ");
					}
					
					Object propValue = xpathCtx.getValue(propertyName);
					sb.append("entity.");
					sb.append(propertyName);
					sb.append("=?");
					paras.add(propValue);
				}
				
				ClassMetadata clazzMeta = session.getSessionFactory().getClassMetadata(clazz);
				String idPropName = clazzMeta.getIdentifierPropertyName();
				Object id = xpathCtx.getValue(idPropName);;
				sb.append("where entity.");
				sb.append(idPropName);
				sb.append("=?");
				paras.add(id);
				
				Query query = session.createQuery(sb.toString());
				
				for ( int i=0; i < paras.size(); i++ ) {
					query.setParameter(i, paras.get(i));
				}
				
				return query.executeUpdate() == 1;
			}
		});
	}

	public boolean update(final I id, final Map<String, Object> props) {
		HibernateTemplate ht = getHibernateTemplate();
		return ht.doReturningWork(new HibernateReturningWork<Boolean>() {
			public Boolean doInHibernate(Session session) {
				StringBuffer sb = new StringBuffer();
				sb.append("update " + clazz.getName() + " entity set ");
				boolean first = true;
				List<Object> paras = new ArrayList<Object>();
				for ( Map.Entry<String, Object> entry : props.entrySet() ) {
					if ( first ) {
						first = false;
					} else {
						sb.append(", ");
					}
					
					sb.append("entity.");
					sb.append(entry.getKey());
					sb.append("=?");
					paras.add(entry.getValue());
				}
				
				ClassMetadata clazzMeta = session.getSessionFactory().getClassMetadata(clazz);
				String idPropName = clazzMeta.getIdentifierPropertyName();
				sb.append("where entity.");
				sb.append(idPropName);
				sb.append("=?");
				paras.add(id);
				
				Query query = session.createQuery(sb.toString());
				
				for ( int i=0; i < paras.size(); i++ ) {
					query.setParameter(i, paras.get(i));
				}
				
				return query.executeUpdate() == 1;
			}
		});
	}

}
