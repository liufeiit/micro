package micro.core.dataobject;

import java.math.BigDecimal;

import tulip.data.annotation.Column;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年11月12日 下午1:21:57
 */
public class UserIncomeDO extends BaseDO {

	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
	private long userId;
	@Column(name = "year_month")
	private String yearMonth;
	@Column(name = "u_ip")
	private long uip;
	@Column(name = "pv")
	private long pv;
	@Column(name = "referee_award")
	private long referee;
	@Column(name = "activity_award")
	private long activity;
	@Column(name = "total_income")
	private BigDecimal totalIncome = new BigDecimal(0.00);

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return the yearMonth
	 */
	public String getYearMonth() {
		return yearMonth;
	}

	/**
	 * @param yearMonth
	 *            the yearMonth to set
	 */
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	/**
	 * @return the uip
	 */
	public long getUip() {
		return uip;
	}

	/**
	 * @param uip
	 *            the uip to set
	 */
	public void setUip(long uip) {
		this.uip = uip;
	}

	/**
	 * @return the pv
	 */
	public long getPv() {
		return pv;
	}

	/**
	 * @param pv
	 *            the pv to set
	 */
	public void setPv(long pv) {
		this.pv = pv;
	}

	/**
	 * @return the referee
	 */
	public long getReferee() {
		return referee;
	}

	/**
	 * @param referee
	 *            the referee to set
	 */
	public void setReferee(long referee) {
		this.referee = referee;
	}

	/**
	 * @return the activity
	 */
	public long getActivity() {
		return activity;
	}

	/**
	 * @param activity
	 *            the activity to set
	 */
	public void setActivity(long activity) {
		this.activity = activity;
	}

	/**
	 * @return the totalIncome
	 */
	public BigDecimal getTotalIncome() {
		return totalIncome;
	}

	/**
	 * @param totalIncome
	 *            the totalIncome to set
	 */
	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}
}
