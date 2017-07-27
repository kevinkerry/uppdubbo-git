package com.csii.upp.payment.action.start;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.constant.CheckStatus;
import com.csii.upp.constant.CodeTypCd;
import com.csii.upp.constant.CurrencyCode;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.InteralFlag;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.ProcStatus;
import com.csii.upp.constant.ProcStep;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.generate.EtranscodeDAO;
import com.csii.upp.dao.generate.OnlinesubtransDAO;
import com.csii.upp.dao.generate.OnlinetransDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.extend.SubTransData;
import com.csii.upp.dto.generate.Etranscode;
import com.csii.upp.dto.generate.Onlinesubtrans;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 插入子交易明细和总交易明细基类
 * 
 * @author xujin
 * 
 */
public abstract class BaseOnlineTransAction extends PaymentOnlineAction {

	protected abstract void prepareOnlineTrans(Context ctx,InputPaymentData inputData) throws PeException;

	protected abstract void prepareOnlineSubTrans(InputPaymentData transData,SubTransData subTrans)throws PeException;

	@Override
	public void execute(Context ctx) throws PeException {
		if(log.isInfoEnabled()){
			log.info(new StringBuilder().append("支付方式[")
			.append(ctx.getData(Dict.PAY_TYP_CD)).append("]")
		    .append("商户号[").append(ctx.getData(Dict.MER_NBR)).append("]")
			.append("商户流水[").append(ctx.getData(Dict.MER_SEQ_NBR)).append("]")
			.append("商户时间[").append(ctx.getData(Dict.MER_TRANS_DATE_TIME)).append("]")
			.append("交易代码[").append(ctx.getData(Dict.TRANS_CODE))
			.append("] 保存订单交易明细流水!").toString());
		}	
		// 准备总交易明细和子交易明细数据
		final InputPaymentData inputData = this.prepareTransData(ctx);
		// 插入子交易明细和总交易明细
		this.getTransactionTemplate().execute(new TransactionCallback() {//事务处理 保证总交易明细与子交易明细插入都成功或都失败
			public Object doInTransaction(TransactionStatus arg0) {
				try {
					OnlinetransDAO.insert(inputData);
					List<SubTransData> onlineSubTransList = inputData
							.getOnlineSubTransList();
					if (onlineSubTransList != null
							&& !onlineSubTransList.isEmpty()) {
						for (Onlinesubtrans subtrans : onlineSubTransList) {
					    	if(StringUtil.isStringEmpty(subtrans.getSubtransseqnbr())){
					    		subtrans.setSubtransseqnbr(IDGenerateFactory.generateRtxnNbr());
					    	}
							OnlinesubtransDAO.insert(subtrans);
						}
					}
				} catch (Exception e) {
					throw new PeRuntimeException(e);
				}
				return null;
			}
		});
		ctx.setData(Dict.INPUT_PAYMENT_DATA, inputData);
	}
	 /**
	  * 
	  * 功能：交易前数据准备
	  * 作者：
	  * @mender liru
	  * @param  Context ctx(上下文参数)
	  * @return: InputPaymentData
	  * @remack (认为有必要的描述)
	  */
	private InputPaymentData prepareTransData(Context ctx) throws PeException {
		ctx.setData(Dict.POST_DATE, this.getPostDate());
		String transCode = ctx.getTransactionId();//交易码
		try {
			Etranscode transCodeInfo = EtranscodeDAO.selectByPrimaryKey(transCode);//查询交易码表中是否有此交易
			if (transCodeInfo == null) {
				throw new PeException(DictErrors.TRANS_CODE_ERROR);
			}
			ctx.setData(Dict.TRANS_TYP_CD, transCodeInfo.getTranstypcd());
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		InputPaymentData transData = new InputPaymentData(ctx.getDataMap());
		// 交易代码
		transData.setTranscode(transCode);
		transData.setCheckstatus(CheckStatus.Init);
		transData.setTransstatus(TransStatus.INIT);
		transData.setTransseqnbr(IDGenerateFactory.generateRtxnNbr());
		transData.setTransnbr(transData.getTransseqnbr());
		this.prepareOnlineTrans(ctx,transData);
		return transData;
	}

	@SuppressWarnings("rawtypes")
	protected SubTransData makeSubTrans(Map subMap, InputPaymentData transData)
			throws PeException {
		SubTransData subTrans = new SubTransData();
		subTrans.setElecportflag(StringUtil.parseObjectToString(subMap
				.get(Dict.SUB_MER_IMPORT)));
		subTrans.setMernbr(StringUtil.parseObjectToString(subMap
				.get(Dict.SUB_MERCHANT_ID)));
		if (StringUtil.isStringEmpty(subTrans.getMernbr())) {
			throw new PeException(DictErrors.VALUE_NOT_EMPTY,
					new Object[] { Dict.MER_NBR });
		}
		subTrans.setMemo2(transData.getMemo2());
		// 子交易商户流水号
		subTrans.setMerseqnbr(StringUtil.parseObjectToString(subMap
				.get(Dict.SUB_MER_SEQ_NO)));
		//二维码扫码字段 transamt存打折后金额
		if(PayTypCd.OTHACTSCAN.equals(transData.getPaytypcd())
				||PayTypCd.OTHPASSCAN.equals(transData.getPaytypcd())){
			//二维码类型
			subTrans.setCodetypcd(transData.getCodetypcd());
			//扫码模式
			subTrans.setScancodemode(transData.getScancodemode());
		}
		//支付宝计算打折金额
		if(CodeTypCd.ALIPAY.equals(transData.getCodetypcd())){
			if(null ==subMap.get(Dict.DISCOUNTABLE_AMOUNT)){
				subMap.put(Dict.DISCOUNTABLE_AMOUNT, BigDecimal.ZERO);
			}
			BigDecimal realTransAmt=StringUtil.parseBigDecimal(subMap
					.get(Dict.SUB_TRANS_AMT)).subtract(StringUtil.parseBigDecimal(subMap.get(Dict.DISCOUNTABLE_AMOUNT)));
			//打折金额
			subTrans.setDiscountableamt(StringUtil.parseBigDecimal(subMap.get(Dict.DISCOUNTABLE_AMOUNT)));
			//打折前金额
			subTrans.setUndiscountableamt(StringUtil.parseBigDecimal(subMap
					.get(Dict.SUB_TRANS_AMT)));
			subTrans.setTransamt(realTransAmt);
		}else{
			// 交易金额-前端传送
			subTrans.setTransamt(StringUtil.parseBigDecimal(subMap
					.get(Dict.SUB_TRANS_AMT)));
		}		
		subTrans.setCurrencycd(CurrencyCode.CNY);
		subTrans.setTransamt1(BigDecimal.ZERO);
		// 已退金额
		subTrans.setRefundedamt(BigDecimal.ZERO);
		// 未退货的金额
		subTrans.setUnrefundamt(subTrans.getTransamt());
		subTrans.setTransamt2(subTrans.getTransamt());
		// 手续费金额
		subTrans.setFeeamt(BigDecimal.ZERO);
		subTrans.setCleardate(transData.getCleardate());
		subTrans.setTransdate(transData.getCleardate());
		subTrans.setTranstime(transData.getTranstime());
		subTrans.setTranscode(transData.getTranscode());
		subTrans.setTranstypcd(transData.getTranstypcd());
		//积分标识
		subTrans.setInteralflag(transData.getInteralflag());
		if(InteralFlag.YES.equals(subTrans.getInteralflag())){
			//积分抵扣金额
			if(null ==subMap.get(Dict.SUB_DEDUCT_AMT)){
				subMap.put(Dict.SUB_DEDUCT_AMT, BigDecimal.ZERO);
			}
			subTrans.setDeductamt(StringUtil.parseBigDecimal(subMap.get(Dict.SUB_DEDUCT_AMT)));
			subTrans.setUnrefunddeductamt(StringUtil.parseBigDecimal(subMap.get(Dict.SUB_DEDUCT_AMT)));
			//实际扣除金额
			subTrans.setRealamt(subTrans.getTransamt().subtract(subTrans.getDeductamt()));
			subTrans.setClientno(transData.getClientno());
			subTrans.setBranchno(transData.getBranchno());
		}
		
		subTrans.setOrigsubmerseqnbr(StringUtil.parseObjectToString(subMap
				.get(Dict.ORIG_SUB_MER_SEQ_NO)));
		if (!StringUtil.isObjectEmpty(subMap.get(Dict.SUB_MER_DATE_TIME))) {
			subTrans.setMertransdatetime(DateUtil.DateTimeFormat_To_Date(subMap
					.get(Dict.SUB_MER_DATE_TIME)));
			subTrans.setMertransdate(DateUtil.DateFormat_To_Date(DateUtil
					.Date_To_DateTimeFormat(subTrans.getMertransdatetime(),
							DateFormatCode.DATE_FORMATTER1)));
		} else {
			subTrans.setMertransdate(transData.getMertransdate());
			subTrans.setMertransdatetime(transData.getMertransdatetime());
		}

		// 原二级商户交易日期-前端传送,如果字段OrigSubMerDate有值，则取这个值，否则，取OrigMerDate
		if (!StringUtil.isObjectEmpty(subMap.get(Dict.ORIG_SUB_MER_DATE))) {
			subTrans.setOrigsubmertransdate(DateUtil.DateFormat_To_Date(subMap
					.get(Dict.ORIG_SUB_MER_DATE)));
		} else {
			subTrans.setOrigsubmertransdate(transData.getOrigmerdate());
		}
		subTrans.setSystemcode(transData.getSystemcode());
		subTrans.setDepartmentnbr(transData.getDepartmentnbr());
		// 交易处理状态默认为交易处理中
		subTrans.setProcstatus(ProcStatus.PENDING);
		subTrans.setTransstatus(TransStatus.INIT);
		subTrans.setProcstep(ProcStep.Init);
		subTrans.setPaytypcd(transData.getPaytypcd());
		subTrans.setSubtransseqnbr(IDGenerateFactory.generateRtxnNbr());
		subTrans.setTransseqnbr(transData.getTransseqnbr());
		// 客户号-前端传送
		subTrans.setCustcifnbr(transData.getCustcifnbr());
		// 付款账户-前端传送
		subTrans.setPayeracctnbr(transData.getPayeracctnbr());
		// 付款账户名称-前端传送
		subTrans.setPayeracctname(transData.getPayeracctname());
		// 付款账户类型-前端传送
		subTrans.setPayercardtypcd(transData.getPayercardtypcd());
		subTrans.setPayeraccttypcd(transData.getPayeraccttypcd());
		subTrans.setPayeracctkind(transData.getPayeracctkind());
		// 付款账户机构
		subTrans.setPayeracctdeptnbr(transData.getPayeracctdeptnbr());
		//根据具体交易设置其它值
		this.prepareOnlineSubTrans(transData, subTrans);
		return subTrans;
	}
	
	
    /**
     * 
     * 功能：商户交易流水号不能为空、交易金额大于0校验
     * 作者：
     * 修改者：liru
     * @param: String merSeqNbr(商户交易流水号)
     * @param: BigDecimal transAmt（交易金额）
     * @return: void
     */
	protected void checkCommon(String merSeqNbr, BigDecimal transAmt)
			throws PeException {
		if (StringUtil.isObjectEmpty(merSeqNbr)) {//商户交易流水号不能为空
			throw new PeException(DictErrors.VALUE_NOT_EMPTY,
					new Object[] { Dict.MER_SEQ_NBR });
		}
        //交易金额必须大于0
		if (StringUtil.isObjectEmpty(transAmt)|| (transAmt.compareTo(BigDecimal.ZERO) <= 0)) {
			throw new PeException(DictErrors.TRANS_AMT_MUST_PLUS);
		}
	}
	/**
	 *积分抵扣金额计算
	 * 
	 * @param discountAmt
	 * @param transAmt
	 * @throws PeException
	 */
	protected void checkPointAmt(InputPaymentData inputData,BigDecimal deductamt, BigDecimal transAmt)
			throws PeException {
		//实际支付的金额
		BigDecimal realAmt=transAmt.subtract(deductamt);
		if (StringUtil.isObjectEmpty(realAmt)
				|| (realAmt.compareTo(BigDecimal.ZERO) <= 0)) {
			throw new PeException(DictErrors.TRANS_AMT_MUST_PLUS);
		}
		//抵扣的金额
		if (StringUtil.isObjectEmpty(deductamt)
				|| (deductamt.compareTo(BigDecimal.ZERO) <= 0)) {
			throw new PeException(DictErrors.TRANS_AMT_MUST_PLUS);
		}
		inputData.setRealamt(realAmt);
		inputData.setUnrefunddeductamt(deductamt);
	}	
	
	/**
	 * 验证和交易金额和抵扣金额
	 * 
	 * @param discountAmt
	 * @param transAmt
	 * @throws PeException
	 */
	protected void checkdiscountAmt(InputPaymentData inputData,BigDecimal discountAmt, BigDecimal transAmt)
			throws PeException {
		//校验抵扣金额大于0小于交易金额,默认为0
		if(StringUtil.isObjectEmpty(discountAmt)){
			inputData.setDiscountableamt(BigDecimal.ZERO);
			inputData.setUndiscountableamt(transAmt);
		}else{
			if((discountAmt.compareTo(BigDecimal.ZERO) <= 0)
					|| (transAmt.compareTo(discountAmt) <= 0)){
				throw new PeException(DictErrors.TRANS_AMT_MUST_PLUS);
			}else{
				BigDecimal realTransAmt=inputData.getTransamt().subtract(inputData.getDiscountableamt());
				//原订单金额
				inputData.setUndiscountableamt(inputData.getTransamt());
				//打折后金额
				inputData.setTransamt(realTransAmt);
				//未退金额 
				inputData.setUnrefundamt(inputData.getTransamt());
				inputData.setTransamt4(inputData.getTransamt());
			}
		}
	}

}
