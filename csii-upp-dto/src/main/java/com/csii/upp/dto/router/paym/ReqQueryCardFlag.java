package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;
/**
 * 查询卡标识
 * @author wy
 *
 */
public class ReqQueryCardFlag extends ReqPaymHead{

	public ReqQueryCardFlag(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.QueryCardFlag);
	}

}
