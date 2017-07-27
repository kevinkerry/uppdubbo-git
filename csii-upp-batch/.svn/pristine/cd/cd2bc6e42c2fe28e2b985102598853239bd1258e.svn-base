package com.csii.upp.batch.appl.paym;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.batch.appl.base.BaseAppl;
import com.csii.upp.constant.SettPeriod;
import com.csii.upp.constant.SettleStatus;
import com.csii.upp.dao.extend.BatchFeeProfitTransExtendDAO;
import com.csii.upp.dao.generate.BatchfeeprofitdaysettleDAO;
import com.csii.upp.dao.generate.BatchfeeprofittransDAO;
import com.csii.upp.dao.generate.MeracctinfoDAO;
import com.csii.upp.dto.extend.DeptSettlementMap;
import com.csii.upp.dto.generate.Batchfeeprofitdaysettle;
import com.csii.upp.dto.generate.BatchfeeprofitdaysettleExample;
import com.csii.upp.dto.generate.Batchfeeprofittrans;
import com.csii.upp.dto.generate.BatchfeeprofittransExample;
import com.csii.upp.dto.generate.BatchfeeprofittransExample.Criteria;
import com.csii.upp.dto.generate.Meracctinfo;
import com.csii.upp.supportor.IDGenerateFactory;

/**
 * paym结算
 * 
 * @author chen chao
 *
 */
public class DeptProfitSettlementAppl extends BaseAppl {
	protected MessageSource messageSource;

	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps) throws Exception {
		String settleStatus = SettleStatus.UNSETTLE;
		Date settleDate = this.getCheckDate(argMaps);
		Date clearDate =settleDate;
		// 商户结算处理
		merchantSettlement(settleStatus, settleDate, clearDate);
	}

	/**
	 * 商户结算处理
	 * 
	 * @throws SQLException
	 */
	protected void merchantSettlement(String settleStatus, Date settleDate, final Date clearDate) throws SQLException {
		// 删除Batchfeeprofitdaysettle（商户分润日结算汇总表）中当日结算日的数据
		BatchfeeprofitdaysettleExample batchfeeprofitdaysettleExample = new BatchfeeprofitdaysettleExample();
		//删除未结算数据
		batchfeeprofitdaysettleExample.createCriteria().andCleardateEqualTo(clearDate).andSettlestatusEqualTo(settleStatus);
		BatchfeeprofitdaysettleDAO.deleteByExample(batchfeeprofitdaysettleExample);

		// 查询当日结算日未结算的BatchMerTrans(商户台帐表)数据
		final Map<String, Object> params = new HashMap<String,Object>();
		params.put("settleDate", settleDate);
		params.put("settleStatus", settleStatus);
		final List<DeptSettlementMap> bmtList = BatchFeeProfitTransExtendDAO.selectFromBatchFeeProfitTrans(params);

		if (!bmtList.isEmpty() && bmtList != null) {
			getTransactionTemplate().execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus arg0) {
					try {
						for (DeptSettlementMap deptset : bmtList) {
							BigDecimal transAmount = deptset.getTransAmount();
							if (BigDecimal.ZERO.compareTo(transAmount) < 0) {
								Meracctinfo meracctinfo = null;
	
								meracctinfo = MeracctinfoDAO.selectByPrimaryKey(deptset.getMernbr());
								Batchfeeprofitdaysettle bmdsRecord = new Batchfeeprofitdaysettle();
								
								// 结算流水
								bmdsRecord.setSettleseqnbr(IDGenerateFactory.generateSeqId());
								// 交易日期
								bmdsRecord.setTransdate(getPostDate());
								// 清算日期
								bmdsRecord.setCleardate(clearDate);
								// 交易时间戳
								bmdsRecord.setTranstime(new java.util.Date());
								// 结算状态设置
								if (meracctinfo.getSettperiod().equals(SettPeriod.REALTIME)) {
									bmdsRecord.setSettlestatus(SettleStatus.SETTLED);
								} else {
									bmdsRecord.setSettlestatus(SettleStatus.UNSETTLE);
								}
								// 判断台账类型，设置交易金额
								bmdsRecord.setTransamt(transAmount);
								bmdsRecord.setDepartmentnbr(deptset.getDepartmentnbr());
								bmdsRecord.setMernbr(deptset.getMernbr());
								bmdsRecord.setPayeracctnbr(deptset.getPayeracctnbr());
								bmdsRecord.setPayeraccttypcd(deptset.getPayeraccttypcd());
								bmdsRecord.setPayeracctdeptnbr(deptset.getPayeracctdeptnbr());
								bmdsRecord.setPayeeacctnbr(deptset.getPayeeacctnbr());
								bmdsRecord.setPayeeacctdeptnbr(deptset.getPayeeacctdeptnbr());
								bmdsRecord.setPayeeaccttypcd(deptset.getPayeeaccttypcd());
								bmdsRecord.setPayeracctkind(deptset.getPayeracctkind());
								bmdsRecord.setPayeeacctkind(deptset.getPayeeacctkind());
								//bmdsRecord.setMemo(deptset.getMemo());
								//插商户日结算汇总表
								BatchfeeprofitdaysettleDAO.insertSelective(bmdsRecord);
								//更新台账结算状态及结算流水号 modified by wangtao 2016-11-11
								Batchfeeprofittrans batchfeeprofittrans = new Batchfeeprofittrans();
								batchfeeprofittrans.setSettleseqnbr(bmdsRecord.getSettleseqnbr());
								batchfeeprofittrans.setSettlestatus(SettleStatus.SETTLED);
								
								BatchfeeprofittransExample example1 = new BatchfeeprofittransExample();
								Criteria criteria =example1.createCriteria();
								criteria.andPayeracctnbrEqualTo(deptset.getPayeracctnbr())
									.andPayeraccttypcdEqualTo(deptset.getPayeraccttypcd())									
									.andPayeracctkindEqualTo(deptset.getPayeracctkind())
									.andPayeeacctnbrEqualTo(deptset.getPayeeacctnbr())									
									.andPayeeaccttypcdEqualTo(deptset.getPayeeaccttypcd())
									.andPayeeacctkindEqualTo(deptset.getPayeeacctkind())
									.andMernbrEqualTo(deptset.getMernbr())
									.andDepartmentnbrEqualTo(deptset.getDepartmentnbr())									
									.andSettledateEqualTo((Date)params.get("settleDate"))
									.andSettlestatusEqualTo((String)params.get("settleStatus"));
								if(deptset.getPayeracctdeptnbr()==null){
									criteria.andPayeracctdeptnbrIsNull();
								}else{
									criteria.andPayeracctdeptnbrEqualTo(deptset.getPayeracctdeptnbr());
								}
								if(deptset.getPayeeacctdeptnbr()==null){
									criteria.andPayeeacctdeptnbrIsNull();
								}else{
									criteria.andPayeeacctdeptnbrEqualTo(deptset.getPayeeacctdeptnbr());
								}
								if(deptset.getMemo()==null){
									criteria.andMemoIsNull();
								}else{
									criteria.andMemoEqualTo(deptset.getMemo());
								}
									
								BatchfeeprofittransDAO.updateByExampleSelective(batchfeeprofittrans, example1);
								
							}
						}
					} catch (Exception e) {
						throw new PeRuntimeException(e);
					}
					return null;
				}
			});
		}
	}



}
