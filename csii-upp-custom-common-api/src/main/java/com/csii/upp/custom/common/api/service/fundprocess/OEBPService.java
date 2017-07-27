package com.csii.upp.custom.common.api.service.fundprocess;

import com.csii.upp.custom.common.api.data.fundprocess.OEBPReq;
import com.csii.upp.custom.common.api.data.fundprocess.OEBPResp;

public interface OEBPService{
	OEBPResp execute(OEBPReq reqObj);
}
