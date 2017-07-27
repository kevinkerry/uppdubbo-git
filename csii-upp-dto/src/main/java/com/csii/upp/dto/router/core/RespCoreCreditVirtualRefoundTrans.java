package com.csii.upp.dto.router.core;

/**
 * 核心贷记卡虚账户退货交易响应类
 * @author fandy
 *
 */
public class RespCoreCreditVirtualRefoundTrans extends RespCoreHead {
	private String transDate;

	public String getTransDate() {
		return transDate;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	
}