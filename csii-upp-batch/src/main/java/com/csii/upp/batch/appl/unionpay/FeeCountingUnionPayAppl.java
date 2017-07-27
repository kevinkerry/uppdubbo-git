package com.csii.upp.batch.appl.unionpay;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.csii.pe.core.PeException;
import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.batch.appl.base.BaseAppl;
import com.csii.upp.batch.event.handler.TransferDeptFeeEvent;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.SendStatus;
import com.csii.upp.dao.extend.DownsysfundtransExtendDAO;
import com.csii.upp.dao.generate.FundchannelDAO;
import com.csii.upp.dao.generate.FundchannelfeedeptacctinfoDAO;
import com.csii.upp.dao.generate.FundchannelsettleDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.generate.Fundchannel;
import com.csii.upp.dto.generate.Fundchannelfeedeptacctinfo;
import com.csii.upp.dto.generate.Fundchannelsettle;
import com.csii.upp.dto.generate.FundchannelsettleExample;
import com.csii.upp.service.fundprocess.CoreService;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.StringUtil;

/**
 * 批量手续费计算
 */
public class FeeCountingUnionPayAppl extends BaseAppl {

	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps) throws PeException {
		try {
			final String fundchannelcode = StringUtil.parseObjectToString(argMaps.get(Dict.FCNM));
			final Date checkDate = this.getCheckDate(argMaps);
			final Date postdate = this.getPostDate();
			final Fundchannel fundchannel = FundchannelDAO.selectByPrimaryKey(fundchannelcode);

			this.getTransactionTemplate().execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus arg0) {
					Map<String, Object> queryMap = new HashMap<String, Object>();
					queryMap.put("cleardate", checkDate);
					queryMap.put(Dict.FUNDCHANNELCODE, fundchannelcode);
					log.info("(对账日期)checkDate = " + checkDate + ", (渠道类型)fundchannelcode = " + fundchannelcode);

					List<Map<String, Object>> merDeptTransmodes = DownsysfundtransExtendDAO
							.getMerDeptTransmode(queryMap);

					queryMap.put(Dict.TRANSCODE, "01");// 支付类交易
					List<Map<String, Object>> downPayTotal = DownsysfundtransExtendDAO
							.getTotalTransAmtAndFeeAmt(queryMap);
					List<Map<String, Object>> merDeptPayTotal = DownsysfundtransExtendDAO.getMerDeptTransAmt(queryMap);
					List<Map<String, Object>> merDeptPayTotalNew = new ArrayList<Map<String, Object>>();
					if (downPayTotal != null && downPayTotal.size() > 0
							&& downPayTotal.get(0).get("totalTransAmt") != null) {
						BigDecimal totalTransAmt = new BigDecimal(
								StringUtil.parseObjectToString(downPayTotal.get(0).get("totalTransAmt")));
						BigDecimal totalFeeAmt = new BigDecimal(
								StringUtil.parseObjectToString(downPayTotal.get(0).get("totalFeeAmt")));
						log.info("(银联下游支付总额)totalTransAmt = " + totalTransAmt + ", (银联下游支付手续费总额)totalFeeAmt = "
								+ totalFeeAmt);

						BigDecimal hasCalculatedFeeAmt = new BigDecimal(0);
						for (Map<String, Object> map : merDeptPayTotal) {
							BigDecimal merDeptPayTransamt = new BigDecimal(
									StringUtil.parseObjectToString(map.get("merdevdeptnbrTransamt")));

							if (merDeptPayTotal.indexOf(map) == (merDeptPayTotal.size() - 1)) {
								map.put("merdevdeptnbrTransfee", totalFeeAmt.subtract(hasCalculatedFeeAmt).setScale(2,BigDecimal.ROUND_HALF_UP));
								log.info("(商户机构号)" + map.get("merdevdeptnbr") + ", (交易模式)transmode = "
										+ map.get("transmode") + ", (支付手续费)merdevdeptnbrTransfee = "
										+ map.get("merdevdeptnbrTransfee"));
							} else {
								BigDecimal merdevdeptnbrTransfee = merDeptPayTransamt
										.divide(totalTransAmt, 3, BigDecimal.ROUND_HALF_UP).multiply(totalFeeAmt)
										.setScale(2, BigDecimal.ROUND_DOWN);
								map.put("merdevdeptnbrTransfee", merdevdeptnbrTransfee);
								hasCalculatedFeeAmt = hasCalculatedFeeAmt.add(merdevdeptnbrTransfee);
								log.info("(商户机构号)" + map.get("merdevdeptnbr") + ", (交易模式)transmode = "
										+ map.get("transmode") + ", (支付手续费)merdevdeptnbrTransfee = "
										+ map.get("merdevdeptnbrTransfee"));
							}
							merDeptPayTotalNew.add(map);
						}
					}

					queryMap.put(Dict.TRANSCODE, "04");// 消费退货类交易
					List<Map<String, Object>> downRefundTotal = DownsysfundtransExtendDAO
							.getTotalTransAmtAndFeeAmt(queryMap);
					List<Map<String, Object>> merDeptRefundTotal = DownsysfundtransExtendDAO
							.getMerDeptTransAmt(queryMap);
					List<Map<String, Object>> merDeptRefundTotalNew = new ArrayList<Map<String, Object>>();
					if (downRefundTotal != null && downRefundTotal.size() > 0
							&& downRefundTotal.get(0).get("totalTransAmt") != null) {
						BigDecimal totalTransAmt = new BigDecimal(
								StringUtil.parseObjectToString(downRefundTotal.get(0).get("totalTransAmt")));
						BigDecimal totalFeeAmt = new BigDecimal(
								StringUtil.parseObjectToString(downRefundTotal.get(0).get("totalFeeAmt")));
						log.info("(银联下游退货总额)totalTransAmt = " + totalTransAmt + ", (银联下游退货返还手续费总额)totalFeeAmt = "
								+ totalFeeAmt);

						BigDecimal hasCalculatedFeeAmt = new BigDecimal(0);
						for (Map<String, Object> map : merDeptRefundTotal) {
							BigDecimal merDeptRefundTransamt = new BigDecimal(
									StringUtil.parseObjectToString(map.get("merdevdeptnbrTransamt")));

							if (merDeptRefundTotal.indexOf(map) == (merDeptRefundTotal.size() - 1)) {
								map.put("merdevdeptnbrTransfee", totalFeeAmt.subtract(hasCalculatedFeeAmt).setScale(2,BigDecimal.ROUND_HALF_UP));
								log.info("(商户机构号)" + map.get("merdevdeptnbr") + ", (交易模式)transmode = "
										+ map.get("transmode") + ", (退货返还手续费)merdevdeptnbrTransfee = "
										+ map.get("merdevdeptnbrTransfee"));
							} else {
								BigDecimal merdevdeptnbrTransfee = merDeptRefundTransamt
										.divide(totalTransAmt, 3, BigDecimal.ROUND_HALF_UP).multiply(totalFeeAmt)
										.setScale(2, BigDecimal.ROUND_DOWN);
								map.put("merdevdeptnbrTransfee", merdevdeptnbrTransfee);
								hasCalculatedFeeAmt = hasCalculatedFeeAmt.add(merdevdeptnbrTransfee);
								log.info( "(商户机构号)" + map.get("merdevdeptnbr") + ", (交易模式)transmode = "
										+ map.get("transmode") + ", (退货返还手续费)merdevdeptnbrTransfee = "
										+ map.get("merdevdeptnbrTransfee"));
							}
							merDeptRefundTotalNew.add(map);
						}
					}

					queryMap.put(Dict.TRANSCODE, "31");// 消费撤销类交易
					List<Map<String, Object>> downCancleTotal = DownsysfundtransExtendDAO
							.getTotalTransAmtAndFeeAmt(queryMap);
					List<Map<String, Object>> merDeptCancelTotal = DownsysfundtransExtendDAO
							.getMerDeptTransAmt(queryMap);
					List<Map<String, Object>> merDeptCancleTotalNew = new ArrayList<Map<String, Object>>();
					if (downCancleTotal != null && downCancleTotal.size() > 0
							&& downCancleTotal.get(0).get("totalTransAmt") != null) {
						BigDecimal totalTransAmt = new BigDecimal(
								StringUtil.parseObjectToString(downCancleTotal.get(0).get("totalTransAmt")));
						BigDecimal totalFeeAmt = new BigDecimal(
								StringUtil.parseObjectToString(downCancleTotal.get(0).get("totalFeeAmt")));
						log.info("(银联下游消费撤销总额)totalTransAmt = " + totalTransAmt + ", (银联下游消费撤销返还手续费总额)totalFeeAmt = "
								+ totalFeeAmt);

						BigDecimal hasCalculatedFeeAmt = new BigDecimal(0);
						for (Map<String, Object> map : merDeptCancelTotal) {
							BigDecimal merDeptCancleTransamt = new BigDecimal(
									StringUtil.parseObjectToString(map.get("merdevdeptnbrTransamt")));

							if (merDeptCancelTotal.indexOf(map) == (merDeptCancelTotal.size() - 1)) {
								map.put("merdevdeptnbrTransfee", totalFeeAmt.subtract(hasCalculatedFeeAmt).setScale(2,BigDecimal.ROUND_HALF_UP));
								log.info("(商户机构号)" + map.get("merdevdeptnbr") + ", (交易模式)transmode = "
										+ map.get("transmode") + ", (消费撤销返还手续费)merdevdeptnbrTransfee = "
										+ map.get("merdevdeptnbrTransfee"));
							} else {
								BigDecimal merdevdeptnbrTransfee = merDeptCancleTransamt
										.divide(totalTransAmt, 3, BigDecimal.ROUND_HALF_UP).multiply(totalFeeAmt)
										.setScale(2, BigDecimal.ROUND_DOWN);
								map.put("merdevdeptnbrTransfee", merdevdeptnbrTransfee);
								hasCalculatedFeeAmt = hasCalculatedFeeAmt.add(merdevdeptnbrTransfee);
								log.info("(商户机构号)" + map.get("merdevdeptnbr") + ", (交易模式)transmode = "
										+ map.get("transmode") + ", (消费撤销返还手续费)merdevdeptnbrTransfee = "
										+ map.get("merdevdeptnbrTransfee"));
							}
							merDeptCancleTotalNew.add(map);
						}
					}

					if (merDeptTransmodes != null && merDeptTransmodes.size() > 0) {
						for (Map<String, Object> merDeptTransmode : merDeptTransmodes) {
							String merdeptnbr = (String) merDeptTransmode.get("merdevdeptnbr");
							String transmode = (String) merDeptTransmode.get("transmode");
							BigDecimal payFee = new BigDecimal(0);
							BigDecimal refundFee = new BigDecimal(0);
							BigDecimal cancleFee = new BigDecimal(0);
							if (merDeptPayTotalNew.size() > 0) {
								for (Map<String, Object> map : merDeptPayTotalNew) {
									if (merdeptnbr.equals((String) map.get("merdevdeptnbr"))
											&& transmode.equals((String) map.get("transmode"))) {
										payFee = (BigDecimal) map.get("merdevdeptnbrTransfee");
									}
								}
							}
							if (merDeptRefundTotalNew.size() > 0) {
								for (Map<String, Object> map : merDeptRefundTotalNew) {
									if (merdeptnbr.equals((String) map.get("merdevdeptnbr"))
											&& transmode.equals((String) map.get("transmode"))) {
										refundFee = (BigDecimal) map.get("merdevdeptnbrTransfee");
									}
								}
							}
							if (merDeptCancleTotalNew.size() > 0) {
								for (Map<String, Object> map : merDeptCancleTotalNew) {
									if (merdeptnbr.equals((String) map.get("merdevdeptnbr"))
											&& transmode.equals((String) map.get("transmode"))) {
										cancleFee = (BigDecimal) map.get("merdevdeptnbrTransfee");
									}
								}
							}
							try {
								// 插入fundchannelsettle表
								Fundchannelsettle fundchannelsettle = new Fundchannelsettle();
								fundchannelsettle.setCleardate(checkDate);
								fundchannelsettle.setFundchannelcode(fundchannelcode);
								fundchannelsettle.setPayerdeptnbr(merdeptnbr);
								fundchannelsettle.setSendstatus(SendStatus.UNSEND);
								fundchannelsettle.setTransdate(postdate);
								fundchannelsettle.setTransamt(payFee.subtract(refundFee).subtract(cancleFee).abs());
								fundchannelsettle.setPayamount(fundchannelsettle.getTransamt());

								Fundchannelfeedeptacctinfo fundchannelfeedeptacctinfo = FundchannelfeedeptacctinfoDAO
										.selectByPrimaryKey(merdeptnbr, FundChannelCode.UNIONPAY, transmode);
								if (fundchannelfeedeptacctinfo == null) {
									log.error("机构手续费支出账户信息为空-----------fundchannelfeedeptacctinfo is null");
									throw new PeRuntimeException("机构手续费支出账户信息为空");
								}
								log.info("payFee.subtract(refundFee).subtract(cancleFee).compareTo(BigDecimal.ZERO)值：" + 
										payFee.subtract(refundFee).subtract(cancleFee).compareTo(BigDecimal.ZERO));
								if (payFee.subtract(refundFee).subtract(cancleFee).compareTo(BigDecimal.ZERO) > 0) {
									fundchannelsettle.setPayeesettleacctnbr(fundchannel.getSettleacctnbr());
									fundchannelsettle.setPayeeacctdeptnbr(transmode);
									fundchannelsettle
											.setPayersettleacctnbr(fundchannelfeedeptacctinfo.getFeepayacctnbr());
								} else if (payFee.subtract(refundFee).subtract(cancleFee).compareTo(BigDecimal.ZERO) < 0) {
									fundchannelsettle
											.setPayeesettleacctnbr(fundchannelfeedeptacctinfo.getFeepayacctnbr());
									fundchannelsettle.setPayeeacctdeptnbr(transmode);
									fundchannelsettle.setPayersettleacctnbr(fundchannel.getSettleacctnbr());
								}else {
									continue;
								}
								fundchannelsettle.setSettleseqnbr(IDGenerateFactory.generateSeqId());
								FundchannelsettleDAO.insert(fundchannelsettle);
								
								//开启异步线程去把机构手续费支出账户的手续费转到垫资户
//								TransferDeptFeeEvent transferDeptFeeEvent = new TransferDeptFeeEvent();
//								transferDeptFeeEvent.setFundchannelsettle(fundchannelsettle);
//								transferDeptFeeEvent.setCoreService((CoreService) getRouterService(FundChannelCode.CORE));
//								getEventManager().doService(transferDeptFeeEvent);
							} catch (Exception e) {
								log.error(e);
								throw new PeRuntimeException(e);
							}
						}
					}
					return null;
				}
			});
			
			//事务结束后，转账，不允许事务内转账，容易引起多线程调度，导致的问题
			FundchannelsettleExample fundChannelUnsendExample = new FundchannelsettleExample();
			//查询条件，清算日期未当前清算日期，交易状态为未发送，且SETTLETYPE为空
			fundChannelUnsendExample.createCriteria()
				.andCleardateEqualTo(checkDate)
				.andSendstatusEqualTo(SendStatus.UNSEND)
				.andSettletypIsNull()
				.andFundchannelcodeEqualTo(fundchannelcode);
			List<Fundchannelsettle> fundChannelSettleList = FundchannelsettleDAO.selectByExample(fundChannelUnsendExample);

			//遍历List，依次异步线程执行每一条转账
			for(Fundchannelsettle fundChannelSettle : fundChannelSettleList) {
				log.info(new StringBuilder()
							.append("批量手续费转账开始,")
							.append("转账流水：")
							.append(fundChannelSettle.getSettleseqnbr())
							.append("，转出账号：")
							.append(fundChannelSettle.getPayersettleacctnbr())
							.append("，转入账号：")
							.append(fundChannelSettle.getPayeesettleacctnbr())
							.append("，交易金额：")
							.append(fundChannelSettle.getTransamt())
							.append("。"));
				TransferDeptFeeEvent transferDeptFeeEvent = new TransferDeptFeeEvent();
				transferDeptFeeEvent.setFundchannelsettle(fundChannelSettle);
				transferDeptFeeEvent.setCoreService((CoreService) getRouterService(FundChannelCode.CORE));
				getEventManager().doService(transferDeptFeeEvent);
			}
		} catch (Exception e) {
			throw new PeException(DictErrors.DATABASE_EXCEPTION);
		}
	}
}
