package me.andpay.ti.daf.dao.hibernate.simplequery;

import java.util.HashMap;
import java.util.Map;

import me.andpay.ti.daf.dao.simplequery.ExpressionDescription;

/**
 * SimpleQuery定义类。
 * 
 * @author sea.bao
 */
public class SimpleQueryDefinition {
	private ConditionBean condition;
	
	private String defaultPersistenceAlias;
	
	private Map<String, ExpressionDescription> expressions;

	public ConditionBean getCondition() {
		return condition;
	}

	public void setCondition(ConditionBean condition) {
		this.condition = condition;
	}
	
	public void addExpression(ExpressionDescription expBean) {
		if ( expressions == null ) {
			expressions = new HashMap<String, ExpressionDescription>();
		}
		
		expressions.put(expBean.getCondProperty(), expBean);
	}

	public ExpressionDescription getExpression(String conditionProperty) {
		ExpressionDescription expression = expressions.get(conditionProperty);
		if ( expression == null ) {
			throw new RuntimeException("Not found the expression for [" + conditionProperty + "].");
		}
		
		return expression;
	}
	
	public Map<String, ExpressionDescription> getExpressions() {
		return expressions;
	}

	public void setExpressions(Map<String, ExpressionDescription> expressions) {
		this.expressions = expressions;
	}

	public String getDefaultPersistenceAlias() {
		return defaultPersistenceAlias;
	}

	public void setDefaultPersistenceAlias(String defaultPersistenceAlias) {
		this.defaultPersistenceAlias = defaultPersistenceAlias;
	}

}
