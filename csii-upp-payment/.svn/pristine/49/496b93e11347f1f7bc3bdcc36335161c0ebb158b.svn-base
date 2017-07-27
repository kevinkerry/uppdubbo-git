package com.csii.upp.payment.action.post;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.dao.extend.OnlineTransExtendDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.router.fundprocess.RespFundProHead;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.DateUtil;

/**
 * 单笔退货交易
 * 
 * @author xujin
 * 
 */
public class ReturnTransAction extends PaymentOnlineAction {

	@Override  
	public void execute(Context ctx) throws PeException {
		final InputPaymentData inputData = (InputPaymentData) ctx.getData(Dict.INPUT_PAYMENT_DATA);
       //此处需加日专级判断 if(log.isInfoEnabled()){};提高程序运行性能
		if(log.isInfoEnabled()){
			log.info(new StringBuilder().append("支付平台流水[")
			.append(inputData.getTransseqnbr()).append("]").append("商户号[")
			.append(inputData.getMernbr()).append("]").append("商户流水[")
			.append(inputData.getMerseqnbr()).append("]").append("商户时间[")
			.append(ctx.getData(Dict.MER_TRANS_DATE_TIME)).append("]")
			.append("交易代码[").append(inputData.getTranscode())
			.append("] 发送退货交易!").toString());
		}		
		//发往资金处理平台
		RespFundProHead output = (RespFundProHead) this.getFDPSService().refound(inputData);
		ctx.setData(Dict.HOST_CLEAR_DATE, DateUtil.Date_To_DateTimeFormat(output.getHostClearDate(),DateFormatCode.DATE_FORMATTER3));
		//退货成功,更新原交易订单
		OnlineTransExtendDAO.updateOrigTrans(inputData);
	}
	
	
	

}
