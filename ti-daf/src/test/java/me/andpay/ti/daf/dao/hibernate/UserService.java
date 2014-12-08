package me.andpay.ti.daf.dao.hibernate;

import me.andpay.ti.cache.annotation.Cache;
import me.andpay.ti.lnk.annotaion.LnkService;

/**
 * 用户服务接口定义类。
 * 
 * @author sea.bao
 */
@LnkService(serviceGroup="ti-daf.test-srv")
public interface UserService {
	/**
	 * 获得用户
	 * @param id
	 * @return
	 */
	@Cache(keys = "id=args[0]")
	User getUser(long id);
	
	/**
	 * 增加用户
	 * @param user
	 */
	void addUser(User user);
}
