package com.csii.upp.paygate.action.common;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqSMSR;
import com.csii.upp.paygate.action.PayGateAction;

/**
 * 二级商户信息同步
 * @author qgs
 *
 */

public class SMSRAction extends PayGateAction {

	@Override
	public void execute(Context context) throws PeException {
	
		
		// 组装查询卡信息报文
//		if("0".equals(context.getString(Dict.PAYEE_ACCT_KIND))){
//			context.setData(Dict.PAYEE_ACCT_KIND, AcctKind.CORE);
//		}else if("1".equals(context.getString(Dict.PAYEE_ACCT_KIND))){
//			context.setData(Dict.PAYEE_ACCT_KIND, AcctKind.OTHB);
//		}
		context.setData(Dict.SUB_MERCHANT_ID,context.getString(Dict.MERCHANT_ID)); 
		context.setData(Dict.MERCHANT_ID, context.getString(Dict.PARENT_MERCHANT_ID));
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		@SuppressWarnings({ "rawtypes", "unused" })
		Map resultMap = this.sendPaymenTransport(new ReqSMSR(inputData));
		context.setDataMap(resultMap);

	}

}
	