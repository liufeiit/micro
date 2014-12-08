package me.andpay.ti.daf.dao.hibernate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.andpay.ti.util.DateUtil;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class HibernateDaoTest {
	@Autowired
	private UserDao userDao;

	@Autowired
	private SessionFactory sessionFactory;

	@Test
	public void test() {
		User user = new User();
		user.setCreateTime(new java.util.Date());
		user.setLastUpdateTime(user.getCreateTime());
		user.setName("James.Bond");

		userDao.add(user);

		QueryUserCond cond = new QueryUserCond();
		cond.setName("James%"); // anno at getName()
		cond.setOrders("name");

		List<User> users = userDao.query(cond, 0, -1);
		assertTrue(users.size() > 0);

		cond = new QueryUserCond();
		cond.setStartCreateDate(new java.util.Date()); // @StartDate
		cond.setEndCreateDate(new java.util.Date()); // @EndDate

		users = userDao.query(cond, 0, -1);
		assertTrue(users.size() > 0);

		user = users.get(0);
		user.setName("Jame.Bond");
		boolean updFlag = userDao.update(user, "name");
		assertTrue(updFlag);

		Map<String, Object> paraValues = new HashMap<String, Object>();
		paraValues.put("name", "James.Bond");
		updFlag = userDao.update(user.getId(), paraValues);

		assertTrue(updFlag);

		int prevSize = users.size();
		userDao.delete(user.getId());

		cond = new QueryUserCond();
		cond.setStartCreateDate(new java.util.Date());
		cond.setEndCreateDate(new java.util.Date());
		users = userDao.query(cond, 0, -1);

		assertEquals(prevSize - 1, users.size());
	}

	@Test
	public void testIsNull() {
		User user = new User();
		user.setCreateTime(new java.util.Date());
		user.setLastUpdateTime(user.getCreateTime());
		user.setName("Aaron.Cross");

		QueryUserCond cond = new QueryUserCond();
		cond.setNameIsNull(false); // @IsNull
		assertTrue(userDao.count(cond) > 0);
		assertTrue(userDao.exists(cond));

		cond = new QueryUserCond();
		cond.setNameIsNull(true);
		assertEquals(0, userDao.count(cond));
		assertFalse(userDao.exists(cond));

		cond = new QueryUserCond();
		cond.setLastUpdateTimeIsNull("abc"); // false:Boolean.valueOf(prop.toString())
		assertTrue(userDao.count(cond) > 0);
		assertTrue(userDao.exists(cond));

		cond = new QueryUserCond();
		cond.setLastUpdateTimeIsNull("TRUE");
		assertEquals(0, userDao.count(cond));
		assertFalse(userDao.exists(cond));

		userDao.add(user);
	}

	@Test
	public void testCompositeExpression() {
		List<String> longNames = new ArrayList<String>();
		longNames.add("Jeremy.Renner");

		while (longNames.size() < 3) {
			longNames.add(Long.toString(System.nanoTime()));
		}

		User user = null;
		for (String userName : longNames) {
			user = new User();
			user.setCreateTime(new java.util.Date());
			user.setLastUpdateTime(user.getCreateTime());
			user.setName(userName);

			userDao.add(user);
		}

		QueryUserCond cond = new QueryUserCond();
		cond.setId(user.getId());
		cond.setAtSameDay(true); // @CompositeExpression
		assertTrue(userDao.count(cond) > 0);
		assertTrue(userDao.exists(cond));

		cond = new QueryUserCond();
		cond.setId(user.getId());
		cond.setAtSameDay(false);
		assertEquals(0, userDao.count(cond));
		assertFalse(userDao.exists(cond));

		cond = new QueryUserCond();
		cond.setId(user.getId());
		cond.setDaysBetween(0);
		assertTrue(userDao.count(cond) > 0);
		assertTrue(userDao.exists(cond));

		cond = new QueryUserCond();
		cond.setLongNames(longNames);
		cond.setCreateDate(new Date());
		assertEquals(2, userDao.count(cond));
		assertTrue(userDao.exists(cond));

		cond = new QueryUserCond();
		cond.setCreateDate(DateUtil.rollDate(new Date(), Calendar.MONTH, 1));
		assertEquals(0, userDao.count(cond));
		assertFalse(userDao.exists(cond));
	}

	@Test
	public void testStartsWith() {
		User user = new User();
		user.setCreateTime(new java.util.Date());
		user.setLastUpdateTime(user.getCreateTime());
		user.setName("Robin.Williams");
		
		userDao.add(user);

		QueryUserCond cond = new QueryUserCond();
		cond.setNamePrefix("Robin"); // @StartsWith
		List<User> users = userDao.query(cond, 0, -1);
		assertTrue(users.size() > 0);

		cond = new QueryUserCond();
		cond.setNamePrefix("Williams");
		users = userDao.query(cond, 0, -1);
		assertTrue(users.size() == 0);
		
		cond = new QueryUserCond();
		cond.setNamePrefix(" ");
		users = userDao.query(cond, 0, -1);
		assertTrue(users.size() == 0);
	}
}
