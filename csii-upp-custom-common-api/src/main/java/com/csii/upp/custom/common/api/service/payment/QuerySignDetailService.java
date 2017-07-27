package com.csii.upp.custom.common.api.service.payment;

import com.csii.upp.custom.common.api.data.payment.QuerySignDetailReq;
import com.csii.upp.custom.common.api.data.payment.QuerySignDetailResp;

public interface QuerySignDetailService{
	QuerySignDetailResp execute(QuerySignDetailReq reqObj);
}
