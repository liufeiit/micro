package me.andpay.ti.daf.dao.simplequery;

/**
 * 排序项目类
 * 
 * @author sea.bao
 */
public class OrderItem {
	/**
	 * 排序属性
	 */
	private String orderByPropName;

	/**
	 * 逆序标志
	 */
	private boolean orderDesc = false;

	public OrderItem() {	
	}
	
	public OrderItem(String orderByPropName, boolean orderDesc) {
		this.orderByPropName = orderByPropName;
		this.orderDesc = orderDesc;
	}
	
	public static OrderItem asc(String propertyName) {
		return new OrderItem(propertyName, false);
	}
	
	public static OrderItem desc(String propertyName) {
		return new OrderItem(propertyName, true);
	}
	
	public String getOrderByPropName() {
		return orderByPropName;
	}

	public boolean isOrderDesc() {
		return orderDesc;
	}

	public void setOrderByPropName(String string) {
		orderByPropName = string;
	}

	public void setOrderDesc(boolean b) {
		orderDesc = b;
	}

}