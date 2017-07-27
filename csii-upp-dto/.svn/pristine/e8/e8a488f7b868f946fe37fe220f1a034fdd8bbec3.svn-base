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
 * TODO 请填写注释.
 * @author wuwenxiang
 * @version $Revision:$
 */
public class ReqQueryVirtualAcctBalance extends ReqCoreHead {

   private String PayeeAcctNbr;
	public String getPayeeAcctNbr() {
	return PayeeAcctNbr;
}
public void setPayeeAcctNbr(String payeeAcctNbr) {
	PayeeAcctNbr = payeeAcctNbr;
}
	public ReqQueryVirtualAcctBalance(InputFundData data) {
		super(data);
		this.setTransCode(CoreTransCode.QueryVirtualAcctBalance);
		this.setPayeeAcctNbr(data.getPayeeacctnbr());
	}



}
