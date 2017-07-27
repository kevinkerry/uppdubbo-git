package com.csii.upp.payment.action.start;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.AcctTypCd;
import com.csii.upp.constant.CardTypCd;
import com.csii.upp.constant.CodeTypCd;
import com.csii.upp.constant.InteralFlag;
import com.csii.upp.constant.IsParentMer;
import com.csii.upp.constant.MerStatus;
import com.csii.upp.constant.PayModeCd;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.TransId;
import com.csii.upp.dao.generate.AgencyDAO;
import com.csii.upp.dao.generate.DeptacctinfoDAO;
import com.csii.upp.dao.generate.MeracctinfoDAO;
import com.csii.upp.dao.generate.MerbaseinfoDAO;
import com.csii.upp.dao.generate.PaytypDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.FeeInput;
import com.csii.upp.dto.extend.FeeOutput;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.extend.SubTransData;
import com.csii.upp.dto.generate.Agency;
import com.csii.upp.dto.generate.Deptacctinfo;
import com.csii.upp.dto.generate.DeptacctinfoExample;
import com.csii.upp.dto.generate.Meracctinfo;
import com.csii.upp.dto.generate.Merbaseinfo;
import com.csii.upp.dto.generate.Paytyp;
import com.csii.upp.service.FeeService;
import com.csii.upp.util.StringUtil;

/**
 * 支付交易插入子交易明细和总交易明细
 * 
 * @author xujin
 *
 */
public class AddSGCTTransAction extends BaseOnlineTransAction {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void prepareOnlineTrans(Context ctx,InputPaymentData inputData) throws PeException {
		String payTypCd = ctx.getString(Dict.PAY_TYP_CD);
		// 本行网银支付由于浙江网银不改变无法上传支付方式，所以这里要加判断
		String transId=ctx.getString(Dict.TRANS_ID);
		if(StringUtil.isStringEmpty(payTypCd)
				&&(TransId.FYEP.equals(transId)||TransId.PP01.equals(transId))){
			payTypCd=PayTypCd.OURCYBER;
			inputData.setPaytypcd(payTypCd);
			inputData.setPayercardtypcd(inputData.getPayercardtypcd().replaceAll("D", CardTypCd.DEBIT));
			inputData.setPayercardtypcd(inputData.getPayercardtypcd().replaceAll("C", CardTypCd.CREDIT));
		}
		// 支付方式不能为空
		if (StringUtil.isStringEmpty(payTypCd)) {
			throw new PeException(DictErrors.VALUE_NOT_EMPTY,new Object[] { Dict.PAY_TYP_CD});
		}
		
		// 活动字段不为空则赋值
		String actMemo1=null;
		if (!StringUtil.isStringEmpty(ctx.getString(Dict.ACT_MEMO))) {
		     actMemo1 = ctx.getString(Dict.ACT_MEMO);
		}
		
