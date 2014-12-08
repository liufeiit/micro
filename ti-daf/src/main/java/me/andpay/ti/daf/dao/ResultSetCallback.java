package me.andpay.ti.daf.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.andpay.ti.base.AppBizException;

/**
 * 结果集回调接口定义类。
 * 
 * @author sea.bao
 */
public interface ResultSetCallback {
	/**
	 * 处理Jdbc结果集
	 * @param rs
	 * @throws AppBizException
	 * @throws SQLException
	 */
    void doInResultSet(ResultSet rs) throws AppBizException, SQLException;
}
