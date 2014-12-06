package micro.core.dao;

import tulip.data.jdbc.support.JdbcDAOSupport;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月19日 下午3:10:50
 */
public class BaseDAO extends JdbcDAOSupport {
	protected static final String LIKE_SIG = "%";
	
	protected SettingDAO settingDAO;

	/**
	 * @param settingDAO the settingDAO to set
	 */
	public void setSettingDAO(SettingDAO settingDAO) {
		this.settingDAO = settingDAO;
	}
}