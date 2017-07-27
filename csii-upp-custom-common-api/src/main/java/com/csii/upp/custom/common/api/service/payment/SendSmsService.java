package com.csii.upp.custom.common.api.service.payment;

import com.csii.upp.custom.common.api.data.payment.SendSmsReq;
import com.csii.upp.custom.common.api.data.payment.SendSmsResp;

public interface SendSmsService{
	SendSmsResp execute(SendSmsReq reqObj);
}
