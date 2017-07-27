/**
 * 
 */
package com.csii.upp.dto.router.core;

import com.csii.upp.constant.CoreTransCode;
import com.csii.upp.dto.extend.InputFundData;

/**
 * @author lixinyou
 * 借记卡卡密码校验请求类
 */
public class ReqCheckDeditCardPwd extends ReqCoreHead {
	
	private String payercardpwd;
	private String payeracctnbr;
	private String payeridtypcd;
	private String checkpwdflag;
	private String payeridnbr;
	
	public ReqCheckDeditCardPwd(InputFundData data) {
		super(data);
		this.setTransCode(CoreTransCode.CheckDeditPwd);
		this.setPayeracctnbr(data.getPayeracctnbr());
		this.setPayercardpwd(data.getPayercardpwd());
		this.setPayeridnbr(data.getPayeridnbr());
		this.setPayeridtypcd(data.getPayeridtypcd());
		this.setCheckpwdflag(data.getCheckpwdflag());
	}
	
	public String getPayercardpwd() {
		return payercardpwd;
	}
	public void setPayercardpwd(String payercardpwd) {
		this.payercardpwd = payercardpwd;
	}

	public String getPayeridtypcd() {
		return payeridtypcd;
	}

	public void setPayeridtypcd(String payeridtypcd) {
		this.payeridtypcd = payeridtypcd;
	}

	public String getPayeracctnbr() {
		return payeracctnbr;
	}

	public void setPayeracctnbr(String payeracctnbr) {
		this.payeracctnbr = payeracctnbr;
	}

	public String getCheckpwdflag() {
		return checkpwdflag;
	}

	public void setCheckpwdflag(String checkpwdflag) {
		this.checkpwdflag = checkpwdflag;
	}

	public String getPayeridnbr() {
		return payeridnbr;
	}

	public void setPayeridnbr(String payeridnbr) {
		this.payeridnbr = payeridnbr;
	}

}
