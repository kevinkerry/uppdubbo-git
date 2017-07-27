package com.csii.upp.dao.extend;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.csii.pe.core.PeException;
import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.constant.InteralFlag;
import com.csii.upp.constant.PayStatus;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dao.generate.OnlineorderinfoDAO;
import com.csii.upp.dao.generate.OnlinesubtransDAO;
import com.csii.upp.dao.generate.OnlinesubtranshistDAO;
import com.csii.upp.dao.generate.OnlinetransDAO;
import com.csii.upp.dao.generate.OnlinetranshistDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Onlineorderinfo;
import com.csii.upp.dto.generate.Onlinesubtrans;
import com.csii.upp.dto.generate.OnlinesubtransExample;
import com.csii.upp.dto.generate.Onlinesubtranshist;
import com.csii.upp.dto.generate.Onlinetrans;
import com.csii.upp.dto.generate.Onlinetranshist;

public class OnlineTransExtendDAO extends BasePayDAO {
	/**
	 * 根据客户账号，交易类型，开始、初始时间查询客户交易信息
	 * 
	 * @param map
	 * @return List<Map>
	 */
	public static List<Map> qryTrsDetailResult(Map map) {

		return queryForList("ONLINETRANSEXTEND.queryTrsDetail", map);
	}

	/**
	 * 查询丰收e支付退货订单详情
	 * 
	 * @author gaoqi
	 */

	public static List<Map> selectFoisonWithdrawList(Map map) {

		return queryForList("ONLINETRANSEXTEND.selectFoisonWithdrawList", map);
	}

	/**
	 * 查询丰收e支付总支付条数
	 * 
	 * @author gaoqi
	 */

	public static int countTrans(Map<String, String> map) {

		return (Integer) queryForObject("ONLINETRANSEXTEND.countTrans", map);
	}

	/**
	 * 查询丰收e支付退货总条数
	 */

	public static int selectFoisonWithdrawListCount(Map<String, String> map) {

		return (Integer) queryForObject("ONLINETRANSEXTEND.selectFoisonWithdrawListCount", map);
	}

	/**
	 * 将待对账的OnlineTrans（总交易明细表）数据插入OnlineTransHist（总交易明细历史表）
	 * 
	 * @author chen chao
	 */
	public static void insertOnlineTransToHist(Map<String, Object> map) {
		getSqlMap().insert("ONLINETRANSEXTEND.insertOnlineTransToHist", map);
	}

	/**
	 * 将待状态的OnlineTrans（总交易明细表）数据插入BatchClearTrans（清算交易明细表）
	 * 
	 * @author chen chao
	 */
	public static void insertOnlineTransToBatchClear(Map<String, Object> map) {
		getSqlMap().insert("ONLINETRANSEXTEND.insertOnlineTransToBatchClear", map);
	}

