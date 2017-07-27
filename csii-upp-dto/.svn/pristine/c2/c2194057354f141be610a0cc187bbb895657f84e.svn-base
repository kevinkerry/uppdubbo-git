package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;
/**
 * 查询卡信息请求参数
 * @author fgq
 *
 */
public class ReqQueryOrderInfo extends ReqPaymHead{

	private String transSeqNbr;
	

	public String getTransSeqNbr() {
		return transSeqNbr;
	}


	public void setTransSeqNbr(String transSeqNbr) {
		this.transSeqNbr = transSeqNbr;
	}


	public ReqQueryOrderInfo(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.QueryOrder);
		this.setTransSeqNbr(data.getTransnbr());
	}
}
