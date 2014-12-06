package micro.core.dao.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import micro.core.dao.BaseDAO;
import micro.core.dao.SettingDAO;

import org.springframework.stereotype.Repository;

import tulip.util.StringUtil;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年12月6日 下午3:48:39
 */
@Repository("settingDAO")
public class DefaultSettingDAO extends BaseDAO implements SettingDAO {

	private final ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<String, String>();
	
	@Override
	public String getSetting(String group, String key, String defaultSetting) {
		String K = "setting:" + group + ":" + key;
		String setting = cache.get(K);
		if(StringUtil.isNotBlank(setting)) {
			return setting;
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("group", group);
		paramMap.put("key", key);
		setting = jdbcTemplate.queryForObject("SELECT value FROM site_setting WHERE group = :group AND key = :key;", paramMap, String.class);
		if (StringUtil.isBlank(setting)) {
			return defaultSetting;
		}
		cache.put(K, setting);
		return setting;
	}
}
