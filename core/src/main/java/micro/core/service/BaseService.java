package micro.core.service;

import micro.core.dao.ArticleCatDAO;
import micro.core.dao.ArticleDAO;
import micro.core.dao.AdminDAO;
import micro.core.dao.RevenueDAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月25日 下午1:12:04
 */
public abstract class BaseService implements InitializingBean, BeanNameAware, ApplicationContextAware,
		ApplicationEventPublisherAware {

	protected final Log log = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier(value = "adminDAO")
	protected AdminDAO adminDAO;
	@Autowired
	@Qualifier(value = "articleDAO")
	protected ArticleDAO articleDAO;
	@Autowired
	@Qualifier(value = "articleCatDAO")
	protected ArticleCatDAO articleCatDAO;
	@Autowired
	@Qualifier(value = "revenueDAO")
	protected RevenueDAO revenueDAO;

	protected ApplicationContext application;
	protected ApplicationEventPublisher publisher;
	protected String name;

	@Override
	public final void setBeanName(String name) {
		this.name = name;
	}

	@Override
	public final void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		publisher = applicationEventPublisher;
		publisher.publishEvent(new ApplicationEvent(this) {
			private static final long serialVersionUID = 1L;
		});
	}

	@Override
	public final void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		application = applicationContext;
	}

	@Override
	public final void afterPropertiesSet() throws Exception {
		try {
			initialize();
		} catch (Exception e) {
			log.error("Service Named : " + name + " Initialize Error.", e);
		}
	}

	protected void initialize() throws Exception {

	}

	/**
	 * @param adminDAO the adminDAO to set
	 */
	public void setAdminDAO(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}
	
	/**
	 * @param articleDAO the articleDAO to set
	 */
	public void setArticleDAO(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}
	
	/**
	 * @param articleCatDAO the articleCatDAO to set
	 */
	public void setArticleCatDAO(ArticleCatDAO articleCatDAO) {
		this.articleCatDAO = articleCatDAO;
	}
}