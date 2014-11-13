package micro.web.controller;

import javax.servlet.http.HttpServletRequest;

import micro.core.dataobject.ArticleDO;
import micro.core.service.Result;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年11月13日 下午1:22:32
 */
@Controller
public class Article extends BaseController {
	
	@RequestMapping(value = "/article_detail.htm")
	public ModelAndView article_detail(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("article_detail");
		Result result =  articleCatService.getAllArticleCat();
		mv.addObject("hasCat", result.isSuccess());
		mv.addObject("catList", result.get("catList"));
		return mv;
	}
	
	@RequestMapping(value = "/article_create_page.htm")
	public ModelAndView article_create_page(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("article_create");
		Result result =  articleCatService.getAllArticleCat();
		mv.addObject("hasCat", result.isSuccess());
		mv.addObject("catList", result.get("catList"));
		return mv;
	}
	
	@RequestMapping(value = "/article_create.htm")
	public ModelAndView article_create(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("create_story");
		ArticleDO article = new ArticleDO();
		article.setCatId(NumberUtils.toLong(request.getParameter("catId"), 0L));
		article.setClicks(0L);
		article.setContent(request.getParameter("content"));
		article.setCreator(getUserId(request));
		article.setPosition(NumberUtils.toInt(request.getParameter("position"), 0));
		article.setStatus(request.getParameter("status"));
		article.setTitle(request.getParameter("title"));
		article.setType(request.getParameter("type"));
		Result result = articleService.addArticle(article);
		
		return mv;
	}
	
	@RequestMapping(value = "/article_edit_page.htm")
	public ModelAndView article_edit_page(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("article_edit");
		Result result =  articleCatService.getAllArticleCat();
		mv.addObject("hasCat", result.isSuccess());
		mv.addObject("catList", result.get("catList"));
		return mv;
	}
	
	@RequestMapping(value = "/article_edit.htm")
	public ModelAndView article_edit(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("create_story");
		return mv;
	}
}