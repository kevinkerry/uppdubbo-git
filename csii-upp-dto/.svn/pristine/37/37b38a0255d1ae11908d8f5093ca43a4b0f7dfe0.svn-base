package com.csii.upp.dto.router.eaccount;

import java.math.BigDecimal;

import com.csii.upp.constant.EAccountTransCode;
import com.csii.upp.dto.extend.InputFundData;

/**
 * 小米付输入参数
 * 
 * @author
 * 
 */
public class ReqXmPayment extends ReqEAccountHead {
	public ReqXmPayment(InputFundData data) {
		super(data);
		setTransCode(EAccountTransCode.CreditLoanConsume);
		this.setCustomerId(data.getCifNo());
		this.setPayMethod(data.getPayMethod());
		this.setTranAmt(data.getTranAmt());
		this.setTerm(data.getTerm());
		this.setOrderNbr(data.getOveralltransnbr());
	}
	
	private String customerId;
	private BigDecimal tranAmt;
	private String payMethod;
	private String term;
	private String orderNbr;
	public BigDecimal getTranAmt() {
		return tranAmt;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public String getTerm() {
		return term;
	}
	public void setTranAmt(BigDecimal tranAmt) {
		this.tranAmt = tranAmt;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getOrderNbr() {
		return orderNbr;
	}
	public void setOrderNbr(String orderNbr) {
		this.orderNbr = orderNbr;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	

	
}
