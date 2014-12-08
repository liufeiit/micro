package me.andpay.ti.daf.dao.simplequery;

import me.andpay.ti.daf.dao.hibernate.simplequery.IsNullHQLConditionGenerator;
import me.andpay.ti.daf.dao.simplequery.annotation.IsNull;
import me.andpay.ti.util.StringUtil;

/**
 * 是否为Null标签解析器实现类。
 * 
 * @author alex
 */
public class IsNullAnnotationParser implements AnnotationParser<IsNull> {
	public ExpressionDescription parse(IsNull anno) {
		ExpressionDescription expDesc = new ExpressionDescription();
		expDesc.setProperty(StringUtil.emptyAsNull(anno.property()));
		expDesc.setEntity(StringUtil.emptyAsNull(anno.entity()));
		expDesc.setGenerator(IsNullHQLConditionGenerator.class);

		return expDesc;
	}
}
