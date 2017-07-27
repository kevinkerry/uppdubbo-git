package com.csii.upp.fundprocess.action.online;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.fundprocess.action.PayOnlineAction;
import com.csii.upp.service.fundprocess.EAccountService;

/**
 * @ClassName:&{type_name
 * @Description:消费
 * @author :xujin
 * @date：2015-08-13
 */
public class CupsConsumeAction extends PayOnlineAction {

	public void execute(Context ctx) throws PeException {
		InputFundData input = new InputFundData(ctx.getDataMap());
		// 电子账户信息验证
		EAccountService service=this.getDBankService(input);
		ctx.setData(Dict.CHECK_CARD_PWD_FLAG, input.getCheckdataflag());
		service.validatePayerAcctInfo(input);
		// 交易金额限制
		// 借方:电子账户缴费
		getDBankService(input).consume(input);
	}

}
