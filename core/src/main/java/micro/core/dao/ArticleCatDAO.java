package micro.core.dao;

import java.util.List;

import micro.core.dataobject.ArticleCatDO;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年11月12日 下午11:23:17
 */
public interface ArticleCatDAO {
	
	ArticleCatDO getArticleCat(long catId) throws DAOException;

	void insert(ArticleCatDO category) throws DAOException;
	
	void delete(long catId) throws DAOException;
	
	void update(ArticleCatDO category) throws DAOException;
	
	List<ArticleCatDO> selectAll(boolean simple) throws DAOException;
}