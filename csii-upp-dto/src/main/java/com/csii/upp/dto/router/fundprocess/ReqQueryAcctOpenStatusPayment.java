
package com.csii.upp.dto.router.fundprocess;

import com.csii.upp.constant.FundProcessTransCode;
import com.csii.upp.dto.extend.InputPaymentData;

/**
 * @author zhubenle 订单支付结果查询
 */
public class ReqQueryAcctOpenStatusPayment extends ReqFundProHead {

	public ReqQueryAcctOpenStatusPayment(InputPaymentData data) {
		super(data);
		this.setTransCode(FundProcessTransCode.QueryAcctOpenStatus);
		this.setPayerAcctNbr(data.getPayeracctnbr());
	}
}
