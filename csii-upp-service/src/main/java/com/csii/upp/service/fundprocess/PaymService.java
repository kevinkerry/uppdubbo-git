/**
 * 
 */
package com.csii.upp.service.fundprocess;

import com.csii.pe.core.PeException;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.dto.router.paym.ReqNotifyPayResult;
import com.csii.upp.dto.router.paym.RespNotifyPayResult;
import com.csii.upp.service.BasePayService;

/**
 * @author lixinyoui
 * 
 *
 */
public class PaymService extends BasePayService {

	/**
	 * 支付结果通知payment
	 * 
	 * @return
	 * @throws PeException
	 */
	public RespSysHead notifyPayResult(InputFundData inputData) throws PeException {
		RespNotifyPayResult output = this.send(new ReqNotifyPayResult(inputData), 
				RespNotifyPayResult.class);
		return output;
	}
	
	/**
	 * 财政验签发payment
	 */
	public RespSysHead signatureValidate(InputFundData inputData) throws PeException {
		RespNotifyPayResult output = this.send(new ReqNotifyPayResult(inputData), 
				RespNotifyPayResult.class);
		return output;
	}
}
