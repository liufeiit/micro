package micro.core.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import micro.core.dao.ArticleDAO;
import micro.core.dao.BaseDAO;
import micro.core.dao.DAOException;
import micro.core.dao.statement.ArticleMapper;
import micro.core.dataobject.ArticleDO;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import tulip.data.jdbc.mapper.BeanParameterMapper;
import tulip.data.jdbc.mapper.BeanRowMapper;
import tulip.util.StringUtil;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年11月12日 下午11:50:55
 */
@Repository("articleDAO")
public class DefaultArticleDAO extends BaseDAO implements ArticleDAO, ArticleMapper {

	@Override
	public void insert(ArticleDO article) throws DAOException {
		try {
			//1.保存文章信息
			KeyHolder h1 = new GeneratedKeyHolder();
			jdbcTemplate.update(INSERT_SQL, BeanParameterMapper.newInstance(article), h1,
					new String[] { "content_id" });
			Number id = h1.getKey();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", id);
			paramMap.put("content", article.getContent());
			//2.保存文章内容
			jdbcTemplate.update(INSERT_CONTENT, paramMap);
			article.setId(id.longValue());
			
			if(StringUtil.isNotBlank(article.getCover())) {
				KeyHolder h2 = new GeneratedKeyHolder();
				//3.保存文章封面内容
				jdbcTemplate.update(COVER_MEDIA_INSERT, BeanParameterMapper.newInstance(article), h2,
						new String[] { "media_id" });
				Number media_id = h2.getKey();
				paramMap.put("content_id", article.getId());
				paramMap.put("media_id", media_id.longValue());
				//4.保存文章与封面的关联信息
				jdbcTemplate.update(CONTENT_MEDIA_INSERT, paramMap);
			}
			
		} catch (DataAccessException e) {
			log.error("Insert Error.", e);
			throw new DAOException("Insert Error.", e);
		}
	}

	@Override
	public void delete(long id) throws DAOException {
		try {
			jdbcTemplate.update(DELETE_SQL, BeanParameterMapper.newSingleParameterMapper("content_id", id));
			jdbcTemplate.update(DELETE_CONTENT, BeanParameterMapper.newSingleParameterMapper("id", id));
			
		} catch (DataAccessException e) {
			log.error("Delete Error.", e);
			throw new DAOException("Delete Error.", e);
		}
	}

	@Override
	public void update(ArticleDO article) throws DAOException {
		try {
			jdbcTemplate.update(UPDATE_SQL, BeanParameterMapper.newInstance(article));
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", article.getId());
			paramMap.put("content", article.getContent());
			jdbcTemplate.update(UPDATE_CONTENT, paramMap);
			if(StringUtil.isBlank(article.getCover())) {
				return;
			}
			String oldCover = jdbcTemplate.queryForObject(Query_Cover,
					BeanParameterMapper.newSingleParameterMapper("content_id", article.getId()), String.class);
			if(StringUtil.isNotBlank(oldCover)) {
				paramMap.put("url", article.getCover());
				paramMap.put("content_id", article.getId());
				jdbcTemplate.update(UPDATE_COVER, paramMap);
				return;
			}
			KeyHolder h2 = new GeneratedKeyHolder();
			//保存文章封面内容
			jdbcTemplate.update(COVER_MEDIA_INSERT, BeanParameterMapper.newInstance(article), h2,
					new String[] { "media_id" });
			Number media_id = h2.getKey();
			paramMap.put("content_id", article.getId());
			paramMap.put("media_id", media_id.longValue());
			//保存文章与封面的关联信息
			jdbcTemplate.update(CONTENT_MEDIA_INSERT, paramMap);
		} catch (DataAccessException e) {
			log.error("Update Error.", e);
			throw new DAOException("Update Error.", e);
		}
	}

	@Override
	public List<ArticleDO> query(long catId, String type, String status, String title, int index, int size)
			throws DAOException {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("content_category_id", catId);
			paramMap.put("type", LIKE_SIG + type + LIKE_SIG);
			paramMap.put("status", LIKE_SIG + status + LIKE_SIG);
			paramMap.put("title", LIKE_SIG + title + LIKE_SIG);
			paramMap.put("start", index);
			paramMap.put("size", size);
			return jdbcTemplate.query(catId > 0L ? QUERY_CAT_BY_CATID_SQL : QUERY_CAT_SQL,
					BeanParameterMapper.newMapParameterMapper(paramMap), BeanRowMapper.newInstance(ArticleDO.class));
		} catch (DataAccessException e) {
			log.error("Select Article By Cat Error.", e);
			throw new DAOException("Select Article By Cat Error.", e);
		}
	}

	@Override
	public ArticleDO selectArticle(long id, boolean withContent) throws DAOException {
		try {
			ArticleDO article = jdbcTemplate.queryForObject(SELECT_ONE_SQL,
					BeanParameterMapper.newSingleParameterMapper("content_id", id),
					BeanRowMapper.newInstance(ArticleDO.class));
			if (article != null && withContent) {
				// 后面可以考虑使用key-value缓存
				article.setContent(jdbcTemplate.queryForObject(QUERY_CONTENT,
						BeanParameterMapper.newSingleParameterMapper("id", id), String.class));
				article.setCover(jdbcTemplate.queryForObject(Query_Cover,
						BeanParameterMapper.newSingleParameterMapper("content_id", id), String.class));
			}
			return article;
		} catch (DataAccessException e) {
			log.error("Select Article Error.", e);
			throw new DAOException("Select Article Error.", e);
		}
	}
}
