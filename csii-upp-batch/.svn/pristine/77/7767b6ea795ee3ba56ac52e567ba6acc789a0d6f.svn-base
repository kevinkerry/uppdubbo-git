package com.csii.upp.batch.base;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csii.upp.dao.extend.SysinfoExtendDAO;
import com.csii.upp.dao.generate.ParameterDAO;
import com.csii.upp.dao.generate.QueapplparamDAO;
import com.csii.upp.dto.generate.Appl;
import com.csii.upp.dto.generate.Parameter;
import com.csii.upp.dto.generate.Que;
import com.csii.upp.dto.generate.Queappl;
import com.csii.upp.dto.generate.Queapplhist;
import com.csii.upp.dto.generate.Queapplparam;
import com.csii.upp.dto.generate.QueapplparamExample;
import com.csii.upp.util.DateUtil;


class ApplConfiguration {
	private final static Log log = LogFactory.getLog(ApplConfiguration.class);
	private Que parentQue;
	private Queappl queAppl;
	private Queapplhist queApplHist;
	private Appl workerAppl;
	private Appl dataProviderAppl;
	private Map<String, Object> paramMap;
	private Long runSeqNbr;
	private Object[] processingData;
	private IJavaBatchDataProvider batchDataProvider;

	public ApplConfiguration(Que parentQue, Queappl queAppl,
			Queapplhist queApplHist, Appl workerAppl, Appl dataProviderAppl,
			Long runSeqNbr) {
		this.parentQue = parentQue;
		this.queAppl = queAppl;
		this.queApplHist = queApplHist;
		this.workerAppl = workerAppl;
		this.dataProviderAppl = dataProviderAppl;
		this.runSeqNbr = runSeqNbr;
	}

	public Que getParentQue() {
		return parentQue;
	}

	public Appl getWorkerAppl() {
		return workerAppl;
	}

	public Queapplhist getQueApplHist() {
		return queApplHist;
	}

	public Appl getDataProviderAppl() {
		return dataProviderAppl;
	}

	public Map<String, Object> getParamMap() {
		if (paramMap == null) {
			paramMap = buildParameters(queAppl);
		}
		return paramMap;
	}

	public Queappl getQueAppl() {
		return this.queAppl;
	}

	public Long getRunSeqNbr() {
		return runSeqNbr;
	}
    public boolean needToRun(){
    	if (this.getWorkerAppl() != null) {
    		return getJavaBatchWorker().needToRun(this.getQueAppl());
    	}else{
    		return false;
    	}
    }
	public Object[] getProcessingData() {
		if (this.processingData == null) {
			if (this.getDataProviderAppl() != null) {
				long start = System.currentTimeMillis();
				processingData = getBatchDataProvider().getBatchData(
						getParamMap());
				long end = System.currentTimeMillis();
				System.out
						.println("time interval of Getting ProcessingData is: "
								+ (end - start) + "ms.");
			}
		}
		return processingData;
	}

	private IJavaBatchDataProvider getBatchDataProvider() {
		if (this.batchDataProvider == null) {
			try {
				Class<?> cls = Class.forName(dataProviderAppl.getApplname());
				batchDataProvider = (IJavaBatchDataProvider) cls.newInstance();
			} catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}
		return batchDataProvider;
	}
	/**
	 * 实例化应用执行器
	 * 
	 * @param className
	 * @return
	 */
	private IJavaBatchWorker getJavaBatchWorker() {
		try {
			Class<?> cls = Class.forName(this.workerAppl.getApplname());
			return (IJavaBatchWorker) cls.newInstance();
		} catch (Throwable t) {
			throw new RuntimeException(
					"an exception has occurred when create a instance of IJavaBatchWorker.",
					t);
		}
	}
	private Map<String, Object> buildParameters(Queappl queAppl) {
		Map<String, Object> paraMap = new HashMap<String, Object>();

		QueapplparamExample condition = new QueapplparamExample();
		condition.createCriteria().andApplnbrEqualTo(queAppl.getApplnbr())
				.andQuesubnbrEqualTo(queAppl.getQuesubnbr())
				.andQuenbrEqualTo(queAppl.getQuenbr());
		List<Queapplparam> list;
		try {
			list = QueapplparamDAO.selectByExample(condition);

			if (list != null && !list.isEmpty()) {
				for (Queapplparam item : list) {
					Parameter p = ParameterDAO
							.selectByPrimaryKey(item.getParametercd());
					if ("NUM".equals(p.getDatatypcd())) {
						paraMap.put(item.getParametercd(),
								Long.parseLong(item.getParametervalue()));
					} else if ("DATE".equals(p.getDatatypcd())) {
						String str = item.getParametervalue();
						if (str.contains("TODAY")) {
							Date postDate = SysinfoExtendDAO.getSysInfo().getPostdate();
							if (!str.endsWith("TODAY")) {
								int days = Integer.parseInt(str.substring("TODAY"
										.length()));
								Calendar c = Calendar.getInstance();
								c.setTime(postDate);

								c.add(Calendar.DAY_OF_YEAR, (int) days);
								postDate = c.getTime();
							}
							paraMap.put(item.getParametercd(), postDate);
						} else {
							paraMap.put(item.getParametercd(),
									DateUtil.DateFormat_To_Date(str));
						}
					} else {
						paraMap.put(item.getParametercd(), item.getParametervalue());
					}
				}
			}

		} catch (Exception e) {
			log.error("error",e);
		}

		paraMap.put("BKID", 1L);
		paraMap.put("BRCH", 1L);
		paraMap.put("TELL", 1L);
		return paraMap;
	}
}
