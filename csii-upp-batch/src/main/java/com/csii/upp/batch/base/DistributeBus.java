package com.csii.upp.batch.base;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csii.upp.dao.generate.BankoptionDAO;
import com.csii.upp.dao.generate.QueapplhistDAO;
import com.csii.upp.dto.generate.Queapplhist;
import com.csii.upp.util.BeanUtils;

/**
 * 应用数据分发总线，总线负责对数据进行分组，然后分发到不同的ApplRunner中执行
 * 
 * @author lubiao
 * 
 */
class DistributeBus {
	private static Log log = LogFactory.getLog(DistributeBus.class);

	public static void distribute(ApplConfiguration applConfig)
			throws ApplExecutionException {
		Queapplhist hist = applConfig.getQueApplHist();
		try {
			logRunBefore(hist, applConfig);

			if (applConfig.getDataProviderAppl() != null
					&& (applConfig.getProcessingData() == null || applConfig
							.getProcessingData().length == 0)) {
				logRunSuccess(hist,
						"This appl has no data to run.so the appl was skipped.");
				QueStatusHolder.getInstance().updateStatus(applConfig.getRunSeqNbr(),
						applConfig.getParentQue().getQuenbr(),
						hist.getApplnbr(), null, null,
						QueStatusHolder.CompletedStatus, null);
			} else if (!applConfig.needToRun()) {
				logRunSuccess(
						hist,
						"Due to the cornExpressFiled This appl need not to be run.so the appl was skipped.");
				QueStatusHolder.getInstance().updateStatus(applConfig.getRunSeqNbr(),
						applConfig.getParentQue().getQuenbr(),
						hist.getApplnbr(), null, null,
						QueStatusHolder.CompletedStatus, null);

			} else {
				List<RunResult> resultList = null;

				String runMode = getRunMode();
				if ("SIGL".equals(runMode)) {
					resultList = runInSingleMode(applConfig);// 单机运行
				} else {
					resultList = runInFarmMode(applConfig);// 多机器集群执行
				}

				if (isFailureExists(resultList)) {
					logRunFailure(hist, BeanUtils.beanToXmlString(resultList));
					throw new ApplExecutionException(applConfig);
				} else {
					logRunSuccess(hist, BeanUtils.beanToXmlString(resultList));
				}
			}
		} catch (ApplExecutionException e) {
			log.error("DistributeBus", e);
			throw e;
		} catch (Throwable t) {
			log.error("DistributeBus", t);
			logRunFailure(hist, BeanUtils.beanToXmlString(t));
			throw new ApplExecutionException(applConfig, t);
		}
	}

	private static List<RunResult> runInSingleMode(ApplConfiguration applConfig) {
		// 更新appl状态监控信息为GROUPING
		QueStatusHolder.getInstance()
				.updateStatus(applConfig.getRunSeqNbr(), applConfig
						.getParentQue().getQuenbr(), applConfig.getWorkerAppl()
						.getApplnbr(), null, null,
						QueStatusHolder.GroupingStatus, null);

		RunningMessage message = new RunningMessage(
				applConfig.getProcessingData(), applConfig.getParamMap(),
				applConfig.getWorkerAppl().getApplname(), "Y".equals(applConfig
						.getWorkerAppl().getParalyn()),
				applConfig.getQueApplHist());

		// 更新appl状态监控信息为RUNNING
		QueStatusHolder.getInstance().updateStatus(applConfig.getRunSeqNbr(), applConfig
				.getParentQue().getQuenbr(), applConfig.getWorkerAppl()
				.getApplnbr(), null, null, QueStatusHolder.RunningStatus, null);

		List<RunResult> list = new ApplRunner().run(message);

		// 更新appl状态监控信息为COMPLETED
		QueStatusHolder.getInstance().updateStatus(applConfig.getRunSeqNbr(), applConfig
				.getParentQue().getQuenbr(), applConfig.getWorkerAppl()
				.getApplnbr(), null, null, QueStatusHolder.CompletedStatus,
				null);

		return list;
	}

	private static List<RunResult> runInFarmMode(ApplConfiguration applConfig) {
		return null;
		// 1 如果appl允许并发，则分发到所有机器

		// 如果appl不允许并发，则发到下一台机器 目前 没考虑复杂的负载均衡特性

		// 并要等待返回
	}

	private static String getRunMode() {
		try {
			return BankoptionDAO
					.selectByPrimaryKey("BATM",1L).getBankoptionvalue();
		} catch (SQLException e) {
			log.error("error",e);
			return null;
		}
	}

	private static boolean isFailureExists(List<RunResult> resultList) {
		if (resultList == null) {
			return false;
		}
		for (RunResult r : resultList) {
			if (r.getException() != null) {
				return true;
			}
		}
		return false;
	}

	private static void logRunBefore(Queapplhist queApplHist,
			ApplConfiguration applConfig) {
		log.info(String.format("Appl {0} is started", queApplHist.getApplnbr()));

		// 设置状态为grouping
		QueStatusHolder.getInstance().updateStatus(applConfig.getRunSeqNbr(),applConfig.getQueApplHist().getQuenbr(), applConfig.getWorkerAppl().getApplnbr(), null, null,
				QueStatusHolder.GroupingStatus, null);
		
		queApplHist.setStartdatetime(new Date());
		queApplHist.setRunningstatus((short) 1);
		try {
			QueapplhistDAO
					.updateByPrimaryKeySelective(queApplHist);
		} catch (SQLException e1) {
			log.error("error",e1);
		}

		long dataCount = applConfig.getProcessingData() != null ? applConfig
				.getProcessingData().length : 0;
		queApplHist.setDatacount(dataCount);
		try {
			QueapplhistDAO
					.updateByPrimaryKeySelective(queApplHist);
		} catch (SQLException e) {
			log.error("error",e);
		}
	}

	private static void logRunSuccess(Queapplhist queApplHist, String detail) {
		log.info(String.format("Appl {0} is sucessfully executed",
				queApplHist.getApplnbr()));

		queApplHist.setStopdatetime(new Date());
		queApplHist.setRunningstatus((short) 2);
		queApplHist.setDetail(detail);
		try {
			QueapplhistDAO
					.updateByPrimaryKeySelective(queApplHist);
		} catch (SQLException e) {
			log.error("error",e);
		}
	}

	private static void logRunFailure(Queapplhist queApplHist, String detail) {
		log.info(String.format("Appl {0} is failed", queApplHist.getApplnbr()));

		queApplHist.setStopdatetime(new Date());
		queApplHist.setRunningstatus((short) 3);
		queApplHist.setDetail(detail);
		try {
			QueapplhistDAO
					.updateByPrimaryKeySelective(queApplHist);
		} catch (SQLException e) {
			log.error("error",e);
		}
	}
}
