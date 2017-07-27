package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;
/**
 * 新添订单信息
 * @author dell
 *
 */
public class ReqAddOrderInfo  extends ReqPaymHead{
	
	private String cifNo;
	
	public String getCifNo() {
		return cifNo;
	}

	public void setCifNo(String cifNo) {
		this.cifNo = cifNo;
	}

	public ReqAddOrderInfo(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.AddOrderInfo);
		this.setCifNo(data.getCifNo());
	}

}
