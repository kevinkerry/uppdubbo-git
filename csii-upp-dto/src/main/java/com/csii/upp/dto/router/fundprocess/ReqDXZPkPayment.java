/**
 * 
 */
package com.csii.upp.dto.router.fundprocess;

import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.FundProcessTransCode;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * @author tongzongbing
 *
 */
public class ReqDXZPkPayment extends ReqFundProHead {

	private String transCode;
	private String transTime;
	private String trsferONum;
	private String trsferOName;
	private String trsferOBank;
	private String trsferINum;
	private String trsferIName;
	private String trsferIBank;
	private String transAmount;
	private String cuurrency;

	public ReqDXZPkPayment(InputPaymentData data) {
		super(data);
		this.setTransCode(FundProcessTransCode.TelecomCheck);
		this.setTransTime(DateUtil.Date_To_DateTimeFormat(data.getTranstime(),DateFormatCode.DATETIME_FORMATTER3));
		this.setTrsferONum(data.getTrsferONum());
		this.setTrsferOName(data.getTrsferOName());
		this.setTrsferOBank(data.getTrsferOBank());
		this.setTrsferINum(data.getTrsferINum());
		this.setTrsferIName(data.getTrsferIName());
		this.setTrsferIBank(data.getTrsferIBank());
		this.setTransAmount(StringUtil.parseObjectToString(data.getTransAmount()));
		this.setCuurrency(data.getCuurrency());
	}

	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	public String getTransTime() {
		return transTime;
	}

	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}

	public String getTrsferONum() {
		return trsferONum;
	}

	public void setTrsferONum(String trsferONum) {
		this.trsferONum = trsferONum;
	}

	public String getTrsferOName() {
		return trsferOName;
	}

	public void setTrsferOName(String trsferOName) {
		this.trsferOName = trsferOName;
	}

	public String getTrsferOBank() {
		return trsferOBank;
	}

	public void setTrsferOBank(String trsferOBank) {
		this.trsferOBank = trsferOBank;
	}

	public String getTrsferINum() {
		return trsferINum;
	}

	public void setTrsferINum(String trsferINum) {
		this.trsferINum = trsferINum;
	}

	public String getTrsferIName() {
		return trsferIName;
	}

	public void setTrsferIName(String trsferIName) {
		this.trsferIName = trsferIName;
	}

	public String getTrsferIBank() {
		return trsferIBank;
	}

	public void setTrsferIBank(String trsferIBank) {
		this.trsferIBank = trsferIBank;
	}

	public String getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(String transAmount) {
		this.transAmount = transAmount;
	}

	public Object getCuurrency() {
		return cuurrency;
	}

	public void setCuurrency(String cuurrency) {
		this.cuurrency = cuurrency;
	}

	
}
