package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.util.DateUtil;

/**
 * 单笔查询
 * 
 * @author zgb
 *
 */
public class ReqQuerySingleTrans extends ReqPaymHead {

	public ReqQuerySingleTrans(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.IQSR);
		this.setMerTransDate(DateUtil.Date_To_DateTimeFormat(data.getMertransdate(),DateFormatCode.DATE_FORMATTER3));
	}

	private String merTransDate;

	public String getMerTransDate() {
		return merTransDate;
	}

	public void setMerTransDate(String merTransDate) {
		this.merTransDate = merTransDate;
	}
}
