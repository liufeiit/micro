package me.andpay.ti.daf.dao.simplequery;

import me.andpay.ti.daf.dao.hibernate.simplequery.StartsWithHQLConditionGenerator;
import me.andpay.ti.daf.dao.simplequery.annotation.StartsWith;
import me.andpay.ti.util.StringUtil;

/**
 * 启始字符标签解析器实现类。
 * 
 * @author alex
 */
public class StartsWithAnnotationParser implements AnnotationParser<StartsWith> {

	public ExpressionDescription parse(StartsWith anno) {
		ExpressionDescription expDesc = new ExpressionDescription();
		expDesc.setProperty(StringUtil.emptyAsNull(anno.property()));
		expDesc.setEntity(StringUtil.emptyAsNull(anno.entity()));
		expDesc.setGenerator(StartsWithHQLConditionGenerator.class);

		return expDesc;
	}
}
