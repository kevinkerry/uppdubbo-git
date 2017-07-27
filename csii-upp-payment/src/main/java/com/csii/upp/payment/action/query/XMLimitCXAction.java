package com.csii.upp.payment.action.query;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PayStatus;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.router.fundprocess.RespQueryLimitInfo;
import com.csii.upp.payment.action.PaymentOnlineAction;

public class XMLimitCXAction extends PaymentOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		//插入订单
		this.addOrderInfo(context, PayStatus.PAY_STATUS_NO);
		
		InputPaymentData inputData = new InputPaymentData(context.getDataMap());
		log.info("查询电子账户"+context.getData(Dict.CIF_NO)+"的额度信息开始！");
		RespQueryLimitInfo output = null;
		output = (RespQueryLimitInfo) this.getFDPSService().queryXiaoMiTransLimit(inputData);
		log.info("**************查询结束*********************");
		context.setData(Dict.CREDIT_LIMIT_AMT_REMAIN1, output.getCreditLimitAmtRemain1());
		context.setData(Dict.CREDIT_LIMIT_AMT_REMAIN2, output.getCreditLimitAmtRemain2());

	}

}
