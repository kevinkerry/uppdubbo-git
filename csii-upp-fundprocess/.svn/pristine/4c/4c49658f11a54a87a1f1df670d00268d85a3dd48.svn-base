/**
 * 
 */
package com.csii.upp.fundprocess.action.payment;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.InteralFlag;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.fundprocess.action.PayOnlineAction;

/**
 * @author lixinyou
 * @description 类信用卡付
 *
 */
public class CccPaymentAction extends PayOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		context.setData(Dict.POINT_DATA_FLAG, InteralFlag.NO);
		InputFundData input = new InputFundData(context.getDataMap());
		RespSysHead result = getDBankService(input).cCConsumePayment(input);
		
	}
	
	
}