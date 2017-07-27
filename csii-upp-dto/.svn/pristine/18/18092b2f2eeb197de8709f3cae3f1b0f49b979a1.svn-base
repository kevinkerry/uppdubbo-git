package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;
/**
 * 丰收E支付查询签约关系
 * @author qgs
 *
 */
public class ReqQueryTransCtrl extends ReqPaymHead{
	private String signTypCd;
	private String signNbr;	

	public ReqQueryTransCtrl(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.QueryTransLimit);
		this.setSignTypCd(data.getSignTypCd());
		this.setSignNbr(data.getSignNbr());
	}
    
	public String getSignNbr() {
		return signNbr;
	}
	public void setSignNbr(String signNbr) {
		this.signNbr = signNbr;
	}

	public String getSignTypCd() {
		return signTypCd;
	}
	public void setSignTypCd(String signTypCd) {
		this.signTypCd = signTypCd;
	}
}
