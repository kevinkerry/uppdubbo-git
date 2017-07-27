package com.csii.upp.custom.common.api.service.payment;

import com.csii.upp.custom.common.api.data.payment.ValidationInfoReq;
import com.csii.upp.custom.common.api.data.payment.ValidationInfoResp;

public interface ValidationInfoService{
	ValidationInfoResp execute(ValidationInfoReq reqObj);
}
