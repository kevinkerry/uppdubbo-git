package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;
/**
 * 增加签约信息
 * @author zgb   
 *
 */
public class ReqAddSignInfo extends ReqPaymHead{

	public ReqAddSignInfo(InputPaygateData data) {
		super(data);
		
		this.setTransCode(PaymTransCode.AddSignInfo);
		this.setTellerNo(data.getTellerNo());
	}
	
	private String operationType;
	private String tellerNo;
	private String payerCardPwd;

	
	public String getTellerNo() {
		return tellerNo;
	}

	public void setTellerNo(String tellerNo) {
		this.tellerNo = tellerNo;
	}

	public String getPayerCardPwd() {
		return payerCardPwd;
	}

	public void setPayerCardPwd(String payerCardPwd) {
		this.payerCardPwd = payerCardPwd;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
}
