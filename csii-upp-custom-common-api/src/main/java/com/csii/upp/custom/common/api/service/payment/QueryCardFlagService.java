package com.csii.upp.custom.common.api.service.payment;

import com.csii.upp.custom.common.api.data.payment.QueryCardFlagReq;
import com.csii.upp.custom.common.api.data.payment.QueryCardFlagResp;

public interface QueryCardFlagService{
	QueryCardFlagResp execute(QueryCardFlagReq reqObj);
}
