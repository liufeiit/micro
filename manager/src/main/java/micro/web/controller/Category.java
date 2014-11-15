package micro.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import micro.core.dataobject.ArticleCatDO;
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
 * @since 2014年11月13日 下午10:46:35
 */
@Controller
public class Category extends WebBase {

	@RequestMapping(value = "/cat_create_page.htm")
	public ModelAndView article_create_page(HttpServletRequest request) {
		ModelAndView mv = returnView(request, "cat_create", "文章类型", "创建文章类型");
		return mv;
	}
	
	@RequestMapping(value = "/cat_create.htm")
	public ModelAndView article_create(HttpServletRequest request) {
		ArticleCatDO cat = new ArticleCatDO();
		cat.setDescription(request.getParameter("description"));
		cat.setIcon(request.getParameter("icon"));
		cat.setName(request.getParameter("name"));
		cat.setPosition(NumberUtils.toInt(request.getParameter("position"), 0));
		Result result = articleCatService.addArticle(cat);
		Map<String, Object> data = new HashMap<String, Object>();
		if (result.isSuccess()) {
			return post("cat_list.htm", data, "创建中...");
		}
		return post("cat_create_page.htm", data, "创建中...");
	}
	
	@RequestMapping(value = "/cat_list.htm")
	public ModelAndView article_detail(HttpServletRequest request) {
		ModelAndView mv = returnView(request, "cat_list", "文章类型", "文章类型列表");
		Result result = articleCatService.getAllArticleCat(false);
		mv.addObject("hasCat", result.isSuccess());
		mv.addObject("catList", result.get("catList"));
		return mv;
	}
}