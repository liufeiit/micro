package micro.core.dao.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import micro.core.dao.BaseDAO;
import micro.core.dao.DAOException;
import micro.core.dao.RevenueDAO;
import micro.core.dao.statement.RevenueMapper;
import micro.core.dataobject.UserDO;
import micro.core.dataobject.UserIncomeDO;
import micro.core.service.PageQuery;
import micro.core.util.CalendarUtil;
import micro.core.util.Static;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import tulip.data.jdbc.mapper.BeanParameterMapper;
import tulip.data.jdbc.mapper.BeanRowMapper;
import tulip.util.CollectionUtil;

/**
 * user_referee表中 referee_id推荐user_id
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年11月16日 下午9:39:33
 */
@Repository("revenueDAO")
public class DefaultRevenueDAO extends BaseDAO implements RevenueMapper, RevenueDAO {

	@Override
	public List<UserDO> queryUser(PageQuery query) throws DAOException {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("start", query.getIndex());
			paramMap.put("size", query.getSize());
			return jdbcTemplate.query(Query_User_List_SQL, 
					paramMap, 
					BeanRowMapper.newInstance(UserDO.class));
		} catch (DataAccessException e) {
			log.error("Query User Error.", e);
			throw new DAOException("Query User Error.", e);
		}
	}
	
	@Override
	public UserDO queryUser(long userId) throws DAOException {
		try {
			return jdbcTemplate.queryForObject(Query_User,
					BeanParameterMapper.newSingleParameterMapper("user_id", userId),
					BeanRowMapper.newInstance(UserDO.class));
		} catch (DataAccessException e) {
			log.error("Query User Error.", e);
			throw new DAOException("Query User Error.", e);
		}
	}

	/**
	 * 主要是直接查询收入记录
	 */
	@Override
	public UserIncomeDO queryIncome(int month, long userId) throws DAOException {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, month);
			String yearMonth = CalendarUtil.formatDate(calendar.getTime(), CalendarUtil.DATE_YM_DASH_FORMAT);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("user_id", userId);
			paramMap.put("year_month", yearMonth);
			return jdbcTemplate.queryForObject(Query_Income, paramMap, BeanRowMapper.newInstance(UserIncomeDO.class));
		} catch (DataAccessException e) {
			log.error("Query User Error.", e);
			throw new DAOException("Query User Error.", e);
		}
	}

	/**
	 * 主要是直接计算收入记录
	 */
	@Override
	public UserIncomeDO sumIncome(int month, long userId) throws DAOException {
		long ip = countUserIP(month, userId);
		long pv = countUserPV(month, userId);
		long referee_award = referee_award(month, userId);
		long activity_award = activity_award(month, userId);
		double totalIncome = (ip + referee_award + activity_award) * Static.PER_IP_PRICE;
		UserIncomeDO incomeDO = new UserIncomeDO();
		incomeDO.setActivity(activity_award);
		incomeDO.setPv(pv);
		incomeDO.setReferee(referee_award);
		incomeDO.setTotalIncome(totalIncome);
		incomeDO.setUip(ip);
		incomeDO.setUserId(userId);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, month);
		String yearMonth = CalendarUtil.formatDate(calendar.getTime(), CalendarUtil.DATE_YM_DASH_FORMAT);
		incomeDO.setYearMonth(yearMonth);
		return incomeDO;
	}

	/**
	 * 是否有推荐人，也就是是否是被推荐的人
	 */
	@Override
	public boolean hasReferee(long userId) {
		try {
			return !CollectionUtil.isEmpty(jdbcTemplate.queryForList(
					"SELECT referee_id FROM user_referee where user_id = :user_id;",
					BeanParameterMapper.newSingleParameterMapper("user_id", userId), Long.class));
		} catch (DataAccessException e) {
			log.error("Check User Has Referee Error.", e);
		}
		return false;
	}

	/**
	 * 推荐奖励，自己的下线（被我推荐的人）给的提成IP
	 */
	@Override
	public long referee_award(int nextMonth, long userId) {
		try {
			List<Long> userList = jdbcTemplate.queryForList(
					"SELECT user_id FROM user_referee where referee_id = :referee_id;",
					BeanParameterMapper.newSingleParameterMapper("referee_id", userId), Long.class);
			if (CollectionUtil.isEmpty(userList)) {
				return 0L;
			}
			long ip_all = 0L;
			for (Long uid : userList) {
				if (uid == null || uid <= 0L) {
					continue;
				}
				ip_all += countUserIP(nextMonth, uid);
			}
			return new Double(ip_all * Static.REFEREE_AWARD_PERCENT).longValue();
		} catch (DataAccessException e) {
			log.error("Query Referee User Error.", e);
		}
		return 0L;
	}

	/**
	 * 活跃度奖励，自己的博客（创建的或推荐的）的访问IP
	 */
	@Override
	public long activity_award(int nextMonth, long userId) {
		try {
			List<Long> articleList = jdbcTemplate.queryForList(
					"SELECT content_id FROM content WHERE creator = :userId AND type = 'private';",
					BeanParameterMapper.newSingleParameterMapper("userId", userId), Long.class);
			if (CollectionUtil.isEmpty(articleList)) {
				return 0L;
			}
			long ip_all = 0L;
			for (Long aid : articleList) {
				if (aid == null || aid <= 0L) {
					continue;
				}
				ip_all += countArticleIP(nextMonth, aid);
			}
			return new Double(ip_all * Static.ACTIVITY_AWARD_PERCENT).longValue();
		} catch (DataAccessException e) {
			log.error("Query Activity Error.", e);
		}
		return 0L;
	}

	/**
	 * 用户的IP总数
	 */
	@Override
	public long countUserIP(int nextMonth, long userId) {
		try {
			String tableName = getTableName(nextMonth);
			if(!isExistTable(tableName)) {
				createTable(tableName);
			}
			long ip = jdbcTemplate.queryForObject(String.format(COUNT_USER_IP_SQL_Template, tableName),
					BeanParameterMapper.newSingleParameterMapper("userId", userId), long.class);
			if (hasReferee(userId)) {
				return new Double(ip * (1.0D - Static.REFEREE_AWARD_PERCENT)).longValue();
			}
			return ip;
		} catch (Exception e) {
			log.error("Count IP Error.", e);
		}
		return 0L;
	}

	/**
	 * 用户的PV总数
	 */
	@Override
	public long countUserPV(int nextMonth, long userId) {
		try {
			String tableName = getTableName(nextMonth);
			if(!isExistTable(tableName)) {
				createTable(tableName);
			}
			return jdbcTemplate.queryForObject(String.format(COUNT_USER_PV_SQL_Template, tableName),
					BeanParameterMapper.newSingleParameterMapper("userId", userId), long.class);
		} catch (Exception e) {
			log.error("Count PV Error.", e);
		}
		return 0L;
	}

	/**
	 * 文章的IP总数
	 */
	@Override
	public long countArticleIP(int nextMonth, long articleId) {
		try {
			String tableName = getTableName(nextMonth);
			if(!isExistTable(tableName)) {
				createTable(tableName);
			}
			return jdbcTemplate.queryForObject(String.format(COUNT_ARTICLE_IP_SQL_Template, tableName),
					BeanParameterMapper.newSingleParameterMapper("content_id", articleId), long.class);
		} catch (Exception e) {
			log.error("Count IP Error.", e);
		}
		return 0L;
	}

	/**
	 * 文章的PV总数
	 */
	@Override
	public long countArticlePV(int nextMonth, long articleId) {
		try {
			String tableName = getTableName(nextMonth);
			if(!isExistTable(tableName)) {
				createTable(tableName);
			}
			return jdbcTemplate.queryForObject(String.format(COUNT_ARTICLE_PV_SQL_Template, tableName),
					BeanParameterMapper.newSingleParameterMapper("content_id", articleId), long.class);
		} catch (Exception e) {
			log.error("Count PV Error.", e);
		}
		return 0L;
	}

	@Override
	public boolean isExistTable(String tableName) throws DAOException {
		try {
			return !CollectionUtil.isEmpty(jdbcTemplate.queryForList(Test_RevenueTable_Exist,
					BeanParameterMapper.newSingleParameterMapper("tableName", tableName), String.class));
		} catch (Exception e) {
			log.error("Test RevenueTable Exist Error.", e);
			throw new DAOException("Test RevenueTable Exist Error.", e);
		}
	}

	@Override
	public void createTable(String tableName) throws DAOException {
		try {
			jdbcTemplate.update(String.format(Create_Table_SQL_Template, tableName), new HashMap<String, Object>());
		} catch (DataAccessException e) {
			log.error("Create RevenueTable Error.", e);
			throw new DAOException("Create RevenueTable Error.", e);
		}
	}

	@Override
	public String getTableName(int nextMonth) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, nextMonth);
		return BASE_TABLE_NAME + CalendarUtil.formatDate(calendar.getTime(), CalendarUtil.DATE_YM_FORMAT);
	}
}
