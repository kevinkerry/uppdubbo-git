package com.csii.upp.custom.common.api.service.payment;

import com.csii.upp.custom.common.api.data.payment.ReturnTransReq;
import com.csii.upp.custom.common.api.data.payment.ReturnTransResp;

public interface ReturnTransService{
	ReturnTransResp execute(ReturnTransReq reqObj);
}
