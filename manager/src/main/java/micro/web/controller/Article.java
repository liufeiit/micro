package micro.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import micro.core.dataobject.ArticleCatDO;
import micro.core.dataobject.ArticleDO;
import micro.core.service.PageQuery;
import micro.core.service.Result;
import micro.core.util.QiniuUtils;
import micro.web.WebBase;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import tulip.util.CollectionUtil;
import tulip.util.StringUtil;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年11月13日 下午1:22:32
 */
@SuppressWarnings("unchecked")
@Controller
public class Article extends WebBase {

	@RequestMapping(value = "/article_detail.htm")
	public ModelAndView article_detail(HttpServletRequest request) {
		ModelAndView mv = returnView(request, "article_detail", "文章", "文章详情");
		long id = NumberUtils.toLong(request.getParameter("id"), 0L);
		Result result = articleService.getArticle(id, true);
		mv.addObject("success", result.isSuccess());
		mv.addObject("article", result.get("article"));
		return mv;
	}
	
	@RequestMapping(value = "/article_list.htm")
	public ModelAndView article_list(HttpServletRequest request) {
		ModelAndView mv = returnView(request, "article_list", "文章", "文章列表");
		long catId = NumberUtils.toLong(request.getParameter("catId"), 0L);
		mv.addObject("selected_catId", catId);
		String type = request.getParameter("type");
		mv.addObject("selected_type", type);
		String status = request.getParameter("status");
		mv.addObject("selected_status", status);
		String title = request.getParameter("title");
		mv.addObject("selected_title", title);
		int page = NumberUtils.toInt(request.getParameter("page"), 1);
		Result catResult = articleCatService.getAllArticleCat(true);
		List<ArticleCatDO> catList = (List<ArticleCatDO>) catResult.get("catList");
		Map<Long, String> catMapper = new HashMap<Long, String>();
		if(!CollectionUtil.isEmpty(catList)) {
			for (ArticleCatDO cat : catList) {
				catMapper.put(cat.getId(), cat.getName());
			}
		}
		mv.addObject("catMapper", catMapper);
		mv.addObject("catList", catList);
		Result result = articleService.query(catId, type, status, title, new PageQuery(page));
		boolean hasArticle = result.isSuccess();
		if(page > 1) {
			mv.addObject("prePage", page - 1);
			mv.addObject("currentPage", page);
			mv.addObject("nextPage", hasArticle ? (page + 1) : (page));
		} else {
			mv.addObject("prePage", 1);
			mv.addObject("currentPage", 1);
			mv.addObject("nextPage", 2);
		}
		mv.addObject("hasArticle", hasArticle);
		mv.addObject("articleList", result.get("articleList"));
		return mv;
	}

	@RequestMapping(value = "/article_create_page.htm")
	public ModelAndView article_create_page(HttpServletRequest request) {
		ModelAndView mv = returnView(request, "article_create", "文章", "创建文章");
		Result result = articleCatService.getAllArticleCat(true);
		mv.addObject("hasCat", result.isSuccess());
		mv.addObject("catList", result.get("catList"));
		return mv;
	}
	
	@RequestMapping(value = "/editor_upload.htm")
	public void editor_upload(
			HttpServletRequest request, 
			HttpServletResponse response,
			@RequestParam("upload") MultipartFile upload) {
		String callback = request.getParameter("CKEditorFuncNum"); 
		String uri = null;
		try {
			uri = QiniuUtils.upload(upload.getOriginalFilename(), upload.getInputStream());
		} catch (Exception e) {
			log.error("Qiniu Upload Error.", e);
		}
		PrintWriter out = null;
		try {
			out = response.getWriter();  
			if(StringUtil.isBlank(uri)) {
				StringBuffer ret = new StringBuffer()
				.append("<script type=\"text/javascript\">")
				.append("alert('上传失败！');")
				.append("</script>")
				;
		        out.print(ret.toString());
		        return;
			} 
			StringBuffer ret = new StringBuffer()
			.append("<script type=\"text/javascript\">")
			//.append("window.parent.document.getElementById('cke_68_previewImage').style.display = '';")
			.append("window.parent.document.getElementById('cke_68_previewImage').src = '" + uri + "';")
			.append("window.parent.document.getElementById('cke_73_textInput').value = '" + uri + "';")
			.append("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + uri + "','');")
			//.append("alert('上传成功 : " + uri + "');")
			.append("</script>")
			;
	        out.print(ret.toString());    
		} catch (Exception e) {
			log.error("Ckeditor Upload Error.", e);
		} finally {
			if(out != null) {
				out.flush();
				out.close();
				out = null;
			}
		}
	}

	@RequestMapping(value = "/article_create.htm")
	public ModelAndView article_create(HttpServletRequest request, 
			@RequestParam("cover") MultipartFile cover) {
		long catId = NumberUtils.toLong(request.getParameter("catId"), 0L);
		String content = request.getParameter("content");
		String title = request.getParameter("title");
		
		ArticleDO article = new ArticleDO();
		article.setCatId(catId);
		article.setClicks(0L);
		article.setContent(content);
		article.setCreator(getAdminId(request));
		article.setPosition(NumberUtils.toInt(request.getParameter("position"), 0));
		article.setStatus(request.getParameter("status"));
		article.setTitle(title);
		article.setType(request.getParameter("type"));
		
		if (cover != null && !cover.isEmpty()) {
			try {
				String uri = QiniuUtils.upload(cover.getOriginalFilename(), cover.getInputStream());
				article.setCover(uri);
			} catch (IOException e) {
				log.error("Upload Cover Error.", e);
			}
		}
		
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
		ModelAndView mv = returnView(request, "article_edit", "文章", "文章编辑");
		Result cat = articleCatService.getAllArticleCat(true);
		mv.addObject("hasCat", cat.isSuccess());
		mv.addObject("catList", cat.get("catList"));
		long id = NumberUtils.toLong(request.getParameter("id"), 0L);
		Result result = articleService.getArticle(id, true);
		mv.addObject("success", result.isSuccess());
		mv.addObject("article", result.get("article"));
		return mv;
	}

	@RequestMapping(value = "/article_edit.htm")
	public ModelAndView article_edit(HttpServletRequest request, 
			@RequestParam("cover") MultipartFile cover) {
		ArticleDO article = new ArticleDO();
		long id = NumberUtils.toLong(request.getParameter("id"), 0L);
		article.setId(id);
		article.setCatId(NumberUtils.toLong(request.getParameter("catId"), 0L));
		article.setClicks(0L);
		article.setContent(request.getParameter("content"));
		article.setCreator(getAdminId(request));
		article.setPosition(NumberUtils.toInt(request.getParameter("position"), 0));
		article.setStatus(request.getParameter("status"));
		article.setTitle(request.getParameter("title"));
		article.setType(request.getParameter("type"));
		if (cover != null && !cover.isEmpty()) {
			try {
				String uri = QiniuUtils.upload(cover.getOriginalFilename(), cover.getInputStream());
				article.setCover(uri);
			} catch (IOException e) {
				log.error("Upload Cover Error.", e);
			}
		}
		Result result = articleService.updateArticle(article);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", id);
		if (result.isSuccess()) {
			return post("article_detail.htm", data, "保存中...");
		}
		return post("article_edit_page.htm", data, "保存中...");
	}
}