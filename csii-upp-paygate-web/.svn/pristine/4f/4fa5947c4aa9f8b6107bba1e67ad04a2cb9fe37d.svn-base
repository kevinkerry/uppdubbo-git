package com.csii.upp.paygate.action.wap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.CardTypCd;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqQueryCardInfo;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.DateUtil;


/**
 * 卡信息校验
 * 
 * @author gaoqi
 * 
 */
public  class CheckCardInfoAction extends PayGateAction {

	@Override
	public void execute(Context context) throws PeException {
		//增加页面个时间戳标志
		long timeStampToken =  DateUtil.getCurrentDateTime().getTime();
		context.setData("timeStampToken", timeStampToken);
		/*
		 * 组装查询卡信息报文
		 */	
		context.getData(Dict.PAYER_ACCT_NBR);
		context.getData(Dict.PAYER_PHONE_NO);
		context.setData(Dict.PAY_TYP_CD, PayTypCd.CARDPWD);
		InputPaygateData inputData=new InputPaygateData(context.getDataMap());			
		/*
		 * 查询卡信息
		 */
		Map resMap=this.sendPaymenTransport(new ReqQueryCardInfo(inputData));
		//验证卡状态信息
		this.validateCardStatusInfo(resMap);
		String respCode = (String) resMap.get(Dict.RESP_CODE);
		if(ResponseCode.SUCCESS.equals(respCode))
		{
			context.setData(Dict.PAYER_CARD_TYP_CD, resMap.get(Dict.PAYER_CARD_TYP_CD));
			context.setData(Dict.PAYER_ACCT_DEPT_NBR, resMap.get(Dict.PAYER_ACCT_DEPT_NBR));
			List PayerPhoneNoList = (List) resMap.get(Dict.PAYER_PHONE_NO_LIST);
			if(!PayerPhoneNoList.contains(context.getString(Dict.PAYER_PHONE_NO))){
				throw new PeException(DictErrors.ACCOUNT_PHONE_ERROR);
			}
			
			if(resMap.get(Dict.PAYER_CARD_TYP_CD).equals(CardTypCd.CREDIT)){
				Calendar cal = Calendar.getInstance();
		        int currentYear = Integer.valueOf(String.valueOf(cal.get(Calendar.YEAR)).substring(2, 4));
		        List<String> yearList = new ArrayList<String>();
		        for(int i = 0 ;i<20;i++) {
		            yearList.add(String.valueOf((currentYear+i)%100));
		        }
		        context.setData("YearList",yearList);
				context.setState(3);
			}else if(resMap.get(Dict.PAYER_CARD_TYP_CD).equals(CardTypCd.DEBIT)){
				context.setState(2);
			}else{
				context.setState(99999);
				throw new PeException(DictErrors.CARD_NBR_ERROR);
			}
		}else{
			context.setState(99999);
			context.setDataMap(resMap);
		}
	}	
}
