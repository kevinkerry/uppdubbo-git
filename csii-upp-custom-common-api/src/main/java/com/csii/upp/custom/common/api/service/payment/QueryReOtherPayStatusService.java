package com.csii.upp.custom.common.api.service.payment;

import com.csii.upp.custom.common.api.data.payment.QueryReOtherPayStatusReq;
import com.csii.upp.custom.common.api.data.payment.QueryReOtherPayStatusResp;

public interface QueryReOtherPayStatusService{
	QueryReOtherPayStatusResp execute(QueryReOtherPayStatusReq reqObj);
}
