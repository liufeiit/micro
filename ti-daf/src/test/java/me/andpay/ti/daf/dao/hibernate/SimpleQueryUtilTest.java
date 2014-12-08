package me.andpay.ti.daf.dao.hibernate;

import me.andpay.ti.daf.dao.hibernate.simplequery.SimpleQueryUtil;

import org.junit.Assert;
import org.junit.Test;

/**
 * SimpleQueryUtil测试类
 * 
 * @author alex
 */
public class SimpleQueryUtilTest {
	@Test
	public void testGetDefaultPersistenceAlias() {
		Assert.assertNull(SimpleQueryUtil.getDefaultPersistenceAlias(null));
		Assert.assertNull(SimpleQueryUtil.getDefaultPersistenceAlias(""));
		Assert.assertNull(SimpleQueryUtil.getDefaultPersistenceAlias("         "));
		Assert.assertNull(SimpleQueryUtil.getDefaultPersistenceAlias(" , "));
		Assert.assertNull(SimpleQueryUtil.getDefaultPersistenceAlias("ObjectA  "));
		Assert.assertNull(SimpleQueryUtil.getDefaultPersistenceAlias("ObjectA , ObjectB b"));
		Assert.assertNull(SimpleQueryUtil.getDefaultPersistenceAlias("ObjectA JOIN ObjectB b"));
		Assert.assertNull(SimpleQueryUtil.getDefaultPersistenceAlias("ObjectA full join ObjectB b"));
		Assert.assertNull(SimpleQueryUtil.getDefaultPersistenceAlias("ObjectA Inner Join ObjectB b"));
		Assert.assertNull(SimpleQueryUtil.getDefaultPersistenceAlias("ObjectA LEFT join ObjectB b"));
		Assert.assertNull(SimpleQueryUtil.getDefaultPersistenceAlias("ObjectA right outer join ObjectB b"));

		Assert.assertEquals("a", SimpleQueryUtil.getDefaultPersistenceAlias("ObjectA a "));
		Assert.assertEquals("a", SimpleQueryUtil.getDefaultPersistenceAlias("ObjectA a , ObjectB b"));
		Assert.assertEquals("a", SimpleQueryUtil.getDefaultPersistenceAlias("ObjectA a JOIN ObjectB b"));
		Assert.assertEquals("a", SimpleQueryUtil.getDefaultPersistenceAlias("ObjectA a full join ObjectB b"));
		Assert.assertEquals("a", SimpleQueryUtil.getDefaultPersistenceAlias("ObjectA a Inner Join ObjectB b"));
		Assert.assertEquals("a", SimpleQueryUtil.getDefaultPersistenceAlias("ObjectA a LEFT join ObjectB b"));
		Assert.assertEquals("a", SimpleQueryUtil.getDefaultPersistenceAlias("ObjectA a right outer join ObjectB b"));
	}

	@Test
	public void testInsertDefaultPersistenceAlias() {
		Assert.assertEquals("ObjectA a", SimpleQueryUtil.insertDefaultPersistenceAlias("ObjectA", "a"));
		Assert.assertEquals("ObjectA a , ObjectB b",
				SimpleQueryUtil.insertDefaultPersistenceAlias("ObjectA , ObjectB b", "a"));
		Assert.assertEquals("ObjectA a JOIN ObjectB b",
				SimpleQueryUtil.insertDefaultPersistenceAlias("ObjectA JOIN ObjectB b", "a"));
		Assert.assertEquals("ObjectA a right outer join ObjectB b",
				SimpleQueryUtil.insertDefaultPersistenceAlias("ObjectA right outer join ObjectB b", "a"));

		Assert.assertNull(SimpleQueryUtil.insertDefaultPersistenceAlias(null, "a"));
		Assert.assertNull(SimpleQueryUtil.insertDefaultPersistenceAlias("", "a"));
		Assert.assertNull(SimpleQueryUtil.insertDefaultPersistenceAlias(" , ", "a"));
		Assert.assertNull(SimpleQueryUtil.insertDefaultPersistenceAlias("         ", "a"));
		Assert.assertEquals("ObjectA", SimpleQueryUtil.insertDefaultPersistenceAlias("ObjectA", null));
	}
}
