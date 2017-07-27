package com.csii.upp.batch.appl.paym;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.batch.appl.base.BaseAppl;
import com.csii.upp.constant.CheckStatus;
import com.csii.upp.constant.PayModeCd;
import com.csii.upp.constant.PayStatus;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.extend.BatchConfirmSubClearTransExtendDAO;
import com.csii.upp.dao.extend.BatchcleartransExtendDAO;
import com.csii.upp.dao.extend.BatchdownsystransExtendDAO;
import com.csii.upp.dao.extend.BatchdownsystranshistExtendDAO;
import com.csii.upp.dao.generate.BatchclearsubtransDAO;
import com.csii.upp.dao.generate.BatchcleartransDAO;
import com.csii.upp.dao.generate.BatchdownsystransDAO;
import com.csii.upp.dao.generate.EpaybatchcheckerrorDAO;
import com.csii.upp.dao.generate.MeracctinfoDAO;
import com.csii.upp.dao.generate.OnlineorderinfoDAO;
import com.csii.upp.dao.generate.OnlinesubtranshistDAO;
import com.csii.upp.dao.generate.OnlinetranshistDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.CheckFdpsUpdateState;
import com.csii.upp.dto.generate.Batchclearsubtrans;
import com.csii.upp.dto.generate.BatchclearsubtransExample;
import com.csii.upp.dto.generate.Batchcleartrans;
import com.csii.upp.dto.generate.Batchdownsystrans;
import com.csii.upp.dto.generate.BatchdownsystransExample;
import com.csii.upp.dto.generate.Epaybatchcheckerror;
import com.csii.upp.dto.generate.Meracctinfo;
import com.csii.upp.dto.generate.Onlineorderinfo;
import com.csii.upp.dto.generate.Onlinesubtranshist;
import com.csii.upp.dto.generate.OnlinesubtranshistExample;
import com.csii.upp.dto.generate.Onlinetranshist;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 对账数据核对应用调用入口
 * 
 * @author GFQ
 * 
 */
public abstract class BaseCheckFdpsAppl extends BaseAppl {
	/**
	 * 后台交易流水不存在时对账流程
	 * 
	 * @param cleartrans
	 * @param intervalDate
	 * @param clearEAccttrans
	 */
	protected abstract CheckFdpsUpdateState checkBatchDownSysTransNotExist(Batchcleartrans cleartrans,
			Date intervalDate);

