package me.andpay.ti.daf.dao.hibernate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import me.andpay.ti.base.AppBizException;
import me.andpay.ti.daf.dao.JdbcCallback;
import me.andpay.ti.daf.dao.JdbcDao;
import me.andpay.ti.daf.dao.ResultSetCallback;
import me.andpay.ti.daf.dao.SqlCommand;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * 基于Hibernate的JdbcDao类。
 * 
 * @author sea.bao
 */
public class HibernateJdbcDao extends HibernateDaoSupport implements JdbcDao {
	private PreparedStatement prepareStatement(Connection conn, SqlCommand sqlCmd) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sqlCmd.getClause());
		int i = 1;
		for (Iterator<Object> it = sqlCmd.getParameters().iterator(); it.hasNext(); i++) {
			Object para = it.next();
			if (para instanceof java.util.Date) {
				para = new java.sql.Date(((java.util.Date) para).getTime());
			}

			pstmt.setObject(i, para);
		}

		return pstmt;
	}

	public void iterate(final SqlCommand sqlCmd, final ResultSetCallback resultSetCallback) throws AppBizException {
		AppBizException e = (AppBizException) this.getHibernateTemplate().doReturningWork(new HibernateReturningWork<Object>() {
			public Object doInHibernate(Session session) throws HibernateException {
				return session.doReturningWork(new ReturningWork<Object>() {
					public Object execute(Connection connection) throws SQLException {
						PreparedStatement pstmt = null;
						ResultSet rs = null;

						try {
							pstmt = prepareStatement(connection, sqlCmd);
							rs = pstmt.executeQuery();

							try {
								resultSetCallback.doInResultSet(rs);
							} catch (AppBizException e) {
								return e;
							}

							return null;
						} finally {
							if (rs != null) {
								try {
									rs.close();
								} catch (SQLException e) {
								}
							}

							if (pstmt != null) {
								try {
									pstmt.close();
								} catch (SQLException e) {
								}
							}
						}
					}

				});

			}
		});

		if (e != null) {
			throw e;
		}
	}

	public long executeUpdate(final SqlCommand sqlCmd) {
		Long retValue = this.getHibernateTemplate().doReturningWork(new HibernateReturningWork<Long>() {
			public Long doInHibernate(Session session) throws HibernateException {
				return session.doReturningWork(new ReturningWork<Long>() {
					public Long execute(Connection connection) throws SQLException {
						PreparedStatement pstmt = null;

						try {
							pstmt = prepareStatement(connection, sqlCmd);
							return new Long(pstmt.executeUpdate());
						} finally {
							if (pstmt != null) {
								try {
									pstmt.close();
								} catch (SQLException e) {
								}
							}
						}
					}
				});

			}
		});

		return retValue.intValue();
	}

	private class ContainException extends RuntimeException {
		public static final long serialVersionUID = 0x01;

		public ContainException(Throwable cause) {
			super(cause);
		}
	}

	public <T> T executeJdbc(final JdbcCallback<T> callback) throws AppBizException {
		try {
			return this.getHibernateTemplate().doReturningWork(new HibernateReturningWork<T>() {
				public T doInHibernate(Session session) throws HibernateException {
					return session.doReturningWork(new ReturningWork<T>() {
						public T execute(Connection connection) throws SQLException {
							try {
								return callback.doInJdbc(connection);
							} catch (AppBizException e) {
								throw new ContainException(e);
							}
						}
					});
				}
			});
		} catch (ContainException e) {
			throw (AppBizException) e.getCause();
		}
	}

	public void flushSession() {
		HibernateTemplate ht = this.getHibernateTemplate();
		ht.flush();
	}

	@Autowired(required = false)
	public void setSessionFactory2(@Qualifier("hibernateSessionFactory") SessionFactory sf) {
		super.setSessionFactory(sf);
	}
}