package micro.core.service.impl;

import java.util.List;

import micro.core.dao.DAOException;
import micro.core.dataobject.ArticleDO;
import micro.core.service.ArticleService;
import micro.core.service.BaseService;
import micro.core.service.PageQuery;
import micro.core.service.Result;
import micro.core.util.ResultCode;

import org.springframework.stereotype.Service;

import tulip.util.CollectionUtil;
import tulip.util.StringUtil;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年11月13日 上午10:38:30
 */
@Service("articleService")
public class DefaultArticleService extends BaseService implements ArticleService {

	@Override
	public Result getArticle(long id) {
		try {
			ArticleDO article = articleDAO.selectArticle(id);
			if(article == null) {
				return Result.newError().with(ResultCode.Error_Query);
			}
			return Result.newSuccess().with(ResultCode.Success).with("article", article);
		} catch (DAOException e) {
			log.error("Query Article Error.", e);
		}
		return Result.newError().with(ResultCode.Error_Query);
	}

	@Override
	public Result updateArticle(ArticleDO article) {
		try {
			articleDAO.update(article);
			return Result.newSuccess().with(ResultCode.Success);
		} catch (DAOException e) {
			log.error("Update Article Error.", e);
		}
		return Result.newError().with(ResultCode.Error_Update);
	}

	@Override
	public Result addArticle(ArticleDO article) {
		try {
			articleDAO.insert(article);
			return Result.newSuccess().with(ResultCode.Success);
		} catch (DAOException e) {
			log.error("Insert Article Error.", e);
		}
		return Result.newError().with(ResultCode.Error_Insert);
	}

	@Override
	public Result query(long catId, String type, String status, String title, PageQuery pageQuery) {
		try {
			type = StringUtil.defaultIfBlank(type, StringUtil.EMPTY);
			status = StringUtil.defaultIfBlank(status, StringUtil.EMPTY);
			title = StringUtil.defaultIfBlank(title, StringUtil.EMPTY);
			List<ArticleDO> articleList = articleDAO.query(catId, type, status, title, pageQuery.getIndex(), pageQuery.getSize());
			if(CollectionUtil.isEmpty(articleList)) {
				return Result.newError().with(ResultCode.Error_Query);
			}
			return Result.newSuccess().with(ResultCode.Success).with("articleList", articleList);
		} catch (DAOException e) {
			log.error("Insert Article Error.", e);
		}
		return Result.newError().with(ResultCode.Error_Query);
	}
}