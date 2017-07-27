package com.csii.upp.custom.common.api.service.payment;

import com.csii.upp.custom.common.api.data.payment.QueryCardPhoneReq;
import com.csii.upp.custom.common.api.data.payment.QueryCardPhoneResp;

public interface QueryCardPhoneService{
	QueryCardPhoneResp execute(QueryCardPhoneReq reqObj);
}
