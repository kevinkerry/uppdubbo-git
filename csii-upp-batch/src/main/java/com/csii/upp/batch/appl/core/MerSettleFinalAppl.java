package com.csii.upp.batch.appl.core;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.batch.appl.base.BaseAppl;
import com.csii.upp.batch.supportor.BatchUtil;
import com.csii.upp.batch.xml.format.FileFormat;
import com.csii.upp.constant.CurrencyCode;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.ErrorState;
import com.csii.upp.constant.ErrorTyp;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.OveralltransTyp;
import com.csii.upp.constant.SendStatus;
import com.csii.upp.constant.Separator;
import com.csii.upp.constant.StandBookTypCD;
import com.csii.upp.constant.UploadFlag;
import com.csii.upp.dao.extend.BatchMerTransHistExtendDAO;
import com.csii.upp.dao.extend.BatchfeeprofitdaysettleExtendDAO;
import com.csii.upp.dao.extend.BatchfeeprofitsettleExtendDAO;
import com.csii.upp.dao.extend.BatchmerdaysettleExtendDAO;
import com.csii.upp.dao.extend.BatchmersettleExtendDAO;
import com.csii.upp.dao.generate.BatchcheckerrorDAO;
import com.csii.upp.dao.generate.BatchfeeprofitsettleDAO;
import com.csii.upp.dao.generate.BatchmersettleDAO;
import com.csii.upp.dao.generate.OnlinefileinfoDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Batchcheckerror;
import com.csii.upp.dto.generate.Batchfeeprofitsettle;
import com.csii.upp.dto.generate.Batchmerdaysettle;
import com.csii.upp.dto.generate.Batchmersettle;
import com.csii.upp.dto.generate.Batchmertranshist;
import com.csii.upp.dto.generate.Onlinefileinfo;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.service.fundprocess.CoreService;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.FileHandler;
import com.csii.upp.util.StringUtil;

/**
 * 商户结算
 * 
 * @author 姜星
 *
 */
