package me.andpay.ti.daf.dao.hibernate;

import java.io.Serializable;

import org.hibernate.LockOptions;
import org.hibernate.ReplicationMode;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.jdbc.Work;

/**
 * Hibernate模版接口定义类
 * 
 * @author sea.bao
 */
public interface HibernateTemplate {
	void doWork(HibernateWork work);

	<T> T doReturningWork(HibernateReturningWork<T> work);

	boolean delete(Object obj);

	boolean delete(String entityName, Object obj);

	<T> T doReturningWork(ReturningWork<T> work);

	void doWork(Work work);

	void evict(Object obj);

	void evictEntityRegion(Class<?> clazz);
	
	void flush();

	<T> T get(Class<T> clazz, Serializable id);

	<T> T get(Class<T> clazz, Serializable id, LockOptions lockOptions);

	Object get(String entityName, Serializable id);

	Object get(String entityName, Serializable id, LockOptions lockOptions);

	<T> T load(Class<T> theClass, Serializable id);

	<T> T load(Class<T> theClass, Serializable id, LockOptions lockOptions);

	void load(Object object, Serializable id);

	Object load(String entityName, Serializable id);

	Object load(String entityName, Serializable id, LockOptions lockOptions);

	<T> T merge(T object);

	<T> T merge(String entityName, T object);

	void persist(Object object);

	void persist(String entityName, Object object);

	void refresh(Object object);

	void refresh(Object object, LockOptions lockOptions);

	void refresh(String entityName, Object object);

	void refresh(String entityName, Object object, LockOptions lockOptions);

	void replicate(Object object, ReplicationMode replicationMode);

	void replicate(String entityName, Object object, ReplicationMode replicationMode);

	Serializable save(Object object);

	Serializable save(String entityName, Object object);

	void saveOrUpdate(Object object);

	void saveOrUpdate(String entityName, Object object);

	void update(Object object);

	void update(String entityName, Object object);
	
	long bulkUpdate(String clause, Object... paras);
	
	boolean isInTransaction();
}
