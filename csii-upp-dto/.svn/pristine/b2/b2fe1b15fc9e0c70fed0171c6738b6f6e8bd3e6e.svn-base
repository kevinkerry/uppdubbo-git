/**
 * 
 */
package com.csii.upp.dto.router.beps;

import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.DateUtil;

public class ReqBepsQueryRtxn extends ReqBepsHead {

	private String refno; // 交易参考号
	private String channelno; // 渠道流水号
	private String sourcedate; // 交易日期

	public ReqBepsQueryRtxn(InputFundData data) {
		super(data);
		this.setTransCode("HvpsQueryRtxn");
		// 业务报文体
		this.setSourcedate(DateUtil.Date_To_DateTimeFormat(data.getTransdate(),
				DateFormatCode.DATE_FORMATTER3)); // 交易日期
	}

	public String getRefno() {
		return this.refno;
	}

	public void setRefno(String refno) {
		this.refno = refno;
	}

	public String getChannelno() {
		return this.channelno;
	}

	public void setChannelno(String channelno) {
		this.channelno = channelno;
	}

	public String getSourcedate() {
		return this.sourcedate;
	}

	public void setSourcedate(String sourcedate) {
		this.sourcedate = sourcedate;
	}
}
