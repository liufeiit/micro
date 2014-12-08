package me.andpay.ti.daf.dao.simplequery.annotation;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 是否为Null标签类
 * 
 * @author alex
 */
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RUNTIME)
public @interface IsNull {
	String entity() default "";

	String property() default "";
}
