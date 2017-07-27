package com.csii.upp.paygate.action.wap;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.AppointedPayType;
import com.csii.upp.constant.ChannelNbr;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.ElecPortFlag;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqAddOrderInfo;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 手机wap支付
 * 
 * @author gaoqi
 * 
 */
public  class WapIndexAction extends PayGateAction {

	@Override
	public void execute(Context context) throws PeException {
		context.setData(Dict.CHANNEL_NBR,ChannelNbr.WAP);
		String merDate = context.getString(Dict.MER_DATE_TIME);
		context.setData(Dict.MER_DATE, DateUtil.Date_To_DateTimeFormat(
				DateUtil.DateTimeFormat_To_Date(merDate),
				DateFormatCode.DATE_FORMATTER1));
		context.setData(Dict.PAYER_ACCT_NBR, context.getString(Dict.APPOINTED_PAY_ACCT_NO));
		context.setData(Dict.PAYER_PHONE_NO, context.getString(Dict.APPOINTED_MOBILE_NO));
		context.setData(Dict.MER_NBR, context.getString(Dict.MERCHANT_ID));
		context.setData(Dict.MOBILE_NO, context.getString(Dict.MOBILE_NO));
		context.setData(Dict.APPOINTED_PAY_TYPE, context.getString(Dict.APPOINTED_PAY_TYPE));
		InputPaygateData inputData=new InputPaygateData(context.getDataMap());
		Map resultMap = this.sendPaymenTransport(new ReqAddOrderInfo(inputData));
		String respCode = (String) resultMap.get(Dict.RESP_CODE);
		if (!ResponseCode.SUCCESS.equals(respCode)) {
			Map dataMap = context.getDataMap();
	        dataMap.putAll(resultMap);
			context.setDataMap(resultMap);
			context.setState(10);
			return;
		}
		context.setData(Dict.PAY_TYPE_CD_STR, resultMap.get(Dict.PAY_TYPE_CD_STR));
		if(StringUtil.toStringAndTrim(context.getData(Dict.MER_TRANS_LIST)).contains(ElecPortFlag.ElecPortNotify)){
			context.setData("ElecPortNotify", ElecPortFlag.ElecPortNotify);
		}
		context.setData(Dict.MERCHANT_NAME,resultMap.get(Dict.MERCHANT_NAME));
		this.valRespStatus(resultMap);
		String appointedPayType = context.getString(Dict.APPOINTED_PAY_TYPE);
		if(!StringUtil.isStringEmpty(appointedPayType)){
			if(appointedPayType.equals(AppointedPayType.appointedPayTypeM)){
				
			}else if(appointedPayType.equals(AppointedPayType.appointedPayTypeC)){
				
				/*
				 * 组装查询卡信息报文
				 */				
				context.setData(Dict.PAY_TYP_CD, PayTypCd.CARDPWD);
				context.setState(1);
				/*
				 * 查询卡信息
				 */
               
			}else if(appointedPayType.equals(AppointedPayType.appointedPayTypeF)){
				context.setData(Dict.PAY_TYP_CD, PayTypCd.FOSION);
				context.setState(2);
			}else{
				context.setState(10);
				throw new PeException(DictErrors.PAY_TYP_NOT_EXIST);
			}
			
		}	
	}	
}