public class MerSettleFinalAppl extends BaseAppl {

	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps) throws Exception {
		// 查询商户结算汇总表
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("SendStatus", SendStatus.UNSEND);
		para.put("ClearDate", getCheckDate(argMaps));
		List<Map> settList = BatchmersettleExtendDAO.queryMerchantSettExtForDeposit(para);
		if (settList != null && !settList.isEmpty()) {
			for (Iterator it = settList.iterator(); it.hasNext();) {
				Map settMap = (Map) it.next();
				// 商户结算
				if (StringUtil.parseBigDecimal(settMap.get(Dict.TRANS_AMT)).compareTo(BigDecimal.ZERO) == 0) {
					continue;
				}else {
					CoreService coreService = (CoreService)this.getRouterService(FundChannelCode.CORE.toLowerCase());
					InputFundData input = new InputFundData();
					input.setPayeracctnbr(StringUtil.parseObjectToString(settMap.get(Dict.PAYER_ACCT_NBR)));
					input.setTransamt(StringUtil.parseBigDecimal(settMap.get(Dict.TRANS_AMT)).setScale(2, BigDecimal.ROUND_HALF_UP));
					input.setCurrencycd(CurrencyCode.CNY);
					input.setMernbr(StringUtil.parseObjectToString(settMap.get(Dict.MER_NBR)));
					input.setPayeeacctnbr(StringUtil.parseObjectToString(settMap.get(Dict.PAYEE_ACCT_NBR)));
					input.setPayeeacctdeptnbr(StringUtil.parseObjectToString(settMap.get(Dict.PAYEE_ACCT_DEPT_NBR)));
					input.setSettleseqnbr(StringUtil.parseObjectToString(settMap.get(Dict.SETTLE_SEQ_NBR)));
					input.setTransdate(this.getPostDate());
					input.setTransid(OveralltransTyp.MERSETTLE);
					List<Map<String, String>> payeeAcctList = new ArrayList<Map<String, String>>();
					Map map = new HashMap();
					map.put(Dict.PAYEE_ACCT_NBR, StringUtil.parseObjectToString(settMap.get(Dict.PAYEE_ACCT_NBR)));
					map.put(Dict.SUB_TRANS_AMT, StringUtil.parseBigDecimal(settMap.get(Dict.TRANS_AMT)));
					payeeAcctList.add(map);
					input.setPayeeAcctList(payeeAcctList);
					RespSysHead output = coreService.settleMerchant(input);
					//RespSysHead output = RespSysHead.class.newInstance();
//					output.setResultStatus("SUCCESS");
//					output.setDownrtxnnbr("000000");
//					output.setDownrtxndate(new Date());					
					if (output.isSuccess()) {
						// 更新已经转账的本金结算记录
						Batchmersettle settle = new Batchmersettle();
						settle.setSendstatus(SendStatus.SUCCESS);
						settle.setSettleseqnbr(StringUtil.parseObjectToString(settMap.get(Dict.SETTLE_SEQ_NBR)));
						//modified by wangtao 20161215 更新下游流水号和下游日期
						settle.setDownsysseqnbr(output.getDownrtxnnbr());
						settle.setDownsysdate(output.getDownrtxndate());
						settle.setMemo(output.getDownflag());//1-挂账 0-不挂账
						BatchmersettleDAO.updateByPrimaryKeySelective(settle);
					} else {
						// 结算超时或者失败则增加日终差错
						Batchcheckerror record = new Batchcheckerror();
						record.setTransdate(getPostDate());
						record.setTranscode(OveralltransTyp.BTTF);
						record.setInnerfundtransnbr(StringUtil.parseObjectToString(settMap.get(Dict.SETTLE_SEQ_NBR)));
						record.setPayeracctnbr(StringUtil.parseObjectToString(settMap.get(Dict.PAYER_ACCT_NBR)));
						record.setPayeeacctnbr(StringUtil.parseObjectToString(settMap.get(Dict.PAYEE_ACCT_NBR)));
						record.setTransamt(StringUtil.parseBigDecimal(settMap.get(Dict.TRANS_AMT)));
						record.setTransstatus(output.getRtxnstate());
						record.setFundchannelcode(FundChannelCode.CORE);
						record.setErrorstatus(ErrorState.PRECHECK);
						record.setCurrencycd(CurrencyCode.CNY);
						record.setErrornbr(IDGenerateFactory.generateSeqId());
						record.setCheckerrortyp(ErrorTyp.PRERVMERCOREPROCESS);
						//更新为发送失败
						BatchcheckerrorDAO.insertSelective(record);
						Batchmersettle settle = new Batchmersettle();
						settle.setSendstatus(SendStatus.FAILURE);
						
						settle.setSettleseqnbr(StringUtil.parseObjectToString(settMap.get(Dict.SETTLE_SEQ_NBR)));
						//modified by wangtao 20161215 更新返回信息
						settle.setDownsysrespcode(output.getReturncode());
						settle.setDownsysrespmsg(output.getReturnmsg()); 
						BatchmersettleDAO.updateByPrimaryKeySelective(settle);
					}
				}
			}
		}
		
		//分润结算
		List<Map> profitSettList = BatchfeeprofitsettleExtendDAO.queryMerchantSettExtForDeposit(para);
		if (profitSettList != null && !profitSettList.isEmpty()) {
			for (Iterator it = profitSettList.iterator(); it.hasNext();) {
				Map settMap = (Map) it.next();
				// 商户结算
				if (StringUtil.parseBigDecimal(settMap.get(Dict.TRANS_AMT)).compareTo(BigDecimal.ZERO) == 0) {
					continue;
				}else {
					CoreService coreService = (CoreService)this.getRouterService(FundChannelCode.CORE.toLowerCase());
					InputFundData input = new InputFundData();
					input.setPayeracctnbr(StringUtil.parseObjectToString(settMap.get(Dict.PAYER_ACCT_NBR)));
					input.setTransamt(StringUtil.parseBigDecimal(settMap.get(Dict.TRANS_AMT)).setScale(2, BigDecimal.ROUND_HALF_UP));
					input.setCurrencycd(CurrencyCode.CNY);
					input.setMernbr(StringUtil.parseObjectToString(settMap.get(Dict.MER_NBR)));
					input.setPayeeacctnbr(StringUtil.parseObjectToString(settMap.get(Dict.PAYEE_ACCT_NBR)));
					input.setPayeeacctdeptnbr(StringUtil.parseObjectToString(settMap.get(Dict.PAYEE_ACCT_DEPT_NBR)));
					input.setSettleseqnbr(StringUtil.parseObjectToString(settMap.get(Dict.SETTLE_SEQ_NBR)));
					input.setTransdate(this.getPostDate());
					input.setTransid(OveralltransTyp.MERSETTLE);
					List<Map<String, String>> payeeAcctList = new ArrayList<Map<String, String>>();
					Map map = new HashMap();
					map.put(Dict.PAYEE_ACCT_NBR, StringUtil.parseObjectToString(settMap.get(Dict.PAYEE_ACCT_NBR)));
					map.put(Dict.SUB_TRANS_AMT, StringUtil.parseBigDecimal(settMap.get(Dict.TRANS_AMT)));
					payeeAcctList.add(map);
					input.setPayeeAcctList(payeeAcctList);
					RespSysHead output = coreService.settleMerchant(input);
				
					if (output.isSuccess()) {
						// 更新已经转账的本金结算记录
						Batchfeeprofitsettle settle = new Batchfeeprofitsettle();
						settle.setSendstatus(SendStatus.SUCCESS);
						settle.setSettleseqnbr(StringUtil.parseObjectToString(settMap.get(Dict.SETTLE_SEQ_NBR)));
						//modified by wangtao 20161215 更新下游流水号和下游日期
						settle.setDownsysseqnbr(output.getDownrtxnnbr());
						settle.setDownsysdate(output.getDownrtxndate());
						settle.setMemo(output.getDownflag());//1-挂账 0-不挂账
						BatchfeeprofitsettleDAO.updateByPrimaryKeySelective(settle);
					} else {
						// 结算超时或者失败则增加日终差错
						Batchcheckerror record = new Batchcheckerror();
						record.setTransdate(getPostDate());
						record.setTranscode(OveralltransTyp.BTTF);
						record.setInnerfundtransnbr(StringUtil.parseObjectToString(settMap.get(Dict.SETTLE_SEQ_NBR)));
						record.setPayeracctnbr(StringUtil.parseObjectToString(settMap.get(Dict.PAYER_ACCT_NBR)));
						record.setPayeeacctnbr(StringUtil.parseObjectToString(settMap.get(Dict.PAYEE_ACCT_NBR)));
						record.setTransamt(StringUtil.parseBigDecimal(settMap.get(Dict.TRANS_AMT)));
						record.setTransstatus(output.getRtxnstate());
						record.setFundchannelcode(FundChannelCode.CORE);
						record.setErrorstatus(ErrorState.PRECHECK);
						record.setCurrencycd(CurrencyCode.CNY);
						record.setErrornbr(IDGenerateFactory.generateSeqId());
						record.setCheckerrortyp(ErrorTyp.PRERVMERCOREPROCESS);
						//更新为发送失败
						BatchcheckerrorDAO.insertSelective(record);
						Batchfeeprofitsettle settle = new Batchfeeprofitsettle();
						settle.setSendstatus(SendStatus.FAILURE);
						
						settle.setSettleseqnbr(StringUtil.parseObjectToString(settMap.get(Dict.SETTLE_SEQ_NBR)));
						//modified by wangtao 20161215 更新返回信息
						settle.setDownsysrespcode(output.getReturncode());
						//settle.setDownsysrespmsg(output.getReturnmsg()); 
						BatchfeeprofitsettleDAO.updateByPrimaryKeySelective(settle);
					}
				}
			}
		}		
		
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("SendStatus", SendStatus.SUCCESS);
		params.put("ClearDate", this.getCheckDate(argMaps));
		//商户结算交易明细
		List<Map<String, Object>> merList = BatchmersettleExtendDAO.getMerNbrFromBatchmersettle(params);
		if (merList != null && !merList.isEmpty()) {
			for (Map<String, Object> map : merList) {
				Map<String, Object> queryDetailMap = new HashMap<String,Object>();
				queryDetailMap.put("merNbr", map.get("MERNBR"));
				queryDetailMap.put("ClearDate", this.getCheckDate(argMaps));
				//结算交易明细数据
				List<Batchmertranshist> batchmertranshistList = BatchMerTransHistExtendDAO.getDateFromBatchMerTrans(queryDetailMap);
				//结算明细数据
				List<Batchmerdaysettle> batchmerdaysettlesList = BatchmerdaysettleExtendDAO.getDateFromBatchMerDaySettle(queryDetailMap);
				//生成结算交易明细文件
				if (batchmertranshistList != null) {
					List<Map<String, Object>> transDetailList = new ArrayList<Map<String, Object>>();
					for (Batchmertranshist transDetail : batchmertranshistList) {
						Map<String, Object> transDetailMap = new HashMap<String,Object>();
						transDetailMap.put("TransSettDate", transDetail.getTransdate());
						transDetailMap.put("TransDateTime", transDetail.getTranstime());
						transDetailMap.put("MerSeqNo", transDetail.getMerseqnbr());
						transDetailMap.put("TransSeqNo", transDetail.getTransseqnbr());
						transDetailMap.put("MerId", transDetail.getMernbr());
						transDetailMap.put("TransAmt", transDetail.getTransamt().setScale(2, BigDecimal.ROUND_HALF_UP));
						transDetailMap.put("SettStatus", transDetail.getSettlestatus());
						transDetailMap.put("MerAcctType", transDetail.getStandbooktypcd());
						transDetailMap.put("Remark1", transDetail.getMemo());
						transDetailMap.put("Remark2", "");
						transDetailList.add(transDetailMap);
					}
					generateSubmerTransDetail(transDetailList, map,getCheckDate(argMaps));
				}
				//生成结算明细文件
				if (batchmerdaysettlesList != null) {
					List<Map<String, Object>> settDetailList = new ArrayList<Map<String, Object>>();
					for (Batchmerdaysettle settDetail : batchmerdaysettlesList) {
						Map<String, Object> settDetailMap = new HashMap<String,Object>();
						BigDecimal transAmount = settDetail.getTransamt().setScale(2, BigDecimal.ROUND_HALF_UP);
						String transferType = settDetail.getStandbooktypcd();
						settDetailMap.put("ClearDate",settDetail.getCleardate() );
						settDetailMap.put("MerchantId", settDetail.getMernbr());
						//settDetailMap.put("TermNo", );
						if (transferType.equals(StandBookTypCD.MERCHANT_TRANS_PAY)) {
							settDetailMap.put("PrncAmount", transAmount);
						}else if (transferType.equals(StandBookTypCD.MERCHANT_TRANS_BALANCE_FEE_RCV)) {
							settDetailMap.put("InFeeAmount", transAmount);
						}else if (transferType.equals(StandBookTypCD.MERCHANT_TRANS_FEE_RETURN)) {
							settDetailMap.put("RfdFeeAmount",transAmount );
						}else if (transferType.equals(StandBookTypCD.MERCHANT_TRANS_FEE_RCV)) {
							settDetailMap.put("InFeeAmount", transAmount);
						}
						settDetailMap.put("Remark1", settDetail.getMemo());
						settDetailMap.put("Remark2", "");
						settDetailList.add(settDetailMap);
					}
					generateSubmerSettDetail(settDetailList, map,getCheckDate(argMaps));
				}
			}
		}
		
	
		// 结算数据清除
		merchantSettlementAfter(getCheckDate(argMaps));
	}
	public void generateSubmerTransDetail(List<Map<String, Object>> list,Map<String, Object> map,Date cleardate) throws Exception{
		FileFormat format = this.getApplBean().getIssueFileFormat().getFileFormat("submer_trans_detail");
		//String clearDate = DateUtil.Date_To_DateTimeFormat(getPostDate(), DateFormatCode.DATE_FORMATTER3);
		String fileName = new StringBuilder(format.getPrefix()).append(Separator.FILENAME_SEPARATOR).
				append(StringUtil.parseObjectToString(map.get("MERNBR"))).append(Separator.FILENAME_SEPARATOR).append(DateUtil.Date_To_DateTimeFormat(cleardate, DateFormatCode.DATE_FORMATTER3)).append(".txt").toString();
		String filePath = new StringBuilder(this.getApplBean().getSubmerTransDetailPath()).append(StringUtil.parseObjectToString(map.get("MERNBR"))).append("/").toString();
		//String fileURI = new StringBuilder(filePath).append(mernbr).append("/").toString();
		this.writeIssueFile(list, format, filePath, fileName);
		
		Onlinefileinfo of = new Onlinefileinfo();
		of.setFilenbr(IDGenerateFactory.generateSeqId());
		of.setBatchnbr("2");
		of.setFilepath(filePath);
		of.setFilename(fileName);
		of.setFileparsedate(cleardate);
		of.setFilememo(StringUtil.parseObjectToString(map.get("MERPLATFORMNBR")));//文件摘要存放平台号
		of.setCleardate(cleardate);
		of.setUploadflag(UploadFlag.Int);
		OnlinefileinfoDAO.insertSelective(of);
	}
	public void generateSubmerSettDetail(List<Map<String, Object>> list,Map<String, Object> map ,Date cleardate) throws Exception{
		FileFormat format = this.getApplBean().getIssueFileFormat().getFileFormat("submer_sett_detail");
		//String clearDate = DateUtil.Date_To_DateTimeFormat(getPostDate(), DateFormatCode.DATE_FORMATTER3);
		String fileName = new StringBuilder(format.getPrefix()).append(Separator.FILENAME_SEPARATOR).
				append(StringUtil.parseObjectToString(map.get("MERNBR"))).append(Separator.FILENAME_SEPARATOR).append(DateUtil.Date_To_DateTimeFormat(cleardate, DateFormatCode.DATE_FORMATTER3)).append(".txt").toString();
		String filePath = new StringBuilder(this.getApplBean().getSubmerSettDetailPath()).append(StringUtil.parseObjectToString(map.get("MERNBR"))).append("/").toString();
		//String fileURI = new StringBuilder(filePath).append(mernbr).append("/").toString();
		this.writeIssueFile(list, format, filePath, fileName);
		
		Onlinefileinfo of = new Onlinefileinfo();
		of.setFilenbr(IDGenerateFactory.generateSeqId());
		of.setBatchnbr("3");
		of.setFilepath(filePath);
		of.setFilename(fileName);
		of.setFileparsedate(cleardate);
		of.setFilememo(StringUtil.parseObjectToString(map.get("MERPLATFORMNBR")));//文件摘要存放平台号
		of.setCleardate(cleardate);
		of.setUploadflag(UploadFlag.Int);
		OnlinefileinfoDAO.insertSelective(of);
	}
	
