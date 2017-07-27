package com.csii.upp.payment.action;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.action.BaseAction;
import com.csii.upp.constant.BankOptionCd;
import com.csii.upp.constant.CardTypCd;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.DepartmentNbr;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.LargeCard;
import com.csii.upp.constant.MerTransCtrlStatus;
import com.csii.upp.constant.PayStatus;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.SignStatus;
import com.csii.upp.constant.SignTypCd;
import com.csii.upp.constant.TransId;
import com.csii.upp.constant.YN;
import com.csii.upp.dao.extend.MertransctrlExtendDAO;
import com.csii.upp.dao.extend.SysinfoExtendDAO;
import com.csii.upp.dao.generate.BankoptionDAO;
import com.csii.upp.dao.generate.CardbininfoDAO;
import com.csii.upp.dao.generate.CusttransctrlDAO;
import com.csii.upp.dao.generate.MerbaseinfoDAO;
import com.csii.upp.dao.generate.OnlineorderinfoDAO;
import com.csii.upp.dao.generate.OnlineorderinfohistDAO;
import com.csii.upp.dao.generate.UserpaytypsigninfoDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Cardbininfo;
import com.csii.upp.dto.generate.Custtransctrl;
import com.csii.upp.dto.generate.Merbaseinfo;
import com.csii.upp.dto.generate.Onlineorderinfo;
import com.csii.upp.dto.generate.Onlineorderinfohist;
import com.csii.upp.dto.generate.Userpaytypsigninfo;
import com.csii.upp.dto.generate.UserpaytypsigninfoExample;
import com.csii.upp.encrypt.Encryption;
import com.csii.upp.service.payment.DXZPCheckService;
import com.csii.upp.service.payment.FDPSService;
import com.csii.upp.service.payment.NotifyService;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

public abstract class PaymentOnlineAction extends BaseAction {

	protected Date getPostDate() {
		return SysinfoExtendDAO.getSysInfo().getPostdate();
	}

	protected Encryption getPasswordService() {
		return (Encryption) getService("password");
	}

	/**
	 * 获取电信诈骗服务
	 * 
	 * @return
	 */
	protected DXZPCheckService getDXZPCheckService() {
		return (DXZPCheckService) getService(FundChannelCode.FDPS.toLowerCase());
	}
	
	/**
	 * 获取FDPS服务
	 * 
	 * @return
	 */
	protected FDPSService getFDPSService() {
		return (FDPSService) getService(FundChannelCode.FDPS.toLowerCase());
	}

	/**
	 * 获取通知服务
	 * 
	 * @return
	 */
	protected NotifyService getNotifyService() {
		return (NotifyService) getService("resultnotify");
	}

