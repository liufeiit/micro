package me.andpay.ti.daf.dao.hibernate;

import me.andpay.ti.daf.dao.SessionContext;

import org.hibernate.Session;

/**
 * HibernateSession上下文类。
 * 
 * @author sea.bao
 */
public class HibernateSessionContext implements SessionContext {
	private Session session;

	public HibernateSessionContext(Session session) {
		this.session = session;
	}

	public void update(Object obj) {
		session.update(obj);
	}

	public void evict(Object obj) {
		session.evict(obj);
	}

	public void flush() {
		session.flush();
	}
}
