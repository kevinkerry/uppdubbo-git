package com.csii.upp.payment.action.start;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.AcctTypCd;
import com.csii.upp.constant.InteralFlag;
import com.csii.upp.constant.IsParentMer;
import com.csii.upp.constant.MerStatus;
import com.csii.upp.constant.PayModeCd;
import com.csii.upp.constant.ProcStep;
import com.csii.upp.constant.RefundMode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.generate.DeptacctinfoDAO;
import com.csii.upp.dao.generate.MeracctinfoDAO;
import com.csii.upp.dao.generate.OnlinesubtransDAO;
import com.csii.upp.dao.generate.OnlinesubtranshistDAO;
import com.csii.upp.dao.generate.OnlinetransDAO;
import com.csii.upp.dao.generate.OnlinetranshistDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.extend.SubTransData;
import com.csii.upp.dto.generate.Deptacctinfo;
import com.csii.upp.dto.generate.Meracctinfo;
import com.csii.upp.dto.generate.Onlinesubtrans;
import com.csii.upp.dto.generate.OnlinesubtransExample;
import com.csii.upp.dto.generate.Onlinesubtranshist;
import com.csii.upp.dto.generate.OnlinesubtranshistExample;
import com.csii.upp.dto.generate.Onlinetrans;
import com.csii.upp.dto.generate.OnlinetransExample;
import com.csii.upp.dto.generate.Onlinetranshist;
import com.csii.upp.dto.generate.OnlinetranshistExample;
import com.csii.upp.util.StringUtil;

/**
 * 
 * 创建日期：2016年12月28日
 * Description:交易前数据准备实现类
 * @author xujin
 * @mender liru
 * @version  1.0
 * @Remark:  包含总交易流水信息准备与子交易流水信息准备两个方法
 */
