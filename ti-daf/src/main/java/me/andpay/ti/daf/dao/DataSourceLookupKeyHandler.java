package me.andpay.ti.daf.dao;

import java.util.Stack;

/**
 * 数据源查找关键字处理器
 * 
 * @author alex
 */
public class DataSourceLookupKeyHandler {
	/**
	 * 查找关键字线程存储变量
	 */
	private static final ThreadLocal<Stack<Object>> LOOKUP_KEYS = new ThreadLocal<Stack<Object>>();

	/**
	 * 添加数据源查找关键字
	 * 
	 * @param lookupKey
	 */
	public static void setCurrentLookupKey(Object lookupKey) {
		Stack<Object> keyStack = LOOKUP_KEYS.get();
		if (keyStack == null) {
			keyStack = new Stack<Object>();
			LOOKUP_KEYS.set(keyStack);
		}

		keyStack.push(lookupKey);
	}

	/**
	 * 获取最新数据源查找关键字
	 * 
	 * @return
	 */
	public static Object getCurrentLookupKey() {
		Stack<Object> keyStack = LOOKUP_KEYS.get();
		if (keyStack == null) {
			return null;
		}

		return (keyStack.isEmpty() ? null : keyStack.peek());
	}

	/**
	 * 移除并返回最新的数据源查找关键字，如果已经没有任何关键字存在，则清理存储变量
	 */
	public static Object clearCurrentLookupKey() {
		Stack<Object> keyStack = LOOKUP_KEYS.get();
		if (keyStack == null) {
			return null;
		}

		Object key = (keyStack.isEmpty() ? null : keyStack.pop());
		if (keyStack.isEmpty()) {
			LOOKUP_KEYS.remove();
		}

		return key;
	}

	/**
	 * 清理所有数据源查找关键字
	 */
	public static void clearLookupKeys() {
		LOOKUP_KEYS.remove();
	}
}
