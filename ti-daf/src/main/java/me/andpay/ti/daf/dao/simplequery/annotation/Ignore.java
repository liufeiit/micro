package me.andpay.ti.daf.dao.simplequery.annotation;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RUNTIME)
public @interface Ignore {
	String persistenceAlias() default "";

	String convertor() default "";

	Class<?> convertorClass() default DefaultConvertor.class;

	String generator() default "";

	Class<?> generatorClass() default DefaultConvertor.class;

	String conditionProperty() default "";

	boolean absenceWhenNull() default true;

	String operator() default "";

	String persistenceProperty() default "";

	String generateTemplate() default "";

	boolean nonePersistenceProperty() default false;
}
