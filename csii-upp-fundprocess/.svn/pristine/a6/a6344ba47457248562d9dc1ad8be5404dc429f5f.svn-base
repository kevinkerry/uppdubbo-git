
package com.csii.upp.fundprocess.action.payment;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.fundprocess.action.PayOnlineAction;
import com.csii.upp.service.fundprocess.router.CreditRouter;

/**
 * 提现
 * @author lcy
 *
 */
public class NewWithDrawalAction extends PayOnlineAction {


	@Override
	public void execute(Context context) throws PeException {
		InputFundData input = new InputFundData(context.getDataMap());
		context.setData(Dict.CHECK_DATA_FLAG, input.getCheckdataflag());
		RespSysHead eaccount = getCoreService().rtdtdr(input);
		if(eaccount.isSuccess()){
			input.setInnerfundtransnbr("");
			CreditRouter crebitRouter = getCreditRouter(input);
			crebitRouter.withdrawal(input);
		}
	}
}