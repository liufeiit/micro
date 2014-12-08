package me.andpay.ti.daf.dao.hibernate.simplequery;

import me.andpay.ti.daf.dao.hibernate.HQLCondition;
import me.andpay.ti.daf.dao.simplequery.ExpressionDescription;

/**
 * IsNull条件生成器类
 * 
 * @author alex
 */
public class IsNullHQLConditionGenerator implements HQLConditionGenerator {

	public HQLCondition genHQLCondition(ConditionMeta conditionMeta, ConditionProperties condition, String genTemplate,
			ExpressionDescription expBean) {
		Object value = condition.getConditionProperty(expBean.getCondProperty());
		if (value == null) {
			return null;
		}

		String persistenceAlias = expBean.getEntity();
		if (persistenceAlias == null) {
			persistenceAlias = conditionMeta.getDefaultPersistenceAlias();
		}

		if (Boolean.valueOf(value.toString())) {
			return HQLCondition.getInstance(persistenceAlias + "." + expBean.getProperty(), "IS NULL");
		} else {
			return HQLCondition.getInstance(persistenceAlias + "." + expBean.getProperty(), "IS NOT NULL");
		}
	}
}
