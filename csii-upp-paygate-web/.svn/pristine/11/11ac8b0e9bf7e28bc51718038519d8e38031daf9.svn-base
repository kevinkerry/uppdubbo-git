package com.csii.upp.paygate.action.foison;



import java.util.HashMap;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.InnerCardFlag;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqQueryCardFlag;
import com.csii.upp.dto.router.paym.ReqQuerySignInfo;
import com.csii.upp.dto.router.paym.ReqUpdateTransCtrl;
import com.csii.upp.paygate.action.PayGateAction;
public class UpdateCtrlConfirmNextAction extends PayGateAction {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void execute(Context context) throws PeException {
		context.setData(Dict.CHANNEL_NBR, "03");
		context.setData(Dict.PAY_TYP_CD, PayTypCd.FOSION);
		// 组装查询卡信息报文
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		//判断本行它行卡
		Map result = this.sendPaymenTransport(new ReqQueryCardFlag(inputData));
		if(!result.get(Dict.RESP_CODE).equals(ResponseCode.SUCCESS)){
			context.setDataMap(result);
			context.setState(1);
			return;
		}
		String cardFlag=(String) result.get(Dict.INNER_CARD_FLAG);
		if(InnerCardFlag.InnerCardFlag_other.equals(cardFlag)){
			inputData.setPaytypcd(PayTypCd.INTEL);
		}else{
			inputData.setPaytypcd(PayTypCd.FOSION);
		}
		// 查询本地卡 签约关系
		Map resultMap = this.sendPaymenTransport(new ReqQuerySignInfo(inputData));
		String respCode = (String) resultMap.get(Dict.RESP_CODE);
		if (!ResponseCode.SUCCESS.equals(respCode)) {
			context.setState(1);
			context.setDataMap(resultMap);
			return;
		}
		//根据查询到的签约信息去修改交易限额控制表
		String signNbr = (String) resultMap.get(Dict.SIGN_NBR);
		String signTypCd = (String) resultMap.get(Dict.SIGN_TYP_CD);
		String perTransLimit = (String) inputData.getPerTransLimit();
		String perDayLimit = (String) inputData.getPerDayLimit();   
		Map pram =new HashMap();
		    pram.put(Dict.SIGN_NBR, signNbr);
		    pram.put(Dict.SIGN_TYP_CD,signTypCd);
		    pram.put(Dict.PER_TRANS_LIMIT,perTransLimit);
		    pram.put(Dict.PER_DAY_LIMIT,perDayLimit);
		    pram.put(Dict.PAYER_PHONE_NO,inputData.getPayerphoneno());
		    pram.put(Dict.PAYER_ACCT_NBR,inputData.getPayeracctnbr());
		pram = ChangePayLimit(pram);
		if (!ResponseCode.SUCCESS.equals(pram.get(Dict.RESP_CODE))) {
			context.setDataMap(pram);
			context.setState(1);
			return;
		}
		Map dataMap = context.getDataMap();
        dataMap.putAll(pram);
		context.setDataMap(dataMap);
	}
	
	private Map ChangePayLimit(Map map) throws PeException{
		InputPaygateData data = new InputPaygateData(map);
		Map mapdata = this.sendPaymenTransport(new ReqUpdateTransCtrl(data));
		return mapdata;
	}
}
