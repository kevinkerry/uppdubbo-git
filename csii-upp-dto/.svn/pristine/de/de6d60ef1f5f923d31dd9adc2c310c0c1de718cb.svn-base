package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;
/**
 * 个人网银支付
 * @author zgb
 *
 */
public class ReqBankPay  extends ReqPaymHead{

	public ReqBankPay(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.bankPay);
		this.setSubMerchantId(data.getSubMerchantId());
		this.setUserNbr(data.getUsernbr());
	}
	
	private String subMerchantId;
	private String userNbr;
	
	public String getSubMerchantId() {
		return subMerchantId;
	}
	public void setSubMerchantId(String subMerchantId) {
		this.subMerchantId = subMerchantId;
	}
	public String getUserNbr() {
		return userNbr;
	}
	public void setUserNbr(String userNbr) {
		this.userNbr = userNbr;
	}

}
