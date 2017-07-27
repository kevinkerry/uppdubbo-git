package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;
/**
 * 丰收e注册
 * @author zgb   
 *
 */
public class ReqConfirmCancelSignInfo extends ReqPaymHead{

	public ReqConfirmCancelSignInfo(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.ConfirmCancelSignInfo);
		this.setCancelConfirm(data.getCancelConfirm());
		this.setPlain(null);
		this.setSignature(null);
	}
	
	private String cancelConfirm;

	public String getCancelConfirm() {
		return cancelConfirm;
	}

	public void setCancelConfirm(String cancelConfirm) {
		this.cancelConfirm = cancelConfirm;
	}
}
