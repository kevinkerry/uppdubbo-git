package com.csii.upp.custom.common.api.service.payment;

import com.csii.upp.custom.common.api.data.payment.QueryPayReq;
import com.csii.upp.custom.common.api.data.payment.QueryPayResp;

public interface QueryPayService{
	QueryPayResp execute(QueryPayReq reqObj);
}
