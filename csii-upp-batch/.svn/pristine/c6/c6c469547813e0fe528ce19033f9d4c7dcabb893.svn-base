package com.csii.upp.batch.base;

import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.csii.upp.dao.extend.SysinfoExtendDAO;
import com.csii.upp.dao.generate.ApplDAO;
import com.csii.upp.dao.generate.QueDAO;
import com.csii.upp.dao.generate.QueapplDAO;
import com.csii.upp.dao.generate.QueapplhistDAO;
import com.csii.upp.dto.generate.Appl;
import com.csii.upp.dto.generate.Que;
import com.csii.upp.dto.generate.Queappl;
import com.csii.upp.dto.generate.QueapplExample;
import com.csii.upp.dto.generate.Queapplhist;
import com.csii.upp.dto.generate.QueapplhistExample;
import com.csii.upp.supportor.IDGenerateFactory;

/**
 * 批量调度器
 * 
 * @author lubiao
 * 
 */
public class BatchRunner implements Job {
	private static Log log = LogFactory.getLog(BatchRunner.class);
	private static Vector<Long> queuingQueList = new Vector<Long>();
	private static Object lock = new Object();
	private static Thread monitor;

	/**
	 * // / 默认构造函数 // / 启动一个线程，监视待运行的list // /如果有了就启动执行，如果查不到就等待一秒钟。 // /王建平
	 * 20141231
	 */
	public BatchRunner() {
		if (monitor == null) {
			monitor = new Thread() {
				public void run() {
					while (true) {
						if (queuingQueList.size() > 0) {
							System.out.println("监视到有队列，直行之");
							runBatch(queuingQueList.remove(0), null);
						} else {
							// System.out.println("所有队列运行完毕，等待一秒钟");
							try {
								synchronized (lock) {
									lock.wait(1000);
								}
							} catch (InterruptedException e) {
								log.error("error",e);
							}
						}
					}
				}
			};
			monitor.start();
		}
	}

	/**
	 * 执行队列
	 * 
	 * @param queNbr
	 *            队列号
	 * @param reRunSeqNbr
	 *            批次号，如果此项不为空 表明是重跑批
	 */
	private static void runBatch(Long queNbr, Long reRunSeqNbr) {
		try {
			log.info("BatchRunner started with quenbr:" + queNbr);

			// 读取队列
			Que que = QueDAO.selectByPrimaryKey(queNbr);
			if (que == null) {
				return;
			}

			// 读取该队列下的所有APPL，并按顺序号排序
			QueapplExample criteria = new QueapplExample();
			criteria.setOrderByClause("seqnbr");
			criteria.setDistinct(true);
			criteria.createCriteria().andQuenbrEqualTo(queNbr)
					.andOnynEqualTo("Y");
			List<Queappl> queApplList = QueapplDAO.selectByExample(criteria);
			if (queApplList == null) {
				return;
			}

			// 根据queApplList创建执行队列
			long runSeqNbr = Long.parseLong(IDGenerateFactory.generateSeqId());

			// 对账日期
			Date checkDate = null;
			// 重跑就需要插表
			if (reRunSeqNbr != null) {
				QueStatusHolder.getInstance().getQues()
						.remove(reRunSeqNbr + "+" + queNbr);

				// 插表设置原来的队列历史为被重新跑了，重跑队列为新批次号
				QueapplhistExample example = new QueapplhistExample();
				example.createCriteria().andQuenbrEqualTo(queNbr)
						.andRunseqnbrEqualTo(reRunSeqNbr);
				List<Queapplhist> queApplHistList = QueapplhistDAO
						.selectByExampleWithoutBLOBs(example);
				for (Queapplhist h : queApplHistList) {
					checkDate = h.getRundate();
					h.setRerunseqnbr(runSeqNbr);
					QueapplhistDAO.updateByPrimaryKeySelective(h);
				}
			}
			// 初始化监控对象
			QueStatusHolder.getInstance().QueCreated(runSeqNbr,
					que.getQuenbr(), que.getQuedesc());

			Queue<WorkStep> queue = new LinkedList<WorkStep>();
			WorkStep step = null;
			for (Queappl item : queApplList) {
				ApplConfiguration appl = constructApplConfiguration(que, item,
						runSeqNbr, checkDate);
				boolean skipit = false;// 是否跳过该appl
				if (reRunSeqNbr != null) {
					// 加载对应的queapplhist 如果applhist的状态为结束，就要跳过该appl
					QueapplhistExample example = new QueapplhistExample();
					example.createCriteria()
							.andQuenbrEqualTo(queNbr)
							.andRunseqnbrEqualTo(reRunSeqNbr)
							.andApplnbrEqualTo(
									appl.getWorkerAppl().getApplnbr());
					List<Queapplhist> queApplHistList = QueapplhistDAO
							.selectByExampleWithoutBLOBs(example);

					for (Queapplhist h : queApplHistList) {
						if (h.getRunningstatus() == 2) {
							skipit = true;
						}
					}

				}
				if (skipit) {
					Queapplhist queApplHist = appl.getQueApplHist();
					log.info(String.format("Appl {0} is sucessfully skipped",
							queApplHist.getApplnbr()));
					queApplHist.setStartdatetime(new Date());
					queApplHist.setStopdatetime(new Date());
					queApplHist.setRunningstatus((short) 2);
					queApplHist
							.setDetail("Because of COMPLETED status, This appl need not to be run during RERUN scene");
					QueapplhistDAO.updateByPrimaryKeySelective(queApplHist);
					continue;
				}
				// 监控对象添加appl
				QueStatusHolder.getInstance().ApplLoaded(appl);

				if (step != null && step.isBelongsTo(item.getSeqnbr())) {
					step.getApplList().add(appl);
				} else {
					step = new WorkStep(item.getSeqnbr());
					step.getApplList().add(appl);
				}
				if (!queue.contains(step)) {
					queue.offer(step);
				}
			}

			// 遍历队列，执行应用
			int index = 1;
			for (WorkStep workStep : queue) {
				workStep.execute();
				if (!workStep.isSuccess() && workStep.isCriticalStep()) {
					throw new RuntimeException(
							"the critical workstep with stepnbr:"
									+ workStep.getSeqNbr() + " is failed.");
				}
			}

			log.info("BatchRunner with quenbr：" + queNbr
					+ "is successfully executed.");
		} catch (Throwable t) {
			log.error("BatchRunner with quenbr：" + queNbr + "is failed.", t);
			MessageSender.send("运行批量应用时出现错误，请排查." + "错误所在队列:" + queNbr);
		}
	}

