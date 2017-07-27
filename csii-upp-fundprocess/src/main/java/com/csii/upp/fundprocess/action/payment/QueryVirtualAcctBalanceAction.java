/**
 * 
 */
package com.csii.upp.fundprocess.action.payment;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.core.RespQueryVirtualAcctBalance;
import com.csii.upp.fundprocess.action.PayOnlineAction;

/**
 * @author wuwenxiang
 * @description 虚账户余额查询
 *
 */
public class QueryVirtualAcctBalanceAction extends PayOnlineAction {

	/* (non-Javadoc)
	 * @see com.csii.pe.action.Executable#execute(com.csii.pe.core.Context)
	 */
	@Override
	public void execute(Context context) throws PeException {
		InputFundData input = new InputFundData(context.getDataMap());
		RespQueryVirtualAcctBalance output = (RespQueryVirtualAcctBalance) getCoreService().queryViratualBalace(input);
		context.setData(Dict.BDOA_BAL, output.getBdoABal());
		context.setData(Dict.BDOB_BAL, output.getBdoBBal());
		context.setData(Dict.BDOG_BAL, output.getBdoGBal());
		context.setData(Dict.USER_NBR, output.getUserNbr());
	}
}