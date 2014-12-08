package me.andpay.ti.daf.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import me.andpay.ti.cache.api.HookExecutor;
import me.andpay.ti.cache.api.HookHandler;

/**
 * 保护Cache的Hook执行器访问类。
 * 
 * @author sea.bao
 * 
 */
public class ProtectedHookExecutorAccessor extends HookExecutor {
	public static void initHookExecutor() {
		Stack<List<HookHandler>> stack = HookExecutor.handlers.get();
		if (stack == null) {
			stack = new Stack<List<HookHandler>>();
			HookExecutor.handlers.set(stack);
		}

		stack.push(new ArrayList<HookHandler>());
	}

	public static void executeHookHandlers() {
		Stack<List<HookHandler>> stack = HookExecutor.handlers.get();
		List<HookHandler> handlers = stack.peek();
		for (HookHandler handler : handlers) {
			handler.execute();
		}
	}

	public static void cleanHookExector() {
		Stack<List<HookHandler>> stack = HookExecutor.handlers.get();
		if (stack == null) {
			return;
		}

		stack.pop();
		if (stack.isEmpty()) {
			HookExecutor.handlers.remove();
		}
	}
}
