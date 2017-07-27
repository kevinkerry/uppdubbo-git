package com.csii.upp.batch.appl.paym;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.batch.appl.base.BaseAppl;
import com.csii.upp.batch.event.handler.RunQueEvent;
import com.csii.upp.constant.PayModeCd;
import com.csii.upp.constant.SendStatus;
import com.csii.upp.constant.SettleStatus;
import com.csii.upp.constant.StandBookTypCD;
import com.csii.upp.dao.extend.BatchfeeprofitdaysettleExtendDAO;
import com.csii.upp.dao.extend.BatchmerdaysettleExtendDAO;
import com.csii.upp.dao.extend.MeracctinfoExtendDAO;
import com.csii.upp.dao.generate.BatchfeeprofitdaysettleDAO;
import com.csii.upp.dao.generate.BatchfeeprofitsettleDAO;
import com.csii.upp.dao.generate.BatchmerdaysettleDAO;
import com.csii.upp.dao.generate.BatchmersettleDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.generate.Batchfeeprofitdaysettle;
import com.csii.upp.dto.generate.BatchfeeprofitdaysettleExample;
import com.csii.upp.dto.generate.Batchfeeprofitsettle;
import com.csii.upp.dto.generate.Batchmerdaysettle;
import com.csii.upp.dto.generate.BatchmerdaysettleExample;
import com.csii.upp.dto.generate.Batchmersettle;
import com.csii.upp.dto.generate.Deptacctinfo;
import com.csii.upp.dto.generate.Meracctinfo;
import com.csii.upp.supportor.DefaultSupportor;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