		if(!StringUtil.isStringEmpty(inputData.getPayeracctnbr())){
			// 付款卡号类型不能为空
			if (StringUtil.isStringEmpty(inputData.getPayercardtypcd())) {
				throw new PeException(DictErrors.VALUE_NOT_EMPTY,new Object[] { Dict.PAYER_CARD_TYP_CD});
			}
		}
		//验证商户流水号和交易金额
		this.checkCommon(inputData.getMerseqnbr(), inputData.getTransamt());
		// 支付方式不能为空
		if (StringUtil.isStringEmpty(inputData.getPaytypcd())) {
			throw new PeException(DictErrors.VALUE_NOT_EMPTY,new Object[] { Dict.PAY_TYP_CD});
		}
		//支付宝验证交易金额和打折金额
		if(CodeTypCd.ALIPAY.equals(inputData.getCodetypcd())){
			this.checkdiscountAmt(inputData,inputData.getDiscountableamt(),inputData.getTransamt());
		}
		//积分金额检查
		if(InteralFlag.YES.equals(inputData.getInteralflag())){
			this.checkPointAmt(inputData, inputData.getDeductamt(), inputData.getTransamt());
		}
		Paytyp paytyp=null;
		Meracctinfo merAcct=null;
		try {
			paytyp = PaytypDAO.selectByPrimaryKey(payTypCd);
			// 判断一级商户下是否有该二级商户
			merAcct = MeracctinfoDAO.selectByPrimaryKey(inputData
					.getMernbr());
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		// 判断该商户是否存在
		if (merAcct == null) {
			throw new PeException(DictErrors.MERCHANT_NOT_EXIST);
		}
		// 商户已冻结或注销
		if (!MerStatus.NORMAL.equals(merAcct.getMerstatus())) {
			throw new PeException(DictErrors.MER_STATUS_INVALID);
		}

		inputData.setPayeraccttypcd(paytyp.getAcctypcd());
		inputData.setPayeracctkind(paytyp.getAcctkind());
		// 商户开户行
		inputData.setMeropendeptnbr(merAcct.getMeropendeptnbr());
		//活动字段
		inputData.setMemo2(actMemo1);
		// 商户发展行
		inputData.setMerdevdeptnbr(merAcct.getMerdevelopdeptnbr());
		inputData.setMerAcct(merAcct);
		//获得清算机构编号
		inputData.setDepartmentnbr(this.getMerDepartmentnbr(inputData.getMernbr()));
		
			
			List<SubTransData> onlineSubTransList = new ArrayList<SubTransData>();
		
			Map subMap = new HashMap();
			subMap.put(Dict.SUB_MERCHANT_ID,inputData.getSubMerchantId());
			subMap.put(Dict.SUB_MER_SEQ_NO,inputData.getMerseqnbr());
			subMap.put(Dict.SUB_TRANS_AMT,inputData.getTransAmount());
			subMap.put(Dict.ORG_SUB_MER_DATE_TIME,inputData.getMertransdatetime());
	
				SubTransData subtrans=this.makeSubTrans(subMap, inputData);
				
				onlineSubTransList.add(subtrans);
				inputData.setTransmode(subtrans.getTransmode());
				inputData.setPayeeacctnbr(subtrans.getPayeeacctnbr());
				inputData.setPayeeacctname(subtrans.getPayeeacctname());
				inputData.setPayeeaccttypcd(subtrans.getPayeeaccttypcd());
				inputData.setPayeeacctkind(subtrans.getPayeeacctkind());
				if(InteralFlag.YES.equals(inputData.getInteralflag())){
					inputData.setPointreacctdeptnbr(subtrans.getPointreacctdeptnbr());
				}
				inputData.setOnlineSubTransList(onlineSubTransList);
	}
	

