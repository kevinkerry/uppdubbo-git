package com.csii.upp.paygate.action.qrcodepay;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.constant.ScanCodeMode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqQueryCodeUrl;
import com.csii.upp.dto.router.paym.ReqQueryQrCodeStatus;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.StringUtil;
/**
 * 二维码支付
 * 
 * @author WY
 *
 */
public class QRCodePayAction extends PayGateAction {
	
	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Context context) throws PeException {
		
		String orderNbr = StringUtil.toStringAndTrim(context.getString(Dict.ORDER_ID));
		//支付方式为扫码支付
		context.setData(Dict.PAY_TYP_CD, PayTypCd.OTHACTSCAN);
		//扫码方式为主扫模式
		context.setData(Dict.SCAN_CODE_MODE, ScanCodeMode.ACTIVE);
		context.setData(Dict.MER_NAME, context.getString(Dict.MERCHANT_NAME));
		
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		
		//查询确保该笔订单只生成一次二位码流水 
		Map queryResultMap = this.sendPaymenTransport(new ReqQueryQrCodeStatus(inputData));
		if (!ResponseCode.SUCCESS.equals(queryResultMap.get(Dict.RESP_CODE))) {
			context.setDataMap(queryResultMap);
			context.setData("json", context.getDataMap());
			if(log.isDebugEnabled()){
				log.info(new StringBuilder().append("订单号[").append(orderNbr).append("]").append("---二维码查询订单---").
						append("RespCode[").append(queryResultMap.get(Dict.RESP_CODE)).append("]").
						append("RespMessage[").append(queryResultMap.get(Dict.RESP_MESSAGE)).append("]").toString());
			}
			return;
		}
		context.setData(Dict.ALIPAYPROXYSTATUS, queryResultMap.get(Dict.ALIPAYPROXYSTATUS));
		context.setData(Dict.WECHATPROXYSTATUS, queryResultMap.get(Dict.WECHATPROXYSTATUS));
		inputData.setCodetypcd((String)queryResultMap.get(Dict.CODE_TYP_CD));
		
		//二维码已经生成过且没有支付完成，用原二维码订单号
		if(!StringUtil.isStringEmpty((String)queryResultMap.get(Dict.QRCODEORDERNBR))){
			inputData.setQrcodeordernbr((String)queryResultMap.get(Dict.QRCODEORDERNBR));
			inputData.setMerName((String)queryResultMap.get(Dict.MER_NAME));
			inputData.setSubMerchantId((String)queryResultMap.get(Dict.SUB_MERCHANT_ID));
			inputData.setThirdMerNbr((String)queryResultMap.get(Dict.THIRD_MER_NBR));
			Map codeUrlMap = this.sendPaymenTransport(new ReqQueryCodeUrl(inputData));
			if (ResponseCode.SUCCESS.equals(codeUrlMap.get(Dict.RESP_CODE))) {
				context.setDataMap(codeUrlMap);
				context.setData("json", context.getDataMap());
			}
			return;
		}else{
			Map codeUrlMap = this.sendPaymenTransport(new ReqQueryCodeUrl(inputData));
			if (ResponseCode.SUCCESS.equals(codeUrlMap.get(Dict.RESP_CODE))) {
				String url =(String) codeUrlMap.get(Dict.CODE_URL);
				url.replaceAll("&amp;", "&");
				codeUrlMap.put(Dict.CODE_URL, url);
				context.setDataMap(codeUrlMap);
				context.setData("json", context.getDataMap());
				
			}
		}
		 
//		Map hostMap = this.sendPaymenTransport(new ReqPayTrans(inputData));
//		if (!ResponseCode.SUCCESS.equals(hostMap.get(Dict.RESP_CODE))) {
//			if(log.isInfoEnabled())
//			log.info(new StringBuilder().append("订单号[").append(orderNbr).append("]").append("---二维码请求支付---").
//					append("RespCode[").append(hostMap.get(Dict.RESP_CODE)).append("]").
//					append("RespMessage[").append(hostMap.get(Dict.RESP_MESSAGE)).append("]").toString());
//			return;
//		}
//		
//		context.setDataMap(hostMap);
//		context.setData("json", context.getDataMap());
		
		}
}