	/**
	 * 卡类型查询： 借记卡、贷记卡
	 */
	protected void queryCardType(InputPaymentData input) throws PeException {

		if (PayTypCd.FOSION.equals(input.getPaytypcd())) {
			String payeracctnbr = input.getPayeracctnbr();
			if(StringUtil.isStringEmpty(payeracctnbr)){
				throw new PeException(DictErrors.SYSTEMABNORMAL);
			}
			if (LargeCard.LARGECARDCHAT.equals(payeracctnbr.substring(8, 9))
					&& LargeCard.LARGECARDSIGN.equals(payeracctnbr.substring(0, 6))) {
				throw new PeException(DictErrors.LARGE_NOT_BE_SIGNED);
			}
		}
		String cardBinNbr = input.getPayeracctnbr().substring(0, 6);
		Cardbininfo cardBinInfo = null;
		try {
			cardBinInfo = CardbininfoDAO.selectByPrimaryKey(cardBinNbr);
			if (cardBinInfo == null) {
				throw new PeException(DictErrors.CARD_BIN_NOT_EXIST);
			}
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		input.setCardBinName(cardBinInfo.getCardbinname());
		input.setInnerCardFlag(cardBinInfo.getInnercardflag());
		if (CardTypCd.DEBIT.equals(cardBinInfo.getDrcrflag())) {
			input.setPayercardtypcd(CardTypCd.DEBIT);
		} else if (CardTypCd.CREDIT.equals(cardBinInfo.getDrcrflag())) {
			input.setPayercardtypcd(CardTypCd.CREDIT);
		}

	}

	/**
	 * 自动做用户支付方式签约
	 * 
	 * @param signPhoneNo
	 * @param signCardNbr
	 * @param payTypCd
	 * @throws PeException
	 */
	protected Userpaytypsigninfo autoSignUserPayTyp(final String signPhoneNo, final String signCardNbr,final String signDeptNbr,
			final String payTypCd) throws PeException {
		final Userpaytypsigninfo signRecord = new Userpaytypsigninfo();
		this.getTransactionTemplate().execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus arg0) {
				try {

					signRecord.setSigncardnbr(signCardNbr);
					signRecord.setSignmobile(signPhoneNo);
					signRecord.setSigntypcd(SignTypCd.USER_PAY_TYP);
					signRecord.setPaytypcd(payTypCd);
					signRecord.setSigndate(getPostDate());
					signRecord.setSignstatus(SignStatus.NORMA);
					signRecord.setSigneffdate(signRecord.getSigndate());
					signRecord.setSigndeptnbr(signDeptNbr);
					signRecord.setSignnbr(IDGenerateFactory.generateSeqId());
					UserpaytypsigninfoDAO.insert(signRecord);

					Custtransctrl ctrlRecord = new Custtransctrl();
					ctrlRecord.setPerdaylimit(new BigDecimal(
							BankoptionDAO.selectByPrimaryKey(BankOptionCd.USTL, 1L).getBankoptionvalue()));
					ctrlRecord.setPertranslimit(new BigDecimal(
							BankoptionDAO.selectByPrimaryKey(BankOptionCd.USDL, 1L).getBankoptionvalue()));
					ctrlRecord.setSignnbr(signRecord.getSignnbr());
					ctrlRecord.setSigntypcd(signRecord.getSigntypcd());
					CusttransctrlDAO.insert(ctrlRecord);
				} catch (SQLException e) {
					throw new PeRuntimeException(e);

				}
				return null;
			}

		});
		return signRecord;
	}

	protected void autoDeleteSignUser(final String signPhoneNo, final String signCardNbr,
			final String payTypCd) throws PeException {
		this.getTransactionTemplate().execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus arg0) {
				try {
					UserpaytypsigninfoExample example = new UserpaytypsigninfoExample();
					example.createCriteria().andSigncardnbrEqualTo(signCardNbr).andPaytypcdEqualTo(payTypCd);
					List<Userpaytypsigninfo> userpaytypsigninfo = UserpaytypsigninfoDAO.selectByExample(example);
					
					CusttransctrlDAO.deleteByPrimaryKey(userpaytypsigninfo.get(0).getSignnbr(), userpaytypsigninfo.get(0).getSigntypcd());
					
					UserpaytypsigninfoDAO.deleteByExample(example);

				} catch (SQLException e) {
					throw new PeRuntimeException(e);
				}
				return null;
			}

		});
	}
	/**
	 * 
	 * 功能：添加订单信息
	 * 作者：          
	 * @mender liru
	 * @param: Context
	 * @param: String payStatus(订单支付状态)
	 * @return: void
	 * @remack 1.判断商户是否存在 
	 *         2.根据商户号、商户流水号查询订单信息如无再查询历史订表
	 *         3.有记录查看原订单状态（1为该笔订单已经退货成功 3订单处理中 4该笔订单已取消）时程序终止返回
	 *         4.无记录时插入（交易日期、清算日期为资金平台的账务日期 、订单状态为处理中。渠道号为01）。历史表中有记录状态不为1、3、4时 插入订单信息记录日终时会删除历史表订单记录保留当前表最新订单记录
	 */
	protected void addOrderInfo(Context context, String payStatus) throws PeException {
		String merNbr = StringUtil.toStringAndTrim(context.getString(Dict.MER_NBR));//商户号
		String merSeqNo = StringUtil.toStringAndTrim(context.getData(Dict.MER_SEQ_NBR));//商户交易流水号
		Date merDateTime = DateUtil.DateTimeFormat_To_Date(StringUtil.toStringAndTrim(context.getData(Dict.MER_TRANS_DATE_TIME)));//商户交易时间
		String orderNbr = StringUtil.toStringAndTrim(context.getString(Dict.ORDER_NBR));//订单号
		if(log.isInfoEnabled()){//判断系统日志级别
			log.info(new StringBuilder().append("订单号[").append(orderNbr).append("]").append("商户号[").append(merNbr)
			.append("]").append("商户流水[").append(merSeqNo).append("]").append("商户时间[")
			.append(context.getData(Dict.MER_TRANS_DATE_TIME)).append("]").append("交易代码[")
			.append(context.getData(Dict.TRANS_CODE)).append("] 插入商户订单信息!").toString());
		}		
		try {
			Merbaseinfo merchant = MerbaseinfoDAO.selectByPrimaryKey(merNbr);//根据商户号查询商户信息
			if (merchant == null) {//判断商户是否存在。如商户信息不存在程序终止
				throw new PeException(DictErrors.MERCHANT_NOT_EXIST);
			}
			context.setData(Dict.MERCHANT_NAME, merchant.getMername());
			Onlineorderinfo order = OnlineorderinfoDAO.selectByPrimaryKey(merNbr, merSeqNo);//根据商户号、商户交易流水号查询订单信息.
			boolean isOrderHist = false;//设置是否是历史表
			String status = null;
			if (order == null) {//如果退款订单信息在当前表中为空，查询历史订单信息表
				Onlineorderinfohist orderHist = OnlineorderinfohistDAO.selectByPrimaryKey(merSeqNo, merNbr);
				if (orderHist != null) {
					status = orderHist.getPaystatus();//订单支付状态
					isOrderHist = true;//设置为历史表
				}
			}
			if (order == null && !isOrderHist) {//如果当前订单信息表中无此订单 ，插入订单信息到当前订单信息表
				// 如果订单信息为null 则insert
				Onlineorderinfo record = new Onlineorderinfo();
				record.setMernbr(merNbr);
				record.setMerseqnbr(merSeqNo);
				record.setMertranstime(merDateTime);
				record.setPaystatus(payStatus);
				Date transDate = SysinfoExtendDAO.getSysInfo().getPostdate();//查询资金平台的账务日期
				record.setTransdate(transDate);
				record.setCleardate(transDate);
				record.setMertransdate(DateUtil.DateFormat_To_Date(
						DateUtil.Date_To_DateTimeFormat(merDateTime, DateFormatCode.DATE_FORMATTER1)));
				record.setOrdernbr(orderNbr);
				record.setTransamt(StringUtil.parseBigDecimal(context.getString(Dict.TRANS_AMT)));
				record.setChannelnbr(context.getString(Dict.CHANNEL_NBR));
				record.setCurrencycd(context.getString(Dict.CURRENCY_CD));
				OnlineorderinfoDAO.insert(record);
				log.info("****************OrderInfo 订单成功插入 **********");
			} else {
				if (status == null) {
					status = order.getPaystatus();//订单支付状态
				}
				this.throwOrderInfoException(context, status);
				// 如果历史表是状态是待支付或支付失败则在当前表插入新的支付订单记录。日终时会删除历史表订单记录保留当前表最新订单记录
				if (isOrderHist) {
					Onlineorderinfo record = new Onlineorderinfo();
					record.setMernbr(merNbr);
					record.setMerseqnbr(merSeqNo);
					record.setMertranstime(merDateTime);
					record.setPaystatus(payStatus);
					Date transDate = SysinfoExtendDAO.getSysInfo().getPostdate();
					record.setTransdate(transDate);
					record.setCleardate(transDate);
					record.setMertransdate(DateUtil.DateFormat_To_Date(
							DateUtil.Date_To_DateTimeFormat(merDateTime, DateFormatCode.DATE_FORMATTER1)));
					record.setOrdernbr(orderNbr);
					record.setTransamt(StringUtil.parseBigDecimal(context.getString(Dict.TRANS_AMT)));
					record.setChannelnbr(context.getString(Dict.CHANNEL_NBR));
					record.setCurrencycd(context.getString(Dict.CURRENCY_CD));
					OnlineorderinfoDAO.insert(record);
					log.info("****************OrderInfo 订单成功插入 **********");
				}
			}

		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
	}
	/**
	 * 
	 * 功能：根据原订单信息状态返回相应信息
	 * 作者：
	 * 修改：liru
	 * @param:Context
	 * @param:String status(订单状态)
	 * @return:void
	 */
	protected void throwOrderInfoException(Context context, String status) throws PeException {
		// 设置不更新订单
		context.setData(Dict.UPD_ORDERYN, YN.N);

		if (PayStatus.PAY_STATUS_OK.equals(status)) {//支付状态为订单处理成功
			if(TransId.IPSR.equals(context.getString(Dict.TRANS_ID))){//退货
				log.info("****************OrderInfo 订单处理成功！**********");
				throw new PeException(DictErrors.RETURN_INFO_OK);
			}
			log.info("****************OrderInfo 订单处理成功！**********");
			throw new PeException(DictErrors.ORDER_INFO_OK);
		} else if (PayStatus.PAY_STATUS_HAND.equals(status)) {//支付状态为订单处理中
			log.info("****************OrderInfo 订单处理中！**********");
			throw new PeException(DictErrors.ORDER_INFO_HANDLE);
		} else if (PayStatus.PAY_STATUS_CANCEL.equals(status)) {//支付状态为订单取消
			log.info("****************OrderInfo 订单取消！**********");
			throw new PeException(DictErrors.ORDER_INFO_CANCEL);
		}
	}

	/**
	 * 计算清算机构编号
	 * 
	 * @param merNbr
	 * @return
	 * @throws PeException
	 */
	protected String getMerDepartmentnbr(String merNbr) throws PeException {
		List<String> merPayTyps = MertransctrlExtendDAO.queryMerPayTyp(merNbr, MerTransCtrlStatus.Y);
		if (merPayTyps.isEmpty()) {
			throw new PeException(DictErrors.MER_TRANS_CTR_ERROR);
		}
		if (merPayTyps.contains(PayTypCd.OTHCYBER) || merPayTyps.contains(PayTypCd.INTEL)
				||merPayTyps.contains(PayTypCd.OTHACTSCAN)||merPayTyps.contains(PayTypCd.OTHPASSCAN)) {
			return DepartmentNbr.THIRD;
		} else if (merPayTyps.contains(PayTypCd.OURCYBER) || merPayTyps.contains(PayTypCd.CARDPWD)
				|| merPayTyps.contains(PayTypCd.FOSION) || merPayTyps.contains(PayTypCd.EACCOUNT)
				|| merPayTyps.contains(PayTypCd.POINT)) {
			return DepartmentNbr.OUR;
		} else {
			throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.DEPARTMENT_NBR });
		}
	}
	
	/**
	 * false:直接做支付结果通知;true:等待异步回调进行支付结果通知
	 * 
	 * @param payTypCD
	 * @return
	 */
	protected boolean isCallBackPayResult(String payTypCD) {
		if (PayTypCd.OTHCYBER.equals(payTypCD) || PayTypCd.INTEL.equals(payTypCD)
				||PayTypCd.OTHACTSCAN.equals(payTypCD)) {
			return true;
		}
		return false;
	}
	
}
