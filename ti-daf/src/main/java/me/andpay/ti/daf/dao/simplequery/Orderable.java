package me.andpay.ti.daf.dao.simplequery;

import me.andpay.ti.daf.dao.simplequery.annotation.Ignore;

/**
 * 可排序接口类。
 * 
 * @author sea.bao
 */
public interface Orderable {
	@Ignore
	public String getOrders();
}