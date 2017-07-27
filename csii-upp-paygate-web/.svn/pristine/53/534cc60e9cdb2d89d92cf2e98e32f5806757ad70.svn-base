/**
 * 
 */
package com.csii.upp.paygate.action.otherquickpay;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.ChannelNbr;
import com.csii.upp.constant.PayStatus;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.constant.TransId;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqPayTrans;
import com.csii.upp.dto.router.paym.ReqQueryOrderStatus;
import com.csii.upp.paygate.action.PayGateAction;

/**
 * @author gaoqi 跨行智能支付
 *
 */
public class OtherQuickPayAction extends PayGateAction {

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
		Map resultMap = this.sendPaymenTransport(new ReqPayTrans(inputData));
		if (ResponseCode.SUCCESS.equals(resultMap.get(Dict.RESP_CODE))) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				log.equals(e.getMessage());
			}
			Map queryResultMap = this.sendPaymenTransport(new ReqQueryOrderStatus(inputData));
			if(PayStatus.PAY_STATUS_HAND.equals(queryResultMap.get(Dict.PAY_STATUS))){
				try {
					Thread.sleep(2000);
				} catch (Exception e) {
					throw new PeException(e);
				}
				queryResultMap = this.sendPaymenTransport(new ReqQueryOrderStatus(inputData));
			}
			context.setData(Dict.PAY_STATUS, queryResultMap.get(Dict.PAY_STATUS));
			
			if (ChannelNbr.PC.equals(channelNbr)) {
				if (PayStatus.PAY_STATUS_OK.equals(queryResultMap.get(Dict.PAY_STATUS))) {
					context.setState(0);
				} else if (PayStatus.PAY_STATUS_RECEIVED.equals(queryResultMap.get(Dict.PAY_STATUS))) {
					context.setState(3);
				} else {
					context.setState(99999);
				}
			} else if (ChannelNbr.WAP.equals(channelNbr)) {
				if (PayStatus.PAY_STATUS_OK.equals(queryResultMap.get(Dict.PAY_STATUS))) {
					context.setState(1);
				} else if (PayStatus.PAY_STATUS_RECEIVED.equals(queryResultMap.get(Dict.PAY_STATUS))) {
					context.setState(4);
				} else {
					context.setState(2);
				}
			}
		} else {
			context.setDataMap(resultMap);
			context.setData(Dict.PAY_STATUS, PayStatus.PAY_STATUS_FAIL);
			if (ChannelNbr.PC.equals(channelNbr)) {
				context.setState(99999);
			}else if (ChannelNbr.WAP.equals(channelNbr)) {
				context.setState(2);
			}
		}
	}
}
