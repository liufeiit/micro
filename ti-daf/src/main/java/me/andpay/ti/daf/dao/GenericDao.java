package me.andpay.ti.daf.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import me.andpay.ti.base.AppBizException;

/**
 * 通用Dao接口定义类。
 * 
 * @author seabao
 * 
 * @param <T>
 * @param <I>
 * @param <Q>
 */
public interface GenericDao<T, I extends Serializable, Q> extends CrudDao<T, I> {
	/**
	 * 查询条件内的实体对象，最大返回实体对象数量500个。
	 * 
	 * @param cond
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	List<T> query(Q cond, long firstResult, int maxResults);

	/**
	 * 互斥获得条件对应的实体对象集，数据库中超过100个实体对象符合条件，将报异常。
	 * 
	 * @param cond
	 * @return
	 */
	List<T> queryForUpdate(Q cond);

	/**
	 * 获得条件对应的实体对象，数据库中超过一个实体对象符合条件，将报异常。
	 * 
	 * @param cond
	 * @return
	 */
	T getUnique(Q cond);

	/**
	 * 互斥获得条件对应的实体对象，数据库中超过一个实体对象符合条件，将报异常。
	 * 
	 * @param cond
	 * @return
	 */
	T getUniqueForUpdate(Q cond);

	/**
	 * 遍历条件对应的实体对象。
	 * 
	 * @param cond
	 * @param callback
	 * @throws AppBizException
	 */
	void iterate(Q cond, IterateCallback<T> callback) throws AppBizException;

	/**
	 * 互斥锁定遍历条件内的实体对象
	 * 
	 * @param cond
	 * @param callback
	 * @throws AppBizException
	 */
	void iterateForUpdate(Q cond, IterateCallback<T> callback) throws AppBizException;

	/**
	 * 获得条件内的实体对象数量。
	 * 
	 * @param cond
	 * @return
	 */
	long count(Q cond);
	
	/**
	 * 获得条件内的记录是否存在
	 * 
	 * @param cond
	 * @return
	 */
	boolean exists(Q cond);

	/**
	 * 查询结果集，
	 * 
	 * @param cond
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	QueryResult<T> queryForResult(Q cond, long firstResult, int maxResults);

	/**
	 * 批次修改条件内的记录。
	 * 
	 * @param cond
	 * @param values
	 * @return
	 */
	long bulkUpdate(Q cond, Map<String, Object> values);
}
