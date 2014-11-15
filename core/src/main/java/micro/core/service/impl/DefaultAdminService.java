package micro.core.service.impl;

import java.util.Date;

import micro.core.dao.DAOException;
import micro.core.dataobject.AdminDO;
import micro.core.service.BaseService;
import micro.core.service.Result;
import micro.core.service.AdminService;
import micro.core.util.ResultCode;
import micro.core.util.PasswdUtil;

import org.springframework.stereotype.Service;

import tulip.util.StringUtil;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月5日 上午11:10:30
 */
@Service("adminService")
public class DefaultAdminService extends BaseService implements AdminService {

	@Override
	public Result createAdmin(String name, String email, String mobile, String weixin, String password) {
		AdminDO admin = new AdminDO();
		admin.setName(name);
		admin.setEmail(email);
		admin.setMobile(mobile);
		admin.setWeixin(weixin);
		admin.setPassword(PasswdUtil.signPwsswd(password));
		Date date = new Date();
		admin.setCreated(date);
		admin.setUpdated(date);
		try {
			adminDAO.insertAdmin(admin);
		} catch (DAOException e) {
			log.error("CreateAdmin Error.", e);
			return Result.newError().with(ResultCode.Error_CreateUser);
		}
		return Result.newSuccess().with(ResultCode.Success).with("admin", admin);
	}
	
	public static void main(String[] args) {
		System.out.println(PasswdUtil.signPwsswd("Ruoogle90an"));
	}

	@Override
	public Result login(String name, String passwd) {
		try {
			if(StringUtil.isBlank(name)) {
				return Result.newError().with(ResultCode.Error_InputUsername);
			}
			if(StringUtil.isBlank(passwd)) {
				return Result.newError().with(ResultCode.Error_InputPasswd);
			}
			AdminDO admin = adminDAO.selectAdmin(name);
			if(admin == null) {
				return Result.newError().with(ResultCode.Error_NonUser);
			}
			if(!PasswdUtil.signPwsswd(passwd).equals(admin.getPassword())) {
				return Result.newError().with(ResultCode.Error_ErrPasswd);
			}
			admin.setPassword("");
			return Result.newSuccess().with(ResultCode.Success).with("admin", admin);
		} catch (DAOException e) {
			log.error("Login Error.", e);
		}
		return Result.newError().with(ResultCode.Error_UserLogin);
	}
}