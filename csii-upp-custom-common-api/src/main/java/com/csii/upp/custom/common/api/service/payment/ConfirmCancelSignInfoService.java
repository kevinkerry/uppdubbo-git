package com.csii.upp.custom.common.api.service.payment;

import com.csii.upp.custom.common.api.data.payment.ConfirmCancelSignInfoReq;
import com.csii.upp.custom.common.api.data.payment.ConfirmCancelSignInfoResp;

public interface ConfirmCancelSignInfoService{
	ConfirmCancelSignInfoResp execute(ConfirmCancelSignInfoReq reqObj);
}
