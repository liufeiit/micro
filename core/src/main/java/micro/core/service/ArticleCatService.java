package micro.core.service;

import micro.core.dataobject.ArticleCatDO;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年11月13日 上午10:36:07
 */
public interface ArticleCatService {
	
	Result getArticleCat(long id);
	
	Result getAllArticleCat(boolean simple);
	
	Result updateArticle(ArticleCatDO articleCat);

	Result addArticle(ArticleCatDO articleCat);
}