package com.csii.upp.payment.action.post;

import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.MerTransCtrlStatus;
import com.csii.upp.constant.PayStatus;
import com.csii.upp.dao.extend.MertransctrlExtendDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.payment.action.PaymentOnlineAction;

/**
 * 插入状态为等待支付的订单信息 
 * 
 * @author 徐锦
 * 
 */
public class AddOrderInfoAction extends PaymentOnlineAction {
	
	@Override
	public void execute(Context context) throws PeException {
		this.addOrderInfo(context, PayStatus.PAY_STATUS_NO);
		this.queryPaytype(context);
	}
	

	public void queryPaytype(Context context) throws PeException
	{
		String merchantId = context.getString(Dict.MER_NBR);
		String payTypes="";
		List<String> merPayTyps = MertransctrlExtendDAO.queryMerPayTyp(merchantId, MerTransCtrlStatus.Y);
		for(String payTypCd : merPayTyps)
		{
			payTypes+=payTypCd+"|";		
		}	
		context.setData(Dict.PAY_TYPE_CD_STR, payTypes);
	}
}