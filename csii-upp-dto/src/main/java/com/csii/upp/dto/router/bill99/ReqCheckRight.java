package com.csii.upp.dto.router.bill99;

import java.math.BigDecimal;

import com.csii.upp.dto.extend.InputFundData;

public class ReqCheckRight extends ReqBill99Head {
	public ReqCheckRight(InputFundData data) {
		super(data);
		setPan(data.getPayeracctnbr());
		setPhoneNO(data.getPayerphoneno());
		setAmount(data.getTransamt());
	}

	String pan;
	String phoneNO;
	BigDecimal amount;

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getPhoneNO() {
		return phoneNO;
	}

	public void setPhoneNO(String phoneNO) {
		this.phoneNO = phoneNO;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
