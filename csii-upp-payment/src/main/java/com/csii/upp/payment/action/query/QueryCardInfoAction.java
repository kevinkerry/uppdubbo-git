package com.csii.upp.payment.action.query;

import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.dao.generate.UserpaytypsigninfoDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Userpaytypsigninfo;
import com.csii.upp.dto.generate.UserpaytypsigninfoExample;
import com.csii.upp.dto.router.fundprocess.RespQueryCardInfo;
import com.csii.upp.payment.action.PaymentOnlineAction;

/**
 * 查询卡信息：如果卡密支付，进行自动签约
 * 
 * @author fgq
 * 
 */
public class QueryCardInfoAction extends PaymentOnlineAction {


	@Override
	public void execute(Context context) throws PeException {
		InputPaymentData input = new InputPaymentData(context.getDataMap());
		String inputMobile = input.getPayerphoneno();
		String inputCardNbr = input.getPayeracctnbr();
		String payTypCd = input.getPaytypcd();


		// 判断卡类型
		queryCardType(input);

		RespQueryCardInfo hostInfo = (RespQueryCardInfo) getFDPSService()
				.queryCardInfo(input);
		context.setData(Dict.PAYER_ACCT_NAME, hostInfo.getPayerAcctName());
		context.setData(Dict.PAYER_CARD_TYP_CD, input.getPayercardtypcd());
		context.setData(Dict.CUST_CIF_NBR, hostInfo.getCustCifNbr());
		context.setData(Dict.PAYER_ACCT_DEPT_NBR,hostInfo.getPayerAcctDeptNbr());
		context.setData(Dict.PAYER_PHONE_NO_LIST,hostInfo.getPayerPhoneNoList());
		context.setData(Dict.PAYER_ACCT_STATUS,hostInfo.getPayerAcctStatus());
		context.setData(Dict.ACCT_LOSS_STATUS,hostInfo.getAcctLossStatus());
		context.setData(Dict.ACCT_STATUS_WORD,hostInfo.getAcctStatusWord());
		context.setData(Dict.CARD_STATUS_WORD,hostInfo.getCardStatusWord());
		context.setData(Dict.CARD_STATUS,hostInfo.getCardStatus());
     // 如果卡密支付，进行自动签约
		if (PayTypCd.CARDPWD.equals(payTypCd)) {
			/*
			 * 查询签约信息,并设置限额
			 */
			UserpaytypsigninfoExample example = new UserpaytypsigninfoExample();
			example.createCriteria()
					.andSigncardnbrEqualTo(input.getPayeracctnbr())
					.andPaytypcdEqualTo(input.getPaytypcd());
			List<Userpaytypsigninfo> existSignInfo = null;
			try {
				existSignInfo = UserpaytypsigninfoDAO.selectByExample(example);
			} catch (SQLException e1) {
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
			// 进行自动签约
			if (existSignInfo.isEmpty()) {
				this.autoSignUserPayTyp(inputMobile, inputCardNbr, hostInfo.getPayerAcctDeptNbr(),payTypCd);
			}
		}
	}
}
