/**
 * 
 */
package com.csii.upp.fundprocess.action.payment;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.fundprocess.action.PayOnlineAction;
import com.csii.upp.util.StringUtil;

/**
 * @author zhubenle
 * @description 批量转账接口
 *
 */
public class BatchTransferAction extends PayOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		InputFundData input = new InputFundData(context.getDataMap());
		List<Map<String, String>> list =  input.getPayeeAcctList();
		if(list.size() > 0 && !StringUtil.isStringEmpty(list.get(0).get(Dict.PAYEE_BANK_NBR))){
			input.setPayeeacctnbr(list.get(0).get(Dict.PAYEE_ACCT_NBR));
			input.setPayeename(list.get(0).get(Dict.PAYEE_ACCT_NAME));
			input.setPayeebanknbr(list.get(0).get(Dict.PAYEE_BANK_NBR));
			if(input.getTransamt().compareTo(new BigDecimal(50000)) == -1){
				input.setCheckdataflag(FundChannelCode.BEPS);
				context.setData(Dict.CHECK_DATA_FLAG, input.getCheckdataflag());
				//小于5万，走小额
				this.getBepsService().virAcctWithdrawl(input);
			}else {
				input.setCheckdataflag(FundChannelCode.HVPS);
				context.setData(Dict.CHECK_DATA_FLAG, input.getCheckdataflag());
				this.getHvpsService().virAcctWithdrawl(input);
			}
		}else {
			input.setCheckdataflag(FundChannelCode.CORE);
			context.setData(Dict.CHECK_DATA_FLAG, input.getCheckdataflag());
			this.getCoreService().batchTransfer(input);
		}
	}
}