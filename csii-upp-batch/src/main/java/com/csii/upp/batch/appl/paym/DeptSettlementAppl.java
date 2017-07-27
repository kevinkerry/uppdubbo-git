package com.csii.upp.batch.appl.paym;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.batch.appl.base.BaseAppl;
import com.csii.upp.batch.supportor.BatchUtil;
import com.csii.upp.batch.xml.format.FileFormat;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.ProcStep;
import com.csii.upp.constant.Separator;
import com.csii.upp.constant.SettMode;
import com.csii.upp.constant.SettPeriod;
import com.csii.upp.constant.SettleStatus;
import com.csii.upp.constant.StandBookTypCD;
import com.csii.upp.dao.extend.BatchMerTransExtendDAO;
import com.csii.upp.dao.extend.BatchmerdaysettleExtendDAO;
import com.csii.upp.dao.extend.OnlinesubtranshistExtendDAO;
import com.csii.upp.dao.generate.BatchmerdaysettleDAO;
import com.csii.upp.dao.generate.BatchmertransDAO;
import com.csii.upp.dao.generate.DeptinfoDAO;
import com.csii.upp.dao.generate.MeracctinfoDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.DeptSettlementMap;
import com.csii.upp.dto.generate.Batchmerdaysettle;
import com.csii.upp.dto.generate.BatchmerdaysettleExample;
import com.csii.upp.dto.generate.Batchmertrans;
import com.csii.upp.dto.generate.BatchmertransExample;
import com.csii.upp.dto.generate.BatchmertransExample.Criteria;
import com.csii.upp.dto.generate.Deptinfo;
import com.csii.upp.dto.generate.DeptinfoExample;
import com.csii.upp.dto.generate.Meracctinfo;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.FileHandler;
import com.csii.upp.util.StringUtil;

/**
 * paym结算
 * 
 * @author chen chao
 *
 */
