package com.csii.upp.custom.common.api.service.payment;

import com.csii.upp.custom.common.api.data.payment.QuerySingleOnlineTransReq;
import com.csii.upp.custom.common.api.data.payment.QuerySingleOnlineTransResp;

public interface QuerySingleOnlineTransService{
	QuerySingleOnlineTransResp execute(QuerySingleOnlineTransReq reqObj);
}
