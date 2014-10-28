package micro.core.service.impl;

import java.util.Date;

import micro.core.dao.DAOException;
import micro.core.dataobject.UserDO;
import micro.core.service.BaseService;
import micro.core.service.Result;
import micro.core.service.UserService;
import micro.core.util.ErrorCode;
import micro.core.util.PasswdUtil;

import org.springframework.stereotype.Service;

import tulip.util.StringUtil;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月5日 上午11:10:30
 */
@Service("userService")
public class DefaultUserService extends BaseService implements UserService {

	@Override
	public Result createUser(String name, String email, String mobile, String weixin, String password) {
		UserDO user = new UserDO();
		user.setName(name);
		user.setEmail(email);
		user.setMobile(mobile);
		user.setWeixin(weixin);
		user.setPassword(PasswdUtil.signPwsswd(password));
		Date date = new Date();
		user.setGmt_created(date);
		user.setGmt_modified(date);
		try {
			userDAO.insertUser(user);
		} catch (DAOException e) {
			log.error("CreateUser Error.", e);
			return Result.newError().with(ErrorCode.Error_CreateUser);
		}
		return Result.newSuccess().with(ErrorCode.Success).with("user", user);
	}
	
	public static void main(String[] args) {
		System.out.println(PasswdUtil.signPwsswd("Ruoogle90an"));
	}

	@Override
	public Result login(String name, String passwd) {
		try {
			if(StringUtil.isBlank(name)) {
				return Result.newError().with(ErrorCode.Error_InputUsername);
			}
			if(StringUtil.isBlank(passwd)) {
				return Result.newError().with(ErrorCode.Error_InputPasswd);
			}
			UserDO user = userDAO.selectUser(name);
			if(user == null) {
				return Result.newError().with(ErrorCode.Error_NonUser);
			}
			if(!PasswdUtil.signPwsswd(passwd).equals(user.getPassword())) {
				return Result.newError().with(ErrorCode.Error_ErrPasswd);
			}
			user.setPassword("");
			return Result.newSuccess().with(ErrorCode.Success).withUser(user);
		} catch (DAOException e) {
			log.error("Login Error.", e);
		}
		return Result.newError().with(ErrorCode.Error_UserLogin);
	}
}