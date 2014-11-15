package micro.core.dao.impl;

import micro.core.dao.BaseDAO;
import micro.core.dao.DAOException;
import micro.core.dao.AdminDAO;
import micro.core.dao.statement.AdminMapper;
import micro.core.dataobject.AdminDO;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import tulip.data.jdbc.mapper.BeanParameterMapper;
import tulip.data.jdbc.mapper.BeanRowMapper;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月5日 上午10:59:30
 */
@Repository("adminDAO")
public class DefaultAdminDAO extends BaseDAO implements AdminMapper, AdminDAO {

	@Override
	public void insertAdmin(AdminDO admin) throws DAOException {
		try {
			KeyHolder holder = new GeneratedKeyHolder();
			jdbcTemplate.update(ADD_SQL, BeanParameterMapper.newInstance(admin), holder, new String[]{ "id" });
			Number id = holder.getKey();
			admin.setId(id.longValue());
		} catch (DataAccessException e) {
			log.error("InsertAdmin Error.", e);
			throw new DAOException("InsertAdmin Error.", e);
		}
	}

	@Override
	public AdminDO selectAdmin(String name) throws DAOException {
		try {
			return jdbcTemplate.queryForObject(SELECT_BYNAME_SQL, 
					BeanParameterMapper.newSingleParameterMapper("name", name), BeanRowMapper.newInstance(AdminDO.class));
		} catch (DataAccessException e) {
			log.error("SelectAdmin Error.", e);
			throw new DAOException("SelectAdmin Error.", e);
		}
	}
}