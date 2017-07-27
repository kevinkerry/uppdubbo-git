/**
 * 
 */
package com.csii.upp.dto.router.eaccount;

import com.csii.upp.dto.extend.InputFundData;

/**
 * 通过收款人账号查询电子账户信息入参
 * 
 * @author 姜星
 * 
 */
public class ReqQueryPayeeAcctInfo extends ReqQueryDirectAcctInfo {
	public ReqQueryPayeeAcctInfo(InputFundData data) {
		super(data);
		this.setMediId(data.getPayeeacctnbr());
	}
}
