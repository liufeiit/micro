package me.andpay.ti.daf.dao.hibernate;

import me.andpay.ti.base.AppBizException;
import me.andpay.ti.daf.dao.IterateCallback;
import me.andpay.ti.daf.dao.SessionContext;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 基于Hibernate实现CrudDao测试类。
 * 
 * @author sea.bao
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config/ti-daf-schema.xml" })
@Transactional
public class HibernateDaoLockTest {
	@Autowired
	private UserDao userDao;

	@Autowired
	private SessionFactory sessionFactory;

	@Test
	public void test() throws Exception {
		User user = new User();
		user.setCreateTime(new java.util.Date());
		user.setLastUpdateTime(user.getCreateTime());
		user.setName("James.Bond");

		userDao.add(user);
		
		//user = userDao.getForUpdate(2L);
		
		QueryUserCond cond = new QueryUserCond();
		userDao.iterateForUpdate(cond, new IterateCallback<User>() {
			public boolean doElement(SessionContext sessCtx, User element) throws AppBizException {
				return true;
			}
		});
		
		userDao.iterate(cond, new IterateCallback<User>() {
			public boolean doElement(SessionContext sessCtx, User element) throws AppBizException {
				return true;
			}
		});
	}
}
