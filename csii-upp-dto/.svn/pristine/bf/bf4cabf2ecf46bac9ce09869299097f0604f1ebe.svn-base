/**
 * 
 */
package com.csii.upp.dto.router.ibps;

import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.IBPSTransCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.DateUtil;

/**
 * 超级网银处理结果查询输入参数
 * 
 * @author 姜星
 * 
 */
public class ReqIbpsQueryRtxn extends ReqIbpsHead {
	private String refno; // 交易参考号
	private String channelno; // 渠道流水号
	private String trandate; // 交易日期

	public ReqIbpsQueryRtxn(InputFundData data) {
		// 公共报文头
		super(data);
		this.setTransCode(IBPSTransCode.QueryRtxn);
		// 业务报文体
		this.setChannelno(data.getInnerfundtransnbr()); // 渠道流水号
		this.setTrandate(DateUtil.Date_To_DateTimeFormat(data.getTransdate(),
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

	public String getTrandate() {
		return trandate;
	}

	public void setTrandate(String trandate) {
		this.trandate = trandate;
	}
}
