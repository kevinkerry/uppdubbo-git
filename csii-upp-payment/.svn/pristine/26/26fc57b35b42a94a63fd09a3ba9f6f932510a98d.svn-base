package com.csii.upp.payment.action.post;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.payment.action.PaymentOnlineAction;
/**
 * 提现
 * @author xujin
 *
 */
public class WithdrawTransAction extends PaymentOnlineAction{

	@Override
	public void execute(Context ctx) throws PeException {
//		//验证富阳付款账户名和提现账户名是否一致
//		String payerName = ctx.getString(Dict.PAYER_ACCT_NAME);
//		String payeeName = ctx.getString(Dict.PAYEE_ACCT_NAME);
//		Merthirdpartacct thirdAcct=null;
//		try{
//			if(!StringUtil.isStringEmpty(ctx.getString(Dict.SUB_MERCHANT_ID))){
//				thirdAcct=MerthirdpartacctDAO.selectByPrimaryKey(ctx.getString(Dict.MER_NBR), ctx.getString(Dict.SUB_MERCHANT_ID)); 
//				// 判断虚账户是否存在
//				if (thirdAcct == null) {
//					throw new PeException(DictErrors.THIRD_PART_ACCT_NOT_EXIST);
//				}
//			}else if(!StringUtil.isStringEmpty(ctx.getString(Dict.USER_NBR))){
//				MerthirdpartacctExample example=new MerthirdpartacctExample();
//				example.createCriteria().andUsernbrEqualTo(ctx.getString(Dict.USER_NBR));
//				List<Merthirdpartacct> thirdAccts=MerthirdpartacctDAO.selectByExample(example);
//				// 判断虚账户是否存在
//				if (thirdAccts.isEmpty()) {
//					throw new PeException(DictErrors.THIRD_PART_ACCT_NOT_EXIST);
//				}
//				thirdAcct=thirdAccts.get(0);
//			}
//		} catch (SQLException e) {
//			throw new PeException(DictErrors.TRANS_EXCEPTION);
//		}
//		log.info("网银发来的付款账户名称" + payerName);
//		log.info("网银发来的收款账户名称" + payeeName);
//		log.info("数据库查到的付款账户名称" + thirdAcct.getVirtualacctname());
//		log.info("数据库查到的付款账户名称" + thirdAcct.getAcctname());
//		
//		if(StringUtil.isStringEmpty(payerName)){
//			throw new PeException(DictErrors.VALUE_NOT_EMPTY,new Object[]{Dict.PAYER_ACCT_NAME});
//		}
//		if(!payerName.equals(payeeName) || !payerName.equals(thirdAcct.getVirtualacctname()) || !payerName.equals(thirdAcct.getAcctname())){
//			throw new PeException(DictErrors.ACCT_NAME_IS_NOT_MATCH);
//		}
//		
		InputPaymentData inputData = (InputPaymentData) ctx.getData(Dict.INPUT_PAYMENT_DATA);
		log.info(new StringBuilder().append("支付方式[").append(inputData.getPaytypcd()).append("]").append("支付平台流水[")
				.append(inputData.getTransseqnbr()).append("]").append("商户号[").append(inputData.getMernbr()).append("]")
				.append("商户流水[").append(inputData.getMerseqnbr()).append("]").append("商户时间[")
				.append(ctx.getData(Dict.MER_TRANS_DATE_TIME)).append("]").append("交易代码[").append(inputData.getTranscode())
				.append("] 发送支付交易!").toString());
		// 发往资金处理平台
		this.getFDPSService().virtualAcctTransfer(inputData);
		
	}

}
