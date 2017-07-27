package com.csii.upp.dto.router.cnaps2;

import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.ReqAppHead;

/**
 * 二代支付系统服务调用入口参数
 * 
 * @author 徐锦
 *
 */
public class ReqCnaps2Head extends ReqAppHead {
	public ReqCnaps2Head(InputFundData data) {
		super(data);
		this.setChnlId(FundChannelCode.PAY);
		data.setFundchannelcode(FundChannelCode.CNAPS2);
		this.setSrvChnlId(FundChannelCode.CNAPS2);
	}
}
