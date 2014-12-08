package me.andpay.ti.daf.dao.simplequery;

import java.util.Calendar;
import java.util.Date;

import me.andpay.ti.util.DateUtil;

/**
 * 对齐启始日期转换器类。
 * @author sea.bao
 *
 */
public class RoundToStartTimeConvertor implements ConditionPropertyConvertor<Date> {
    public Object convert(Date conditionProperty) {
        return DateUtil.roundDate( (java.util.Date )conditionProperty, Calendar.DATE );
    }

}
