package com.csii.upp.batch.appl.paym;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.csii.pe.core.PeException;
import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.batch.appl.base.BaseAppl;
import com.csii.upp.constant.ClearClassModeCd;
import com.csii.upp.constant.FeeMode;
import com.csii.upp.constant.FeeReturnFlag;
import com.csii.upp.constant.MerStatus;
import com.csii.upp.constant.ProcStep;
import com.csii.upp.constant.SettMode;
import com.csii.upp.constant.SettPeriod;
import com.csii.upp.constant.SettleStatus;
import com.csii.upp.constant.StandBookTypCD;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.constant.TransTypCd;
import com.csii.upp.dao.extend.BatchConfirmSubClearTransExtendDAO;
import com.csii.upp.dao.extend.OnlinesubtranshistExtendDAO;
import com.csii.upp.dao.generate.AgencyDAO;
import com.csii.upp.dao.generate.BatchconfirmsubcleartransDAO;
import com.csii.upp.dao.generate.BatchfeeprofittransDAO;
import com.csii.upp.dao.generate.BatchmertransDAO;
import com.csii.upp.dao.generate.DeptacctinfoDAO;
import com.csii.upp.dao.generate.FeeparamDAO;
import com.csii.upp.dao.generate.MeracctinfoDAO;
import com.csii.upp.dao.generate.MerfeerelDAO;
import com.csii.upp.dao.generate.OnlinesubtranshistDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.generate.Agency;
import com.csii.upp.dto.generate.Batchconfirmsubcleartrans;
import com.csii.upp.dto.generate.BatchconfirmsubcleartransExample;
import com.csii.upp.dto.generate.Batchfeeprofittrans;
import com.csii.upp.dto.generate.Batchmertrans;
import com.csii.upp.dto.generate.BatchmertransExample;
import com.csii.upp.dto.generate.Deptacctinfo;
import com.csii.upp.dto.generate.Feeparam;
import com.csii.upp.dto.generate.FeeparamExample;
import com.csii.upp.dto.generate.Meracctinfo;
import com.csii.upp.dto.generate.MeracctinfoExample;
import com.csii.upp.dto.generate.Merfeerel;
import com.csii.upp.dto.generate.MerfeerelExample;
import com.csii.upp.dto.generate.Onlinesubtranshist;
import com.csii.upp.dto.generate.OnlinesubtranshistExample;
import com.csii.upp.dto.generate.Onlinetranshist;
import com.csii.upp.service.FeeService;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;
/**
 * 清分
 * @author 姜星
 *
 */
