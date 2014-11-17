package micro.core.dataobject;

import java.util.Date;

import tulip.data.annotation.Column;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年11月17日 下午12:53:23
 */
public class UserDO extends BaseDO {

	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
	private long id;

	@Column(name = "user_group_id")
	private int userGroupId;

	@Column(name = "status")
	private String status;

	@Column(name = "email")
	private String email;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "salt")
	private String salt;

	@Column(name = "fullname")
	private String fullname;

	@Column(name = "account_balance")
	private double accountBalance;

	@Column(name = "is_email_verified")
	private byte isEmailVerified = 0;

	@Column(name = "is_mobile_verified")
	private byte isMobileVerified = 0;

	@Column(name = "client_ip")
	private long clientIp;

	@Column(name = "last_login")
	private Date lastLogin;

	@Column(name = "last_ip")
	private long lastIp;
	
	private UserIncomeDO currentIncome;
	
	private UserIncomeDO lastMonthIncome;

	/**
	 * @return the currentIncome
	 */
	public UserIncomeDO getCurrentIncome() {
		return currentIncome;
	}

	/**
	 * @param currentIncome the currentIncome to set
	 */
	public void setCurrentIncome(UserIncomeDO currentIncome) {
		this.currentIncome = currentIncome;
	}

	/**
	 * @return the lastMonthIncome
	 */
	public UserIncomeDO getLastMonthIncome() {
		return lastMonthIncome;
	}

	/**
	 * @param lastMonthIncome the lastMonthIncome to set
	 */
	public void setLastMonthIncome(UserIncomeDO lastMonthIncome) {
		this.lastMonthIncome = lastMonthIncome;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the userGroupId
	 */
	public int getUserGroupId() {
		return userGroupId;
	}

	/**
	 * @param userGroupId
	 *            the userGroupId to set
	 */
	public void setUserGroupId(int userGroupId) {
		this.userGroupId = userGroupId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * @param salt
	 *            the salt to set
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname
	 *            the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @return the accountBalance
	 */
	public double getAccountBalance() {
		return accountBalance;
	}

	/**
	 * @param accountBalance
	 *            the accountBalance to set
	 */
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	/**
	 * @return the isEmailVerified
	 */
	public byte getIsEmailVerified() {
		return isEmailVerified;
	}

	/**
	 * @param isEmailVerified
	 *            the isEmailVerified to set
	 */
	public void setIsEmailVerified(byte isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	/**
	 * @return the isMobileVerified
	 */
	public byte getIsMobileVerified() {
		return isMobileVerified;
	}

	/**
	 * @param isMobileVerified
	 *            the isMobileVerified to set
	 */
	public void setIsMobileVerified(byte isMobileVerified) {
		this.isMobileVerified = isMobileVerified;
	}

	/**
	 * @return the clientIp
	 */
	public long getClientIp() {
		return clientIp;
	}

	/**
	 * @param clientIp
	 *            the clientIp to set
	 */
	public void setClientIp(long clientIp) {
		this.clientIp = clientIp;
	}

	/**
	 * @return the lastLogin
	 */
	public Date getLastLogin() {
		return lastLogin;
	}

	/**
	 * @param lastLogin
	 *            the lastLogin to set
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * @return the lastIp
	 */
	public long getLastIp() {
		return lastIp;
	}

	/**
	 * @param lastIp
	 *            the lastIp to set
	 */
	public void setLastIp(long lastIp) {
		this.lastIp = lastIp;
	}
}