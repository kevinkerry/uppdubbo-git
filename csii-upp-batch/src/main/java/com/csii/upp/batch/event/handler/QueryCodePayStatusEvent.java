package com.csii.upp.batch.event.handler;

import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.event.Event;
import com.csii.upp.service.fundprocess.AliPayService;
import com.csii.upp.service.fundprocess.WechatService;

public class QueryCodePayStatusEvent extends Event {

	private boolean timeout;
	private Innerfundtrans innerfundtrans;
	private AliPayService alipayService;
	private WechatService wechatService;
	public AliPayService getAlipayService() {
		return alipayService;
	}



	public void setAlipayService(AliPayService alipayService) {
		this.alipayService = alipayService;
	}



	public WechatService getWechatService() {
		return wechatService;
	}



	public Innerfundtrans getInnerfundtrans() {
		return innerfundtrans;
	}



	public void setWechatService(WechatService wechatService) {
		this.wechatService = wechatService;
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
