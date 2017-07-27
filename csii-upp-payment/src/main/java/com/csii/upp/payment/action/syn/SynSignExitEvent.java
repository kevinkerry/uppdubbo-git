package com.csii.upp.payment.action.syn;

import java.util.Map;

import com.csii.upp.event.Event;

public class SynSignExitEvent extends Event {
	
	private Map paramMap;
	
	public Map getParamMap() {
		return paramMap;
	}
	public void setParamMap(Map paramMap) {
		this.paramMap = paramMap;
	}

}
