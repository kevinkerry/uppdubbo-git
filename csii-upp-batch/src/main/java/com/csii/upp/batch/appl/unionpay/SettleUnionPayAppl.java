package com.csii.upp.batch.appl.unionpay;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.csii.pe.core.PeException;
import com.csii.upp.batch.appl.base.BaseAppl;
import com.csii.upp.constant.InnerRtxnTyp;
import com.csii.upp.constant.SettleTyp;
import com.csii.upp.dao.extend.DownsysfundtransExtendDAO;
import com.csii.upp.dao.generate.FundchannelsettleDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.generate.Fundchannelsettle;
import com.csii.upp.dto.generate.FundchannelsettleExample;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 银联结算
 * 
 * @author lichao
 *
 */
public class SettleUnionPayAppl extends BaseAppl {

	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps) throws PeException {
		final String fundchannelcode = StringUtil.parseObjectToString(argMaps.get(Dict.FCNM));
		final Date checkDate = this.getCheckDate(argMaps);
		
		this.getTransactionTemplate().execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus arg0) {
				try {
					Map<String, Object> queryMap = new HashMap<String, Object>();
					queryMap.put("cleardate", checkDate);
					queryMap.put(Dict.FUNDCHANNELCODE, fundchannelcode);
					
					//银联本日清算
					queryMap.put(Dict.TRANSCODE, "01");// 支付类交易
					List<Map<String, Object>> downPayTotal = DownsysfundtransExtendDAO.getTotalTransAmtAndFeeAmt(queryMap);
					queryMap.put(Dict.TRANSCODE, "04");// 消费退货类交易
					List<Map<String, Object>> downRefundTotal = DownsysfundtransExtendDAO.getTotalTransAmtAndFeeAmt(queryMap);
					queryMap.put(Dict.TRANSCODE, "31");// 消费撤销类交易
					List<Map<String, Object>> downCancleTotal = DownsysfundtransExtendDAO.getTotalTransAmtAndFeeAmt(queryMap);
					BigDecimal totalPayTransAmt = new BigDecimal(0);
					BigDecimal totalPayRefoundAmt = new BigDecimal(0);
					BigDecimal totalPayCancleAmt = new BigDecimal(0);
					BigDecimal totalFeeTransAmt = new BigDecimal(0);
					BigDecimal totalFeeRefoundAmt = new BigDecimal(0);
					BigDecimal totalFeeCancleAmt = new BigDecimal(0);
					if (downPayTotal != null && downPayTotal.size() > 0
							&& downPayTotal.get(0).get("totalTransAmt") != null) {
						totalPayTransAmt = new BigDecimal(
								StringUtil.parseObjectToString(downPayTotal.get(0).get("totalTransAmt")));
						totalFeeTransAmt = new BigDecimal(
								StringUtil.parseObjectToString(downPayTotal.get(0).get("totalFeeAmt")));
						log.info("(银联下游支付总额)totalPayTransAmt = " + totalPayTransAmt +", (银联下游支付手续费总额)totalFeeTransAmt = " + totalFeeTransAmt);
					}
					if (downRefundTotal != null && downRefundTotal.size() > 0
							&& downRefundTotal.get(0).get("totalTransAmt") != null) {
						totalPayRefoundAmt = new BigDecimal(
								StringUtil.parseObjectToString(downRefundTotal.get(0).get("totalTransAmt")));
						totalFeeRefoundAmt = new BigDecimal(
								StringUtil.parseObjectToString(downRefundTotal.get(0).get("totalFeeAmt")));
						log.info("(银联下游退货总额)totalPayRefoundAmt = " + totalPayRefoundAmt +", (银联下游退货返还手续费总额)totalFeeRefoundAmt = " + totalFeeRefoundAmt);
					}
					if (downCancleTotal != null && downCancleTotal.size() > 0
							&& downCancleTotal.get(0).get("totalTransAmt") != null) {
						totalPayCancleAmt = new BigDecimal(
								StringUtil.parseObjectToString(downCancleTotal.get(0).get("totalTransAmt")));
						totalFeeCancleAmt = new BigDecimal(
								StringUtil.parseObjectToString(downCancleTotal.get(0).get("totalFeeAmt")));
						log.info("(银联下游撤销总额)totalPayCancleAmt = " + totalPayCancleAmt +", (银联下游撤销返还手续费总额)totalFeeCancleAmt = " + totalFeeCancleAmt);
					}
					BigDecimal todayUnionPayFeeAmt = totalFeeTransAmt.subtract(totalFeeRefoundAmt)
							.subtract(totalFeeCancleAmt);
					BigDecimal todayUnionPayClearAmt = totalPayTransAmt.subtract(totalPayRefoundAmt)
							.subtract(totalPayCancleAmt).subtract(todayUnionPayFeeAmt);
					log.info("(银联下游支付手续费总额)todayUnionPayFeeAmt = " + todayUnionPayFeeAmt +", (银联银联本日清算总额)todayUnionPayClearAmt = " + todayUnionPayClearAmt);

					//核心本日清算
					queryMap = new HashMap<String, Object>();
					queryMap.put("cleardate", checkDate);
					queryMap.put("transcode", InnerRtxnTyp.CoreInnerTransfer);
					List<Map<String, Object>> corePayMap = DownsysfundtransExtendDAO
							.getCoreTodayClearAmt(queryMap);
					queryMap.put("transcode", InnerRtxnTyp.CoreRefoundTrans4UnionPay);
					List<Map<String, Object>> coreRefound4UnionPayMap = DownsysfundtransExtendDAO
							.getCoreTodayClearAmt(queryMap);
					BigDecimal corePayAmt = new BigDecimal(0);
					if (corePayMap != null && corePayMap.size() > 0 && corePayMap.get(0).get("coretodayclearamt") != null) {
						corePayAmt = new BigDecimal(StringUtil.parseObjectToString(corePayMap.get(0).get("coretodayclearamt")));
					}
					BigDecimal coreRefound4UnionPayAmt = new BigDecimal(0);
					if (coreRefound4UnionPayMap != null && coreRefound4UnionPayMap.size() > 0 && coreRefound4UnionPayMap.get(0).get("coretodayclearamt") != null) {
						coreRefound4UnionPayAmt = new BigDecimal(StringUtil.parseObjectToString(coreRefound4UnionPayMap.get(0).get("coretodayclearamt")));
					}
					log.info("(核心本日支付发生总额)corePayAmt = " + corePayAmt +", (核心本日退货发生总额)coreRefound4UnionPayAmt = " + coreRefound4UnionPayAmt);
					BigDecimal todatyCoreClearAmt = corePayAmt.subtract(coreRefound4UnionPayAmt).subtract(todayUnionPayFeeAmt);
					
					BigDecimal preNotClearAmt = new BigDecimal(0);
					FundchannelsettleExample example = new FundchannelsettleExample();
					example.createCriteria().andCleardateEqualTo(DateUtil.addDate(checkDate, -1)).andSettletypEqualTo(SettleTyp.NotClear);
					List<Fundchannelsettle> fundchannelsettles = FundchannelsettleDAO.selectByExample(example);
					if(fundchannelsettles != null && fundchannelsettles.size() > 0){
						preNotClearAmt  = fundchannelsettles.get(0).getTransamt();
					}
					log.info("(核心本日清算总额)todatyCoreClearAmt = " + todatyCoreClearAmt +", (上日未清算总额)preNotClearAmt = " + preNotClearAmt);
					
					// 插入fundchannelsettle表
					Fundchannelsettle fundchannelsettle = new Fundchannelsettle();
					fundchannelsettle.setCleardate(checkDate);
					fundchannelsettle.setFundchannelcode(fundchannelcode);
					fundchannelsettle.setPayerdeptnbr("1111111111111111");
					fundchannelsettle.setTransdate(checkDate);
					fundchannelsettle.setPayeeacctdeptnbr("1111111111111111");
					
					fundchannelsettle.setSettletyp(SettleTyp.NotClear);
					fundchannelsettle.setTransamt(todatyCoreClearAmt.subtract(todayUnionPayClearAmt).add(preNotClearAmt));
					fundchannelsettle.setSettleseqnbr(IDGenerateFactory.generateSeqId());
					FundchannelsettleDAO.insert(fundchannelsettle);
					
					fundchannelsettle.setSettletyp(SettleTyp.CoreClear);
					fundchannelsettle.setTransamt(todatyCoreClearAmt);
					fundchannelsettle.setSettleseqnbr(IDGenerateFactory.generateSeqId());
					FundchannelsettleDAO.insert(fundchannelsettle);
					
					fundchannelsettle.setSettletyp(SettleTyp.UnionPayClear);
					fundchannelsettle.setTransamt(todayUnionPayClearAmt);
					fundchannelsettle.setSettleseqnbr(IDGenerateFactory.generateSeqId());
					FundchannelsettleDAO.insert(fundchannelsettle);
				} catch (Exception e) {
					log.error(e.getMessage());
				}
				return null;
			}
		});
	}
}
