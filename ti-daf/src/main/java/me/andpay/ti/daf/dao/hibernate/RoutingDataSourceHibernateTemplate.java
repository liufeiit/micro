package me.andpay.ti.daf.dao.hibernate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import me.andpay.ti.daf.dao.DataSourceLookupKeyHandler;

import org.springframework.beans.factory.FactoryBean;

/**
 * 用于多数据源的Hibernate模板类
 * 
 * @author alex
 */
public class RoutingDataSourceHibernateTemplate implements InvocationHandler, FactoryBean<HibernateTemplate> {
	/**
	 * 数据库搜索关键字
	 */
	private Object dataSourceLookupKey;

	/**
	 * Hibernate模板类
	 */
	private HibernateTemplate hibernateTemplate;

	/**
	 * {@inheritDoc}
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		DataSourceLookupKeyHandler.setCurrentLookupKey(dataSourceLookupKey);
		try {
			return method.invoke(hibernateTemplate, args);
		} finally {
			DataSourceLookupKeyHandler.clearCurrentLookupKey();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public HibernateTemplate getObject() throws Exception {
		return (HibernateTemplate) Proxy.newProxyInstance(this.getClass().getClassLoader(),
				new Class<?>[] { HibernateTemplate.class }, this);
	}

	/**
	 * {@inheritDoc}
	 */
	public Class<?> getObjectType() {
		return (hibernateTemplate != null ? hibernateTemplate.getClass() : HibernateTemplate.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isSingleton() {
		return false;
	}

	/**
	 * @param dataSourceLookupKey the dataSourceLookupKey to set
	 */
	public void setDataSourceLookupKey(Object dataSourceLookupKey) {
		this.dataSourceLookupKey = dataSourceLookupKey;
	}
	
	/**
	 * @param hibernateTemplate
	 *            the hibernateTemplate to set
	 */
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
}
