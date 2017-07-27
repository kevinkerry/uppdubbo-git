
package com.csii.upp.fundprocess.action.payment;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.dto.router.core.RespQueryInnerAcctInfo;
import com.csii.upp.fundprocess.action.PayOnlineAction;

/**
 * 查询内部账户详细信息
 * 
 * @author qgs
 *
 */
public class QueryInnerAcctInfoAction extends PayOnlineAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.csii.pe.action.Executable#execute(com.csii.pe.core.Context)
	 */
	@Override
	public void execute(Context context) throws PeException {
		// TODO Auto-generated method stub
		InputFundData input = new InputFundData(context.getDataMap());
		RespSysHead output = getCoreService().queryInnerAcctInfo(input);
		RespQueryInnerAcctInfo deditoutput = (RespQueryInnerAcctInfo) output;
		context.setData(Dict.MER_OPEN_DEPT_NBR, deditoutput.getPayerAcctDeptNbr());
		context.setData(Dict.INNER_ACCT_STATUS, deditoutput.getInnerAcctStatus());
	}
}