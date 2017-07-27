package com.csii.upp.paygate.action.otherquickpay;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.ChannelNbr;
import com.csii.upp.constant.PayStatus;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqQueryOrderStatus;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.StringUtil;

public class QueryQuickPayOrderStatusAction extends PayGateAction {
	protected static Log log = LogFactory.getLog(QueryQuickPayOrderStatusAction.class);

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Context context) throws PeException {
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		inputData.setMerName(StringUtil.toStringAndTrim(context.getSessionAttribute("MerchantName")));
		log.info("QueryQuickPayOrderStatusAction-->MerName=" + context.getSessionAttribute("MerchantName"));
		
		inputData.setMerseqnbr(StringUtil.toStringAndTrim(context.getSessionAttribute("MerSeqNo")));
		log.info("QueryQuickPayOrderStatusAction-->MerSeqNo=" + context.getSessionAttribute("MerSeqNo"));
		
		inputData.setMernbr(StringUtil.toStringAndTrim(context.getSessionAttribute("MerchantId")));
		log.info("QueryQuickPayOrderStatusAction-->MerchantId=" + context.getSessionAttribute("MerchantId"));
		
		if (!StringUtil.isStringEmpty(inputData.getMernbr()) && !StringUtil.isStringEmpty(inputData.getMerseqnbr())) {
			String merurl1 = inputData.getMerurl1();
			if(StringUtil.isStringEmpty(merurl1)){
				context.setData(Dict.MER_URL1, context.getSessionAttribute("MerURL1"));
				log.info("QueryQuickPayOrderStatusAction-->MER_URL1=" + context.getSessionAttribute("MerURL1"));
			}
			inputData.setSignature(null);
			Map resultMap = this.sendPaymenTransport(new ReqQueryOrderStatus(inputData));
			int i = 0;
			while (i < 4
					&& PayStatus.PAY_STATUS_HAND.equals(resultMap
							.get(Dict.PAY_STATUS))) {
				try {
					Thread.sleep(100 * (int) Math.pow(2, i));
				} catch (Exception e) {
					throw new PeException(e);
				}
				resultMap = this.sendPaymenTransport(new ReqQueryOrderStatus(
						inputData));
			}
			context.setData(Dict.PAY_STATUS, resultMap.get(Dict.PAY_STATUS));
			String channelNbr = context.getString(Dict.CHANNEL_NBR);
			if (ChannelNbr.PC.equals(channelNbr)) {
				if (PayStatus.PAY_STATUS_OK.equals(resultMap.get(Dict.PAY_STATUS))) {
					context.setState(0);
				} else if (PayStatus.PAY_STATUS_RECEIVED.equals(resultMap.get(Dict.PAY_STATUS))) {
					context.setState(3);
				} else {
					context.setState(99999);
				}
			} else if (ChannelNbr.WAP.equals(channelNbr)) {
				if (PayStatus.PAY_STATUS_OK.equals(resultMap.get(Dict.PAY_STATUS))) {
					context.setState(1);
				} else if (PayStatus.PAY_STATUS_RECEIVED.equals(resultMap.get(Dict.PAY_STATUS))) {
					context.setState(4);
				} else {
					context.setState(2);
				}
			}else {
				context.setState(99999);
			}
		}else {
			context.setState(99999);
		}
	}

}
