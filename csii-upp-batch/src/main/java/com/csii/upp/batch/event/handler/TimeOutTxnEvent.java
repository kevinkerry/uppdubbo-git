package com.csii.upp.batch.event.handler;

import com.csii.pe.service.Service;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.event.Event;

public class TimeOutTxnEvent extends Event {

	private boolean timeout;
	private Innerfundtrans innerfundtrans;
	private Service routerService;
	
	public Innerfundtrans getInnerfundtrans() {
		return innerfundtrans;
	}

	public Service getRouterService() {
		return routerService;
	}

	public void setRouterService(Service routerService) {
		this.routerService = routerService;
	}

	public void setInnerfundtrans(Innerfundtrans innerfundtrans) {
		this.innerfundtrans = innerfundtrans;
	}

	public boolean isTimeout() {
		return timeout;
	}

	public void setTimeout(boolean timeout) {
		this.timeout = timeout;
	}

}
