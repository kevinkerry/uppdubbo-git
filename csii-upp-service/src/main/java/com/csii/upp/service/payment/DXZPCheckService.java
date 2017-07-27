package com.csii.upp.service.payment;

import com.csii.pe.core.PeException;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.dto.router.fundprocess.ReqDXZPkPayment;
import com.csii.upp.dto.router.fundprocess.RespDXZPPayment;
import com.csii.upp.service.BasePayService;

public class DXZPCheckService extends BasePayService {
	
	

	/**
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead otherEbankPayment(InputPaymentData input) throws PeException {
		RespDXZPPayment output = this.send(new ReqDXZPkPayment(input), RespDXZPPayment.class);
		if (output.isTimeout()) {
			//账号记录
			
			throw new PeException(DictErrors.TRANS_TIMEOUT);
		} else if (output.isFailure()) {
			this.formatException(output);
		}
		return output;
	}

	
}
