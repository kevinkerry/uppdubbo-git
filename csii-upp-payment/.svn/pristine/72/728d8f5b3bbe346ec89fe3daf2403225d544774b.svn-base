package com.csii.upp.payment.action.post;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.CardTypCd;
import com.csii.upp.constant.CodeTypCd;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.InteralFlag;
import com.csii.upp.constant.PayModeCd;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.extend.SubTransData;
import com.csii.upp.dto.generate.Meracctinfo;
import com.csii.upp.dto.router.fundprocess.RespUnifiefPayment;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 支付交易
 * 
 * @author xujin
 *
 */
public class PayTransAction extends PaymentOnlineAction {

	@Override
	public void execute(Context ctx) throws PeException {
		InputPaymentData inputData = (InputPaymentData) ctx.getData(Dict.INPUT_PAYMENT_DATA);
		log.info(new StringBuilder().append("支付方式[").append(inputData.getPaytypcd()).append("]").append("支付平台流水[")
				.append(inputData.getTransseqnbr()).append("]").append("商户号[").append(inputData.getMernbr()).append("]")
				.append("商户流水[").append(inputData.getMerseqnbr()).append("]").append("商户时间[")
				.append(ctx.getData(Dict.MER_TRANS_DATE_TIME)).append("]").append("交易代码[")
				.append(inputData.getTranscode()).append("] 发送支付交易!").toString());

		// 密码先软解密后再硬加密
		if (PayTypCd.CARDPWD.equals(inputData.getPaytypcd())
				&& CardTypCd.DEBIT.equals(inputData.getPayercardtypcd())) {
			inputData.setPayercardpwd(
					this.getPasswordService().encrypt(inputData.getPayercardpwd(), inputData.getChannelnbr()));
		} else if (!StringUtil.isStringEmpty(inputData.getPayercardpwd())) {
			throw new PeException(DictErrors.PIN_EMPTY);
		}
		
		List<SubTransData> onlineSubTransList = inputData.getOnlineSubTransList();
		Meracctinfo subMerAcct = onlineSubTransList.get(0).getSubMerAcct();
		if (PayModeCd.HOLD.equals(subMerAcct.getPaymodecd())) {
			// 冻结支付模式调用虚账户转账并冻结接口
			this.getFDPSService().virtualAcctTransfer(inputData);
		}else {
			// 合并相同机构的子订单用于记账
			inputData.setPayeeAcctList(this.combineSubOrder(onlineSubTransList));
			RespUnifiefPayment output = null;
			// 发往资金处理平台
			if (PayTypCd.OTHCYBER.equals(inputData.getPaytypcd())) {
				output = (RespUnifiefPayment) this.getFDPSService().otherEbankPayment(inputData);
			} else {
				output = (RespUnifiefPayment) this.getFDPSService().unifiedPayment(inputData);
			}
			if (output.getReturnForm() != null) {
				ctx.setData(Dict.RETURN_FORM, output.getReturnForm());
			}
			if (output.getCodeUrl() != null) {
				ctx.setData(Dict.CODE_URL, output.getCodeUrl());
				ctx.setData(Dict.QRCODEORDERNBR, output.getQrcodeordernbr());
			}
			
			if(!StringUtil.isStringEmpty(output.getReceiptAmount())){
				ctx.setData(Dict.RECEIPT_AMOUNT, output.getReceiptAmount());
				ctx.setData(Dict.PAYERACCTNBR, output.getPayerAcctNbr());
			}
			String merurl1 = inputData.getMerurl1();
			if(!StringUtil.isStringEmpty(merurl1)){
				merurl1 = merurl1.replaceAll("amp;", "");
				ctx.setData(Dict.MER_URL1, merurl1);
			}
			
			ctx.setData(Dict.TRANS_DATE, output.getDownrtxndate());
			ctx.setData(Dict.TRANS_SEQ_NBR, output.getDownrtxnnbr());
			ctx.setData(Dict.HOST_CLEAR_DATE, DateUtil.Date_To_DateTimeFormat(output.getHostClearDate(),DateFormatCode.DATE_FORMATTER3));
		}
	}

	/**
	 * 合并相同机构的子订单用于记账
	 * 
	 * @param onlineSubTransList
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Map<String, String>> combineSubOrder(List<SubTransData> onlineSubTransList) {
		List<Map<String, String>> payeeAcctList = new ArrayList<Map<String, String>>();
		if (onlineSubTransList != null && !onlineSubTransList.isEmpty()) {
			List<String> payeeAcctNbrs = new ArrayList<String>();
			for (SubTransData onlineSubTrans : onlineSubTransList) {
				String payeeAcctNbr = onlineSubTrans.getPayeeacctnbr();
				if (!payeeAcctNbrs.contains(payeeAcctNbr)) {
					Map combMap = new HashMap();
					combMap.put(Dict.PAYEE_ACCT_NBR, payeeAcctNbr);
					combMap.put(Dict.PAYEE_ACCT_DEPT_NBR, onlineSubTrans.getMerdevdeptnbr());
					payeeAcctNbrs.add(payeeAcctNbr);
					BigDecimal sumTransAmt = BigDecimal.ZERO;
					BigDecimal sumRealAmt = BigDecimal.ZERO;
					BigDecimal sumDeductAmt = BigDecimal.ZERO;
					for (SubTransData tmpSubTrans : onlineSubTransList) {
						if (payeeAcctNbr.equals(tmpSubTrans.getPayeeacctnbr())) {
							sumTransAmt = sumTransAmt.add(tmpSubTrans.getTransamt());
							//积分支付的情况下合并子交易的sumRealAmt和sumDeductAmt
							if(InteralFlag.YES.equals(tmpSubTrans.getInteralflag())){
								sumRealAmt = sumRealAmt.add(tmpSubTrans.getRealamt());
								sumDeductAmt = sumDeductAmt.add(tmpSubTrans.getDeductamt());
							}
						}
					}
					combMap.put(Dict.SUB_TRANS_AMT, StringUtil.parseObjectToString(sumTransAmt));
					combMap.put(Dict.SUB_DEDUCT_AMT, StringUtil.parseObjectToString(sumDeductAmt));
					combMap.put(Dict.SUB_REAL_AMT, StringUtil.parseObjectToString(sumRealAmt));
					if(PayTypCd.OTHACTSCAN.equals(onlineSubTrans.getPaytypcd())
							||PayTypCd.OTHPASSCAN.equals(onlineSubTrans.getPaytypcd())){
						combMap.put(Dict.MER_NAME, onlineSubTrans.getMerName());
						combMap.put(Dict.THIRD_MER_NBR,onlineSubTrans.getThirdmernbr() );
						if(CodeTypCd.WECHAT.equals(onlineSubTrans.getCodetypcd())){
							combMap.put(Dict.PROXY_MER_NBR, onlineSubTrans.getWechatproxymernbr());	
						}else{
							combMap.put(Dict.PROXY_MER_NBR, onlineSubTrans.getMernbr());
						}
					}
					payeeAcctList.add(combMap);
				}
			}
		}
		return payeeAcctList;
	}

}
