package me.andpay.ti.daf.dao;

import me.andpay.ti.util.JacksonSerializer;
import me.andpay.ti.util.Serializer;

/**
 * 非唯一异常类。
 * 
 * @author sea.bao
 */
public class NonUniqueException extends RuntimeException {
	private static final long serialVersionUID = 8869874459421998845L;

	private static Serializer serializer = new JacksonSerializer(false);

	public NonUniqueException() {
	}

	public NonUniqueException(Object cond) {
		super("Condition isn't unique, cond=[" + cond.getClass().getName() + "->" + serializer.serialize(cond) + "].");
	}
}
