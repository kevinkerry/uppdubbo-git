package com.csii.upp.payment.action.supportor;

import java.util.Map;

import com.csii.pe.action.Executable;
import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.payment.action.PaymentOnlineAction;

/**
 * 
 * 
 * 执行组装交易类
 * 
 * @author xujin
 * 
 * 
 */
public class ExecuteGroupAction extends PaymentOnlineAction {

	@SuppressWarnings("rawtypes")
	private Map groups;

	@SuppressWarnings("rawtypes")
	public void setGroups(Map groups) {
		this.groups = groups;
	}
	
	public void execute(Context ctx) throws PeException {
		Object obj = this.groups.get(ctx.getTransactionId());
		if (obj != null) {
			// 执行交易检查类
			((Executable) obj).execute(ctx);
		}
	}

}
