package me.andpay.ti.daf.dao;

/**
 * SessionContext类。
 * 
 * @author sea.bao
 */
public interface SessionContext {
	/**
	 * 更新对象
	 * 
	 * @param obj
	 */
	void update(Object obj);

	/**
	 * 刷新Session
	 */
	void flush();

	/**
	 * 将对象丛一/二级缓存中清除
	 * 
	 * @param obj
	 */
	void evict(Object obj);
}