	/**
	 * 后台交易流水存在时对账流程
	 * 
	 * @param cleartrans
	 * @param downtrans
	 * @param clearEAccttrans
	 */
	protected abstract CheckFdpsUpdateState checkBatchDownSysTransExist(Batchcleartrans cleartrans,
			Batchdownsystrans downtrans);

	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps) {
		String preCheck = CheckStatus.PRECHECK;
		Date checkDate = this.getCheckDate(argMaps);
		String fundchanneltypcd = StringUtil.parseObjectToString(argMaps.get(Dict.XBBZ));
		// 获取后台交易对账流水列表
		List<Batchdownsystrans> downtranss = BatchdownsystransExtendDAO.getBatchDownsystrans(preCheck);
		// 获取清算明细流水列表
		List<Batchcleartrans> cleartranss = BatchcleartransExtendDAO.getBatchcleartransByCheckStatus(preCheck,
				fundchanneltypcd);
		// 循环取交易流水对账
		if (cleartranss != null && !cleartranss.isEmpty()) {
			for (Batchcleartrans cleartrans : cleartranss) {
				// 是否存在下游交易标志
				boolean isExist = false;
				for (Batchdownsystrans downtrans : downtranss) {
					// 下游交易存在处理
					if (downtrans.getTransseqnbr().equals(cleartrans.getTransseqnbr())) {
						isExist = true;
						CheckFdpsUpdateState fdpsupdateState = this.checkBatchDownSysTransExist(cleartrans, downtrans);
						// 根据计算出的状态更新相应的交易表
						this.fdpsupdateState(fdpsupdateState, cleartrans, downtrans,checkDate);
					}
				}
				// 下游交易不存在处理
				if (!isExist) {
					// 获得对账宽限日
					int waitDays = StringUtil.parseInteger(argMaps.get(Dict.DZKX));
					// 获得intervalDays天前的日期
					Date intervalDate = DateUtil.addDate(checkDate, -waitDays);
					CheckFdpsUpdateState fdpsupdateState = this.checkBatchDownSysTransNotExist(cleartrans, intervalDate);
					// 根据计算出的状态更新相应的交易表
					this.fdpsupdateState(fdpsupdateState, cleartrans, null,checkDate);
				}
			}

		}
		
		// 后续处理
		this.checkAfter(checkDate,fundchanneltypcd);
	}

	private void checkAfter(final Date checkDate,final String fundchanneltypcd) {
		getTransactionTemplate().execute(new TransactionCallback() {
			@Override
			public Object doInTransaction(TransactionStatus arg0) {
				try {
					// step1 已对账的后台交易明细移植到后台交易历史表
					BatchdownsystranshistExtendDAO.insertBatchDownSysTransHist(CheckStatus.PRECHECK);
					// step2 删除移植数据
					BatchdownsystransExample example = new BatchdownsystransExample();
					example.createCriteria().andCheckstatusNotEqualTo(CheckStatus.PRECHECK);
					BatchdownsystransDAO.deleteByExample(example);
					// step3:子交易明细提取到确认支付子交易清算表
					BatchConfirmSubClearTransExtendDAO.insertBatchConfirmSubClearTrans(checkDate,fundchanneltypcd);
				} catch (Exception e) {
					throw new PeRuntimeException(e);
				}
				return null;
			}
		});
	}

	/**
	 * 关键域是否一致
	 * 
	 * @param cleartrans
	 * @param downtrans
	 * @return
	 */
	protected boolean isKeyFieldEqual(Batchcleartrans cleartrans, Batchdownsystrans downtrans) {
		boolean isKeyField = false;
		if (cleartrans.getPayeracctnbr() != null) {
			isKeyField = cleartrans.getTransamt().equals(downtrans.getTransamt())
					&& (cleartrans.getPayeracctnbr().equals(downtrans.getPayeracctnbr()));
		}
		if (cleartrans.getPayeeacctnbr() != null && !isKeyField) {
			isKeyField = cleartrans.getTransamt().equals(downtrans.getTransamt())
					&& (cleartrans.getPayeeacctnbr().equals(downtrans.getPayeeacctnbr()));
		}
		if (!isKeyField) {
			isKeyField = cleartrans.getTransamt().equals(downtrans.getTransamt());
		}
		return isKeyField;
	}

	/**
	 * 根据状态更新相应的交易表
	 * 
	 * @param clearCheckStatus
	 * @param clearTransStatus
	 * @param ProcStep
	 * @param checkErrorType
	 * @param cleartrans
	 * @param downtrans
	 */
	protected void fdpsupdateState(final CheckFdpsUpdateState fdpsupdateState, final Batchcleartrans cleartrans,
			final Batchdownsystrans downtrans,final Date checkdate) {
		if (fdpsupdateState == null) {
			return;
		}
		getTransactionTemplate().execute(new TransactionCallback() {
			@Override
			public Object doInTransaction(TransactionStatus arg0) {
				try {
					String clearCheckStatus = fdpsupdateState.getClearTransCheckStatus();
					String clearTransStatus = fdpsupdateState.getClearTransTransStatus();
					String procStep = fdpsupdateState.getClearProcStep();
					String checkErrorType = fdpsupdateState.getCheckErrorType();
					// 更新清算交易明细流水
					Batchcleartrans record = new Batchcleartrans();
					record.setTransseqnbr(cleartrans.getTransseqnbr());
					record.setCheckstatus(clearCheckStatus);
					record.setTransstatus(clearTransStatus);
					record.setProcstep(procStep);
					record.setCleardate(checkdate);
					BatchcleartransDAO.updateByPrimaryKeySelective(record);
					// 更新总交易明细历史表
					Onlinetranshist transhist = new Onlinetranshist();
					transhist.setTransseqnbr(cleartrans.getTransseqnbr());
					transhist.setCheckstatus(clearCheckStatus);
					transhist.setTransstatus(clearTransStatus);
					transhist.setProcstep(procStep);
					transhist.setCleardate(checkdate);
					//更新下游流水号，避免因超时导致的下游流水号为空  modified by wangtao 20161121
					if(StringUtil.isStringEmpty(cleartrans.getDownsystransnbr())){
						log.debug("交易表下游流水号为空,transseqnbr==="+cleartrans.getTransseqnbr());
						if(TransStatus.SUCCESS.equals(clearTransStatus)){
							transhist.setDownsystransnbr(downtrans.getDownsystransnbr());
						}else{
							if(downtrans!=null){
								transhist.setDownsystransnbr(downtrans.getDownsystransnbr());
							}
						}						
					}

					OnlinetranshistDAO.updateByPrimaryKeySelective(transhist);
					// 更新子订单清算
					Batchclearsubtrans subcleartrans = new Batchclearsubtrans();
					subcleartrans.setTransstatus(clearTransStatus);
					subcleartrans.setProcstep(procStep);
					subcleartrans.setCleardate(checkdate);
					BatchclearsubtransExample example = new BatchclearsubtransExample();
					example.createCriteria().andTransseqnbrEqualTo(cleartrans.getTransseqnbr());
					BatchclearsubtransDAO.updateByExampleSelective(subcleartrans, example);
					// 更新子交易历史明细
					Onlinesubtranshist subtranshist = new Onlinesubtranshist();
					subtranshist.setTransstatus(clearTransStatus);
					subtranshist.setProcstep(procStep);
					subtranshist.setCleardate(checkdate);
					if (TransStatus.SUCCESS.equals(clearTransStatus)) {
						Meracctinfo meracctinfo = MeracctinfoDAO.selectByPrimaryKey(cleartrans.getMernbr());
						if (PayModeCd.DIRECT.equals(meracctinfo.getPaymodecd())) {
							subtranshist.setConfirmedcleardate(checkdate);
						}
					}
					
					//更新下游流水号 ,避免因超时导致的下游流水号为空  modified by wangtao 20161121
					if(downtrans!=null && !StringUtil.isStringEmpty(downtrans.getDownsystransnbr())){
						log.debug("更新子交易表下游流水号,transseqnbr==="+cleartrans.getTransseqnbr());
						subtranshist.setDownsysseqnbr(downtrans.getDownsystransnbr());
					}					
					OnlinesubtranshistExample example1 = new OnlinesubtranshistExample();
					example1.createCriteria().andTransseqnbrEqualTo(cleartrans.getTransseqnbr());
					OnlinesubtranshistDAO.updateByExampleSelective(subtranshist, example1);
					// 更新onlieorderinfo订单状态
					if(TransStatus.SUCCESS.equals(clearTransStatus)){
						Onlineorderinfo onlineorderinfo  = new Onlineorderinfo();
						onlineorderinfo.setMernbr(cleartrans.getMernbr());
						onlineorderinfo.setMerseqnbr(cleartrans.getMerseqnbr());
						onlineorderinfo.setPaystatus(PayStatus.PAY_STATUS_OK);
						log.info("更新transseqnbr=" + cleartrans.getTransseqnbr() + "订单paystatus=" 
								+ onlineorderinfo.getPaystatus() + "，transstatus=" + clearTransStatus);
						OnlineorderinfoDAO.updateByPrimaryKeySelective(onlineorderinfo);
					}else if (TransStatus.FAILURE.equals(clearTransStatus)) {
						Onlineorderinfo onlineorderinfo  = new Onlineorderinfo();
						onlineorderinfo.setMernbr(cleartrans.getMernbr());
						onlineorderinfo.setMerseqnbr(cleartrans.getMerseqnbr());
						onlineorderinfo.setPaystatus(PayStatus.PAY_STATUS_FAIL);
						log.info("更新transseqnbr=" + cleartrans.getTransseqnbr() + "订单paystatus=" 
								+ onlineorderinfo.getPaystatus() + "，transstatus=" + clearTransStatus);
						OnlineorderinfoDAO.updateByPrimaryKeySelective(onlineorderinfo);
					}
					// 更新行内后台交易明细表
					if (downtrans != null) {
						Batchdownsystrans downsystrans = new Batchdownsystrans();
						downsystrans.setDownsystransnbr(downtrans.getDownsystransnbr());
						downsystrans.setCheckstatus(clearCheckStatus);
						BatchdownsystransDAO.updateByPrimaryKeySelective(downsystrans);
					}
					// 插入差错表
					if (checkErrorType != null) {
						Epaybatchcheckerror epayCheckError = new Epaybatchcheckerror();
						epayCheckError.setTransseqnbr(cleartrans.getTransseqnbr());
						epayCheckError.setTransdate(cleartrans.getTransdate());
						epayCheckError.setDownsystransdate(cleartrans.getDownsysdate());
						epayCheckError.setCustcifnbr(cleartrans.getCustcifnbr());
						epayCheckError.setPayeracctnbr(cleartrans.getPayeracctnbr());
						epayCheckError.setPayeraccttypcd(cleartrans.getPayeraccttypcd());
						epayCheckError.setPayeracctdeptnbr(cleartrans.getPayeracctdeptnbr());
						epayCheckError.setPayeracctname(cleartrans.getPayeracctname());
						epayCheckError.setPayeeacctnbr(cleartrans.getPayeeacctnbr());
						epayCheckError.setPayeeaccttypcd(cleartrans.getPayeeaccttypcd());
						epayCheckError.setPayeeacctdeptnbr(cleartrans.getPayeeacctdeptnbr());
						epayCheckError.setPayeeacctname(cleartrans.getPayeeacctname());
						epayCheckError.setPayeracctkind(cleartrans.getPayeracctkind());
						epayCheckError.setPayeeacctkind(cleartrans.getPayeeacctkind());
						epayCheckError.setTranscode(cleartrans.getTranscode());
						epayCheckError.setTransamt(cleartrans.getTransamt());
						epayCheckError.setCurrencycd(cleartrans.getCurrencycd());
						epayCheckError.setTransstatus(clearTransStatus != null ? clearTransStatus : cleartrans.getTransstatus());
						epayCheckError.setCheckerrortyp(checkErrorType);
						epayCheckError.setDepartmentnbr(cleartrans.getDepartmentnbr());
						EpaybatchcheckerrorDAO.insertSelective(epayCheckError);
					}
				} catch (Exception e) {
					throw new PeRuntimeException(e);
				}
				return null;
			}
		});
	}
}
