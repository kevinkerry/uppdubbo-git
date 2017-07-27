package com.csii.upp.dto.router.core;

/**
 * 核心借记卡虚账户退货交易响应类
 * @author fandy
 *
 */
public class RespCoreDebitVirtualRefoundTrans extends RespCoreHead {
	private String coreCurrentWorkDate;//核心当前工作日期

	public String getCoreCurrentWorkDate() {
		return coreCurrentWorkDate;
	}

	public void setCoreCurrentWorkDate(String coreCurrentWorkDate) {
		this.coreCurrentWorkDate = coreCurrentWorkDate;
	}

}
