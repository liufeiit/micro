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

	public static void selectedApp(HttpSession session, long appId) {
		session.setAttribute(Static.SELECTED_APP, appId);
	}
	
	public static long getSelectedApp(HttpSession session) {
		Object val = session.getAttribute(Static.SELECTED_APP);
		if(val == null) {
			return 0L;
		}
		long appId = ((Long) val).longValue();
		if(appId <= 0L) {
			return 0L;
		}
		return appId;
	}
	
	public static void login(HttpSession session, UserDO user) {
		session.setAttribute(Static.ONLINE_USER, user);
		/*if(!Application.isRedisAvailable()) {
			return;
		}
		final String id = session.getId();
		final String name = user.getName();
		final byte[] sessionId = CharsetUtils.getUTF8Bytes(id);
		final String userGson = Static.gson.toJson(user);
		Application.redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.hSet(sessionId, CharsetUtils.getUTF8Bytes(Static.ONLINE_USER), CharsetUtils.getUTF8Bytes(userGson));
				log.error("Session[" + id + "] Binding User Named " + name + " is login Success.");
				System.err.println("Session[" + id + "] Binding User Named " + name + " is login Success.");
				return true;
			}
		});*/
	}
	
	public static void logout(HttpSession session) {
		session.removeAttribute(Static.ONLINE_USER);
		/*if(!Application.isRedisAvailable()) {
			return;
		}
		final String id = session.getId();
		final byte[] sessionId = CharsetUtils.getUTF8Bytes(id);
		Application.redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.hDel(sessionId, CharsetUtils.getUTF8Bytes(Static.ONLINE_USER));
				log.error("User Session[" + id + "] " + " is logout Success.");
				System.err.println("User Session[" + id + "] " + " is logout Success.");
				return true;
			}
		});*/
	}

	public static boolean isLogin(HttpSession session) {
		return session.getAttribute(Static.ONLINE_USER) != null;
		/*if(!Application.isRedisAvailable()) {
			return session.getAttribute(Static.ONLINE_USER) != null;
		}
		final byte[] sessionId = CharsetUtils.getUTF8Bytes(session.getId());
		Boolean isLogin = Application.redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.hExists(sessionId, CharsetUtils.getUTF8Bytes(Static.ONLINE_USER));
			}
		});
		if (isLogin != null) {
			return isLogin;
		}
		return false;*/
	}

	public static UserDO getUser(HttpSession session) {
		return (UserDO) session.getAttribute(Static.ONLINE_USER);
		/*if(!Application.isRedisAvailable()) {
			return (UserDO) session.getAttribute(Static.ONLINE_USER);
		}
		final byte[] sessionId = CharsetUtils.getUTF8Bytes(session.getId());
		return Application.redisTemplate.execute(new RedisCallback<UserDO>() {
			@Override
			public UserDO doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] bytes = connection.hGet(sessionId, CharsetUtils.getUTF8Bytes(Static.ONLINE_USER));
				String userGson = CharsetUtils.buildFromUTF8(bytes);
				if (StringUtil.isBlank(userGson)) {
					return null;
				}
				return Static.gson.fromJson(userGson, UserDO.class);
			}
		});*/
	}
}