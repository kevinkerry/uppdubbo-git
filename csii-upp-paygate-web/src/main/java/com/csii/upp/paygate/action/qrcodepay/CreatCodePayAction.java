package com.csii.upp.paygate.action.qrcodepay;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.ElecPortFlag;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.constant.ScanCodeMode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqAddOrderInfo;
import com.csii.upp.dto.router.paym.ReqCreatQrCodeByte;
import com.csii.upp.dto.router.paym.ReqPayTrans;
import com.csii.upp.dto.router.paym.ReqQueryCodeUrl;
import com.csii.upp.dto.router.paym.ReqQueryQrCodeStatus;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.StringUtil;
/**
 * 生成二维码
 * 
 * @author WY
 *
 */
public class CreatCodePayAction extends PayGateAction {
	
	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Context context) throws PeException {
		String orderNbr = StringUtil.toStringAndTrim(context.getString(Dict.ORDER_ID));
		//支付方式为扫码支付
		context.setData(Dict.PAY_TYP_CD, PayTypCd.OTHACTSCAN);
		//扫码方式为主扫模式
		context.setData(Dict.SCAN_CODE_MODE, ScanCodeMode.ACTIVE);
		
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		//生成二维码并插入订单信息
		Map addOrderInfoMap = this.sendPaymenTransport(new ReqAddOrderInfo(inputData));
		String respCode=StringUtil.parseObjectToString(addOrderInfoMap.get(Dict.RESP_CODE));
		if(!ResponseCode.SUCCESS.equals(respCode)){
			//失败返回加签报文
			inputData.setRespcode((String)addOrderInfoMap.get(Dict.RESP_CODE));
			inputData.setRespMessage(((String)addOrderInfoMap.get(Dict.RESP_MESSAGE)));
			Map respMap = this.sendPaymenTransport(new ReqCreatQrCodeByte(inputData));
			String Content = (String) respMap.get(Dict.CONTENT);
			log.info("paygate收到的加签后的报文"+Content+"***********");
			context.setDataMap(respMap);
			return;
		}
		//电子口岸校验
		if(StringUtil.toStringAndTrim(context.getData(Dict.MER_TRANS_LIST)).contains(ElecPortFlag.ElecPortNotify)){
			log.info(new StringBuilder().append("订单号[").append(orderNbr).append("]").append("---扫码支付不支持跨境支付---"));
			//失败返回加签报文
			inputData.setRespcode("100000");
			inputData.setRespMessage("涉及跨境商品不支持二维码支付");
			Map respMap = this.sendPaymenTransport(new ReqCreatQrCodeByte(inputData));
			String Content = (String) respMap.get(Dict.CONTENT);
			log.info("paygate收到的加签后的报文"+Content+"***********");
			context.setDataMap(respMap);
			return;
		}
		//查询确保该笔订单只生成一次二位码流水 
		Map queryResultMap = this.sendPaymenTransport(new ReqQueryQrCodeStatus(inputData));
		if (!ResponseCode.SUCCESS.equals(queryResultMap.get(Dict.RESP_CODE))) {
			if(log.isDebugEnabled()){
				log.info(new StringBuilder().append("订单号[").append(orderNbr).append("]").append("---二维码查询订单---").
						append("RespCode[").append(queryResultMap.get(Dict.RESP_CODE)).append("]").
						append("RespMessage[").append(queryResultMap.get(Dict.RESP_MESSAGE)).append("]").toString());
			}
			//失败返回加签报文
			inputData.setRespcode((String)queryResultMap.get(Dict.RESP_CODE));
			inputData.setRespMessage(((String)queryResultMap.get(Dict.RESP_MESSAGE)));
			Map respMap = this.sendPaymenTransport(new ReqCreatQrCodeByte(inputData));
			String Content = (String) respMap.get(Dict.CONTENT);
			log.info("paygate收到的加签后的报文"+Content+"***********");
			context.setDataMap(respMap);
			return;
		}
		inputData.setCodetypcd((String)queryResultMap.get(Dict.CODE_TYP_CD));
		//二维码已经生成过且没有支付完成，用原二维码订单号
		if(!StringUtil.isStringEmpty((String)queryResultMap.get(Dict.QRCODEORDERNBR))){
			inputData.setQrcodeordernbr((String)queryResultMap.get(Dict.QRCODEORDERNBR));
			inputData.setMerName((String)queryResultMap.get(Dict.MER_NAME));
			inputData.setSubMerchantId((String)queryResultMap.get(Dict.SUB_MERCHANT_ID));
			inputData.setThirdMerNbr((String)queryResultMap.get(Dict.THIRD_MER_NBR));
			Map codeUrlMap = this.sendPaymenTransport(new ReqQueryCodeUrl(inputData));
			if (ResponseCode.SUCCESS.equals(codeUrlMap.get(Dict.RESP_CODE))) {
				context.setData(Dict.CODE_URL,codeUrlMap.get(Dict.CODE_URL));
			}else{
				inputData.setRespcode((String)codeUrlMap.get(Dict.RESP_CODE));
				inputData.setRespMessage(((String)codeUrlMap.get(Dict.RESP_MESSAGE)));
				Map respMap = this.sendPaymenTransport(new ReqCreatQrCodeByte(inputData));
				String Content = (String) respMap.get(Dict.CONTENT);
				log.info("paygate收到的加签后的报文"+Content+"***********");
				context.setDataMap(respMap);
				return;
			}
			
		}else{
			Map hostMap = this.sendPaymenTransport(new ReqPayTrans(inputData));
			if (!ResponseCode.SUCCESS.equals(hostMap.get(Dict.RESP_CODE))) {
				if(log.isInfoEnabled())
				log.info(new StringBuilder().append("订单号[").append(orderNbr).append("]").append("---二维码请求支付---").
						append("RespCode[").append(hostMap.get(Dict.RESP_CODE)).append("]").
						append("RespMessage[").append(hostMap.get(Dict.RESP_MESSAGE)).append("]").toString());
				//失败返回加签报文
				inputData.setRespcode((String)hostMap.get(Dict.RESP_CODE));
				inputData.setRespMessage(((String)hostMap.get(Dict.RESP_MESSAGE)));
				Map respMap = this.sendPaymenTransport(new ReqCreatQrCodeByte(inputData));
				String Content = (String) respMap.get(Dict.CONTENT);
				log.info("paygate收到的加签后的报文"+Content+"***********");
				context.setDataMap(respMap);
				return;
			}
			context.setData(Dict.CODE_URL,hostMap.get(Dict.CODE_URL));
		}
		 
		//生成二维码后生成图像字节请求payment给商户
		if(!StringUtil.isObjectEmpty(context.getData((Dict.CODE_URL)))){
			String codeStreamByte=this.createCodeByte((String)context.getData((Dict.CODE_URL)));
			//inputData.setCodeStreamByte(codeStreamByte);
			inputData.setCodeurl((String)context.getData(Dict.CODE_URL));
			Map codeStreamtMap = this.sendPaymenTransport(new ReqCreatQrCodeByte(inputData));
			inputData.setRespcode((String)codeStreamtMap.get(Dict.RESP_CODE));
			inputData.setRespMessage(((String)codeStreamtMap.get(Dict.RESP_MESSAGE)));
			Map respMap = this.sendPaymenTransport(new ReqCreatQrCodeByte(inputData));
			String Content = (String) respMap.get(Dict.CONTENT);
			log.info("paygate收到的加签后的报文"+Content+"***********");
			context.setDataMap(respMap);
		
		}
		
		
	}
}