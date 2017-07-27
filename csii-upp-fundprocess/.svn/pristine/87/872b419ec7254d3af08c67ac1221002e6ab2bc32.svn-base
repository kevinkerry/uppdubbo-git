package com.csii.upp.fundprocess.action.online;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.AcctTypCd;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.fundprocess.action.PayOnlineAction;

/**
 * 基金赎回交易
 * 
 * @author JIANGXING
 * 
 */
public class FundRedemptionAction extends PayOnlineAction {

	@Override
	public void execute(Context arg0) throws PeException {
		InputFundData input = new InputFundData(arg0.getDataMap());
		input.setTranscode(arg0.getString("redemptionTypCd"));
		input.setPayeraccttypcd(AcctTypCd.INNER);
//		if ("RTRP".equals(arg0.getString("redemptionTypCd"))) {
//			input.setPayerAcctNbr(input.getDzh()); // 账号代号：垫资户
//		} else if ("ODRP".equals(arg0.getString("redemptionTypCd"))) {
//			input.setPayerAcctNbr(input.getShh()); // 账号代号：赎回户
//		}		

		RespSysHead withdraw = this.getDebitRouter(input).freddr(input);
		arg0.setData(Dict.CHECK_CARD_PWD_FLAG, input.getCheckdataflag());
		if (withdraw.isSuccess()) {
			RespSysHead deposite = getDBankService(input).fredcr(input);
			if (deposite.isSuccess()) {

			}
		}
	}
}
