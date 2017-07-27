
package com.csii.upp.fundprocess.action.payment;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.fundprocess.action.PayOnlineAction;
import com.csii.upp.service.fundprocess.router.DebitRouter;

/**
 * 充值
 * @author lcy
 *
 */
public class RechargeAction extends PayOnlineAction {


	@Override
	public void execute(Context context) throws PeException {
		
		InputFundData input = new InputFundData(context.getDataMap());
		DebitRouter debitRouter = getDebitRouter(input);
		context.setData(Dict.CHECK_DATA_FLAG, input.getCheckdataflag());
		RespSysHead recharge = debitRouter.recharge(input);
		if(recharge.isSuccess()){
			input.setInnerfundtransnbr("");
			getCoreService().rtdtdr(input);
		}
	}
}