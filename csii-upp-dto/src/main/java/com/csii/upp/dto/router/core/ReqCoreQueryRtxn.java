package com.csii.upp.dto.router.core;

import com.csii.upp.constant.ConstCore;
import com.csii.upp.constant.CoreTransCode;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.DateUtil;

/**
 * 行内转账查询
 * 
 * @author zgb
 *
 */
public class ReqCoreQueryRtxn extends ReqCoreHead {

	
	
	private String channelseqno; // 原渠道流水号
	
	private String trandate; // 原交易日期

	public ReqCoreQueryRtxn(InputFundData data) {
		super(data);
		setTransCode(CoreTransCode.QueryRtxn);
		this.setReceiver(ConstCore.RECEIVER_CORE);
		this.setChannelseqno(data.getInnerfundtransnbr());
		this.setTrandate(DateUtil.Date_To_DateTimeFormat(data.getTransdate(), DateFormatCode.DATE_FORMATTER3));
		
	}


	public String getChannelseqno() {
		return channelseqno;
	}

	public void setChannelseqno(String channelseqno) {
		this.channelseqno = channelseqno;
	}

	public String getTrandate() {
		return trandate;
	}

	public void setTrandate(String trandate) {
		this.trandate = trandate;
	}
}
