package com.csii.upp.paygate.action.mer;

import java.util.HashMap;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqMerCertUpload;
import com.csii.upp.paygate.action.PayGateAction;

/**
 * 商户证书上传
 */
public class MerCertUploadAction extends PayGateAction {
	@Override
	public void execute(Context context) throws PeException {
		Map dataMap = context.getDataMap();
		String merId = (String) dataMap.get("merId");
		String merCert = (String) dataMap.get("merCert");

		Map paramMap = new HashMap();
		paramMap.put("merId", merId);
		paramMap.put("merCert", merCert);
		paramMap.put("merCertStatus", "0");

		context.setDataMap(paramMap);

		context.setData(Dict.PLAIN, null);
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		ReqMerCertUpload reqMerCertUpload = new ReqMerCertUpload(inputData, merId, merCert, "0");
		reqMerCertUpload.setTransCode(PaymTransCode.MerCertUpload);
		Map result = this.sendPaymenTransport(reqMerCertUpload);
		if (!result.get(Dict.RESP_CODE).equals(ResponseCode.SUCCESS)) {
			context.setDataMap(result);
			context.setState(1);
			return;
		}
	}
}