	/**
	 * 支付交易状态更新
	 * 
	 * @param input
	 */
	public static void updateTransStatus(final InputPaymentData input) {
		getTransactionTemplate().execute(new TransactionCallback() {
			@Override
			public Object doInTransaction(TransactionStatus arg0) {
				try {
					OnlinesubtransExample onlinesubtransExample = new OnlinesubtransExample();
					onlinesubtransExample.createCriteria().andTransseqnbrEqualTo(input.getTransseqnbr());
					Onlinesubtrans updateRecord = new Onlinesubtrans();
					updateRecord.setTransstatus(input.getTransstatus());
					updateRecord.setPayeracctnbr(input.getPayeracctnbr());
					updateRecord.setPayercardtypcd(input.getPayercardtypcd());
					updateRecord.setCleardate(input.getCleardate());
					updateRecord.setDownsysseqnbr(input.getDownsystransnbr());
					OnlinesubtransDAO.updateByExampleSelective(updateRecord, onlinesubtransExample);

					Onlinetrans onlineTrans = new Onlinetrans();
					onlineTrans.setTransseqnbr(input.getTransseqnbr());
					onlineTrans.setTransstatus(input.getTransstatus());
					onlineTrans.setPayeracctnbr(input.getPayeracctnbr());
					onlineTrans.setPayercardtypcd(input.getPayercardtypcd());
					onlineTrans.setCleardate(input.getCleardate());
					onlineTrans.setDownsystransnbr(input.getDownsystransnbr());
					OnlinetransDAO.updateByPrimaryKeySelective(onlineTrans);

					Onlineorderinfo onlineorderinfo = new Onlineorderinfo();
					onlineorderinfo.setMernbr(input.getMernbr());
					onlineorderinfo.setMerseqnbr(input.getMerseqnbr());
					if (TransStatus.FAILURE.equals(input.getTransstatus())) {
						onlineorderinfo.setPaystatus(PayStatus.PAY_STATUS_FAIL);
					} else if (TransStatus.SUCCESS.equals(input.getTransstatus())) {
						onlineorderinfo.setPaystatus(PayStatus.PAY_STATUS_OK);
					} else {
						onlineorderinfo.setPaystatus(PayStatus.PAY_STATUS_HAND);
					}
					OnlineorderinfoDAO.updateByPrimaryKeySelective(onlineorderinfo);
				} catch (SQLException e) {
					throw new PeRuntimeException(e);
				}
				return null;
			}
		});
	}

