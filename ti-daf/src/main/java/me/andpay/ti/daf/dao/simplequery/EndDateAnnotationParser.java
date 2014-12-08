package me.andpay.ti.daf.dao.simplequery;

import me.andpay.ti.daf.dao.simplequery.annotation.EndDate;
import me.andpay.ti.util.StringUtil;

/**
 * 截至日期标签解析器实现类。
 * 
 * @author sea.bao
 */
public class EndDateAnnotationParser implements AnnotationParser<EndDate> {
	public ExpressionDescription parse(EndDate anno) {
		ExpressionDescription expDesc = new ExpressionDescription();
		expDesc.setProperty(StringUtil.emptyAsNull(anno.property()));
		expDesc.setEntity(StringUtil.emptyAsNull(anno.entity()));
		expDesc.setConvertor(RoundToEndTimeConvertor.class);
		expDesc.setOperator("<");
		
		return expDesc;
	}

}
