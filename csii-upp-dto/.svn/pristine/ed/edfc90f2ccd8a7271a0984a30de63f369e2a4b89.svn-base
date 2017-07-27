/*
 * Copyright 2005-2016 Client Service International, Inc. All rights reserved.
 * CSII PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * project: csii-upp-dto
 * create: 2016年1月21日 上午9:55:12
 * vc: $Id: $
 */
package com.csii.upp.dto.router.core;

import com.csii.upp.constant.CoreTransCode;
import com.csii.upp.dto.extend.InputFundData;

/**
 * 查询内部账户详细信息请求类
 * @author qgs
 *
 */
public class ReqQueryInnerAcctInfo extends ReqCoreHead {

	/**
	 * @param data
	 */
	public ReqQueryInnerAcctInfo(InputFundData data) {
		super(data);
		this.setPayerAcctNbr(data.getPayeracctnbr());
		this.setTransCode(CoreTransCode.QueryInnerAcctInfo);
		
	}
	public String getPayerAcctNbr() {
		return payerAcctNbr;
	}
	public void setPayerAcctNbr(String payerAcctNbr) {
		this.payerAcctNbr = payerAcctNbr;
	}
	private String payerAcctNbr;

}