public class DataClearingAppl extends BaseAppl {

	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps) throws Exception {
		final Date checkDate = getCheckDate(argMaps);
		// 直接清分（清分一级商户）
		final List<Onlinetranshist> dirtList = BatchConfirmSubClearTransExtendDAO
				.queryOnlineTransList(ClearClassModeCd.DIRT);
		getTransactionTemplate().execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus arg0) {
				for (Onlinetranshist dirt : dirtList) {
					try {
						if (TransTypCd.PAY.equals(dirt.getTranstypcd())) {
							// 交易类型为支付的处理
							payClearing_DIRT(dirt,checkDate);
						} else if (TransTypCd.RETURN.equals(dirt.getTranstypcd())) {
							// 支付类型为退货的处理
							returnClearing_DIRT(dirt,checkDate);
						}
					} catch (Exception ex) {
						throw new PeRuntimeException(ex);
					}
				}
				return null;
			}
		});

		// 一口清分（清分二级商户）
		final List<Batchconfirmsubcleartrans> biteList = BatchConfirmSubClearTransExtendDAO
				.queryConfirmTransList(ClearClassModeCd.BITE);
		getTransactionTemplate().execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus arg0) {
				for (Batchconfirmsubcleartrans bite : biteList) {
					try {
						if (TransTypCd.PAY.equals(bite.getTranstypcd())) {
							// 交易类型为支付的处理
							payClearing_BITE(bite,checkDate);
						} else if (TransTypCd.RETURN.equals(bite.getTranstypcd())) {
							// 支付类型为退货的处理
							returnClearing_BITE(bite,checkDate);
						}
					} catch (Exception ex) {
						throw new PeRuntimeException(ex);
					}
				}
				return null;
			}
		});

		// 递进清分（清分一级、二级商户）
		final List<Onlinetranshist> stepList_1 = BatchConfirmSubClearTransExtendDAO
				.queryOnlineTransList(ClearClassModeCd.STEP);
		getTransactionTemplate().execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus arg0) {
				for (Onlinetranshist dirt : stepList_1) {
					try {
						if (TransTypCd.PAY.equals(dirt.getTranstypcd())) {
							// 交易类型为支付的处理
							payClearing_DIRT(dirt,checkDate);
						}
					} catch (Exception ex) {
						throw new PeRuntimeException(ex);
					}
				}
				return null;
			}
		});
		final List<Batchconfirmsubcleartrans> stepList_2 = BatchConfirmSubClearTransExtendDAO
				.queryConfirmTransList(ClearClassModeCd.STEP);
		getTransactionTemplate().execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus arg0) {
				for (Batchconfirmsubcleartrans step : stepList_2) {
					try {
						if (TransTypCd.PAY.equals(step.getTranstypcd())) {
							// 交易类型为支付的处理
							payClearing_STEP(step,checkDate);
						} else if (TransTypCd.RETURN.equals(step.getTranstypcd())) {
							// 支付类型为退货的处理
							returnClearing_BITE(step,checkDate);
						}
					} catch (Exception ex) {
						throw new PeRuntimeException(ex);
					}
				}
				return null;
			}
		});

		int monthFeeDay = StringUtil.toInt(argMaps.get(Dict.MTFD).toString());
		// 如果是包月手续费收取日，生成所有包月商户的手续费划转台账，以及对应利润分配的台账
		if (DateUtil.isMonthFeeDay(checkDate, monthFeeDay)) {
			// 手续费包月的商户手续费收取 （这里将所有商户放在同一个事务中处理，以减少事务数）
			MeracctinfoExample example = new MeracctinfoExample();
			example.createCriteria().andMerstatusEqualTo(MerStatus.NORMAL);
			final List<Meracctinfo> merchants = MeracctinfoDAO.selectByExample(example);
			log.info("list*********************************" + merchants.size());
			getTransactionTemplate().execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus arg0) {
					if (merchants != null) {
						for (Iterator<Meracctinfo> it = merchants.iterator(); it.hasNext();) {
							Meracctinfo merchant = (Meracctinfo) it.next();
							//包月手续费查重
							BatchmertransExample batchmertransExample = new BatchmertransExample();
							batchmertransExample.createCriteria().andMernbrEqualTo(merchant.getMernbr()).andCleardateEqualTo(checkDate);
							List<Batchmertrans> batchmertrans = null;
							try {
								batchmertrans = BatchmertransDAO.selectByExample(batchmertransExample);
							} catch (SQLException e) {
								e.printStackTrace();
							}
							if (batchmertrans == null) {
								try {
									if (FeeMode.MON.equals(merchant.getFeemode())) {
										clearMonthMerchant(merchant,checkDate);
									}
								} catch (Exception ex) {
									log.error("清分包月商户[" + merchant.getMernbr() + "-" + merchant.getMername() + "]手续费失败",
											ex);
									throw new PeRuntimeException(ex);
								}
							}
						}
					}
					return null;
				}
			});
		}
	}

	/**
	 * 一口清分支付清分
	 */
	private void payClearing_BITE(Batchconfirmsubcleartrans cfm,Date checkDate) {
		// 获取清算机构账户信息
		Deptacctinfo acct = getDeptAcctInfo(cfm.getMerdevdeptnbr(),cfm.getTransmode());
		// 商户账户信息查询
		Meracctinfo acctinfo = null;
		try {
			acctinfo = MeracctinfoDAO.selectByPrimaryKey(cfm.getMernbr());
			if(acctinfo == null){
				throw new PeException("一口清分支付清分未配置商户账户信息【"+cfm.getMernbr()+"】");
			}
		} catch (Exception e) {
			throw new PeRuntimeException(e);
		}
		// 收取商户手续费
		BigDecimal feeAmount = payFeeClearing_BITE(cfm, acct, acctinfo,checkDate);
		// 商户支付台账汇总
		paySettlement_BITE(cfm,acct,feeAmount, acctinfo,checkDate);
		// 分润处理
		profitAssign_BITE(cfm,acct,feeAmount, acctinfo,checkDate);
		// 如果订单状态没有被设置为已结算,则置为清分成功
		if(acctinfo.getSettperiod().equals(SettPeriod.REALTIME)){ 
			cfm.setProcstep(ProcStep.SETTLED);
		} else {
			cfm.setProcstep(ProcStep.CLEARED);
		}
		
		try {
			BatchconfirmsubcleartransDAO.updateByPrimaryKeySelective(cfm);
		} catch (SQLException e) {
			throw new PeRuntimeException(e);
		}
		// 更新子历史交易明细表的处理阶段
		Onlinesubtranshist record = new Onlinesubtranshist();
		record.setTransseqnbr(cfm.getTransseqnbr());
		record.setSubtransseqnbr(cfm.getSubtransseqnbr());
		//记录手续费  modified by wangtao 20161214 
		record.setFeeamt(feeAmount);	
		record.setProcstep(cfm.getProcstep());
		try {
			OnlinesubtranshistDAO.updateByPrimaryKeySelective(record);
		} catch (SQLException e) {
			throw new PeRuntimeException(e);
		}
	}

	/**
	 * 一口清分退货清分
	 */
	private void returnClearing_BITE(Batchconfirmsubcleartrans confirm,Date checkDate) {
		// 获取清算机构内部账户，商户开户行，商户发展行账户
		Deptacctinfo deptAcct = getDeptAcctInfo(confirm.getMerdevdeptnbr(),confirm.getTransmode());
		// 获取原支付子订单
		List<Onlinesubtranshist> originalsubhist = OnlinesubtranshistExtendDAO.getOnlineSubTransHist(confirm);
		Onlinesubtranshist origsubtrans = originalsubhist.get(0);
		// 商户账务信息查询
		Meracctinfo merAcct = null;
		try {
			merAcct = MeracctinfoDAO.selectByPrimaryKey(confirm.getMernbr());
			if (merAcct == null) {
				throw new PeException("一口清分退货清分未配置商户账户信息【"+confirm.getMernbr()+"】");
			}
		} catch (Exception e) {
			throw new PeRuntimeException(e);
		}
		// 退还商户手续费计算
		BigDecimal feeAmt = withdrawFeeClearing_BITE(confirm, origsubtrans, deptAcct, merAcct,checkDate);
		// 商户退货台账汇总
		withdrawSettlement_BITE(confirm, deptAcct, merAcct, feeAmt,checkDate);
		// 如果订单状态没有被设置为已结算,则置为清分成功
		if (merAcct.getSettleacctnbr().equals(confirm.getPayeracctnbr())) {
			confirm.setProcstep(ProcStep.SETTLED);
		} else {
			confirm.setProcstep(ProcStep.CLEARED);
		}
		try {
			// 更新确认表
			BatchconfirmsubcleartransDAO.updateByPrimaryKeySelective(confirm);
		} catch (SQLException e) {
			throw new PeRuntimeException(e);
		}
		// 更新子历史交易明细表的处理阶段
		Onlinesubtranshist record = new Onlinesubtranshist();
		record.setTransseqnbr(confirm.getTransseqnbr());
		record.setSubtransseqnbr(confirm.getSubtransseqnbr());
		//记录手续费 modified by wangtao 20161214
		record.setFeeamt(feeAmt);
		record.setProcstep(confirm.getProcstep());
		try {
			OnlinesubtranshistDAO.updateByPrimaryKeySelective(record);
		} catch (SQLException e) {
			throw new PeRuntimeException(e);
		}
	}

	/**
	 * 递进清分支付清分
	 */
	private void payClearing_STEP(Batchconfirmsubcleartrans cfm,Date checkDate) {
		// 获取清算机构账户信息
		Deptacctinfo acct = getDeptAcctInfo(cfm.getMerdevdeptnbr(),cfm.getTransmode());
		// 二级商户账户信息查询
		Meracctinfo acctinfo = null;
		try {
			acctinfo = MeracctinfoDAO.selectByPrimaryKey(cfm.getMernbr());
			if(acctinfo == null){
				throw new PeException("递进清分支付清分未配置商户账户信息【"+cfm.getMernbr()+"】");
			}
		} catch (Exception e) {
			throw new PeRuntimeException(e);
		}
		//一级商户账户信息查询
		Meracctinfo superacctinfo = null;
		try {
			superacctinfo = MeracctinfoDAO.selectByPrimaryKey(acctinfo.getSupermernbr());
			if(superacctinfo == null){
				throw new PeException("递进清分支付清分未配置商户账户信息【"+acctinfo.getSupermernbr()+"】");
			}
		} catch (Exception e) {
			throw new PeRuntimeException(e);
		}
		// 收取商户手续费
		BigDecimal feeAmount = payFeeClearing_BITE(cfm, acct, acctinfo,checkDate);
		// 商户支付台账汇总
		paySettlement_STEP(cfm,acct,feeAmount, acctinfo,superacctinfo,checkDate);
		// 如果订单状态没有被设置为已结算,则置为清分成功
		if(acctinfo.getSettperiod().equals(SettPeriod.REALTIME)){ 
			cfm.setProcstep(ProcStep.SETTLED);
		} else {
			cfm.setProcstep(ProcStep.CLEARED);
		}
		
		try {
			BatchconfirmsubcleartransDAO.updateByPrimaryKeySelective(cfm);
		} catch (SQLException e) {
			throw new PeRuntimeException(e);
		}
		// 更新子历史交易明细表的处理阶段
		Onlinesubtranshist record = new Onlinesubtranshist();
		record.setTransseqnbr(cfm.getTransseqnbr());
		record.setSubtransseqnbr(cfm.getSubtransseqnbr());
		record.setProcstep(cfm.getProcstep());
		try {
			OnlinesubtranshistDAO.updateByPrimaryKeySelective(record);
		} catch (SQLException e) {
			throw new PeRuntimeException(e);
		}
	}
	
	/**
	 * 退还商户手续费计算
	 */
	private BigDecimal withdrawFeeClearing_BITE(Batchconfirmsubcleartrans confirm, Onlinesubtranshist origsubtrans,
			Deptacctinfo acct, Meracctinfo acctinfo,Date checkDate) {
		BigDecimal feeAmount = null;
		BigDecimal transamt = null;
		String feeNbr = null;
		if (confirm.getFeenbr() == null) {
			feeNbr = acctinfo.getFeenbr();
		} else {
			feeNbr = confirm.getFeenbr();
		}
		// 如果商户为退货不退手续费或者商户为定额、包月、分段的都不退手续费
		if (FeeReturnFlag.N.equals(acctinfo.getFeereturnflag()) || FeeMode.MON.equals(acctinfo.getFeemode())
				|| FeeMode.SEC.equals(acctinfo.getFeemode())) {
			feeAmount = BigDecimal.ZERO;
		} else if (FeeMode.FIX.equals(acctinfo.getFeemode())) {
			transamt = confirm.getTransamt();
			//modified by wangtao 20161208 定额 部分退款不退手续费
			if(!isAllWithdraw(origsubtrans,confirm)){
				feeAmount = BigDecimal.ZERO;
				log.debug("定额部分退款手续费为零,subtransseqnbr=["+confirm.getSubtransseqnbr()+"]");
			}else{
				feeAmount = this.getFeeAmount(feeNbr, transamt); 	
				}			
			
		} else {
			/*
			 * 退还时的手续费计算 用剩余手续费减去应该保留的手续费
			 */
			// 未退货金额
			BigDecimal reservedAmt = origsubtrans.getUnrefundamt();
			// 被退货时候还剩余的手续费金额
			transamt = reservedAmt.add(confirm.getTransamt());
			BigDecimal reservedFeeAmt1 = this.getFeeAmount(feeNbr, transamt);
			// //本次退货后需要保留的手续费金额
			BigDecimal reservedFeeAmt = this.getFeeAmount(feeNbr, reservedAmt); 
			if (reservedFeeAmt.compareTo(reservedFeeAmt1) > 0) {
				reservedFeeAmt = reservedFeeAmt1;
			}

			// //本次退货需要返还的手续费金额
			feeAmount = reservedFeeAmt1.subtract(reservedFeeAmt);
		}

		// 如果手续费金额大于0，往商户台账表中插一条退手续费的记录
		if (feeAmount.compareTo(BigDecimal.ZERO) > 0) {
			Batchmertrans transData = new Batchmertrans();
			transData.setCleartransnbr(IDGenerateFactory.generateSeqId());
			transData.setSubtransseqnbr(confirm.getSubtransseqnbr());
			transData.setTransseqnbr(confirm.getTransseqnbr());
			transData.setMerseqnbr(confirm.getMerseqnbr());
			transData.setCleardate(confirm.getCleardate());
			transData.setSettledate(DateUtil.getSettlementDate(acctinfo.getSettperiod(), checkDate));
			transData.setTransdate(confirm.getTransdate());
			transData.setTranstime(confirm.getTranstime());
			transData.setSettlestatus(SettleStatus.UNSETTLE);
			transData.setMernbr(confirm.getMernbr());
			transData.setCustcifnbr(confirm.getCustcifnbr());

			// 付款方账户信息
			transData.setPayeracctdeptnbr(acct.getDeptnbr()); // 修改为商户发展行机构
			//修改退款手续费付款方账户为行社手续费支出户 modefied by wangtao 2017-01-22
			transData.setPayeracctnbr(acct.getFeespendacctnbr());
			transData.setPayeracctname(acct.getFeespendacctname());
			transData.setPayeracctkind(acct.getFeespendacctkind());
			transData.setPayeraccttypcd(acct.getFeespendaccttpycd());

			// 收款方账户信息
			if (acctinfo.getSettleacctnbr().equals(confirm.getPayeracctnbr())) {
				transData.setStandbooktypcd(StandBookTypCD.MERCHANT_TRANS_FEE_RETURN);
				transData.setPayeeacctdeptnbr(acctinfo.getMeropendeptnbr());
				transData.setPayeeacctnbr(acctinfo.getFeeacctnbr());
				transData.setPayeeacctname(acctinfo.getFeeacctname());
				transData.setPayeeacctkind(acctinfo.getFeeacctkind());
				transData.setPayeeaccttypcd(acctinfo.getFeeaccttyp());
			} else {
				transData.setStandbooktypcd(StandBookTypCD.MERCHANT_TRANS_BALANCE_FEE_RETURN);
				transData.setPayeeacctdeptnbr(acct.getDeptnbr());
				transData.setPayeeacctnbr(acct.getAcctnbr());
				transData.setPayeeacctname(acct.getAcctname());
				transData.setPayeeacctkind(acct.getAcctkindcd());
				transData.setPayeeaccttypcd(acct.getAccttypcd());
			}
			transData.setTransamt(feeAmount);
			transData.setDepartmentnbr(acct.getDeptnbr());
			try {
				BatchmertransDAO.insertSelective(transData);
			} catch (SQLException e) {
				throw new PeRuntimeException(e);
			}
		}
		return feeAmount;
	}

	/**
	 * 获取清算机构账户信息
	 */
	private Deptacctinfo getDeptAcctInfo(String merDevDeptNbr,String innerAcctCfmMode) {
		Deptacctinfo acctInfo = null;
		try {
			acctInfo = DeptacctinfoDAO.selectByPrimaryKey(merDevDeptNbr.substring(0, 3)+"000", innerAcctCfmMode);
		} catch (SQLException e) {
			throw new PeRuntimeException(e);
		}
		if(acctInfo == null){
			throw new PeRuntimeException("未配置机构账户信息");
		}	
		return acctInfo;
	}

	/**
	 * 手续费计算
	 * 
	 * @return
	 */
	private BigDecimal getFeeAmount(String feeNbr, BigDecimal transamt) {
		BigDecimal feeAmount = null;
		// 通过费率号获取费率参数
		FeeparamExample example = new FeeparamExample();
		example.createCriteria().andFeenbrEqualTo(feeNbr);
		example.setOrderByClause("SEQNBR");
		List<Feeparam> feeparams = null;
		try {
			feeparams = FeeparamDAO.selectByExample(example);
		} catch (SQLException e) {
			throw new PeRuntimeException(e);
		}
		// 通过费率参数获取手续费金额
		// 收取商户手续费
		FeeService feecal = (FeeService) this.getRouterService("FEE".toLowerCase());
		try {
			feeAmount = feecal.calculateFee(feeparams, transamt, null).get(Dict.FEE_AMT);
		} catch (Exception e) {
			throw new PeRuntimeException(e);
		}
		return feeAmount;
	}

	/**
	 * 一口清分支付交易手续费计算
	 * @return 
	 */
	private BigDecimal payFeeClearing_BITE(Batchconfirmsubcleartrans confirm, Deptacctinfo acct, Meracctinfo acctinfo,Date checkDate) {
		// 如果日间计算过手续，则取计算过的，否则日终计算
		BigDecimal feeAmount = new BigDecimal(0);
		String feeNbr = null;
		if (confirm.getFeenbr() == null) {
			feeNbr = acctinfo.getFeenbr();  
		} else {
			feeNbr = confirm.getFeenbr();
		}
		if (feeNbr != null && (confirm.getFeeamt() == null || confirm.getFeeamt().compareTo(new BigDecimal(0)) == 0)) {
			feeAmount = getFeeAmount(feeNbr, confirm.getTransamt());
		} else {
			feeAmount = confirm.getFeeamt();
		}

		// 包月商户手续费不收取
		if (FeeMode.MON.equals(acctinfo.getFeemode())) {
			feeAmount = BigDecimal.ZERO;
		} else if (SettMode.BITE.equals(acctinfo.getSettmode())) {
			// 差额结算商户需要判断交易金额是否小于手续费
			if (confirm.getTransamt().compareTo(feeAmount) < 0) {
				// 差额结算如果交易金额小于手续费金额，则全部收取
				feeAmount = confirm.getTransamt();
			}
		}
		confirm.setFeeamt(feeAmount);
		// 手续费大于0就记录收取商户手续费的台账
		if (feeAmount.compareTo(BigDecimal.ZERO) > 0) {
			Batchmertrans transData = new Batchmertrans();
			transData.setCleartransnbr(IDGenerateFactory.generateSeqId());
			transData.setSubtransseqnbr(confirm.getSubtransseqnbr());
			transData.setTransseqnbr(confirm.getTransseqnbr());
			transData.setMerseqnbr(confirm.getMerseqnbr());
			transData.setCleardate(confirm.getCleardate());
			transData.setSettledate(DateUtil.getSettlementDate(acctinfo.getFeesettperiod(), checkDate));
			transData.setTransdate(confirm.getTransdate());
			transData.setTranstime(confirm.getTranstime());
			if (SettPeriod.REALTIME.equals(acctinfo.getSettperiod())) {
				transData.setSettlestatus(SettleStatus.SETTLED);
			}else {
				transData.setSettlestatus(SettleStatus.UNSETTLE);
			}
			transData.setMernbr(confirm.getMernbr());
			transData.setCustcifnbr(confirm.getCustcifnbr());
			if(SettMode.BITE.equals(acctinfo.getSettmode())){//差额付款方信息设置 
				transData.setStandbooktypcd(StandBookTypCD.MERCHANT_TRANS_BALANCE_FEE_RCV);
				transData.setPayeracctnbr(acct.getAcctnbr());
				transData.setPayeraccttypcd(acct.getAccttypcd());
				transData.setPayeracctdeptnbr(acct.getDeptnbr());
				transData.setPayeracctname(acct.getAcctname());
				transData.setPayeracctkind(acct.getAcctkindcd());
				
			}else if(SettMode.DIRT.equals(acctinfo.getSettmode())){//全额付款方信息设置 
				transData.setStandbooktypcd(StandBookTypCD.MERCHANT_TRANS_FEE_DIRT_RCV);
				transData.setPayeracctnbr(acctinfo.getFeeacctnbr());
				transData.setPayeraccttypcd(acctinfo.getFeeaccttyp());
				transData.setPayeracctdeptnbr(acctinfo.getMeropendeptnbr());
				transData.setPayeracctname(acctinfo.getFeeacctname());
				transData.setPayeracctkind(acctinfo.getFeeacctkind());
			}
			// 收款方信息设置
			transData.setPayeeacctnbr(acct.getFeeacctnbr());
			transData.setPayeeaccttypcd(acct.getFeeaccttpycd());
			transData.setPayeeacctdeptnbr(acct.getDeptnbr());
			transData.setPayeeacctname(acct.getFeeacctname());
			transData.setPayeeacctkind(acct.getFeeacctkind());
			transData.setTransamt(feeAmount);
			transData.setDepartmentnbr(acct.getDeptnbr());
			try {
				BatchmertransDAO.insertSelective(transData);
			} catch (SQLException e) {
				throw new PeRuntimeException(e);
			}
		}
		return feeAmount;
	}

	/**
	 *一口清分商户支付台账汇总
	 */
	private void paySettlement_BITE(Batchconfirmsubcleartrans confirm,Deptacctinfo acct,BigDecimal feeAmount, Meracctinfo acctinfo,Date checkDate) {
		try {
			Batchmertrans transData = new Batchmertrans();
			transData.setCleartransnbr(IDGenerateFactory.generateSeqId());
			transData.setSubtransseqnbr(confirm.getSubtransseqnbr());
			transData.setTransseqnbr(confirm.getTransseqnbr());
			transData.setMerseqnbr(confirm.getMerseqnbr());
			transData.setCleardate(confirm.getCleardate());
			transData.setSettledate(DateUtil.getSettlementDate(acctinfo.getSettperiod(), checkDate));
			transData.setTransdate(confirm.getTransdate());
			transData.setTranstime(confirm.getTranstime());
			transData.setSettlestatus(SettleStatus.UNSETTLE);
			transData.setMernbr(confirm.getMernbr());
			transData.setStandbooktypcd(StandBookTypCD.MERCHANT_TRANS_PAY);
			transData.setCustcifnbr(confirm.getCustcifnbr());
			transData.setDepartmentnbr(acct.getDeptnbr());

			// 付款方信息设置
			//如果是实时结算的商户将结算标志设置为已结算
			if(acctinfo.getSettperiod().equals(SettPeriod.REALTIME)){
				transData.setPayeracctnbr(confirm.getPayeracctnbr());//付款账户为客户账
				transData.setPayeraccttypcd(confirm.getPayeraccttypcd());
				transData.setPayeracctdeptnbr(confirm.getPayeracctdeptnbr());
				transData.setPayeracctname(confirm.getPayeracctname());
				transData.setPayeracctkind(confirm.getPayeracctkind());
				transData.setPayercardtypcd(confirm.getPayercardtypcd());
				transData.setSettlestatus(SettleStatus.SETTLED);
				
			}else{
			    transData.setPayeracctnbr(acct.getAcctnbr());//付款账户为内部账
			    transData.setPayeraccttypcd(acct.getAccttypcd());
			    transData.setPayeracctdeptnbr(acct.getDeptnbr());
			    transData.setPayeracctname(acct.getAcctname());
			    transData.setPayeracctkind(acct.getAcctkindcd());
			    transData.setSettlestatus(SettleStatus.UNSETTLE);
			    
			}
			// 收款方信息设置
			transData.setPayeeacctnbr(acctinfo.getSettleacctnbr());
			transData.setPayeeaccttypcd(acctinfo.getSettleaccttyp());
			transData.setPayeeacctdeptnbr(acctinfo.getMeropendeptnbr());
			transData.setPayeeacctname(acctinfo.getSettleacctname());
			transData.setPayeeacctkind(acctinfo.getSettleacctkind());
			//差额结算为交易金额减去手续费金额，全额结算为交易金额
			if(SettMode.BITE.equals(acctinfo.getSettmode())){
				transData.setTransamt(confirm.getTransamt().subtract(feeAmount));
			}else{
				transData.setTransamt(confirm.getTransamt());
			}
			BatchmertransDAO.insertSelective(transData);
		} catch (Exception e) {
			throw new PeRuntimeException(e);
		}
	}
	
	/**
	 *递进清分商户支付台账汇总
	 */
	private void paySettlement_STEP(Batchconfirmsubcleartrans confirm,Deptacctinfo acct,BigDecimal feeAmount, Meracctinfo acctinfo,Meracctinfo superacctinfo,Date checkDate) {
		try {
			Batchmertrans transData = new Batchmertrans();
			transData.setCleartransnbr(IDGenerateFactory.generateSeqId());
			transData.setSubtransseqnbr(confirm.getSubtransseqnbr());
			transData.setTransseqnbr(confirm.getTransseqnbr());
			transData.setMerseqnbr(confirm.getMerseqnbr());
			transData.setCleardate(confirm.getCleardate());
			transData.setSettledate(DateUtil.getSettlementDate(acctinfo.getSettperiod(), checkDate));
			transData.setTransdate(confirm.getTransdate());
			transData.setTranstime(confirm.getTranstime());
			transData.setSettlestatus(SettleStatus.UNSETTLE);
			transData.setMernbr(confirm.getMernbr());
			transData.setStandbooktypcd(StandBookTypCD.MERCHANT_TRANS_PAY);
			transData.setCustcifnbr(confirm.getCustcifnbr());
			transData.setDepartmentnbr(acct.getDeptnbr());

			// 付款方信息设置
			//如果是实时结算的商户将结算标志设置为已结算
			if(acctinfo.getSettperiod().equals(SettPeriod.REALTIME)){
				transData.setPayeracctnbr(confirm.getPayeracctnbr());//付款账户为客户账
				transData.setPayeraccttypcd(confirm.getPayeraccttypcd());
				transData.setPayeracctdeptnbr(confirm.getPayeracctdeptnbr());
				transData.setPayeracctname(confirm.getPayeracctname());
				transData.setPayeracctkind(confirm.getPayeracctkind());
				transData.setPayercardtypcd(confirm.getPayercardtypcd());
				transData.setSettlestatus(SettleStatus.SETTLED);
				
			}else{
				//付款账户为一级商户结算账户信息
				transData.setPayeeacctnbr(superacctinfo.getSettleacctnbr());
				transData.setPayeeaccttypcd(superacctinfo.getSettleaccttyp());
				transData.setPayeeacctdeptnbr(superacctinfo.getMeropendeptnbr());
				transData.setPayeeacctname(superacctinfo.getSettleacctname());
				transData.setPayeeacctkind(superacctinfo.getSettleacctkind());
			    transData.setSettlestatus(SettleStatus.UNSETTLE);		    
			}
			// 收款方信息设置
			transData.setPayeeacctnbr(acctinfo.getSettleacctnbr());
			transData.setPayeeaccttypcd(acctinfo.getSettleaccttyp());
			transData.setPayeeacctdeptnbr(acctinfo.getMeropendeptnbr());
			transData.setPayeeacctname(acctinfo.getSettleacctname());
			transData.setPayeeacctkind(acctinfo.getSettleacctkind());
			//差额结算为交易金额减去手续费金额，全额结算为交易金额
			if(SettMode.BITE.equals(acctinfo.getSettmode())){
				transData.setTransamt(confirm.getTransamt().subtract(feeAmount));
			}else{
				transData.setTransamt(confirm.getTransamt());
			}
			BatchmertransDAO.insertSelective(transData);
		} catch (Exception e) {
			throw new PeRuntimeException(e);
		}
	}

	/**
	 * 包月商户清分
	 * 
	 * @param merAcct
	 *            商户账户
	 * @author 姜星
	 */
	private void clearMonthMerchant(Meracctinfo merAcct,Date checkDate) {
		BigDecimal feeAmount = new BigDecimal(0);
		List<Merfeerel> list = new ArrayList<Merfeerel>();
		Deptacctinfo acct = null;
		try {
			acct =this.getDeptAcctInfo(merAcct.getMerdevelopdeptnbr(), merAcct.getInneracctcfmmode());
			MerfeerelExample example = new MerfeerelExample();
			example.createCriteria().andMernbrEqualTo(merAcct.getMernbr());
			list = MerfeerelDAO.selectByExample(example);
		} catch (SQLException e1) {
			throw new PeRuntimeException(e1);
		}
		if (list.size() > 0) {
			feeAmount = getFeeAmount(list.get(0).getFeenbr(), null);
		} else {
			feeAmount = getFeeAmount(merAcct.getFeenbr(), null);
		}

		if (feeAmount.compareTo(BigDecimal.ZERO) > 0 && acct != null) {
			Batchmertrans transData = new Batchmertrans();
			transData.setCleartransnbr(IDGenerateFactory.generateSeqId());
			transData.setTransseqnbr("ZZZZZZZZZZZZ");
			transData.setMerseqnbr("ZZZZZZZZZZZZ");
			transData.setCleardate(checkDate);
			transData.setSettledate(DateUtil.getSettlementDate(merAcct.getFeesettperiod(), checkDate));
			transData.setTransdate(checkDate);
			transData.setSettlestatus(SettleStatus.UNSETTLE);
			transData.setStandbooktypcd(StandBookTypCD.MERCHANT_TRANS_FEE_RCV); // 台账类型
			transData.setDepartmentnbr(acct.getDeptnbr());
			transData.setMernbr(merAcct.getMernbr());
			// 付款方信息设置
			transData.setPayeracctnbr(merAcct.getFeeacctnbr());
			transData.setPayeraccttypcd(merAcct.getFeeaccttyp());
			transData.setPayeracctdeptnbr(merAcct.getMeropendeptnbr());
			transData.setPayeracctname(merAcct.getFeeacctname());
			transData.setPayeracctkind(merAcct.getFeeacctkind());
			// 收款方信息设置
			transData.setPayeeacctnbr(acct.getFeeacctnbr());
			transData.setPayeeaccttypcd(acct.getFeeaccttpycd());
			transData.setPayeeacctdeptnbr(acct.getDeptnbr());
			transData.setPayeeacctname(acct.getFeeacctname());
			transData.setPayeeacctkind(acct.getAcctkindcd());
			transData.setTransamt(feeAmount);
			
			try {
				// 商户台帐表
				BatchmertransDAO.insertSelective(transData);
			} catch (SQLException e) {
				throw new PeRuntimeException(e);
			}
		}

		// 交易手续费的分润（暂不分润）
	}

	/**
	 * 支付交易直接清分
	 * 
	 * @author 姜星
	 * @throws PeException 
	 */
	private void payClearing_DIRT(Onlinetranshist dirt,Date checkDate) {
		// 获取清算机构账户信息
		Deptacctinfo deptAcct = null;
		// 根据一级商户结算账户信息
		Meracctinfo merAcct = null;
		try {
			merAcct = MeracctinfoDAO.selectByPrimaryKey(dirt.getMernbr());
			if (merAcct == null) {
				throw new PeException("直接清分支付未配置商户账户信息【"+dirt.getMernbr()+"】");
			}
			deptAcct = this.getDeptAcctInfo(merAcct.getMerdevelopdeptnbr(), merAcct.getInneracctcfmmode());		
		} catch (Exception e1) {
			throw new PeRuntimeException(e1);
		}
		Map map = BatchConfirmSubClearTransExtendDAO.getBatchconfirmsubcleartrans(dirt.getTransseqnbr());
		BigDecimal transAmt = StringUtil.parseBigDecimal(map.get("TRANSAMT"));
		String supfeenbr = StringUtil.parseObjectToString(map.get("SUPFEENBR"));
		// 收取商户手续费
		BigDecimal feeAmount = payFeeClearing_DIRT(dirt, deptAcct, merAcct, transAmt,supfeenbr,checkDate);
		// 商户支付台账汇总
		paySettlement_DIRT(dirt,deptAcct,feeAmount, merAcct, transAmt,checkDate);
		// 如果订单状态没有被设置为已结算,则置为清分成功
		if (SettPeriod.REALTIME.equals(merAcct.getSettperiod())) {
			dirt.setProcstep(ProcStep.SETTLED);
		} else {
			dirt.setProcstep(ProcStep.CLEARED);
		}
		// 用总流水更新Batchconfirmsubcleartrans表
		BatchconfirmsubcleartransExample example = new BatchconfirmsubcleartransExample();
		example.createCriteria().andTransseqnbrEqualTo(dirt.getTransseqnbr());
		Batchconfirmsubcleartrans record = new Batchconfirmsubcleartrans();
		record.setProcstep(dirt.getProcstep());
		try {
			//递进清分此处不更新
			if (!ClearClassModeCd.STEP.equals(merAcct.getClearclassmodecd())) {
				BatchconfirmsubcleartransDAO.updateByExampleSelective(record, example);
				// 更新子历史交易明细表的处理阶段 
				OnlinesubtranshistExample subTransHistExample=new OnlinesubtranshistExample();
				subTransHistExample.createCriteria().andTransseqnbrEqualTo(dirt.getTransseqnbr());
				Onlinesubtranshist subTransHistRecord=new Onlinesubtranshist();
				subTransHistRecord.setProcstep(dirt.getProcstep());
		        OnlinesubtranshistDAO.updateByExampleSelective(subTransHistRecord, subTransHistExample);
			}
		} catch (SQLException e) {
			throw new PeRuntimeException(e);
		}
	}

	/**
	 * 退货交易直接清分
	 * 
	 * @author 姜星
	 * 
	 */
	private void returnClearing_DIRT(Onlinetranshist dirt,Date checkDate) {
		// 获取清算机构账户信息
		Deptacctinfo deptAcct = null;
		// 根据一级商户结算账户信息
		Meracctinfo merAcct = null;
		try {
			merAcct = MeracctinfoDAO.selectByPrimaryKey(dirt.getMernbr());
			if (merAcct == null) {
				throw new PeException("直接清分未配置商户账户信息【"+dirt.getMernbr()+"】");
			}
			deptAcct = this.getDeptAcctInfo(merAcct.getMerdevelopdeptnbr(), merAcct.getInneracctcfmmode());
		} catch (Exception e1) {
			throw new PeRuntimeException(e1);
		}
		Map map = BatchConfirmSubClearTransExtendDAO.getOrigOnlinesubtrans(dirt.getTransseqnbr());
		BigDecimal transAmt = StringUtil.parseBigDecimal(map.get("TRANSAMT"));
		BigDecimal unrefundamt = StringUtil.parseBigDecimal(map.get("UNREFUNDAMT"));
		String supfeenbr = StringUtil.parseObjectToString(map.get("SUPFEENBR"));
		// 汇总手续费处理：汇总手续费计算，入台账表，账户信息为借内部手续费账号贷商户手续费结算账号
		BigDecimal feeAmt = this.withdrawFeeClearing_DIRT(dirt, deptAcct, merAcct,transAmt,unrefundamt,supfeenbr,checkDate);
		// 汇总流水数据处理：汇总数据入台账表，账户信息为借商户结算账号（已结算）/内部账（未结算）贷内部账号
		withdrawSettlement_DIRT(dirt, deptAcct, merAcct, feeAmt,checkDate);
		// 如果订单状态没有被设置为已结算,则置为清分成功
        if (merAcct.getSettleacctnbr().equals(dirt.getPayeracctnbr())) {
        	dirt.setProcstep(ProcStep.SETTLED);
		} else {
			dirt.setProcstep(ProcStep.CLEARED);
		}
		// 更新交易状态
		BatchconfirmsubcleartransExample example = new BatchconfirmsubcleartransExample();
		example.createCriteria().andTransseqnbrEqualTo(dirt.getTransseqnbr());
		Batchconfirmsubcleartrans record = new Batchconfirmsubcleartrans();
		record.setProcstep(dirt.getProcstep());
		try {
			BatchconfirmsubcleartransDAO.updateByExampleSelective(record, example);
		} catch (SQLException e) {
			throw new PeRuntimeException(e);
		}
		// 更新子历史交易明细表的处理阶段 
		Onlinesubtranshist record2 = new Onlinesubtranshist();
		record2.setProcstep(dirt.getProcstep());
		OnlinesubtranshistExample histExample = new OnlinesubtranshistExample();
		histExample.createCriteria().andTransseqnbrEqualTo(dirt.getTransseqnbr());
		try {
			OnlinesubtranshistDAO.updateByExampleSelective(record2, histExample);
		} catch (SQLException e) {
			throw new PeRuntimeException(e);
		}
	}
	
	/**
	 * 直接清分支付清分
	 */
	private void paySettlement_DIRT(Onlinetranshist trans, Deptacctinfo acct, BigDecimal feeAmount,
			Meracctinfo acctinfo, BigDecimal transAmt,Date checkDate) {
		try {
			Batchmertrans transData = new Batchmertrans();
			transData.setCleartransnbr(IDGenerateFactory.generateSeqId());
			transData.setTransseqnbr(trans.getTransseqnbr());
			transData.setMerseqnbr(trans.getMerseqnbr());
			transData.setCleardate(trans.getCleardate());
			transData.setSettledate(DateUtil.getSettlementDate(acctinfo.getSettperiod(), checkDate));
			transData.setTransdate(trans.getTransdate());
			transData.setTranstime(trans.getTranstime());
			transData.setMernbr(trans.getMernbr());
			transData.setStandbooktypcd(StandBookTypCD.MERCHANT_TRANS_PAY);
			transData.setCustcifnbr(trans.getCustcifnbr());
			transData.setDepartmentnbr(acct.getDeptnbr());

			// 付款方信息设置
			// 如果是实时结算的商户将结算标志设置为已结算
			if (SettPeriod.REALTIME.equals(acctinfo.getSettperiod())) {
				transData.setPayeracctnbr(trans.getPayeracctnbr());// 付款账户为客户账
				transData.setPayeraccttypcd(trans.getPayeraccttypcd());
				transData.setPayeracctdeptnbr(trans.getPayeracctdeptnbr());
				transData.setPayeracctname(trans.getPayeracctname());
				transData.setPayeracctkind(trans.getPayeracctkind());
				transData.setPayercardtypcd(trans.getPayercardtypcd());
				transData.setSettlestatus(SettleStatus.SETTLED);
			} else {
				transData.setPayeracctnbr(acct.getAcctnbr());// 付款账户为内部账
				transData.setPayeraccttypcd(acct.getAccttypcd());
				transData.setPayeracctdeptnbr(acct.getDeptnbr());
				transData.setPayeracctname(acct.getAcctname());
				transData.setPayeracctkind(acct.getAcctkindcd());
				transData.setSettlestatus(SettleStatus.UNSETTLE);
			}
			// 收款方信息设置
			transData.setPayeeacctnbr(acctinfo.getSettleacctnbr());
			transData.setPayeeaccttypcd(acctinfo.getSettleaccttyp());
			transData.setPayeeacctdeptnbr(acctinfo.getMeropendeptnbr());
			transData.setPayeeacctname(acctinfo.getSettleacctname());
			transData.setPayeeacctkind(acctinfo.getSettleacctkind());
			// 差额结算为交易金额减去手续费金额，全额结算为交易金额
			if (SettMode.BITE.equals(acctinfo.getSettmode())) {
				transData.setTransamt(transAmt.subtract(feeAmount));
			} else {
				transData.setTransamt(transAmt);
			}
			BatchmertransDAO.insertSelective(transData);
		} catch (Exception e) {
			throw new PeRuntimeException(e);
		}
	}
	
	/**
	 * 直接清分支付交易手续费计算 
	 * @param trans 总交易流水历史
	 * @param acct 机构账户信息
	 * @return 手续费金额
	 */
	private BigDecimal payFeeClearing_DIRT(Onlinetranshist trans, Deptacctinfo acct, Meracctinfo acctinfo, BigDecimal transAmt,String supfeenbr,Date checkDate) {
		// 如果日间计算过手续，则取计算过的，否则日终计算
		BigDecimal feeAmount = BigDecimal.ZERO;		
		String feeNbr = null;
		if (supfeenbr == null) {
			feeNbr = acctinfo.getFeenbr();  
		} else {
			feeNbr = supfeenbr;
		}
		if (trans.getFeeamt() == null || trans.getFeeamt().compareTo(new BigDecimal(0)) == 0) {
			   feeAmount = getFeeAmount(feeNbr, transAmt);
		   } else {
			   feeAmount = trans.getFeeamt();
		   }
		
		// 包月商户手续费不收取
		if (FeeMode.MON.equals(acctinfo.getFeemode())) {
			feeAmount = BigDecimal.ZERO;
		} else if (SettMode.BITE.equals(acctinfo.getSettmode())) {
			// 差额结算商户需要判断交易金额是否小于手续费
			if (trans.getTransamt().compareTo(feeAmount) < 0) {
				// 差额结算如果交易金额小于手续费金额，则全部收取
				feeAmount = trans.getTransamt();
			}
		}
		trans.setFeeamt(feeAmount);
		// 手续费大于0就记录收取商户手续费的台账
		if (feeAmount.compareTo(BigDecimal.ZERO) > 0) {
			Batchmertrans transData = new Batchmertrans();
			transData.setCleartransnbr(IDGenerateFactory.generateSeqId());
			transData.setTransseqnbr(trans.getTransseqnbr());
			transData.setMerseqnbr(trans.getMerseqnbr());
			transData.setCleardate(trans.getCleardate());
			transData.setSettledate(DateUtil.getSettlementDate(acctinfo.getFeesettperiod(), checkDate)); 
			transData.setTransdate(trans.getTransdate());
			transData.setTranstime(trans.getTranstime());
			if (SettPeriod.REALTIME.equals(acctinfo.getSettperiod())) {
				transData.setSettlestatus(SettleStatus.SETTLED);
			}else {
				transData.setSettlestatus(SettleStatus.UNSETTLE);
			}
			transData.setMernbr(trans.getMernbr());
			transData.setCustcifnbr(trans.getCustcifnbr());
			
			if(SettMode.BITE.equals(acctinfo.getSettmode())){//差额付款方信息设置 
				transData.setStandbooktypcd(StandBookTypCD.MERCHANT_TRANS_BALANCE_FEE_RCV);
				transData.setPayeracctnbr(acct.getAcctnbr());
				transData.setPayeraccttypcd(acct.getAccttypcd());
				transData.setPayeracctdeptnbr(acct.getDeptnbr());
				transData.setPayeracctname(acct.getAcctname());
				transData.setPayeracctkind(acct.getAcctkindcd());
				
			}else if(SettMode.DIRT.equals(acctinfo.getSettmode())){//全额付款方信息设置 
				transData.setStandbooktypcd(StandBookTypCD.MERCHANT_TRANS_FEE_DIRT_RCV);
				transData.setPayeracctnbr(acctinfo.getFeeacctnbr());
				transData.setPayeraccttypcd(acctinfo.getFeeaccttyp());
				transData.setPayeracctdeptnbr(acctinfo.getMeropendeptnbr());
				transData.setPayeracctname(acctinfo.getFeeacctname());
				transData.setPayeracctkind(acctinfo.getFeeacctkind());
			}
			
			// 收款方信息设置
			transData.setPayeeacctnbr(acct.getFeeacctnbr());
			transData.setPayeeaccttypcd(acct.getFeeaccttpycd());
			transData.setPayeeacctdeptnbr(acct.getDeptnbr());
			transData.setPayeeacctname(acct.getFeeacctname());
			transData.setPayeeacctkind(acct.getFeeacctkind());
			transData.setTransamt(feeAmount);
			transData.setDepartmentnbr(acct.getDeptnbr());
			try {
				BatchmertransDAO.insertSelective(transData);
			} catch (SQLException e) {
				throw new PeRuntimeException(e);
			}
		}
		return feeAmount;
	}
	
	/**
	 * 一口清分退货本金计算
	 * 
	 * @author 姜星
	 */
	private void withdrawSettlement_BITE(Batchconfirmsubcleartrans confirm, Deptacctinfo acct, Meracctinfo acctinfo,
			BigDecimal feeAmt,Date checkDate) {
		Batchmertrans transData = new Batchmertrans();
		transData.setCleartransnbr(IDGenerateFactory.generateSeqId());
		transData.setSubtransseqnbr(confirm.getSubtransseqnbr());
		transData.setTransseqnbr(confirm.getTransseqnbr());
		transData.setMerseqnbr(confirm.getMerseqnbr());
		transData.setCleardate(confirm.getCleardate());
		transData.setSettledate(DateUtil.getSettlementDate(acctinfo.getSettperiod(), checkDate));
		transData.setTransdate(confirm.getTransdate()); 
		transData.setTranstime(confirm.getTranstime());
		transData.setSettlestatus(SettleStatus.UNSETTLE); 
		transData.setMernbr(confirm.getMernbr());
		transData.setCustcifnbr(confirm.getCustcifnbr());
		transData.setStandbooktypcd(StandBookTypCD.MERCHANT_TRANS_WITHDRAW);
		// 付款方账户信息
		transData.setPayeracctdeptnbr(acctinfo.getMeropendeptnbr());
		transData.setPayeracctnbr(acctinfo.getSettleacctnbr()); // 商户结算账号
		transData.setPayeracctname(acctinfo.getSettleacctname());
		transData.setPayeracctkind(acctinfo.getSettleacctkind());
		transData.setPayeraccttypcd(acctinfo.getSettleaccttyp());
		// 收款方账户信息
		if (acctinfo.getSettleacctnbr().equals(confirm.getPayeracctnbr())) {
			// 如果商户结算账户与付款账户相等，说明已经结算
			transData.setSettlestatus(SettleStatus.SETTLED);
			transData.setPayeeacctdeptnbr(confirm.getPayeeacctdeptnbr());
			transData.setPayeeacctnbr(confirm.getPayeeacctnbr());
			transData.setPayeeacctname(confirm.getPayeeacctname());
			transData.setPayeeacctkind(confirm.getPayeeacctkind());
			transData.setPayeeaccttypcd(confirm.getPayeeaccttypcd());
			transData.setPayeecardtypcd(confirm.getPayeecardtypcd());
		} else {
			// 未结算
			transData.setPayeeacctdeptnbr(acct.getDeptnbr());
			transData.setPayeeacctnbr(acct.getAcctnbr());
			transData.setPayeeacctname(acct.getAcctname());
			transData.setPayeeacctkind(acct.getAcctkindcd());
			transData.setPayeeaccttypcd(acct.getAccttypcd());
		}
		if (SettMode.BITE.equals(acctinfo.getSettmode())) {
			// 结算方式为差额
			transData.setTransamt(confirm.getTransamt().subtract(feeAmt));
		} else if (SettMode.DIRT.equals(acctinfo.getSettmode())) {
			// 结算方式为全额
			transData.setTransamt(confirm.getTransamt());
		}
		transData.setDepartmentnbr(acct.getDeptnbr());
		try {
			BatchmertransDAO.insertSelective(transData);
		} catch (SQLException e) {
			throw new PeRuntimeException(e);
		}
	}
	
	private BigDecimal withdrawFeeClearing_DIRT(Onlinetranshist origTrans, Deptacctinfo acct, Meracctinfo acctinfo,BigDecimal transamt,BigDecimal unrefundamt,String supfeenbr,Date checkDate) {
		BigDecimal feeAmount = new BigDecimal(0);
		
		String feeNbr = null;
		if (supfeenbr == null) {
			feeNbr = acctinfo.getFeenbr();
		} else {
			feeNbr = supfeenbr;
		}
		
		Map map = BatchConfirmSubClearTransExtendDAO.getBatchconfirmsubcleartrans(origTrans.getTransseqnbr());
		// 如果商户为退货不退手续费或者商户为定额、包月、分段的都不退手续费
		if (FeeReturnFlag.N.equals(acctinfo.getFeereturnflag()) || FeeMode.MON.equals(acctinfo.getFeemode())
				|| FeeMode.SEC.equals(acctinfo.getFeemode())) {
			feeAmount = BigDecimal.ZERO;
		} else if (feeNbr != null) { // 费率编号为null则无需计算手续费
			if (FeeMode.FIX.equals(acctinfo.getFeemode())) {
				feeAmount = this.getFeeAmount(feeNbr, transamt);
			} else {
				/*
				 * 退还时的手续费计算 用剩余手续费减去应该保留的手续费
				 */
				// 未退货金额
				BigDecimal reservedAmt = unrefundamt;
				// 被退货时候还剩余的手续费金额
				transamt = reservedAmt.add(transamt);
				BigDecimal reservedFeeAmt1 = this.getFeeAmount(feeNbr, transamt);
				// 本次退货后需要保留的手续费金额
				BigDecimal reservedFeeAmt = this.getFeeAmount(feeNbr, reservedAmt);
				if (reservedFeeAmt.compareTo(reservedFeeAmt1) > 0) {
					reservedFeeAmt = reservedFeeAmt1;
				}
				// 本次退货需要返还的手续费金额
				feeAmount = reservedFeeAmt1.subtract(reservedFeeAmt);
			}
		}

		// 如果手续费金额大于0，往商户台账表中插一条退手续费的记录
		if (feeAmount.compareTo(BigDecimal.ZERO) > 0) {
			Batchmertrans transData = new Batchmertrans();
			transData.setCleartransnbr(IDGenerateFactory.generateSeqId());
			transData.setTransseqnbr(origTrans.getTransseqnbr());
			transData.setMerseqnbr(origTrans.getMerseqnbr());
			transData.setCleardate(origTrans.getCleardate());
			transData.setSettledate(DateUtil.getSettlementDate(acctinfo.getFeesettperiod(), checkDate)); 
			transData.setTransdate(origTrans.getTransdate());
			transData.setTranstime(origTrans.getTranstime());
			transData.setSettlestatus(SettleStatus.UNSETTLE);
			transData.setMernbr(origTrans.getMernbr());
			transData.setCustcifnbr(origTrans.getCustcifnbr());
			// 付款方账户信息
			transData.setPayeracctdeptnbr(origTrans.getMerdevdeptnbr()); // 修改为商户发展行机构
			transData.setPayeracctnbr(acct.getFeeacctnbr());
			transData.setPayeracctname(acct.getFeeacctname());
			transData.setPayeracctkind(acct.getFeeacctkind());
			transData.setPayeraccttypcd(acct.getFeeaccttpycd());
			// 收款方账户信息
			if (acctinfo.getSettleacctnbr().equals(origTrans.getPayeracctnbr())) {
				transData.setStandbooktypcd(StandBookTypCD.MERCHANT_TRANS_FEE_RETURN);
				transData.setPayeeacctdeptnbr(acctinfo.getMeropendeptnbr());
				transData.setPayeeacctnbr(acctinfo.getFeeacctnbr());
				transData.setPayeeacctname(acctinfo.getFeeacctname());
				transData.setPayeeacctkind(acctinfo.getFeeacctkind());
				transData.setPayeeaccttypcd(acctinfo.getFeeaccttyp());
			} else {
				transData.setStandbooktypcd(StandBookTypCD.MERCHANT_TRANS_BALANCE_FEE_RETURN);
				transData.setPayeeacctdeptnbr(acct.getDeptnbr());
				transData.setPayeeacctnbr(acct.getAcctnbr());
				transData.setPayeeacctname(acct.getAcctname());
				transData.setPayeeacctkind(acct.getAcctkindcd());
				transData.setPayeeaccttypcd(acct.getAccttypcd());
			}
			transData.setTransamt(feeAmount);
			transData.setDepartmentnbr(acct.getDeptnbr());
			try {
				BatchmertransDAO.insertSelective(transData);
			} catch (SQLException e) {
				throw new PeRuntimeException(e);
			}
		}
		return feeAmount;
	}
	
	private void withdrawSettlement_DIRT(Onlinetranshist trans, Deptacctinfo acct, Meracctinfo acctinfo,BigDecimal feeAmt,Date checkDate) {
		Batchmertrans transData = new Batchmertrans();
		transData.setCleartransnbr(IDGenerateFactory.generateSeqId());
		transData.setTransseqnbr(trans.getTransseqnbr());
		transData.setMerseqnbr(trans.getMerseqnbr());
		transData.setCleardate(trans.getCleardate());
		transData.setSettledate(DateUtil.getSettlementDate(acctinfo.getSettperiod(), checkDate));
		transData.setTransdate(trans.getTransdate());
		transData.setTranstime(trans.getTranstime());
		transData.setSettlestatus(SettleStatus.UNSETTLE);
		transData.setMernbr(trans.getMernbr());
		transData.setCustcifnbr(trans.getCustcifnbr());
		transData.setStandbooktypcd(StandBookTypCD.MERCHANT_TRANS_WITHDRAW);
		// 付款方账户信息
		transData.setPayeracctdeptnbr(acctinfo.getMeropendeptnbr());
		transData.setPayeracctnbr(acctinfo.getSettleacctnbr()); // 商户结算账号
		transData.setPayeracctname(acctinfo.getSettleacctname());
		transData.setPayeracctkind(acctinfo.getSettleacctkind());
		transData.setPayeraccttypcd(acctinfo.getSettleaccttyp());
		// 收款方账户信息
		if (acctinfo.getSettleacctnbr().equals(trans.getPayeracctnbr())) {
			// 如果商户结算账户与付款账户相等，说明已经结算
			transData.setSettlestatus(SettleStatus.SETTLED);
			transData.setPayeeacctdeptnbr(trans.getPayeeacctdeptnbr());
			transData.setPayeeacctnbr(trans.getPayeeacctnbr());
			transData.setPayeeacctname(trans.getPayeeacctname());
			transData.setPayeeacctkind(trans.getPayeeacctkind());
			transData.setPayeeaccttypcd(trans.getPayeeaccttypcd());
			transData.setPayeecardtypcd(trans.getPayeecardtypcd());
		} else {
			// 未结算
			transData.setPayeeacctdeptnbr(acct.getDeptnbr());
			transData.setPayeeacctnbr(acct.getAcctnbr());
			transData.setPayeeacctname(acct.getAcctname());
			transData.setPayeeacctkind(acct.getAcctkindcd());
			transData.setPayeeaccttypcd(acct.getAccttypcd());
		}
		if (SettMode.BITE.equals(acctinfo.getSettmode())) {
			// 结算方式为差额
			transData.setTransamt(trans.getTransamt().subtract(feeAmt));
		} else if (SettMode.DIRT.equals(acctinfo.getSettmode())) {
			// 结算方式为全额
			transData.setTransamt(trans.getTransamt());
		}
		transData.setDepartmentnbr(acct.getDeptnbr());
		try {
			BatchmertransDAO.insertSelective(transData);
		} catch (SQLException e) {
			throw new PeRuntimeException(e);
		}
	}
	
	/**
	 * 判断是否全部退款
	 * @param origsubtrans
	 * @param withDrawConfirm
	 * @return true 全部退款
	 */
	private boolean isAllWithdraw(Onlinesubtranshist origsubtrans,Batchconfirmsubcleartrans withDrawConfirm){
		if( TransStatus.ALL_WITHDRAW.equals(origsubtrans.getTransstatus()) 
				&& !StringUtil.isStringEmpty(withDrawConfirm.getMemo1()) 
				&& TransStatus.ALL_WITHDRAW.equals(withDrawConfirm.getMemo1().trim()) ){
			//原支付为全部退货状态，且当前退货为最后一笔退货
			return true;
		}
		return false;
	}
	
	/**
	 *一口清分商户分润
	 */
	private void profitAssign_BITE(Batchconfirmsubcleartrans confirm,Deptacctinfo acct,BigDecimal feeAmount, Meracctinfo acctinfo,Date checkDate) {
		try {
			//查询是否存在分润流水号
			String profitSeqNbr = confirm.getProfitseqnbr();
			if(StringUtil.isObjectEmpty(profitSeqNbr)){
				log.info("交易分润号为空,subtransseqbr["+confirm.getSubtransseqnbr()+"]");
				return;
			}
			
			//根据根据分润流水号查询分润信息			
			//参与分润的手续费台账信息不需要再结算
			BatchmertransExample batchMerTransExample = new BatchmertransExample();
			List feeStandBookTypCdList = new ArrayList();
			feeStandBookTypCdList.add(StandBookTypCD.MERCHANT_TRANS_BALANCE_FEE_RCV);
			feeStandBookTypCdList.add(StandBookTypCD.MERCHANT_TRANS_FEE_DIRT_RCV);
			batchMerTransExample.createCriteria().andSubtransseqnbrEqualTo(confirm.getSubtransseqnbr()).andStandbooktypcdIn(feeStandBookTypCdList);
			Batchmertrans record = new Batchmertrans();
			record.setSettlestatus(SettleStatus.SETTLED);//设置为已结算
			BatchmertransDAO.updateByExampleSelective(record, batchMerTransExample);
			// 通过费率号获取费率参数
			FeeparamExample example = new FeeparamExample();
			example.createCriteria().andFeenbrEqualTo(profitSeqNbr);
			example.setOrderByClause("SEQNBR");
			List<Feeparam> feeparams = null;
			try {
				feeparams = FeeparamDAO.selectByExample(example);
			} catch (SQLException e) {
				throw new PeRuntimeException(e);
			}	
			if(feeparams.size()!=1){
				log.error("费率参数维护异常！费率编号["+profitSeqNbr+"]");
				throw new PeRuntimeException();
			}
			//根据手续费和费率参数，计算分润金额
			FeeService feecal = (FeeService) this.getRouterService("FEE".toLowerCase());
			BigDecimal feeProfit = new BigDecimal(0);
			try {
				feeProfit = feecal.calculateFee(feeparams, feeAmount, null).get(Dict.FEE_AMT);
			} catch (Exception e) {
				throw new PeRuntimeException(e);
			}
			//付款方信息
			Batchfeeprofittrans batchfeeprofittrans = new Batchfeeprofittrans();
			batchfeeprofittrans.setCleartransnbr(confirm.getSubtransseqnbr());
			batchfeeprofittrans.setTransseqnbr(confirm.getTransseqnbr());
			batchfeeprofittrans.setMerseqnbr(confirm.getMerseqnbr());
			batchfeeprofittrans.setCleardate(confirm.getCleardate());
			batchfeeprofittrans.setSettledate(DateUtil.getSettlementDate(acctinfo.getFeesettperiod(), checkDate));
			batchfeeprofittrans.setTransdate(confirm.getTransdate());
			batchfeeprofittrans.setTranstime(confirm.getTranstime());
			if (SettPeriod.REALTIME.equals(acctinfo.getSettperiod())) {
				//transData.setSettlestatus(SettleStatus.SETTLED);
			}else {
				batchfeeprofittrans.setSettlestatus(SettleStatus.UNSETTLE);
			}
			batchfeeprofittrans.setMernbr(confirm.getMernbr());//
			batchfeeprofittrans.setCustcifnbr(confirm.getCustcifnbr());
			if(SettMode.BITE.equals(acctinfo.getSettmode())){//差额付款方信息设置 
				//batchfeeprofittrans.setStandbooktypcd(StandBookTypCD.MERCHANT_TRANS_BALANCE_FEE_RCV);
				batchfeeprofittrans.setPayeracctnbr(acct.getAcctnbr());
				batchfeeprofittrans.setPayeraccttypcd(acct.getAccttypcd());
				batchfeeprofittrans.setPayeracctdeptnbr(acct.getDeptnbr());
				batchfeeprofittrans.setPayeracctname(acct.getAcctname());
				batchfeeprofittrans.setPayeracctkind(acct.getAcctkindcd());
				
			}else if(SettMode.DIRT.equals(acctinfo.getSettmode())){//全额付款方信息设置 
				//batchfeeprofittrans.setStandbooktypcd(StandBookTypCD.MERCHANT_TRANS_FEE_DIRT_RCV);
				batchfeeprofittrans.setPayeracctnbr(acctinfo.getFeeacctnbr());
				batchfeeprofittrans.setPayeraccttypcd(acctinfo.getFeeaccttyp());
				batchfeeprofittrans.setPayeracctdeptnbr(acctinfo.getMeropendeptnbr());
				batchfeeprofittrans.setPayeracctname(acctinfo.getFeeacctname());
				batchfeeprofittrans.setPayeracctkind(acctinfo.getFeeacctkind());
			}			
			// 收款方信息设置
			if (feeProfit.compareTo(BigDecimal.ZERO) > 0) {//手续费大于0就记录收取商户手续费的台账
				String agencyNbr = acctinfo.getAgencynbr();
				if(StringUtil.isObjectEmpty(agencyNbr)){
					throw new PeRuntimeException("未查到代理商信息！");
				}
				//查询代理商账户信息
				Agency agency = AgencyDAO.selectByPrimaryKey(agencyNbr);
				batchfeeprofittrans.setProfitcleartransnbr(IDGenerateFactory.generateSeqId());
				batchfeeprofittrans.setPayeeacctnbr(agency.getAgencysettleacct());
				batchfeeprofittrans.setPayeeaccttypcd(acct.getFeeaccttpycd());
				batchfeeprofittrans.setPayeeacctdeptnbr(acct.getDeptnbr());
				batchfeeprofittrans.setPayeeacctname(agency.getAgencyname());
				batchfeeprofittrans.setPayeeacctkind(acct.getFeeacctkind());
				batchfeeprofittrans.setTransamt(feeProfit);
				batchfeeprofittrans.setDepartmentnbr(acct.getDeptnbr());
				try {
					BatchfeeprofittransDAO.insertSelective(batchfeeprofittrans);
				} catch (SQLException e) {
					throw new PeRuntimeException(e);
				}	
			}
			BigDecimal feeProfitBank = feeAmount.subtract(feeProfit).setScale(2,BigDecimal.ROUND_HALF_UP);
			//银行分润金额
			//插入分润表,银行与代理商分润，分润表计算出代理商的手续费分润金额，银行分润金额=手续费总金额-代理商分润金额
			if(feeProfitBank.compareTo(BigDecimal.ZERO) > 0){
				batchfeeprofittrans.setProfitcleartransnbr(IDGenerateFactory.generateSeqId());
				batchfeeprofittrans.setPayeeacctnbr(acct.getFeeacctnbr());
				batchfeeprofittrans.setPayeeaccttypcd(acct.getFeeaccttpycd());
				batchfeeprofittrans.setPayeeacctdeptnbr(acct.getDeptnbr());
				batchfeeprofittrans.setPayeeacctname(acct.getFeeacctname());
				batchfeeprofittrans.setPayeeacctkind(acct.getFeeacctkind());
				batchfeeprofittrans.setTransamt(feeProfitBank);
				batchfeeprofittrans.setDepartmentnbr(acct.getDeptnbr());
				try {
					BatchfeeprofittransDAO.insertSelective(batchfeeprofittrans);
				} catch (SQLException e) {
					throw new PeRuntimeException(e);
				}					
			}
		} catch (Exception e) {
			throw new PeRuntimeException(e);
		}
	}	
	
}
