package me.andpay.ti.daf.dao.simplequery;

/**
 * 条件类型转换器接口定义类。
 * 
 * @author sea.bao
 */
public interface ConditionPropertyConvertor<T> {
    public Object convert(T conditionProperty);
}
