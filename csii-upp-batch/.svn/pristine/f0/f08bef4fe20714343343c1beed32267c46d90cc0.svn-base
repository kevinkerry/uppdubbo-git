package com.csii.upp.batch.appl.qrcodepay;

import java.math.BigDecimal;
import java.sql.SQLException;
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
import com.csii.upp.constant.InnerAcctCfmMode;
import com.csii.upp.constant.SendStatus;
import com.csii.upp.dao.extend.DownsysfundtransExtendDAO;
import com.csii.upp.dao.generate.DeptacctinfoDAO;
import com.csii.upp.dao.generate.FundchannelDAO;
import com.csii.upp.dao.generate.FundchannelsettleDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.generate.Deptacctinfo;
import com.csii.upp.dto.generate.Fundchannel;
import com.csii.upp.dto.generate.Fundchannelsettle;
import com.csii.upp.dto.generate.FundchannelsettleExample;
import com.csii.upp.service.fundprocess.CoreService;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.StringUtil;

/**
 * 二维码行社但保户转账
 */
public class GuaranteeTransferAppl extends BaseAppl {

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

					List<Map<String, Object>> merDeptQrCodeAmt = DownsysfundtransExtendDAO
							.getMerDeptQrCodeAmt(queryMap);
					
					//遍历取出机构
					List<String> merDeptList = new ArrayList<String>();
					for(int i=0;i<merDeptQrCodeAmt.size();i++){
						Map merDeptQrCodeAmtMap = (Map)merDeptQrCodeAmt.get(i);
						if(!merDeptList.contains(merDeptQrCodeAmtMap.get("merdevdeptnbr"))){
							merDeptList.add(merDeptQrCodeAmtMap.get("merdevdeptnbr").toString());
						}
					}
					
