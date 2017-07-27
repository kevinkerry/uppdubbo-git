package com.csii.upp.payment.action.query;

import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.router.fundprocess.RespQueryQrCodeUrl;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.StringUtil;

/**
 * 查询二维码信息
 * 
 * @author wy
 * 
 */
public class QueryQrCodeUrlAction extends PaymentOnlineAction {


	@SuppressWarnings("unchecked")
	@Override
	public void execute(Context context) throws PeException {
		
		InputPaymentData inputData = new InputPaymentData(context.getDataMap());
		log.info(new StringBuilder().append("原二维码支付订单号[").append(inputData.getQrcodeordernbr()).append("]").append("支付平台流水[")
				.append("商户号[").append(inputData.getMernbr()).append("]")
				.append(inputData.getTranscode()).append("] 发送二维码查询!").toString());
		List<Map<String, String>> merSubTransMaps = (List<Map<String, String>>) context.getData(Dict.MER_TRANS_LIST);
		inputData.setPayeeAcctList(merSubTransMaps);
		RespQueryQrCodeUrl output = null;
		output = (RespQueryQrCodeUrl) this.getFDPSService().queryQrCodeUrl(inputData);
		if(!StringUtil.isStringEmpty(output.getCodeUrl())){
			context.setData(Dict.CODE_URL, output.getCodeUrl());
		}
		
		
	}
	
}
