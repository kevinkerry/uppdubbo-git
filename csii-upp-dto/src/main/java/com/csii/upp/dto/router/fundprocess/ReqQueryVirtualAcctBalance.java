
package com.csii.upp.dto.router.fundprocess;
import com.csii.upp.constant.FundProcessTransCode;
import com.csii.upp.dto.extend.InputPaymentData;
/**
 *  @author wuwenxiang
 * 富阳虚账户余额查询
 */
public class ReqQueryVirtualAcctBalance extends ReqFundProHead {

	public ReqQueryVirtualAcctBalance(InputPaymentData data) {
		super(data);
		this.setTransCode(FundProcessTransCode.QueryVirtualAcctBalance);
		
	}
}
