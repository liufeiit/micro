package me.andpay.ti.daf.dao.simplequery.annotation;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 日期精度比较标签类。
 * 
 * @author sea.bao
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RUNTIME)
public @interface Date {
	String entity() default "";

	String property() default "";
}