public class AddReturnOnlineTransAction extends BaseOnlineTransAction {
 /**
  * 
  * 功能：交易前数据准备（总交易流水信息准备）
  * 作者：
  * @mender liru
  * @param  Context
  * @param  InputPaymentData transData
  * @return
  * @remack (认为有必要的描述)
  */	
	@Override
	protected void  prepareOnlineTrans(Context ctx,InputPaymentData transData) throws PeException {
		//原商户交易流水号是否为空
		if (StringUtil.isObjectEmpty(transData.getOrigmerseqnbr())) {
			throw new PeException(DictErrors.VALUE_NOT_EMPTY,
					new Object[] { Dict.ORIG_MER_SEQ_NBR });
		}
		transData.setTransamt(StringUtil.parseBigDecimal(ctx.getData(Dict.SUB_TRANS_AMT)));
		transData.setTransamt4(transData.getTransamt());
		// 未退货的金额
		transData.setUnrefundamt(BigDecimal.ZERO);
		//验证商户流水号和交易金额校验
		this.checkCommon(transData.getMerseqnbr(), transData.getTransamt());
		Meracctinfo merAcct = null;
		try {
			merAcct = MeracctinfoDAO.selectByPrimaryKey(transData.getMernbr());//查询商户账务信息
			transData.setMeropendeptnbr(merAcct.getMeropendeptnbr());//商户开户机构
			transData.setMerdevdeptnbr(merAcct.getMerdevelopdeptnbr());//商户发展机构
			// 查询原总交易流水
			Onlinetrans origTrans = null;
			List<String> statusList = new ArrayList<String>(Arrays.asList(TransStatus.PROCESSING, TransStatus.TIMEOUT,TransStatus.FAILURE));
			OnlinetransExample example = new OnlinetransExample();
			example.createCriteria().andMernbrEqualTo(transData.getMernbr()).andMerseqnbrEqualTo(transData.getOrigmerseqnbr()).andTransstatusNotIn(statusList);
			//查询原交易总交易明细 表(状态不为处理中、交易超时、交易失败)
			List<Onlinetrans> origOnlinetranss = OnlinetransDAO.selectByExample(example);
			if (origOnlinetranss.isEmpty()) {
				OnlinetranshistExample histExample = new OnlinetranshistExample();
				histExample.createCriteria().andMernbrEqualTo(transData.getMernbr())
						.andMerseqnbrEqualTo(transData.getOrigmerseqnbr())
						.andTransstatusNotIn(statusList);
				List<Onlinetranshist> origOnlinetranshists = OnlinetranshistDAO
						.selectByExample(histExample);
				if (origOnlinetranshists.isEmpty()) {
					throw new PeException(DictErrors.QUERY_ORIG_NOT_EXISTS);
				}
				Onlinetranshist origTransHist = origOnlinetranshists.get(0);
				origTrans = new Onlinetrans();
				// 更新退货订单中的原清算日期
				origTrans.setCleardate(origTransHist.getCleardate());
				origTrans.setTransseqnbr(origTransHist.getTransseqnbr());
				origTrans.setCustcifnbr(origTransHist.getCustcifnbr());
				origTrans.setPayeracctnbr(origTransHist.getPayeracctnbr());
				origTrans.setPayeracctname(origTransHist.getPayeracctname());
				origTrans.setPayercardtypcd(origTransHist.getPayercardtypcd());
				origTrans.setPayeraccttypcd(origTransHist.getPayeraccttypcd());
				origTrans.setPayeracctkind(origTransHist.getPayeracctkind());
				origTrans.setPayeracctdeptnbr(origTransHist.getPayeracctdeptnbr());
				origTrans.setDepartmentnbr(origTransHist.getDepartmentnbr());
				origTrans.setPaytypcd(origTransHist.getPaytypcd());
				origTrans.setProcstep(origTransHist.getProcstep());
				origTrans.setCybertypcd(origTransHist.getCybertypcd());
				origTrans.setTransamt(origTransHist.getTransamt());
				origTrans.setRefundedamt(origTransHist.getRefundedamt());
				//积分相关字段
				origTrans.setInteralflag(origTransHist.getInteralflag());
				origTrans.setDeductamt(origTransHist.getDeductamt());
				origTrans.setRealamt(origTransHist.getRealamt());
				origTrans.setClientno(origTransHist.getClientno());
				origTrans.setBranchno(origTransHist.getBranchno());
				origTrans.setPointreacctdeptnbr(origTransHist.getPointreacctdeptnbr());
				origTrans.setRefunddeductamt(origTransHist.getRefunddeductamt());
				origTrans.setUnrefunddeductamt(origTransHist.getUnrefunddeductamt());
				//扫码支付相关字段
				origTrans.setCodetypcd(origTransHist.getCodetypcd());//二维码类型:01-支付宝02-微信
				origTrans.setScancodemode(origTransHist.getScancodemode());//扫码模式:1-主扫2-贝嫂
				origTrans.setQrcodeordernbr(origTransHist.getQrcodeordernbr());//二维码订单号
				origTrans.setCodeurl(origTransHist.getCodeurl());//二维码地址
				//查询当前表还是历史表：true:历史表,false:当前表
				transData.setQueryHistFlag(true);
			} else {
				origTrans = origOnlinetranss.get(0);
			}
			transData.setPaytypcd(origTrans.getPaytypcd());
			// 更新退货订单中的原清算日期
			transData.setOrigcleardate(origTrans.getCleardate());
			transData.setOrigseqnbr(origTrans.getTransseqnbr());
			// 补充订单信息-如果订单信息中的客户号为空,使用原交易的客户号
			if (StringUtil.isStringEmpty(transData.getCustcifnbr())) {
				transData.setCustcifnbr(origTrans.getCustcifnbr());
			}
			// 退货交易的收款人账户、账户类型和账户机构为原支付交易的付款人账户、账户类型和账户机构
			transData.setPayeeacctnbr(origTrans.getPayeracctnbr());
			transData.setPayeeacctname(origTrans.getPayeracctname());
			transData.setPayeecardtypcd(origTrans.getPayercardtypcd());
			transData.setPayeeaccttypcd(origTrans.getPayeraccttypcd());
			transData.setPayeeacctkind(origTrans.getPayeracctkind());
			transData.setPayeeacctdeptnbr(origTrans.getPayeeacctdeptnbr());
			transData.setDepartmentnbr(origTrans.getDepartmentnbr());
			transData.setCybertypcd(origTrans.getCybertypcd());
			//积分相关字段
			transData.setInteralflag(origTrans.getInteralflag());
			transData.setClientno(origTrans.getClientno());
			transData.setBranchno(origTrans.getBranchno());
			transData.setPointreacctdeptnbr(origTrans.getPointreacctdeptnbr());
			transData.setRefunddeductamt(origTrans.getRefunddeductamt());
			transData.setUnrefunddeductamt(origTrans.getUnrefunddeductamt());
			//二维码类别
			transData.setCodetypcd(origTrans.getCodetypcd());
			//为下一步退货交易提供原总交易明细信息
			transData.setOrigTrans(origTrans);
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		SubTransData subTrans = this.makeSubTrans(ctx.getDataMap(), transData);
		List<SubTransData> onlineSubTransList = new ArrayList<SubTransData>();
		onlineSubTransList.add(subTrans);
		transData.setOnlineSubTransList(onlineSubTransList);
		transData.setTransmode(subTrans.getTransmode());
	}
 /**
  * 
  * 功能：交易前数据准备（子交易流水信息准备）
  * 作者：
  * @mender liru
  * @param  InputPaymentData
  * @param  SubTransData subTrans
  * @return
  * @remack (认为有必要的描述)
  */
	@Override
	protected void prepareOnlineSubTrans(InputPaymentData transData,SubTransData subTrans) throws PeException {
		//未退货的金额
		subTrans.setUnrefundamt(BigDecimal.ZERO);
		//验证商户流水号和交易金额
		this.checkCommon(subTrans.getMerseqnbr(), subTrans.getTransamt());
		Meracctinfo subMerAcct=null;
		Onlinesubtrans origTrans = null;
		try {
			// 判断一级商户下是否有该二级商户
			subMerAcct = MeracctinfoDAO.selectByPrimaryKey(subTrans
					.getMernbr());
			// 判断该商户是否是二级商户
			if (subMerAcct == null
					|| IsParentMer.Y.equals(subMerAcct.getIsparentmer())
					|| (!subMerAcct.getSupermernbr().equals(transData
							.getMernbr()))) {
				throw new PeException(DictErrors.MER_NOT_FOUND_SUB_MER,new Object[]{subTrans
						.getMernbr()});
			}
			// 商户已冻结或注销
			if (!MerStatus.NORMAL.equals(subMerAcct.getMerstatus())) {
				throw new PeException(DictErrors.MER_STATUS_INVALID);
			}
			// 商户结算周期不能为空
			if (StringUtil.isStringEmpty(subMerAcct.getSettperiod())) {
				throw new PeException(DictErrors.MER_SETT_PERIOD_ERROR);
			}
			List<String> statusList = new ArrayList<String>(Arrays.asList(
					TransStatus.PROCESSING, TransStatus.TIMEOUT,TransStatus.FAILURE));
			// 查询原子交易流水
			if (transData.isQueryHistFlag()) {
				OnlinesubtranshistExample histExample = new OnlinesubtranshistExample();
				histExample.createCriteria().andMernbrEqualTo(subTrans.getMernbr())
						.andMerseqnbrEqualTo(subTrans.getOrigsubmerseqnbr())
						.andTransstatusNotIn(statusList);
				List<Onlinesubtranshist> origOnlineSubtransHists = OnlinesubtranshistDAO
						.selectByExample(histExample);
				if (origOnlineSubtransHists.isEmpty()) {
					throw new PeException(DictErrors.QUERY_ORIG_NOT_EXISTS);
				}
				Onlinesubtranshist origTransHist = origOnlineSubtransHists.get(0);
				origTrans =new Onlinesubtrans();
				origTrans.setCleardate(origTransHist.getCleardate());
				origTrans.setSubtransseqnbr(origTransHist.getSubtransseqnbr());
				origTrans.setTransseqnbr(origTransHist.getTransseqnbr());
				origTrans.setCustcifnbr(origTransHist.getCustcifnbr());
				origTrans.setPayeracctnbr(origTransHist.getPayeracctnbr());
				origTrans.setPayeraccttypcd(origTransHist.getPayeraccttypcd());
				origTrans.setPayercardtypcd(origTransHist.getPayercardtypcd());
				origTrans.setPayeracctname(origTransHist.getPayeracctname());
				origTrans.setPayeracctdeptnbr(origTransHist.getPayeracctdeptnbr());
				origTrans.setProcstep(origTransHist.getProcstep());
				origTrans.setConfirmedcleardate(origTransHist
						.getConfirmedcleardate());
				origTrans.setTranstypcd(origTransHist.getTranstypcd());
				origTrans.setTransstatus(origTransHist.getTransstatus());
				origTrans.setTransamt(origTransHist.getTransamt());
				origTrans.setRefundedamt(origTransHist.getRefundedamt());
				origTrans.setUnrefundamt(origTransHist.getUnrefundamt());
				origTrans.setPaytypcd(origTransHist.getPaytypcd());
				origTrans.setProcstep(origTransHist.getProcstep());
				//积分相关字段
				origTrans.setInteralflag(origTransHist.getInteralflag());
				origTrans.setDeductamt(origTransHist.getDeductamt());
				origTrans.setRealamt(origTransHist.getRealamt());
				origTrans.setClientno(origTransHist.getClientno());
				origTrans.setBranchno(origTransHist.getBranchno());
				origTrans.setPointreacctdeptnbr(origTransHist.getPointreacctdeptnbr());
				origTrans.setRefunddeductamt(origTransHist.getRefunddeductamt());
				origTrans.setUnrefunddeductamt(origTransHist.getUnrefunddeductamt());
				//扫码支付相关字段
				origTrans.setCodetypcd(origTransHist.getCodetypcd());//二维码类型:01-支付宝02-微信
				origTrans.setScancodemode(origTransHist.getScancodemode());//扫码模式:1-主扫2-贝嫂
				origTrans.setQrcodeordernbr(origTransHist.getQrcodeordernbr());//二维码订单号
				origTrans.setCodeurl(origTransHist.getCodeurl());//二维码地址
				
			} else {
				OnlinesubtransExample example = new OnlinesubtransExample();
				example.createCriteria().andMernbrEqualTo(subTrans.getMernbr())
						.andMerseqnbrEqualTo(subTrans.getOrigsubmerseqnbr())
						.andTransstatusNotIn(statusList);
				List<Onlinesubtrans> origOnlineSubtranss = OnlinesubtransDAO
						.selectByExample(example);
				if(origOnlineSubtranss.isEmpty()){
					throw new PeException(DictErrors.QUERY_ORIG_NOT_EXISTS);
				}
				origTrans = origOnlineSubtranss.get(0);
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
		subTrans.setPaytypcd(origTrans.getPaytypcd());
		// 更新退货订单中的原清算日期
		subTrans.setOrigcleardate(origTrans.getCleardate());
		subTrans.setOrigsubtransseqnbr(origTrans.getSubtransseqnbr());
		// 补充订单信息-如果订单信息中的客户号为空,使用原交易的客户号
		if (StringUtil.isStringEmpty(origTrans.getCustcifnbr())) {
			subTrans.setCustcifnbr(origTrans.getCustcifnbr());
		}
		//补充部分退货全部退货标识memo1部分退：3全部退：4
		subTrans.setMemo1(subTrans.getTransamt().compareTo(origTrans.getUnrefundamt()) == 0?
				TransStatus.ALL_WITHDRAW:TransStatus.SUB_WITHDRAW);
		
		//积分相关字段
		subTrans.setInteralflag(origTrans.getInteralflag());
	    subTrans.setPointreacctdeptnbr(origTrans.getPointreacctdeptnbr());
	    subTrans.setRefunddeductamt(origTrans.getRefunddeductamt());
	    subTrans.setUnrefundamt(origTrans.getRefunddeductamt());
	    //扫码支付相关字段
	    subTrans.setCodetypcd(origTrans.getCodetypcd());//二维码类型:01-支付宝02-微信
	    subTrans.setScancodemode(origTrans.getScancodemode());//扫码模式:1-主扫2-贝嫂
	    subTrans.setQrcodeordernbr(origTrans.getQrcodeordernbr());//二维码订单号
	    subTrans.setCodeurl(origTrans.getCodeurl());//二维码地址
		MathContext mc = new MathContext(5, RoundingMode.HALF_UP);
		//包含积分的部分退款 计算积分抵扣金额和客户实际支付金额
	    if(InteralFlag.YES.equals(origTrans.getInteralflag())){
	    	if(StringUtil.isObjectEmpty(origTrans.getUnrefundamt()) ||
	    			StringUtil.isObjectEmpty(origTrans.getUnrefunddeductamt())){
	    		throw new PeException(DictErrors.SQL_UNREFUND_AMT_ERROR);
	    	}
	    	if(subTrans.getTransamt().compareTo(origTrans.getUnrefundamt()) == 0){
	    		subTrans.setDeductamt(origTrans.getUnrefunddeductamt());
	    		subTrans.setRealamt(subTrans.getTransamt().subtract(origTrans.getUnrefunddeductamt()));
	    	}else{
	    		BigDecimal realAmt = subTrans.getTransamt().divide(origTrans.getTransamt(), mc)
		    			.multiply(origTrans.getRealamt())
		    			.setScale(2, RoundingMode.HALF_UP);
		    	BigDecimal deductamt = subTrans.getTransamt().subtract(realAmt);
		    	subTrans.setRealamt(realAmt);
		    	subTrans.setDeductamt(deductamt);
		    }
	    	//如果部分退款金额无法拆分退款金额只贷客户账
	    	if(subTrans.getRealamt().compareTo(BigDecimal.ZERO)==0){
	    		subTrans.setRealamt(subTrans.getTransamt());
	    		subTrans.setDeductamt(BigDecimal.ZERO);
	    	}
	    	//避免抵扣和实际支付退款金额大于原支付金额
	    	if(subTrans.getRealamt().compareTo(origTrans.getUnrefundamt().subtract(origTrans.getUnrefunddeductamt()))>0){
	    		subTrans.setDeductamt(subTrans.getTransamt());
	    		subTrans.setRealamt(BigDecimal.ZERO);
	    	}
	    	
	    }
	    
		// 退货交易的收款人账户、账户类型和账户机构为原支付交易的付款人账户、账户类型和账户机构
		subTrans.setPayeeacctnbr(origTrans.getPayeracctnbr());
		subTrans.setPayeecardtypcd(origTrans.getPayercardtypcd());
		subTrans.setPayeeaccttypcd(origTrans.getPayeraccttypcd());
		subTrans.setPayeeacctkind(origTrans.getPayeracctkind());
		subTrans.setPayeeacctname(origTrans.getPayeracctname());
		subTrans.setPayeeacctdeptnbr(origTrans.getPayeracctdeptnbr());
		// 原支付订单的交易处理步骤:0－未对账 1－对账成功 2－对账失败 3－清算成功 4－清算失败 5－结算成功 6－结算失败
		// 原交易已经结算、原交易确认支付清算日期非空或冻结支付即可认为从商户账退款
		if (ProcStep.SETTLED.equals(origTrans.getProcstep())
				|| (!StringUtil.isObjectEmpty(origTrans.getConfirmedcleardate()))
				||PayModeCd.HOLD.equals(subMerAcct.getPaymodecd())) {
			subTrans.setPayeracctnbr(subMerAcct.getSettleacctnbr());
			subTrans.setPayeracctname(subMerAcct.getSettleacctname());
			subTrans.setPayeraccttypcd(subMerAcct.getSettleaccttyp());
			subTrans.setPayeracctkind(subMerAcct.getSettleacctkind());
			subTrans.setPayeracctdeptnbr(subMerAcct.getMeropendeptnbr());
			subTrans.setRefundmode(RefundMode.SETT);
		}else {
			Deptacctinfo deptAcct=null;
			// 查询商户所属机构的信息
			try {
				deptAcct = DeptacctinfoDAO.selectByPrimaryKey(
						subMerAcct.getMerdevelopdeptnbr(),
						subMerAcct.getInneracctcfmmode());
			} catch (SQLException e) {
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
			// 非实时结算的交易，从中间账户出账。即商户的归属机构
			subTrans.setPayeracctdeptnbr(deptAcct.getDeptnbr());
			subTrans.setPayeracctnbr(deptAcct.getAcctnbr());
			subTrans.setPayeracctname(deptAcct.getAcctname());
			subTrans.setPayeraccttypcd(AcctTypCd.INNER);
			subTrans.setPayeracctkind(deptAcct.getAcctkindcd());
			subTrans.setRefundmode(RefundMode.INNER);
		}
		transData.setPayeracctdeptnbr(subTrans.getPayeracctdeptnbr());
		transData.setPayeracctnbr(subTrans.getPayeracctnbr());
		transData.setPayeracctname(subTrans.getPayeracctname());
		transData.setPayeraccttypcd(subTrans.getPayeraccttypcd());
		transData.setPayeracctkind(subTrans.getPayeracctkind());
		transData.setRefundmode(subTrans.getRefundmode());
		transData.setPayModeCd(subMerAcct.getPaymodecd());
		
		//积分相关字段
		transData.setDeductamt(subTrans.getDeductamt());
		transData.setRealamt(subTrans.getRealamt());
		//为下一步退货交易提供原子交易明细信息
		transData.setOrigSubTrans(origTrans);
	}

}
