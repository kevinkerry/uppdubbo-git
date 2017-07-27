package com.csii.upp.dto.router.unionpay;

import java.math.BigDecimal;
import java.util.Date;

import com.csii.upp.constant.ConstUnionPay;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.DateUtil;

public class ReqSendMessage extends ReqUnionPayHead {

	private BigDecimal txnAmt; //交易金额
	
	public ReqSendMessage(InputFundData data) {
		super(data);
		String date =  DateUtil.Date_To_DateTimeFormat(new Date(), DateFormatCode.DATETIME_FORMATTER3);
		data.setSendUnionPayTime(date);
		this.setTxnTime(date);
		this.setTxnType("77");
		this.setBizType("000301");
		this.setTxnSubType("02");
		this.setMerId("929331053110008");
		this.setTxnAmt(data.getTransamt());
		this.setCurrencyCode("156");// 默认人民币
		this.setAccNo(data.getPayeracctnbr());
		this.setCustomerInfo(this.getCustomer(this.getEncoding(), new CustomerInfoObj(data, ConstUnionPay.PAY_OUT)));
	}

	public BigDecimal getTxnAmt() {
		return txnAmt;
	}

	public void setTxnAmt(BigDecimal txnAmt) {
		this.txnAmt = txnAmt;
	}
}
