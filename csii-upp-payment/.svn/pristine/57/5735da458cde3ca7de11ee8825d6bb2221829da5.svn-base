package com.csii.upp.payment.action.query;

import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dao.generate.UserpaytypsigninfoDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Userpaytypsigninfo;
import com.csii.upp.dto.generate.UserpaytypsigninfoExample;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.StringUtil;
/**
 * 查询签约信息
 * @author xujin
 *
 */
public class QuerySignInfoAction extends PaymentOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		InputPaymentData input = new InputPaymentData(context.getDataMap());
		
		queryCardType(input);
		
		if (StringUtil.isStringEmpty(input.getPayerphoneno())) {
			throw new PeException(DictErrors.VALUE_NOT_EMPTY,new Object[] { Dict.FS_PAYER_PHONE_NO});
		}
		UserpaytypsigninfoExample example = new UserpaytypsigninfoExample();
		example.createCriteria().andSigncardnbrEqualTo(input.getPayeracctnbr()).andPaytypcdEqualTo(input.getPaytypcd());
		List<Userpaytypsigninfo> userSignInfoList = null;
		try {
			userSignInfoList = UserpaytypsigninfoDAO.selectByExample(example);
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		if (userSignInfoList.isEmpty()) {
			// 本地签约信息为空
			throw new PeException(DictErrors.CUST_NOT_SIGN);
		}
		
		Userpaytypsigninfo userSignInfo = userSignInfoList.get(0);
		context.setData(Dict.PAYER_CARD_TYP_CD,input.getPayercardtypcd());
		context.setData(Dict.PAYER_PHONE_NO, userSignInfo.getSignmobile());
		context.setData(Dict.PAYER_ACCT_NBR, userSignInfo.getSigncardnbr());
		context.setData(Dict.SIGN_NBR, userSignInfo.getSignnbr());
		context.setData(Dict.SIGN_TYP_CD, userSignInfo.getSigntypcd());
		context.setData(Dict.SIGN_STATUS, userSignInfo.getSignstatus());
	}

}
