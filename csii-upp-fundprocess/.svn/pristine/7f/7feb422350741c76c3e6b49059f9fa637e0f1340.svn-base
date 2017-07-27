package com.csii.upp.fundprocess.action.event.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.event.EventHandler;
/**
 * 异步通知payment支付结果
 * @author zbl
 *
 */
public class NotifyPaymentResultEventHandler  implements EventHandler<NotifyPaymentResultEvent>{
	protected static Log log = LogFactory.getLog(NotifyPaymentResultEventHandler.class);
	@Override
	public void handler(NotifyPaymentResultEvent event) {
		InputFundData inputData2Payment = event.getInputData2Payment();
		try {
			event.getPaymService().notifyPayResult(inputData2Payment);
		} catch (Exception e) {
			log.error("NotifyPaymentResultEventHandler-->" + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public Class<NotifyPaymentResultEvent> getAcceptedEventType() {
		return NotifyPaymentResultEvent.class;
	}

}
