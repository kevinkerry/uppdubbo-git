package com.csii.upp.batch.base;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.support.TransactionTemplate;

import com.csii.upp.dao.generate.ApplDAO;
import com.csii.upp.dao.generate.BankoptionDAO;
import com.csii.upp.dao.generate.QueapplerrorDAO;
import com.csii.upp.dto.generate.Appl;
import com.csii.upp.dto.generate.Bankoption;
import com.csii.upp.dto.generate.Queapplerror;
import com.csii.upp.dto.generate.Queapplhist;
import com.csii.upp.util.BeanUtils;

public class ApplRunner {
	private final Log log = LogFactory.getLog(ApplRunner.class);

	public List<RunResult> run(RunningMessage message) {
		try {
			if (message.isApplParal() && message.getRunningData() != null) {
				return runInMultiThread(message);
			} else {
				return runInSingleThread(message);
			}
		} catch (Throwable t) {
			log.error(t);
			throw new RuntimeException(t);
		}
	}

	private List<RunResult> runInSingleThread(RunningMessage message) {

		/**************************** begin：更新执行状态 ********************************************/
		long queNbr = message.getQueApplHist().getQuenbr();
		long applNbr = message.getQueApplHist().getApplnbr(); // 更新监控状态：增加线程1号并且设置线程总任务量
		// 如果是不需要分组的程序 就将任务量设置为2 以便将来执行的时候任务保持在50%
		int datacount = 2;
		if (message.getRunningData() != null
				&& message.getRunningData().length > 0)
			datacount = message.getRunningData().length;
		QueStatusHolder.getInstance().ThreadCreated(Long.MIN_VALUE, 1L, "No.1",
				message, datacount);
		// 设置状态running

		QueStatusHolder.getInstance().updateStatus(
				message.getQueApplHist().getRunseqnbr(),
				message.getQueApplHist().getQuenbr(),
				message.getQueApplHist().getApplnbr(), Long.MIN_VALUE, 1L,
				QueStatusHolder.RunningStatus, null);

		/**************************** end：更新执行状态 *********************************************/

		ApplThread t = new ApplThread(1, message.getRunningData(),
				getApplInstance(message.getWorkerClassName()),
				message.getParamMap(), message.getQueApplHist());
		t.run();
		List<RunResult> resultList = new ArrayList<RunResult>();
		resultList.add(t.getResult());
		return resultList;
	}

