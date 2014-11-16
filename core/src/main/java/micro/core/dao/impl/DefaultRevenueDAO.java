package micro.core.dao.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import micro.core.dao.BaseDAO;
import micro.core.dao.DAOException;
import micro.core.dao.RevenueDAO;
import micro.core.dao.statement.RevenueMapper;
import micro.core.util.CalendarUtil;
import micro.core.util.Static;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import tulip.data.jdbc.mapper.BeanParameterMapper;
import tulip.util.CollectionUtil;

/**
 * user_referee表中referee_id推荐user_id
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年11月16日 下午9:39:33
 */
@Repository("revenueDAO")
public class DefaultRevenueDAO extends BaseDAO implements RevenueMapper, RevenueDAO {

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
			return jdbcTemplate.queryForObject(String.format(COUNT_USER_IP_SQL_Template, getTableName(nextMonth)),
					BeanParameterMapper.newSingleParameterMapper("userId", userId), long.class);
		} catch (DataAccessException e) {
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
			return jdbcTemplate.queryForObject(String.format(COUNT_USER_PV_SQL_Template, getTableName(nextMonth)),
					BeanParameterMapper.newSingleParameterMapper("userId", userId), long.class);
		} catch (DataAccessException e) {
			log.error("Count IP Error.", e);
		}
		return 0L;
	}

	/**
	 * 文章的IP总数
	 */
	@Override
	public long countArticleIP(int nextMonth, long articleId) {
		try {
			return jdbcTemplate.queryForObject(String.format(COUNT_ARTICLE_IP_SQL_Template, getTableName(nextMonth)),
					BeanParameterMapper.newSingleParameterMapper("content_id", articleId), long.class);
		} catch (DataAccessException e) {
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
			return jdbcTemplate.queryForObject(String.format(COUNT_ARTICLE_PV_SQL_Template, getTableName(nextMonth)),
					BeanParameterMapper.newSingleParameterMapper("content_id", articleId), long.class);
		} catch (DataAccessException e) {
			log.error("Count IP Error.", e);
		}
		return 0L;
	}

	@Override
	public boolean isExistTable(String tableName) throws DAOException {
		try {
			return !CollectionUtil.isEmpty(jdbcTemplate.queryForList(Test_RevenueTable_Exist,
					BeanParameterMapper.newSingleParameterMapper("tableName", tableName), String.class));
		} catch (DataAccessException e) {
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
