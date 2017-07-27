package com.csii.upp.payment.action.syn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.supportor.DefaultSupportor;

/**
 * 同步签约信息（迁移用）
 * 
 * @author gaoqi
 *
 */
public class SynSignExitAction extends PaymentOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		List<String> transCodeList = new ArrayList<String>(Arrays.asList("UPP10003","UPP10014", "UPP10019","UPP10005", "UPP10006","UPP10001", "UPP10019","UPP10014", "UPP10019"));
		if(!transCodeList.contains(context.getString(Dict.TRANS_CODE))){
			return ;
		}
		if(!ResponseCode.SUCCESS.equals(context.getString(Dict.RESP_CODE))){
			return ;
		}
	//	InputPaymentData inputData = (InputPaymentData) context.getData(Dict.INPUT_PAYMENT_DATA);
		SynSignExitEvent event = new SynSignExitEvent();
		event.setParamMap(context.getDataMap());
		DefaultSupportor.getEventManager().doService(event);
		}
		
}

