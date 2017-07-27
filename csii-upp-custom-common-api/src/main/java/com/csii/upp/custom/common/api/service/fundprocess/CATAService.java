package com.csii.upp.custom.common.api.service.fundprocess;

import com.csii.upp.custom.common.api.data.fundprocess.CATAReq;
import com.csii.upp.custom.common.api.data.fundprocess.CATAResp;

public interface CATAService{
	CATAResp execute(CATAReq reqObj);
}
