/**
 * 
 */
package com.csii.upp.dto.router.core;
import com.csii.upp.constant.CoreTransCode;
import com.csii.upp.dto.extend.InputFundData;

/**
 * @author lixinyou
 * 借记卡卡信息查询请求类
 */
public class ReqQueryDeditCardInfo extends ReqCoreHead {
	
	private String currencyCd; //币种
	private String cashFlag; //钞汇标志
	private String payerAcctNbr; //付款账号
	public ReqQueryDeditCardInfo(InputFundData data) {
		super(data);
		this.setTransCode(CoreTransCode.QueryDeditCard);
		this.setCashFlag("1");
		this.setPayerAcctNbr(data.getPayeracctnbr());
		this.setCurrencyCd(data.getCurrencycd());
	}
	public String getCurrencyCd() {
		return currencyCd;
	}
	public void setCurrencyCd(String currencyCd) {
		this.currencyCd = currencyCd;
	}
	public String getCashFlag() {
		return cashFlag;
	}
	public void setCashFlag(String cashFlag) {
		this.cashFlag = cashFlag;
	}
	public String getPayerAcctNbr() {
		return payerAcctNbr;
	}
	public void setPayerAcctNbr(String payerAcctNbr) {
		this.payerAcctNbr = payerAcctNbr;
	}

}
