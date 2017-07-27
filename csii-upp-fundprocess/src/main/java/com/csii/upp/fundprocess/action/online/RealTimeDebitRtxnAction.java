package com.csii.upp.fundprocess.action.online;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.fundprocess.action.PayOnlineAction;

/**
 * 实时借记交易
 * 
 * 根据路由规则选取借记交易通道，调用对应的扣款交易，扣款成功则调用电子账户入金交易
 * 
 * @author 徐锦
 * @modifer 徐锦
 * 
 */
public class RealTimeDebitRtxnAction extends PayOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		InputFundData input = new InputFundData(context.getDataMap());
		//getDebitRouter(input);
		context.setData(Dict.CHECK_CARD_PWD_FLAG, input.getCheckdataflag());
		// 判断验证收款账户是否存在（调电子账户查询接口)
//		this.getDBankService(input).validatePayeeAcctInfo(input); 
		RespSysHead deposite = getDBankService(input).rtdtcr(input);
		

		if (deposite.isSuccess()) {
			RespSysHead withdraw = getCoreService().rtdtdr(input);
			if (deposite.isSuccess()) {

			}
		}
		// getUnionPayService().queryRtxnStateForDebit(input);
	}

}
