package com.csii.upp.payment.action.common;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.constant.PayModeCd;
import com.csii.upp.constant.PayStatus;
import com.csii.upp.constant.ProcStatus;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.constant.YN;
import com.csii.upp.dao.generate.CusttransctrlDAO;
import com.csii.upp.dao.generate.MercusttransctrlDAO;
import com.csii.upp.dao.generate.OnlineorderinfoDAO;
import com.csii.upp.dao.generate.OnlinesubtransDAO;
import com.csii.upp.dao.generate.OnlinetransDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.extend.SubTransData;
import com.csii.upp.dto.generate.Custtransctrl;
import com.csii.upp.dto.generate.Meracctinfo;
import com.csii.upp.dto.generate.Mercusttransctrl;
import com.csii.upp.dto.generate.Onlineorderinfo;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 更新订单和交易流水
 * 
 * @author xujin
 *
 */
public class UpdOnlineTransAction extends PaymentOnlineAction {
	@Override
	public void execute(Context ctx) throws PeException {
		final InputPaymentData inputData = (InputPaymentData) ctx.getData(Dict.INPUT_PAYMENT_DATA);
		// 未到插入总交易明细表步骤可以直接返回
		if (StringUtil.isObjectEmpty(inputData)) {
			// 插入或更新订单时会对订单状态进行判断，如果订单为处理成功、处理中、取消就抛出异常，这里就不用更新订单状态
			if (!YN.N.equals(ctx.getString(Dict.UPD_ORDERYN))) {
				this.updateOnlineOrderInfo(ctx.getString(Dict.MER_NBR), ctx.getString(Dict.MER_SEQ_NBR), null,
						PayStatus.PAY_STATUS_FAIL);
			}
			return;
		}
		log.info(new StringBuilder().append("支付方式[").append(inputData.getPaytypcd()).append("]").append("支付平台流水[")
				.append(inputData.getTransseqnbr()).append("]").append("商户号[").append(inputData.getMernbr()).append("]")
				.append("商户流水[").append(inputData.getMerseqnbr()).append("]").append("商户时间[")
				.append(ctx.getData(Dict.MER_TRANS_DATE_TIME)).append("]").append("交易代码[")
				.append(inputData.getTranscode()).append("] 支付交易完成后做交易流水更新!").toString());
		String respCode = ctx.getString(Dict.RESP_CODE);
		final String transStatus = this.getOnlineTransStatus(respCode, inputData.getTransstatus());
		inputData.setTransstatus(transStatus);
		inputData.setRespcode(respCode);
		inputData.setProcstatus(ProcStatus.OK);
		// 回滚交易限额-条件：未成功且不为超时的并且已做每日累积金额更新
		if (TransStatus.FAILURE.equals(transStatus)) {
			Custtransctrl custTransCtrl = inputData.getCustTransCtrl();
			Mercusttransctrl merCustCtrl = inputData.getMercusttransctrl();
			if (custTransCtrl != null) {
				// 当日限额-本次交易金额
				custTransCtrl.setDayamt(custTransCtrl.getDayamt().subtract(inputData.getTransamt()));
				try {
					int result = CusttransctrlDAO.updateByPrimaryKey(custTransCtrl);
					log.info("******成功回滚了" + result + "个客户当日交易限额!******");
				} catch (SQLException e) {
				}
			}
			if (merCustCtrl != null) {
				// 当日限额-本次交易金额
				merCustCtrl.setDayamt(merCustCtrl.getDayamt().subtract(inputData.getTransamt()));
				try {
					int result = MercusttransctrlDAO.updateByPrimaryKey(merCustCtrl);
					log.info("******特殊商户用户当日交易限额成功回滚了" + result + "个!******");
				} catch (SQLException e) {
				}
			}
		}

		// 更新子交易明细表OT_SUB_TRANS和总交易明细表（OT_TRANS）,如果商户是直接支付就更新确认信息
		final List<SubTransData> onlineSubTransList = inputData.getOnlineSubTransList();
		final Date hostDate = DateUtil.Object_To_Date(ctx.getData(Dict.HOST_CLEAR_DATE));
		final String qrCodeOrdernbr = ctx.getString(Dict.QRCODEORDERNBR);
		final String alipayPayerId = ctx.getString(Dict.PAYERACCTNBR);
		final String receiptAmount = ctx.getString(Dict.RECEIPT_AMOUNT);
		if (onlineSubTransList != null && !onlineSubTransList.isEmpty()) {
			this.getTransactionTemplate().execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus arg0) {
					try {
						BigDecimal confirSumAmt = BigDecimal.ZERO;
						Long confireSumCnt = 0L;
						for (SubTransData subtrans : onlineSubTransList) {
							if (!TransStatus.FAILURE.equals(transStatus)) {
								Meracctinfo subMerAcct = subtrans.getSubMerAcct();
								if (subMerAcct != null && PayModeCd.DIRECT.equals(subMerAcct.getPaymodecd())) {
									// 确认支付金额 = 子交易金额
									subtrans.setConfirmedpayamt(subtrans.getTransamt());
									// 确认支付日期 = 支付平台日期
									subtrans.setConfirmedcleardate(hostDate);
									// 确认支付商户交易时间 = 一级商户交易时间
									subtrans.setConfirmedmerdatetime(inputData.getMertransdatetime());
									// 确认支付商户流水号 =一级商户交易流水号
									subtrans.setConfirmedmerseqnbr(inputData.getMerseqnbr());
									// 确认支付日期 = 支付平台日期
									subtrans.setConfirmedpaydate(inputData.getTransdate());
									confirSumAmt = confirSumAmt.add(subtrans.getTransamt());
									confireSumCnt = confireSumCnt + 1L;
								}
							}
							subtrans.setCleardate(hostDate);
							subtrans.setProcstatus(ProcStatus.OK);
							subtrans.setTransstatus(inputData.getTransstatus());
							subtrans.setDownsysseqnbr(inputData.getDownsystransnbr());
							OnlinesubtransDAO.updateByPrimaryKey(subtrans);
						}
						if (BigDecimal.ZERO.compareTo(confirSumAmt) != 0) {
							inputData.setConfirmedamt(confirSumAmt);
							inputData.setConfirmedcount(confireSumCnt);
						}
						// 二维码前置返回实收金额
						if (!StringUtil.isStringEmpty(receiptAmount)) {
							inputData.setReceiptamt(
									StringUtil.parseBigDecimal(receiptAmount).divide(new BigDecimal(100)));
							inputData.setPayeracctnbr(alipayPayerId);
						}
						// 二维码订单号
						if (!StringUtil.isStringEmpty(qrCodeOrdernbr)) {
							inputData.setQrcodeordernbr(qrCodeOrdernbr);
						}
						inputData.setCleardate(hostDate);
						OnlinetransDAO.updateByPrimaryKey(inputData);
					} catch (Exception e) {
						throw new PeRuntimeException(e);
					}
					return null;
				}
			});
		}
		String payStatus = null;
		// 根据总交易明细状态修改订单状态为成功或失败
		if (!TransStatus.TIMEOUT.equals(transStatus) && !TransStatus.PROCESSING.equals(transStatus)) {
			if (TransStatus.PENDING.equals(transStatus)) {
				payStatus = PayStatus.PAY_STATUS_PENDING;
			} else {
				payStatus = TransStatus.SUCCESS.equals(transStatus) ? PayStatus.PAY_STATUS_OK
						: PayStatus.PAY_STATUS_FAIL;
			}
		}
		// 更新订单信息状态
		this.updateOnlineOrderInfo(inputData.getMernbr(), inputData.getMerseqnbr(), inputData.getTransseqnbr(),
				payStatus);
	}

	/**
	 * 更新订单信息状态
	 * 
	 * @param merNbr
	 * @param merSeqNbr
	 * @param status
	 * @throws PeException
	 */
	private void updateOnlineOrderInfo(String merNbr, String merSeqNbr, String transSeqNbr, String payStatus)
			throws PeException {
		Onlineorderinfo record = new Onlineorderinfo();
		record.setMernbr(merNbr);
		record.setMerseqnbr(merSeqNbr);
		record.setTransseqnbr(transSeqNbr);
		record.setPaystatus(payStatus);
		try {
			OnlineorderinfoDAO.updateByPrimaryKeySelective(record);
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
	}

	/**
	 * 判断交易最终状态
	 * 
	 * @param context
	 * @return
	 */
	private String getOnlineTransStatus(String respCode, String transStatus) {
		if (ResponseCode.SUCCESS.equals(respCode)) {
			return transStatus;
		} else if (ResponseCode.TIMEOUT.equals(respCode)) {
			return TransStatus.TIMEOUT;
		} else {
			return TransStatus.FAILURE;
		}
	}
}
