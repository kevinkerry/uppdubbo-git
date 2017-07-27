package com.csii.upp.dto.router.eaccount;

import java.math.BigDecimal;

import com.csii.upp.constant.EAccountTransCode;
import com.csii.upp.dto.extend.InputFundData;

/**
 * 基金申购请求参数
 * 
 * @author JIANGXING
 * 
 */
public class ReqPurchaseFund extends ReqEAccountHead {
	public ReqPurchaseFund(InputFundData data) {
		super(data);
		this.setTransCode(EAccountTransCode.AcctFundPurchases);
		this.setPayerCardNbr(data.getPayeracctnbr());
		this.setCurrencyCd(data.getCurrencycd()); // 币种
		this.setMediPwdCheck(data.getPayercardpwd());
		this.setTranAmt(data.getTransamt());
		this.setSummary("充值");
		this.setRtxnDesc(data.getNote());
	}

	private String payerCardNbr; // 电子账户
	private String currencyCd; // 币种
	private String mediPwdCheck; // 是否验密
	private String agreementtypcd; // 签约类型
	private String mediPwd; // 电子账号交易密码
	private BigDecimal tranAmt; // 交易金额
	private String summary; // 摘要
	private String rtxnDesc; // 备注

	public String getPayerCardNbr() {
		return payerCardNbr;
	}

	public void setPayerCardNbr(String payerCardNbr) {
		this.payerCardNbr = payerCardNbr;
	}

	public String getCurrencyCd() {
		return currencyCd;
	}

	public void setCurrencyCd(String currencyCd) {
		this.currencyCd = currencyCd;
	}

	public String getMediPwdCheck() {
		return mediPwdCheck;
	}

	public void setMediPwdCheck(String mediPwdCheck) {
		this.mediPwdCheck = mediPwdCheck;
	}

	public String getAgreementtypcd() {
		return agreementtypcd;
	}

	public void setAgreementtypcd(String agreementtypcd) {
		this.agreementtypcd = agreementtypcd;
	}

	public String getMediPwd() {
		return mediPwd;
	}

	public void setMediPwd(String mediPwd) {
		this.mediPwd = mediPwd;
	}

	public BigDecimal getTranAmt() {
		return tranAmt;
	}

	public void setTranAmt(BigDecimal tranAmt) {
		this.tranAmt = tranAmt;
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
}
