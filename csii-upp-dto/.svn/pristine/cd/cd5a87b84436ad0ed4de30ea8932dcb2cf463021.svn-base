package com.csii.upp.dto.router.eaccount;

import com.csii.upp.constant.EAccountTransCode;
import com.csii.upp.dto.extend.InputFundData;

/**
 * 电子账户交易历史查询入参
 * 
 * @author WHD
 * 
 */
public class ReqEcQueryAcctTxn extends ReqEAccountHead {
	public ReqEcQueryAcctTxn(InputFundData data) {
		super(data);
		this.setTransCode(EAccountTransCode.QueryEcAcctTxn);
		this.setExttxnid(data.getInnerfundtransnbr());
	}

	String exttxnid;

	public String getExttxnid() {
		return exttxnid;
	}

	public void setExttxnid(String exttxnid) {
		this.exttxnid = exttxnid;
	}

	
}
