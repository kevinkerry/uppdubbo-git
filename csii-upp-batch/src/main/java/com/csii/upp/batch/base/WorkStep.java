package com.csii.upp.batch.base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 代表批量队列中的一个工作步骤
 * 
 * @author lubiao
 * 
 */
class WorkStep {
	private static Log log = LogFactory.getLog(WorkStep.class);

	private Long seqNbr;
	private List<ApplConfiguration> applList;
	private boolean isSuccess = true;

	public WorkStep(long seqNbr) {
		this.seqNbr = seqNbr;
	}

	public Long getSeqNbr() {
		return this.seqNbr;
	}

	/**
	 * 该WorkStep是否属于指定的执行序号
	 * 
	 * @param seqNbr
	 *            执行序号
	 * @return true-属于;false-不属于
	 */
	public boolean isBelongsTo(long seqNbr) {
		if (this.seqNbr == null) {
			return false;
		}
		return this.seqNbr.equals(seqNbr);
	}

	/**
	 * 该WorStep是否是队列中的不可忽略的关键步骤
	 * 
	 * @return true-是;false-不是
	 */
	public boolean isCriticalStep() {
		if (applList == null || applList.isEmpty()) {
			return false;
		}

		boolean result = false;
		for (ApplConfiguration item : applList) {
			if ("Y".equals(item.getQueAppl().getCriticalyn())) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * 该WorStep是否成功执行了
	 * 
	 * @return true-是;false-否
	 */
	public boolean isSuccess() {
		return isSuccess;
	}

	public List<ApplConfiguration> getApplList() {
		if (this.applList == null) {
			this.applList = new ArrayList<ApplConfiguration>();
		}
		return this.applList;
	}

	/**
	 * 运行该WorStep对应的任务
	 */
	public void execute() {
		if (applList == null || applList.isEmpty()) {
			return;
		}

		if (applList.size() > 1) {
			ExecutorService executor = Executors.newCachedThreadPool();
			for (ApplConfiguration appl : applList) {
				executor.execute(new RunnerThread(appl));
			}
			executor.shutdown();
			try {
				if (!executor.awaitTermination(Long.MAX_VALUE,
						TimeUnit.MILLISECONDS)) {
					isSuccess = false;
				}
			} catch (InterruptedException e) {
				log.error("WorkStep", e);
				isSuccess = false;
			}
		} else {
			new RunnerThread(applList.get(0)).run();
		}
	}

	private class RunnerThread implements Runnable {
		private ApplConfiguration appConfig;

		public RunnerThread(ApplConfiguration appConfig) {
			this.appConfig = appConfig;
		}

		@Override
		public void run() {
			try {
				DistributeBus.distribute(appConfig);
			} catch (ApplExecutionException e) {
				log.error("an error orrurred when executing appl with applnbr"
						+ appConfig.getWorkerAppl().getApplnbr(), e);
				isSuccess = false;
			}
		}
	}
}
