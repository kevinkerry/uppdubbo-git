package com.csii.upp.dto.router.eaccount;

import java.math.BigDecimal;

import com.csii.upp.constant.EAccountTransCode;
import com.csii.upp.dto.extend.InputFundData;

/**
 * 单笔借记输入参数
 * 
 * @author 徐锦
 * 
 */
public class ReqAcctInnerWithdrawal extends ReqEAccountHead {
	public ReqAcctInnerWithdrawal(InputFundData data) {
		super(data);
		setTransCode(EAccountTransCode.AcctInnerWithdrawal);
//		this.setPayerCardNbr(data.getPayeracctnbr());
		this.setPayeeCardNbr(data.getPayeeacctnbr());
		this.setPayerName(data.getPayername());
		this.setCurrencyCd(data.getCurrencycd());
		this.setTranAmt(data.getTransamt());
		this.setMediPwd(data.getPayercardpwd());
		this.setRtxnDesc(data.getNote());
		this.setPayeeName(data.getPayeename());
		this.setMediPwdCheck("0");
		this.setMediId(data.getPayeracctnbr());
	}

	private String payeeCardNbr;// 收款人账号
	private String payerCardNbr;// 付款人账号
	private String payerName;// 付款人名称
	private String currencyCd;// 币种
	private BigDecimal tranAmt;// 交易金额
	private String mediPwdCheck = "0";// 是否验密
	private String mediPwd;// 电子账号交易密码
	private String summary;// 摘要
	private String rtxnDesc;// 备注
	private String payeeName;
	private String mediId;
	public String getPayeeCardNbr() {
		return payeeCardNbr;
	}
	public void setPayeeCardNbr(String payeeCardNbr) {
		this.payeeCardNbr = payeeCardNbr;
	}
	public String getPayerCardNbr() {
		return payerCardNbr;
	}
	public void setPayerCardNbr(String payerCardNbr) {
		this.payerCardNbr = payerCardNbr;
	}
	public String getPayerName() {
		return payerName;
	}
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}
	public String getCurrencyCd() {
		return currencyCd;
	}
	public void setCurrencyCd(String currencyCd) {
		this.currencyCd = currencyCd;
	}
	public BigDecimal getTranAmt() {
		return tranAmt;
	}
	public void setTranAmt(BigDecimal tranAmt) {
		this.tranAmt = tranAmt;
	}
	public String getMediPwdCheck() {
		return mediPwdCheck;
	}
	public void setMediPwdCheck(String mediPwdCheck) {
		this.mediPwdCheck = mediPwdCheck;
	}
	public String getMediPwd() {
		return mediPwd;
	}
	public void setMediPwd(String mediPwd) {
		this.mediPwd = mediPwd;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getRtxnDesc() {
		return rtxnDesc;
	}
	public void setRtxnDesc(String rtxnDesc) {
		this.rtxnDesc = rtxnDesc;
	}
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	public String getMediId() {
		return mediId;
	}
	public void setMediId(String mediId) {
		this.mediId = mediId;
	}

}
