package com.csii.upp.paygate.action.common;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqCreateSubMerchantId;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.StringUtil;

/**
 * 确认支付交易
 * 
 * @author gaoqi
 * 
 */
public class CreateSubMerchantIdAction extends PayGateAction {
	
	@Override
	public void execute(Context context) throws PeException {
		context.setData(Dict.TRANS_ID,"CTSM");
		//获取外部电商平台一级商户编号
		context.setData(Dict.MERCHANT_ID,StringUtil.toStringAndTrim(context.getData(Dict.MERCHANT_ID))); 
		//获取外部电商二级商户编号
		context.setData(Dict.OUT_SUB_MERCHANT_ID,StringUtil.toStringAndTrim(context.getString(Dict.OUT_SUB_MERCHANT_ID)));
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		Map resultMap = this.sendPaymenTransport(new ReqCreateSubMerchantId(inputData));
		String Content = (String) resultMap.get(Dict.CONTENT);
		log.info("paygate收到的加签后的报文"+Content+"***********");
		context.setDataMap(resultMap);
		
	}
}