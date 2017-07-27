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
import com.csii.upp.dto.router.core.RespQueryCreditCardInfo;
import com.csii.upp.dto.router.core.RespQueryDeditCardInfo;
import com.csii.upp.fundprocess.action.PayOnlineAction;

/**
 * @author lixinyou
 * 卡信息查询快照记录
 */
public class QueryCardInfoAction extends PayOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		InputFundData input = new InputFundData(context.getDataMap());
		if(input.getPayeracctnbr() == null || input.getPayeracctnbr().equals("")){
			throw new PeException(DictErrors.VALUE_NOT_EMPTY,new Object[]{Dict.PAYER_ACCT_NBR});
		}
	
		
		if(input.getPayercardtypcd().equals(CardTypCd.DEBIT)){
			input.setTranscode("3002");
			RespQueryDeditCardInfo deditCardInfo = (RespQueryDeditCardInfo)getCoreService().queryDebitCardInfo(input);
			
            context.setData(Dict.PAYER_ACCT_NAME, deditCardInfo.getPayerAcctName()); //户名
			context.setData(Dict.PAYER_ID_TYP_CD, deditCardInfo.getPayerIdTypCd());  //证件类型
			context.setData(Dict.PAYER_ID_NBR, deditCardInfo.getPayerIdNbr()); //证件号
			context.setData(Dict.PAYER_PHONE_NO_LIST, deditCardInfo.getPayerPhoneNoList()); //手机号列表
			context.setData(Dict.PAYER_ACCT_NBR, deditCardInfo.getPayerAcctNbr()); //卡号
			context.setData(Dict.ACCT_LOSS_STATUS, deditCardInfo.getAcctLossStatus()); //账户挂失状态
			context.setData(Dict.ACCT_STATUS_WORD, deditCardInfo.getAcctStatusWord()); //账户状态字
			context.setData(Dict.ACCT_FREEZE_FLAG, deditCardInfo.getAcctFreezeFlag());  //部分冻结标志
			context.setData(Dict.ACCT_NATURE, deditCardInfo.getAcctNature()); //账户性质
			context.setData(Dict.CARD_STATUS, deditCardInfo.getCardStatus()); //卡片状态
			context.setData(Dict.CARD_STATUS_WORD, deditCardInfo.getCardStatusWord()); //卡片状态字
			context.setData(Dict.PAYER_ACCT_DEPT_NBR, deditCardInfo.getPayerAcctDeptNbr());//机构号
			context.setData(Dict.CUST_CIF_NBR, deditCardInfo.getCustCifNbr());
			context.setData(Dict.PAYER_ACCT_STATUS, deditCardInfo.getPayerAcctStatus());//账户状态
			
		}else if(input.getPayercardtypcd().equals(CardTypCd.CREDIT)){
			RespCheckCreditCardPwd checkInfo = (RespCheckCreditCardPwd) getCoreService().checkCreditCardPwd(input);		
			RespQueryCreditCardInfo creditCardInfo = (RespQueryCreditCardInfo)getCoreService().queryCreditCardInfo(input);
			context.setData(Dict.PAYER_ACCT_NAME, creditCardInfo.getPayerAcctName()); //户名
			context.setData(Dict.PAYER_ID_TYP_CD, creditCardInfo.getPayerIdTypCd()); //证件类型
			context.setData(Dict.PAYER_ID_NBR, creditCardInfo.getPayerIdNbr()); //证件号
			context.setData(Dict.PAYER_PHONE_NO_LIST, creditCardInfo.getPayerPhoneNoList()); //手机号列表
			context.setData(Dict.PAYER_ACCT_NBR, creditCardInfo.getPayerAcctNbr()); //卡号	
			context.setData(Dict.PAYER_ACCT_DEPT_NBR,checkInfo.getPayerAcctDeptNbr());//机构号
			context.setData(Dict.CUST_CIF_NBR, checkInfo.getCustCifNbr());	//客户号
			context.setData(Dict.CARD_STATUS, checkInfo.getCardStat());	//卡状态
		}
		
		
		
	}
}
