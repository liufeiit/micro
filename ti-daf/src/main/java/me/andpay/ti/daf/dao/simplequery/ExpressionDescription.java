package me.andpay.ti.daf.dao.simplequery;

import me.andpay.ti.daf.dao.hibernate.simplequery.HQLConditionGenerator;

/**
 * 表达式描述类。
 * 
 * @author sea.bao
 */
public class ExpressionDescription {
	/**
	 * hql实体名称
	 */
	private String entity;

	/**
	 * 转换器类
	 */
	private Class<? extends ConditionPropertyConvertor<?>> convertor;

	/**
	 * 生成器
	 */
	private Class<? extends HQLConditionGenerator> generator;

	/**
	 * 空为条件
	 */
	private boolean nullCondition;

	/**
	 * 操作符
	 */
	private String operator;

	/**
	 * 实体属性名称
	 */
	private String property;

	/**
	 * 条件属性
	 */
	private String condProperty;

	/**
	 * 模版
	 */
	private String template;

	/**
	 * 条件属性忽略
	 */
	private boolean ignore;

	public String getEntity() {
		return entity;
	}

	public void setEntity(String persistenceAlias) {
		this.entity = persistenceAlias;
	}

	public Class<? extends ConditionPropertyConvertor<?>> getConvertor() {
		return convertor;
	}

	public void setConvertor(Class<? extends ConditionPropertyConvertor<?>> convertor) {
		this.convertor = convertor;
	}

	public boolean isNullCondition() {
		return nullCondition;
	}

	public void setNullCondition(boolean absenceWhenNull) {
		this.nullCondition = absenceWhenNull;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String persistenceProperty) {
		this.property = persistenceProperty;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String generateTemplate) {
		this.template = generateTemplate;
	}

	public boolean isIgnore() {
		return ignore;
	}

	public void setIgnore(boolean nonePersistenceProperty) {
		this.ignore = nonePersistenceProperty;
	}

	public String getCondProperty() {
		return condProperty;
	}

	public void setCondProperty(String condProperty) {
		this.condProperty = condProperty;
	}

	public Class<? extends HQLConditionGenerator> getGenerator() {
		return generator;
	}

	public void setGenerator(Class<? extends HQLConditionGenerator> generator) {
		this.generator = generator;
	}
}
