package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;
/**
 * @author fgq
 * @category 验证码验证
 */
public class ReqValidateSms extends ReqPaymHead{

	public ReqValidateSms(InputPaygateData data) {
		super(data);
		this.setSmsCode(data.getSmsCode());
		this.setTransTypCd(data.getTransTypCd());
		this.setSmsSqenbr(data.getSmsSqenbr());
		this.setTransCode(PaymTransCode.ValidateSms);
		
	}
	private String smsCode;
	private String transTypCd;
	private String smsSqenbr;
	public String getSmsCode() {
		return smsCode;
	}
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	public String getTransTypCd() {
		return transTypCd;
	}
	public void setTransTypCd(String transTypCd) {
		this.transTypCd = transTypCd;
	}
	public String getSmsSqenbr() {
		return smsSqenbr;
	}
	public void setSmsSqenbr(String smsSqenbr) {
		this.smsSqenbr = smsSqenbr;
	}

}
