package com.csii.upp.custom.common.api.service.payment;

import com.csii.upp.custom.common.api.data.payment.QueryAuthReq;
import com.csii.upp.custom.common.api.data.payment.QueryAuthResp;

public interface QueryAuthService{
	QueryAuthResp execute(QueryAuthReq reqObj);
}