public class DeptSettlementAppl extends BaseAppl {
	protected MessageSource messageSource;

	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps) throws Exception {
		String settleStatus = SettleStatus.UNSETTLE;
		String checkDataFlag = StringUtil.parseObjectToString(argMaps.get(Dict.DZBZ));
		Date settleDate = this.getCheckDate(argMaps);
		Date clearDate = settleDate;
		// 商户结算处理
		merchantSettlement(settleStatus, settleDate, clearDate);
		// 生成机构结算明细文件
		deptSettleFileGen(settleStatus, settleDate, clearDate);
	}

	/**
	 * 商户结算处理
	 * 
	 * @throws SQLException
	 */
	protected void merchantSettlement(String settleStatus, Date settleDate, final Date clearDate) throws SQLException {
		// 删除BatchMerDaySettle（商户日结算汇总表）中当日结算日的数据
		BatchmerdaysettleExample batchmerdaysettleExample = new BatchmerdaysettleExample();
		//删除未结算数据
		batchmerdaysettleExample.createCriteria().andCleardateEqualTo(clearDate).andSettlestatusEqualTo(settleStatus);
		BatchmerdaysettleDAO.deleteByExample(batchmerdaysettleExample);

		// 查询当日结算日未结算的BatchMerTrans(商户台帐表)数据
		final Map<String, Object> params = new HashMap<String,Object>();
		params.put("settleDate", settleDate);
		params.put("settleStatus", settleStatus);
		final List<DeptSettlementMap> bmtList = BatchMerTransExtendDAO.selectFromBatchMerTrans(params);

		if (!bmtList.isEmpty() && bmtList != null) {
			getTransactionTemplate().execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus arg0) {
					try {
						for (DeptSettlementMap deptset : bmtList) {
							BigDecimal transAmount = deptset.getTransAmount();
							if (BigDecimal.ZERO.compareTo(transAmount) < 0) {
								Meracctinfo meracctinfo = null;
	
								meracctinfo = MeracctinfoDAO.selectByPrimaryKey(deptset.getMernbr());
								Batchmerdaysettle bmdsRecord = new Batchmerdaysettle();
	
								// 结算流水
								bmdsRecord.setDaysettleseqnbr(IDGenerateFactory.generateSeqId());
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
								if (StandBookTypCD.MERCHANT_TRANS_WITHDRAW.equals(deptset.getStandbooktypcd())
										|| StandBookTypCD.MERCHANT_TRANS_BALANCE_FEE_RETURN.equals(deptset.getStandbooktypcd())) {
									bmdsRecord.setTransamt(transAmount.multiply(new BigDecimal(-1)));
								} else {
									bmdsRecord.setTransamt(transAmount);
								}
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
								bmdsRecord.setStandbooktypcd(deptset.getStandbooktypcd());
								bmdsRecord.setMemo(deptset.getMemo());
								//插商户日结算汇总表
								BatchmerdaysettleDAO.insertSelective(bmdsRecord);
								//更新台账结算状态及结算流水号 modified by wangtao 2016-11-11
								Batchmertrans batchMerTrans = new Batchmertrans();
								batchMerTrans.setSettleseqnbr(bmdsRecord.getDaysettleseqnbr());
								batchMerTrans.setSettlestatus(SettleStatus.SETTLED);
								
								BatchmertransExample example = new BatchmertransExample();
								Criteria criteria = example.createCriteria();
								criteria.andPayeracctnbrEqualTo(deptset.getPayeracctnbr())
									.andPayeraccttypcdEqualTo(deptset.getPayeraccttypcd())									
									.andPayeracctkindEqualTo(deptset.getPayeracctkind())
									.andPayeeacctnbrEqualTo(deptset.getPayeeacctnbr())									
									.andPayeeaccttypcdEqualTo(deptset.getPayeeaccttypcd())
									.andPayeeacctkindEqualTo(deptset.getPayeeacctkind())
									.andStandbooktypcdEqualTo(deptset.getStandbooktypcd())
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
									
								BatchmertransDAO.updateByExampleSelective(batchMerTrans, example);
								
								if (StandBookTypCD.MERCHANT_TRANS_PAY.equals(deptset.getStandbooktypcd()) || 
										StandBookTypCD.MERCHANT_TRANS_WITHDRAW.equals(deptset.getStandbooktypcd())) {
									Map<String, Object> onlineMap= new HashMap<String,Object>();
									onlineMap.put("procStep",ProcStep.SETTLED );
									onlineMap.put("merNbr", deptset.getMernbr());
									onlineMap.put("departmentNbr", deptset.getDepartmentnbr());
									onlineMap.put("standbooktypCD",deptset.getStandbooktypcd() );
									OnlinesubtranshistExtendDAO.updateSubHistTransStep(onlineMap);
								}
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

	/**
	 * 生成机构结算明细文件
	 * 
	 * @throws SQLException
	 */
	protected void deptSettleFileGen(String settleStatus, Date settleDate, Date clearDate) throws SQLException {

		List<Map<String, Object>> deptSettFileList = new ArrayList<Map<String, Object>>();

		// 查询DeptAcctInfo（机构账号信息表）获取所有机构信息
		DeptinfoExample deptinfoExample = new DeptinfoExample();
		deptinfoExample.createCriteria().andDeptnbrNotEqualTo("999000");
		List<Deptinfo> depList = DeptinfoDAO.selectByExample(deptinfoExample);
		for (Deptinfo deptinfo : depList) {
			// 查询BatchMerDaySettle（商户日结算汇总表）当前机构当前清算日期下的数据
			BatchmerdaysettleExample bmdsExample = new BatchmerdaysettleExample();
			bmdsExample.createCriteria().andDepartmentnbrEqualTo(deptinfo.getDeptnbr()).andCleardateEqualTo(clearDate)
					.andSettlestatusEqualTo(settleStatus);
			List<Batchmerdaysettle> bmdsList = BatchmerdaysettleDAO.selectByExample(bmdsExample);

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("deptNbr", deptinfo.getDeptnbr());
			params.put("settleStatus", settleStatus);
			params.put("clearDate", clearDate);
			// 计算结算金额
			Map<String, Object> deptSettFileMap = new HashMap<String, Object>();
			deptSettFileMap = computeSettleAmount(bmdsList, params);

			deptSettFileMap.put("deptNbr", deptinfo.getDeptnbr());// 机构号
			deptSettFileMap.put("clearDate",
					DateUtil.Date_To_DateTimeFormat(clearDate, DateFormatCode.DATE_FORMATTER3));
			deptSettFileList.add(deptSettFileMap);

		}
		// 生成文件
		generateDeptSettFile(deptSettFileList, clearDate);
	}

	private Map<String, Object> computeSettleAmount(List<Batchmerdaysettle> bmdsList, Map<String, Object> params)
			throws SQLException {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		// PayAmount(支付金额)
		BigDecimal payAmount = new BigDecimal(0);
		BigDecimal sum1 = BatchmerdaysettleExtendDAO.sumPayAmount(params);
		if (sum1 != null) {
			payAmount = sum1;
		}
		// PrncAmount（结算金额）
		BigDecimal prncAmount = new BigDecimal(0);
		// WithdrawAmount（未结算退货金额）
		BigDecimal withdrawAmount = new BigDecimal(0);
		BigDecimal sum2 = BatchmerdaysettleExtendDAO.sumwithdrawAmount(params);
		if (sum2 != null) {
			withdrawAmount = sum2;
		}
		// WithdrawFeeAmount（退还手续费金额）
		BigDecimal withdrawFeeAmount = new BigDecimal(0);
		// InFeeAmount（手续费收入金额）
		BigDecimal inFeeAmount = new BigDecimal(0);

		BigDecimal transAmount = new BigDecimal(0);

		if (bmdsList.size() > 0 && bmdsList != null) {
			for (Batchmerdaysettle batchmerdaysettle : bmdsList) {
				// 台账类型
				String standBookTypCD = batchmerdaysettle.getStandbooktypcd();

				transAmount = ("").equals(batchmerdaysettle.getTransamt()) ? new BigDecimal(0)
						: batchmerdaysettle.getTransamt();

				String settMode = MeracctinfoDAO.selectByPrimaryKey(batchmerdaysettle.getMernbr()).getSettmode();

				if (standBookTypCD.equals(StandBookTypCD.MERCHANT_TRANS_PAY)
						|| standBookTypCD.equals(StandBookTypCD.MERCHANT_TRANS_WITHDRAW)) {
					prncAmount = prncAmount.add(transAmount);
				}

				if (standBookTypCD.equals(StandBookTypCD.MERCHANT_TRANS_BALANCE_FEE_RETURN)) {
					prncAmount = prncAmount.add(transAmount);
				}

				if (standBookTypCD.equals(StandBookTypCD.MERCHANT_TRANS_BALANCE_FEE_RCV)) {
					if (settMode.equals(SettMode.BITE)) {// 差额结算
						prncAmount = prncAmount.add(transAmount);
					}
				}

				if (standBookTypCD.equals(StandBookTypCD.MERCHANT_TRANS_FEE_RCV)
						|| standBookTypCD.equals(StandBookTypCD.MERCHANT_TRANS_BALANCE_FEE_RCV)
						|| standBookTypCD.equals(StandBookTypCD.MERCHANT_TRANS_BALANCE_FEE_RETURN)) {
					inFeeAmount = inFeeAmount.add(transAmount);
				} else if (standBookTypCD.equals(StandBookTypCD.MERCHANT_TRANS_FEE_RETURN)) {
					withdrawFeeAmount = withdrawFeeAmount.add(transAmount);
				}
			}
		}
		resultMap.put("payAmount", payAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
		resultMap.put("prncAmount", prncAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
		resultMap.put("withdrawAmount", withdrawAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
		resultMap.put("withdrawFeeAmount", withdrawFeeAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
		resultMap.put("inFeeAmount", inFeeAmount.setScale(2, BigDecimal.ROUND_HALF_UP));

		return resultMap;

	}

	private void generateDeptSettFile(List<Map<String, Object>> deptSettFileList, Date clearDate) {
		// 本地路径
		String filePath = this.getApplBean().getClearbalanceFileFormatLocalPath();

		if (!deptSettFileList.isEmpty() && deptSettFileList != null) {

			FileFormat format = this.getApplBean().getIssueFileFormat().getFileFormat(this.getIssueFileFormatFileId());

			String fileName = new StringBuilder(format.getPrefix()).append(Separator.FILENAME_SEPARATOR)
					.append("CLEARBALANCE").append(Separator.FILENAME_SEPARATOR)
					.append(DateUtil.Date_To_DateTimeFormat(clearDate, DateFormatCode.DATE_FORMATTER3))
					.append(format.getSuffix()).toString();

			// String fileURI = new
			// StringBuilder(filePath).append("/").toString();

			// String file = new StringBuilder(fileURI).toString();

			this.writeIssueFile(deptSettFileList, format, filePath, fileName);

		}
	}

	/**
	 * 获取结算文件摸版
	 * 
	 * @return
	 */
	protected String getIssueFileFormatFileId() {
		return getApplBean().getSettleFileFormatPaym();
	}

	/**
	 * 生成下发文件
	 */
	private void writeIssueFile(List<Map<String, Object>> deptSettFileList, FileFormat format, String file,
			String fileName) {
		FileOutputStream out = null;
		try {
			String encoding = format.getEncoding();
			String lineSeparator = format.getLineSeparator();
			FileHandler.createFile(file, fileName);
			out = new FileOutputStream(file + fileName, true);
			for (Map<String, Object> map : deptSettFileList) {
				String standardString = BatchUtil.getFormatString(map, format);
				FileHandler.writeRecorde(standardString + lineSeparator, out, encoding);

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
}
