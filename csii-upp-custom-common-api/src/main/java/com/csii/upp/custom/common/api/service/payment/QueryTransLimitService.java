package com.csii.upp.custom.common.api.service.payment;

import com.csii.upp.custom.common.api.data.payment.QueryTransLimitReq;
import com.csii.upp.custom.common.api.data.payment.QueryTransLimitResp;

public interface QueryTransLimitService{
	QueryTransLimitResp execute(QueryTransLimitReq reqObj);
}
