package com.csii.upp.payment.action.query;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.router.fundprocess.RespQueryIntegral;
import com.csii.upp.payment.action.PaymentOnlineAction;
/**
 * 积分查询
 * @author wangkl
 *
 */
public class QueryIntegralPaymentAction extends PaymentOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		InputPaymentData input = new InputPaymentData(context.getDataMap());
		RespQueryIntegral output = getFDPSService().QueryIntegral(input);
		context.setData(Dict.OCCU_DATE, output.getOccuDate());
		context.setData(Dict.POINT_RECORDS, output.getPointRecords());
	}

}
