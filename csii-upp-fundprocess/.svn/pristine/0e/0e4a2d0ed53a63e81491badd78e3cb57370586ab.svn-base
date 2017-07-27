package com.csii.upp.fundprocess.action.event.handler;

import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.event.Event;
import com.csii.upp.service.fundprocess.PaymService;
/**
 * 异步通知payment支付结果
 * @author zbl
 *
 */
public class NotifyPaymentResultEvent extends Event {
	private InputFundData inputData2Payment;
	private PaymService paymService;

	public InputFundData getInputData2Payment() {
		return inputData2Payment;
	}

	public void setInputData2Payment(InputFundData inputData2Payment) {
		this.inputData2Payment = inputData2Payment;
	}

	public PaymService getPaymService() {
		return paymService;
	}

	public void setPaymService(PaymService paymService) {
		this.paymService = paymService;
	}
}
