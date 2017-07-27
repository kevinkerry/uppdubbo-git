package com.csii.upp.fundprocess.action.payment;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.unionpay.RespQueryRtxnState;
import com.csii.upp.fundprocess.action.PayOnlineAction;

/**
 * @author zhubenle
 * @description 查询快捷支付开通状态
 *
 */
public class QueryAcctOpenStatusFundAction extends PayOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		InputFundData input = new InputFundData(context.getDataMap());
		RespQueryRtxnState resp = (RespQueryRtxnState) getUnionPayService().queryAcctOpenStatus(input);
		if(resp.isFailure() || resp.isTimeout()){
			context.setData(Dict.OPEN_STATUS, "0");
		}else {
			context.setData(Dict.OPEN_STATUS, resp.getActivateStatus());
		}
	}
}