					//遍历机构
					for(int i=0;i<merDeptList.size();i++){
						//取出机构账户信息
						//遍历机构金额
						BigDecimal transferAmt = new BigDecimal(0);
						for(int j=0;j<merDeptQrCodeAmt.size();j++){
							Map merDeptQrCodeAmtMap = (Map)merDeptQrCodeAmt.get(j);
							if(!merDeptQrCodeAmtMap.get("merdevdeptnbr").equals(merDeptList.get(i))){
								continue;
							}
							BigDecimal transAmt = 
									new BigDecimal(merDeptQrCodeAmtMap.get("transferfeeamt").toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
							BigDecimal feeAmt = 
									new BigDecimal(merDeptQrCodeAmtMap.get("feeamt").toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
							if(!"1".equals(merDeptQrCodeAmtMap.get(Dict.TRANSCODE))){
								transAmt = transAmt.multiply(new BigDecimal(-1));
							}	
							log.debug("行社["+merDeptList.get(i)+"]交易类型["+merDeptQrCodeAmtMap.get(Dict.TRANSCODE)+"]清算金额["+transAmt+"]");		
							log.debug("行社["+merDeptList.get(i)+"]交易类型["+merDeptQrCodeAmtMap.get(Dict.TRANSCODE)+"]手续费金额["+feeAmt+"]");							
							transferAmt = transferAmt.add(transAmt);

						}
						log.info("行社["+merDeptList.get(i)+"]清算日期["+checkDate+"]转账清算金额["+transferAmt+"]");
						//将数据存入表中
						try {
							// 插入fundchannelsettle表
							Fundchannelsettle fundchannelsettle = new Fundchannelsettle();
							fundchannelsettle.setCleardate(checkDate);
							fundchannelsettle.setFundchannelcode(fundchannelcode);
							fundchannelsettle.setPayerdeptnbr(merDeptList.get(i));
							fundchannelsettle.setSendstatus(SendStatus.UNSEND);
							fundchannelsettle.setTransdate(postdate);
							fundchannelsettle.setTransamt(transferAmt);
							fundchannelsettle.setPayamount(fundchannelsettle.getTransamt());

							//查询机构账户
							Deptacctinfo acct = getDeptAcctInfo(merDeptList.get(i),InnerAcctCfmMode.INNER_ES);//默认取自建电商担保账户
							//从资金归集户转账到行社担保户
							if (transferAmt.compareTo(BigDecimal.ZERO) > 0) {
								fundchannelsettle.setPayeesettleacctnbr(acct.getAcctnbr());
								fundchannelsettle.setPayeeacctdeptnbr(acct.getAcctdeptnbr());
								fundchannelsettle
										.setPayersettleacctnbr(fundchannel.getSettleacctnbr());
							} else if (transferAmt.compareTo(BigDecimal.ZERO) < 0) {
								fundchannelsettle
										.setPayeesettleacctnbr(fundchannel.getSettleacctnbr());
								fundchannelsettle.setPayeeacctdeptnbr(acct.getAcctdeptnbr());
								fundchannelsettle.setPayersettleacctnbr(acct.getAcctnbr());
							}else {
								continue;
							}
							fundchannelsettle.setSettleseqnbr(IDGenerateFactory.generateSeqId());
							FundchannelsettleDAO.insert(fundchannelsettle);

						} catch (Exception e) {
							log.error(e);
							throw new PeRuntimeException(e);
						}	
					}
					
					//再次遍历机构，从行社手续费支出账户向行社担保户转账
					for(int i=0;i<merDeptList.size();i++){
						//根据行社计算手续费，区分不同的交易模式，电商平台，O2O及外商			
						queryMap.put("merdevdeptnbr", merDeptList.get(i));

						List<Map<String, Object>> merDeptQrCodeFeeAmt = DownsysfundtransExtendDAO
								.getMerDeptQrCodeFeeAmt(queryMap);		
						BigDecimal feeAmtINNER_ES = new BigDecimal(0);
						BigDecimal feeAmtO2O = new BigDecimal(0);
						BigDecimal feeAmtOUT_ES = new BigDecimal(0);
						for(int j=0;j<merDeptQrCodeFeeAmt.size();j++){
							Map merDeptQrCodeFeeAmtMap = (Map)merDeptQrCodeFeeAmt.get(j);
							BigDecimal feeAmt = 
									new BigDecimal(merDeptQrCodeFeeAmtMap.get("feeamt").toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
							if(!"1".equals(merDeptQrCodeFeeAmtMap.get(Dict.TRANSCODE))){
								feeAmt = feeAmt.multiply(new BigDecimal(-1));
							}							
							if(InnerAcctCfmMode.INNER_ES.equals(merDeptQrCodeFeeAmtMap.get("transmode"))){//电商
								feeAmtINNER_ES = feeAmtINNER_ES.add(feeAmt);
							}else if(InnerAcctCfmMode.O2O.equals(merDeptQrCodeFeeAmtMap.get("transmode"))){//O2O
								feeAmtO2O = feeAmtO2O.add(feeAmt);
							}else if(InnerAcctCfmMode.OUT_ES.equals(merDeptQrCodeFeeAmtMap.get("transmode"))){//外商
								feeAmtOUT_ES = feeAmtOUT_ES.add(feeAmt);
							}
							
						}
						log.debug("行社["+merDeptList.get(i)+"]交易模式电商[11]手续费金额["+feeAmtINNER_ES+"]");
						log.debug("行社["+merDeptList.get(i)+"]交易模式O2O[12]手续费金额["+feeAmtO2O+"]");
						log.debug("行社["+merDeptList.get(i)+"]交易模式外商[13]手续费金额["+feeAmtOUT_ES+"]");
						
						try {
							// 插入fundchannelsettle表
							Fundchannelsettle fundchannelsettle = new Fundchannelsettle();
							fundchannelsettle.setCleardate(checkDate);
							fundchannelsettle.setFundchannelcode(fundchannelcode);
							fundchannelsettle.setPayerdeptnbr(merDeptList.get(i));
							fundchannelsettle.setSendstatus(SendStatus.UNSEND);
							fundchannelsettle.setTransdate(postdate);
							//查询机构账户
							Deptacctinfo acct = getDeptAcctInfo(merDeptList.get(i),InnerAcctCfmMode.INNER_ES);//默认取自建电商担保账户							
							//电商							
							insertFundChannelSettle(acct,InnerAcctCfmMode.INNER_ES,feeAmtINNER_ES,fundchannelsettle);	
							//O2O							
							insertFundChannelSettle(acct,InnerAcctCfmMode.O2O,feeAmtO2O,fundchannelsettle);
							//外商
							insertFundChannelSettle(acct,InnerAcctCfmMode.OUT_ES,feeAmtOUT_ES,fundchannelsettle);
						} catch (Exception e) {
							log.error(e);
							throw new PeRuntimeException(e);
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
							.append("二维码行社担保户转账开始,")
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
			throw new PeRuntimeException("机构["+merDevDeptNbr+"]未配置机构账户信息");
		}	
		return acctInfo;
	}	
	
	/**
	 * 
	 * @param acct
	 * @param innerAcctCfmMode
	 * @param feeAmt
	 * @param fundchannelsettle
	 * @throws SQLException
	 */
	private void insertFundChannelSettle(Deptacctinfo acct,String innerAcctCfmMode,BigDecimal feeAmt,Fundchannelsettle fundchannelsettle) throws SQLException{
		Deptacctinfo acctFee = getDeptAcctInfo(acct.getAcctdeptnbr(),innerAcctCfmMode);
		fundchannelsettle.setTransamt(feeAmt);
		fundchannelsettle.setPayamount(fundchannelsettle.getTransamt());			
		//从资金归集户转账到行社担保户
		if (feeAmt.compareTo(BigDecimal.ZERO) > 0) {
			fundchannelsettle.setPayeesettleacctnbr(acct.getAcctnbr());
			fundchannelsettle.setPayeeacctdeptnbr(acct.getAcctdeptnbr());
			fundchannelsettle
					.setPayersettleacctnbr(acctFee.getFeeacctnbr());
		} else if (feeAmt.compareTo(BigDecimal.ZERO) < 0) {
			fundchannelsettle
					.setPayeesettleacctnbr(acctFee.getFeeacctnbr());
			fundchannelsettle.setPayeeacctdeptnbr(acct.getAcctdeptnbr());
			fundchannelsettle.setPayersettleacctnbr(acct.getAcctnbr());
		}else {
			return;
		}
		fundchannelsettle.setSettleseqnbr(IDGenerateFactory.generateSeqId());
		FundchannelsettleDAO.insert(fundchannelsettle);			
		
	}
}