//	public void generateDeptSettFile(List<Map> list) {
//
//		if (list != null && !list.isEmpty()) {
//			log.info("共有[" + list.size() + "]条" + "商户本金和手续费" + "记录需要向核心发送转账");
//			FileFormat format = this.getApplBean().getIssueFileFormat().getFileFormat("mer-prncpfee-sett");
//			String clearDate = DateUtil.Date_To_DateTimeFormat(getPostDate(), DateFormatCode.DATE_FORMATTER3);
//			// 生成商户结算文件
//			String fileName = new StringBuilder(FundChannelCode.PAYM).append("_").append(clearDate).append(".txt")
//					.toString();
//			String filePath = this.getApplBean().getMerSettleFinalPath();
//			String fileURI = new StringBuilder(filePath).append(clearDate).append("/").toString();
//			this.writeIssueFile(list, format, fileURI, fileName);
//			FileOutputStream out = null;
//			try {
//				String encoding = format.getEncoding();
//				String lineSeparator = format.getLineSeparator();
//				FileHandler.createFile(fileURI, fileName);
//				out = new FileOutputStream(fileURI + fileName, true);
//				for (Map map : list) {
//					String standardString = BatchUtil.getFormatString(map, format);
//					FileHandler.writeRecorde(standardString + lineSeparator, out, encoding);
//				}
//			} catch (Exception e) {
//				throw new PeRuntimeException(e.getMessage());
//			} finally {
//				try {
//					if (out != null)
//						out.close();
//				} catch (IOException e) {
//				}
//			}
			// 下发文件上传到服务器
