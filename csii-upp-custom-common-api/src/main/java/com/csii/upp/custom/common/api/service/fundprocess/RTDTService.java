package com.csii.upp.custom.common.api.service.fundprocess;

import com.csii.upp.custom.common.api.data.fundprocess.RTDTReq;
import com.csii.upp.custom.common.api.data.fundprocess.RTDTResp;

public interface RTDTService{
	RTDTResp execute(RTDTReq reqObj);
}
