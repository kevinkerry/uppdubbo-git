package com.csii.upp.custom.common.api.service.payment;

import com.csii.upp.custom.common.api.data.payment.PayTransReq;
import com.csii.upp.custom.common.api.data.payment.PayTransResp;

public interface PayTransService{
	PayTransResp execute(PayTransReq reqObj);
}
