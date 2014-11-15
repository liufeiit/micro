package micro.core.dao;

import micro.core.dataobject.AdminDO;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月5日 上午10:56:59
 */
public interface AdminDAO {

	void insertAdmin(AdminDO admin) throws DAOException;
	
	AdminDO selectAdmin(String name) throws DAOException;
}