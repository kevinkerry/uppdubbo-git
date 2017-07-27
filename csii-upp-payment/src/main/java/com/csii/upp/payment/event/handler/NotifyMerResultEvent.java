package com.csii.upp.payment.event.handler;

import java.util.Map;

import com.csii.upp.event.Event;
import com.csii.upp.service.payment.NotifyService;
/**
 * 商户通知输入参数
 * @author xujin
 *
 */
public class NotifyMerResultEvent extends Event {
	private Map paramMap;
	private NotifyService notifyService;

	public Map getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map paramMap) {
		this.paramMap = paramMap;
	}

	public NotifyService getNotifyService() {
		return notifyService;
	}

	public void setNotifyService(NotifyService notifyService) {
		this.notifyService = notifyService;
	}
}
