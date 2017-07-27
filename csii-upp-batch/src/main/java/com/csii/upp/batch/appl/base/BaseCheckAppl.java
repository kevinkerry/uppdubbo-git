package com.csii.upp.batch.appl.base;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.batch.appl.eaccount.ErrorCategory;
import com.csii.upp.batch.supportor.BatchApplSupportor;
import com.csii.upp.constant.CheckFileDealFlag;
import com.csii.upp.constant.CheckStatus;
import com.csii.upp.constant.ErrorState;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.InnerRtxnTyp;
import com.csii.upp.constant.OveralltransTyp;
import com.csii.upp.constant.ProcessMode;
import com.csii.upp.constant.YN;
import com.csii.upp.dao.extend.DownsysfundtransExtendDAO;
import com.csii.upp.dao.extend.InnerpreclearfundtransExtendDAO;
import com.csii.upp.dao.generate.BatchcheckerrorDAO;
import com.csii.upp.dao.generate.DownsysfundtransDAO;
import com.csii.upp.dao.generate.FundchannelcleartransDAO;
import com.csii.upp.dao.generate.InnercheckapplyDAO;
import com.csii.upp.dao.generate.InnerfundtransDAO;
import com.csii.upp.dao.generate.InnerfundtranshistDAO;
import com.csii.upp.dao.generate.InnerpreclearfundtransDAO;
import com.csii.upp.dao.generate.OveralltransDAO;
import com.csii.upp.dao.generate.OveralltranshistDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.CheckUpdateStatus;
import com.csii.upp.dto.extend.PreClearCheckTrans;
import com.csii.upp.dto.generate.Batchcheckerror;
import com.csii.upp.dto.generate.Downsysfundtrans;
import com.csii.upp.dto.generate.DownsysfundtransExample;
import com.csii.upp.dto.generate.Fundchannelcleartrans;
import com.csii.upp.dto.generate.Innercheckapply;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.dto.generate.InnerfundtransExample;
import com.csii.upp.dto.generate.Innerfundtranshist;
import com.csii.upp.dto.generate.Innerpreclearfundtrans;
import com.csii.upp.dto.generate.InnerpreclearfundtransExample;
import com.csii.upp.dto.generate.Overalltrans;
import com.csii.upp.dto.generate.Overalltranshist;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 对账数据核对应用调用入口
 * 
 * @author 徐锦
 * 
 */
public abstract class BaseCheckAppl extends BaseAppl {
	
	/**
	 * 获得渠道对账文件信息
	 * @param argMaps
	 * @return
	 */
	protected abstract List<Innercheckapply> getInnerCheckApply(Map<String, Object> argMaps);
	
	/**
	 * 下游交易流水不存在时对账流程
	 * 
	 * @param innerRtxn
	 * @param intervalDate
	 */
	protected abstract CheckUpdateStatus checkDownRtxnNotExist(PreClearCheckTrans preClearCheckTrans,
			Date intervalDate,String checkdataflag);

