package com.csii.upp.custom.common.api.data.payment;

import java.io.Serializable;
import java.util.List;

import com.csii.upp.custom.common.api.data.base.PaymentRespHead;

public class QuerySmsCodeResp extends PaymentRespHead {
	private static final long serialVersionUID = -8092862502602178132L;
	private String payerPhoneNo;
	private List<SMSDetail> sMSList;
	
	public static class SMSDetail implements Serializable{
		private static final long serialVersionUID = -8567049790570311209L;
		//TODO 报文参数名小写 UPP10036
		
		private String transTime;
		private String code;
		private String msg;
		public String getTransTime() {
			return transTime;
		}
		public void setTransTime(String transTime) {
			this.transTime = transTime;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		
		
		
	}

	public void setPayerPhoneNo(String payerPhoneNo) {
		this.payerPhoneNo = payerPhoneNo;
	}

	public String getPayerPhoneNo() {
		return payerPhoneNo;
	}

	public List<SMSDetail> getsMSList() {
		return sMSList;
	}

	public void setsMSList(List<SMSDetail> sMSList) {
		this.sMSList = sMSList;
	}
}
