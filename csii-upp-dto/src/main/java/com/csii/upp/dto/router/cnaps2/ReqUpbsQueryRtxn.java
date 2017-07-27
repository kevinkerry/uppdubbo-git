package com.csii.upp.dto.router.cnaps2;

import com.csii.upp.constant.Cnaps2TransCode;
import com.csii.upp.dto.extend.InputFundData;

/**
 * 查询交易流水入参
 * 
 * @author WHD
 *
 */
public class ReqUpbsQueryRtxn extends ReqCnaps2Head {

	public ReqUpbsQueryRtxn(InputFundData data) {
		super(data);
		setTransCode(Cnaps2TransCode.QueryRtxn);
	}
}
