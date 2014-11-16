package micro.core.dao.statement;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年11月16日 下午9:40:12
 */
public interface RevenueMapper {

	String Test_RevenueTable_Exist = "SELECT `TABLE_NAME` FROM `INFORMATION_SCHEMA`.`TABLES` WHERE `TABLE_NAME`= :tableName";
	
	String Create_Table_SQL_Template = "CREATE TABLE `%s` ("
			+ "`content_history_id` bigint(20) NOT NULL AUTO_INCREMENT, "
			+ "`user_id` bigint(20) DEFAULT NULL, "
			+ "`content_id` int(11) NOT NULL, "
			+ "`ip` bigint(20) NOT NULL, "
			+ "`created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00', "
			+ "PRIMARY KEY (`content_history_id`)) "
			+ "ENGINE=InnoDB DEFAULT CHARSET=utf8;";
	
	String COUNT_USER_IP_SQL_Template = "SELECT COUNT(DISTINCT ip) as IP FROM %s WHERE user_id = :userId";
	
	String COUNT_USER_PV_SQL_Template = "SELECT COUNT(ip) as PV FROM %s WHERE user_id = :userId";
	
	String COUNT_ARTICLE_IP_SQL_Template = "SELECT COUNT(DISTINCT ip) as IP FROM %s WHERE content_id = :content_id";
	
	String COUNT_ARTICLE_PV_SQL_Template = "SELECT COUNT(ip) as PV FROM %s WHERE content_id = :content_id";
	
}