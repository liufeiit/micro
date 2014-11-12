package micro.core.dataobject;

import java.math.BigDecimal;
import java.util.Date;

import tulip.data.annotation.Column;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月5日 上午10:49:20
 */
public class UserDO extends BaseDO {

	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
	private long userId;

	@Column(name = "user_group_id")
	private int groupId;

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
	private BigDecimal accountBalance = new BigDecimal(0);

	@Column(name = "is_email_verified")
	private boolean emailVerified = false;

	@Column(name = "is_mobile_verified")
	private boolean mobileVerified = false;

	@Column(name = "client_ip")
	private long clientIp;

	@Column(name = "last_login")
	private Date lastLogin;

	@Column(name = "last_ip")
	private long lastIp;
}