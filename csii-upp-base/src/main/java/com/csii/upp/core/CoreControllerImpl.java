package com.csii.upp.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.ApplicationObjectSupport;

import com.csii.pe.chain.Chain;
import com.csii.pe.core.Context;
import com.csii.pe.core.CoreController;
import com.csii.pe.core.PeException;
import com.csii.pe.core.Throttler;
import com.csii.pe.core.TransactionConfig;
import com.csii.pe.core.TransactionConfigAware;

public class CoreControllerImpl extends ApplicationObjectSupport implements
		CoreController {
	private static ThreadLocal contextThreadLocal = new ThreadLocal() {
		protected Object initialValue() {
			return new ArrayList();
		}
	};
	private String defaultTransaction;
	private LoggingInterceptor interceptor;
	private Throttler throttler;

	public void setLoggingInterceptor(LoggingInterceptor loggingInterceptor) {
		this.interceptor = loggingInterceptor;
	}

	public void setMaxRequestsPerSecond(int maximumRequestsPerPeriod) {
		this.throttler = new Throttler(maximumRequestsPerPeriod);
	}


	public void execute(Context context) throws PeException {
		setContext(context);
		try {
			context.setApplicationContext(getApplicationContext());
			if (context instanceof TransactionConfigAware) {
				if ((context.getTransactionId() != null)
						&& (getApplicationContext().containsBean(context
								.getTransactionId()))) {
					TransactionConfig transactionConfig = (TransactionConfig) getApplicationContext()
							.getBean(context.getTransactionId());
					((TransactionConfigAware) context)
							.setTransactionConfig(transactionConfig);
				} else {
					if (this.defaultTransaction == null) {
						throw new PeException("system.undefined_transaction",
								new Object[] { context.getTransactionId() });
					}

					TransactionConfig transactionConfig = (TransactionConfig) getApplicationContext()
							.getBean(this.defaultTransaction);
					((TransactionConfigAware) context)
							.setTransactionConfig(transactionConfig);
				}

			}

			TransactionConfig transactionConfig = context
					.getTransactionConfig();
			if (transactionConfig == null) {
				throw new PeException("system.undefined_transaction",
						new Object[] { context.getTransactionId() });
			}

			Chain chain = transactionConfig.getTemplate().getChain();

			if (this.interceptor != null)
				this.interceptor.preChain(context.getDataMap());
			if (this.throttler != null) {
				this.throttler.throttling();
			}
			chain.execute(context, null);
		} finally {
			setContext(null);
			this.interceptor.cleanUp();
		}
	}

	public void setDefaultTransaction(String string) {
		this.defaultTransaction = string;
	}

	public static Context getContext() {
		List list = (List) contextThreadLocal.get();
		int size = list.size();
		return ((size == 0) ? null : (Context) list.get(size - 1));
	}

	public static void setContext(Context context) {
		if (context == null) {
			List list = (List) contextThreadLocal.get();
			if (list.size() > 0)
				list.remove(list.size() - 1);
		} else {
			List list = (List) contextThreadLocal.get();
			list.add(context);
		}
	}
}
