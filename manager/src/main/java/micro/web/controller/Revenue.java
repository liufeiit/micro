package micro.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import micro.core.dataobject.UserDO;
import micro.core.service.PageQuery;
import micro.core.service.Result;
import micro.web.WebBase;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年11月15日 下午9:37:55
 */
@SuppressWarnings("unchecked")
@Controller
public class Revenue extends WebBase {
	
	@RequestMapping(value = "/revenue_list.htm")
	public ModelAndView revenue_list(HttpServletRequest request) {
		ModelAndView mv = returnView(request, "revenue_list", "收入", "收入列表");
		long userId = NumberUtils.toLong(request.getParameter("uid"), 0L);
		mv.addObject("selected_uid", userId > 0L ? String.valueOf(userId) : "");
		int page = NumberUtils.toInt(request.getParameter("page"), 1);
		Result result = revenueService.query(userId, new PageQuery(page), false);
		boolean success = result.isSuccess();
		if(page > 1) {
			mv.addObject("prePage", page - 1);
			mv.addObject("currentPage", page);
			mv.addObject("nextPage", success ? (page + 1) : (page));
		} else {
			mv.addObject("prePage", 1);
			mv.addObject("currentPage", 1);
			mv.addObject("nextPage", 2);
		}
		mv.addObject("hasRevenue", success);
		mv.addObject("revenueList", result.get("userList"));
		return mv;
	}
	
	@RequestMapping(value = "/revenue_detail.htm")
	public ModelAndView revenue_detail(HttpServletRequest request) {
		ModelAndView mv = returnView(request, "revenue_detail", "收入", "会员收入");
		long userId = NumberUtils.toLong(request.getParameter("uid"), 0L);
		if(userId <= 0L) {
			mv.addObject("hasUser", false);
			return mv;
		}
		Result result = revenueService.queryUser(userId, true);
		mv.addObject("hasUser", result.isSuccess());
		mv.addObject("user", result.get("user"));
		return mv;
	}
	
	@RequestMapping(value = "/revenue_user.htm")
	public ModelAndView revenue_user(HttpServletRequest request) {
		ModelAndView mv = returnView(request, "revenue_user", "转账", "会员转账");
		long userId = NumberUtils.toLong(request.getParameter("uid"), 0L);
		if(userId <= 0L) {
			mv.addObject("hasUser", false);
			return mv;
		}
		Result result = revenueService.queryUser(userId, false);
		mv.addObject("hasUser", result.isSuccess());
		mv.addObject("user", result.get("user"));
		return mv;
	}
	
	@RequestMapping(value = "/revenue.htm")
	public ModelAndView revenue(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
		long userId = NumberUtils.toLong(request.getParameter("uid"), 0L);
		if(userId <= 0L) {
			data.put("uid", userId);
			return post("revenue_user.htm", data, true, "请输入用户ID！", "处理中...");
		}
		Result result = revenueService.queryUser(userId, false);
		if(result.isSuccess()) {
			UserDO user = (UserDO) result.get("user");
			if(user == null) {
				data.put("uid", userId);
				return post("revenue_user.htm", data, true, "用户信息查询失败！", "处理中...");
			}
			double income = NumberUtils.toDouble(request.getParameter("income"), 0.0D);
			if(income <= 0.0D || (user.getAccountBalance() - income < 0.0D)) {
				data.put("uid", userId);
				return post("revenue_user.htm", data, true, "请输入有效金额！", "处理中...");
			}
			result = revenueService.revenue(userId, income);
			data.put("uid", userId);
			return post("revenue_user.htm", data, true, result.getMessage(), "处理中...");
		}
		data.put("uid", userId);
		return post("revenue_user.htm", data, true, "转账失败！", "处理中...");
	}
}