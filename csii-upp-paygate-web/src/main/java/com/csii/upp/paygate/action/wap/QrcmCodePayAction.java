package com.csii.upp.paygate.action.wap;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.paygate.action.PayGateAction;

public class QrcmCodePayAction extends PayGateAction {

	@Override
	public void execute(Context context) throws PeException {
		// TODO Auto-generated method stub
		String str = "http://ww.7har.com/paygate2/qrCode?TransName=QRCM&MerchantName="+context.getString(Dict.MERCHANT_NAME);
		context.setData("url",str);
		
		context.setState(0);
	}

}
