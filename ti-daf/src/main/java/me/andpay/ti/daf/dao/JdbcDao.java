package me.andpay.ti.daf.dao;

import me.andpay.ti.base.AppBizException;

/**
 * JdbcDao接口定义类。
 * 
 * @author sea.bao
 */
public interface JdbcDao {
    /**
     * 遍历记录
     * @param sqlCmd
     * @param resultSetCallback
     * @throws AppBizException
     */
    void iterate(SqlCommand sqlCmd, ResultSetCallback resultSetCallback) throws AppBizException;
    
    /**
     * 执行修改
     * @param sqlCmd
     * @return
     */
    long executeUpdate(SqlCommand sqlCmd);
    
    /**
     * 执行Jdbc语句
     * @param callback
     * @return
     * @throws AppBizException
     */
    <T> T executeJdbc(JdbcCallback<T> callback) throws AppBizException;
    
    /**
     * 刷新数据库会话
     */
    void flushSession();
}
