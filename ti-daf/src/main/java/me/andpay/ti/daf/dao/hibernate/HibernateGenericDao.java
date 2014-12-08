package me.andpay.ti.daf.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import me.andpay.ti.base.AppBizException;
import me.andpay.ti.daf.dao.GenericDao;
import me.andpay.ti.daf.dao.IterateCallback;
import me.andpay.ti.daf.dao.NonUniqueException;
import me.andpay.ti.daf.dao.QueryResult;
import me.andpay.ti.daf.dao.TooManyRecordLockException;
import me.andpay.ti.daf.dao.hibernate.simplequery.ConditionMeta;
import me.andpay.ti.daf.dao.hibernate.simplequery.SimpleQueryFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Hibernate实现的通用Dao类。
 * 
 * @author sea.bao
 * 
 * @param <T>
 * @param <I>
 * @param <Q>
 */
public class HibernateGenericDao<T, I extends Serializable, Q> extends HibernateCrudDao<T, I> implements
		GenericDao<T, I, Q> {
	/**
	 * 最大查询记录数
	 */
	protected static final int MAX_QUERY_RESULT_SIZE = 500;
	
	/**
	 * SimpleQuery工厂
	 */
	protected SimpleQueryFactory simpleQueryFactory;

	/**
	 * 条件类
	 */
	protected Class<? extends Q> conditionClazz;

	/**
	 * 游标取读尺寸，缺省10笔记录。
	 */
	protected int cursorFetchSize = 10;

	@SuppressWarnings("unchecked")
	public HibernateGenericDao() {
		clazz = (Class<? extends T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		conditionClazz = (Class<? extends Q>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[2];
	}

	@PostConstruct
	public void init() {
		simpleQueryFactory.registerCondition(conditionClazz, clazz);
	}

	public long count(Q cond) {
		HQLQuery hqlQuery = simpleQueryFactory.genHQLQuery(cond);
		return HibernateUtil.countElements(this.getHibernateTemplate(), hqlQuery);
	}
	
	public boolean exists(Q cond) {
		HQLQuery hqlQuery = simpleQueryFactory.genHQLQuery(cond);
		return HibernateUtil.existsElement(this.getHibernateTemplate(), hqlQuery);
	}

	@SuppressWarnings("unchecked")
	public List<T> query(Q cond, long firstResult, int maxResults) {
		HQLQuery hqlQuery = simpleQueryFactory.genHQLQuery(cond);

		if (maxResults < 0 || maxResults > MAX_QUERY_RESULT_SIZE) {
			maxResults = MAX_QUERY_RESULT_SIZE;
		}

		return (List<T>) HibernateUtil.queryElements(getHibernateTemplate(), hqlQuery, firstResult, maxResults);
	}

	public void iterate(Q cond, IterateCallback<T> callback) throws AppBizException {
		HQLQuery hqlQuery = simpleQueryFactory.genHQLQuery(cond);
		HibernateUtil.iterateElement(getHibernateTemplate(), hqlQuery, callback, cursorFetchSize, false);
	}

	public void iterateForUpdate(Q cond, IterateCallback<T> callback) throws AppBizException {
		HQLQuery hqlQuery = simpleQueryFactory.genHQLQuery(cond);
		HibernateUtil.iterateElement(getHibernateTemplate(), hqlQuery, callback, cursorFetchSize, true);
	}

	public QueryResult<T> queryForResult(Q cond, long firstResult, int maxResults) {
		QueryResult<T> res = new QueryResult<T>();
		res.setElements(query(cond, firstResult, maxResults));
		res.setCount(count(cond));
		res.setFirst(firstResult);

		return res;
	}

	public T getUnique(Q cond) {
		List<T> list = query(cond, 0, 2);
		if (list.isEmpty()) {
			return null;
		}

		if (list.size() > 1) {
			throw new NonUniqueException(cond);
		}

		return list.get(0);
	}

	public T getUniqueForUpdate(Q cond) {
		List<T> list = queryForUpdate(cond);
		if (list.isEmpty()) {
			return null;
		}

		if (list.size() > 1) {
			throw new NonUniqueException(cond);
		}

		return list.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<T> queryForUpdate(Q cond) {
		HibernateTemplate ht = getHibernateTemplate();
		if (ht.isInTransaction() == false) {
			throw new RuntimeException("loadForUpdate must be called in tx.");
		}

		HQLQuery hqlQuery = simpleQueryFactory.genHQLQuery(cond);
		ConditionMeta condMeta = simpleQueryFactory.getConditionMeta(cond.getClass());
		long count = HibernateUtil.countElements(ht, hqlQuery);
		if (count > 100) {
			// 超过100个实体对象，不允许进行互斥锁定
			throw new TooManyRecordLockException(cond);
		}

		return (List<T>) HibernateUtil.loadElementsForUpdate(ht, hqlQuery, condMeta.getDefaultPersistenceAlias());
	}

	public long bulkUpdate(Q cond, Map<String, Object> values) {
		HQLQuery hqlQuery = simpleQueryFactory.genHQLQuery(cond);
		ConditionMeta condMeta = simpleQueryFactory.getConditionMeta(cond.getClass());
		StringBuffer clause = new StringBuffer();
		clause.append("update ");
		clause.append(condMeta.getSimpleQueryDefinition().getCondition().getFromClause());
		clause.append(" set ");

		List<Object> parameters = new ArrayList<Object>();
		for (Iterator<String> it = values.keySet().iterator(); it.hasNext();) {
			String name = it.next();
			Object value = values.get(name);

			clause.append(condMeta.getDefaultPersistenceAlias());
			clause.append(".");
			clause.append(name);
			clause.append("=? ");

			if (it.hasNext()) {
				clause.append(", ");
			}

			parameters.add(value);
		}

		clause.append(" where ");
		clause.append(hqlQuery.getWhereClause());
		parameters.addAll(hqlQuery.getParameters());

		HibernateTemplate ht = getHibernateTemplate();
		ht.evictEntityRegion(clazz);

		return ht.bulkUpdate(clause.toString(), parameters.toArray());
	}

	public SimpleQueryFactory getSimpleQueryFactory() {
		return simpleQueryFactory;
	}

	@Autowired
	public void setSimpleQueryFactory(@Qualifier("simpleQueryFactory") SimpleQueryFactory simpleQueryFactory) {
		this.simpleQueryFactory = simpleQueryFactory;
	}

}
