package me.andpay.ti.daf.dao.hibernate;

import java.util.Date;

/**
 * BadSql日志项目类
 * 
 * @author sea.bao
 */
public class BadSqlLogItem {
	/**
	 * 跟踪代码
	 */
	private String trackingCode;
	
	/**
	 * 调用时间
	 */
	private Date callTime;

	/**
	 * 过期时间
	 */
	private long expiredTime;
	
	/**
	 * BadSql语句
	 */
	private String badSql;

	/**
	 * 语句参数
	 */
	private Object[] parameters;

	/**
	 * 当前堆栈信息
	 */
	private String stackTrace;

	public Date getCallTime() {
		return callTime;
	}

	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}

	public String getBadSql() {
		return badSql;
	}

	public void setBadSql(String badSql) {
		this.badSql = badSql;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}

	public long getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(long expiredTime) {
		this.expiredTime = expiredTime;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	public String getTrackingCode() {
		return trackingCode;
	}

	public void setTrackingCode(String trackingCode) {
		this.trackingCode = trackingCode;
	}
}
