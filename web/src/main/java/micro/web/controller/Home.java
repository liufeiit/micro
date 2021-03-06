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
 * @since 2014年10月29日 下午10:52:48
 */
@Controller
public class Home extends BaseController {
	
	@RequestMapping(value = "/home.htm")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("home");
		return mv;
	}
}