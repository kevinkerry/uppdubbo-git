package com.csii.upp.batch.appl.paym;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.constant.CheckStatus;
import com.csii.upp.constant.ErrorTyp;
import com.csii.upp.constant.InteralFlag;
import com.csii.upp.constant.ProcStep;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.constant.TransTypCd;
import com.csii.upp.dao.extend.OnlinesubtranshistExtendDAO;
import com.csii.upp.dao.generate.OnlinesubtranshistDAO;
import com.csii.upp.dao.generate.OnlinetranshistDAO;
import com.csii.upp.dto.extend.CheckFdpsUpdateState;
import com.csii.upp.dto.generate.Batchcleartrans;
import com.csii.upp.dto.generate.Batchdownsystrans;
import com.csii.upp.dto.generate.Onlinesubtranshist;
import com.csii.upp.dto.generate.Onlinetranshist;

/*
 *  GFQ 
 */
public class CheckFdpsAppl extends BaseCheckFdpsAppl {
	/**
	 * 后台交易流水不存在时对账流程
	 * 
	 * @param cleartrans
	 * @param intervalDate
	 * @param clearEAccttrans
	 */
	@Override
	protected CheckFdpsUpdateState checkBatchDownSysTransNotExist(Batchcleartrans cleartrans, Date intervalDate) {
		CheckFdpsUpdateState fdpsupdateState = new CheckFdpsUpdateState();
		// 如果清算明细流水是intervalDate前(包括)的数据，设置清算明细流水交易状态为失败，交易阶段为对账失败，对账状态为对账不平，差错类型为下游无
		if (cleartrans.getTransdate().before(intervalDate)) {
			fdpsupdateState.setClearCheckStatus(CheckStatus.CHECKED);
			fdpsupdateState.setClearTransStatus(TransStatus.FAILURE);
			fdpsupdateState.setClearProcStep(ProcStep.CHECKED);
//			fdpsupdateState.setCheckErrorType(ErrorTyp.DOWNNOTEXIST);
		} else {
			fdpsupdateState = null;
		}
		return fdpsupdateState;
	}

