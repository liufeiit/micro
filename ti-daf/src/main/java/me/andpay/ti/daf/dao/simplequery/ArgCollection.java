package me.andpay.ti.daf.dao.simplequery;

import java.util.Collection;

/**
 * 参数类
 * @author sea.bao
 *
 */
public class ArgCollection {
	private Collection<?> args;

	public ArgCollection(Collection<?> args) {
		this.args = args;
	}

	public Collection<?> getArgs() {
		return args;
	}

	public void setArgs(Collection<?> args) {
		this.args = args;
	}
}
