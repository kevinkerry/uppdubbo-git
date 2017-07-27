package com.csii.upp.paygate.action.otherquickpay;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.InnerCardFlag;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqDeleteSignInfo;
import com.csii.upp.dto.router.paym.ReqQueryAcctOpenStatus;
import com.csii.upp.dto.router.paym.ReqQueryCardFlag;
import com.csii.upp.dto.router.paym.ReqQuerySignDetail;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.DateUtil;

public class OtherQuickPayPreAction extends PayGateAction {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void execute(Context context) throws PeException {
		//增加页面个时间戳标志
		long timeStampToken =  DateUtil.getCurrentDateTime().getTime();
		context.setData("timeStampToken", timeStampToken);

		context.setData(Dict.PAYER_PHONE_NO, context.getString(Dict.PAYER_PHONE_NO));
		context.setData(Dict.PAY_TYP_CD, PayTypCd.INTEL);
		// 组装查询卡信息报文
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		Map resultMap = this.sendPaymenTransport(new ReqQuerySignDetail(inputData));
		if(!ResponseCode.SUCCESS.equals(resultMap.get(Dict.RESP_CODE))){
			if(ResponseCode.MOBILENOSIGN.equals(resultMap.get(Dict.RESP_CODE))){
				context.setDataMap(resultMap);
				return;
			}
			context.setState(99999);
		}
		context.setDataMap(resultMap);
 		List<Map<String, Object>> list = (List<Map<String, Object>>) resultMap.get(Dict.CARD_LIST);
		if (list != null && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> lm = list.get(i);
				inputData.setPayeracctnbr(lm.get(Dict.PAYER_ACCT_NBR).toString());
				Map result = this.sendPaymenTransport(new ReqQueryCardFlag(inputData));
				String cardFlag=null;
				if(result.get(Dict.RESP_CODE).equals(ResponseCode.SUCCESS)){
					cardFlag=(String) result.get(Dict.INNER_CARD_FLAG);
				}
				if(InnerCardFlag.InnerCardFlag_other.equals(cardFlag)){
	//				inputData.setIsQueryOpenStatus("0");	//暂时不去他行系统查看该卡信息
					
					inputData.setIsQueryOpenStatus("1");
					
					Map aoMap = this.sendPaymenTransport(new ReqQueryAcctOpenStatus(inputData));
					String openStatus = (String) aoMap.get(Dict.OPEN_STATUS);
					if(!"1".equals(openStatus)){
						this.sendPaymenTransport(new ReqDeleteSignInfo(inputData));
						list.remove(lm);
					}
				}
				
			}
		}
	
		if(list.size() != 0){
			context.setData("cardList", list);
			context.setData(Dict.PAYER_ACCT_NBR, list.get(0).get(Dict.PAYER_ACCT_NBR).toString());
			//查询积分结果集
			List<Map<String, Object>> PointList = new ArrayList<Map<String, Object>>();
			PointList = QueryPointRecords(context);
			context.setData(Dict.POINT_RECORDS,PointList);
		
		if("01".equals(context.getString(Dict.CHANNEL_NBR))){
			context.setState(0);
		}else if ("02".equals(context.getString(Dict.CHANNEL_NBR))) {
			context.setState(1);
		}
		if(PayTypCd.FOSION.equals(list.get(0).get(Dict.PAY_TYP_CD))){
			context.setData(Dict.CARD_LIST, list);
			context.setState(2);
		}
	}
		
  }

}
