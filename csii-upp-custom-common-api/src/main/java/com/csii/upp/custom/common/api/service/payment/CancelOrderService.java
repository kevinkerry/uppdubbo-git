package com.csii.upp.custom.common.api.service.payment;

import com.csii.upp.custom.common.api.data.payment.CancelOrderReq;
import com.csii.upp.custom.common.api.data.payment.CancelOrderResp;

public interface CancelOrderService{
	CancelOrderResp execute(CancelOrderReq reqObj);
}
