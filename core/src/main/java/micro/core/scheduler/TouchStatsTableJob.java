package micro.core.scheduler;

import micro.core.dao.DAOException;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年11月10日 下午4:23:38
 */
public class TouchStatsTableJob extends QuartzStatefulJob {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		for(int i = 0; i <= 12; i++) {//计算12个月的
			String tableName = revenueDAO.getTableName(i);
			try {
				if(!revenueDAO.isExistTable(tableName)) {
					revenueDAO.createTable(tableName);
				}
			} catch (DAOException e) {
				log.error("Touch " + tableName + " Revenue Table Error.", e);
			}
		}
	}
}
