package com.csii.upp.payment.action.common;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.action.BaseAction;
import com.csii.upp.payment.event.handler.RegOnlineJnlEvent;
import com.csii.upp.supportor.DefaultSupportor;
/**
 * 异步登记交易流水
 * @author 徐锦
 *
 */
public class OnlineJnlRegAction   extends BaseAction {

	@Override
	public void execute(Context context) throws PeException {
		RegOnlineJnlEvent event = new RegOnlineJnlEvent();
		event.setParamMap(context.getDataMap());
		DefaultSupportor.getEventManager().doService(event);
	}

}
