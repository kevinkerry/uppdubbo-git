package com.csii.upp.paygate.action.foison;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqQuerySignDetail;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.DateUtil;


public class QuerySignDetailAction extends PayGateAction {

@SuppressWarnings({ "unchecked", "rawtypes" })
public void execute(Context context) throws PeException {
		//增加页面个时间戳标志
		long timeStampToken =  DateUtil.getCurrentDateTime().getTime();
		context.setData("timeStampToken", timeStampToken);
	
		context.setData(Dict.PAYER_PHONE_NO, context.getString(Dict.FS_PAYER_PHONE_NO));
		context.setData(Dict.PAY_TYP_CD, PayTypCd.FOSION);
		//组装查询卡信息报文
		InputPaygateData inputData=new InputPaygateData(context.getDataMap());
		
		//查询签约关系
		Map resultMap=this.sendPaymenTransport(new ReqQuerySignDetail(inputData));
		if(!ResponseCode.SUCCESS.equals(resultMap.get(Dict.RESP_CODE))){
			if(ResponseCode.MOBILENOSIGN.equals(resultMap.get(Dict.RESP_CODE))){
				context.setDataMap(resultMap);
				context.setState(1);
				return;
			}
			context.setState(99999);
		}
		context.setDataMap(resultMap);
		context.setData(Dict.CARD_LIST, resultMap.get(Dict.CARD_LIST));
		List<Map<String, Object>> list = (List<Map<String, Object>>) resultMap.get(Dict.CARD_LIST);
		context.setData(Dict.PAYER_ACCT_NBR, list.get(0).get(Dict.PAYER_ACCT_NBR).toString());
		//查询积分结果集
		List<Map<String, Object>> PointList = new ArrayList<Map<String, Object>>();
		PointList = QueryPointRecords(context);
		context.setData(Dict.POINT_RECORDS,PointList);
	
	}
}
