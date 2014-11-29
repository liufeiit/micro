package micro.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import tulip.util.CollectionUtil;
import micro.core.dao.DAOException;
import micro.core.dataobject.UserDO;
import micro.core.service.BaseService;
import micro.core.service.PageQuery;
import micro.core.service.Result;
import micro.core.service.RevenueService;
import micro.core.util.ResultCode;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年11月17日 下午12:50:53
 */
@Service("revenueService")
public class DefaultRevenueService extends BaseService implements RevenueService {

	@Override
	public Result revenue(long userId, double income) {
		try {
			boolean ret = revenueDAO.revenue(userId, income);
			if(ret) {
				return Result.newSuccess().with(ResultCode.Revenue_Success);
			}
		} catch (DAOException e) {
			log.error("Revenue Error.", e);
			return Result.newError().with(ResultCode.Revenue_Exception);
		}
		return Result.newSuccess().with(ResultCode.Revenue_Error);
	}
	
	@Override
	public Result queryUser(long userId, boolean withIncome) {
		try {
			UserDO user = revenueDAO.queryUser(userId);
			if(user != null) {
				if(withIncome) {
					user.setCurrentIncome(revenueDAO.sumIncome(0, userId));
					user.setLastMonthIncome(revenueDAO.queryIncome(-1, userId));
				}
				return Result.newError().with(ResultCode.Success).with("user", user);
			}
		} catch (DAOException e) {
			log.error("Query User Error.", e);
		}
		return Result.newError().with(ResultCode.Error_Query);
	}
	
	@Override
	public Result query(long userId, PageQuery query, boolean withIncome) {
		List<UserDO> userList = new ArrayList<UserDO>();
		if(userId > 0L) {
			try {
				UserDO user = revenueDAO.queryUser(userId);
				if(user != null) {
					if(withIncome) {
						user.setCurrentIncome(revenueDAO.sumIncome(0, userId));
						user.setLastMonthIncome(revenueDAO.queryIncome(-1, userId));
					}
					userList.add(user);
				}
			} catch (DAOException e) {
				log.error("Query User Error.", e);
				return Result.newError().with(ResultCode.Error_Query);
			}
		} else {
			try {
				List<UserDO> users = revenueDAO.queryUser(query);
				if(!CollectionUtil.isEmpty(users)) {
					if(withIncome) {
						for (UserDO user : users) {
							user.setCurrentIncome(revenueDAO.sumIncome(0, userId));
							user.setLastMonthIncome(revenueDAO.queryIncome(-1, userId));
						}
					}
					userList.addAll(users);
				}
			} catch (DAOException e) {
				log.error("Query User Error.", e);
				return Result.newError().with(ResultCode.Error_Query);
			}
		}
		if(CollectionUtil.isEmpty(userList)) {
			return Result.newError().with(ResultCode.Error_Query);
		}
		return Result.newSuccess().with(ResultCode.Success).with("userList", userList);
	}
}