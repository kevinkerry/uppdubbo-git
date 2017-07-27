package com.csii.upp.custom.common.api.service.payment;

import com.csii.upp.custom.common.api.data.payment.XMLimitCXReq;
import com.csii.upp.custom.common.api.data.payment.XMLimitCXResp;

public interface XMLimitCXService{
	XMLimitCXResp execute(XMLimitCXReq reqObj);
}
