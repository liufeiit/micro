package me.andpay.ti.daf.dao.hibernate.simplequery;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.andpay.ti.daf.dao.simplequery.ConditionPropertyConvertor;

/**
 * SimpleQuery工具类。
 * 
 * @author sea.bao
 */
public class SimpleQueryUtil {
	public static final String OPERATOR_LT = "lt";

	public static final String OPERATOR_LE = "le";

	public static final String OPERATOR_EQ = "eq";

	public static final String OPERATOR_NEQ = "neq";

	public static final String OPERATOR_GE = "ge";

	public static final String OPERATOR_GT = "gt";

	private static Map<String, String> operatorConvertMap;

	static {
		operatorConvertMap = new HashMap<String, String>();
		operatorConvertMap.put(OPERATOR_LT, "<");
		operatorConvertMap.put(OPERATOR_LE, "<=");
		operatorConvertMap.put(OPERATOR_EQ, "=");
		operatorConvertMap.put(OPERATOR_NEQ, "!=");
		operatorConvertMap.put(OPERATOR_GE, ">=");
		operatorConvertMap.put(OPERATOR_GT, ">");
	}

	private SimpleQueryUtil() {
	}

	static Map<String, String> getOperatorConvertMap() {
		return operatorConvertMap;
	}

	public static String convertOperator(String operator) {
		if (operator == null) {
			operator = OPERATOR_EQ;
		}

		String lowerCaseOperator = operator.toLowerCase();
		String cOperator = (String) operatorConvertMap.get(lowerCaseOperator);
		if (cOperator != null) {
			return cOperator;
		} else {
			return operator;
		}
	}

	/**
	 * 获取默认持久化对象的别名，不支持子查询语句
	 * 
	 * @param fromClause
	 * @return 找不到别名时返回null
	 */
	public static String getDefaultPersistenceAlias(String fromClause) {
		if (fromClause == null) {
			return null;
		}
		
		// table1 [alias1], table2 [alias2]
		int joinIdx = fromClause.indexOf(",");
		if (joinIdx != -1) {
			fromClause = fromClause.substring(0, joinIdx);
		}

		String[] formParts = fromClause.trim().split("\\s+");
		if (formParts.length <= 1 || formParts[1].matches("(?i)(join|full|left|right|inner)")) {
			// no alias (table1 | table1 [xxx] join)
			return null;
		}

		return formParts[1];
	}

	public static String insertDefaultPersistenceAlias(String fromClause, String alias) {
		if (fromClause == null || alias == null) {
			return fromClause;
		}
		
		Pattern p = Pattern.compile("\\s*[^\\,\\s]+(.*)");
		Matcher m = p.matcher(fromClause);

		if (m.find()) {
			StringBuilder buff = new StringBuilder(fromClause);
			buff.insert(m.start(1), " " + alias);
			return buff.toString();
		}

		return null;
	}

	public static Object convertProperty(Class<? extends ConditionPropertyConvertor<?>> convertorClazz, Object condProp)
			throws Exception {
		@SuppressWarnings("unchecked")
		ConditionPropertyConvertor<Object> convertor = (ConditionPropertyConvertor<Object>) convertorClazz
				.newInstance();
		return convertor.convert(condProp);
	}
}