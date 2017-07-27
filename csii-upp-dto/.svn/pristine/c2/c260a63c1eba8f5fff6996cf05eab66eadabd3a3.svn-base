package com.csii.upp.dto.router.eaccount;

import java.math.BigDecimal;

import com.csii.upp.constant.EAccountTransCode;
import com.csii.upp.dto.extend.InputFundData;

/**
 * @author JIANGXING
 * 
 */
public class ReqRedeemFund extends ReqEAccountHead {
	private String payeeCardNbr; // 收款人账号(卡号）
	private String currencyCd; // 币种
	private String redemptionTypCd; // 赎回类型
	private BigDecimal tranAmt; // 交易金额
	private String summary; // 摘要
	private String rtxnDesc; // 备注

	public ReqRedeemFund(InputFundData data) {
		super(data);
		this.setTransCode(EAccountTransCode.acctFundRedemption);
		this.setPayeeCardNbr(data.getPayeeacctnbr());
		this.setCurrencyCd(data.getCurrencycd());
		this.setRedemptionTypCd(data.getTranscode());
		this.setTranAmt(data.getTransamt());
		this.setSummary("充值");
		this.setRtxnDesc(data.getNote());
	}

	public String getPayeeCardNbr() {
		return payeeCardNbr;
	}

	public void setPayeeCardNbr(String payeeCardNbr) {
		this.payeeCardNbr = payeeCardNbr;
	}

	public String getCurrencyCd() {
		return currencyCd;
	}

	public void setCurrencyCd(String currencyCd) {
		this.currencyCd = currencyCd;
	}

	public String getRedemptionTypCd() {
		return redemptionTypCd;
	}

	public void setRedemptionTypCd(String redemptionTypCd) {
		this.redemptionTypCd = redemptionTypCd;
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
