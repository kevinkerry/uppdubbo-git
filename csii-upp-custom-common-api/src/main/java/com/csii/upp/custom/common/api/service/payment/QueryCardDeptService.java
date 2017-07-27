package com.csii.upp.custom.common.api.service.payment;

import com.csii.upp.custom.common.api.data.payment.QueryCardDeptReq;
import com.csii.upp.custom.common.api.data.payment.QueryCardDeptResp;

public interface QueryCardDeptService{
	QueryCardDeptResp execute(QueryCardDeptReq reqObj);
}
