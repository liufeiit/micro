package me.andpay.ti.daf.dao.hibernate.simplequery;

import me.andpay.ti.daf.dao.hibernate.HQLCondition;
import me.andpay.ti.daf.dao.simplequery.ExpressionDescription;
import me.andpay.ti.spring.SpringContextHolder;

/**
 * 基于Spring容器的HQL条件生成器 (Spring配置文件中需配置SpringContextHolder)
 * 
 * @author alex
 */
public class SpringHQLConditionGenerator implements HQLConditionGenerator {
	/**
	 * {@inheritDoc}
	 */
	public HQLCondition genHQLCondition(ConditionMeta conditionMeta, ConditionProperties condition, String genTemplate,
			ExpressionDescription expBean) {
		if (genTemplate == null) {
			throw new IllegalArgumentException("The template name of hql condition generator must be specified");
		}

		// template属性作为beanName
		HQLConditionGenerator generator = SpringContextHolder.getBean(genTemplate, HQLConditionGenerator.class);

		return generator.genHQLCondition(conditionMeta, condition, genTemplate, expBean);
	}
}