	private List<RunResult> runInMultiThread(RunningMessage message) {
		int threadCount = getParalThreadCount();
		List<Object[]> list = DataDivider.divideByGroupNbr(
				message.getRunningData(), threadCount);
		threadCount = list.size();

		List<ApplThread> threadList = new ArrayList<ApplThread>();
		for (Long i = 0L; i < threadCount; i++) {
			/**************************** begin：更新执行状态 ********************************************/
			// 更新监控状态：增加线程并且设置线程总任务量
			QueStatusHolder.getInstance().ThreadCreated(Long.MIN_VALUE, i,
					"No." + i, message, list.get(i.intValue()).length);
			QueStatusHolder.getInstance().updateStatus(
					message.getQueApplHist().getRunseqnbr(),
					message.getQueApplHist().getQuenbr(),
					message.getQueApplHist().getApplnbr(), null, i,
					QueStatusHolder.RunningStatus, null);

			/**************************** end：更新执行状态 *********************************************/

			threadList.add(new ApplThread(i, list.get(i.intValue()),
					getApplInstance(message.getWorkerClassName()), message
							.getParamMap(), message.getQueApplHist()));
		}

		ExecutorService executor = Executors.newCachedThreadPool();
		for (ApplThread t : threadList) {
			executor.execute(t);
		}
		try {
			executor.shutdown();
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);// 超时时间够大，不会出现超时的情况
			List<RunResult> resultList = new ArrayList<RunResult>();
			for (ApplThread t : threadList) {
				resultList.add(t.getResult());
			}
			return resultList;
		} catch (InterruptedException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取参数表中配置的应用内并发线程数目
	 * 
	 * @return
	 */
	private int getParalThreadCount() {

		try {
			Bankoption b;
			b = BankoptionDAO.selectByPrimaryKey("BTRD", 1L);
			return Integer.valueOf(b.getBankoptionvalue());
		} catch (SQLException e) {
			log.error("error",e);
			return 1;
		}

	}

	/**
	 * 实例化应用执行器
	 * 
	 * @param className
	 * @return
	 */
	private IJavaBatchWorker getApplInstance(String className) {
		try {
			Class<?> cls = Class.forName(className);
			return (IJavaBatchWorker) cls.newInstance();
		} catch (Throwable t) {
			throw new RuntimeException(
					"an exception has occurred when create a instance of IJavaBatchAppl.",
					t);
		}
	}

	private class ApplThread implements Runnable {
		private Object[] groupedData;
		private IJavaBatchWorker applInstance;
		private Map<String, Object> paramMap = null;
		private Queapplhist queApplHist = null;
		private long threadNbr;
		private RunResult result;
		private List<Object> conflictList;

		public ApplThread(long threadNbr, Object[] groupedData,
				IJavaBatchWorker applInstance, Map<String, Object> paramMap,
				Queapplhist queApplHist) {
			this.groupedData = groupedData;
			this.applInstance = applInstance;
			this.paramMap = paramMap;
			this.queApplHist = queApplHist;
			this.threadNbr = threadNbr;
			if (this.groupedData != null && this.groupedData.length > 0) {
				this.result = new RunResult(queApplHist.getQuenbr(),
						queApplHist.getApplnbr(), threadNbr, groupedData.length);
			} else {
				this.result = new RunResult(queApplHist.getQuenbr(),
						queApplHist.getApplnbr(), threadNbr, 0);
			}
		}

		@Override
		public void run() {
			try {
				if (this.groupedData != null && this.groupedData.length > 0) {
					runWithData();
				} else {
					runWithoutData();
				}
			} catch (Throwable ex) {

				QueStatusHolder.getInstance().updateStatus(
						queApplHist.getRunseqnbr(), queApplHist.getQuenbr(),
						queApplHist.getApplnbr(), null, threadNbr,
						QueStatusHolder.ErrorStatus, ex.getMessage());

				log.error("执行编号为" + queApplHist.getApplnbr()
						+ "的应用时出现异常，线程终止,线程号{" + this.threadNbr + "}", ex);
				this.result.setException(ex);
			}
		}

		private void runWithoutData() {
			/**************************** begin：更新执行状态 ********************************************/
			// 更新状态监控数据 线程中完成量加1 没有分组的应用监控程序不好监控 统一增加一步 执行完再增加一步
			QueStatusHolder.getInstance().updateStatus(
					queApplHist.getRunseqnbr(), queApplHist.getQuenbr(),
					queApplHist.getApplnbr(), null, threadNbr,
					QueStatusHolder.RunningStatus, "无法监控");
			QueStatusHolder.getInstance().updateStep(
					queApplHist.getRunseqnbr(), queApplHist.getQuenbr(),
					queApplHist.getApplnbr(), null, threadNbr, 1, "无法监控");
			/**************************** end：更新执行状态 ********************************************/

//			TransactionTemplate transactionTemplate = BatchSupportor
//					.getTransactionTemplate();
//			transactionTemplate.execute(new TransactionCallback() {

//				@Override
//				public Object doInTransaction(TransactionStatus arg0) {
					try {

						applInstance.run(null, paramMap);
//						return null;

					} catch (Throwable t) {
						/*logError(t, null);*/
						throw new RuntimeException(t);
					}
//				}
//			});
			/**************************** begin：更新执行状态 ********************************************/
			// 更新状态监控数据 线程中完成量加1 没有分组的应用监控程序不好监控 统一增加一步 执行完再增加一步
			QueStatusHolder.getInstance().updateStep(
					queApplHist.getRunseqnbr(), queApplHist.getQuenbr(),
					queApplHist.getApplnbr(), null, threadNbr, 1, "无法监控");
			// /同时任务结束
			QueStatusHolder.getInstance().updateStatus(
					queApplHist.getRunseqnbr(), queApplHist.getQuenbr(),
					queApplHist.getApplnbr(), null, threadNbr,
					QueStatusHolder.CompletedStatus, "无法监控");
			/**************************** end：更新执行状态 ********************************************/

		}

		private void runWithData() {
			List<Object[]> list = DataDivider.divideByQuota(this.groupedData,
					getQuotaNbr());
//			TransactionTemplate transactionTemplate = BatchSupportor
//					.getTransactionTemplate();
			conflictList = new ArrayList<Object>();

			for (int i = 0; i < list.size(); i++) {
				Object[] array = list.get(i);
				doOneUnit(array, null);
			}

			if (!conflictList.isEmpty()) {
				doOneUnit(conflictList.toArray(), null);
			}
			// /全部跑完更新状态
			QueStatusHolder.getInstance().updateStatus(
					queApplHist.getRunseqnbr(), queApplHist.getQuenbr(),
					queApplHist.getApplnbr(), null, threadNbr,
					QueStatusHolder.CompletedStatus, "无法监控");
		}

		private void doOneUnit(final Object[] array,
				TransactionTemplate transactionTemplate) {
//			transactionTemplate.execute(new TransactionCallback() {
//				@Override
//				public Object doInTransaction(TransactionStatus ts) {
					int successCount = 0;
					int failCount = 0;

					for (int j = 0; j < array.length; j++) {
//						Object obj = ts.createSavepoint();
						final Object entry = array[j];
						try {

							applInstance.run(entry, paramMap);
							successCount++;
						} catch (Throwable t) {
							logError(t, entry);// 这条语句必须在rollbackToSavepoint之后，否则日志会被回滚掉
							// 更新状态监控数据 线程中完成量加-1 这地方要去掉一次增加！！
							QueStatusHolder.getInstance().updateStep(
									queApplHist.getRunseqnbr(),
									queApplHist.getQuenbr(),
									queApplHist.getApplnbr(), null, threadNbr,
									-1, entry.toString());

							throw new RuntimeException(t);
						}
						/**************************** begin：更新执行状态 ********************************************/
						// 更新状态监控数据 线程中完成量加1
						QueStatusHolder.getInstance().updateStep(
								queApplHist.getRunseqnbr(),
								queApplHist.getQuenbr(),
								queApplHist.getApplnbr(), null, threadNbr, 1,
								entry.toString());
						/**************************** end：更新执行状态 ********************************************/
					}
					// 事务commit的时候出异常的几率非常小，所以将数量更新操作放到这里问题不大
					result.plusSuccessCount(successCount);
					result.plusFailureCount(failCount);
//					return ts;
//				}
//			});
		}

		public RunResult getResult() {
			return this.result;
		}

		private void logError(Throwable t, Object runData) {
			Queapplhist hist = this.queApplHist;

			Queapplerror error = new Queapplerror();
			error.setQuenbr(hist.getQuenbr());
			error.setApplnbr(hist.getApplnbr());
			error.setQuesubnbr(hist.getQuesubnbr());
			error.setRunseqnbr(hist.getRunseqnbr());
			error.setErrordetail(BeanUtils.beanToXmlString(t));
			if (runData == null) {
				error.setRundata("there is no rundata with this appl.");
			} else {
				error.setRundata(BeanUtils.beanToXmlString(runData));
			}

			try {
				QueapplerrorDAO.insertSelective(error);
			} catch (SQLException e) {
				log.error("error",e);
			}
			long errorSeqNbr = error.getErrorseqnbr();

			log.error(
					hist.getQuenbr() + hist.getApplnbr() + hist.getQuesubnbr()
							+ hist.getRunseqnbr() + errorSeqNbr, t);
		}

		private int getQuotaNbr() {

			try {
				Appl appl;
				appl = ApplDAO.selectByPrimaryKey(queApplHist.getApplnbr());
				Long quotaNbr = (appl == null ? 50
						: appl.getQuotanbr() == null ? 50 : appl.getQuotanbr());
				return quotaNbr.intValue();
			} catch (SQLException e) {
				log.error("error",e);
				return 50;
			}

		}

	}
}
