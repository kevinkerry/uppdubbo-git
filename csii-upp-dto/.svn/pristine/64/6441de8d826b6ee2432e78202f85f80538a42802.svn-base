package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;
/**
 * 丰收E支付查询签约关系
 * @author qgs
 *
 */
public class ReqUpdateTransCtrl extends ReqPaymHead{
	private String signTypCd;
	private String signNbr;
    private String perDayLimit;
    private String perTransLimit;
	

	public ReqUpdateTransCtrl(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.UpdateTransLimit);
		this.setSignTypCd(data.getSignTypCd());
		this.setSignNbr(data.getSignNbr());
		this.setPerDayLimit(data.getPerDayLimit());
		this.setPerTransLimit(data.getPerTransLimit());
	}
    
	public String getPerDayLimit() {
		return perDayLimit;
	}

	public String getPerTransLimit() {
		return perTransLimit;
	}

	public void setPerDayLimit(String perDayLimit) {
		this.perDayLimit = perDayLimit;
	}

	public void setPerTransLimit(String perTransLimit) {
		this.perTransLimit = perTransLimit;
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
