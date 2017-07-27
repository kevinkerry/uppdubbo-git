package com.csii.upp.custom.common.api.service.fundprocess;

import com.csii.upp.custom.common.api.data.fundprocess.CVAPReq;
import com.csii.upp.custom.common.api.data.fundprocess.CVAPResp;

public interface CVAPService{
	CVAPResp execute(CVAPReq reqObj);
}
