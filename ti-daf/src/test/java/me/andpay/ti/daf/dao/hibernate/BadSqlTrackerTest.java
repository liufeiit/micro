package me.andpay.ti.daf.dao.hibernate;

import org.junit.Test;

/**
 * BadSql跟踪器测试类。
 * 
 * @author sea.bao
 */
public class BadSqlTrackerTest {

	@Test
	public void test() throws InterruptedException {
		BadSqlTracker tracker = BadSqlTracker.start(1, "select * from User where name like ?", new Object[]{"%James%"});
		Thread.sleep(1200);
		tracker.stop();
		
		tracker = BadSqlTracker.start(3, "select * from User where name like ?", new Object[]{"%James%"});
		tracker.stop();
		Thread.sleep(1200);
	}

}