	@Override
	protected void prepareOnlineSubTrans(InputPaymentData transData, SubTransData subTrans) throws PeException {
		// 验证商户流水号和交易金额
		this.checkCommon(subTrans.getMerseqnbr(), subTrans.getTransamt());
		Meracctinfo subMerAcct = null;
		try {
			// 判断一级商户下是否有该二级商户
			subMerAcct = MeracctinfoDAO.selectByPrimaryKey(subTrans.getMernbr());
			// 判断该商户是否是二级商户
			if (subMerAcct == null || IsParentMer.Y.equals(subMerAcct.getIsparentmer())
					|| (!subMerAcct.getSupermernbr().equals(transData.getMernbr()))) {
				throw new PeException(DictErrors.MER_NOT_FOUND_SUB_MER, new Object[] { subTrans.getMernbr() });
			}
			// 商户已冻结或注销
			if (!MerStatus.NORMAL.equals(subMerAcct.getMerstatus())) {
				throw new PeException(DictErrors.MER_STATUS_INVALID);
			}
			// 实时结算商户与非实时结算商户区别处理
			if (StringUtil.isStringEmpty(subMerAcct.getSettperiod())) {
				throw new PeException(DictErrors.MER_SETT_PERIOD_ERROR);
			}
			// 商户支付类型(0或 null:全支持，1: 借记卡、2: 贷记卡)判断是否支持目前卡类型交易
			// if (CardTypCd.DEBIT.equals(transData.getPayercardtypcd())
			// &&CardTypCd.CREDIT.equals(merAcct.getCardtypecd())) {
			// throw new PeException(DictErrors.MER_ONLY_DEBIT,new
			// Object[]{transData
			// .getMernbr()});
			// } else if (CardTypCd.CREDIT.equals(transData.getPayercardtypcd())
			// &&CardTypCd.DEBIT.equals(merAcct.getCardtypecd())) {
			// throw new PeException(DictErrors.MER_ONLY_CREDIT,new
			// Object[]{transData
			// .getMernbr()});
			// }

			if (CardTypCd.DEBIT.equals(transData.getPayercardtypcd())
					&& CardTypCd.CREDIT.equals(subMerAcct.getCardtypecd())) {
				throw new PeException(DictErrors.MER_ONLY_DEBIT, new Object[] { subTrans.getMernbr() });
			} else if (CardTypCd.CREDIT.equals(transData.getPayercardtypcd())
					&& CardTypCd.DEBIT.equals(subMerAcct.getCardtypecd())) {
				throw new PeException(DictErrors.MER_ONLY_CREDIT, new Object[] { subTrans.getMernbr() });
			}
			// 支付方式为它行扫码支付从商户基础信息表获取商户同步信息
			if (PayTypCd.OTHACTSCAN.equals(transData.getPaytypcd())
					|| PayTypCd.OTHPASSCAN.equals(transData.getPaytypcd())) {
				Merbaseinfo merchant = MerbaseinfoDAO.selectByPrimaryKey(subTrans.getMernbr());
				if (merchant == null) {
					throw new PeException(DictErrors.MERCHANT_NOT_EXIST);
				}
				if (CodeTypCd.WECHAT.equals(transData.getCodetypcd())) {
					subTrans.setThirdmernbr(merchant.getWechatsubmerchantid());
					subTrans.setWechatproxymernbr(merchant.getProxymernbr());
				}
				if (CodeTypCd.ALIPAY.equals(transData.getCodetypcd())) {
					subTrans.setThirdmernbr(merchant.getAlipaymerchantid());
				}
				subTrans.setMerName(merchant.getMershortname());
			}
			if (!StringUtil.isStringEmpty(subMerAcct.getAgencynbr())) {
				Agency agency = AgencyDAO.selectByPrimaryKey(subMerAcct.getAgencynbr());
				subTrans.setProfitseqnbr(agency.getAgencyprofitnbr());
			}

		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}

		// 接入方式：电商、O2O
		subTrans.setTransmode(subMerAcct.getInneracctcfmmode());
		// 商户开户行
		subTrans.setMeropendeptnbr(subMerAcct.getMeropendeptnbr());
		// 商户发展行
		subTrans.setMerdevdeptnbr(subMerAcct.getMerdevelopdeptnbr());

		subTrans.setSubMerAcct(subMerAcct);
		// 冻结支付收款账户信息为商户结算账户信息否则为商户所属机构的信息
		if (PayModeCd.HOLD.equals(subMerAcct.getPaymodecd())) {
			subTrans.setPayeeacctdeptnbr(subMerAcct.getMeropendeptnbr());
			subTrans.setPayeeacctnbr(subMerAcct.getSettleacctnbr());
			subTrans.setPayeeacctname(subMerAcct.getSettleacctname());
			subTrans.setPayeeaccttypcd(subMerAcct.getSettleaccttyp());
			subTrans.setPayeeacctkind(subMerAcct.getSettleacctkind());
		} else {
			Deptacctinfo deptAcct = null;
			try {
				deptAcct = DeptacctinfoDAO.selectByPrimaryKey(subMerAcct.getMerdevelopdeptnbr(),
						subMerAcct.getInneracctcfmmode());
			} catch (SQLException e) {
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
			subTrans.setPayeeacctdeptnbr(deptAcct.getDeptnbr());
			subTrans.setPayeeacctnbr(deptAcct.getAcctnbr());
			subTrans.setPayeeacctname(deptAcct.getAcctname());
			subTrans.setPayeeaccttypcd(AcctTypCd.INNER);
			subTrans.setPayeeacctkind(deptAcct.getAcctkindcd());
		}

		// 积分支付 根据行社机构号获取行社积分账户
		if (InteralFlag.YES.equals(subTrans.getInteralflag())) {
			List<Deptacctinfo> pointDeptacctinfo = new ArrayList<Deptacctinfo>();
			try {
				DeptacctinfoExample example = new DeptacctinfoExample();
				example.createCriteria().andDeptnbrEqualTo(subTrans.getBranchno());
				pointDeptacctinfo = DeptacctinfoDAO.selectByExample(example);
			} catch (SQLException e) {
				throw new PeException(DictErrors.DATABASE_EXCEPTION);
			}
			if (pointDeptacctinfo != null && pointDeptacctinfo.size() > 0) {
				subTrans.setPointreacctdeptnbr(pointDeptacctinfo.get(0).getPointreacctdeptnbr());
			} else {
				throw new PeException(DictErrors.DATABASE_EXCEPTION);
			}
		}
		// 不是他行网银支付可以先计算手续费参数，他行网银在批量计算手续费参数
		if (!PayTypCd.OTHCYBER.equals(transData.getPaytypcd())) {
			FeeInput feeInput = new FeeInput();
			feeInput.setChannelNbr(transData.getChannelnbr());
			feeInput.setCardTypCd(transData.getPayercardtypcd());
			feeInput.setMerNbr(transData.getMernbr());
			feeInput.setSubMerNbr(subTrans.getMernbr());
			feeInput.setPayTypCd(subTrans.getPaytypcd());
			feeInput.setTransTypCd(subTrans.getTranstypcd());
			FeeOutput feeOutput = ((FeeService) this.getFeeService()).calcMerFeeParam(feeInput, transData.getMerAcct(),
					subTrans.getSubMerAcct());
			subTrans.setFeenbr(feeOutput.getFeeNbr());
			subTrans.setSupfeenbr(feeOutput.getSuperFeeNbr());
		}
	}
}
