package com.csii.upp.custom.common.api.service.payment;

import com.csii.upp.custom.common.api.data.payment.QueryQrCodeUrlReq;
import com.csii.upp.custom.common.api.data.payment.QueryQrCodeUrlResp;

public interface QueryQrCodeUrlService{
	QueryQrCodeUrlResp execute(QueryQrCodeUrlReq reqObj);
}
