package com.csii.upp.fundprocess.action.online;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.dto.router.eaccount.AcctInfo;
import com.csii.upp.dto.router.eaccount.RespQueryDirectAcctInfo;
import com.csii.upp.fundprocess.action.PayOnlineAction;
import com.csii.upp.service.fundprocess.EAccountService;

/**
 * @ClassName:账户关系验证与查询
 * @Description: 账户验证
 * @author :xujin
 * @date：2015-08-07
 */
public class CupsValidateAcctAction extends PayOnlineAction {
	public void execute(Context ctx) throws PeException {
		InputFundData input = new InputFundData(ctx.getDataMap());
		EAccountService service=this.getDBankService(input);
		ctx.setData(Dict.CHECK_CARD_PWD_FLAG, input.getCheckdataflag());
		RespSysHead query = service.validatePayerAcctInfo(input);
		AcctInfo acctInfo = ((RespQueryDirectAcctInfo) query).getAcctinfo();
		ctx.setData(Dict.PAYER_PHONE_NO, acctInfo.getPhoneNbr());
		ctx.setData(Dict.PAYER_ACCT_NAME, acctInfo.getAcctname());
		ctx.setData(Dict.PAYER_ACCT_DEPT_NBR, acctInfo.getBranchorgnbr());
	}
}
