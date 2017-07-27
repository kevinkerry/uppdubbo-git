package com.csii.upp.dto.router.eaccount;

import java.math.BigDecimal;

import com.csii.upp.constant.EAccountTransCode;
import com.csii.upp.dto.extend.InputFundData;

/**
 * 缴费输入参数
 * 
 * @author 徐锦
 * 
 */
public class ReqAcctPayment extends ReqEAccountHead {
	public ReqAcctPayment(InputFundData data) {
		super(data);
		setTransCode(EAccountTransCode.AcctPayment);
		this.setMediId(data.getPayeracctnbr());
		this.setCurrencyCd(data.getCurrencycd());
		this.setTranAmt(data.getTransamt());
		this.setMediPwd(data.getPayercardpwd());
		this.setRtxnDesc("缴费");
		this.setSummary("缴费");
	}

	private String mediId;// 付款人账号
	private String currencyCd;// 币种
	private BigDecimal tranAmt;// 交易金额
	private String mediPwdCheck = "0";// 是否验密
	private String mediPwd;// 电子账号交易密码
	private String summary;// 摘要
	private String rtxnDesc;// 备注
	public String getMediId() {
		return mediId;
	}
	public void setMediId(String mediId) {
		this.mediId = mediId;
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

	
}
