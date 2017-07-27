package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;

/**
 * 发送短信验证信息
 * @author fgq
 */
public class ReqSendSms extends ReqPaymHead{
	

	public ReqSendSms(InputPaygateData data) {
		super(data);
		this.setSmsCode(data.getSmsCode());
		this.setSmsContent(data.getSmsContent());
		this.setTransTypCd(data.getTransTypCd());
		this.setTransCode(PaymTransCode.SendSms);
	}
	
	private String smsCode;
	private String smsContent;
	private String transTypCd;

	public String getTransTypCd() {
		return transTypCd;
	}

	public void setTransTypCd(String transTypCd) {
		this.transTypCd = transTypCd;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

}
