package com.csii.upp.payment.action.query;


import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.payment.action.PaymentOnlineAction;
/**
 * 查询签约信息
 * @author wy
 *
 */
public class QueryCardFlagAction extends PaymentOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		InputPaymentData input = new InputPaymentData(context.getDataMap());
		queryCardType(input);
		context.setData(Dict.INNER_CARD_FLAG, input.getInnerCardFlag());
	}

}