	/**
	 * 后台交易流水存在时对账流程
	 * 
	 * @param cleartrans
	 * @param downtrans
	 * @param clearEAccttrans
	 */
	@Override
	protected CheckFdpsUpdateState checkBatchDownSysTransExist(Batchcleartrans cleartrans,
			Batchdownsystrans downtrans) {
		CheckFdpsUpdateState fdpsupdateState = new CheckFdpsUpdateState();

		// 检查金额关键字是否一致
		if (this.isKeyFieldEqualFdps(cleartrans, downtrans)) {
			// 清算明细流水与后台交易流水状态核对
			// PAYM的交易状态为成功FDPS交易状态成功 或 PAYM的交易状态为失败FDPS交易状态为失败 ： 对账平
			// PAYM的交易状态为超时，PAYM流水存在：对账平
			// PAYM的交易状态为成功、PAYM的交易状态为失败：对账不平-平台成功-下游失败
			// PAYM的交易状态为失败、PAYM的交易状态为成功：对账不平-平台失败-下游成功
			if ((TransStatus.SUCCESS.equals(downtrans.getTransstatus())
					&& (TransStatus.SUCCESS.equals(cleartrans.getTransstatus())
							|| TransStatus.SUB_WITHDRAW.equals(cleartrans.getTransstatus())
							|| TransStatus.ALL_WITHDRAW.equals(cleartrans.getTransstatus())))
					|| (TransStatus.FAILURE.equals(downtrans.getTransstatus())
							&& TransStatus.FAILURE.equals(cleartrans.getTransstatus()))) {
				// 设置对账状态为对账平、交易阶段为对账成功
				fdpsupdateState.setClearCheckStatus(CheckStatus.CHECKED);
				fdpsupdateState.setClearProcStep(ProcStep.CHECKED);
			} else if (TransStatus.TIMEOUT.equals(cleartrans.getTransstatus())
					|| TransStatus.PROCESSING.equals(cleartrans.getTransstatus())) {
				// 设置交易状态为FDPS的交易状态、对账状态为对账平、交易阶段为对账成功
				fdpsupdateState.setClearTransStatus(downtrans.getTransstatus());
				fdpsupdateState.setClearCheckStatus(CheckStatus.CHECKED);
				fdpsupdateState.setClearProcStep(ProcStep.CHECKED);
				// 如果交易类型为退货并且下游成功，更新原交易信息
				if (TransTypCd.RETURN.equals(cleartrans.getTranstypcd())
						&&TransStatus.SUCCESS.equals(downtrans.getTransstatus())) {
					// 更新原订单信息
					updateOriginal(cleartrans);
					// 更新原子订单信息
					updateSubOriginal(cleartrans);
				}
			} else if (TransStatus.FAILURE.equals(downtrans.getTransstatus())
					&& (TransStatus.SUCCESS.equals(cleartrans.getTransstatus())
							|| TransStatus.SUB_WITHDRAW.equals(cleartrans.getTransstatus())
							|| TransStatus.ALL_WITHDRAW.equals(cleartrans.getTransstatus()))) {
				// 设置交易状态为失败、对账状态为对账不平、交易阶段为对账失败、差错类型为平台成功-下游失败
				fdpsupdateState.setClearTransStatus(TransStatus.FAILURE);
				fdpsupdateState.setClearCheckStatus(CheckStatus.CHECKED);
				fdpsupdateState.setClearProcStep(ProcStep.CHECKED);
//				fdpsupdateState.setCheckErrorType(ErrorTyp.DOWNFAIL);
			} else if (TransStatus.SUCCESS.equals(downtrans.getTransstatus())
					&& TransStatus.FAILURE.equals(cleartrans.getTransstatus())) {
				// 设置交易状态为失败、对账状态为对账不平、交易阶段为对账失败、差错类型为平台失败-下游成功
				fdpsupdateState.setClearTransStatus(TransStatus.SUCCESS);
				fdpsupdateState.setClearCheckStatus(CheckStatus.CHECKED);
				fdpsupdateState.setClearProcStep(ProcStep.CHECKED);
//				fdpsupdateState.setCheckErrorType(ErrorTyp.UPFAIL);
				// 如果交易类型为退货，更新原交易信息
				if (TransTypCd.RETURN.equals(cleartrans.getTranstypcd())) {
					// 更新原订单信息
					updateOriginal(cleartrans);
					// 更新原子订单信息
					updateSubOriginal(cleartrans);
				}
			}
		} else {
			// 关键字缎不一致时处理，设置对账状态为对账不平，交易阶段为对账失败，差错类型为人工处理
			fdpsupdateState.setClearCheckStatus(CheckStatus.UNCHECKEDKEY);
			fdpsupdateState.setClearProcStep(ProcStep.UNCHECKED);
			fdpsupdateState.setCheckErrorType(ErrorTyp.ARTPROCESS);
		}
		return fdpsupdateState;
	}

	protected boolean isKeyFieldEqualFdps(Batchcleartrans cleartrans, Batchdownsystrans downtrans) {
		boolean result = false;
		result = this.isKeyFieldEqual(cleartrans, downtrans);
		return result;
	}

