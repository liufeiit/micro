package me.andpay.ti.daf.dao.hibernate.simplequery;

import me.andpay.ti.daf.dao.hibernate.HQLCondition;
import me.andpay.ti.daf.dao.simplequery.ExpressionDescription;

/**
 * HQL条件生成器接口定义类。
 * 
 * @author sea.bao
 */
public interface HQLConditionGenerator {
    public HQLCondition genHQLCondition(ConditionMeta conditionMeta, ConditionProperties condition, String genTemplate, ExpressionDescription expBean);
}
