package me.andpay.ti.daf.dao;

/**
 * Sla类。
 * 
 * @author sea.bao
 */
public class Sla {
	/**
	 * 缺省AvgTime为2秒
	 */
	public static final int DEFAULT_AVG_TIME = 2;
	
	protected static ThreadLocal<Integer> avgTimeHolder = new ThreadLocal<Integer>();
	
	public static void setAvgTime(int avgTime) {
		avgTimeHolder.set(avgTime);
	}
	
	public static int getAvgTime() {
		Integer avgTime = avgTimeHolder.get();
		if ( avgTime == null ) {
			return DEFAULT_AVG_TIME;
		}
		
		avgTimeHolder.remove();
		return avgTime;
	}
}
