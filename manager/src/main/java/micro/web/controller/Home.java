package micro.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import micro.core.dataobject.AdminDO;
import micro.core.service.Result;
import micro.web.WebBase;
import micro.web.handler.SessionManager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Web首页。
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2013年12月30日 下午10:26:04
 */
@Controller
public class Home extends WebBase {
	
	@RequestMapping(value = "/home.htm")
	public ModelAndView home(HttpServletRequest request) {
		ModelAndView mv = returnView(request, "home", "首页", "首页概况");
		Result result = articleCatService.getAllArticleCat(false);
		mv.addObject("hasCat", result.isSuccess());
		mv.addObject("catList", result.get("catList"));
		return mv;
	}
	
	@RequestMapping(value = "/index.htm")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("errorMsg", request.getParameter("errorMsg"));
		return mv;
	}
	
	@RequestMapping(value = "/logout.htm")
	public ModelAndView logout(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("login");
		SessionManager.logout(request.getSession(true));
		return mv;
	}
	
	@RequestMapping(value = "/invalidate.htm")
	public ModelAndView session_invalidate(HttpServletRequest request) {
		SessionManager.logout(request.getSession(true));
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("errorMsg", "用户登录会话失效");
		return mv;
	}
	
	@RequestMapping(value = "/login.htm")
	public ModelAndView login(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
		String name = request.getParameter("name");
		String passwd = request.getParameter("passwd");
		Result result = adminService.login(name, passwd);
		if(result.isSuccess()) {
			adminLogin(request, (AdminDO) result.get("admin"));
			return post("home.htm", data, "登录中...");
		}
		data.put("errorMsg", result.getMessage());
		return post("index.htm", data, true, result.getMessage(), "登录中...");
	}
}