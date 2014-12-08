package me.andpay.ti.daf.dao.simplequery;

import java.util.Calendar;
import java.util.Date;

import me.andpay.ti.util.DateUtil;

/**
 * 对齐日期转换器类。
 * @author sea.bao
 *
 */
public class RoundToDateConvertor implements ConditionPropertyConvertor<Date> {
    public Object convert(Date conditionProperty) {
        return DateUtil.roundDate( (java.util.Date )conditionProperty, Calendar.DATE );
    }

}
