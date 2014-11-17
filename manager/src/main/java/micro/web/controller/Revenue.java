package micro.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import micro.core.dataobject.UserDO;
import micro.core.service.PageQuery;
import micro.core.service.Result;
import micro.web.WebBase;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tulip.util.CollectionUtil;

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
		mv.addObject("selected_uid", userId);
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
		Result result = revenueService.query(userId, new PageQuery(1), true);
		boolean success = result.isSuccess();
		mv.addObject("hasUser", success);
		if(success) {
			List<UserDO> userList = (List<UserDO>) result.get("userList");
			if(!CollectionUtil.isEmpty(userList)) {
				mv.addObject("user", userList.get(0));
			}
		}
		return mv;
	}
	
	@RequestMapping(value = "/revenue_user.htm")
	public ModelAndView revenue_user(HttpServletRequest request) {
		ModelAndView mv = returnView(request, "revenue_user", "转账", "会员转账");
		long userId = NumberUtils.toLong(request.getParameter("uid"), 0L);
		Result result = revenueService.query(userId, new PageQuery(1), false);
		boolean success = result.isSuccess();
		mv.addObject("hasUser", success);
		if(success) {
			List<UserDO> userList = (List<UserDO>) result.get("userList");
			if(!CollectionUtil.isEmpty(userList)) {
				mv.addObject("user", userList.get(0));
			}
		}
		return mv;
	}
}