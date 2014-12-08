package me.andpay.ti.daf.dao;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 路由数据源实现类
 * 
 * @author alex
 */
public class RoutingDataSource extends AbstractRoutingDataSource {
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		// 根据当前线程的LookupKey查找数据源
		return DataSourceLookupKeyHandler.getCurrentLookupKey();
	}
}
