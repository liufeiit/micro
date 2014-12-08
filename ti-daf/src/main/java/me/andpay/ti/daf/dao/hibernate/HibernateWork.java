package me.andpay.ti.daf.dao.hibernate;

import org.hibernate.Session;

/**
 * Hibernate回调接口定义类。
 * 
 * @author sea.bao
 */
public interface HibernateWork {
	void doInHibernate(Session session);
}
