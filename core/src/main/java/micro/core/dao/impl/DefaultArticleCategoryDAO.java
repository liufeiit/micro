package micro.core.dao.impl;

import java.util.List;

import micro.core.dao.ArticleCategoryDAO;
import micro.core.dao.BaseDAO;
import micro.core.dao.DAOException;
import micro.core.dao.statement.ArticleCategoryMapper;
import micro.core.dataobject.ArticleCategoryDO;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tulip.data.jdbc.mapper.BeanParameterMapper;
import tulip.data.jdbc.mapper.BeanRowMapper;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年11月12日 下午11:24:55
 */
public class DefaultArticleCategoryDAO extends BaseDAO implements ArticleCategoryDAO, ArticleCategoryMapper {

	@Override
	public void insert(ArticleCategoryDO category) throws DAOException {
		try {
			KeyHolder holder = new GeneratedKeyHolder();
			jdbcTemplate.update(INSERT_SQL, BeanParameterMapper.newInstance(category), holder, new String[]{ "content_category_id" });
			Number id = holder.getKey();
			category.setId(id.longValue());
		} catch (DataAccessException e) {
			log.error("insert Error.", e);
			throw new DAOException("insert Error.", e);
		}
	}

	@Override
	public void delete(long catId) throws DAOException {
		try {
			jdbcTemplate.update(DELETE_SQL, BeanParameterMapper.newSingleParameterMapper("content_category_id", catId));
		} catch (DataAccessException e) {
			log.error("delete Error.", e);
			throw new DAOException("delete Error.", e);
		}
	}

	@Override
	public void update(ArticleCategoryDO category) throws DAOException {
		try {
			jdbcTemplate.update(UPDATE_SQL, BeanParameterMapper.newInstance(category));
		} catch (DataAccessException e) {
			log.error("update Error.", e);
			throw new DAOException("update Error.", e);
		}
	}

	@Override
	public List<ArticleCategoryDO> selectAll() throws DAOException {
		try {
			return jdbcTemplate.query(SELECT_ALL, BeanRowMapper.newInstance(ArticleCategoryDO.class));
		} catch (DataAccessException e) {
			log.error("SelectAll Error.", e);
			throw new DAOException("SelectAll Error.", e);
		}
	}
}