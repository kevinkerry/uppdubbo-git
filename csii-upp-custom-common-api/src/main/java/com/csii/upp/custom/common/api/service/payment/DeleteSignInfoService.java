package com.csii.upp.custom.common.api.service.payment;

import com.csii.upp.custom.common.api.data.payment.DeleteSignInfoReq;
import com.csii.upp.custom.common.api.data.payment.DeleteSignInfoResp;

public interface DeleteSignInfoService{
	DeleteSignInfoResp execute(DeleteSignInfoReq reqObj);
}