	public void updateOriginal(Batchcleartrans cleartrans) {
		try {
			Onlinetranshist originalhist = OnlinetranshistDAO.selectByPrimaryKey(cleartrans.getOrigseqnbr());
			if (originalhist != null) {
				// 已退货金额
				BigDecimal alreadyWithdrawAmount = (BigDecimal) originalhist.getRefundedamt();
				// 退货交易成功Onlinetranshist表中的退货金额等于原退货金额加上本次退货金额
				alreadyWithdrawAmount = alreadyWithdrawAmount.add(cleartrans.getTransamt());
				originalhist.setRefundedamt(alreadyWithdrawAmount);
				// 原交易尚未退货金额
				originalhist.setUnrefundamt(originalhist.getTransamt().subtract(alreadyWithdrawAmount));
				//积分交易
				if(InteralFlag.YES.equals(originalhist.getInteralflag())){
					BigDecimal alreadyRefunddeductamt = originalhist.getRefunddeductamt()==null?BigDecimal.ZERO:originalhist.getRefunddeductamt();
					alreadyRefunddeductamt = alreadyRefunddeductamt.add(cleartrans.getDeductamt());
					//积分抵扣金额已退
					originalhist.setRefunddeductamt(alreadyRefunddeductamt);
					//积分抵扣金额未退
					originalhist.setUnrefunddeductamt(originalhist.getDeductamt().subtract(alreadyRefunddeductamt));
				}				
				// 设置原订单交易状态
				if (alreadyWithdrawAmount.compareTo(originalhist.getTransamt()) == 0) {
					originalhist.setTransstatus(TransStatus.ALL_WITHDRAW);

				} else {
					originalhist.setTransstatus(TransStatus.SUB_WITHDRAW);
				}
				// 更新历史交易明细表原交易
				OnlinetranshistDAO.updateByPrimaryKeySelective(originalhist);
			}
		} catch (Exception e) {
			throw new PeRuntimeException(e);
		}
	}

	/**
	 * 更新退货子交易的原交易流水
	 * 
	 * @param order
	 * @author Fisher
	 * @date 2015-4-28
	 */
	private void updateSubOriginal(Batchcleartrans cleartrans) {
		/****** 查询退货交易子订单 *************/
		List<Onlinesubtranshist> subhistList = null;
		String transSeqNbr = cleartrans.getTransseqnbr();
		subhistList = OnlinesubtranshistExtendDAO.getOnlineSubTransHistBytransSeqNbr(transSeqNbr);
		try {
			for (Onlinesubtranshist subhist : subhistList) {
				/** 根据退货子订单查询原支付交易子订单 ***/
				Onlinesubtranshist originalsubhist = OnlinesubtranshistDAO.selectByPrimaryKey(
							subhist.getOrigsubtransseqnbr(), cleartrans.getOrigseqnbr());
				/** 修改原支付子订单相关金额 ***/
				// 已退货金额
				BigDecimal alreadyWithdrawAmount = (BigDecimal) originalsubhist.getRefundedamt();
				// 退货交易成功
				// order表中的退货金额等于原退货金额加上本次退货金额
				alreadyWithdrawAmount = alreadyWithdrawAmount.add(subhist.getTransamt());
				originalsubhist.setRefundedamt(alreadyWithdrawAmount);
				// 原交易尚未退货金额
				originalsubhist.setUnrefundamt(originalsubhist.getTransamt().subtract(alreadyWithdrawAmount));
				//积分交易
				if(InteralFlag.YES.equals(originalsubhist.getInteralflag())){
					BigDecimal alreadyRefunddeductamt = originalsubhist.getRefunddeductamt()==null?BigDecimal.ZERO:originalsubhist.getRefunddeductamt();
					alreadyRefunddeductamt = alreadyRefunddeductamt.add(subhist.getDeductamt());
					//积分抵扣金额已退
					originalsubhist.setRefunddeductamt(alreadyRefunddeductamt);
					//积分抵扣金额未退
					originalsubhist.setUnrefunddeductamt(originalsubhist.getDeductamt().subtract(alreadyRefunddeductamt));
				}	
				// 设置原子订单交易状态
				if (alreadyWithdrawAmount.compareTo(originalsubhist.getTransamt()) == 0) {
					originalsubhist.setTransstatus(TransStatus.ALL_WITHDRAW);
	
				} else {
					originalsubhist.setTransstatus(TransStatus.SUB_WITHDRAW);
				}
				// 新增使用transAmt2字段，记录未确认支付之前退货的总金额
				if (originalsubhist.getConfirmedcleardate() == null) {
					originalsubhist.setTransamt2(originalsubhist.getTransamt().subtract(alreadyWithdrawAmount));
				}				
				// 更新历史交易明细表原交易
				OnlinesubtranshistDAO.updateByPrimaryKeySelective(originalsubhist);
			}
		} catch (SQLException e) {
			throw new PeRuntimeException(e);
		}
	}
}
