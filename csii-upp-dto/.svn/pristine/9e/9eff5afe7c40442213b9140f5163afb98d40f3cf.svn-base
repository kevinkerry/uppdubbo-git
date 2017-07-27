/*
 * Copyright 2005-2016 Client Service International, Inc. All rights reserved.
 * CSII PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * project: csii-upp-dto
 * create: 2016年1月21日 上午9:55:12
 * vc: $Id: $
 */
package com.csii.upp.dto.router.core;

import java.math.BigDecimal;
import java.util.Date;

import com.csii.upp.constant.CoreTransCode;
import com.csii.upp.dto.extend.InputFundData;

/**
 * TODO 请填写注释.
 * @author gaoqi
 * @version $Revision:$
 */
public class ReqConfirmedVirtualAcctPay extends ReqCoreHead {
	private String payeeAcctNbr;
	private String origInnerTransNbr;
	private BigDecimal transAmt;
	private Date transDate;
	private String note;
	public ReqConfirmedVirtualAcctPay(InputFundData data) {
		super(data);
		this.setTransCode(CoreTransCode.ConfirmedVirtualAcctPay);
		this.setPayeeAcctNbr(data.getPayeeacctnbr());
		this.setOrigInnerTransNbr(data.getOriginnertransnbr());
		/****************begin 并行期间要求,并行完后可以去掉************************/
		if(data.getOriginnertransnbr().length()==6){
			this.setOrigInnerTransNbr(data.getOriguppertransnbr());
		}
		/****************end 并行期间要求,并行完后可以去掉**************************/
		this.setTransAmt(data.getTransamt());
		this.setNote(data.getNote());
		
	}

	public String getPayeeAcctNbr() {
		return payeeAcctNbr;
	}

	public void setPayeeAcctNbr(String payeeAcctNbr) {
		this.payeeAcctNbr = payeeAcctNbr;
	}

	public String getOrigInnerTransNbr() {
		return origInnerTransNbr;
	}
	public void setOrigInnerTransNbr(String origInnerTransNbr) {
		this.origInnerTransNbr = origInnerTransNbr;
	}
	public BigDecimal getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}
	public Date getTransDate() {
		return transDate;
	}
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

}
