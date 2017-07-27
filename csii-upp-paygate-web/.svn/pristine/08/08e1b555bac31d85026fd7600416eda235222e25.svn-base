package com.csii.upp.paygate.action.specialpay;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqQueryYYTSignInfo;
import com.csii.upp.paygate.action.PayGateAction;

/**
* 银医通支付网关
* @auther fgq email:f_xust@163.com
* @version 创建时间：2016年10月24日 下午2:59:46
* 
*/
public class YinYiTongQueryAction extends PayGateAction{

	@Override
	public void execute(Context context) throws PeException {
		InputPaygateData inputData=new InputPaygateData(context.getDataMap());
		Map resultMap = this.sendPaymenTransport(new ReqQueryYYTSignInfo(inputData));
//		this.valRespStatus(resultMap);
		context.setData(Dict.CONTENT, "<Finance><Message><RespCode>"+resultMap.get(Dict.RESP_CODE) +"</RespCode><RespMessage>"+resultMap.get(Dict.RESP_MESSAGE) + "</RespMessage><SmsSqenbr>" +resultMap.get(Dict.SMS_SQENBR)+ "</SmsSqenbr></Message></Finance>");
	}

}
