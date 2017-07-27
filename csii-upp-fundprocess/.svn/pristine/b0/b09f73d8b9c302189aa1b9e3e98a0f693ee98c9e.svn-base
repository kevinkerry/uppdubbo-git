/**
 * 
 */
package com.csii.upp.fundprocess.action.payment;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.InteralFlag;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.fundprocess.action.PayOnlineAction;
import com.csii.upp.service.fundprocess.router.DebitRouter;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * @author lixinyou
 * @description 用于对payment提供统一的支付交易
 *
 */
public class UnifiedPaymentAction extends PayOnlineAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.csii.pe.action.Executable#execute(com.csii.pe.core.Context)
	 */
	
	@Override
	public void execute(Context context) throws PeException {	
		
		
			if(!StringUtil.isStringEmpty(context.getString(Dict.INTERAL_FLAG))&&InteralFlag.YES.equals(context.getString(Dict.INTERAL_FLAG))){
				context.setData(Dict.CHECK_DATA_FLAG,  FundChannelCode.CORE);
				InputFundData inputInit = new InputFundData(context.getDataMap());
				inputInit.setPointdataflag(InteralFlag.YES);
				getPointService().consumePayerIntegral(inputInit);
				InputFundData inputPoint = new InputFundData(context.getDataMap());
				getCoreService().coreIntegalPay(inputPoint);
			}
			context.setData(Dict.POINT_DATA_FLAG, InteralFlag.NO);
			InputFundData input = new InputFundData(context.getDataMap());
			DebitRouter debitRouter = getDebitRouter(input);
			context.setData(Dict.CHECK_DATA_FLAG, input.getCheckdataflag());
			RespSysHead anothertb = debitRouter.unifiedPayment(input);
			if (anothertb.getReturnHtml() != null) {
				context.setData(Dict.RETURN_FORM, anothertb.getReturnHtml());
				// 返回交易已受理
				context.setData(Dict.TRANS_STATUS, TransStatus.PROCESSING);
			}
			if(!StringUtil.isStringEmpty(anothertb.getCodeUrl())){
				context.setData(Dict.CODE_URL, anothertb.getCodeUrl());
				context.setData(Dict.QRCODEORDERNBR, input.getInnerfundtransnbr());
				//返回交易成功待扫码
				context.setData(Dict.TRANS_STATUS, TransStatus.PENDING);
			}//根据实收金额判断，二维码前置返回则返回
			if(!StringUtil.isStringEmpty(input.getReceiptAmount())){
				context.setData(Dict.PAYER_ACCT_NBR, input.getPayeracctnbr());
				context.setData(Dict.RECEIPT_AMOUNT, input.getReceiptAmount());
			}
			context.setData(Dict.HOST_CLEAR_DATE, DateUtil.Date_To_DateTimeFormat(anothertb.getDownrtxndate(),DateFormatCode.DATE_FORMATTER3));
			
			if(context.getString(Dict.CIF_NO)!=null){
				String paytype=context.getString(Dict.PAY_TYP_CD);
				context.setData(Dict.POINT_DATA_FLAG, InteralFlag.NO);
				RespSysHead result = null;
				if(paytype.equals("2")){
					InputFundData input1 = new InputFundData(context.getDataMap());
					
					result = getDBankService(input).cCConsumePayment(input1);
				}else{
					if(paytype.equals("0")){
						context.setData(Dict.PAY_METHOD, "SISP");
					}else{
						context.setData(Dict.PAY_METHOD, "EFB");
					}
					InputFundData input2 = new InputFundData(context.getDataMap());
					result = getDBankService(input).xiaoMiPayment(input2);
				}
				
				if(!"0000".equals(result.getReturncode())){
					context.setData(Dict.RESP_CODE, result.getReturncode());
					context.setData(Dict.RESP_MESSAGE, result.getReturnmsg());
				}
				
			}
		
	}
	
	
}