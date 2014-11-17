package micro.core.scheduler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import micro.core.dataobject.UserIncomeDO;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import tulip.data.jdbc.mapper.BeanParameterMapper;
import tulip.data.jdbc.named.NamedJdbcTemplate;
import tulip.util.CollectionUtil;

/**
 * 每月1号执行统计计算。
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年11月17日 上午12:36:19
 */
public class RevenueJob extends QuartzStatefulJob {

	private static final int PAGE_SIZE = 5000;

	private static final String Query_User_SQL = "SELECT user_id FROM user ORDER BY created DESC LIMIT :start, :size;";

	private static final String Insert_Income_SQL = "INSERT INTO user_income " + "(user_id, year_month, "
			+ "u_ip, pv, " + "referee_award, activity_award, " + "total_income, created) VALUES "
			+ "(:user_id, :year_month, " + ":u_ip, :pv, " + ":referee_award, :activity_award, "
			+ ":total_income, NOW());";

	private static final String Incr_Income_SQL = "UPDATE user SET account_balance = (account_balance + :income) WHERE user_id = :user_id";

	/**
	 * 计算用户收入情况，并汇总到账户余额。
	 */
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		NamedJdbcTemplate jdbcTemplate = getJdbcTemplate();
		Map<String, Object> paramSource = new HashMap<String, Object>();
		int page = 1;
		int index = (page - 1) * PAGE_SIZE;
		paramSource.put("start", index);
		paramSource.put("size", PAGE_SIZE);
		List<Long> userIdList = jdbcTemplate.queryForList(Query_User_SQL, paramSource, Long.class);
		int month = -1;// 表示上月
		while (!CollectionUtil.isEmpty(userIdList)) {
			for (Long uid : userIdList) {
				if (uid == null || uid <= 0L) {
					continue;
				}
				try {
					UserIncomeDO income = revenueDAO.sumIncome(month, uid);
					jdbcTemplate.update(Insert_Income_SQL, BeanParameterMapper.newInstance(income));
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("user_id", uid);
					paramMap.put("income", income.getTotalIncome());
					jdbcTemplate.update(Incr_Income_SQL, paramMap);
				} catch (Exception e) {
					log.error("Sum Revenue Error.", e);
				}
			}
			page++;
			index = (page - 1) * PAGE_SIZE;
			paramSource.put("start", index);
			userIdList = jdbcTemplate.queryForList(Query_User_SQL, paramSource, Long.class);
		}
	}

	private NamedJdbcTemplate getJdbcTemplate() {
		return new NamedJdbcTemplate(dataSource);
	}
}