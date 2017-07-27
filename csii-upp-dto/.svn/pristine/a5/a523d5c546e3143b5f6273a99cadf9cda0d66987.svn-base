
package com.csii.upp.dto.router.fundprocess;

import com.csii.upp.constant.FundProcessTransCode;
import com.csii.upp.dto.extend.InputPaymentData;

/**
 * @author zgb 发送短信
 */
public class ReqSendMessage extends ReqFundProHead {

	public ReqSendMessage(InputPaymentData data) {
		super(data);
		this.setTransCode(FundProcessTransCode.SendMessage);
		this.setPayerPhoneNo(data.getPayerphoneno());
		this.setPayerAcctNbr(data.getPayeracctnbr());
	}

	private String payerPhoneNo;// 付款人手机号

	public String getPayerPhoneNo() {
		return payerPhoneNo;
	}

	public void setPayerPhoneNo(String payerPhoneNo) {
		this.payerPhoneNo = payerPhoneNo;
	}

}
