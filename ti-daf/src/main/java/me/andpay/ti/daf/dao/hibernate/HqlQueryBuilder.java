package me.andpay.ti.daf.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Hql查询构建器类。
 * 
 * @author sea.bao
 */
public class HqlQueryBuilder {
	/**
	 * 语句
	 */
	protected StringBuffer clause = new StringBuffer();

	/**
	 * 参数
	 */
	protected List<Object> paras = new ArrayList<Object>();

	public HqlQueryBuilder() {
	}

	public static HqlQueryBuilder newInstance() {
		return new HqlQueryBuilder();
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

	public HqlQueryBuilder add(String clause, Object... paras) {
		if (this.clause.length() > 0) {
			this.clause.append(" ");
		}

		this.clause.append(clause);
		for (Object para : paras) {
			this.paras.add(para);
		}

		return this;
	}
	
	public HqlQueryBuilder add(String clause, String... paras) {
		if (this.clause.length() > 0) {
			this.clause.append(" ");
		}

		this.clause.append(clause);
		for (Object para : paras) {
			this.paras.add(para);
		}

		return this;
	}

	public Query build(Session session) {
		Query query = session.createQuery(clause.toString());
		for (int i = 0; i < paras.size(); i++) {
			query.setParameter(i, paras.get(i));
		}

		return query;
	}
}
