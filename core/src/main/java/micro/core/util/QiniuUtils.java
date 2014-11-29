package micro.core.util;

import java.io.InputStream;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;

import tulip.util.StringUtil;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.net.CallRet;
import com.qiniu.api.rs.PutPolicy;
import com.qiniu.api.rs.RSClient;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年11月29日 下午2:18:48
 */
public class QiniuUtils {
	private final static Log log = LogFactory.getLog(QiniuUtils.class);

	private static final String Base_URI = "http://ec-blog.qiniudn.com/";

	private static final String BUCKET_NAME = "ec-blog";

	private static final Mac Qiniu_MAC;
	
	private static final RSClient client;

	static {
		Config.ACCESS_KEY = "bmgDw8-OaK2i2-WxFPq5GG2i_fnwQ6sEFEHRMr6U";
		Config.SECRET_KEY = "M-R7YcfHQF2FSzTt6Jm-W0t8ShT2xO_uvwuOibQJ";
		Qiniu_MAC = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
		client = new RSClient(Qiniu_MAC);
	}
	
	public static void main(String[] args) {
		String key = upload("hh.jpg", "D://姓氏.jpg");
		System.out.println(key);
	}

	public static String upload(String key, String localFile) {
		PutExtra extra = new PutExtra();
		PutRet ret = null;
		try {
			ret = IoApi.putFile(token(), key(key), localFile, extra);
		} catch (AuthException e) {
			log.error("Qiniu Auth Error.", e);
		} catch (JSONException e) {
			log.error("Qiniu JSON Error.", e);
		}
		if (!ret.ok()) {
			return StringUtil.EMPTY;
		}
		return httpURL(ret.getKey());
	}

	public static String upload(String key, InputStream in) {
		PutExtra extra = new PutExtra();
		PutRet ret = null;
		try {
			ret = IoApi.Put(token(), key(key), in, extra);
		} catch (AuthException e) {
			log.error("Qiniu Auth Error.", e);
		} catch (JSONException e) {
			log.error("Qiniu JSON Error.", e);
		}
		if (!ret.ok()) {
			return StringUtil.EMPTY;
		}
		return httpURL(ret.getKey());
	}

	public static boolean delete(String key) {
		CallRet ret = client.delete(BUCKET_NAME, key);
		if (ret.ok()) {
			return true;
		}
		return false;
	}
	
	private static String key(String key) {
		return CalendarUtil.formatDate(new Date(), CalendarUtil.YYYY_MM_DD_HH_MM_SS) + "_" + key;
	}

	private static String token() throws AuthException, JSONException {
		PutPolicy putPolicy = new PutPolicy(BUCKET_NAME);
		putPolicy.expires = 86400L;
		return putPolicy.token(Qiniu_MAC);
	}
	
	private static String httpURL(String key) {
		return Base_URI + key;
	}
}