package com.csii.upp.dto.router.eaccount;

import com.csii.upp.constant.EAccountTransCode;
import com.csii.upp.dto.extend.InputFundData;

public class ReqPwdValid extends ReqEAccountHead{

	public ReqPwdValid(InputFundData data) {
		super(data);
		this.setTransCode(EAccountTransCode.PwdValid);
		this.setMediId(data.getPayeracctnbr());
		this.setPassword(data.getPayercardpwd());
	}
	// 电子账号
	private String mediId;

	public String getMediId() {
		return mediId;
	}


	public void setMediId(String mediiId) {
		this.mediId = mediiId;
	}
	
	private String password;

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
}
