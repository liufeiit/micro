package me.andpay.ti.daf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import me.andpay.ti.util.CloseUtil;

/**
 * 查询构建器类。
 * 
 * @author sea.bao
 */
public class QueryBuilder {
	/**
	 * 语句
	 */
	protected StringBuffer clause = new StringBuffer();

	/**
	 * 参数
	 */
	protected List<Object> paras = new ArrayList<Object>();

	public QueryBuilder() {
	}

	public static QueryBuilder newInstance() {
		return new QueryBuilder();
	}
	
	public static String placeholders(int num) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < num; i++) {
			if (sb.length() > 0) {
				sb.append(",");
			}

			sb.append("?");
		}

		return sb.toString();
	}
	
	public QueryBuilder add(String clause, Object... paras) {
		if (this.clause.length() > 0) {
			this.clause.append(" ");
		}

		this.clause.append(clause);
		for (Object para : paras) {
			this.paras.add(para);
		}

		return this;
	}
	
	public QueryBuilder add(String clause, String... paras) {
		if (this.clause.length() > 0) {
			this.clause.append(" ");
		}

		this.clause.append(clause);
		for (Object para : paras) {
			this.paras.add(para);
		}

		return this;
	}

	public PreparedStatement build(Connection conn) {
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(clause.toString());

			for (int i = 0; i < paras.size(); i++) {
				pstmt.setObject(i + 1, paras.get(i));
			}
		} catch (SQLException e) {
			if (pstmt != null) {
				CloseUtil.close(pstmt);
			}

			throw new RuntimeException(e.getMessage(), e);
		}

		return pstmt;
	}
}
