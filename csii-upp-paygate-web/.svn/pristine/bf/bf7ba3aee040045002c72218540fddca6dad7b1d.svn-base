package com.csii.upp.paygate.action.common;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqBatchTransfer;
import com.csii.upp.paygate.action.PayGateAction;

/**
 * 批量转账
 * 
 * @author 朱本乐
 *
 */
public class BatchTransferAction extends PayGateAction {
	
	@SuppressWarnings("rawtypes")
	public void execute(Context context) throws PeException {
		context.setData(Dict.TRANS_AMT, context.getString(Dict.TOTAL_TRANS_AMT));
		context.setData(Dict.PAYER_BANK_NBR, context.getString(Dict.MER_SETT_ACCT_BANK_NO));
		context.setData(Dict.PAYER_ACCT_NAME, context.getString(Dict.MER_SETT_ACCT_NAME));
		context.setData(Dict.PAYER_ACCT_TYP_CD, context.getString(Dict.MER_SETT_ACCT_TYPE));
		context.setData(Dict.PAYER_ACCT_NBR, context.getString(Dict.MER_SETT_ACCTNO));
		
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		Map resultMap = this.sendPaymenTransport(new ReqBatchTransfer(inputData));
		context.setDataMap(resultMap);
		}
}
