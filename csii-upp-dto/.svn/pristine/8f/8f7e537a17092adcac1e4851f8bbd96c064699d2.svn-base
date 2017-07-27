package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;
/**
 * 卡密验证卡号手机号
 * @author qgs
 *
 */
public class ReqQueryCardPhone extends ReqPaymHead{

	public ReqQueryCardPhone(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.QueryCardPhone);
		this.setPhone(data.getPayerphoneno());
		this.setCard(data.getPayeracctnbr());
		this.setQueryFlag(data.getQueryFlag());
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getQueryFlag() {
		return queryFlag;
	}

	public void setQueryFlag(String queryFlag) {
		this.queryFlag = queryFlag;
	}

	private String phone;
	private String card;
	private String queryFlag;
}
