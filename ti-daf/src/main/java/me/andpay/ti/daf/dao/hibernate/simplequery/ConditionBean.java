package me.andpay.ti.daf.dao.hibernate.simplequery;


/**
 * 条件Bean类。
 * 
 * @author sea.bao
 */
public class ConditionBean {
	private String selectClause;
	
	private String fromClause;
	
	private String whereClause;

	public String getSelectClause() {
		return selectClause;
	}

	public void setSelectClause(String selectClause) {
		this.selectClause = selectClause;
	}

	public String getFromClause() {
		return fromClause;
	}

	public void setFromClause(String fromClause) {
		this.fromClause = fromClause;
	}

	public String getWhereClause() {
		return whereClause;
	}

	public void setWhereClause(String whereClause) {
		this.whereClause = whereClause;
	}

}
