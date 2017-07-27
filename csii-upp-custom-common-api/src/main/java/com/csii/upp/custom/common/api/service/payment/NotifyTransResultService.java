package com.csii.upp.custom.common.api.service.payment;

import com.csii.upp.custom.common.api.data.payment.NotifyTransResultReq;
import com.csii.upp.custom.common.api.data.payment.NotifyTransResultResp;

public interface NotifyTransResultService{
	NotifyTransResultResp execute(NotifyTransResultReq reqObj);
}
