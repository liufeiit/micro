package me.andpay.ti.daf.dao;

import java.io.Serializable;
import java.util.Map;
 
/**
 * CrudDao接口定义类。
 * 
 * @author sea.bao
 * 
 * @param <T> 实体类
 * @param <I> 主键值
 */
public interface CrudDao<T, I extends Serializable> {
	/**
	 * 获得主键值对应的实体对象
	 * @param id
	 * @return
	 */
	T get(I id);
	
	/**
	 * 互斥锁获得主键值对应的实体对象
	 * @param id
	 * @return
	 */
	T getForUpdate(I id);
	
	/**
	 * 不等待互斥锁获得主键值对应的实体对象
	 * @param id
	 * @return
	 */
	T getForUpdateNoWait(I id);
	
	/**
	 * 获得主键值对应的实体对象
	 * @param id
	 * @return
	 */
	T load(I id);
	
	/**
	 * 互斥锁获得主键值对应的实体对象
	 * @param id
	 * @return
	 */
	T loadForUpdate(I id);
	
	/**
	 * 刷新实体对象
	 * @param o
	 * @return
	 */
	T refresh(T o);
	
	/**
	 * 互斥锁刷新实体对象
	 * @param o
	 * @return
	 */
	T refreshForUpdate(T o);
	
	/**
	 * 增加实体对象
	 * @param o
	 * @return
	 */
	I add(T o);
	
	/**
	 * 修改实体对象
	 * @param o
	 */
	void update(T o);
	
	/**
	 * 修改实体对象指定属性
	 * @param o
	 * @param property
	 */
	boolean update(T o, String... property);
	
	/**
	 * 修改实体对象指定属性
	 * @param id
	 * @param props
	 */
	boolean update(I id, Map<String, Object> props);
	
	/**
	 * 删除实体对象
	 * @param o
	 */
	boolean delete(T o);
	
	/**
	 * 删除主键值对应的实体对象
	 * @param id
	 */
	boolean delete(I id);
	
	/**
	 * 刷新当前的数据会话
	 */
	void flush();
	
	/**
	 * 丛一/二级缓存淘汰对象
	 * @param o
	 */
	void evict(T o);
}
