package com.csii.upp.fundprocess.action.online;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.AcctTypCd;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.fundprocess.action.PayOnlineAction;

/**
 * 基金申购交易
 * 
 * @author JIANGXING
 * 
 */
public class FundPurchaseAction extends PayOnlineAction {

	@Override
	public void execute(Context arg0) throws PeException {
		InputFundData input = new InputFundData(arg0.getDataMap());
		input.setPayeeaccttypcd(AcctTypCd.INNER);
		// 先获得贷方通道路由得到科目账户外部识别号
		this.getCreditRouter(input);
		arg0.setData(Dict.CHECK_CARD_PWD_FLAG, input.getCheckdataflag());
		RespSysHead fpurdr = getDBankService(input).fpurdr(input);
		if (fpurdr.isSuccess()) {
//			RespSysHead fpurcr = this.getCreditRouter(input).fpurcr(input);
//			if (fpurcr.isSuccess()) {
//
//			}
		}
	}
}
