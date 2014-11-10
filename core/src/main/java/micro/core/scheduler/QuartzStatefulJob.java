package micro.core.scheduler;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.quartz.StatefulJob;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyAccessorFactory;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年11月10日 下午3:48:18
 */
public abstract class QuartzStatefulJob implements StatefulJob {

	protected final Log log = LogFactory.getLog(getClass());
	
	protected final String JOB_NAME = getClass().getSimpleName();
	
	protected DataSource dataSource;
	
	@Override
	public final void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(this);
			MutablePropertyValues pvs = new MutablePropertyValues();
			pvs.addPropertyValues(context.getScheduler().getContext());
			pvs.addPropertyValues(context.getMergedJobDataMap());
			bw.setPropertyValues(pvs, true);
		} catch (SchedulerException ex) {
			throw new JobExecutionException("QuartzStatefulJob init Error.", ex);
		}
		try {
			executeInternal(context);
		} catch (Exception e) {
			log.error("Job Named " + JOB_NAME + " Running Error.", e);
		}
	}
	
	protected abstract void executeInternal(JobExecutionContext context) throws JobExecutionException;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}