package me.andpay.ti.daf.dao.hibernate.simplequery;

import me.andpay.ti.daf.dao.hibernate.HQLCondition;
import me.andpay.ti.daf.dao.hibernate.HQLQuery;

/**
 * SimpleQuery工厂接口定义类。
 * 
 * @author sea.bao
 */
public interface SimpleQueryFactory {
    /**
     * 生成HQL查询对象。
     * @param condition
     * @return
     */
    public HQLQuery genHQLQuery(Object condition);
    
    /**
     * 生成HQL查询对象。
     * @param clazz
     * @param condition
     * @return
     */
    public HQLQuery genHQLQuery(Class<?> clazz, Object condition);
    
    /**
     * 生成HQL条件对象。
     * @param clazz
     * @param condition
     * @return
     */
    public HQLCondition genHQLCondition(Class<?> clazz, Object condition);
    
    /**
     * 生成HQL条件对象。
     * @param condition
     * @return
     */
    public HQLCondition genHQLCondition(Object condition);
    
    /**
     * 生成条件元数据
     * @param clazz
     * @return
     */
    public ConditionMeta getConditionMeta(Class<?> clazz);
    
    /**
     * 注册条件类
     * @param clazz
     */
    public void registerCondition(Class<?> condClass, Class<?> entityClass);
    
    /**
     * 获得SimpleQuery定义
     * @param clazzName
     * @return
     */
    public SimpleQueryDefinition getSimpleQueryDefinition(String clazzName);
}