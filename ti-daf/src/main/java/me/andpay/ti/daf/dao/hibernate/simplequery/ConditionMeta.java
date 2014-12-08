package me.andpay.ti.daf.dao.hibernate.simplequery;

/**
 * 条件元类。
 * 
 * @author sea.bao
 */
public class ConditionMeta {
	/**
	 * SimpleQuery定义
	 */
	private SimpleQueryDefinition simpleQueryDefinition;

	public ConditionMeta(SimpleQueryDefinition simpleQueryDefinition) {
		this.simpleQueryDefinition = simpleQueryDefinition;
	}

	public String hqlPropName(String property) {
		return getDefaultPersistenceAlias() + "." + property;
	}
	
	public String getDefaultPersistenceAlias() {
		return simpleQueryDefinition.getDefaultPersistenceAlias();
	}

	public SimpleQueryDefinition getSimpleQueryDefinition() {
		return simpleQueryDefinition;
	}
}