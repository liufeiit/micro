package micro.web.controller;

import javax.servlet.http.HttpServletRequest;

import micro.web.WebBase;

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
		return mv;
	}
	
	@RequestMapping(value = "/revenue_detail.htm")
	public ModelAndView revenue_detail(HttpServletRequest request) {
		ModelAndView mv = returnView(request, "revenue_detail", "收入", "会员收入");
		return mv;
	}
}