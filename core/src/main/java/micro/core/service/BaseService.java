package micro.core.service;

import micro.core.dao.UserDAO;

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
public abstract class BaseService implements InitializingBean, BeanNameAware, ApplicationContextAware, ApplicationEventPublisherAware {

	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	@Qualifier(value="userDAO")
	protected UserDAO userDAO;
	
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

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}