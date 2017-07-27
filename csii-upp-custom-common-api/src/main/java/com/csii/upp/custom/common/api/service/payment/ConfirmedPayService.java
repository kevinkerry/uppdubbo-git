package com.csii.upp.custom.common.api.service.payment;

import com.csii.upp.custom.common.api.data.payment.ConfirmedPayReq;
import com.csii.upp.custom.common.api.data.payment.ConfirmedPayResp;

public interface ConfirmedPayService{
	ConfirmedPayResp execute(ConfirmedPayReq reqObj);
}
