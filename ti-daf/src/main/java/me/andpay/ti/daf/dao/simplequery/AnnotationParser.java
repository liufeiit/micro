package me.andpay.ti.daf.dao.simplequery;

import java.lang.annotation.Annotation;

/**
 * 标签解析器接口定义类。
 * 
 * @author sea.bao
 */
public interface AnnotationParser<T extends Annotation> {
	/**
	 * 解析
	 * @param anno
	 * @return
	 */
	ExpressionDescription parse(T anno);
}
