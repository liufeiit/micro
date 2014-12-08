package me.andpay.ti.daf.dao.simplequery;

import me.andpay.ti.daf.dao.simplequery.annotation.Date;
import me.andpay.ti.util.StringUtil;

/**
 * 日期标签解析器实现类。
 * 
 * @author sea.bao
 */
public class DateAnnotationParser implements AnnotationParser<Date> {
	public ExpressionDescription parse(Date anno) {
		ExpressionDescription expDesc = new ExpressionDescription();
		expDesc.setProperty(StringUtil.emptyAsNull(anno.property()));
		expDesc.setEntity(StringUtil.emptyAsNull(anno.entity()));
		expDesc.setConvertor(RoundToDateConvertor.class);
		expDesc.setOperator("=");
		
		return expDesc;
	}

}
