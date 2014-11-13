package micro.core.service;

import micro.core.dataobject.ArticleDO;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年11月13日 上午10:32:45
 */
public interface ArticleService {
	
	Result getArticle(long id);
	
	Result updateArticle(ArticleDO article);
	
	Result addArticle(ArticleDO article);
}