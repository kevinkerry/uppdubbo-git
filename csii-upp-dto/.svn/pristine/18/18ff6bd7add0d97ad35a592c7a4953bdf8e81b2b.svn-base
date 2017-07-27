package com.csii.upp.dto.router.eaccount;

import java.math.BigDecimal;
import java.util.Date;

import com.csii.upp.constant.EAccountTransCode;
import com.csii.upp.dto.extend.InputFundData;

/**
 * 类信用卡付输入参数
 * 
 * @author
 * 
 */
public class ReqCccPayment extends ReqEAccountHead {
	public ReqCccPayment(InputFundData data) {
		super(data);
		setTransCode(EAccountTransCode.CCConsume);
		this.setCustomerId(data.getCifNo());
		this.setTranAmt(data.getTranAmt());
		this.setTranDate(data.getTransdate());
		this.setOrderNbr(data.getOveralltransnbr());
	}
	
	private String customerId;
	private BigDecimal tranAmt;
	private String payMethod;
	private Date tranDate;
	private String orderNbr;
	public BigDecimal getTranAmt() {
		return tranAmt;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setTranAmt(BigDecimal tranAmt) {
		this.tranAmt = tranAmt;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getOrderNbr() {
		return orderNbr;
	}
	public Date getTranDate() {
		return tranDate;
	}
	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
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
