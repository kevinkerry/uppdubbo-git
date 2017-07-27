package com.csii.upp.custom.common.api.service.payment;

import com.csii.upp.custom.common.api.data.payment.QueryIntegralReq;
import com.csii.upp.custom.common.api.data.payment.QueryIntegralResp;

public interface QueryIntegralService{
	QueryIntegralResp execute(QueryIntegralReq reqObj);
}
