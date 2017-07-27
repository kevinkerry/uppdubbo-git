package com.csii.upp.payment.action.end;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.payment.event.handler.NotifyMerResultEvent;
import com.csii.upp.supportor.DefaultSupportor;
import com.csii.upp.util.StringUtil;

/**
 * 异步通知商户结果
 * 
 * @author xujin
 *
 */
public class NotifyMerResultAction extends PaymentOnlineAction {
	@Override
	public void execute(Context context) throws PeException {
		InputPaymentData inputData = (InputPaymentData) context.getData(Dict.INPUT_PAYMENT_DATA);
		// 未到插入总交易明细表步骤或交易不成功可以直接返回
		if (StringUtil.isObjectEmpty(inputData)
				||!TransStatus.SUCCESS.equals(inputData.getTransstatus()) || "201611220002137".equals(inputData.getMernbr())) {
			return;
		}
		//flase:直接做支付结果通知;true:等待异步回调进行支付结果通知
		if (this.isCallBackPayResult(inputData.getPaytypcd())) {
			return;
		}
		NotifyMerResultEvent event = new NotifyMerResultEvent();
		event.setParamMap(context.getDataMap());
		event.setNotifyService(getNotifyService());
		DefaultSupportor.getEventManager().doService(event);
	}


}
