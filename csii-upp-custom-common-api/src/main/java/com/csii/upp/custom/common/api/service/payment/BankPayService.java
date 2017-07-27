package com.csii.upp.custom.common.api.service.payment;

import com.csii.upp.custom.common.api.data.payment.BankPayReq;
import com.csii.upp.custom.common.api.data.payment.BankPayResp;

public interface BankPayService{
	BankPayResp execute(BankPayReq reqObj);
}
