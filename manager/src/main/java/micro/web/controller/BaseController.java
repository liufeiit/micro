package micro.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import micro.core.dataobject.UserDO;
import micro.core.service.ArticleCatService;
import micro.core.service.ArticleService;
import micro.core.service.UserService;
import micro.web.handler.SessionManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月25日 下午1:55:47
 */
public class BaseController {

	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	@Qualifier(value = "userService")
	protected UserService userService;
	@Autowired
	@Qualifier(value = "articleService")
	protected ArticleService articleService;
	@Autowired
	@Qualifier(value = "articleCatService")
	protected ArticleCatService articleCatService;
	
	protected ModelAndView post(String action, Map<String, Object> data, String title) {
		return post(action, data, false, null, title);
	}
	
	protected ModelAndView post(String action, Map<String, Object> data, boolean alert, String alertMsg, String title) {
		ModelAndView mv = new ModelAndView("post");
		mv.addObject("data", data);
		mv.addObject("action", action);
		mv.addObject("title", title);
		if(alert) {
			mv.addObject("alert", alert);
			mv.addObject("alertMsg", alertMsg);
		}
		return mv;
	}
	
	protected ModelAndView returnView(HttpServletRequest request, String name, String nav, String nav_desc) {
		ModelAndView mv = new ModelAndView(name);
		UserDO user = getLoginUser(request);
		mv.addObject("name", user.getName());
		mv.addObject("uid", user.getId());
		mv.addObject("nav", nav);
		mv.addObject("nav_desc", nav_desc);
		return mv;
	}
	
	protected void userLogin(HttpServletRequest request, UserDO user) {
		SessionManager.login(request.getSession(true), user);
	}
	
	protected UserDO getLoginUser(HttpServletRequest request) {
		return SessionManager.getUser(request.getSession(true));
	}
	
	protected long getUserId(HttpServletRequest request) {
		UserDO user = getLoginUser(request);
		if(user == null) {
			return -1L;
		}
		return user.getId();
	}
}