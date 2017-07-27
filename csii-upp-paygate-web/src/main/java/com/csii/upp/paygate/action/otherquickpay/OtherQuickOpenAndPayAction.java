/**
 * 
 */
package com.csii.upp.paygate.action.otherquickpay;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.pe.core.SessionUpdatableContext;
import com.csii.upp.constant.ChannelNbr;
import com.csii.upp.constant.PayStatus;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.constant.TransId;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqPayTrans;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.StringUtil;

/**
 * @author gaoqi 跨行智能支付
 *
 */
public class OtherQuickOpenAndPayAction extends PayGateAction {

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Context context) throws PeException {
		context.setData(Dict.PAY_TYP_CD, PayTypCd.INTEL);
		context.setData(Dict.TRANS_ID, TransId.OQP1);
		String channelNbr = context.getString(Dict.CHANNEL_NBR);
		if (ChannelNbr.PC.equals(channelNbr)) {
			validateTimeStampToken(context);
		} else if (ChannelNbr.WAP.equals(channelNbr)) {
			if(!validateTimeStampTokenMobile(context)){
				context.setState(2);
				return;
			}
		}
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		inputData.setFrontCallBackUrl("CLPA.do");
		if (ChannelNbr.WAP.equals(inputData.getChannelnbr()) ||
				ChannelNbr.PC.equals(inputData.getChannelnbr())) {
			((SessionUpdatableContext)context).setSessionAttribute("MerURL1", inputData.getMerurl1());
			((SessionUpdatableContext)context).setSessionAttribute("MerchantName", context.getString(Dict.MERCHANT_NAME));
			((SessionUpdatableContext)context).setSessionAttribute("MerSeqNo", inputData.getMerseqnbr());
			((SessionUpdatableContext)context).setSessionAttribute("MerchantId", inputData.getMernbr());

			if(ChannelNbr.WAP.equals(inputData.getChannelnbr()))
				inputData.setFrontCallBackUrl("qQPOS.do?ChannelNbr=02" + "&OrderId=" + inputData.getOrderNbr() 
					+ "&TransAmt=" + inputData.getTransamt());
		}

		Map resultMap = this.sendPaymenTransport(new ReqPayTrans(inputData));
		if (ResponseCode.SUCCESS.equals(resultMap.get(Dict.RESP_CODE))) {
			context.setState(0);
			String returnFrom = StringUtil.parseObjectToString(resultMap.get(Dict.RETURN_FORM));
			context.setData(Dict.RETURN_FORM, returnFrom);
		} else {
			context.setDataMap(resultMap);
			context.setData(Dict.PAY_STATUS, PayStatus.PAY_STATUS_FAIL);
			if (ChannelNbr.WAP.equals(inputData.getChannelnbr())) {
				context.setState(2);
			}else {
				context.setState(99999);
			}
		}
	}
}
