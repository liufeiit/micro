package micro.web.controller;

import javax.servlet.http.HttpServletRequest;

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
@Controller
public class Revenue extends WebBase {
	
	@RequestMapping(value = "/revenue_list.htm")
	public ModelAndView revenue_list(HttpServletRequest request) {
		ModelAndView mv = returnView(request, "revenue_list", "收入", "收入列表");
		long userId = NumberUtils.toLong(request.getParameter("uid"), 0L);
		int page = NumberUtils.toInt(request.getParameter("page"), 1);
		Result result = revenueService.query(userId, new PageQuery(page), false);
		mv.addObject("hasUser", result.isSuccess());
		mv.addObject("userList", result.get("userList"));
		return mv;
	}
	
	@RequestMapping(value = "/revenue_detail.htm")
	public ModelAndView revenue_detail(HttpServletRequest request) {
		ModelAndView mv = returnView(request, "revenue_detail", "收入", "会员收入");
		long userId = NumberUtils.toLong(request.getParameter("uid"), 0L);
		Result result = revenueService.query(userId, new PageQuery(1), true);
		mv.addObject("hasUser", result.isSuccess());
		mv.addObject("userList", result.get("userList"));
		return mv;
	}
}