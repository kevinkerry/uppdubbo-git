package com.csii.upp.payment.action.mgmt;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.router.fundprocess.RespUnifiefPayment;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.DateUtil;

public class RegisterOtherPayAction extends PaymentOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		InputPaymentData input = new InputPaymentData(context.getDataMap());
		RespUnifiefPayment output = (RespUnifiefPayment) getFDPSService().RegisterOtherQuickPay(input);
		if (output.getReturnForm() != null) {
			context.setData(Dict.RETURN_FORM, output.getReturnForm());
		}
		context.setData(Dict.HOST_CLEAR_DATE, DateUtil.Date_To_DateTimeFormat(output.getHostClearDate(),DateFormatCode.DATE_FORMATTER3));
	}

}
