package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;

/**
 * 提现交易请求
 * @author 徐锦
 *
 */
public class ReqWithdrawTrans extends ReqPaymHead {

	public ReqWithdrawTrans(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.WithdrawTrans);
		this.setUserNbr(data.getUsernbr());
		this.setSubMerchantId(data.getSubMerchantId());
	}
	
	private String userNbr;
	private String subMerchantId;

	public String getUserNbr() {
		return userNbr;
	}

	public void setUserNbr(String userNbr) {
		this.userNbr = userNbr;
	}

	public String getSubMerchantId() {
		return subMerchantId;
	}

	public void setSubMerchantId(String subMerchantId) {
		this.subMerchantId = subMerchantId;
	}

}
