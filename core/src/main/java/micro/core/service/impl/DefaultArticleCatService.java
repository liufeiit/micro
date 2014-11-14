package micro.core.service.impl;

import java.util.List;

import micro.core.dao.DAOException;
import micro.core.dataobject.ArticleCatDO;
import micro.core.service.ArticleCatService;
import micro.core.service.BaseService;
import micro.core.service.Result;
import micro.core.util.ResultCode;

import org.springframework.stereotype.Service;

import tulip.util.CollectionUtil;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年11月13日 上午10:38:58
 */
@Service("articleCatService")
public class DefaultArticleCatService extends BaseService implements ArticleCatService {

	@Override
	public Result getArticleCat(long id) {
		try {
			ArticleCatDO articleCat = articleCatDAO.getArticleCat(id);
			if(articleCat == null) {
				return Result.newError().with(ResultCode.Error_Query);
			}
			return Result.newSuccess().with(ResultCode.Success).with("articleCat", articleCat);
		} catch (DAOException e) {
			log.error("Query ArticleCat Error.", e);
		}
		return Result.newError().with(ResultCode.Error_Query);
	}

	@Override
	public Result getAllArticleCat(boolean simple) {
		try {
			List<ArticleCatDO> catList = articleCatDAO.selectAll(simple);
			if(CollectionUtil.isEmpty(catList)) {
				return Result.newError().with(ResultCode.Error_Query);
			}
			return Result.newSuccess().with(ResultCode.Success).with("catList", catList);
		} catch (DAOException e) {
			log.error("Query ArticleCat List Error.", e);
		}
		return Result.newError().with(ResultCode.Error_Query);
	}

	@Override
	public Result updateArticle(ArticleCatDO articleCat) {
		try {
			articleCatDAO.update(articleCat);
			return Result.newSuccess().with(ResultCode.Success);
		} catch (DAOException e) {
			log.error("Update Article Error.", e);
		}
		return Result.newError().with(ResultCode.Error_Update);
	}

	@Override
	public Result addArticle(ArticleCatDO articleCat) {
		try {
			articleCatDAO.insert(articleCat);
			return Result.newSuccess().with(ResultCode.Success);
		} catch (DAOException e) {
			log.error("Insert Article Error.", e);
		}
		return Result.newError().with(ResultCode.Error_Insert);
	}
}