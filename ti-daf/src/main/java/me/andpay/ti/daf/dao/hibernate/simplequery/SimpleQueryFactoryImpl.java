package me.andpay.ti.daf.dao.hibernate.simplequery;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import me.andpay.ti.daf.dao.hibernate.HQLCondition;
import me.andpay.ti.daf.dao.hibernate.HQLQuery;
import me.andpay.ti.daf.dao.simplequery.AnnotationParser;
import me.andpay.ti.daf.dao.simplequery.CompositeExpressionAnnotationParser;
import me.andpay.ti.daf.dao.simplequery.ConditionPropertyConvertor;
import me.andpay.ti.daf.dao.simplequery.DateAnnotationParser;
import me.andpay.ti.daf.dao.simplequery.EndDateAnnotationParser;
import me.andpay.ti.daf.dao.simplequery.ExpressionDescription;
import me.andpay.ti.daf.dao.simplequery.IsNullAnnotationParser;
import me.andpay.ti.daf.dao.simplequery.Orderable;
import me.andpay.ti.daf.dao.simplequery.StartDateAnnotationParser;
import me.andpay.ti.daf.dao.simplequery.StartsWithAnnotationParser;
import me.andpay.ti.daf.dao.simplequery.annotation.CompositeExpression;
import me.andpay.ti.daf.dao.simplequery.annotation.Condition;
import me.andpay.ti.daf.dao.simplequery.annotation.Date;
import me.andpay.ti.daf.dao.simplequery.annotation.DefaultConvertor;
import me.andpay.ti.daf.dao.simplequery.annotation.EndDate;
import me.andpay.ti.daf.dao.simplequery.annotation.Expression;
import me.andpay.ti.daf.dao.simplequery.annotation.Ignore;
import me.andpay.ti.daf.dao.simplequery.annotation.IsNull;
import me.andpay.ti.daf.dao.simplequery.annotation.StartDate;
import me.andpay.ti.daf.dao.simplequery.annotation.StartsWith;
import me.andpay.ti.util.StringUtil;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * SimpleQuery工厂实现类。
 * 
 * @author sea.bao
 */
public class SimpleQueryFactoryImpl implements SimpleQueryFactory {
	private Log logger = LogFactory.getLog(this.getClass());

	/**
	 * SimpleQuery定义集
	 */
	private Map<String, SimpleQueryDefinition> simpleQueryDefinitions = new ConcurrentHashMap<String, SimpleQueryDefinition>();

	/**
	 * 条件类集
	 */
	private Set<Class<Object>> conditions;

	/**
	 * 排序字段必须被选择标志
	 */
	private boolean orderColumnMustBeSelect = false;

	/**
	 * 忽略属性集
	 */
	private static Set<String> ignoreProperties = new HashSet<String>();

	/**
	 * 标签解析器
	 */
	private static Map<Class<? extends Annotation>, AnnotationParser<? extends Annotation>> annotationParsers = new HashMap<Class<? extends Annotation>, AnnotationParser<? extends Annotation>>();

	static {
		ignoreProperties.add("class");
		annotationParsers.put(StartDate.class, new StartDateAnnotationParser());
		annotationParsers.put(EndDate.class, new EndDateAnnotationParser());
		annotationParsers.put(IsNull.class, new IsNullAnnotationParser());
		annotationParsers.put(CompositeExpression.class, new CompositeExpressionAnnotationParser());
		annotationParsers.put(Date.class, new DateAnnotationParser());
		annotationParsers.put(StartsWith.class, new StartsWithAnnotationParser());
	}

	private ExpressionDescription convertToBean(Expression exp, String propertyName) {
		ExpressionDescription expBean = new ExpressionDescription();
		expBean.setNullCondition(exp.nullCondition());

		Class<? extends ConditionPropertyConvertor<?>> convertorClass = exp.convertor();
		if (convertorClass.equals(DefaultConvertor.class) == false) {
			expBean.setConvertor(convertorClass);
		}

		Class<? extends HQLConditionGenerator> generatorClass = exp.generator();
		if (generatorClass.equals(HQLConditionGenerator.class) == false) {
			expBean.setGenerator(generatorClass);
		}

		expBean.setTemplate(StringUtil.emptyAsNull(exp.template()));
		expBean.setIgnore(exp.ignore());
		expBean.setOperator(StringUtil.emptyAsNull(exp.operator()));
		expBean.setEntity(StringUtil.emptyAsNull(exp.entity()));
		expBean.setProperty(StringUtil.emptyAsNull(exp.property()));
		expBean.setCondProperty(propertyName);

		return expBean;
	}

