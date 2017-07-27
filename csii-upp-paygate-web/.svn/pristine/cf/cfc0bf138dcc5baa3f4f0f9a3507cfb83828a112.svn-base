package com.csii.upp.paygate.action.cardpwd;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.ElecPortFlag;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.constant.ScanCodeMode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqAddOrderInfo;
import com.csii.upp.dto.router.paym.ReqCreatQrCodeByte;
import com.csii.upp.dto.router.paym.ReqPayTrans;
import com.csii.upp.dto.router.paym.ReqQueryQrCodeStatus;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

public class CardPwdPreAction extends PayGateAction {
	@Override
	public void execute(Context context) throws PeException {
		String merDate = context.getString(Dict.MER_DATE_TIME);
		context.setData(Dict.MER_DATE, DateUtil.Date_To_DateTimeFormat(
				DateUtil.DateTimeFormat_To_Date(merDate),
				DateFormatCode.DATE_FORMATTER1));
		String scanCodeFlag=context.getString(Dict.SCAN_CODE_FLAG);
		//扫码标识，被扫直接被扫方法不跳页面
		if(!StringUtil.isStringEmpty(scanCodeFlag)){
			log.info(new StringBuilder().append("订单号[").append(context.getString(Dict.ORDER_ID)).append("]").append("---维码模式---").
					append("ScanCodeFlag[").append(context.getData(Dict.SCAN_CODE_FLAG)).append("]").toString());
			if(ScanCodeMode.PASSIVE.equals(scanCodeFlag)){
				context.setState(1);
				qrCodePassivePay(context);
				return;
			}
		}
		InputPaygateData inputData=new InputPaygateData(context.getDataMap());
		Map resultMap = this.sendPaymenTransport(new ReqAddOrderInfo(inputData));
		context.setData(Dict.MERCHANT_NAME,resultMap.get(Dict.MERCHANT_NAME));
		context.setData(Dict.MER_PLATFORM_NBR,resultMap.get(Dict.MER_PLATFORM_NBR));
		
		context.setData(Dict.PAY_TYPE_CD_STR, resultMap.get(Dict.PAY_TYPE_CD_STR));
		if(StringUtil.toStringAndTrim(context.getData(Dict.MER_TRANS_LIST)).contains(ElecPortFlag.ElecPortNotify)){
			context.setData("ElecPortNotify", ElecPortFlag.ElecPortNotify);
		}
		this.valRespStatus(resultMap);
	}
	protected void qrCodePassivePay(Context context) throws PeException{
		//支付方式为它行被扫支付
		context.setData(Dict.PAY_TYP_CD, PayTypCd.OTHPASSCAN);
		//扫码方式为被扫模式
		context.setData(Dict.SCAN_CODE_MODE, ScanCodeMode.PASSIVE);
		//生成二维码并插入订单信息
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
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
		//电子口岸标识校验
		String orderNbr = StringUtil.toStringAndTrim(context.getString(Dict.ORDER_ID));
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
		Map hostMap = this.sendPaymenTransport(new ReqPayTrans(inputData));
		if (!ResponseCode.SUCCESS.equals(hostMap.get(Dict.RESP_CODE))) {
			if(log.isInfoEnabled())
			log.info(new StringBuilder().append("订单号[").append(orderNbr).append("]").append("---二维码请求支付---").
					append("RespCode[").append(hostMap.get(Dict.RESP_CODE)).append("]").
					append("RespMessage[").append(hostMap.get(Dict.RESP_MESSAGE)).append("]").toString());
			
		}
		inputData.setRespcode((String)hostMap.get(Dict.RESP_CODE));
		inputData.setRespMessage(((String)hostMap.get(Dict.RESP_MESSAGE)));
		Map respMap = this.sendPaymenTransport(new ReqCreatQrCodeByte(inputData));
		String Content = (String) respMap.get(Dict.CONTENT);
		log.info("paygate收到的加签后的报文"+Content+"***********");
		context.setDataMap(respMap);
	
		
		
	}		
				
	
}
