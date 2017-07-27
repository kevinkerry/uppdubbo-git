package com.csii.upp.fundprocess.action.online;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.dto.router.eaccount.RespAcctCCQueryLimitAmt;
import com.csii.upp.dto.router.eaccount.RespQueryXmTransLimit;
import com.csii.upp.fundprocess.action.PayOnlineAction;

public class XiaoMiTransLimitAction extends PayOnlineAction {

	@Override
	public void execute(Context ctx) throws PeException {
		InputFundData input = new InputFundData(ctx.getDataMap());
		RespSysHead result1 = getDBankService(input).queryXmTransLimit(input);
		RespSysHead result2 = getDBankService(input).queryAcctCCQueryLimitAmt(input);
		if("0000".equals(result1.getReturncode())){
			ctx.setData("creditLimitAmtRemain1", ((RespQueryXmTransLimit) result1).getCreditLimitAmtRemain());
			ctx.setData("creditLimitAmtRemain2", ((RespAcctCCQueryLimitAmt) result2).getCreditLimitAmtRemain());
		}else{
			ctx.setData(Dict.RESP_CODE, result1.getReturncode());
			ctx.setData(Dict.RESP_MESSAGE, result1.getReturnmsg());
		}
	}

}
