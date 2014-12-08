package me.andpay.ti.daf.dao.hibernate;

import org.hibernate.Session;

/**
 * Hibernate返回工作接口定义类。
 * 
 * @author sea.bao
 * 
 * @param <T>
 */
public interface HibernateReturningWork<T> {
	T doInHibernate(Session session);
}
