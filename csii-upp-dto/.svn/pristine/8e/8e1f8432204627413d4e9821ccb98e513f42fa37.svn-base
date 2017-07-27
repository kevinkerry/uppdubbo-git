package com.csii.upp.dto.router.eaccount;

import java.math.BigDecimal;

import com.csii.upp.constant.EAccountTransCode;
import com.csii.upp.dto.extend.InputFundData;

/**
 * 内部户转出
 * 
 * @author xujin
 * 
 */
public class ReqAcctItlWithdraw extends ReqEAccountHead {
	public ReqAcctItlWithdraw(InputFundData data) {
		super(data);
		// 交易码
		this.setTransCode(EAccountTransCode.AcctItlWithdraw);
		this.setItlnbr(Long.parseLong(data.getPayeracctnbr()));//付款人账号
		this.setAcctnbr(data.getPayeeacctnbr());
		this.setCurrencyCd(data.getCurrencycd());
		this.setTranAmt(data.getTransamt());
	}

	private Long itlnbr;// 收款人账号
	private String acctnbr;// 收款人账号
	private String currencyCd;// 币种
	private BigDecimal tranAmt;// 交易金额
	private String summary;// 摘要
	public Long getItlnbr() {
		return itlnbr;
	}
	public void setItlnbr(Long itlnbr) {
		this.itlnbr = itlnbr;
	}
	public String getAcctnbr() {
		return acctnbr;
	}
	public void setAcctnbr(String acctnbr) {
		this.acctnbr = acctnbr;
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
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}

}
