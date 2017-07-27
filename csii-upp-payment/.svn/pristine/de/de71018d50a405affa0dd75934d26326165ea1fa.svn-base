package com.csii.upp.payment.action.start;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PayStatus;
import com.csii.upp.payment.action.PaymentOnlineAction;

/**
 * 插入状态为处理中的订单信息 
 * 
 * @author 徐锦
 * 
 */
public class AddHandOrderInfoAction extends PaymentOnlineAction {
	
	@Override
	public void execute(Context context) throws PeException {
		this.addOrderInfo(context, PayStatus.PAY_STATUS_HAND);
	}
}