package com.csii.upp.payment.action.mgmt;

import java.util.List;

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

/**
 * 强制注销签约信息
 * 
 * @author zgb
 *
 */
public class CancelSignInfoAction extends PaymentOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		InputPaymentData input = new InputPaymentData(context.getDataMap());
		
		// 判断卡类型 是否判断本行或其他行卡 卡宾已配置
		queryCardType(input);
		
		UserpaytypsigninfoExample example = new UserpaytypsigninfoExample();
		example.createCriteria().andSigncardnbrEqualTo(input.getPayeracctnbr()).andPaytypcdEqualTo(input.getPaytypcd());
		
		try {
			Userpaytypsigninfo record = CancelUserinfo(example);
			UserpaytypsigninfoDAO.updateByExampleSelective(record, example);
		} catch (Exception e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		context.setData(Dict.PAYER_CARD_TYP_CD, input.getPayercardtypcd());
		context.setData(Dict.PAYER_ACCT_NBR, input.getPayeracctnbr());
		return;
	}
	
	public Userpaytypsigninfo CancelUserinfo(UserpaytypsigninfoExample example) throws Exception{
		
		List<Userpaytypsigninfo> list = UserpaytypsigninfoDAO.selectByExample(example);
		Userpaytypsigninfo userpaytypsigninfo=list.get(0);
		userpaytypsigninfo.setSignstatus(SignStatus.CANCEL);
		userpaytypsigninfo.setSigneffdate(this.getPostDate());
		return userpaytypsigninfo;
	}
}