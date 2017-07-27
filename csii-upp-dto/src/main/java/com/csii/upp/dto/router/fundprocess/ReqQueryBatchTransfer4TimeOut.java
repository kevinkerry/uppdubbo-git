
package com.csii.upp.dto.router.fundprocess;

import com.csii.upp.constant.FundProcessTransCode;
import com.csii.upp.dto.extend.InputPaymentData;

/**
 * @author zhubenle 订单支付结果查询
 */
public class ReqQueryBatchTransfer4TimeOut extends ReqFundProHead {

	public ReqQueryBatchTransfer4TimeOut(InputPaymentData data) {
		super(data);
		this.setTransCode(FundProcessTransCode.QueryBatchTransfer4TimeOut);
		this.setReqSeqNo(data.getTransseqnbr());
	}
}
