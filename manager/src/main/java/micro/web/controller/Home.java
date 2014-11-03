package micro.web.controller;

import javax.servlet.http.HttpServletRequest;

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
public class Home extends BaseController {
	
	@RequestMapping(value = "/home.htm")
	public ModelAndView home(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("home");
		return mv;
	}
	
	@RequestMapping(value = "/create_story.htm")
	public ModelAndView create_story(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("create_story");
		return mv;
	}
}