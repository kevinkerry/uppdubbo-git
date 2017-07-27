package com.csii.upp.payment.action.post;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.MerStatus;
import com.csii.upp.constant.PayModeCd;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.extend.OnlineTransExtendDAO;
import com.csii.upp.dao.generate.MeracctinfoDAO;
import com.csii.upp.dao.generate.OnlinesubtransDAO;
import com.csii.upp.dao.generate.OnlinesubtranshistDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Meracctinfo;
import com.csii.upp.dto.generate.Onlinesubtrans;
import com.csii.upp.dto.generate.OnlinesubtransExample;
import com.csii.upp.dto.generate.Onlinesubtranshist;
import com.csii.upp.dto.generate.OnlinesubtranshistExample;
import com.csii.upp.dto.router.fundprocess.RespQueryHostDate;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 确认支付
 * 
 * @author gaoqi
 *
 */
public class ConfirmedPayAction extends PaymentOnlineAction {
	@Override
	public void execute(final Context context) throws PeException {
		context.setData(Dict.ORIG_MER_DATE, context.getString(Dict.ORIG_MER_DATE_TIME).substring(0, 8));
		String origSubTime = context.getString(Dict.ORIG_SUB_MER_DATE_TIME);
		if(origSubTime.isEmpty() || origSubTime == null){
			origSubTime = context.getString(Dict.ORIG_MER_DATE_TIME);
		}
		context.setData(Dict.ORIG_SUB_MER_DATE, origSubTime.substring(0, 8));
		log.info(new StringBuilder().append("****************确认支付COPR开始****************"));
		if (StringUtil.isStringEmpty(context.getString(Dict.MER_SEQ_NBR))
				|| StringUtil.isStringEmpty(context.getString(Dict.MER_NBR))
				|| StringUtil.isStringEmpty(context.getString(Dict.MER_TRANS_DATE_TIME))
				|| StringUtil.isStringEmpty(context.getString(Dict.ORIG_MER_SEQ_NBR))
				|| StringUtil.isStringEmpty(context.getString(Dict.ORIG_MER_DATE_TIME))
				|| StringUtil.isStringEmpty(context.getString(Dict.SUB_MERCHANT_ID))
				|| StringUtil.isStringEmpty(context.getString(Dict.ORIG_SUB_MER_SEQ_NO))) {
			throw new PeException(DictErrors.VALUE_NOT_EMPTY);
		}
		// 获取原子商户交易时间
		Date orgSubMerDate = null;
		if (!StringUtil.isStringEmpty(context.getString(Dict.ORIG_SUB_MER_DATE_TIME))) {
			orgSubMerDate = DateUtil.DateFormat_To_Date(context.getString(Dict.ORIG_SUB_MER_DATE));
		} else {
			orgSubMerDate = DateUtil.DateFormat_To_Date(context.getString(Dict.ORIG_MER_DATE));
		}
		// 根据二级商户号、原二级商户流水号、原二级商户交易时间查询原支付子订单
		OnlinesubtransExample example = new OnlinesubtransExample();
		example.createCriteria().andMernbrEqualTo(context.getString(Dict.SUB_MERCHANT_ID))
				.andMerseqnbrEqualTo(context.getString(Dict.ORIG_SUB_MER_SEQ_NO)).andMertransdateEqualTo(orgSubMerDate);
		example.setOrderByClause("transtime desc");
		List<Onlinesubtrans> subMerInfoList = null;
		List<Onlinesubtranshist> subMerInfoHistList = null;
		Onlinesubtrans subMerInfo = null;
		boolean isQueryHist = false;// 查询当前表还是历史表：true:历史表,false:当前表
		try {
			subMerInfoList = OnlinesubtransDAO.selectByExample(example);
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		if (subMerInfoList.isEmpty()) {
			// 二级商户信息为空
			OnlinesubtranshistExample example1 = new OnlinesubtranshistExample();
			example1.createCriteria().andMernbrEqualTo(context.getString(Dict.SUB_MERCHANT_ID))
					.andMerseqnbrEqualTo(context.getString(Dict.ORIG_SUB_MER_SEQ_NO))
					.andMertransdateEqualTo(orgSubMerDate);
			example1.setOrderByClause("transtime desc");
			try {
				subMerInfoHistList = OnlinesubtranshistDAO.selectByExample(example1);
			} catch (SQLException e) {
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
			if (subMerInfoHistList.isEmpty()) {
				throw new PeException(DictErrors.ORDER_NOT_EXIST);
			}
			Onlinesubtranshist subMerInfoHist = subMerInfoHistList.get(0);
			subMerInfo = new Onlinesubtrans();
			subMerInfo.setTransstatus(subMerInfoHist.getTransstatus());
			subMerInfo.setConfirmedpaydate(subMerInfoHist.getConfirmedpaydate());
			subMerInfo.setTransamt(subMerInfoHist.getTransamt());
			subMerInfo.setTransseqnbr(subMerInfoHist.getTransseqnbr());
			subMerInfo.setSubtransseqnbr(subMerInfoHist.getSubtransseqnbr());
			subMerInfo.setPayeeacctnbr(subMerInfoHist.getPayeeacctnbr());
			subMerInfo.setMernbr(subMerInfoHist.getMernbr());
			subMerInfo.setTransamt2(subMerInfoHist.getTransamt2());
			isQueryHist = true;
		} else {
			subMerInfo = subMerInfoList.get(0);
		}
		String origAmt = context.getString(Dict.ORIG_TRANS_AMT);
		if(StringUtil.isStringEmpty(origAmt)){
			origAmt = StringUtil.parseObjectToString(subMerInfo.getTransamt2());
		}
		// 判断子订单交易状态是否为交易失败、交易超时
		if (TransStatus.FAILURE.equals(subMerInfo.getTransstatus())
				|| TransStatus.TIMEOUT.equals(subMerInfo.getTransstatus())
				||TransStatus.ALL_WITHDRAW.equals(subMerInfo.getTransstatus())) {
			throw new PeException(DictErrors.TRANS_STATUS_ERROR);
		}
		if (subMerInfo.getConfirmedpaydate() != null) {
			throw new PeException(DictErrors.ORIG_ORDER_CONFIRMED);
		}
		// 获取原支付子订单支付金额
		if ((!StringUtil.isObjectEmpty(origAmt))
				&& ((new BigDecimal(origAmt)
						.compareTo(subMerInfo.getTransamt2()) != 0))) {
			throw new PeException(DictErrors.ORIG_AMT_NOT_EQUAL_CONFIRMED);
		}
		final Date merDate = DateUtil.DateTimeFormat_To_Date(context.getString(Dict.MER_TRANS_DATE_TIME));

		Meracctinfo subMerAcct=null;
		try {
			subMerAcct = MeracctinfoDAO.selectByPrimaryKey(subMerInfo
					.getMernbr());
			// 判断该商户是否是二级商户
			if (subMerAcct == null) {
				throw new PeException(DictErrors.MER_NOT_FOUND_SUB_MER,new Object[]{subMerInfo
						.getMernbr()});
			}
			// 商户已冻结或注销
			if (!MerStatus.NORMAL.equals(subMerAcct.getMerstatus())) {
				throw new PeException(DictErrors.MER_STATUS_INVALID);
			}
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		InputPaymentData input = new InputPaymentData(context.getDataMap());
		//如果是冻结支付模式就做解冻处理
		if (PayModeCd.HOLD.equals(subMerAcct.getPaymodecd())) {
			input.setPayModeCd(subMerAcct.getPaymodecd());
			input.setOrigSubTrans(subMerInfo);
			input.setPayeeacctnbr(subMerInfo.getPayeeacctnbr());
			input.setTransamt(new BigDecimal(origAmt));
			input.setTranstime(merDate);
			getFDPSService().confirmedVirtualAcctPay(input);
		}
		BigDecimal transAmt = new BigDecimal(origAmt);
		RespQueryHostDate output = (RespQueryHostDate) this.getFDPSService()
				.queryHostDate(input);
		Date hostDate = output.getHostClearDate();
		OnlineTransExtendDAO.updateComfirmPay(isQueryHist, subMerInfo.getTransseqnbr(), subMerInfo.getSubtransseqnbr(),
				transAmt, context.getString(Dict.ORIG_MER_SEQ_NBR), merDate,hostDate);
	}

}
