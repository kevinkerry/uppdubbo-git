package com.csii.upp.custom.common.api.service.payment;

import com.csii.upp.custom.common.api.data.payment.AddSignInfoReq;
import com.csii.upp.custom.common.api.data.payment.AddSignInfoResp;

public interface AddSignInfoService{
	AddSignInfoResp execute(AddSignInfoReq reqObj);
}
