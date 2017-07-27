package com.csii.upp.custom.common.api.service.payment;

import com.csii.upp.custom.common.api.data.payment.SendMessagePaymentReq;
import com.csii.upp.custom.common.api.data.payment.SendMessagePaymentResp;

public interface SendMessagePaymentService{
	SendMessagePaymentResp execute(SendMessagePaymentReq reqObj);
}