	private ExpressionDescription getExpressionBean(AccessibleObject obj, String condPropName) {
		for (Class<? extends Annotation> annoClazz : annotationParsers.keySet()) {
			if (obj.isAnnotationPresent(annoClazz)) {
				@SuppressWarnings("unchecked")
				AnnotationParser<Annotation> parser = (AnnotationParser<Annotation>) annotationParsers.get(annoClazz);
				ExpressionDescription expBean = parser.parse(obj.getAnnotation(annoClazz));
				if (expBean != null) {
					expBean.setCondProperty(condPropName);
					if (expBean.getProperty() == null) {
						expBean.setProperty(condPropName);
					}

					return expBean;
				}
			}
		}

		return null;
	}

	private ExpressionDescription getExpressionDescription(AccessibleObject obj, String condPropName) {
		ExpressionDescription expBean = null;
		if (obj.isAnnotationPresent(Expression.class)) {
			Expression exp = obj.getAnnotation(Expression.class);
			expBean = convertToBean(exp, condPropName);
		} else {
			expBean = getExpressionBean(obj, condPropName);
		}

		return expBean;
	}

	private void registerConditionImpl(Class<?> condClazz, Class<?> entityClazz) {
		SimpleQueryDefinition def = new SimpleQueryDefinition();
		String condBeanName = condClazz.getName();

		ConditionBean condBean = new ConditionBean();
		Condition condition = condClazz.getAnnotation(Condition.class);
		if (condition == null) {
			throw new RuntimeException("The class=[" + condClazz.getName() + "] isn't a SimpleQueryCondition bean.");
		}

		condBean.setFromClause(StringUtil.emptyAsNull(condition.fromClause()));
		condBean.setSelectClause(StringUtil.emptyAsNull(condition.selectClause()));
		condBean.setWhereClause(StringUtil.emptyAsNull(condition.whereClause()));
		if (condBean.getFromClause() == null) {
			String entityName = null;
			if (condition.entity().equals(DefaultConvertor.class)) {
				if (entityClazz == null) {
					throw new RuntimeException("Invalid QueryCondition=[" + condClazz.getName()
							+ "], must define entity.");
				}

				entityName = entityClazz.getName();
			}

			condBean.setFromClause(entityName + " entity");
			def.setDefaultPersistenceAlias("entity");
		} else {
			String alias = SimpleQueryUtil.getDefaultPersistenceAlias(condBean.getFromClause());
			if (alias == null) {
				alias = "entity";
				condBean.setFromClause(SimpleQueryUtil.insertDefaultPersistenceAlias(condBean.getFromClause(), alias));
			}

			def.setDefaultPersistenceAlias(alias);
		}

		def.setCondition(condBean);

		try {
			BeanInfo info = java.beans.Introspector.getBeanInfo(condClazz);
			for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
				Method m = pd.getReadMethod();
				if (m == null) {
					continue;
				}

				if (m.isAnnotationPresent(Ignore.class)) {
					continue;
				}

				Field field = null;
				try {
					field = condClazz.getDeclaredField(pd.getName());
					if (field.isAnnotationPresent(Ignore.class)) {
						continue;
					}
				} catch (NoSuchFieldException e) {
				}

				ExpressionDescription expBean = getExpressionDescription(m, pd.getName());
				if (expBean == null && field != null) {
					expBean = getExpressionDescription(field, pd.getName());
				}

				if (expBean == null) {
					if (ignoreProperties.contains(pd.getName()) == false) {
						expBean = new ExpressionDescription();
						expBean.setNullCondition(false);
						expBean.setCondProperty(pd.getName());
						expBean.setProperty(pd.getName());
					}
				}

				if (expBean != null) {
					def.addExpression(expBean);
				}
			}
		} catch (IntrospectionException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		simpleQueryDefinitions.put(condBeanName, def);

		logger.info("Loaded simplequery condition bean, [" + condBeanName + "]");
	}

