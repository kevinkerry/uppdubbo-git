package com.csii.upp.fundprocess.action.online;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.fundprocess.action.PayOnlineAction;
/**
 * 人行代收(异步前半部)
 * @author WHD
 *
 */
public class PbcCollectionBeforeAction extends PayOnlineAction {

	@Override
	public void execute(Context ctx) throws PeException {
		getPbcService().rtdtdr(initInputData(ctx));
		ctx.setData(Dict.CHECK_CARD_PWD_FLAG, FundChannelCode.PBC);
	}

	protected InputFundData initInputData(Context ctx) throws PeException{
		InputFundData input = new InputFundData(ctx.getDataMap());
//		input.setBusitype(ctx.getString(Dict.BUSITYPE));
//		input.setContractno(ctx.getString(Dict.CONTRACTNO));
		input.setPayeracctnbr(ctx.getString(Dict.PAYER_ACCT_NBR));
		input.setPayername(ctx.getString(Dict.PAYER_ACCT_NAME));//PAYERNAME
		return input;
	}
}
