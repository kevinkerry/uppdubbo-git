package com.csii.upp.payment.action.supportor;

import java.util.List;

import com.csii.pe.action.Executable;
import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.payment.action.PaymentOnlineAction;

/**
 * 
 * 
 * 组装交易类工厂
 * 
 * @author xujin
 * 
 *
 */
public class BeanFactoryAction extends PaymentOnlineAction {

	@SuppressWarnings("rawtypes")
	private List actions;

	@SuppressWarnings("rawtypes")
	public void setActions(List actions) {
		this.actions = actions;
	}
	
	/* (non-Javadoc)
	 * @see com.csii.pe.action.Executable#execute(com.csii.pe.core.Context)
	 */
	public void execute(Context ctx) throws PeException {

		if (actions != null && !actions.isEmpty()) {

			for (int i = 0; i < actions.size(); i++) {

				((Executable) actions.get(i)).execute(ctx);
			}

		}

	}
}