	/**
	 * 程序入口
	 * 
	 * @param queNbr
	 * @param runSeqNbr
	 */
	public void run(Long queNbr, Long runSeqNbr) {
		if (runSeqNbr == null) {
			try {
				queuingQueList.add(queNbr);
			} catch (Exception exc) {
				System.out.println(exc.getMessage());
			}
			try {
				lock.notify();
			} catch (IllegalMonitorStateException excp) {
			}
		} else {
			runBatch(queNbr, runSeqNbr);
		}
	}

	private void run(Long queNbr) {
		try {
			queuingQueList.add(queNbr);
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
		}
		try {
			lock.notify();
		} catch (IllegalMonitorStateException excp) {
		}
	}

	/**
	 * 将参数配置表中的数据组装成ApplConfiguration对象
	 * 
	 */
	private static ApplConfiguration constructApplConfiguration(Que que,
			Queappl queAppl, long runSeqNbr, Date checkDate) {
		Appl workerAppl;
		try {
			workerAppl = ApplDAO.selectByPrimaryKey(queAppl.getApplnbr());

			Appl dataProviderAppl = ApplDAO.selectByPrimaryKey(workerAppl
					.getProviderapplnbr());
			return new ApplConfiguration(que, queAppl, insertQueapplhist(
					queAppl, runSeqNbr, checkDate), workerAppl,
					dataProviderAppl, runSeqNbr);
		} catch (SQLException e) {
			log.error("error",e);
			return null;
		}

	}

	/**
	 * 插入应用执行历史表
	 * 
	 * @param item
	 * @param runSeqNbr
	 * @return
	 */
	private static Queapplhist insertQueapplhist(Queappl item, long runSeqNbr,
			Date checkDate) {
		Queapplhist queApplHist = new Queapplhist();
		queApplHist.setQuenbr(item.getQuenbr());
		queApplHist.setApplnbr(item.getApplnbr());
		queApplHist.setQuesubnbr(item.getQuesubnbr());
		queApplHist.setRunseqnbr(runSeqNbr);
		queApplHist.setRunntwknodenbr(new Long(33279));
		Date runDate = checkDate;
		if (runDate == null) {
			runDate = SysinfoExtendDAO.getSysInfo().getPrevpostdate();
		}
		queApplHist.setRundate(runDate);
		queApplHist.setRunningstatus((short) 0);// 设为待启动状态
		try {
			QueapplhistDAO.insertSelective(queApplHist);
		} catch (SQLException e) {
			log.error("error",e);
		}
		return queApplHist;
	}

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		run(Long.valueOf(context.getJobDetail().getJobDataMap().get("queNbr")
				.toString()));
	}
}
