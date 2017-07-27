package com.csii.upp.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.upp.constant.BoundFlag;
import com.csii.upp.constant.CalculateType;
import com.csii.upp.constant.ClearClassModeCd;
import com.csii.upp.constant.DeductFlag;
import com.csii.upp.constant.FloatFlag;
import com.csii.upp.constant.ProgressFlag;
import com.csii.upp.dao.generate.FeeparamDAO;
import com.csii.upp.dao.generate.MerfeerelDAO;
import com.csii.upp.dao.generate.PaytypetransctrlDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.FeeInput;
import com.csii.upp.dto.extend.FeeOutput;
import com.csii.upp.dto.generate.Feeparam;
import com.csii.upp.dto.generate.FeeparamExample;
import com.csii.upp.dto.generate.Meracctinfo;
import com.csii.upp.dto.generate.Merfeerel;
import com.csii.upp.dto.generate.Paytypetransctrl;
import com.csii.upp.util.StringUtil;

/**
 * 交易费率计算服务
 * 
 * @author 徐锦
 *
 */
public class FeeService extends BasePayService {
	/**
	 * 根据配置的计费规则以及各种交易类型汇总的总金额与总笔数计算手续费
	 * @param feeparams 手续费率列表
	 * @param amounts 交易金额
	 * @param numbers 交易笔数
	 * @return
	 * @throws Exception
	 */
	public Map<String, BigDecimal> calculateFee(List<Feeparam> feeparams, BigDecimal amounts, BigDecimal numbers)
			throws Exception {
		//numbers = BigDecimal.ONE;
		Map result = new HashMap();
		BigDecimal fee = BigDecimal.ZERO;
		BigDecimal reamount = BigDecimal.ZERO;
		BigDecimal startParam = BigDecimal.ZERO;
		BigDecimal endParam = BigDecimal.ZERO;
		BigDecimal totParam = BigDecimal.ZERO;
		for (Feeparam feeparam : feeparams) {
			switch (StringUtil.toInt(feeparam.getFloatflag())) {
				case FloatFlag.STATIC:
				switch (StringUtil.toInt(feeparam.getCalculatetyp())) {
					case CalculateType.PERCENT:
						//手续费保留两位小数（四舍五入）
						fee = amounts.multiply(new BigDecimal(feeparam.getCalculateparam())).setScale(2, BigDecimal.ROUND_HALF_UP);
						break;
					case CalculateType.STATIC:
						fee = new BigDecimal(feeparam.getCalculateparam());
				}
				break;
			case FloatFlag.FLOAT_BY_AMOUNT:
			case FloatFlag.FLOAT_BY_NUMBER:
				startParam = StringUtil.isStringEmpty(feeparam.getStartparam()) ? startParam = BigDecimal.ZERO
						: new BigDecimal(feeparam.getStartparam());
				endParam = StringUtil.isStringEmpty(feeparam.getEndparam()) ? endParam = BigDecimal.ZERO
						: new BigDecimal(feeparam.getEndparam());
				totParam = FloatFlag.FLOAT_BY_AMOUNT == StringUtil.toInt(feeparam.getFloatflag()) ? amounts
						: numbers;
				if (("0".equals(feeparam.getContainflag()) && totParam.compareTo(startParam) >= 0
						&& totParam.compareTo(endParam) < 0)
						|| ("1".equals(feeparam.getContainflag()) && totParam.compareTo(startParam) > 0
								&& totParam.compareTo(endParam) <= 0)
						|| (totParam.compareTo(startParam) > 0 && totParam.compareTo(endParam) < 0)) {
					if (ProgressFlag.YES.equals(feeparam.getProgressflag())) {
						// 超额累进
						fee = processRun(feeparam, feeparams, totParam);
						break;
					} else if (ProgressFlag.NO.equals(feeparam.getProgressflag())) {
						switch (StringUtil.toInt(feeparam.getCalculatetyp())) {
						case CalculateType.PERCENT:
							//手续费保留两位小数（四舍五入）
							fee = amounts.multiply(new BigDecimal(feeparam.getCalculateparam())).setScale(2, BigDecimal.ROUND_HALF_UP);
							break;
						case CalculateType.STATIC:
							fee = new BigDecimal(feeparam.getCalculateparam());
						}
						break;
					}
				}
			}
			if (fee == null) {
				throw new PeException(DictErrors.FEE_PARAMETER_ERROR);
			}

			if (!BoundFlag.NO.equals(feeparam.getBoundflag())) {
				if (BoundFlag.YES.equals(feeparam.getBoundflag())) {
					fee = feeLimit(fee, feeparam);
				} else
					throw new PeException(DictErrors.FEE_BOUND_ERROR);
			}

			if (DeductFlag.OUT_PAY.equals(feeparam.getDeductflag())) {
				reamount = amounts;
			} else if (DeductFlag.INNER_PAY.equals(feeparam.getDeductflag())) {
				reamount = amounts.subtract(fee);
			} else
				throw new PeException(DictErrors.FEE_DEDUCT_ERROR);
		}
		
		result.put(Dict.TRANS_AMT, reamount);
		result.put(Dict.FEE_AMT, fee);
		return result;
	}

	/**
	 * 费用限额
	 * 
	 * @throws PeException
	 */
	private BigDecimal feeLimit(BigDecimal fee, Feeparam feeparam) throws PeException {
		if ((feeparam.getUpperlimitval() == null) || (feeparam.getLowerlimitval() == null)) {
			throw new PeException(DictErrors.FEE_LIMIT_ERROR);
		}

		if (fee.compareTo(feeparam.getUpperlimitval()) == 1) {
			fee = feeparam.getUpperlimitval();
		} else if (fee.compareTo(feeparam.getLowerlimitval()) == -1) {
			fee = feeparam.getLowerlimitval();
		}

		return fee;
	}

