package me.andpay.ti.daf.dao.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户服务实现类
 * 
 * @author sea.bao
 */
public class UserServiceImpl implements UserService {
	private UserDao userDao;
	
	@Transactional
	public User getUser(long id) {
		return userDao.get(id);
	}

	@Transactional
	public void addUser(User user) {
		userDao.add(user);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
