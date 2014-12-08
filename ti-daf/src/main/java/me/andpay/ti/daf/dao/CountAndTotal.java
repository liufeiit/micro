package me.andpay.ti.daf.dao;

import java.math.BigDecimal;

/**
 * 总笔数和总金额
 * 
 * @author sea.bao
 */
public class CountAndTotal {
	/**
	 * 总笔数
	 */
	private long count;
	
	/**
	 * 总金额
	 */
	private BigDecimal total;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
}
