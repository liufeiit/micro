package micro.web.controller;

import java.util.HashMap;
import java.util.Map;

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
		ModelAndView mv = returnView(request, "article_detail", "文章", "文章详情");
		long id = NumberUtils.toLong(request.getParameter("id"), 0L);
		Result result = articleService.getArticle(id);
		mv.addObject("success", result.isSuccess());
		mv.addObject("article", result.get("article"));
		return mv;
	}
	
	@RequestMapping(value = "/article_list.htm")
	public ModelAndView article_list(HttpServletRequest request) {
		ModelAndView mv = returnView(request, "article_list", "文章", "文章列表");
		return mv;
	}

	@RequestMapping(value = "/article_create_page.htm")
	public ModelAndView article_create_page(HttpServletRequest request) {
		ModelAndView mv = returnView(request, "article_create", "文章", "创建文章");
		Result result = articleCatService.getAllArticleCat();
		mv.addObject("hasCat", result.isSuccess());
		mv.addObject("catList", result.get("catList"));
		return mv;
	}

	@RequestMapping(value = "/article_create.htm")
	public ModelAndView article_create(HttpServletRequest request) {
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
		Map<String, Object> data = new HashMap<String, Object>();
		if (result.isSuccess()) {
			data.put("id", article.getId());
			return post("article_detail.htm", data, "保存中...");
		}
		return post("article_create_page.htm", data, "保存中...");
	}

	@RequestMapping(value = "/article_edit_page.htm")
	public ModelAndView article_edit_page(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("article_edit");
		Result cat = articleCatService.getAllArticleCat();
		mv.addObject("hasCat", cat.isSuccess());
		mv.addObject("catList", cat.get("catList"));
		long id = NumberUtils.toLong(request.getParameter("id"), 0L);
		Result result = articleService.getArticle(id);
		mv.addObject("success", result.isSuccess());
		mv.addObject("article", result.get("article"));
		return mv;
	}

	@RequestMapping(value = "/article_edit.htm")
	public ModelAndView article_edit(HttpServletRequest request) {
		ArticleDO article = new ArticleDO();
		long id = NumberUtils.toLong(request.getParameter("id"), 0L);
		article.setId(id);
		article.setCatId(NumberUtils.toLong(request.getParameter("catId"), 0L));
		article.setClicks(0L);
		article.setContent(request.getParameter("content"));
		article.setCreator(getUserId(request));
		article.setPosition(NumberUtils.toInt(request.getParameter("position"), 0));
		article.setStatus(request.getParameter("status"));
		article.setTitle(request.getParameter("title"));
		article.setType(request.getParameter("type"));
		Result result = articleService.updateArticle(article);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", id);
		if (result.isSuccess()) {
			return post("article_detail.htm", data, "保存中...");
		}
		return post("article_edit_page.htm", data, "保存中...");
	}
}