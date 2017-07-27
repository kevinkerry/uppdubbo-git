package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;

/**
 * 单笔查询
 * 
 * @author zgb
 *
 */
public class ReqQueryAuthSingleTrans extends ReqPaymHead {

	public ReqQueryAuthSingleTrans(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.fileNotify);
		this.setAcctName("Y");
	}

	private void setAcctName(String acctName) {
		this.acctName =acctName;
	}

	private String merTransDate;
	private String  acctName;

	public String getAcctName() {
		return acctName;
	}

	public String getMerTransDate() {
		return merTransDate;
	}

	public void setMerTransDate(String merTransDate) {
		this.merTransDate = merTransDate;
	}
}
