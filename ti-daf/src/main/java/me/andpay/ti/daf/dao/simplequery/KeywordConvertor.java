package me.andpay.ti.daf.dao.simplequery;

/**
 * 关键值转换器类。
 * 
 * @author sea.bao
 */
public class KeywordConvertor implements ConditionPropertyConvertor<String> {

	public Object convert(String conditionProperty) {
		StringBuffer str = new StringBuffer();
		str.append("%");
		str.append(conditionProperty);
		str.append("%");
	
		return str.toString();
	}

}
