package com.csii.upp.batch.base;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 批量系统组件工厂，通过该Supportor获取系统中的一些基础组件
 * 
 * @author lubiao
 *
 */
@SuppressWarnings("unchecked")
public class BatchSupportor implements ApplicationContextAware {
	private static ApplicationContext appContext;
	private static TransactionTemplate transactionTemplate;

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		appContext = context;
	}

	public static TransactionTemplate getTransactionTemplate() {
		if (transactionTemplate == null) {
			transactionTemplate = (TransactionTemplate) appContext
					.getBean("transactionTemplate");
		}
		return transactionTemplate;
	}
}
