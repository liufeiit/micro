package me.andpay.ti.daf.dao.hibernate;

import me.andpay.ti.util.JacksonSerializer;
import me.andpay.ti.util.Serializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BadSql日志器类。
 *  
 * @author sea.bao
 */
public class BadSqlLogger {
	private static Logger logger = LoggerFactory.getLogger("BadSqlLogger");

	/**
	 * 序列化器
	 */
	private static Serializer serializer = new JacksonSerializer(false);

	/**
	 * 记录BadSql日志。
	 * 
	 * @param item
	 */
	public static void log(BadSqlLogItem item) {
		logger.error(new String(serializer.serialize(item)));
	}

}
