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
		return Result.newSuccess().with(ErrorCode.Success);
	}
	
	public static void main(String[] args) {
		System.out.println(PasswdUtil.signPwsswd("Ruoogle90an"));
	}

	@Override
	public Result login(String name, String passwd) {
		return Result.newError().with(ErrorCode.Error_UserLogin);
	}
}