package com.csii.upp.payment.action.end;

import java.math.BigDecimal;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.ElecPortFlag;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.extend.SubTransData;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.payment.event.handler.NotifyDZKNResultEvent;
import com.csii.upp.supportor.DefaultSupportor;
import com.csii.upp.util.StringUtil;

/**
 * 电子口岸结果通知
 * 
 * @author xujin
 *
 */
public class NotifyDZKNResultAction extends PaymentOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		InputPaymentData inputData = (InputPaymentData) context.getData(Dict.INPUT_PAYMENT_DATA);
		// 未到插入总交易明细表步骤或交易不成功可以直接返回
		if (StringUtil.isObjectEmpty(inputData)
				||!TransStatus.SUCCESS.equals(inputData.getTransstatus())) {
			return;
		}
		//flase:直接做电子口岸通知;true:等待异步回调进行电子口岸
		if (this.isCallBackPayResult(inputData.getPaytypcd())) {
			return;
		}
		
		BigDecimal transAmt = new BigDecimal(0);
		for (SubTransData subTrans : inputData.getOnlineSubTransList()) {
			if (ElecPortFlag.ElecPortNotify.equals(subTrans.getElecportflag()))
				transAmt=transAmt.add(subTrans.getTransamt());
		}	
		if(transAmt.compareTo(new BigDecimal(0))==0){
			return;
		}
		context.setData(Dict.TOTAL_TRANS_AMT, transAmt);
		NotifyDZKNResultEvent event = new NotifyDZKNResultEvent();
		event.setScheduleNotify(false);
		event.setParamMap(context.getDataMap());
		event.setNotifyService(this.getNotifyService());
		DefaultSupportor.getEventManager().doService(event);
	}
}
