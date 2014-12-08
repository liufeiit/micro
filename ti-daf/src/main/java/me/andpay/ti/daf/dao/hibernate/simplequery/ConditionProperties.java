package me.andpay.ti.daf.dao.hibernate.simplequery;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

import me.andpay.ti.daf.dao.simplequery.ExpressionDescription;

import org.springframework.beans.BeanUtils;

/**
 * 条件属性类。
 * 
 * @author sea.bao
 */
public class ConditionProperties {
	private SimpleQueryDefinition simpleQueryDefinition;

	private Map<String, Object> propertiesCache = new HashMap<String, Object>();

	private Object condition;

	ConditionProperties(SimpleQueryDefinition simpleQueryDefinition, Object condition) {
		this.simpleQueryDefinition = simpleQueryDefinition;
		this.condition = condition;
	}

	public Object getConditionProperty(String property) {
		if (propertiesCache.containsKey(property)) {
			return propertiesCache.get(property);
		}

		try {
			Object propertyValue = null;
			PropertyDescriptor propDesc = BeanUtils.getPropertyDescriptor(condition.getClass(), property);
			if (propDesc != null) {
				propertyValue = propDesc.getReadMethod().invoke(condition);
			}

			ExpressionDescription expression = simpleQueryDefinition.getExpression(property);
			if (expression.getConvertor() != null) {
				propertyValue = SimpleQueryUtil.convertProperty(expression.getConvertor(), propertyValue);
			}

			propertiesCache.put(property, propertyValue);

			return propertyValue;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}