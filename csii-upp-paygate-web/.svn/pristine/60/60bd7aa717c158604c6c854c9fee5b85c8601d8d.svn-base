package com.csii.upp.paygate.action.common;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqWithdrawTrans;
import com.csii.upp.paygate.action.PayGateAction;

/**
 * 提现
 * @author xujin
 */
public class WithdrawAction extends PayGateAction {
	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Context context) throws PeException {
		InputPaygateData inputData=new InputPaygateData(context.getDataMap());
		Map resultMap = this.sendPaymenTransport(new ReqWithdrawTrans(inputData));
        context.setDataMap(resultMap);
	}


}
