package me.andpay.ti.daf.dao.hibernate;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BadSql跟踪器类。
 * 
 * @author sea.bao
 */
public class BadSqlTracker {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 服务线程
	 */
	private Thread serviceThread;

	/**
	 * 调用时间
	 */
	private Date callTime;

	/**
	 * BadSql语句
	 */
	private String badSql;

	/**
	 * 语句参数
	 */
	private Object[] parameters;

	/**
	 * 定时任务
	 */
	private volatile TimerTask timerTask;

	/**
	 * 定时器
	 */
	private static Timer timer = new Timer("badSqlTracker", true);

	/**
	 * 过期时间
	 */
	private long expiredTime;

	/**
	 * RpcCall跟踪器方法
	 */
	private static Method rpcCallTrackerMethod = null;

	static {
		try {
			Class<?> clazz = Class.forName("me.andpay.ti.ti.lnk.rpc.RpcCallTracker");
			rpcCallTrackerMethod = clazz.getDeclaredMethod("getTrackingCode");
		} catch (Exception e) {
		}
	}

	public static BadSqlTracker start(int avgTime, String badSql, Object... parameters) {
		BadSqlTracker tracker = new BadSqlTracker(badSql, parameters);
		tracker.start(avgTime);

		return tracker;
	}

	public BadSqlTracker(String badSql, Object... parameters) {
		this.badSql = badSql;
		this.parameters = parameters;
	}

	private BadSqlLogItem getLogItem() {
		BadSqlLogItem item = new BadSqlLogItem();
		item.setParameters(parameters);
		item.setCallTime(callTime);
		item.setExpiredTime(expiredTime);
		item.setBadSql(badSql);

		if (rpcCallTrackerMethod != null) {
			try {
				item.setTrackingCode((String) rpcCallTrackerMethod.invoke(null));
			} catch (Exception e) {
			}
		}

		StringBuffer sb = new StringBuffer();
		for (StackTraceElement element : serviceThread.getStackTrace()) {
			if (sb.length() > 0) {
				sb.append("\n");
			}

			sb.append(element.toString());
		}

		item.setStackTrace(sb.toString());

		return item;
	}

	public void start(int avgTime) {
		serviceThread = Thread.currentThread();
		callTime = new java.util.Date();
		expiredTime = callTime.getTime() + avgTime * 1000;
		timerTask = new TimerTask() {
			@Override
			public void run() {
				try {
					BadSqlLogItem item = getLogItem();
					BadSqlLogger.log(item);
				} catch (Throwable e) {
					logger.error("BadSqlLogger.log meet error.", e);
				}
			}
		};

		timer.schedule(timerTask, avgTime * 1000);

		if (logger.isDebugEnabled()) {
			logger.debug("start tracker.");
		}
	}

	public void stop() {
		timerTask.cancel();

		if (logger.isDebugEnabled()) {
			logger.debug("stop tracker.");
		}
	}

	public Thread getServiceThread() {
		return serviceThread;
	}

	public Date getCallTime() {
		return callTime;
	}

	public String getBadSql() {
		return badSql;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public long getExpiredTime() {
		return expiredTime;
	}

}