	/**
	 * 退货成功,更新原交易订单
	 * 
	 * @param inputData
	 * @throws SQLException
	 */
	public static void updateOrigTrans(final InputPaymentData inputData) throws PeException {
		getTransactionTemplate().execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus arg0) {
				try {
					// 更新原总交易明细信息
					updateOrigOnlineTrans(inputData);
					// 更新原子交易明细信息
					updateOrigOnlineSubTrans(inputData);
				} catch (Exception e) {
					throw new PeRuntimeException(e);
				}
				return null;
			}
		});
	}

	/**
	 * 更新子交易明细表信息
	 * 
	 * @param inputData
	 * @throws SQLException
	 */
	private static void updateOrigOnlineSubTrans(InputPaymentData inputData) throws SQLException {
		// 原子交易明细信息
		Onlinesubtrans origSubTrans = inputData.getOrigSubTrans();
		// 本次交易前原订单已退货金额
		BigDecimal alreadyWthAmt = origSubTrans.getRefundedamt() == null ? BigDecimal.ZERO
				: origSubTrans.getRefundedamt();
		// 原订单的已退货金额等于本次交易前的已退货金额加上本次退货金额
		alreadyWthAmt = alreadyWthAmt.add(inputData.getTransamt());

		//// 查询当前表还是历史表：true:历史表,false:当前表
		if (!inputData.isQueryHistFlag()) {
			Onlinesubtrans updSubTrans = new Onlinesubtrans();
			updSubTrans.setSubtransseqnbr(origSubTrans.getSubtransseqnbr());
			updSubTrans.setTransseqnbr(origSubTrans.getTransseqnbr());
			updSubTrans.setRefundedamt(alreadyWthAmt);
			// 原订单的未退货金额为原订单金额-已退货金额
			updSubTrans.setUnrefundamt(origSubTrans.getTransamt().subtract(alreadyWthAmt));
			if(InteralFlag.YES.equals(origSubTrans.getInteralflag())){
				BigDecimal alreadyRefunddeductamt = origSubTrans.getRefunddeductamt()==null?BigDecimal.ZERO:origSubTrans.getRefunddeductamt();
				alreadyRefunddeductamt = alreadyRefunddeductamt.add(inputData.getDeductamt());
				//积分抵扣金额已退
				updSubTrans.setRefunddeductamt(alreadyRefunddeductamt);
				//积分抵扣金额未退
				updSubTrans.setUnrefunddeductamt(origSubTrans.getDeductamt().subtract(alreadyRefunddeductamt));
			}
			// 设置原交易状态为已经退货,原交易日志修改时间为当前日期时间
			if (alreadyWthAmt.compareTo(origSubTrans.getTransamt()) == 0) {
				// 全部退货
				updSubTrans.setTransstatus(TransStatus.ALL_WITHDRAW);
			} else {
				// 部分退货
				updSubTrans.setTransstatus(TransStatus.SUB_WITHDRAW);
			}
			// 新增使用transAmt2字段，记录未确认支付之前退货的总金额
			if (origSubTrans.getConfirmedcleardate() == null) {
				updSubTrans.setTransamt2(origSubTrans.getTransamt().subtract(alreadyWthAmt));
			}
			OnlinesubtransDAO.updateByPrimaryKeySelective(updSubTrans);
		} else {
			Onlinesubtranshist updSubTrans = new Onlinesubtranshist();
			updSubTrans.setSubtransseqnbr(origSubTrans.getSubtransseqnbr());
			updSubTrans.setTransseqnbr(origSubTrans.getTransseqnbr());
			updSubTrans.setRefundedamt(alreadyWthAmt);
			// 原订单的未退货金额为原订单金额-已退货金额
			updSubTrans.setUnrefundamt(origSubTrans.getTransamt().subtract(alreadyWthAmt));
			if(InteralFlag.YES.equals(origSubTrans.getInteralflag())){
				BigDecimal alreadyRefunddeductamt = origSubTrans.getRefunddeductamt()==null?BigDecimal.ZERO:origSubTrans.getRefunddeductamt();
				alreadyRefunddeductamt = alreadyRefunddeductamt.add(inputData.getDeductamt());
				//积分抵扣金额已退
				updSubTrans.setRefunddeductamt(alreadyRefunddeductamt);
				//积分抵扣金额未退
				updSubTrans.setUnrefunddeductamt(origSubTrans.getDeductamt().subtract(alreadyRefunddeductamt));
			}
			// 设置原交易状态为已经退货,原交易日志修改时间为当前日期时间
			if (alreadyWthAmt.compareTo(origSubTrans.getTransamt()) == 0) {
				// 全部退货
				updSubTrans.setTransstatus(TransStatus.ALL_WITHDRAW);
			} else {
				// 部分退货
				updSubTrans.setTransstatus(TransStatus.SUB_WITHDRAW);
			}
			// 新增使用transAmt2字段，记录未确认支付之前退货的总金额
			if (origSubTrans.getConfirmedcleardate() == null) {
				updSubTrans.setTransamt2(origSubTrans.getTransamt().subtract(alreadyWthAmt));
			}
			OnlinesubtranshistDAO.updateByPrimaryKeySelective(updSubTrans);
		}
	}

	/**
	 * 更新总交易明细表信息
	 * 
	 * @param inputData
	 * @throws SQLException
	 */
	private static void updateOrigOnlineTrans(InputPaymentData inputData) throws SQLException {
		// 原总交易明细信息
		Onlinetrans origTrans = inputData.getOrigTrans();
		// 本次交易前原订单已退货金额
		BigDecimal alreadyWthAmt = origTrans.getRefundedamt() == null ? BigDecimal.ZERO : origTrans.getRefundedamt();
		// 原订单的已退货金额等于本次交易前的已退货金额加上本次退货金额
		alreadyWthAmt = alreadyWthAmt.add(inputData.getTransamt());

		//// 查询当前表还是历史表：true:历史表,false:当前表
		if (!inputData.isQueryHistFlag()) {
			Onlinetrans updTrans = new Onlinetrans();
			updTrans.setTransseqnbr(origTrans.getTransseqnbr());
			updTrans.setRefundedamt(alreadyWthAmt);
			// 原订单的未退货金额为原订单金额-已退货金额
			updTrans.setUnrefundamt(origTrans.getTransamt().subtract(alreadyWthAmt));
			if(InteralFlag.YES.equals(origTrans.getInteralflag())){
				BigDecimal alreadyRefunddeductamt = origTrans.getRefunddeductamt()==null?BigDecimal.ZERO:origTrans.getRefunddeductamt();
				alreadyRefunddeductamt = alreadyRefunddeductamt.add(inputData.getDeductamt());
				//积分抵扣金额已退
				updTrans.setRefunddeductamt(alreadyRefunddeductamt);
				//积分抵扣金额未退
				updTrans.setUnrefunddeductamt(origTrans.getDeductamt().subtract(alreadyRefunddeductamt));
			}
			// 设置原交易状态为已经退货,原交易日志修改时间为当前日期时间
			if (alreadyWthAmt.compareTo(origTrans.getTransamt()) == 0) {
				// 全部退货
				updTrans.setTransstatus(TransStatus.ALL_WITHDRAW);
			} else {
				// 部分退货
				updTrans.setTransstatus(TransStatus.SUB_WITHDRAW);
			}
			OnlinetransDAO.updateByPrimaryKeySelective(updTrans);
		} else {
			Onlinetranshist updTrans = new Onlinetranshist();
			updTrans.setTransseqnbr(origTrans.getTransseqnbr());
			updTrans.setRefundedamt(alreadyWthAmt);
			// 原订单的未退货金额为原订单金额-已退货金额
			updTrans.setUnrefundamt(origTrans.getTransamt().subtract(alreadyWthAmt));
			if(InteralFlag.YES.equals(origTrans.getInteralflag())){
				BigDecimal alreadyRefunddeductamt = origTrans.getRefunddeductamt()==null?BigDecimal.ZERO:origTrans.getRefunddeductamt();
				alreadyRefunddeductamt = alreadyRefunddeductamt.add(inputData.getDeductamt());
				//积分抵扣金额已退
				updTrans.setRefunddeductamt(alreadyRefunddeductamt);
				//积分抵扣金额未退
				updTrans.setUnrefunddeductamt(origTrans.getDeductamt().subtract(alreadyRefunddeductamt));
			}
			// 设置原交易状态为已经退货,原交易日志修改时间为当前日期时间
			if (alreadyWthAmt.compareTo(origTrans.getTransamt()) == 0) {
				// 全部退货
				updTrans.setTransstatus(TransStatus.ALL_WITHDRAW);
			} else {
				// 部分退货
				updTrans.setTransstatus(TransStatus.SUB_WITHDRAW);
			}
			OnlinetranshistDAO.updateByPrimaryKeySelective(updTrans);
		}
	}

	/**
	 * 确认支付交易更新确认状态
	 * 
	 * @param inputData
	 * @throws PeException
	 */
	public static void updateComfirmPay(final boolean isQueryHist, final String transSeqNbr,
			final String subTransSeqNbr, final BigDecimal transAmt, final String origMerSeqNbr,
			final Date merTransDateTime) throws PeException {
		final Date payTransDate = SysinfoExtendDAO.getSysInfo().getPostdate();
		// 更新子订单信息和总订单信息
		getTransactionTemplate().execute(new TransactionCallback() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public Object doInTransaction(TransactionStatus arg0) {
				try {
					// 更新子订单信息
					if (isQueryHist) {
						Onlinesubtranshist record1 = new Onlinesubtranshist();
						record1.setTransseqnbr(transSeqNbr);
						record1.setSubtransseqnbr(subTransSeqNbr);
						record1.setConfirmedpayamt(transAmt);
						record1.setConfirmedpaydate(payTransDate);
						record1.setConfirmedcleardate(payTransDate);
						record1.setConfirmedmerseqnbr(origMerSeqNbr);
						record1.setConfirmedmerdatetime(merTransDateTime);
						if (OnlinesubtranshistDAO.updateByPrimaryKeySelective(record1) < 1) {
							throw new PeRuntimeException(DictErrors.SUB_TRANS_NOT_EXST);
						}
					} else {
						Onlinesubtrans record = new Onlinesubtrans();
						record.setTransseqnbr(transSeqNbr);
						record.setSubtransseqnbr(subTransSeqNbr);
						record.setConfirmedpayamt(transAmt);
						record.setConfirmedpaydate(payTransDate);
						record.setConfirmedcleardate(payTransDate);
						record.setConfirmedmerseqnbr(origMerSeqNbr);
						record.setConfirmedmerdatetime(merTransDateTime);
						if (OnlinesubtransDAO.updateByPrimaryKeySelective(record) < 1) {
							throw new PeRuntimeException(DictErrors.SUB_TRANS_NOT_EXST);
						}
					}
					Map map = new HashMap();
					map.put(Dict.TRANS_SEQ_NBR, transSeqNbr);
					map.put(Dict.TRANS_AMT, transAmt);
					int result = 0;
					if (isQueryHist) {
						result = getSqlMap().update("ONLINETRANSHISTEXTEND.updateOnlineTransHist", map);
					} else {
						result = getSqlMap().update("ONLINETRANSEXTEND.updateOnlineTrans", map);
					}
					// 更新总订单信息
					if (result < 1) {
						throw new PeRuntimeException(DictErrors.TRANS_NOT_EXST);
					}
				} catch (Exception e) {
					throw new PeRuntimeException(e);
				}
				return null;
			}
		});
	}

	/**
	 * 确认支付交易更新确认状态
	 * 
	 * @param inputData
	 * @throws PeException
	 */
	public static void updateComfirmPay(final boolean isQueryHist, final String transSeqNbr,
			final String subTransSeqNbr, final BigDecimal transAmt, final String origMerSeqNbr,
			final Date merTransDateTime, final Date payTransDate) throws PeException {
		final Date postDate = SysinfoExtendDAO.getSysInfo().getPostdate();
		// 更新子订单信息和总订单信息
		getTransactionTemplate().execute(new TransactionCallback() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public Object doInTransaction(TransactionStatus arg0) {
				try {
					// 更新子订单信息
					if (isQueryHist) {
						Onlinesubtranshist record1 = new Onlinesubtranshist();
						record1.setTransseqnbr(transSeqNbr);
						record1.setSubtransseqnbr(subTransSeqNbr);
						record1.setConfirmedpayamt(transAmt);
						record1.setConfirmedpaydate(postDate);
						record1.setConfirmedcleardate(payTransDate);
						record1.setConfirmedmerseqnbr(origMerSeqNbr);
						record1.setConfirmedmerdatetime(merTransDateTime);
						if (OnlinesubtranshistDAO.updateByPrimaryKeySelective(record1) < 1) {
							throw new PeRuntimeException(DictErrors.SUB_TRANS_NOT_EXST);
						}
					} else {
						Onlinesubtrans record = new Onlinesubtrans();
						record.setTransseqnbr(transSeqNbr);
						record.setSubtransseqnbr(subTransSeqNbr);
						record.setConfirmedpayamt(transAmt);
						record.setConfirmedpaydate(postDate);
						record.setConfirmedcleardate(payTransDate);
						record.setConfirmedmerseqnbr(origMerSeqNbr);
						record.setConfirmedmerdatetime(merTransDateTime);
						if (OnlinesubtransDAO.updateByPrimaryKeySelective(record) < 1) {
							throw new PeRuntimeException(DictErrors.SUB_TRANS_NOT_EXST);
						}
					}
					Map map = new HashMap();
					map.put(Dict.TRANS_SEQ_NBR, transSeqNbr);
					map.put(Dict.TRANS_AMT, transAmt);
					int result = 0;
					if (isQueryHist) {
						result = getSqlMap().update("ONLINETRANSHISTEXTEND.updateOnlineTransHist", map);
					} else {
						result = getSqlMap().update("ONLINETRANSEXTEND.updateOnlineTrans", map);
					}
					// 更新总订单信息
					if (result < 1) {
						throw new PeRuntimeException(DictErrors.TRANS_NOT_EXST);
					}
				} catch (Exception e) {
					throw new PeRuntimeException(e);
				}
				return null;
			}
		});
	}
}
