package com.csii.upp.paygate.action.wap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.paygate.action.PayGateAction;

/**
 * 卡信息校验
 * 
 * @author wangyong
 * 
 */
public  class OtherMobilPayPreAction extends PayGateAction {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void execute(Context context) throws PeException {
		List cardList = new ArrayList();
		Map map = new HashMap();
		String payerAcctNbr =context.getString(Dict.PAYER_ACCT_NBR).substring(0,context.getString(Dict.PAYER_ACCT_NBR).length()-6);
		String deptId = context.getString(Dict.PAYER_ACCT_NBR).substring(context.getString(Dict.PAYER_ACCT_NBR).length()-6); 
		String preAcctNo = payerAcctNbr.substring(0, 4);
		String aftAcctNo = payerAcctNbr.substring(payerAcctNbr.length()-4);
		String acctno = preAcctNo + "*******" + aftAcctNo;
		map.put(Dict.PAYER_ACCT_NO, acctno);
		map.put(Dict.PAYER_ACCT_NBR,payerAcctNbr);
		map.put(Dict.PAYER_ACCT_DEPT_NBR,deptId);
		cardList.add(map);
		context.setData("cardList", cardList);
		context.setDataMap(context.getDataMap());
		
	}	
}
