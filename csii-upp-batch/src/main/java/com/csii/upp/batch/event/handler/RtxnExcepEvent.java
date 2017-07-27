package com.csii.upp.batch.event.handler;

import com.csii.pe.service.Service;
import com.csii.upp.dto.generate.Transexceptionreg;
import com.csii.upp.event.Event;
/**
 * 日间异常交易处理输入参数
 * @author 徐锦
 *
 */
public class RtxnExcepEvent extends Event {
	
	private Service routerService;
	
	private Transexceptionreg Transexceptionreg;

	public Transexceptionreg getTransexceptionreg() {
		return Transexceptionreg;
	}

	public void setTransexceptionreg(Transexceptionreg Transexceptionreg) {
		this.Transexceptionreg = Transexceptionreg;
	}

	public Service getRouterService() {
		return routerService;
	}

	public void setRouterService(Service routerService) {
		this.routerService = routerService;
	}
	
	
}
