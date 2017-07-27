package com.csii.upp.paygate.action.cardpwd;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqQueryCardInfo;
import com.csii.upp.dto.router.paym.ReqQueryCardPhone;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.DateUtil;

public class QueryCardInfoAction extends PayGateAction{

	@Override
	public void execute(Context context) throws PeException {
		//增加页面个时间戳标志
		long timeStampToken =  DateUtil.getCurrentDateTime().getTime();
		context.setData("timeStampToken", timeStampToken);
		
		/*
		 * 组装查询卡信息报文
		 */
		
		context.setData(Dict.PAY_TYP_CD, PayTypCd.CARDPWD);
		InputPaygateData inputData=new InputPaygateData(context.getDataMap());
		
		/*
		 * 卡密验证卡号手机号输入错误次数
		 */
		inputData.setQueryFlag("0");
		Map quaryCardPhoneMap=this.sendPaymenTransport(new ReqQueryCardPhone(inputData));
		if(!ResponseCode.SUCCESS.equals(quaryCardPhoneMap.get(Dict.RESP_CODE)))
		{
			context.setState(99999);
			Map dataMap = context.getDataMap();
			dataMap.putAll(quaryCardPhoneMap);
			context.setDataMap(dataMap);
			return;
		}
		
		/*
		 * 查询卡信息
		 */
		Map resultMap=this.sendPaymenTransport(new ReqQueryCardInfo(inputData));
		//验证卡状态信息
		this.validateCardStatusInfo(resultMap);
		/*
		 * 判断查询状态
		 */
		if(ResponseCode.SUCCESS.equals(resultMap.get(Dict.RESP_CODE)))
		{
			/*
			 * 输入正确，清空卡号手机号输入错误次数
			 */
			inputData.setQueryFlag("2");
			Map map=this.sendPaymenTransport(new ReqQueryCardPhone(inputData));
			context.setState(1);
		}
		else
		{	
			/*
			 * 核心报错插入卡号手机号输入错误次数
			 */
			inputData.setQueryFlag("1");
			Map map=this.sendPaymenTransport(new ReqQueryCardPhone(inputData));
			context.setState(99999);
			Map dataMap = context.getDataMap();
			dataMap.putAll(resultMap);
			context.setDataMap(dataMap);
			return;
		}
		
		context.setData(Dict.PAYER_CARD_TYP_CD, resultMap.get(Dict.PAYER_CARD_TYP_CD));
		context.setData(Dict.CUST_CIF_NBR, resultMap.get(Dict.CUST_CIF_NBR));
		context.setData(Dict.PAYER_ACCT_DEPT_NBR, resultMap.get(Dict.PAYER_ACCT_DEPT_NBR));	
		context.setData(Dict.PAYER_ACCT_NAME, resultMap.get(Dict.PAYER_ACCT_NAME));	
		Map dataMap = context.getDataMap();
		dataMap.putAll(resultMap);
		context.setDataMap(dataMap);
		
	}

}
