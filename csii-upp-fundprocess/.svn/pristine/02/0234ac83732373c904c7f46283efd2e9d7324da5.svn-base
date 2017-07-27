
package com.csii.upp.fundprocess.action.payment;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.CardTypCd;
import com.csii.upp.constant.ConstFdps;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.dto.router.core.RespCreditVirAcctTransfer;
import com.csii.upp.dto.router.core.RespVirAcctTransfer;
import com.csii.upp.fundprocess.action.PayOnlineAction;
import com.csii.upp.service.fundprocess.router.CreditRouter;

/**
 * 虚账户转账
 * @author qgs
 *
 */
public class VirtualAcctTransferAction extends PayOnlineAction {


	@Override
	public void execute(Context context) throws PeException {
		
		InputFundData input = new InputFundData(context.getDataMap());
		
		RespSysHead output =null;
		if(ConstFdps.PROCESSMODE_PAY.equals(input.getProcessMode())){
			input.setCheckdataflag(FundChannelCode.CORE);
			context.setData(Dict.CHECK_DATA_FLAG, input.getCheckdataflag());
			output = getCoreService().virAcctTransfer(input);
			if(CardTypCd.CREDIT.equals(input.getPayercardtypcd())){
				//富阳贷记卡支付响应
				RespCreditVirAcctTransfer creditOutput = (RespCreditVirAcctTransfer)output;
				context.setData(Dict.TRANS_DATE, creditOutput.getTransDate());
				context.setData(Dict.RESERVE, creditOutput.getReserve());
			}else{
				RespVirAcctTransfer deditoutput = (RespVirAcctTransfer)output;
				context.setData(Dict.TRANS_DATE, deditoutput.getTransDate());
			}
		}else if(ConstFdps.PROCESSMODE_WTH.equals(input.getProcessMode())){
			CreditRouter creditRouter=this.getCreditRouter(input);
			context.setData(Dict.CHECK_DATA_FLAG, input.getCheckdataflag());
			output = creditRouter.virAcctWithdrawl(input);
		}
	}
}