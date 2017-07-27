/**
 * 
 */
package com.csii.upp.payment.action.end;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.ElecPortFlag;
import com.csii.upp.constant.PatternCd;
import com.csii.upp.constant.PayModeCd;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.constant.TransTypCd;
import com.csii.upp.constant.YN;
import com.csii.upp.dao.extend.OnlineTransExtendDAO;
import com.csii.upp.dao.generate.MeracctinfoDAO;
import com.csii.upp.dao.generate.OnlinesubtransDAO;
import com.csii.upp.dao.generate.OnlinesubtranshistDAO;
import com.csii.upp.dao.generate.OnlinetransDAO;
import com.csii.upp.dao.generate.OnlinetranshistDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Meracctinfo;
import com.csii.upp.dto.generate.Onlinesubtrans;
import com.csii.upp.dto.generate.OnlinesubtransExample;
import com.csii.upp.dto.generate.Onlinesubtranshist;
import com.csii.upp.dto.generate.OnlinesubtranshistExample;
import com.csii.upp.dto.generate.Onlinetrans;
import com.csii.upp.dto.generate.Onlinetranshist;
import com.csii.upp.dto.router.fundprocess.RespQueryCardInfo;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.payment.event.handler.NotifyDZKNResultEvent;
import com.csii.upp.payment.event.handler.NotifyMerResultEvent;
import com.csii.upp.supportor.DefaultSupportor;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 本交易适用与所有fundprocess通知payment更新交易状态场景： 1.他行网银和跨行智能支付异步回调通知 ; 2.交易超时日间异常处理通知;
 * 
 * @author xujin
 *
 */
