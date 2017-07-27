/**
 * 
 */
package com.csii.upp.fundprocess.action.payment;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.CardTypCd;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.core.RespCheckCreditCardPwd;
import com.csii.upp.dto.router.core.RespCheckDeditCardPwd;
import com.csii.upp.fundprocess.action.PayOnlineAction;
import com.csii.upp.util.StringUtil;

/**
 * @author lixinyou 校验卡密码快照记录
 *
 */
public class CheckCardPwdAction extends PayOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		InputFundData input = new InputFundData(context.getDataMap());
		if (StringUtil.isStringEmpty(input.getPayeracctnbr())) {
			throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.PAYER_ACCT_NBR });
		}
		if (input.getPayercardtypcd().equals(CardTypCd.DEBIT)) {
			RespCheckDeditCardPwd debitCheck = (RespCheckDeditCardPwd) getCoreService().checkDebitCardPwd(input);
			context.setData(Dict.PAYER_ACCT_NAME, debitCheck.getPayerAcctName());
			context.setData(Dict.CUST_CIF_NBR, debitCheck.getCustCifNbr());
		} else if (input.getPayercardtypcd().equals(CardTypCd.CREDIT)) {
			RespCheckCreditCardPwd creditCheck = (RespCheckCreditCardPwd) getCoreService().checkCreditCardPwd(input);
			context.setData(Dict.PAYER_CARD_CVV2, creditCheck.getPayerCardCvv2()); // 贷记卡cvv
			context.setData(Dict.PAYER_CARD_EXPIRED_DATE, creditCheck.getPayerCardExpiredDate()); // 有效期
			context.setData(Dict.PAYER_ID_NBR, creditCheck.getPayerIdNbr()); // 卡号
			context.setData(Dict.PAYER_ID_TYP_CD, creditCheck.getPayerIdTypCd()); // 证件类型
			context.setData(Dict.PAYER_ACCT_NAME, creditCheck.getPayerAcctName()); // 户名
			context.setData(Dict.PAYER_ACCT_DEPT_NBR, creditCheck.getPayerAcctDeptNbr()); // 机构号
			
		}
	}
}
