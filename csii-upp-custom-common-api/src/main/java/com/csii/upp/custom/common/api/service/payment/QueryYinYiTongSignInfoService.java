package com.csii.upp.custom.common.api.service.payment;

import com.csii.upp.custom.common.api.data.payment.QueryYinYiTongSignInfoReq;
import com.csii.upp.custom.common.api.data.payment.QueryYinYiTongSignInfoResp;

public interface QueryYinYiTongSignInfoService{
	QueryYinYiTongSignInfoResp execute(QueryYinYiTongSignInfoReq reqObj);
}
