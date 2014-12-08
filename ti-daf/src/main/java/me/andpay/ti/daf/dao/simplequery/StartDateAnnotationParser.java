package me.andpay.ti.daf.dao.simplequery;

import me.andpay.ti.daf.dao.simplequery.annotation.StartDate;
import me.andpay.ti.util.StringUtil;

/**
 * 启始日期标签解析器实现类。
 * 
 * @author sea.bao
 */
public class StartDateAnnotationParser implements AnnotationParser<StartDate> {
	public ExpressionDescription parse(StartDate anno) {
		ExpressionDescription expDesc = new ExpressionDescription();
		expDesc.setProperty(StringUtil.emptyAsNull(anno.property()));
		expDesc.setEntity(StringUtil.emptyAsNull(anno.entity()));
		expDesc.setConvertor(RoundToStartTimeConvertor.class);
		expDesc.setOperator(">=");
		
		return expDesc;
	}

}
