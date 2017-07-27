package com.csii.upp.custom.common.api.service.fundprocess;

import com.csii.upp.custom.common.api.data.fundprocess.UPMIReq;
import com.csii.upp.custom.common.api.data.fundprocess.UPMIResp;

public interface UPMIService{
	UPMIResp execute(UPMIReq reqObj);
}