	public void registerCondition(Class<?> condClazz, Class<?> entityClazz) {
		try {
			registerConditionImpl(condClazz, entityClazz);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	@PostConstruct
	public void init() {
		if (conditions == null) {
			return;
		}

		for (Class<Object> clazz : conditions) {
			if (clazz.isAnnotationPresent(Condition.class)) {
				registerConditionImpl(clazz, null);
			}
		}
	}

	private HQLCondition genHQLConditionImpl(SimpleQueryDefinition sqDef, Object condition) throws Exception {
		HQLCondition hqlCond = new HQLCondition();
		String defaultPersistenceAlias = sqDef.getDefaultPersistenceAlias();
		ConditionMeta condMeta = new ConditionMeta(sqDef);
		ConditionProperties conditionProperties = new ConditionProperties(sqDef, condition);
		for (ExpressionDescription expression : sqDef.getExpressions().values()) {
			if (expression.isIgnore()) {
				continue;
			}

			Object condProp = PropertyUtils.getProperty(condition, expression.getCondProperty());

			if (condProp == null) {
				if (expression.isNullCondition() == false) {
					continue;
				}
			}

			if (expression.getGenerator() != null || expression.getTemplate() != null) {
				HQLConditionGenerator generator = null;
				if (expression.getGenerator() != null) {
					generator = expression.getGenerator().newInstance();

				} else {
					generator = new HQLConditionTemplateGenerator();
				}

				HQLCondition hqlCond1 = generator.genHQLCondition(condMeta, conditionProperties,
						expression.getTemplate(), expression);
				if (hqlCond1 == null) {
					continue;
				}

				hqlCond.concat(HQLCondition.AND, hqlCond1);
			} else {
				if (expression.getConvertor() != null) {
					condProp = SimpleQueryUtil.convertProperty(expression.getConvertor(), condProp);

					if (condProp == null && expression.isNullCondition() == false) {
						continue;
					}
				}

				String persistencePropName = expression.getProperty();
				if (persistencePropName == null) {
					persistencePropName = expression.getCondProperty();
				}

				String persistenceAlias = expression.getEntity();
				if (persistenceAlias == null) {
					persistenceAlias = defaultPersistenceAlias;
				}

				String operator = SimpleQueryUtil.convertOperator(expression.getOperator());
				HQLCondition hqlCond1 = HQLCondition.getInstance(persistenceAlias + "." + persistencePropName,
						operator, condProp);
				hqlCond.concat(HQLCondition.AND, hqlCond1);
			}
		}

		return hqlCond;
	}

	private HQLQuery genHQLQueryImpl(SimpleQueryDefinition sqDef, Object condition) throws Exception {
		HQLCondition hqlCond = genHQLConditionImpl(sqDef, condition);

		String defaultPersistenceAlias = sqDef.getDefaultPersistenceAlias();

		HQLQuery hqlQuery = hqlCond.toHQLQuery();
		hqlQuery.setSelectClause(sqDef.getCondition().getSelectClause());
		hqlQuery.setFromClause(sqDef.getCondition().getFromClause());
		if (sqDef.getCondition().getWhereClause() != null) {
			MacroVariableContainer mvc = new MapMacroVariableContainer();
			String whereClause = hqlQuery.getWhereClause();
			mvc.setMacroVariable("condition-clause", whereClause);
			whereClause = MacroVariableInterpreter.interpret(sqDef.getCondition().getWhereClause(), "${", "}", mvc);
			hqlQuery.setWhereClause(whereClause);
		}

		if (condition instanceof Orderable) {
			Orderable orderCond = (Orderable) condition;
			if (orderCond.getOrders() != null) {
				ParsedOrderableCondition poc = new ParsedOrderableCondition(orderCond.getOrders());

				if (hqlQuery.getSelectClause() == null || hqlQuery.getSelectClause().trim().equals("")) {
					hqlQuery.setSelectClause(defaultPersistenceAlias);
				}

				if (orderColumnMustBeSelect) {
					hqlQuery.setSelectClause(hqlQuery.getSelectClause() + ","
							+ poc.genSelectClause(defaultPersistenceAlias));
				}

				hqlQuery.setOrderClause(poc.genOrderClause(defaultPersistenceAlias));
			}
		}

		return hqlQuery;
	}

	public SimpleQueryDefinition getSimpleQueryDefinition(String clazzName) {
		SimpleQueryDefinition sqDef = simpleQueryDefinitions.get(clazzName);

		if (sqDef == null) {
			throw new RuntimeException("Not found the simpleQueryDefinition for " + clazzName);
		}

		return sqDef;
	}

	public HQLQuery genHQLQuery(Class<?> clazz, Object condition) {
		SimpleQueryDefinition sqDef = getSimpleQueryDefinition(clazz.getName());

		try {
			return genHQLQueryImpl(sqDef, condition);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public HQLCondition genHQLCondition(Class<?> clazz, Object condition) {
		SimpleQueryDefinition sqDef = getSimpleQueryDefinition(clazz.getName());

		try {
			return genHQLConditionImpl(sqDef, condition);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public HQLCondition genHQLCondition(Object condition) {
		return genHQLCondition(condition.getClass(), condition);
	}

	public HQLQuery genHQLQuery(Object condition) {
		return genHQLQuery(condition.getClass(), condition);
	}

	public ConditionMeta getConditionMeta(Class<?> clazz) {
		SimpleQueryDefinition sqDef = getSimpleQueryDefinition(clazz.getName());

		return new ConditionMeta(sqDef);
	}

	public boolean isOrderColumnMustBeSelect() {
		return orderColumnMustBeSelect;
	}

	public void setOrderColumnMustBeSelect(boolean orderColumnMustSelect) {
		this.orderColumnMustBeSelect = orderColumnMustSelect;
	}

	public Set<Class<Object>> getConditions() {
		return conditions;
	}

	public void setConditions(Set<Class<Object>> conditions) {
		this.conditions = conditions;
	}
}