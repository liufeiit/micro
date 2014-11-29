package micro.core.service;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年11月17日 下午12:49:26
 */
public interface RevenueService {

	Result query(long userId, PageQuery query, boolean withIncome);

	Result revenue(long userId, double income);

	Result queryUser(long userId, boolean withIncome);
}