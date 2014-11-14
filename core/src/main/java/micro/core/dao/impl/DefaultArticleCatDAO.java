package micro.core.dao.impl;

import java.util.List;

import micro.core.dao.ArticleCatDAO;
import micro.core.dao.BaseDAO;
import micro.core.dao.DAOException;
import micro.core.dao.statement.ArticleCatMapper;
import micro.core.dataobject.ArticleCatDO;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import tulip.data.jdbc.mapper.BeanParameterMapper;
import tulip.data.jdbc.mapper.BeanRowMapper;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年11月12日 下午11:24:55
 */
@Repository("articleCatDAO")
public class DefaultArticleCatDAO extends BaseDAO implements ArticleCatDAO, ArticleCatMapper {

	@Override
	public void insert(ArticleCatDO category) throws DAOException {
		try {
			KeyHolder holder = new GeneratedKeyHolder();
			jdbcTemplate.update(INSERT_SQL, BeanParameterMapper.newInstance(category), holder, new String[]{ "content_category_id" });
			Number id = holder.getKey();
			category.setId(id.longValue());
		} catch (DataAccessException e) {
			log.error("Insert Error.", e);
			throw new DAOException("Insert Error.", e);
		}
	}

	@Override
	public void delete(long catId) throws DAOException {
		try {
			jdbcTemplate.update(DELETE_SQL, BeanParameterMapper.newSingleParameterMapper("content_category_id", catId));
		} catch (DataAccessException e) {
			log.error("Delete Error.", e);
			throw new DAOException("Delete Error.", e);
		}
	}

	@Override
	public void update(ArticleCatDO category) throws DAOException {
		try {
			jdbcTemplate.update(UPDATE_SQL, BeanParameterMapper.newInstance(category));
		} catch (DataAccessException e) {
			log.error("Update Error.", e);
			throw new DAOException("Update Error.", e);
		}
	}

	@Override
	public List<ArticleCatDO> selectAll(boolean simple) throws DAOException {
		try {
			return jdbcTemplate.query(simple ? SELECT_SIMPLE_ALL : SELECT_ALL, BeanRowMapper.newInstance(ArticleCatDO.class));
		} catch (DataAccessException e) {
			log.error("SelectAll Error.", e);
			throw new DAOException("SelectAll Error.", e);
		}
	}

	@Override
	public ArticleCatDO getArticleCat(long catId) throws DAOException {
		try {
			return jdbcTemplate.queryForObject(SELECT_ONE_SQL, 
					BeanParameterMapper.newSingleParameterMapper("content_category_id", catId), BeanRowMapper.newInstance(ArticleCatDO.class));
		} catch (DataAccessException e) {
			log.error("Select ArticleCat Error.", e);
			throw new DAOException("Select ArticleCat Error.", e);
		}
	}
}