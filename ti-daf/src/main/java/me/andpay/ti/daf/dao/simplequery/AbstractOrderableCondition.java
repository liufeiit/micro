package me.andpay.ti.daf.dao.simplequery;

import java.io.Serializable;

import me.andpay.ti.daf.dao.simplequery.annotation.Ignore;


/**
 * 排序条件类。
 * 
 * @author sea.bao
 */
public class AbstractOrderableCondition implements Orderable, Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 排序语句
	 */
	private String orders;

	public void setOrders(String orders) {
		this.orders = orders;
	}

	@Ignore
	public String getOrders() {
		return this.orders;
	}
}