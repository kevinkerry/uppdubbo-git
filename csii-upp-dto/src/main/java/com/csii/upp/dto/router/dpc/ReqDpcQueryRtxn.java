/**
 * 
 */
package com.csii.upp.dto.router.dpc;

import com.csii.upp.dto.extend.InputFundData;

/**
 * 大同城系统处理结果查询输入参数
 * 
 * @author 陈彦鹏
 * 
 */
public class ReqDpcQueryRtxn extends ReqDpcHead {
	public ReqDpcQueryRtxn(InputFundData data) {
		// 公共报文头
		super(data);
		this.setTransCode("DpcQuery"); // 服务代码
		// 业务报文体
		this.setChannelno(data.getInnerfundtransnbr()); // 渠道流水号
	}

	private String refno; // 交易参考号
	private String channelno; // 渠道流水号

	public String getRefno() {
		return refno;
	}

	public void setRefno(String refno) {
		this.refno = refno;
	}

	public String getChannelno() {
		return channelno;
	}

	public void setChannelno(String channelno) {
		this.channelno = channelno;
	}


}
