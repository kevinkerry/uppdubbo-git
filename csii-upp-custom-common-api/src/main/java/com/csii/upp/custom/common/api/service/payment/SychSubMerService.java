package com.csii.upp.custom.common.api.service.payment;

import com.csii.upp.custom.common.api.data.payment.SychSubMerReq;
import com.csii.upp.custom.common.api.data.payment.SychSubMerResp;

public interface SychSubMerService{
	SychSubMerResp execute(SychSubMerReq reqObj);
}
