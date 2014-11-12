package micro.core.dao.impl;

import java.util.List;

import micro.core.dao.ArticleDAO;
import micro.core.dao.BaseDAO;
import micro.core.dao.DAOException;
import micro.core.dao.statement.ArticleMapper;
import micro.core.dataobject.ArticleDO;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tulip.data.jdbc.mapper.BeanParameterMapper;
import tulip.data.jdbc.mapper.BeanRowMapper;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年11月12日 下午11:50:55
 */
public class DefaultArticleDAO extends BaseDAO implements ArticleDAO, ArticleMapper {

	@Override
	public void insert(ArticleDO article) throws DAOException {
		try {
			KeyHolder holder = new GeneratedKeyHolder();
			jdbcTemplate.update(INSERT_SQL, BeanParameterMapper.newInstance(article), holder, new String[]{ "content_id" });
			Number id = holder.getKey();
			article.setId(id.longValue());
		} catch (DataAccessException e) {
			log.error("insert Error.", e);
			throw new DAOException("insert Error.", e);
		}
	}

	@Override
	public void delete(long id) throws DAOException {
		try {
			jdbcTemplate.update(DELETE_SQL, BeanParameterMapper.newSingleParameterMapper("content_id", id));
		} catch (DataAccessException e) {
			log.error("delete Error.", e);
			throw new DAOException("delete Error.", e);
		}
	}

	@Override
	public void update(ArticleDO article) throws DAOException {
		try {
			KeyHolder holder = new GeneratedKeyHolder();
			jdbcTemplate.update(UPDATE_SQL, BeanParameterMapper.newInstance(article), holder, new String[]{ "content_id" });
			Number id = holder.getKey();
			article.setId(id.longValue());
		} catch (DataAccessException e) {
			log.error("update Error.", e);
			throw new DAOException("update Error.", e);
		}
	}

	@Override
	public List<ArticleDO> selectCat(long catId) throws DAOException {
		try {
			return jdbcTemplate.query(SELECT_CAT_SQL, BeanParameterMapper.newSingleParameterMapper("content_category_id", catId), 
					BeanRowMapper.newInstance(ArticleDO.class));
		} catch (DataAccessException e) {
			log.error("SelectYearStats Error.", e);
			throw new DAOException("SelectYearStats Error.", e);
		}
	}

	@Override
	public ArticleDO selectArticle(long id) throws DAOException {
		try {
			return jdbcTemplate.queryForObject(SELECT_ONE_SQL, 
					BeanParameterMapper.newSingleParameterMapper("content_id", id), BeanRowMapper.newInstance(ArticleDO.class));
		} catch (DataAccessException e) {
			log.error("SelectEvent Error.", e);
			throw new DAOException("SelectEvent Error.", e);
		}
	}
}
