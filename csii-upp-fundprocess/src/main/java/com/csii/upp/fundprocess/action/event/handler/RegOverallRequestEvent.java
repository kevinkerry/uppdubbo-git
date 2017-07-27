package com.csii.upp.fundprocess.action.event.handler;

import java.util.Map;

import com.csii.upp.event.Event;
/**
 * 异步登记交易流水输入参数
 * @author xujin
 *
 */
public class RegOverallRequestEvent extends Event {
	private Map paramMap;

	public Map getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map paramMap) {
		this.paramMap = paramMap;
	}
}
