/**
 * 
 */
package com.csii.upp.dto.router.fundprocess;

import com.csii.upp.constant.FundProcessTransCode;
import com.csii.upp.dto.extend.InputPaymentData;

/**
 * @author lixinyou
 * 卡信息查询请求类
 */
public class ReqQueryCardInfo extends ReqFundProHead  {

	 
	public ReqQueryCardInfo(InputPaymentData data) {
		super(data);
		this.setTransCode(FundProcessTransCode.QryCardInfo);
	}
}
