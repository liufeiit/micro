package me.andpay.ti.daf.dao.hibernate.simplequery;

import java.util.Collection;

import me.andpay.ti.daf.dao.hibernate.HQLCondition;
import me.andpay.ti.daf.dao.simplequery.ExpressionDescription;

/**
 * 复杂HQL条件生成器实现类
 * 
 * @author alex
 */
public class CompositeHQLConditionGenerator implements HQLConditionGenerator {

	public HQLCondition genHQLCondition(ConditionMeta conditionMeta, ConditionProperties condition, String genTemplate,
			ExpressionDescription expBean) {
		Object value = condition.getConditionProperty(expBean.getCondProperty());
		if (value == null) {
			return null;
		}

		String expr = expBean.getProperty();
		if (expr == null) {
			throw new IllegalArgumentException("Composite expression must be specifed");
		}

		String variable = expBean.getTemplate();
		if (variable != null) {
			String exprVar = "${" + variable + "}";
			if (expr.contains(exprVar) == false) {
				throw new IllegalArgumentException(String.format(
						"Cannot find the variable in composite expression, expr=[%s], var=[%s]", expr, variable));
			}

			String placeholder = "?";
			if (value instanceof Collection<?>) {
				int size = ((Collection<?>) value).size();

				StringBuilder colPlaceholders = new StringBuilder();
				while (size-- > 0) {
					if (colPlaceholders.length() > 0) {
						colPlaceholders.append(",");
					}
					colPlaceholders.append("?");
				}

				placeholder = colPlaceholders.toString();
			}

			return HQLCondition.getInstance(expr.replace(exprVar, placeholder), null, null, value);
		}

		if (expBean.getOperator() == null) {
			HQLCondition hqlCond = HQLCondition.getInstance(expr, (String) null);
			hqlCond.setNot(Boolean.valueOf(value.toString()) == false);
			return hqlCond;

		} else {
			String operator = SimpleQueryUtil.convertOperator(expBean.getOperator());
			return HQLCondition.getInstance("(" + expr + ")", operator, value);
		}
	}
}
