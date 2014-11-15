package micro.web.controller;

import java.util.Map;

import micro.core.service.AdminService;

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
	protected AdminService userService;
	
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
}