package com.csii.upp.paygate.action.cardpwd;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.paygate.action.PayGateAction;

/**
 * 错误页面返回处理
 * 
 * @author xujin
 * 
 */
public class BackCardPwdAction extends PayGateAction {

	public void execute(Context context) throws PeException {
		
		if(context.getData(Dict.TRANS_ID).equals("FYEP"))
			context.setState(1);
		else
			context.setState(1);
	}
}
