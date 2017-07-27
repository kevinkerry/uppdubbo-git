
package com.csii.upp.dto.router.fundprocess;

import com.csii.upp.constant.FundProcessTransCode;
import com.csii.upp.dto.extend.InputPaymentData;

/**
 * @author gaoqi 订单支付结果查询
 */
public class ReqQueryHostDate extends ReqFundProHead {

	public ReqQueryHostDate(InputPaymentData data) {
		super(data);
		this.setTransCode(FundProcessTransCode.QueryHostDate);
	}
}
