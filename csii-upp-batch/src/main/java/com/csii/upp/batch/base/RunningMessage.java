package com.csii.upp.batch.base;

import java.io.Serializable;
import java.util.Map;

import com.csii.upp.dto.generate.Queapplhist;

public class RunningMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	private Object[] runningData;
	private Map<String, Object> paramMap;
	private String workerClassName;
	private boolean isApplParal;
	private Queapplhist queApplHist;

	public RunningMessage(Object[] runningData, Map<String, Object> paramMap,
			String workerClassName, boolean isApplParal, Queapplhist queApplHist) {
		super();
		paramMap.put("checkDate", queApplHist.getRundate());
		this.runningData = runningData;
		this.paramMap = paramMap;
		this.workerClassName = workerClassName;
		this.isApplParal = isApplParal;
		this.queApplHist = queApplHist;
	}

	public Object[] getRunningData() {
		return runningData;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public String getWorkerClassName() {
		return workerClassName;
	}

	public boolean isApplParal() {
		return isApplParal;
	}

	public Queapplhist getQueApplHist() {
		return queApplHist;
	}
}
