package me.andpay.ti.daf.hibernate;

import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;

/**
 * 考虑和Cache兼容的事务管理器类。
 * 
 * @author sea.bao
 */
public class DafHibernateTransactionManager extends HibernateTransactionManager {
	private static final long serialVersionUID = -810774938390455237L;

	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition) {
		super.doBegin(transaction, definition);
		ProtectedHookExecutorAccessor.initHookExecutor();
	}

	@Override
	protected void doCommit(DefaultTransactionStatus status) {
		super.doCommit(status);
		ProtectedHookExecutorAccessor.executeHookHandlers();
	}

	@Override
	protected void doCleanupAfterCompletion(Object transaction) {
		super.doCleanupAfterCompletion(transaction);
		ProtectedHookExecutorAccessor.cleanHookExector();
	}

}