	/**
	 * 下游交易流水存在时对账流程
	 * 
	 * @param innerRtxn
	 * @param downRtxn
	 * @param innerEAcctrtxn
	 */
	protected abstract CheckUpdateStatus checkDownRtxnExist(PreClearCheckTrans preClearCheckTrans,String checkdataflag);

	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps) {
		String fundChannelCd = StringUtil.parseObjectToString(argMaps.get(Dict.FCNM));
		String checkdataflag = StringUtil.parseObjectToString(argMaps.get(Dict.DZBZ));
		String preCheck = CheckStatus.PRECHECK;
		// 当前对账日期为节假日(大额、小额、超网)跳过
		//modified by wt2013 20161201  如果不存在对账文件，则抛出异常，另一种情况，暂时不晓得什么情况
		List<Innercheckapply> checkapplys = this.getInnerCheckApply(argMaps);
		/*
		 * POC测试添加
		 * 注掉下面部分代码
		 */
//		if (checkapplys.isEmpty() ||
//				(!checkapplys.isEmpty() && CheckFlag.CHECKFLAG_UNWORK.equals(checkapplys.get(0).getCheckflag()))) {
//			log.error(new StringBuilder()
//					.append("当前对账日期：")
//					.append("，当前对账文件个数：")
//					.append(checkapplys.size()));
//			throw new PeRuntimeException("待对账文件个数异常");
//		}
		// 获得对账核对数据
		List<PreClearCheckTrans> preClearCheckTransList = InnerpreclearfundtransExtendDAO
				.getPreClearCheckTrans(fundChannelCd, preCheck);
		// 循环取交易流水对账
		for (PreClearCheckTrans preClearCheckTrans : preClearCheckTransList) {
			CheckUpdateStatus updateState = null;
			// Y:下游交易存在处理,N:下游交易不存在处理
			if (YN.Y.equals(preClearCheckTrans.getDownexist())) {
				updateState = this.checkDownRtxnExist(preClearCheckTrans,checkdataflag);
			} else {
				// 获得对账宽限日
				int waitDays = StringUtil.parseInteger(argMaps.get(Dict.DZKX));
				// 获得intervalDays天前的日期
				/*
				 *POC测试使用
				 *将对账宽限日概念去掉，直接将日期延后两天
				 */
//				Date intervalDate = DateUtil.addDate(checkapplys.get(0).getCheckstartdate(), -waitDays);
				Date intervalDate = DateUtil.addDate(preClearCheckTrans.getTransdate(), 2);
				updateState = this.checkDownRtxnNotExist(preClearCheckTrans, intervalDate,checkdataflag);
			}
			// 根据计算出的状态更新相应的交易表
			this.updateStatus(updateState, preClearCheckTrans);
		}
		// 后续处理
		this.checkAfter(checkapplys);
	}

	/**
	 * 关键域是否一致
	 * 
	 * @param innerRtxn
	 * @param downRtxn
	 * @return
	 */
	protected boolean isKeyFieldEqual(PreClearCheckTrans preClearCheckTrans) {
		boolean isKeyField = false;
		if (preClearCheckTrans.getPayeracctnbr() != null) {
			isKeyField = preClearCheckTrans.getTransamt().equals(preClearCheckTrans.getDowntransamt())
					&& (preClearCheckTrans.getPayeracctnbr().equals(preClearCheckTrans.getDownpayeracctnbr())
							|| preClearCheckTrans.getPayeracctnbr().equals(preClearCheckTrans.getDownpayeeacctnbr()));
		}
		if (preClearCheckTrans.getPayeeacctnbr() != null && !isKeyField) {
			isKeyField = preClearCheckTrans.getTransamt().equals(preClearCheckTrans.getDowntransamt())
					&& (preClearCheckTrans.getPayeeacctnbr().equals(preClearCheckTrans.getDownpayeeacctnbr())
							|| preClearCheckTrans.getPayeeacctnbr().equals(preClearCheckTrans.getDownpayeracctnbr()));
		}
		if (!isKeyField) {
			isKeyField = preClearCheckTrans.getTransamt().equals(preClearCheckTrans.getDowntransamt());
		}
		return isKeyField;

	}

	/**
	 * 关键域是否一致(针对银联特殊处理)
	 * 
	 * @param innerRtxn
	 * @param downRtxn
	 * @return
	 */
	protected boolean isKeyFieldEqualUnionPay(PreClearCheckTrans preClearCheckTrans) {
		// 按照风险管理要求，主账号仅保留前6位和后4位，中间用*号屏蔽，
		// 对于主账号为非卡号的截取后19位，屏蔽规则不变
		String temp;
		String innerRtxnPayeracctnbr = preClearCheckTrans.getPayeracctnbr();
		if (innerRtxnPayeracctnbr != null) {
			temp = innerRtxnPayeracctnbr.substring(6, innerRtxnPayeracctnbr.length() - 4);
			innerRtxnPayeracctnbr = innerRtxnPayeracctnbr.replace(temp, "");
		}

		String innerRtxnPayeeacctnbr = preClearCheckTrans.getPayeeacctnbr();
		if (innerRtxnPayeeacctnbr != null) {
			temp = innerRtxnPayeeacctnbr.substring(6, innerRtxnPayeeacctnbr.length() - 4);
			innerRtxnPayeeacctnbr = innerRtxnPayeeacctnbr.replace(temp, "");
		}

		String downRtxnPayeracctnbr = preClearCheckTrans.getDownpayeracctnbr() != null
				? preClearCheckTrans.getDownpayeracctnbr().replace("*", "") : null;
		String downRtxnPayeeacctnbr = preClearCheckTrans.getDownpayeeacctnbr() != null
				? preClearCheckTrans.getDownpayeeacctnbr().replace("*", "") : null;

		return preClearCheckTrans.getTransamt().equals(preClearCheckTrans.getDowntransamt())
				&& (innerRtxnPayeracctnbr.equals(downRtxnPayeracctnbr)
						|| innerRtxnPayeracctnbr.equals(downRtxnPayeeacctnbr)
						|| innerRtxnPayeeacctnbr.equals(downRtxnPayeeacctnbr)
						|| innerRtxnPayeeacctnbr.equals(downRtxnPayeracctnbr));
	}

	/**
	 * 获取双边账中非电子账户资金渠道代码
	 * 
	 * @param Overalltransnbr
	 *            总交易流水号
	 * @return
	 */
	protected String getFundChannelCode(String overalltransTypCd, String overalltransnbr) {
		if (OveralltransTyp.STXNIBPS.equals(overalltransTypCd)) {
			return FundChannelCode.IBPS;
		} else if (OveralltransTyp.STXNHVPS.equals(overalltransTypCd)) {
			return FundChannelCode.HVPS;
		} else if (OveralltransTyp.STXNBEPS.equals(overalltransTypCd)) {
			return FundChannelCode.BEPS;
		} else if (OveralltransTyp.STXNDPC.equals(overalltransTypCd)) {
			return FundChannelCode.DPC;
		} else {
			List<Innerfundtrans> Innerfundtranss = null;
			InnerfundtransExample example = new InnerfundtransExample();
			example.createCriteria().andOveralltransnbrEqualTo(overalltransnbr)
					.andFundchannelcodeNotEqualTo(FundChannelCode.EACCOUNT);
			try {
				Innerfundtranss = InnerfundtransDAO.selectByExample(example);
			} catch (SQLException e) {
				log.error("BaseCheckAppl Error:" + e.getMessage());
			}
			return (Innerfundtranss != null && Innerfundtranss.size() > 0) ? Innerfundtranss.get(0).getFundchannelcode()
					: null;
		}
	}

	/**
	 * 获取总交易类型
	 * 
	 * @param Overalltransnbr
	 *            总交易流水号
	 * @return
	 */
	protected Overalltrans getOveralltrans(String overalltransnbr) {
		Overalltrans rtxn = null;
		try {
			rtxn = OveralltransDAO.selectByPrimaryKey(overalltransnbr);
		} catch (SQLException e) {
			log.error("BaseCheckAppl getOveralltrans Error:" + e.getMessage());
		}
		return rtxn;
	}

	/**
	 * 通过总交易流水号判断特定交易类型的流水是否存在
	 * 
	 * @param OveralltransNbr
	 *            总交易流水号
	 * @param innerRtxnTypCd
	 *            交易类型代码
	 * @return
	 */
	protected boolean getInnerfundtrans(String overalltransnbr, String innerRtxnTypCd) {
		InnerpreclearfundtransExample example = new InnerpreclearfundtransExample();
		example.createCriteria().andTranscodeEqualTo(innerRtxnTypCd).andOveralltransnbrEqualTo(overalltransnbr);
		try {
			List<Innerpreclearfundtrans> Innerfundtranss = InnerpreclearfundtransDAO.selectByExample(example);
			if (Innerfundtranss != null && Innerfundtranss.size() > 0) {
				return true;
			}
		} catch (SQLException e) {
			log.error("CheckEccountAppl Error:" + e.getMessage());
		}
		return false;
	}

	/**
	 * 通过总交易流水号判断特定交易类型的流水是否存在
	 * 
	 * @param OveralltransNbr
	 *            总交易流水号
	 * @param innerRtxnTypCd
	 *            交易类型代码
	 * @param innerRtxnTypCd
	 *            本次交易流水号
	 * @return
	 */
	protected boolean getGreaterThanInnerfundtrans(String overalltransnbr, String innerRtxnTypCd, String innerRtxnNbr) {
		InnerpreclearfundtransExample example = new InnerpreclearfundtransExample();
		example.createCriteria().andTranscodeEqualTo(innerRtxnTypCd).andOveralltransnbrEqualTo(overalltransnbr)
				.andInnerfundtransnbrGreaterThan(innerRtxnNbr);
		try {
			List<Innerpreclearfundtrans> Innerfundtranss = InnerpreclearfundtransDAO.selectByExample(example);
			if (Innerfundtranss != null && Innerfundtranss.size() > 0) {
				return true;
			}
		} catch (SQLException e) {
			log.error("CheckEccountAppl Error:" + e.getMessage());
		}
		return false;
	}

	protected boolean isSingleTrans(String overallTransTyp) {
		final List<String> transTypList = Arrays.asList(OveralltransTyp.UPAY, OveralltransTyp.PAYR,
				OveralltransTyp.VATF, OveralltransTyp.OEBP,OveralltransTyp.VTRT);
		if (transTypList.contains(overallTransTyp)) {
			return true;
		}
		return false;
	}
	protected boolean isDoubleTrans(String overallTransTyp) {
		final List<String> transTypList = Arrays.asList(InnerRtxnTyp.CoreInnerTransfer);
		if (transTypList.contains(overallTransTyp)) {
			return true;
		}
		return false;
	}

	/**
	 * 对账后处理
	 * 
	 * @param checkapply
	 * @param
	 */
	protected void checkAfter(final List<Innercheckapply> checkapplys) {
		getTransactionTemplate().execute(new TransactionCallback() {

			@Override
			public Object doInTransaction(TransactionStatus arg0) {
				try {
					String fundChannelCd = null;
					/*
					 * POC测试添加
					 * if(checkapplys != null)
					 */
					if(checkapplys != null)
					for(Innercheckapply checkapply : checkapplys){
						// step1：更新渠道对账文件申请表
						checkapply.setDealcode(CheckFileDealFlag.Checked);
						InnercheckapplyDAO.updateByPrimaryKeySelective(checkapply);
						fundChannelCd = checkapply.getDownsysnbr();
					}
					/*
					 * POC测试添加
					 * if(fundChannelCd == null)
					 * fundChannelCd = FundChannelCode.UNIONPAY;
					 */
					if(fundChannelCd == null)
						fundChannelCd = FundChannelCode.UNIONPAY;
					// step2
					// ：将下游交易对账流水表DownSysFundTrans非待对账的数据移植到历史表DownSysFundTransHist
					DownsysfundtransExtendDAO.InsertDownsysfundtransToHist(fundChannelCd, CheckStatus.PRECHECK);

					// step3：删除非待对账下游交易对账流水表DownSysFundTrans
					DownsysfundtransExample example = new DownsysfundtransExample();
					example.createCriteria().andFundchannelcodeEqualTo(fundChannelCd)
							.andCheckstatusNotEqualTo(CheckStatus.PRECHECK);

					DownsysfundtransDAO.deleteByExample(example);

					// step:4：平台待清算流水中对账平的数据移植到台账表里
					//批量转账不记录台账
					InnerpreclearfundtransExample innerExample = new InnerpreclearfundtransExample();
					innerExample.createCriteria().andCheckstatusNotEqualTo(CheckStatus.PRECHECK)
							.andFundchannelcodeEqualTo(fundChannelCd).andTranscodeNotEqualTo(InnerRtxnTyp.BatchTransferTrans);
					List<Innerpreclearfundtrans> innerList = null;
					innerList = InnerpreclearfundtransDAO.selectByExample(innerExample);

					for (Innerpreclearfundtrans innerItem : innerList) {
						Fundchannelcleartrans fundRecord = new Fundchannelcleartrans();
						fundRecord.setCleartransnbr(IDGenerateFactory.generateSeqId());
						fundRecord.setInnerfundtransnbr(innerItem.getInnerfundtransnbr());
						fundRecord.setCleardate(innerItem.getCleardate());
						fundRecord.setTransdate(innerItem.getTransdate());
						fundRecord.setFundchannelcode(innerItem.getFundchannelcode());
						fundRecord.setPayeracctnbr(innerItem.getPayeracctnbr());
						fundRecord.setPayeeacctnbr(innerItem.getPayeeacctnbr());
						fundRecord.setFeeamt(innerItem.getFeeamt());
						fundRecord.setTransamt(innerItem.getTransamt());
						fundRecord.setOveralltransnbr(innerItem.getOveralltransnbr());
						fundRecord.setCheckdataflag(innerItem.getCheckdataflag());
						fundRecord.setTranscode(innerItem.getTranscode());
						FundchannelcleartransDAO.insertSelective(fundRecord);

					}
					InnerpreclearfundtransDAO.deleteByExample(innerExample);
				} catch (SQLException e) {
					throw new PeRuntimeException(e);
				}
				return null;
			}
		});
	}

	/**
	 * 根据状态更新相应的交易表
	 * 
	 * @param updateStatus
	 * @param innerTrans
	 * @param downTrans
	 */
	protected void updateStatus(final CheckUpdateStatus updateStatus, final PreClearCheckTrans innerTrans) {
		if (updateStatus == null) {
			return;
		}
		final String innerCheckStatus = updateStatus.getInnerCheckStatus();
		final String innerTransStatus = updateStatus.getInnerTransStatus();
		final String downCheckStatus = updateStatus.getDownCheckStatus();
		final String checkErrorType = updateStatus.getCheckErrorType();
		final String overallTransStatus = updateStatus.getOverallTransStatus();
		// String origAllRtxnState = updateState.getOrigAllRtxnState(); //
		// 原总交易状态（目前针对撤销交易）
		final String overallCheckStatus = updateStatus.getOverallCheckStatus();
		final String fundChannelCode = updateStatus.getFundChannelCode(); // 差错处理资金通道编号
		this.getTransactionTemplate().execute(new TransactionCallback() {

			@Override
			public Object doInTransaction(TransactionStatus arg0) {
				try {
					// 更新内部待清算资金流水
					if(!StringUtil.isStringEmpty(innerCheckStatus)
							||!StringUtil.isStringEmpty(innerTransStatus)){
						Innerpreclearfundtrans record = new Innerpreclearfundtrans();
						record.setInnerfundtransnbr(innerTrans.getInnerfundtransnbr());
						record.setCheckstatus(innerCheckStatus);
						record.setTransstatus(innerTransStatus);
						InnerpreclearfundtransDAO.updateByPrimaryKeySelective(record);
	
						Innerfundtranshist innerHist = new Innerfundtranshist();
						innerHist.setInnerfundtransnbr(innerTrans.getInnerfundtransnbr());
						innerHist.setCheckstatus(innerCheckStatus);
						innerHist.setTransstatus(innerTransStatus);
						InnerfundtranshistDAO.updateByPrimaryKeySelective(innerHist);
					}

					// 更新下游交易对账流水
					if (downCheckStatus != null) {
						Downsysfundtrans downHist = new Downsysfundtrans();
						downHist.setInnerfundtransnbr(innerTrans.getInnerfundtransnbr());
						downHist.setCheckstatus(downCheckStatus);
						DownsysfundtransDAO.updateByPrimaryKeySelective(downHist);
					}

					if(!StringUtil.isStringEmpty(overallCheckStatus)
							||!StringUtil.isStringEmpty(overallTransStatus)){
						// 更新总流水交易状态和对账状态
						Overalltranshist overHist = new Overalltranshist();
						overHist.setOveralltransnbr(innerTrans.getOveralltransnbr());
						overHist.setCheckstatus(overallCheckStatus);
						overHist.setOveralltransstatus(overallTransStatus);
						OveralltranshistDAO.updateByPrimaryKeySelective(overHist);
					}

					// 插入差错表
					if (checkErrorType != null) {
						ErrorCategory errorCat = BatchApplSupportor.getApplBean().getErrorTypCat();
						
						Batchcheckerror checkError = new Batchcheckerror();
						checkError.setCheckerrortyp(checkErrorType);
						checkError.setTransstatus(
								innerTransStatus != null ? innerTransStatus : innerTrans.getTransstatus());
						checkError.setCheckstatus(
								innerCheckStatus != null ? innerCheckStatus : innerTrans.getCheckstatus());
						checkError.setInnerfundtransnbr(innerTrans.getInnerfundtransnbr());
						checkError.setOveralltransnbr(innerTrans.getOveralltransnbr());
						checkError.setTranscode(innerTrans.getTranscode());
						checkError.setPayeeacctnbr(innerTrans.getPayeeacctnbr());
						checkError.setPayeename(innerTrans.getPayeename());
						checkError.setPayeracctnbr(innerTrans.getPayeracctnbr());
						checkError.setPayername(innerTrans.getPayername());
						checkError.setTransamt(innerTrans.getTransamt());
						checkError.setCurrencycd(innerTrans.getCurrencycd());
						checkError.setFundchannelcode(
								fundChannelCode == null ? innerTrans.getFundchannelcode() : fundChannelCode);
						checkError.setCleardate(innerTrans.getCleardate());
						checkError.setErrorstatus(ErrorState.PRECHECK);
						checkError.setTransdate(innerTrans.getTransdate());
						checkError.setErrornbr(IDGenerateFactory.generateSeqId());
						if (null != errorCat){
							checkError.setAcccktyp(errorCat.getErrorCat(checkErrorType));
							checkError.setMaxprocesstimes(errorCat.getMaxProcessTimes(checkErrorType));
							checkError.setProcessmode(errorCat.getProcessMode(checkErrorType, true));
						} else {
							checkError.setAcccktyp("99");//差错类型未知
							checkError.setMaxprocesstimes(0L);
							checkError.setProcessmode(ProcessMode.PROCESS_BY_MAN);
						}
						checkError.setHasprocesstimes(0L);
						BatchcheckerrorDAO.insertSelective(checkError);
					}
				} catch (SQLException e) {
					throw new PeRuntimeException(e);
				}
				return null;
			}
		});
	}
}
