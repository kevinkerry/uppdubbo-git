package com.csii.upp.custom.common.api.service.payment;

import com.csii.upp.custom.common.api.data.payment.WithdrawTransReq;
import com.csii.upp.custom.common.api.data.payment.WithdrawTransResp;

public interface WithdrawTransService{
	WithdrawTransResp execute(WithdrawTransReq reqObj);
}