public class NotifyTransResultAction extends PaymentOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		if (StringUtil.isStringEmpty(context.getString(Dict.TRANS_STATUS))) {
			throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.TRANS_STATUS });
		}
		if (YN.N.equals(context.getString(Dict.COMFIRM_PAYYN))) {
			InputPaymentData inputData = new InputPaymentData(context.getDataMap());
			Onlinetrans transData = null;
			try {
				transData = OnlinetransDAO.selectByPrimaryKey(inputData.getTransseqnbr());
				
			} catch (SQLException e) {
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}

			// 当前表没有信息不做任何处理，日终批量处理
			if (transData == null) {
				return;
			}
			// 更新流水
			inputData.setMernbr(transData.getMernbr());
			inputData.setMerseqnbr(transData.getMerseqnbr());
			inputData.setTransseqnbr(transData.getTransseqnbr());
			inputData.setTransamt(transData.getTransamt());
			inputData.setOrigseqnbr(transData.getOrigseqnbr());
			inputData.setMertransdatetime(transData.getMertransdatetime());
			inputData.setCleardate(transData.getCleardate());
			inputData.setTransdate(transData.getTransdate());
			inputData.setTranstime(transData.getTranstime());
			inputData.setPayeracctnbr(transData.getPayeracctnbr());
			if(StringUtil.isStringEmpty(transData.getPayeracctnbr())){
				inputData.setPayeracctnbr(inputData.getPayeracctnbr());
			}
			inputData.setPayercardtypcd(transData.getPayercardtypcd());
			//备用字段1：商户结果通知地址
			inputData.setMemo1(transData.getMemo1());
			inputData.setPayeridnbr(null);// 流水表没保存
			inputData.setPayeracctname(null);// 流水表没保存
			if(!StringUtil.isStringEmpty(context.getString(Dict.TRANS_SEQ_NO))){
				inputData.setDownsystransnbr(context.getString(Dict.TRANS_SEQ_NO));
			}
			if(!StringUtil.isObjectEmpty(inputData.getReceiptamt())){
				inputData.setReceiptamt(inputData.getReceiptamt().divide(new BigDecimal(100)));
			}
			log.info("NotifyTransResultAction-->支付方式inputData.getPaytypcd()=" + inputData.getPaytypcd());
			if(!StringUtil.isStringEmpty(inputData.getPaytypcd()) && (PayTypCd.OTHCYBER.equals(inputData.getPaytypcd()) || 
					PayTypCd.INTEL.equals(inputData.getPaytypcd()))){
				try {
					if (!StringUtil.isObjectEmpty(context.getData(Dict.HOST_CLEAR_DATE))) {
						log.info("NotifyTransResultAction-->他行支付，核心返回的HostClearDate=" + context.getData(Dict.HOST_CLEAR_DATE));
						inputData.setCleardate(DateUtil.DateFormat_To_Date(context.getData(Dict.HOST_CLEAR_DATE)));
					}
					
					OnlinesubtransExample example = new OnlinesubtransExample();
					example.createCriteria().andTransseqnbrEqualTo(inputData.getTransseqnbr());
					List<Onlinesubtrans> onlineSubTransList = OnlinesubtransDAO.selectByExample(example);
					for(Onlinesubtrans os:onlineSubTransList){
						if(PayModeCd.DIRECT.equals(MeracctinfoDAO.selectByPrimaryKey
								(os.getMernbr()).getPaymodecd())){
//							OnlinesubtransExample onlinesubtransExample = new OnlinesubtransExample();
//							onlinesubtransExample.createCriteria().andTransseqnbrEqualTo(inputData.getTransseqnbr());
//							
//							Onlinesubtrans updateRecord = new Onlinesubtrans();
//							updateRecord.setConfirmedcleardate(inputData.getCleardate());
//							OnlinesubtransDAO.updateByExampleSelective(updateRecord, onlinesubtransExample);
							os.setConfirmedcleardate(inputData.getCleardate());
							if(!StringUtil.isStringEmpty(inputData.getDownsystransnbr())){
								os.setDownsysseqnbr(inputData.getDownsystransnbr());
							}
							OnlinesubtransDAO.updateByPrimaryKey(os);
							log.info("NotifyTransResultAction-->他行支付，商户" + os.getMernbr() + "支付方式为直接支付，更新Confirmedcleardate=" + inputData.getCleardate() );
						}else {
							log.info("NotifyTransResultAction-->他行支付，商户" + os.getMernbr() + "支付模式为：" + MeracctinfoDAO.selectByPrimaryKey
									(os.getMernbr()).getPaymodecd());
						}
					}
					
				} catch (SQLException e) {
					throw new PeException(DictErrors.TRANS_EXCEPTION);
				}
			}
			
			// 退货成功才修改原交易信息
			if (TransTypCd.RETURN.equals(transData.getTranstypcd())
					&& TransStatus.SUCCESS.equals(context.getString(Dict.TRANS_STATUS))) {
				// 获得原交易信息
				this.initOrigTransInfo(inputData);
				// 退货成功,更新原交易订单
				OnlineTransExtendDAO.updateOrigTrans(inputData);
			}

			OnlineTransExtendDAO.updateTransStatus(inputData);

			// 支付成功做商户结果和电子口岸通知
			if (TransTypCd.PAY.equals(transData.getTranstypcd())
					&& TransStatus.SUCCESS.equals(context.getString(Dict.TRANS_STATUS))) {
				this.notifyResult(inputData);
			}
		} else {
			// 确认支付交易超时处理更新确认状态
			if (TransStatus.FAILURE.equals(context.getString(Dict.TRANS_STATUS))) {
				return;
			}
			this.updateComfirmPay(context);
		}
	}

	/**
	 * 商户结果和电子口岸通知
	 * 
	 * @param inputData
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void notifyResult(InputPaymentData inputData) {
		try {
			// 商户结果通知
			Meracctinfo merAcct = MeracctinfoDAO.selectByPrimaryKey(inputData.getMernbr());
			Map paramMap = new HashMap();
			paramMap.put(Dict.INPUT_PAYMENT_DATA, inputData);
			paramMap.put(Dict.RESP_CODE, ResponseCode.SUCCESS);
			paramMap.put(Dict.MER_PLATFORM_NBR, merAcct.getMerplatformnbr());
			NotifyMerResultEvent event = new NotifyMerResultEvent();
			event.setParamMap(paramMap);
			event.setNotifyService(getNotifyService());
			DefaultSupportor.getEventManager().doService(event);
		} catch (Exception e) {
			log.error(e);
		}
		if(!StringUtil.isStringEmpty(inputData.getPaytypcd()) && (PayTypCd.OTHCYBER.equals(inputData.getPaytypcd()) || 
				PayTypCd.INTEL.equals(inputData.getPaytypcd()))){
			//他行不在这里通知电子口岸
			return;
		}
		// 电子口岸通知
		try {
			BigDecimal transAmt = new BigDecimal(0);
			OnlinesubtransExample example = new OnlinesubtransExample();
			example.createCriteria().andTransseqnbrEqualTo(inputData.getTransseqnbr())
					.andElecportflagEqualTo(ElecPortFlag.ElecPortNotify);
			List<Onlinesubtrans> subTransList = OnlinesubtransDAO.selectByExample(example);
			if (subTransList.isEmpty() || subTransList.size() < 1) {
				OnlinesubtranshistExample exampleHist = new OnlinesubtranshistExample();
				exampleHist.createCriteria().andTransseqnbrEqualTo(inputData.getTransseqnbr())
						.andElecportflagEqualTo(ElecPortFlag.ElecPortNotify);
				List<Onlinesubtrans> subTransHistList = OnlinesubtransDAO.selectByExample(example);
				for (Onlinesubtrans subTrans : subTransHistList) {
					transAmt = transAmt.add(subTrans.getTransamt());
				}
			} else {
				for (Onlinesubtrans subTrans : subTransList) {
					transAmt = transAmt.add(subTrans.getTransamt());
				}
			}
			if (transAmt.compareTo(new BigDecimal(0)) == 0) {
				return;
			}
			if (StringUtil.isStringEmpty(inputData.getPayeridnbr())
					|| StringUtil.isStringEmpty(inputData.getPayeracctname())) {
				RespQueryCardInfo hostInfo = (RespQueryCardInfo) getFDPSService().queryCardInfo(inputData);
				String idCardNumber = hostInfo.getPayerIdNbr();
				String payerAcctName = hostInfo.getPayerAcctName();
				if ("01".equals(hostInfo.getPayerIdTypCd())) {
					if (idCardNumber.endsWith("x"))
						idCardNumber=idCardNumber.replace('x', 'X');
				}
				inputData.setPayeridnbr(idCardNumber);
				inputData.setPayeracctname(payerAcctName);
			}else {
				return;
			}
			// 跨境支付时姓名必须是中文字符
			Pattern partten = Pattern.compile(PatternCd.CNNAME);
			if (!partten.matcher(inputData.getPayeracctname()).matches())
				throw new PeException(DictErrors.CNNAMERROR, new Object[] { inputData.getPayeracctname() });
			Map paramMap = new HashMap();
			paramMap.put(Dict.INPUT_PAYMENT_DATA, inputData);
			paramMap.put(Dict.TOTAL_TRANS_AMT, transAmt);
			NotifyDZKNResultEvent event = new NotifyDZKNResultEvent();
			event.setScheduleNotify(false);
			event.setParamMap(paramMap);
			event.setNotifyService(this.getNotifyService());
			DefaultSupportor.getEventManager().doService(event);
		} catch (Exception e) {
			log.error(e);
		}
	}

	/**
	 * 获得原交易信息
	 * 
	 * @param transData
	 * @throws PeException
	 */
	private void initOrigTransInfo(InputPaymentData transData) throws PeException {
		// 查询原总交易流水
		Onlinetrans origTrans = null;
		try {
			origTrans = OnlinetransDAO.selectByPrimaryKey(transData.getOrigseqnbr());
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		if (origTrans == null) {
			Onlinetranshist origTransHist = null;
			try {
				origTransHist = OnlinetranshistDAO.selectByPrimaryKey(transData.getOrigseqnbr());
			} catch (SQLException e) {
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
			if (origTransHist == null) {
				throw new PeException(DictErrors.QUERY_ORIG_NOT_EXISTS);
			}
			if (TransStatus.ALL_WITHDRAW.equals(origTransHist.getTransstatus())
					|| TransStatus.SUB_WITHDRAW.equals(origTransHist.getTransstatus())) {
				throw new PeException(DictErrors.ORIG_TRANS_SUCCESS);
			}
			origTrans = new Onlinetrans();
			origTrans.setPayeracctnbr(transData.getPayeracctnbr());
			origTrans.setTransseqnbr(origTransHist.getTransseqnbr());
			origTrans.setTransamt(origTransHist.getTransamt());
			origTrans.setRefundedamt(origTransHist.getRefundedamt());
			// 查询当前表还是历史表：true:历史表,false:当前表
			transData.setQueryHistFlag(true);
		} else {
			if (TransStatus.ALL_WITHDRAW.equals(origTrans.getTransstatus())
					|| TransStatus.SUB_WITHDRAW.equals(origTrans.getTransstatus())) {
				throw new PeException(DictErrors.ORIG_TRANS_SUCCESS);
			}
		}
		// 为下一步退货交易提供原总交易明细信息
		transData.setOrigTrans(origTrans);

		OnlinesubtransExample example = new OnlinesubtransExample();
		example.createCriteria().andTransseqnbrEqualTo(transData.getTransseqnbr());
		List<Onlinesubtrans> onlineSubtranss = null;
		try {
			onlineSubtranss = OnlinesubtransDAO.selectByExample(example);
		} catch (SQLException e1) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		if (onlineSubtranss.isEmpty()) {
			throw new PeException(DictErrors.SUB_TRANS_NOT_EXST);
		}
		Onlinesubtrans origSubTrans = null;
		Onlinesubtrans onlineSubTrans = onlineSubtranss.get(0);
		// 查询原子交易流水
		if (transData.isQueryHistFlag()) {
			Onlinesubtranshist origOnlineSubTrans = null;
			try {
				origOnlineSubTrans = OnlinesubtranshistDAO.selectByPrimaryKey(onlineSubTrans.getSubtransseqnbr(),
						onlineSubTrans.getTransseqnbr());
			} catch (SQLException e) {
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
			if (origOnlineSubTrans == null) {
				throw new PeException(DictErrors.QUERY_ORIG_NOT_EXISTS);
			}
			origSubTrans = new Onlinesubtrans();
			origSubTrans.setPayeracctnbr(transData.getPayeeacctnbr());
			origSubTrans.setTransseqnbr(origOnlineSubTrans.getTransseqnbr());
			origSubTrans.setSubtransseqnbr(origOnlineSubTrans.getSubtransseqnbr());
			origSubTrans.setTransamt(origOnlineSubTrans.getTransamt());
			origSubTrans.setRefundedamt(origOnlineSubTrans.getRefundedamt());
		} else {
			try {
				origSubTrans = OnlinesubtransDAO.selectByPrimaryKey(onlineSubTrans.getSubtransseqnbr(),
						onlineSubTrans.getTransseqnbr());
			} catch (SQLException e) {
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
			if (origSubTrans == null) {
				throw new PeException(DictErrors.QUERY_ORIG_NOT_EXISTS);
			}
		}
		transData.setOrigSubTrans(origSubTrans);
	}

	/**
	 * 确认支付交易超时处理更新确认状态
	 * 
	 * @param inputData
	 * @throws PeException
	 */
	private void updateComfirmPay(Context context) throws PeException {
		String origMerSeqNbr = null;
		Date merTransDateTime = DateUtil.DateTimeFormat_To_Date(context.getString(Dict.MER_TRANS_DATE_TIME));
		String transSeqNbr = context.getString(Dict.TRANS_SEQ_NBR);
		String subTransSeqNbr = context.getString(Dict.SUB_TRANS_SEQ_NBR);
		BigDecimal transAmt = StringUtil.parseBigDecimal(context.getString(Dict.TRANS_AMT));
		Onlinetrans onlineTrans = null;
		Onlinetranshist onlineTransHist = null;
		boolean isQueryHist = false;// 查询当前表还是历史表：true:历史表,false:当前表
		try {
			onlineTrans = OnlinetransDAO.selectByPrimaryKey(transSeqNbr);
		} catch (SQLException e1) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		if (onlineTrans == null) {
			try {
				onlineTransHist = OnlinetranshistDAO.selectByPrimaryKey(transSeqNbr);
			} catch (SQLException e) {
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
			if (onlineTransHist != null) {
				origMerSeqNbr = onlineTransHist.getMerseqnbr();
				isQueryHist = true;
			} else {
				throw new PeException(DictErrors.QUERY_ORIG_NOT_EXISTS);
			}
		} else {
			origMerSeqNbr = onlineTrans.getMerseqnbr();
		}
		OnlineTransExtendDAO.updateComfirmPay(isQueryHist, transSeqNbr, subTransSeqNbr, transAmt, origMerSeqNbr,
				merTransDateTime);
	}
}
