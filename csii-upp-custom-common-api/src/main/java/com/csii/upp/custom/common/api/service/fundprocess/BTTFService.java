package com.csii.upp.custom.common.api.service.fundprocess;

import com.csii.upp.custom.common.api.data.fundprocess.BTTFReq;
import com.csii.upp.custom.common.api.data.fundprocess.BTTFResp;

public interface BTTFService{
	BTTFResp execute(BTTFReq reqObj);
}