//			if (!SftpUtil.uploadFile(fileName, fileURI, FtpConfigFactory.getConfig("MerPrncpfeeSett"))) {
//				throw new PeRuntimeException(StringUtil.buildString("upload issue file:", fileName, " error"));
//			}
//		} else {
//			log.info("没有上送核心的商户结算文件需要生成");
//		}
//	}
	/**
	 * 生成下发文件
	 */
	private void writeIssueFile(List<Map<String,Object>> list, FileFormat format,
			String filelocal, String fileName){
		FileOutputStream out = null;
		try {
			String encoding = format.getEncoding();
			String lineSeparator = format.getLineSeparator();
			FileHandler.createFile(filelocal, fileName);
			out = new FileOutputStream(filelocal + fileName, true);
			for (Map<String,Object> params : list) {
				String standardString = BatchUtil.getFormatString(params, format);
				FileHandler.writeRecorde(standardString + lineSeparator, out,
						encoding);

			}
		} catch (Exception e) {
			throw new PeRuntimeException(e.getMessage());
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
			}
		}
	}
	/**
	 * 商户结算后处理
	 */
	private void merchantSettlementAfter(Date cleardate) {
		final Map<String, Object> para = new HashMap<String, Object>();
		para.put("ClearDate",cleardate ); 
		
//		final Map cleanPara = new HashMap();
//		cleanPara.put("ClearDate", DateUtil.rolDate(clearDate, clearDays));		
//		log.info("开始清理过期交易数据，清理日期[" + DateUtil.dateToString(getPostDate()) + "]，【重复数据】清理日期 duplicateCleanDay=" + cleanPara);
		
		getTransactionTemplate().execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus arg0) {
				//清理商户台账明细表
				log.info("清理商户结算日汇总表开始");
				BatchmerdaysettleExtendDAO.transferMerchantSettExtTmpToHistory(para);
				BatchmerdaysettleExtendDAO.deleteMerchantSettExtTmpBeforeDate(para);
				log.info("清理商户结算日汇总表结束");
				return null;
			}
		});
		
		getTransactionTemplate().execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus arg0) {
				//清理商户台账明细表
				log.info("清理商户结算汇总表开始");
				BatchmersettleExtendDAO.transferMerchantSettExtToHistory(para);
				BatchmersettleExtendDAO.deleteMerchantSettExtBeforeDate(para);
				log.info("清理商户结算汇总表结束");
				return null;
			}
		});
		
		getTransactionTemplate().execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus arg0) {
				//清理商户台账明细表
				log.info("清理商户分润日汇总表开始");
				BatchfeeprofitdaysettleExtendDAO.transferMerchantSettExtTmpToHistory(para);
				BatchfeeprofitdaysettleExtendDAO.deleteMerchantSettExtTmpBeforeDate(para);
				log.info("清理商户分润日汇总表结束");
				return null;
			}
		});
		
		getTransactionTemplate().execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus arg0) {
				//清理商户台账明细表
				log.info("清理商户分润汇总表开始");
				BatchfeeprofitsettleExtendDAO.transferMerchantSettExtToHistory(para);
				BatchfeeprofitsettleExtendDAO.deleteMerchantSettExtBeforeDate(para);
				log.info("清理商户分润汇总表结束");
				return null;
			}
		});		
		
		//订单表 和历史订单表  ot_order_info和ht_order_info
//		getTransactionTemplate().execute(new TransactionCallback() {
//			public Object doInTransaction(TransactionStatus arg0) {
//				//清理商户台账明细表
//				log.info("清理电商订单信息表开始");
//				sqlMap.delete("pp.process.deleteHtOrderInfoHistoryDuplicate", cleanPara);
//				sqlMap.insert("pp.process.transferOrderInfoTmpToHistory", para);
//				sqlMap.delete("pp.process.deleteOrderInfoTmpBeforeDate", para);
//				log.info("清理电商订单信息表结束");
//				return null;
//			}
//		});
		
		//确认支付表 OT_TIMEOUT_CFM 删除
//		getTransactionTemplate().execute(new TransactionCallback() {
//			public Object doInTransaction(TransactionStatus arg0) {
//				//清理确认支付表
//				log.info("清理确认支付表OT_TIMEOUT_CFM开始");
//				sqlMap.insert("pp.process.transferTimeoutCfmTmpBeforeDate", para);
//				sqlMap.delete("pp.process.deleteTimeoutCfmTmpBeforeDate", para);
//				log.info("清理确认支付表OT_TIMEOUT_CFM结束");
//				return null;
//			}
//		});       		
	}
}
