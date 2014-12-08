package me.andpay.ti.daf.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ScratchTest {
	static Log logger = LogFactory.getLog(ScratchTest.class);

	public static void main(String[] args) throws Exception {
		// create and configure beans
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring-config/ti-daf-schema.xml" });

		UserDao userDao = context.getBean("test.UserDao", UserDao.class);

		List<User> list = new ArrayList<User>();
		for (int i = 0; i < 100000; i++) {
			User user = new User();
			user.setName("name" + String.valueOf(i));
			user.setCreateTime(new java.util.Date());
			user.setLastUpdateTime(user.getCreateTime());
			list.add(user);

			if (list.size() > 100) {
				userDao.batchAdd(list);
				list.clear();
			}
		}

		if (list.isEmpty() == false) {
			userDao.batchAdd(list);
		}

		System.exit(0);
	}

}
