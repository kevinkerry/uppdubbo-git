/**
 * 
 */
package com.csii.upp.dto.router.eaccount;

import com.csii.upp.constant.EAccountTransCode;
import com.csii.upp.dto.extend.InputFundData;

/**
 * 通过付款人账号查询电子账户信息入参
 * 
 * @author 姜星
 * 
 */
public class ReqQueryPayerAcctInfo extends ReqQueryDirectAcctInfo {
	public ReqQueryPayerAcctInfo(InputFundData data) {
		super(data);
		setTransCode(EAccountTransCode.QueryDirectAcctInfo);
		this.setMediId(data.getPayeracctnbr());
	}
}
