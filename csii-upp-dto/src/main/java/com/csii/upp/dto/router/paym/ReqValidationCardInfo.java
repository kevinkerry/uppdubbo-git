package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;

/**
 * 卡信息验证
 * 
 * @author zgb
 *
 */
public class ReqValidationCardInfo extends ReqPaymHead {

	public ReqValidationCardInfo(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.CSVR);
        this.setAcctName(data.getAcctName());
	}

	private String acctName;

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

}
