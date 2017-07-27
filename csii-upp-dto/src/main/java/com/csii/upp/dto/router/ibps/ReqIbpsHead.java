/**
 * 
 */
package com.csii.upp.dto.router.ibps;

import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.ReqAppHead;

/**
 * 超级网银服务调用入口参数
 * 
 * @author 姜星
 * 
 */
public class ReqIbpsHead extends ReqAppHead {
	/**
	 * 组装请求公共RequestHead
	 * 
	 * @param data
	 * @return
	 */
	public ReqIbpsHead(InputFundData data) {
		super(data);
		this.setChnlId(FundChannelCode.PAY);
		this.setSrvChnlId(FundChannelCode.IBPS);
		data.setFundchannelcode(FundChannelCode.IBPS);
	}
}
