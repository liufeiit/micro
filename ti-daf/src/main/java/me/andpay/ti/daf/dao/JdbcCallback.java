package me.andpay.ti.daf.dao;

import java.sql.Connection;
import java.sql.SQLException;

import me.andpay.ti.base.AppBizException;

/**
 * Jdbc回调接口定义类。
 * 
 * @author sea.bao
 */
public interface JdbcCallback<T> {
	T doInJdbc(Connection conn) throws AppBizException, SQLException;
}
