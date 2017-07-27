package com.csii.upp.dto.router.hvps;


import java.math.BigDecimal;

import com.csii.upp.constant.HvpsTransCode;
import com.csii.upp.dto.extend.InputFundData;


public class ReqHvpsWithdrawal extends ReqHvpsHead {

	public ReqHvpsWithdrawal(InputFundData data) {
		super(data);
		setTransCode(HvpsTransCode.HvpsCheckApply);
		this.setPayerAcctNbr(data.getPayeracctnbr());
		this.setPayeeAcctNbr(data.getPayeeacctnbr());
		this.setTransAmt(data.getTransamt());
	}
	
	private String payerAcctNbr;// 转出方账号
	private String payeeAcctNbr;// 转入方账号
	private BigDecimal transAmt;// 交易金额
	public String getPayerAcctNbr() {
		return payerAcctNbr;
	}
	public void setPayerAcctNbr(String payerAcctNbr) {
		this.payerAcctNbr = payerAcctNbr;
	}
	public String getPayeeAcctNbr() {
		return payeeAcctNbr;
	}
	public void setPayeeAcctNbr(String payeeAcctNbr) {
		this.payeeAcctNbr = payeeAcctNbr;
	}
	public BigDecimal getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}
	
	
}
