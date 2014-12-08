package me.andpay.ti.daf.dao.hibernate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 基于Hibernate实现CrudDao测试类。
 * 
 * @author sea.bao
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config/ti-daf-schema.xml" })
public class HibernateServiceTest {
	@Autowired
	@Qualifier("test.UserService")
	private UserService userService;
	
	@Test
	public void test() {
		User user = new User();
		user.setCreateTime(new java.util.Date());
		user.setLastUpdateTime(user.getCreateTime());
		user.setName("James.Bond");

		userService.addUser(user);
		
		System.out.println("add userId=[" + user.getId() + "].");
		
		user = userService.getUser(user.getId());
		
		System.out.println("got user.");
		
		user = userService.getUser(user.getId());
		
		System.out.println("got user.");
		
	}

}
