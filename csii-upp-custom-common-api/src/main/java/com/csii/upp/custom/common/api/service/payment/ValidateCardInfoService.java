package com.csii.upp.custom.common.api.service.payment;

import com.csii.upp.custom.common.api.data.payment.ValidateCardInfoReq;
import com.csii.upp.custom.common.api.data.payment.ValidateCardInfoResp;

public interface ValidateCardInfoService{
	ValidateCardInfoResp execute(ValidateCardInfoReq reqObj);
}
