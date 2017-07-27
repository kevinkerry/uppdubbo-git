package com.csii.upp.custom.common.api.service.payment;

import com.csii.upp.custom.common.api.data.payment.BatchTransferPaymentReq;
import com.csii.upp.custom.common.api.data.payment.BatchTransferPaymentResp;

public interface BatchTransferPaymentService{
	BatchTransferPaymentResp execute(BatchTransferPaymentReq reqObj);
}
