package com.csii.upp.payment.action.start;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PayModeCd;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.constant.TransTypCd;
import com.csii.upp.dao.generate.OnlinesubtransDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.extend.SubTransData;
import com.csii.upp.dto.generate.Onlinesubtrans;
import com.csii.upp.dto.generate.OnlinesubtransExample;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.StringUtil;

/**
 * 检查原子交易订单是否满足退货条件
 * 
 * @author xujin
 * 
 */
public class CheckReturnOrigTransAction extends PaymentOnlineAction {
	/**
	 * 未做确认支付是否支持部分退货，暂时置为 false-不支持
	 */
	private boolean subWithDraw = false;

	public boolean isSubWithDraw() {
		return subWithDraw;
	}

	public void setSubWithDraw(boolean subWithDraw) {
		this.subWithDraw = subWithDraw;
	}

	@Override
	public void execute(Context ctx) throws PeException {
		ctx.setData(Dict.ORIG_SUB_MER_SEQ_NBR, ctx.getData(Dict.ORIG_SUB_MER_SEQ_NO));
		InputPaymentData inputData = (InputPaymentData) ctx.getData(Dict.INPUT_PAYMENT_DATA);
		log.info(new StringBuilder().append("支付平台流水[").append(inputData.getTransseqnbr()).append("]").append("商户号[")
				.append(inputData.getMernbr()).append("]").append("商户流水[").append(inputData.getMerseqnbr()).append("]")
				.append("商户时间[").append(ctx.getData(Dict.MER_TRANS_DATE_TIME)).append("]").append("交易代码[")
				.append(inputData.getTranscode()).append("] 检查原子交易订单是否满足退货条件!").toString());
		// 原子交易明细信息
		Onlinesubtrans origSubTrans = inputData.getOrigSubTrans();
		// 退货子交易明细
		SubTransData subTrans = inputData.getOnlineSubTransList().get(0);
		// 本次交易前原订单已退货金额
		BigDecimal alreadyWthAmt = origSubTrans.getRefundedamt() == null ? BigDecimal.ZERO
				: origSubTrans.getRefundedamt();
		// 退货金额 !> 原交易金额-原交易已退货金额
		if (subTrans.getTransamt().compareTo(origSubTrans.getTransamt().subtract(alreadyWthAmt)) > 0) {
			throw new PeException(DictErrors.REFUNDED_AMT_OUT);
		}
		// 原交易是支付交易才允许进行该退货交易
		if (!TransTypCd.PAY.equals(origSubTrans.getTranstypcd())) {
			throw new PeException(DictErrors.INVALID_TRANS);
		}
		// 原交易完成标志必须为成功或者部分退货
		if ((!TransStatus.SUCCESS.equals(origSubTrans.getTransstatus())
				&& !TransStatus.SUB_WITHDRAW.equals(origSubTrans.getTransstatus()))) {
			throw new PeException(DictErrors.ORIG_TRANS_STATUS_ERROR);
		}

		if (PayModeCd.HOLD.equals(inputData.getPayModeCd())) {
			// 冻结支付模式下确认支付后不支持退货
			if (!StringUtil.isObjectEmpty(origSubTrans.getConfirmedcleardate())) {
				throw new PeException(DictErrors.HOLD_AND_CONFIRMED_NOT_REFOUND);
			}
		}

		// 未做确认支付不支持部分退货
		// 新增丰收购（010020150521152412）可以部分退货
		List<String> merList = new ArrayList<String>(Arrays.asList("010020150521152412"));
		if (StringUtil.isObjectEmpty(origSubTrans.getConfirmedcleardate())) {
			if (!this.isSubWithDraw() && !merList.contains(inputData.getMernbr())&& subTrans.getTransamt().compareTo(origSubTrans.getTransamt()) != 0) {
				throw new PeException(DictErrors.SUB_WITH_DRAW_ERROR);
			}
		}

		// 重复退货检查:针对同一笔支付交易的退货正在进行中或者超时不允许退货
		OnlinesubtransExample example = new OnlinesubtransExample();
		List<String> statusList = new ArrayList<String>(
				Arrays.asList(TransStatus.INIT, TransStatus.PROCESSING, TransStatus.TIMEOUT));
		example.createCriteria().andOrigsubtransseqnbrEqualTo(subTrans.getOrigsubtransseqnbr())
				.andTransstatusIn(statusList).andSubtransseqnbrNotEqualTo(subTrans.getSubtransseqnbr());
		try {
			if (OnlinesubtransDAO.countByExample(example) > 0) {
				throw new PeException(DictErrors.DUPLICATED_REQ);
			}
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}

		/**
		 * 原来要求：当原交易没有结算或结算未成功时，如果是商户结算日并且系统正在进行批量清分或批量结算，那么就不能做退货
		 * 现要求：已做确认支付就从结算户退款，未做确认支付不参与清分，所以这里经过讨论不需要控制
		 */
	}

}