	/**
	 * 根据计费规则对手续费分段计费（超额累进）
	 */
	private BigDecimal processRun(Feeparam feeparam, List<Feeparam> feeparams, BigDecimal totParam) {
		BigDecimal fee = BigDecimal.ZERO;
		for (Feeparam feeparamRange : feeparams) {
			BigDecimal endParam = new BigDecimal(feeparamRange.getEndparam());
			BigDecimal calParam = new BigDecimal(feeparamRange.getCalculateparam());
			BigDecimal startParam = new BigDecimal(feeparamRange.getStartparam());

			switch (StringUtil.toInt(feeparamRange.getCalculatetyp())) {
			case CalculateType.STATIC: // 固定金额
				if (StringUtil.parseLong(feeparamRange.getSeqnbr()).intValue() <= StringUtil
						.parseLong(feeparam.getSeqnbr()).intValue()) {
					fee = fee.add(StringUtil.parseBigDecimal(feeparamRange.getCalculateparam()));
				}
				break;
			case CalculateType.PERCENT: // 百分比
				if (StringUtil.parseLong(feeparamRange.getSeqnbr()).intValue() < StringUtil
						.parseLong(feeparam.getSeqnbr()).intValue()) {
					//手续费保留两位小数（四舍五入）
					fee = fee.add(endParam.subtract(startParam).multiply(calParam).setScale(2, BigDecimal.ROUND_HALF_UP));
				} else if (StringUtil.parseLong(feeparamRange.getSeqnbr()).intValue() == StringUtil
						.parseLong(feeparam.getSeqnbr()).intValue()) {
					//手续费保留两位小数（四舍五入）
					fee = fee.add(totParam.subtract(startParam).multiply(calParam).setScale(2, BigDecimal.ROUND_HALF_UP));
				}
				break;
			}
		}

		return fee;
	}

	/**
	 * 获取渠道对应的计费规则
	 */
	public List<Feeparam> getFeeParamList(String feenbr) throws SQLException {
		FeeparamExample example = new FeeparamExample();
		example.createCriteria().andFeenbrEqualTo(feenbr);

		return FeeparamDAO.selectByExample(example);
	}

	/**
	 * 计算商户手续费参数
	 * @param feeInput
	 * @param merAcct
	 * @param subMerAcct
	 * @return
	 * @throws PeException
	 */
	public FeeOutput calcMerFeeParam(FeeInput feeInput,Meracctinfo merAcct,Meracctinfo subMerAcct) throws PeException {
		FeeOutput feeOut = new FeeOutput();
		try {
			String clearClassModeCd = merAcct.getClearclassmodecd();
			if (ClearClassModeCd.DIRT.equals(clearClassModeCd)) {
				// 直接清分:只结算一级商户
				Merfeerel merFeeRel = MerfeerelDAO.selectByPrimaryKey(feeInput.getChannelNbr(), feeInput.getMerNbr(),
						feeInput.getTransTypCd(), feeInput.getCardTypCd(), feeInput.getPayTypCd());
				if (merFeeRel != null) {
					feeOut.setSuperFeeNbr(merFeeRel.getFeenbr());
				}else{
					feeOut.setSuperFeeNbr(merAcct.getFeenbr());
				}

			} else if (ClearClassModeCd.BITE.equals(clearClassModeCd)) {
				// 一口清分:只结算二级商户
				Merfeerel subMerFeeRel = MerfeerelDAO.selectByPrimaryKey(feeInput.getChannelNbr(),
						feeInput.getSubMerNbr(), feeInput.getTransTypCd(), feeInput.getCardTypCd(),
						feeInput.getPayTypCd());
				if (subMerFeeRel != null) {
					feeOut.setFeeNbr(subMerFeeRel.getFeenbr());
				}else if(subMerAcct.getFeenbr() != null){
					feeOut.setFeeNbr(subMerAcct.getFeenbr());
				}else {
					Paytypetransctrl paytypeTransCtrl = PaytypetransctrlDAO.selectByPrimaryKey(feeInput.getPayTypCd());
					feeOut.setFeeNbr(paytypeTransCtrl.getFeenbr());
				}
			} else if (ClearClassModeCd.STEP.equals(clearClassModeCd)) {
				// 递进清分:先结算一级商户再结算二级商户
				Merfeerel merFeeRel = MerfeerelDAO.selectByPrimaryKey(feeInput.getChannelNbr(), feeInput.getMerNbr(),
						feeInput.getTransTypCd(), feeInput.getCardTypCd(), feeInput.getPayTypCd());
				if (merFeeRel != null) {
					feeOut.setSuperFeeNbr(merFeeRel.getFeenbr());
				}else{
					feeOut.setSuperFeeNbr(merAcct.getFeenbr());
				}
				Merfeerel subMerFeeRel = MerfeerelDAO.selectByPrimaryKey(feeInput.getChannelNbr(),
						feeInput.getSubMerNbr(), feeInput.getTransTypCd(), feeInput.getCardTypCd(),
						feeInput.getPayTypCd());
				if (subMerFeeRel != null) {
					feeOut.setFeeNbr(subMerFeeRel.getFeenbr());
				}else{
					feeOut.setFeeNbr(subMerAcct.getFeenbr());
				}
			}
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		return feeOut;
	}
}
