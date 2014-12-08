package me.andpay.ti.daf.dao.hibernate;

import java.io.Serializable;

import me.andpay.ti.daf.dao.Sla;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * 缺省HibernateTemplate类。
 * 
 * @author sea.bao
 */
public class DefaultHibernateTemplate implements HibernateTemplate {
	/**
	 * 会话工厂
	 */
	private SessionFactory sessionFactory;

	private class SessionHolder {
		Session session;

		boolean needClose = false;
	}

	private SessionHolder getSession() {
		SessionHolder holder = new SessionHolder();

		try {
			holder.session = sessionFactory.getCurrentSession();
		} catch (Throwable e) {
			holder.session = sessionFactory.openSession();
			holder.needClose = true;
		}

		return holder;
	}

	public DefaultHibernateTemplate() {
	}

	public DefaultHibernateTemplate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void doWork(HibernateWork work) {
		SessionHolder holder = getSession();
		try {
			work.doInHibernate(holder.session);
			if (holder.needClose) {
				if (holder.session.getTransaction().isActive()) {
					holder.session.getTransaction().commit();
				} else {
					holder.session.flush();
				}
			}
		} finally {
			if (holder.needClose) {
				holder.session.close();
			}
		}
	}

	public <T> T doReturningWork(HibernateReturningWork<T> work) {
		SessionHolder holder = getSession();
		try {
			T retObj = work.doInHibernate(holder.session);
			if (holder.needClose) {
				if (holder.session.getTransaction().isActive()) {
					holder.session.getTransaction().commit();
				} else {
					holder.session.flush();
				}
			}

			return retObj;
		} finally {
			if (holder.needClose) {
				holder.session.close();
			}
		}
	}

	public boolean delete(final Object obj) {
		return doReturningWork(new HibernateReturningWork<Boolean>() {
			public Boolean doInHibernate(Session session) {
				session.delete(obj);
				return true;
			}
		});
	}

	public boolean delete(final String entityName, final Object obj) {
		return doReturningWork(new HibernateReturningWork<Boolean>() {
			public Boolean doInHibernate(Session session) {
				session.delete(entityName, obj);
				return true;
			}
		});
	}

	public <T> T doReturningWork(final ReturningWork<T> work) {
		return doReturningWork(new HibernateReturningWork<T>() {
			public T doInHibernate(Session session) {
				return session.doReturningWork(work);
			}
		});
	}

	public void doWork(final Work work) {
		doWork(new HibernateWork() {
			public void doInHibernate(Session session) {
				session.doWork(work);
			}
		});
	}

	public void evict(final Object obj) {
		doWork(new HibernateWork() {
			public void doInHibernate(Session session) {
				session.evict(obj);
			}
		});
	}

	public void evictEntityRegion(final Class<?> clazz) {
		doWork(new HibernateWork() {
			public void doInHibernate(Session session) {
				session.getSessionFactory().getCache().evictEntityRegion(clazz);
			}
		});
	}

	public void flush() {
		doWork(new HibernateWork() {
			public void doInHibernate(Session session) {
				session.flush();
			}
		});
	}

	public <T> T get(final Class<T> clazz, final Serializable id) {
		return doReturningWork(new HibernateReturningWork<T>() {
			@SuppressWarnings("unchecked")
			public T doInHibernate(Session session) {
				return (T) session.get(clazz, id);
			}
		});
	}

	public <T> T get(final Class<T> clazz, final Serializable id, final LockOptions lockOptions) {
		return doReturningWork(new HibernateReturningWork<T>() {
			@SuppressWarnings("unchecked")
			public T doInHibernate(Session session) {
				return (T) session.get(clazz, id, lockOptions);
			}
		});
	}

	public Object get(final String entityName, final Serializable id) {
		return doReturningWork(new HibernateReturningWork<Object>() {
			public Object doInHibernate(Session session) {
				return session.get(entityName, id);
			}
		});
	}

	public Object get(final String entityName, final Serializable id, final LockOptions lockOptions) {
		return doReturningWork(new HibernateReturningWork<Object>() {
			public Object doInHibernate(Session session) {
				return session.get(entityName, id, lockOptions);
			}
		});
	}

	public <T> T load(final Class<T> theClass, final Serializable id) {
		return doReturningWork(new HibernateReturningWork<T>() {
			@SuppressWarnings("unchecked")
			public T doInHibernate(Session session) {
				return (T) session.load(theClass, id);
			}
		});
	}

	public <T> T load(final Class<T> theClass, final Serializable id, final LockOptions lockOptions) {
		return doReturningWork(new HibernateReturningWork<T>() {
			@SuppressWarnings("unchecked")
			public T doInHibernate(Session session) {
				return (T) session.load(theClass, id, lockOptions);
			}
		});
	}

