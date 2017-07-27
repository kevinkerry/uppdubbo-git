package com.csii.upp.paygate.action.foison;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqHoldAcctStatus;
import com.csii.upp.paygate.action.PayGateAction;

public class FreezeConfirmAction extends PayGateAction {

	@Override
	public void execute(Context context) throws PeException {
		
		// 组装查询卡信息报文
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		//跨行冻结需校验短信
		if(PayTypCd.INTEL.equals(context.getString(Dict.PAY_TYP_CD))){
			Map smsInfo=this.validateSms(context);
			if(!ResponseCode.SUCCESS.equals(smsInfo.get(Dict.RESP_CODE))){
				context.setState(1);
				context.setData("state", 0);
				context.setDataMap(smsInfo);
				return;
			}
		}
		// 冻结接口
		@SuppressWarnings("rawtypes")
		Map resultMap = this.sendPaymenTransport(new ReqHoldAcctStatus(
				inputData));
		
		String payerAcctNbr = inputData.getPayeracctnbr();
		String acct = payerAcctNbr.substring(payerAcctNbr.length()-4, payerAcctNbr.length());
		context.setData("acct", acct);
		
	}

}
