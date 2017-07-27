package com.csii.upp.payment.action.mgmt;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.SignStatus;
import com.csii.upp.dao.generate.UserpaytypsigninfoDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Userpaytypsigninfo;
import com.csii.upp.dto.generate.UserpaytypsigninfoExample;
import com.csii.upp.payment.action.PaymentOnlineAction;

public class CancelSignStatusAction extends PaymentOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		
		InputPaymentData input = new InputPaymentData(context.getDataMap());
		
		// 判断卡类型 是否判断本行或其他行卡 卡宾已配置
		queryCardType(input);
		
		UserpaytypsigninfoExample example = new UserpaytypsigninfoExample();
		example.createCriteria().andSigncardnbrEqualTo(input.getPayeracctnbr()).andPaytypcdEqualTo(input.getPaytypcd());
		Userpaytypsigninfo signinfo = new Userpaytypsigninfo();
		signinfo.setSignstatus(SignStatus.CANCEL);
		signinfo.setSigneffdate(this.getPostDate());
		try {
			UserpaytypsigninfoDAO.updateByExampleSelective(signinfo, example);
		} catch (Exception e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		context.setData(Dict.PAYER_CARD_TYP_CD, input.getPayercardtypcd());
		context.setData(Dict.PAYER_ACCT_NBR, input.getPayeracctnbr());
		return;
	}
	

}
