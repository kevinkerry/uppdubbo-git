/**
 * 
 */
package com.csii.upp.dto.router.fundprocess;

import java.util.List;
import java.util.Map;

import com.csii.upp.constant.FundProcessTransCode;
import com.csii.upp.dto.extend.InputPaymentData;

/**
 * @author lixinyou
 * 发往fundprocess的他行网银支付交易 
 *
 */
public class ReqOtherEbankPayment extends ReqFundProHead {

	private String checkNumber;   //对账分类编号
	private List<Map<String,String>> payeeAcctList;   //收款账户列表
	private String note; //备用字段
	private String payerBankNbr;
	private String frontCallBackUrl;

	public ReqOtherEbankPayment(InputPaymentData data) {
		super(data);
		this.setTransCode(FundProcessTransCode.OtherEBankPayment);
		this.setPayeeAcctList(data.getPayeeAcctList());
		this.setPayerBankNbr(data.getPayerbanknbr());
		this.setFrontCallBackUrl(data.getFrontCallBackUrl());
	}

	public String getFrontCallBackUrl() {
		return frontCallBackUrl;
	}


	public void setFrontCallBackUrl(String frontCallBackUrl) {
		this.frontCallBackUrl = frontCallBackUrl;
	}


	public String getPayerBankNbr() {
		return payerBankNbr;
	}

	public void setPayerBankNbr(String payerBankNbr) {
		this.payerBankNbr = payerBankNbr;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public List<Map<String, String>> getPayeeAcctList() {
		return payeeAcctList;
	}

	public void setPayeeAcctList(List<Map<String, String>> payeeAcctList) {
		this.payeeAcctList = payeeAcctList;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
