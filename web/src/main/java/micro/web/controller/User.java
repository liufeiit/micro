package micro.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年10月29日 下午10:52:05
 */
@Controller
public class User extends BaseController {
	
	@RequestMapping(value = "/profile.htm")
	public ModelAndView profile(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("profile");
		return mv;
	}

	@RequestMapping(value = "/study.htm")
	public ModelAndView study(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("study");
		return mv;
	}
}