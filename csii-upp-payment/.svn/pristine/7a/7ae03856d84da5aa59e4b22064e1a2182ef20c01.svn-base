package com.csii.upp.payment.action.query;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.StringUtil;

/**
 * 删除签约信息
 * 
 * @author fgq
 * 
 */
public class DeleteSignInfoAction extends PaymentOnlineAction {
	@Override
	public void execute(Context context) throws PeException {
		String mobilePhone = StringUtil.toStringAndTrim(context.getString(Dict.PAYER_PHONE_NO));
		String payerAcctNbr = StringUtil.toStringAndTrim(context.getString(Dict.PAYER_ACCT_NBR));
		if(StringUtil.isStringEmpty(mobilePhone)){
			throw new PeException(DictErrors.MOBILESIGN_STATUS_ERROR);
		}
		if(StringUtil.isStringEmpty(payerAcctNbr)){
			throw new PeException(DictErrors.CARD_NBR_ERROR);
		}
		this.autoDeleteSignUser(mobilePhone, payerAcctNbr, PayTypCd.INTEL);
	}
}
