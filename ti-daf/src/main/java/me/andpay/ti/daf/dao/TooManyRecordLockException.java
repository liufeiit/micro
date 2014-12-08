package me.andpay.ti.daf.dao;

import me.andpay.ti.util.JacksonSerializer;
import me.andpay.ti.util.Serializer;

/**
 * 太多记录互斥锁定异常类。
 * 
 * @author sea.bao
 */
public class TooManyRecordLockException extends RuntimeException {
	private static final long serialVersionUID = 8869874459421998845L;

	private static Serializer serializer = new JacksonSerializer(false);

	public TooManyRecordLockException() {
	}

	public TooManyRecordLockException(Object cond) {
		super("Too many record will be lock, cond=[" + cond.getClass().getName() + "->" + serializer.serialize(cond) + "].");
	}
}
