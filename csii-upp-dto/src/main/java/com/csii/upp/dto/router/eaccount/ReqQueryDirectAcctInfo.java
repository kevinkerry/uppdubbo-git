/**
 * 
 */
package com.csii.upp.dto.router.eaccount;

import com.csii.upp.constant.EAccountTransCode;
import com.csii.upp.dto.extend.InputFundData;

/**
 * 电子账户信息查询入参
 * 
 * @author 姜星
 * 
 */
public class ReqQueryDirectAcctInfo extends ReqEAccountHead {
	// 电子账号
	private String mediId;

	public String getMediId() {
		return mediId;
	}


	public void setMediId(String mediiId) {
		this.mediId = mediiId;
	}

	public ReqQueryDirectAcctInfo(InputFundData data) {
		super(data);
		this.setTransCode(EAccountTransCode.QueryDirectAcctInfo);
	}
}
