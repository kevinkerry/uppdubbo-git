package com.csii.upp.fundprocess.action.payment;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.dto.router.unionpay.RespPayForAnothertb;
import com.csii.upp.fundprocess.action.PayOnlineAction;
import com.csii.upp.util.DateUtil;

public class RegisterOtherQuickAction extends PayOnlineAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.csii.pe.action.Executable#execute(com.csii.pe.core.Context)
	 */
	@Override
	public void execute(Context context) throws PeException {
		InputFundData input = new InputFundData(context.getDataMap());
		RespSysHead resp = (RespPayForAnothertb) getUnionPayService().unifiedRegister(input);
		if (resp.getReturnHtml() != null) {
			context.setData(Dict.RETURN_FORM, resp.getReturnHtml());
		}
		context.setData(Dict.HOST_CLEAR_DATE, DateUtil.Date_To_DateTimeFormat(resp.getDownrtxndate(),DateFormatCode.DATE_FORMATTER3));
	}

}
