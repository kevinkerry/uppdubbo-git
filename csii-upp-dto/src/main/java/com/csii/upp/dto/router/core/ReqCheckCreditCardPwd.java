/**
 * 
 */
package com.csii.upp.dto.router.core;

import com.csii.upp.constant.ConstCore;
import com.csii.upp.constant.CoreTransCode;
import com.csii.upp.dto.extend.InputFundData;

/**
 * @author lixinyou 贷记卡卡密校验请求类
 *
 */
public class ReqCheckCreditCardPwd extends ReqCoreHead {
	private String payerCardPwd;
	private String payerAcctNbr;
	private String payerIdTypCd;
	private String checkPwdFlag;
	private String payerIdNbr;

	public ReqCheckCreditCardPwd(InputFundData data) {
		super(data);
		this.setTransCode(CoreTransCode.CheckCreditPwd);
		this.setReceiver(ConstCore.RECEIVER_CREDIT);
		this.setPayerAcctNbr(data.getPayeracctnbr());
		this.setPayerCardPwd(data.getPayercardpwd());
		this.setPayerIdNbr(data.getPayeridnbr());
		this.setPayerIdTypCd(data.getPayeridtypcd());
		this.setCheckPwdFlag(data.getCheckpwdflag());
		this.setReqSeqNo(this.getReqSeqNo().substring(this.getReqSeqNo().length() - 6));
	}

	public String getPayerCardPwd() {
		return payerCardPwd;
	}

	public void setPayerCardPwd(String payerCardPwd) {
		this.payerCardPwd = payerCardPwd;
	}

	public String getPayerAcctNbr() {
		return payerAcctNbr;
	}

	public void setPayerAcctNbr(String payerAcctNbr) {
		this.payerAcctNbr = payerAcctNbr;
	}

	public String getPayerIdTypCd() {
		return payerIdTypCd;
	}

	public void setPayerIdTypCd(String payerIdTypCd) {
		this.payerIdTypCd = payerIdTypCd;
	}

	public String getCheckPwdFlag() {
		return checkPwdFlag;
	}

	public void setCheckPwdFlag(String checkPwdFlag) {
		this.checkPwdFlag = checkPwdFlag;
	}

	public String getPayerIdNbr() {
		return payerIdNbr;
	}

	public void setPayerIdNbr(String payerIdNbr) {
		this.payerIdNbr = payerIdNbr;
	}

}
