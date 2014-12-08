package me.andpay.ti.daf.dao.simplequery;

import me.andpay.ti.daf.dao.hibernate.simplequery.CompositeHQLConditionGenerator;
import me.andpay.ti.daf.dao.simplequery.annotation.CompositeExpression;
import me.andpay.ti.util.StringUtil;

/**
 * 复杂条件标签解析器实现类。
 * 
 * @author alex
 */
public class CompositeExpressionAnnotationParser implements AnnotationParser<CompositeExpression> {
	public ExpressionDescription parse(CompositeExpression anno) {
		ExpressionDescription expDesc = new ExpressionDescription();
		expDesc.setProperty(StringUtil.emptyAsNull(anno.value()));
		expDesc.setTemplate(StringUtil.emptyAsNull(anno.variable()));
		expDesc.setOperator(StringUtil.emptyAsNull(anno.operator()));
		expDesc.setIgnore(anno.ignore());
		expDesc.setGenerator(CompositeHQLConditionGenerator.class);

		return expDesc;
	}
}
