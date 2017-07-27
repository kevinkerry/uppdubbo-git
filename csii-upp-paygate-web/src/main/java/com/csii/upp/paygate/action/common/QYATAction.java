package com.csii.upp.paygate.action.common;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqQueryVirtualAcctBalance;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.StringUtil;

/**
 * 富阳虚账户余额查询
 * 
 * @author wuwenxiang
 * 
 */
public class QYATAction extends PayGateAction {
	
	@Override
	public void execute(Context context) throws PeException {
		log.info("**********富阳自行车网 QYAT交易开始**********");
		context.setData(Dict.PAYEE_ACCT_NBR,StringUtil.toStringAndTrim(context.getString(Dict.MER_THIRD_PART_ACCTNO))); 

		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		Map resultMap = this.sendPaymenTransport(new ReqQueryVirtualAcctBalance(inputData));
		context.setDataMap(resultMap);
		return ;
	}
}