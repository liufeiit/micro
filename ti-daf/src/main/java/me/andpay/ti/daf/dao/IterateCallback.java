package me.andpay.ti.daf.dao;

import me.andpay.ti.base.AppBizException;

/**
 * 遍历回调接口定义类。
 * 
 * @author sea.bao
 */
public interface IterateCallback<T> {
	/**
	 * 遍历实体对象
	 * @param sessCtx
	 * @param element
	 * @return
	 * @throws AppBizException
	 */
	boolean doElement( SessionContext sessCtx, T element ) throws AppBizException;
}