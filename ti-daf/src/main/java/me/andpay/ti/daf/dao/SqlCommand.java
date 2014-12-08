package me.andpay.ti.daf.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * SqlCommand类。
 * 
 * @author sea.bao
 */
public class SqlCommand {
	/**
	 * 方法
	 */
	private String clause;

	/**
	 * 参数
	 */
	private List<Object> parameters = new ArrayList<Object>();

	public SqlCommand() {
	}

	public String getClause() {
		return clause;
	}

	public void setClause(String clause) {
		this.clause = clause;
	}

	public void addParameter(Object value) {
		this.parameters.add(value);
	}

	public void clearParameters() {
		parameters.clear();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("sqlClause=[" + clause + "], ");
		for (int i = 0; i < parameters.size(); i++) {
			sb.append("Parameter[" + i + "]=[" + parameters.get(i) + "]");
		}
		
		return sb.toString();
	}

	public List<Object> getParameters() {
		return parameters;
	}

	public void setParameters(List<Object> parameters) {
		this.parameters = parameters;
	}
}