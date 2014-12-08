package me.andpay.ti.daf.dao.simplequery.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import me.andpay.ti.daf.dao.hibernate.simplequery.HQLConditionGenerator;
import me.andpay.ti.daf.dao.simplequery.ConditionPropertyConvertor;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RUNTIME)
public @interface Expression {
	String entity() default "";

	Class<? extends ConditionPropertyConvertor<?>> convertor() default DefaultConvertor.class;
	
	Class<? extends HQLConditionGenerator> generator() default HQLConditionGenerator.class;
	
	boolean nullCondition() default false;

	String operator() default "";

	String property() default "";

	String template() default "";

	boolean ignore() default false;
}