	public void load(final Object object, final Serializable id) {
		doWork(new HibernateWork() {
			public void doInHibernate(Session session) {
				session.load(object, id);
			}
		});
	}

	public Object load(final String entityName, final Serializable id) {
		return doReturningWork(new HibernateReturningWork<Object>() {
			public Object doInHibernate(Session session) {
				return session.load(entityName, id);
			}
		});
	}

	public Object load(final String entityName, final Serializable id, final LockOptions lockOptions) {
		return doReturningWork(new HibernateReturningWork<Object>() {
			public Object doInHibernate(Session session) {
				return session.load(entityName, id, lockOptions);
			}
		});
	}

	public <T> T merge(final T object) {
		return doReturningWork(new HibernateReturningWork<T>() {
			@SuppressWarnings("unchecked")
			public T doInHibernate(Session session) {
				return (T) session.merge(object);
			}
		});
	}

	public <T> T merge(final String entityName, final T object) {
		return doReturningWork(new HibernateReturningWork<T>() {
			@SuppressWarnings("unchecked")
			public T doInHibernate(Session session) {
				return (T) session.merge(entityName, object);
			}
		});
	}

	public void persist(final Object object) {
		doWork(new HibernateWork() {
			public void doInHibernate(Session session) {
				session.persist(object);
			}
		});
	}

	public void persist(final String entityName, final Object object) {
		doWork(new HibernateWork() {
			public void doInHibernate(Session session) {
				session.persist(entityName, object);
			}
		});
	}

	public void refresh(final Object object) {
		doWork(new HibernateWork() {
			public void doInHibernate(Session session) {
				session.refresh(object);
			}
		});
	}

	public void refresh(final Object object, final LockOptions lockOptions) {
		doWork(new HibernateWork() {
			public void doInHibernate(Session session) {
				session.refresh(object, lockOptions);
			}
		});
	}

	public void refresh(final String entityName, final Object object) {
		doWork(new HibernateWork() {
			public void doInHibernate(Session session) {
				session.refresh(entityName, object);
			}
		});
	}

	public void refresh(final String entityName, final Object object, final LockOptions lockOptions) {
		doWork(new HibernateWork() {
			public void doInHibernate(Session session) {
				session.refresh(entityName, object, lockOptions);
			}
		});
	}

	public void replicate(final Object object, final ReplicationMode replicationMode) {
		doWork(new HibernateWork() {
			public void doInHibernate(Session session) {
				session.replicate(object, replicationMode);
			}
		});
	}

	public void replicate(final String entityName, final Object object, final ReplicationMode replicationMode) {
		doWork(new HibernateWork() {
			public void doInHibernate(Session session) {
				session.replicate(entityName, object, replicationMode);
			}
		});
	}

	public Serializable save(final Object object) {
		return doReturningWork(new HibernateReturningWork<Serializable>() {
			public Serializable doInHibernate(Session session) {
				return session.save(object);
			}
		});
	}

	public Serializable save(final String entityName, final Object object) {
		return doReturningWork(new HibernateReturningWork<Serializable>() {
			public Serializable doInHibernate(Session session) {
				return session.save(entityName, object);
			}
		});
	}

	public void saveOrUpdate(final Object object) {
		doWork(new HibernateWork() {
			public void doInHibernate(Session session) {
				session.saveOrUpdate(object);
			}
		});
	}

	public void saveOrUpdate(final String entityName, final Object object) {
		doWork(new HibernateWork() {
			public void doInHibernate(Session session) {
				session.saveOrUpdate(entityName, object);
			}
		});
	}

	public void update(final Object object) {
		doWork(new HibernateWork() {
			public void doInHibernate(Session session) {
				session.update(object);
			}
		});
	}

	public void update(final String entityName, final Object object) {
		doWork(new HibernateWork() {
			public void doInHibernate(Session session) {
				session.update(entityName, object);
			}
		});
	}

	public boolean isInTransaction() {
		return doReturningWork(new HibernateReturningWork<Boolean>() {
			public Boolean doInHibernate(Session session) {
				Transaction txn = session.getTransaction();
				if (txn == null || txn.isActive() == false) {
					return false;
				}

				return true;
			}
		});
	}

	public long bulkUpdate(final String clause, final Object... paras) {
		return doReturningWork(new HibernateReturningWork<Long>() {
			public Long doInHibernate(Session session) {
				Query queryObject = session.createQuery(clause);
				queryObject.setMaxResults(1);

				if (paras != null) {
					for (int i = 0; i < paras.length; i++) {
						queryObject.setParameter(i, paras[i]);
					}
				}

				BadSqlTracker tracker = new BadSqlTracker(clause, paras);
				tracker.start(Sla.getAvgTime());

				try {
					return (long) queryObject.executeUpdate();
				} finally {
					tracker.stop();
				}
			}
		});
	}

	@Autowired(required = false)
	public void setSessionFactory(@Qualifier("hibernateSessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
