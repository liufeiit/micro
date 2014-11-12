package micro.core.dao.impl;

import micro.core.dao.BaseDAO;
import micro.core.dao.DAOException;
import micro.core.dao.UserDAO;
import micro.core.dao.statement.UserMapper;
import micro.core.dataobject.UserDO;

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
@Repository("userDAO")
public class DefaultUserDAO extends BaseDAO implements UserMapper, UserDAO {

	@Override
	public void insertUser(UserDO user) throws DAOException {
		try {
			KeyHolder holder = new GeneratedKeyHolder();
			jdbcTemplate.update(ADD_SQL, BeanParameterMapper.newInstance(user), holder, new String[]{ "id" });
			Number id = holder.getKey();
			user.setId(id.longValue());
		} catch (DataAccessException e) {
			log.error("InsertUser Error.", e);
			throw new DAOException("InsertUser Error.", e);
		}
	}

	@Override
	public UserDO selectUser(String name) throws DAOException {
		try {
			return jdbcTemplate.queryForObject(SELECT_BYNAME_SQL, 
					BeanParameterMapper.newSingleParameterMapper("name", name), BeanRowMapper.newInstance(UserDO.class));
		} catch (DataAccessException e) {
			log.error("SelectUser Error.", e);
			throw new DAOException("SelectUser Error.", e);
		}
	}
}