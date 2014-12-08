package me.andpay.ti.daf.dao.simplequery.annotation;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 启始日期标签类。
 * 
 * @author sea.bao
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RUNTIME)
public @interface StartDate {
	String entity() default "";

	String property() default "";
}
