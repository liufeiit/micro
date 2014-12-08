package me.andpay.ti.daf.dao.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ScratchTest2 {
	static Log logger = LogFactory.getLog(ScratchTest.class);

	public static void main(String[] args) throws Exception {
		Class<?> clazz = Class.forName("org.apache.tomcat.jdbc.pool.DataSource");
		clazz.getMethod("close");
	}

}
