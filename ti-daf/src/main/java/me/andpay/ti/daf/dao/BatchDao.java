package me.andpay.ti.daf.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 批处理Dao接口定义类。
 * 
 * @author sea.bao
 */
public interface BatchDao<T, I extends Serializable, Q> extends GenericDao<T, I, Q> {
	/**
	 * 批量增加
	 * @param list
	 */
	void batchAdd(List<T> list);
	
	/**
	 * 批量更新
	 * @param list
	 * @param property
	 * 
	 * @return
	 */
	int batchUpdate(List<T> list, String... property);
}
