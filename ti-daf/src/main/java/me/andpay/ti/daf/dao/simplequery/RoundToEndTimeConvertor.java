/*
 * File RoundToEndTimeConvertor.java
 * Created on 2004-7-14
 */
package me.andpay.ti.daf.dao.simplequery;

import java.util.Calendar;
import java.util.Date;

import me.andpay.ti.util.DateUtil;

/**
 * 对齐截至日期转换器类。
 * 
 * @author sea.bao
 */
public class RoundToEndTimeConvertor implements ConditionPropertyConvertor<Date> {
	public Object convert(Date conditionProperty) {
		Date d = DateUtil.roundDate(conditionProperty, Calendar.DATE);
		return DateUtil.rollDate(d, Calendar.DATE, 1);
	}

}
