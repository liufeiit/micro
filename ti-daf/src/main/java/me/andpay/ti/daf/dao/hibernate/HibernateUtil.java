package me.andpay.ti.daf.dao.hibernate;

import java.beans.BeanInfo;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Id;

import me.andpay.ti.base.AppBizException;
import me.andpay.ti.daf.dao.IterateCallback;
import me.andpay.ti.daf.dao.Sla;
import me.andpay.ti.util.CloseUtil;

import org.apache.commons.jxpath.JXPathContext;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;

/**
 * Hibernate工具类。
 * 
 * @author sea.bao
 */
public class HibernateUtil {
	private HibernateUtil() {
	}

	public static Object getId(Object obj) {
		Class<?> clazz = obj.getClass();
		BeanInfo info;

		try {
			info = java.beans.Introspector.getBeanInfo(clazz);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
			Method m = pd.getReadMethod();
			if (m == null)
				continue;

			if (m.isAnnotationPresent(Id.class)) {
				try {
					return m.invoke(obj);
				} catch (Exception e) {
					throw new RuntimeException(e.getMessage(), e);
				}
			}
		}

		return null;
	}

	public static void iterateElement(HibernateTemplate hibernateTemp, final HQLQuery hqlQuery,
			final IterateCallback<?> callback, final int fetchSize, final boolean forUpdate) throws AppBizException {
		Object e = hibernateTemp.doReturningWork(new HibernateReturningWork<Object>() {
			@SuppressWarnings("unchecked")
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hqlQuery.getClause());
				if (forUpdate) {
					query.setLockOptions(LockOptions.UPGRADE);
				}

				int pos = 0;
				for (Object arg : hqlQuery.getHQLArgs()) {
					query.setParameter(pos++, arg);
				}

				BadSqlTracker tracker = new BadSqlTracker(hqlQuery.getClause(), hqlQuery.getHQLArgs());
				tracker.start(Sla.getAvgTime());
				boolean stopFlag = false;
				try {
					ScrollableResults cursor = query.setFetchSize(fetchSize).scroll(ScrollMode.FORWARD_ONLY);

					try {
						while (cursor.next()) {
							Object element = cursor.get()[0];

							if (stopFlag == false) {
								// 遍历处理，以第一笔记录返回作为Sla标准
								tracker.stop();
								stopFlag = true;
							}

							HibernateSessionContext sc = new HibernateSessionContext(session);
							if (((IterateCallback<Object>) callback).doElement(sc, element) == false) {
								return null;
							}

							if (session.isDirty()) {
								session.flush();
							}

							session.evict(element);
						}
					} catch (AppBizException e) {
						return e;
					} finally {
						CloseUtil.close(cursor);
					}
				} finally {
					if (stopFlag == false) {
						tracker.stop();
					}
				}

				return null;
			}
		});

		if (e != null && e instanceof AppBizException) {
			throw (AppBizException) e;
		}
	}

	public static void quickIterateElement(HibernateTemplate hibernateTemp, final HQLQuery hqlQuery,
			@SuppressWarnings("rawtypes") final IterateCallback callback, final List<String> props,
			final Class<?> beanClazz) throws AppBizException {
		Object e = hibernateTemp.doReturningWork(new HibernateReturningWork<Object>() {
			@SuppressWarnings("unchecked")
			public Object doInHibernate(Session session) throws HibernateException {
				if (session.isDirty()) {
					session.flush();
				}

				Query query = session.createQuery(hqlQuery.getClause());
				int pos = 0;
				for (Object arg : hqlQuery.getHQLArgs()) {
					query.setParameter(pos++, arg);
				}

				Iterator<Object> it = query.iterate();

				BadSqlTracker tracker = new BadSqlTracker(hqlQuery.getClause(), hqlQuery.getHQLArgs());
				tracker.start(Sla.getAvgTime());
				boolean stopFlag = false;
				try {
					try {
						while (it.hasNext()) {
							Object[] propValues = (Object[]) it.next();

							if (stopFlag == false) {
								// 遍历处理，以第一笔记录返回作为Sla标准
								tracker.stop();
								stopFlag = true;
							}

							Object bean = null;
							try {
								bean = beanClazz.newInstance();
							} catch (Exception e) {
								throw new RuntimeException(e.getMessage(), e);
							}

							JXPathContext jxpathCtx = JXPathContext.newContext(bean);

							for (int i = 0; i < propValues.length; i++) {
								jxpathCtx.setValue(props.get(i), propValues[i]);
							}

							HibernateSessionContext sc = new HibernateSessionContext(session);
							if (callback.doElement(sc, bean) == false) {
								return null;
							}

							if (session.isDirty()) {
								session.flush();
							}
						}
					} catch (AppBizException e) {
						return e;
					} finally {
						Hibernate.close(it);
					}
				} finally {
					if (stopFlag == false) {
						tracker.stop();
					}
				}

				return null;
			}
		});

		if (e != null && e instanceof AppBizException) {
			throw (AppBizException) e;
		}
	}

	public static long countElements(HibernateTemplate hibernateTemp, final HQLQuery hqlQuery) {
		return hibernateTemp.doReturningWork(new HibernateReturningWork<Long>() {
			public Long doInHibernate(Session session) throws HibernateException {
				StringBuffer countSql = new StringBuffer();
				countSql.append("select count(*) ");
				countSql.append("from ");
				countSql.append(hqlQuery.getFromClause());
				if (hqlQuery.getWhereClause() != null && hqlQuery.getWhereClause().equals("") == false) {
					countSql.append(" where ");
					countSql.append(hqlQuery.getWhereClause());
				}

				Query query = session.createQuery(countSql.toString());
				Object[] hqlArgs = hqlQuery.getHQLArgs();
				if (hqlArgs != null) {
					for (int i = 0; i < hqlArgs.length; i++) {
						query.setParameter(i, hqlArgs[i]);
					}
				}

				BadSqlTracker tracker = new BadSqlTracker(hqlQuery.getClause(), hqlQuery.getHQLArgs());
				tracker.start(Sla.getAvgTime());

				try {
					List<?> l = query.list();

					return (Long) l.get(0);
				} finally {
					tracker.stop();
				}
			}
		}).longValue();
	}

	public static boolean existsElement(HibernateTemplate hibernateTemp, final HQLQuery hqlQuery) {
		return hibernateTemp.doReturningWork(new HibernateReturningWork<Boolean>() {
			public Boolean doInHibernate(Session session) throws HibernateException {
				StringBuffer countSql = new StringBuffer();
				countSql.append("select 1 ");
				countSql.append("from ");
				countSql.append(hqlQuery.getFromClause());
				if (hqlQuery.getWhereClause() != null && hqlQuery.getWhereClause().equals("") == false) {
					countSql.append(" where ");
					countSql.append(hqlQuery.getWhereClause());
				}

				Query query = session.createQuery(countSql.toString());
				query.setFirstResult(0);
				query.setMaxResults(1);

				Object[] hqlArgs = hqlQuery.getHQLArgs();
				if (hqlArgs != null) {
					for (int i = 0; i < hqlArgs.length; i++) {
						query.setParameter(i, hqlArgs[i]);
					}
				}

				BadSqlTracker tracker = new BadSqlTracker(hqlQuery.getClause(), hqlQuery.getHQLArgs());
				tracker.start(Sla.getAvgTime());

				try {
					return (query.list().isEmpty() == false);
				} finally {
					tracker.stop();
				}
			}
		});
	}

	public static List<?> queryElements(HibernateTemplate hibernateTemp, final HQLQuery hqlQuery,
			final long firstResult, final long maxResults) {
		return hibernateTemp.doReturningWork(new HibernateReturningWork<List<?>>() {
			public List<?> doInHibernate(Session session) throws HibernateException {
				Query query = HibernateUtil.createQuery(session, hqlQuery);
				query.setFirstResult((int) firstResult);
				if (maxResults != -1) {
					query.setMaxResults((int) maxResults);
				}

				BadSqlTracker tracker = new BadSqlTracker(hqlQuery.getClause(), hqlQuery.getHQLArgs());
				tracker.start(Sla.getAvgTime());

				try {
					return query.list();
				} finally {
					tracker.stop();
				}
			}
		});
	}

	public static List<?> loadElementsForUpdate(HibernateTemplate hibernateTemp, final HQLQuery hqlQuery,
			final String lockAlias) {
		return hibernateTemp.doReturningWork(new HibernateReturningWork<List<?>>() {
			public List<?> doInHibernate(Session session) throws HibernateException {
				Query query = HibernateUtil.createQuery(session, hqlQuery);
				query.setLockMode(lockAlias, LockMode.PESSIMISTIC_WRITE);

				BadSqlTracker tracker = new BadSqlTracker(hqlQuery.getClause(), hqlQuery.getHQLArgs());
				tracker.start(Sla.getAvgTime());

				try {
					return query.list();
				} finally {
					tracker.stop();
				}
			}
		});
	}

	@SuppressWarnings("unchecked")
	public static List<?> queryElements1PlusN(HibernateTemplate hibernateTemp, final HQLQuery hqlQuery,
			final int firstResult, final int maxResults) {
		return hibernateTemp.doReturningWork(new HibernateReturningWork<List<?>>() {
			public List<?> doInHibernate(Session session) throws HibernateException {
				Query query = HibernateUtil.createQuery(session, hqlQuery);
				query.setFirstResult(firstResult);
				if (maxResults != -1) {
					query.setMaxResults(maxResults);
				}

				BadSqlTracker tracker = new BadSqlTracker(hqlQuery.getClause(), hqlQuery.getHQLArgs());
				tracker.start(Sla.getAvgTime());
				try {
					Iterator<Object> it = query.iterate();

					try {
						List<Object> l = new ArrayList<Object>();
						while (it.hasNext()) {
							l.add(it.next());
						}

						return l;
					} finally {
						Hibernate.close(it);
					}
				} finally {
					tracker.stop();
				}
			}
		});
	}

	@SuppressWarnings("unchecked")
	public static Object firstElement(HibernateTemplate hibernateTemp, final HQLQuery hqlQuery) {
		return hibernateTemp.doReturningWork(new HibernateReturningWork<Object>() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hqlQuery.getClause());
				int pos = 0;
				for (Object arg : hqlQuery.getHQLArgs()) {
					query.setParameter(pos++, arg);
				}

				BadSqlTracker tracker = new BadSqlTracker(hqlQuery.getClause(), hqlQuery.getHQLArgs());
				tracker.start(Sla.getAvgTime());
				try {
					Iterator<Object> it = query.iterate();

					try {
						if (it.hasNext()) {
							return it.next();
						} else {
							return null;
						}
					} finally {
						Hibernate.close(it);
					}
				} finally {
					tracker.stop();
				}
			}
		});
	}

	public static Query createQuery(Session session, HQLQuery hqlQuery) throws HibernateException {
		Query query = session.createQuery(hqlQuery.getClause());
		Object[] hqlArgs = hqlQuery.getHQLArgs();
		if (hqlArgs != null) {
			for (int i = 0; i < hqlArgs.length; i++) {
				query.setParameter(i, hqlArgs[i]);
			}
		}

		return query;
	}
}