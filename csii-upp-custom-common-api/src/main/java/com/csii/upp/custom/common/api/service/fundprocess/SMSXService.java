package com.csii.upp.custom.common.api.service.fundprocess;

import com.csii.upp.custom.common.api.data.fundprocess.SMSXReq;
import com.csii.upp.custom.common.api.data.fundprocess.SMSXResp;

public interface SMSXService{
	SMSXResp execute(SMSXReq reqObj);
}
