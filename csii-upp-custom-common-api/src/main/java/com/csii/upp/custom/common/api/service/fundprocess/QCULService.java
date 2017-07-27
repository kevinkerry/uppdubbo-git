package com.csii.upp.custom.common.api.service.fundprocess;

import com.csii.upp.custom.common.api.data.fundprocess.QCULReq;
import com.csii.upp.custom.common.api.data.fundprocess.QCULResp;

public interface QCULService{
	QCULResp execute(QCULReq reqObj);
}
