package me.andpay.ti.daf.dao.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Hibernate实现的访问接口定义类。
 * 
 * @author sea.bao
 */
public class HibernateDaoSupport {
	/**
	 * HibernateTemplate
	 */
	private HibernateTemplate hibernateTemplate;

	/**
	 * 会话工厂
	 */
	private SessionFactory sessionFactory;

	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	protected HibernateTemplate getHibernateTemplate() {
		if (hibernateTemplate == null) {
			synchronized (this) {
				if (hibernateTemplate == null) {
					if (sessionFactory == null) {
						throw new RuntimeException("Need setSessionFactory firstly.");
					}

					this.hibernateTemplate = new DefaultHibernateTemplate(sessionFactory);
				}
			}
		}

		return hibernateTemplate;
	}

	@Autowired(required = false)
	public void setHibernateTemplate(@Qualifier("hibernateTemplate") HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Autowired(required = false)
	public void setSessionFactory(@Qualifier("hibernateSessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
