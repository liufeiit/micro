package me.andpay.ti.daf.dao.hibernate;

import me.andpay.ti.lnk.annotaion.Lnkwired;
import me.andpay.ti.lnk.annotaion.Local;

/**
 * 用户服务本地桥类。
 * 
 * @author sea.bao
 */
@Local
public class UserServiceLocalBridge implements UserService {
	private UserService userService;
	
	public User getUser(long id) {
		return userService.getUser(id);
	}

	public void addUser(User user) {
		userService.addUser(user);
	}

	public UserService getUserService() {
		return userService;
	}

	@Lnkwired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
