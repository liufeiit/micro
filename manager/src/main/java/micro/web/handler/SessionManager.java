package micro.web.handler;

import javax.servlet.http.HttpSession;

import micro.core.dataobject.AdminDO;
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
	
	public static void login(HttpSession session, AdminDO user) {
		session.setAttribute(Static.ONLINE_ADMIN, user);
	}
	
	public static void logout(HttpSession session) {
		session.removeAttribute(Static.ONLINE_ADMIN);
	}

	public static boolean isLogin(HttpSession session) {
		return session.getAttribute(Static.ONLINE_ADMIN) != null;
	}

	public static AdminDO getAdmin(HttpSession session) {
		return (AdminDO) session.getAttribute(Static.ONLINE_ADMIN);
	}
}