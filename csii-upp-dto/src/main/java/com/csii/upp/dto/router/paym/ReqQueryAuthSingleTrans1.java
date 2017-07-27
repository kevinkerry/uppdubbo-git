package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;

/**
 * 单笔查询
 * 
 * @author zgb
 *
 */
public class ReqQueryAuthSingleTrans1 extends ReqPaymHead {

	public ReqQueryAuthSingleTrans1(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.QUAT);
		this.setAcctName(data.getAcctName());
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
