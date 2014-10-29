package micro.core.dao.statement;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年10月29日 下午11:57:58
 */
public interface UserMapper {
	String ADD_SQL = "INSERT INTO usr "
			+ "(name, email, mobile, weixin, password, gmt_created, gmt_modified) VALUES "
			+ "(:name, :email, :mobile, :weixin, :password, NOW(), NOW());";

	String SELECT_BYNAME_SQL = "SELECT id, name, email, mobile, weixin, password, gmt_created, gmt_modified FROM usr WHERE name = :name;";
}