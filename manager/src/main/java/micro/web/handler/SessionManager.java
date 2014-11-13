package micro.web.handler;

import javax.servlet.http.HttpSession;

import micro.core.dataobject.UserDO;
import micro.web.util.Static;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月16日 下午12:54:09
 */
public class SessionManager {

	static final Log log = LogFactory.getLog(SessionManager.class);
	
	public static void login(HttpSession session, UserDO user) {
		session.setAttribute(Static.ONLINE_USER, user);
	}
	
	public static void logout(HttpSession session) {
		session.removeAttribute(Static.ONLINE_USER);
	}

	public static boolean isLogin(HttpSession session) {
		return session.getAttribute(Static.ONLINE_USER) != null;
	}

	public static UserDO getUser(HttpSession session) {
		return (UserDO) session.getAttribute(Static.ONLINE_USER);
	}
}