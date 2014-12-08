package me.andpay.ti.daf.dao.hibernate;

import java.util.Date;
import java.util.List;

import me.andpay.ti.daf.dao.simplequery.Orderable;
import me.andpay.ti.daf.dao.simplequery.annotation.CompositeExpression;
import me.andpay.ti.daf.dao.simplequery.annotation.Condition;
import me.andpay.ti.daf.dao.simplequery.annotation.EndDate;
import me.andpay.ti.daf.dao.simplequery.annotation.Expression;
import me.andpay.ti.daf.dao.simplequery.annotation.Ignore;
import me.andpay.ti.daf.dao.simplequery.annotation.IsNull;
import me.andpay.ti.daf.dao.simplequery.annotation.StartDate;
import me.andpay.ti.daf.dao.simplequery.annotation.StartsWith;

/**
 * 查询User条件类。
 * 
 * @author sea.bao
 */
@Condition
public class QueryUserCond extends User implements Orderable {
	/**
	 * 启始建立日期
	 */
	private java.util.Date startCreateDate;

	/**
	 * 截至建立日期
	 */
	private java.util.Date endCreateDate;

	/**
	 * 名字是否为null
	 */
	@StartsWith(property = "name")
	private String namePrefix;

	/**
	 * 名字是否为null
	 */
	@IsNull(property = "name")
	private Boolean nameIsNull;

	/**
	 * 上次更新是否为null
	 */
	@IsNull(property = "lastUpdateTime")
	private Object lastUpdateTimeIsNull;

	/**
	 * 是否在同一天
	 */
	@CompositeExpression("datediff(lastUpdateTime, createTime) = 0")
	private Boolean atSameDay;

	/**
	 * 间隔天数等于
	 */
	@CompositeExpression(value = "datediff(lastUpdateTime, createTime)", operator = "=")
	private Integer daysBetween;

	/**
	 * 长用户名
	 */
	@CompositeExpression(value = "name in (${names}) and length(name) >= 15", variable = "names")
	private List<String> longNames;

	/**
	 * 创建时间
	 */
	@CompositeExpression(value = "date(createTime) = date(${createDate})", variable = "createDate")
	private Date createDate;

	/**
	 * 排序因子
	 */
	private String orders;

	@Expression(operator = "like")
	public String getName() {
		return super.getName();
	}

	@StartDate(property = "createTime")
	public java.util.Date getStartCreateDate() {
		return startCreateDate;
	}

	public void setStartCreateDate(java.util.Date startCreateDate) {
		this.startCreateDate = startCreateDate;
	}

	@EndDate(property = "createTime")
	public java.util.Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(java.util.Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	@Ignore
	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}

	public String getNamePrefix() {
		return namePrefix;
	}

	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	public Boolean getNameIsNull() {
		return nameIsNull;
	}

	public void setNameIsNull(Boolean nameIsNull) {
		this.nameIsNull = nameIsNull;
	}

	public Object getLastUpdateTimeIsNull() {
		return lastUpdateTimeIsNull;
	}

	public void setLastUpdateTimeIsNull(Object lastUpdateTimeIsNull) {
		this.lastUpdateTimeIsNull = lastUpdateTimeIsNull;
	}

	public Boolean getAtSameDay() {
		return atSameDay;
	}

	public void setAtSameDay(Boolean atSameDay) {
		this.atSameDay = atSameDay;
	}

	public Integer getDaysBetween() {
		return daysBetween;
	}

	public void setDaysBetween(Integer daysBetween) {
		this.daysBetween = daysBetween;
	}

	public List<String> getLongNames() {
		return longNames;
	}

	public void setLongNames(List<String> longNames) {
		this.longNames = longNames;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
