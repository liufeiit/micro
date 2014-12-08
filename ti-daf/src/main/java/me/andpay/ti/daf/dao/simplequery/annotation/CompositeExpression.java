package me.andpay.ti.daf.dao.simplequery.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 复杂条件标签类
 * 
 * @author alex
 */
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RUNTIME)
public @interface CompositeExpression {
	String value() default "";

	String variable() default "";

	String operator() default "";

	boolean ignore() default false;
}
