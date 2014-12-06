package micro.core.dao;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年12月6日 下午3:46:41
 */
public interface SettingDAO {
	String getSetting(String group, String key, String defaultSetting);
}