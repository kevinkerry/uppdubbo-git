package com.csii.upp.dto.router.dpc;

import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.ReqAppHead;

public class ReqDpcHead extends ReqAppHead {

	public ReqDpcHead(InputFundData data) {
		super(data);
		
		this.setChnlId(FundChannelCode.PAY);
		data.setFundchannelcode(FundChannelCode.DPC);
		this.setSrvChnlId(FundChannelCode.DPC);
	}

}
