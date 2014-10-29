package micro.core.util;

/**
 * 错误码
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月4日 下午7:52:58
 */
public enum ErrorCode {
	Success								(200, 		"成功"),

	Error_CreateUser					(-1000, 	"创建用户失败"),
	
	Error_UserLogin						(-2000, 	"用户登录失败"),
	Error_NonUser						(-2001, 	"用户不存在"),
	Error_ErrPasswd						(-2002, 	"密码错误"),
	Error_InputUsername					(-2003, 	"请输入用户名"),
	Error_InputPasswd					(-2004, 	"请输入密码"),
	
	Error_Query							(-3000, 	"查询失败"),
	
	Error_Permission					(-9000, 	"非法权限"),
	
	;
	public final int code;
	public final String description;
	private ErrorCode(int code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public boolean eq(int code) {
		return this.code == code;
	}
	
	public boolean eq(ErrorCode code) {
		return this.code == code.code;
	}
	
	public static ErrorCode parse(int code) {
		for(ErrorCode e : values()) {
			if(e.code == code) {
				return e;
			}
		}
		return Error_UserLogin;
	}
	
	public static String getMessage(int code) {
		for(ErrorCode e : values()) {
			if(e.code == code) {
				return e.description;
			}
		}
		return null;
	}
}