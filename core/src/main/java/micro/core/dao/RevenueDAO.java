package micro.core.dao;

import java.util.List;

import micro.core.dataobject.UserDO;
import micro.core.dataobject.UserIncomeDO;
import micro.core.service.PageQuery;

/**
 * 用户收入信息。
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年11月16日 下午9:38:07
 */
public interface RevenueDAO {
	
	String BASE_TABLE_NAME = "content_history_";
	
	String getTableName(int nextMonth);

	boolean isExistTable(String tableName) throws DAOException;

	void createTable(String tableName) throws DAOException;

	long countUserIP(int nextMonth, long userId);

	long countUserPV(int nextMonth, long userId);

	long referee_award(int nextMonth, long userId);

	long activity_award(int nextMonth, long userId);

	long countArticleIP(int nextMonth, long articleId);

	long countArticlePV(int nextMonth, long articleId);

	boolean hasReferee(long userId);

	UserDO queryUser(long userId) throws DAOException;

	UserIncomeDO queryIncome(int month, long userId) throws DAOException;

	UserIncomeDO sumIncome(int month, long userId) throws DAOException;

	List<UserDO> queryUser(PageQuery query) throws DAOException;
}