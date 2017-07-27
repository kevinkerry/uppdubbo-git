package com.csii.upp.dto.router.point;

import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.supportor.IDGenerateFactory;

public class ReqQueryPayerIntegral extends ReqPointHead {

	private String billNo;// 订单号
	private String cardNo;// 客户卡号

	public ReqQueryPayerIntegral(InputFundData data) {
		super(data);
		this.setTransCode("queryPoint");
		this.setBillNo(IDGenerateFactory.generateSeqId());
		this.setCardNo(data.getPayeracctnbr());
		String url = "/queryPoint?requestId=" + this.getRequestId()
				+ "&protocolVersion=" + this.getProtocolVersion() + "&cardNo="
				+ this.getCardNo() + "&billNo=" + this.getBillNo();
		this.setSuffixUrl(url);
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
}
