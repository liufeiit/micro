package micro.core.dao;

import java.util.List;

import micro.core.dataobject.ArticleDO;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年11月12日 上午11:41:37
 */
public interface ArticleDAO {
	
	void insert(ArticleDO article) throws DAOException;

	void delete(long id) throws DAOException;

	void update(ArticleDO article) throws DAOException;

	List<ArticleDO> selectCat(long catId, int index, int size) throws DAOException;
	
	ArticleDO selectArticle(long id) throws DAOException;
}