public class MerchantSettlementAppl extends BaseAppl {
	/**
	 * 汇总结算
	 * 
	 */

	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps)
			throws Exception {
		Date checkDate = getCheckDate(argMaps);
		//商户结算
		merchantSettlement(checkDate);
		//分润结算
		profitSettment(checkDate);
		//调起下个队列
		RunQueEvent event=new RunQueEvent();
		event.setQueNbr(14L);
		// 异步线程处理
		DefaultSupportor.getEventManager().doService(event);
	}

	/**
	 * 商户结算
	 */
	private void merchantSettlement(Date checkDate) {
		// 结算商户获取
		List<Map<String, Object>> list = MeracctinfoExtendDAO.getMerAcctInfoExtend(checkDate,SettleStatus.UNSETTLE);
		for(Map map : list) {
			Meracctinfo mer = new Meracctinfo();
			mer.setMernbr(StringUtil.parseObjectToString(map.get(Dict.MER_NBR)));
			mer.setSettleacctnbr(StringUtil.parseObjectToString(map.get(Dict.SETTLE_ACCT_NBR)));
			mer.setSettleaccttyp(StringUtil.parseObjectToString(map.get(Dict.SETTLE_ACCT_TYP))); 
			mer.setSettleacctkind(StringUtil.parseObjectToString(map.get(Dict.SETTLE_ACCT_KIND)));
			mer.setPaymodecd(StringUtil.parseObjectToString(map.get(Dict.PAY_MODE_CD)));
			mer.setFeeacctnbr(StringUtil.parseObjectToString(map.get("MerFeeAcctNbr")));
			mer.setFeeaccttyp(StringUtil.parseObjectToString(map.get("MerFeeAcctTyp")));
			mer.setFeeacctkind(StringUtil.parseObjectToString(map.get("MerFeeAcctKind")));
			Deptacctinfo dept = new Deptacctinfo();
			dept.setAcctnbr(StringUtil.parseObjectToString(map.get(Dict.ACCT_NBR)));
			dept.setAcctkindcd(StringUtil.parseObjectToString(map.get(Dict.ACCT_KIND_CD)));
			dept.setAccttypcd(StringUtil.parseObjectToString(map.get(Dict.ACCT_TYP_CD)));
			dept.setFeeacctnbr(StringUtil.parseObjectToString(map.get(Dict.FEE_ACCT_NBR)));
			dept.setFeeacctkind(StringUtil.parseObjectToString(map.get(Dict.FEE_ACCT_KIND)));
			dept.setFeeaccttpycd(StringUtil.parseObjectToString(map.get(Dict.FEE_ACCT_TYP_CD)));
			if(mer.getPaymodecd().equals(PayModeCd.HOLD)){
				//如果支付模式为冻结支付，则不结算
				continue;
			}else{
				// 结算
				settlement(mer, dept, checkDate);
			}

		}
	}

	private void settlement(final Meracctinfo acct, final Deptacctinfo deptAcct, final Date checkDate) {
		// 获取未结算数据
		final List<Map> settlelist = BatchmerdaysettleExtendDAO.queryMerchantSettForExt(acct.getMernbr(), checkDate,SettleStatus.UNSETTLE);
		if(settlelist != null && !settlelist.isEmpty()){
			getTransactionTemplate().execute(new TransactionCallback(){
				public Object doInTransaction(TransactionStatus arg0) {
					//结算商户的本金扎差总金额
					BigDecimal totalAmount = new BigDecimal(0);
					//结算手续费账户扎差总和
					BigDecimal feeAmount = new BigDecimal(0);
					//包月手续费总额
					BigDecimal feeMonAmt = new BigDecimal(0);
					//已结算退货手续费
					BigDecimal feeWithdrawAmt = new BigDecimal(0);
					for(Map daysettle: settlelist){
						//资金格式化
						BigDecimal totalamount_tmp = ((BigDecimal)daysettle.get("TransAmount")).setScale(2,BigDecimal.ROUND_HALF_UP);
						//支付和退货扎差，在清分时已处理退货金额为负值
						if(StandBookTypCD.MERCHANT_TRANS_PAY.equals(daysettle.get("MerchantTransType")) ||
								StandBookTypCD.MERCHANT_TRANS_WITHDRAW.equals(daysettle.get("MerchantTransType"))){
							totalAmount = totalAmount.add(totalamount_tmp);
							continue;
						}
						if(StandBookTypCD.MERCHANT_TRANS_BALANCE_FEE_RCV.equals(daysettle.get("MerchantTransType")) ||
								StandBookTypCD.MERCHANT_TRANS_BALANCE_FEE_RETURN.equals(daysettle.get("MerchantTransType"))){
							feeAmount = feeAmount.add(totalamount_tmp);
							continue;
						}
						if (StandBookTypCD.MERCHANT_TRANS_FEE_RCV.equals(daysettle.get("MerchantTransType"))) {
							feeMonAmt = feeMonAmt.add(totalamount_tmp);
							continue;
						}
						//modified by wangtao 20161215 增加对已结算退货手续费费的处理
						if (StandBookTypCD.MERCHANT_TRANS_FEE_RETURN.equals(daysettle.get("MerchantTransType"))) {
							feeWithdrawAmt = feeWithdrawAmt.add(totalamount_tmp);
							continue;
						}						
					}
					
					Map<String, Object> data = new HashMap<String, Object>();					
					data.put("CLEARDATE", checkDate);
					data.put("PAYERACCTNBR", deptAcct.getAcctnbr());
					data.put("PAYERACCTKIND", deptAcct.getAcctkindcd());
					data.put("PAYERACCTTYPCD", deptAcct.getAccttypcd());
					data.put("PAYEEACCTNBR", acct.getSettleacctnbr());
					data.put("PAYEEACCTTYPCD", acct.getSettleaccttyp());
					data.put("PAYEEACCTKIND", acct.getSettleacctkind());
					data.put("TRANSAMT", totalAmount);
					data.put("STANDBOOKTYPCD", StandBookTypCD.MERCHANT_TRANS_PAY); // 支付结算（银行->商户）					
					processMerchantSettExt(data, acct);
					
					// 手续费（不含包月）
					if (BigDecimal.ZERO.compareTo(feeAmount) < 0) {
						Map<String, Object> feeData = new HashMap<String, Object>();
						feeData.put("CLEARDATE", checkDate);
						feeData.put("PAYERACCTNBR", deptAcct.getAcctnbr());
						feeData.put("PAYERACCTKIND", deptAcct.getAcctkindcd());
						feeData.put("PAYERACCTTYPCD", deptAcct.getAccttypcd());
						feeData.put("PAYEEACCTNBR", deptAcct.getFeeacctnbr());
						feeData.put("PAYEEACCTKIND", deptAcct.getFeeacctkind());
						feeData.put("PAYEEACCTTYPCD", deptAcct.getFeeaccttpycd());
						feeData.put("TRANSAMT", feeAmount);
						feeData.put("STANDBOOKTYPCD", StandBookTypCD.MERCHANT_TRANS_BALANCE_FEE_RCV); // 手续费收取（内部账户->手续费账户）
						processMerchantSettExt(feeData, acct);
					}
					//包月手续费
					if (BigDecimal.ZERO.compareTo(feeMonAmt) < 0) {
						Map<String, Object> feeData = new HashMap<String, Object>();
						feeData.put("CLEARDATE", checkDate);
						feeData.put("PAYERACCTNBR", acct.getFeeacctnbr());
						feeData.put("PAYERACCTKIND", acct.getFeeacctkind());
						feeData.put("PAYERACCTTYPCD", acct.getFeeaccttyp());
						feeData.put("PAYEEACCTNBR", deptAcct.getFeeacctnbr());
						feeData.put("PAYEEACCTKIND", deptAcct.getFeeacctkind());
						feeData.put("PAYEEACCTTYPCD", deptAcct.getFeeaccttpycd());
						feeData.put("TRANSAMT", feeMonAmt);
						feeData.put("STANDBOOKTYPCD", StandBookTypCD.MERCHANT_TRANS_FEE_RCV); // 包月手续费
						processMerchantSettExt(feeData, acct);
					}
					//已结算退货手续费 modified by wangtao 20161215
					if (BigDecimal.ZERO.compareTo(feeWithdrawAmt) < 0) {
						Map<String, Object> feeData = new HashMap<String, Object>();
						feeData.put("CLEARDATE", checkDate);
						feeData.put("PAYERACCTNBR", deptAcct.getFeeacctnbr());
						feeData.put("PAYERACCTKIND", deptAcct.getFeeacctkind());
						feeData.put("PAYERACCTTYPCD", deptAcct.getFeeaccttpycd());
						feeData.put("PAYEEACCTNBR",acct.getFeeacctnbr() );
						feeData.put("PAYEEACCTKIND", acct.getFeeacctkind());
						feeData.put("PAYEEACCTTYPCD", acct.getFeeaccttyp());
						feeData.put("TRANSAMT", feeWithdrawAmt);
						feeData.put("STANDBOOKTYPCD", StandBookTypCD.MERCHANT_TRANS_FEE_RETURN); // 已结算退货手续费
						processMerchantSettExt(feeData, acct);
					}					
					
					Batchmerdaysettle record = new Batchmerdaysettle();
					record.setSettlestatus(SettleStatus.SETTLED);
					BatchmerdaysettleExample example = new BatchmerdaysettleExample();
					example.createCriteria().andCleardateEqualTo(checkDate).andMernbrEqualTo(acct.getMernbr())
							.andSettlestatusEqualTo(SettleStatus.UNSETTLE);
					try {
						BatchmerdaysettleDAO.updateByExampleSelective(record, example);
					} catch (SQLException e) {
						throw new PeRuntimeException(e);
					}
					return null;				
				}				
			});
		}

	}
	
	public void processMerchantSettExt(Map data, Meracctinfo merchant) {
		Batchmersettle record = new Batchmersettle();
		try {
			// 商户号
			record.setMernbr(merchant.getMernbr());
			// 商户结算交易流水
			record.setSettleseqnbr(IDGenerateFactory.generateSeqId());
			// 交易日期
			record.setTransdate(getPostDate());
			// 交易时间戳
			record.setTranstime(DateUtil.getCurrentDateTime());
			// 发送标识
			record.setSendstatus(SendStatus.UNSEND);
			record.setCleardate(DateUtil.DateFormat_To_Date(data.get("CLEARDATE")));
			record.setPayeracctnbr(data.get("PAYERACCTNBR").toString());
			record.setPayeracctkind(data.get("PAYERACCTKIND").toString());
			record.setPayeraccttypcd(data.get("PAYERACCTTYPCD").toString()); 
			record.setPayeeacctnbr(data.get("PAYEEACCTNBR").toString()); 
			record.setPayeeaccttypcd(data.get("PAYEEACCTTYPCD").toString());
			record.setPayeeacctkind(data.get("PAYEEACCTKIND").toString());
			record.setTransamt(StringUtil.parseBigDecimal(data.get("TRANSAMT")));
			record.setStandbooktypcd(data.get("STANDBOOKTYPCD").toString());
			BatchmersettleDAO.insertSelective(record);
		} catch (Exception e) {
			throw new PeRuntimeException(e);
		}
	}
	
	private void profitSettle(final Meracctinfo acct,final Date checkDate){
		final List<Map> settlelist = BatchfeeprofitdaysettleExtendDAO.queryMerchantSettForExt(acct.getMernbr(), checkDate,SettleStatus.UNSETTLE);
		if(settlelist != null && !settlelist.isEmpty()){
			getTransactionTemplate().execute(new TransactionCallback(){
				public Object doInTransaction(TransactionStatus arg0) {
					for(Map daysettle: settlelist){
						BigDecimal totalAmount = new BigDecimal(0);
						Map<String, Object> data = new HashMap<String, Object>();					
						data.put("CLEARDATE", checkDate);
						data.put("PAYERACCTNBR",daysettle.get("PayAcctNo"));
						data.put("PAYERACCTKIND", daysettle.get("PayAcctKind"));
						data.put("PAYERACCTTYPCD", daysettle.get("PayAcctType"));
						data.put("PAYEEACCTNBR", daysettle.get("RcvAcctNo"));
						data.put("PAYEEACCTTYPCD", daysettle.get("RcvAcctType"));
						data.put("PAYEEACCTKIND", daysettle.get("RcvAcctKind"));
						data.put("TRANSAMT", daysettle.get("TransAmount"));

						Batchfeeprofitsettle record = new Batchfeeprofitsettle();
						try {
							// 商户号
							record.setMernbr(acct.getMernbr());
							// 商户结算交易流水
							record.setSettleseqnbr(IDGenerateFactory.generateSeqId());
							// 交易日期
							record.setTransdate(getPostDate());
							// 交易时间戳
							record.setTranstime(DateUtil.getCurrentDateTime());
							// 发送标识
							record.setSendstatus(SendStatus.UNSEND);
							record.setCleardate(DateUtil.DateFormat_To_Date(data.get("CLEARDATE")));
							record.setPayeracctnbr(data.get("PAYERACCTNBR").toString());
							record.setPayeracctkind(data.get("PAYERACCTKIND").toString());
							record.setPayeraccttypcd(data.get("PAYERACCTTYPCD").toString()); 
							record.setPayeeacctnbr(data.get("PAYEEACCTNBR").toString()); 
							record.setPayeeaccttypcd(data.get("PAYEEACCTTYPCD").toString());
							record.setPayeeacctkind(data.get("PAYEEACCTKIND").toString());
							record.setTransamt(StringUtil.parseBigDecimal(data.get("TRANSAMT")));
							record.setDepartmentnbr("1");
							BatchfeeprofitsettleDAO.insertSelective(record);
						} catch (Exception e) {
							throw new PeRuntimeException(e);
						}	
						
						
						Batchfeeprofitdaysettle record1 = new Batchfeeprofitdaysettle();
						record1.setSettlestatus(SettleStatus.SETTLED);
						BatchfeeprofitdaysettleExample example = new BatchfeeprofitdaysettleExample();
						example.createCriteria().andCleardateEqualTo(checkDate).andMernbrEqualTo(acct.getMernbr())
								.andSettlestatusEqualTo(SettleStatus.UNSETTLE);
						try {
							BatchfeeprofitdaysettleDAO.updateByExampleSelective(record1, example);
						} catch (SQLException e) {
							throw new PeRuntimeException(e);
						}
											
					}
					return null;	
				}				
			});
					
		}		
		
	}
	
	public void profitSettment(Date checkDate){

		// 结算商户获取
		List<Map<String, Object>> list = MeracctinfoExtendDAO.getMerAcctInfoExtend1(checkDate,SettleStatus.UNSETTLE);
		for(Map map : list) {
			Meracctinfo mer = new Meracctinfo();
			mer.setMernbr(StringUtil.parseObjectToString(map.get(Dict.MER_NBR)));
			mer.setSettleacctnbr(StringUtil.parseObjectToString(map.get(Dict.SETTLE_ACCT_NBR)));
			mer.setSettleaccttyp(StringUtil.parseObjectToString(map.get(Dict.SETTLE_ACCT_TYP))); 
			mer.setSettleacctkind(StringUtil.parseObjectToString(map.get(Dict.SETTLE_ACCT_KIND)));
			mer.setPaymodecd(StringUtil.parseObjectToString(map.get(Dict.PAY_MODE_CD)));
			mer.setFeeacctnbr(StringUtil.parseObjectToString(map.get("MerFeeAcctNbr")));
			mer.setFeeaccttyp(StringUtil.parseObjectToString(map.get("MerFeeAcctTyp")));
			mer.setFeeacctkind(StringUtil.parseObjectToString(map.get("MerFeeAcctKind")));
			Deptacctinfo dept = new Deptacctinfo();
			dept.setAcctnbr(StringUtil.parseObjectToString(map.get(Dict.ACCT_NBR)));
			dept.setAcctkindcd(StringUtil.parseObjectToString(map.get(Dict.ACCT_KIND_CD)));
			dept.setAccttypcd(StringUtil.parseObjectToString(map.get(Dict.ACCT_TYP_CD)));
			dept.setFeeacctnbr(StringUtil.parseObjectToString(map.get(Dict.FEE_ACCT_NBR)));
			dept.setFeeacctkind(StringUtil.parseObjectToString(map.get(Dict.FEE_ACCT_KIND)));
			dept.setFeeaccttpycd(StringUtil.parseObjectToString(map.get(Dict.FEE_ACCT_TYP_CD)));
			if(mer.getPaymodecd().equals(PayModeCd.HOLD)){
				//如果支付模式为冻结支付，则不结算
				continue;
			}else{
				// 结算
				profitSettle(mer,checkDate);
			}

		}
			
	}
}
