package micro.core.dao;

import java.util.List;

import micro.core.dataobject.ArticleCategoryDO;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年11月12日 下午11:23:17
 */
public interface ArticleCategoryDAO {

	void insert(ArticleCategoryDO category) throws DAOException;
	
	void delete(long catId) throws DAOException;
	
	void update(ArticleCategoryDO category) throws DAOException;
	
	List<ArticleCategoryDO> selectAll() throws DAOException;
}