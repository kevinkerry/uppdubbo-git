package com.csii.upp.service.fundprocess;

import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.PeException;
import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.constant.CheckStatus;
import com.csii.upp.constant.ErrorState;
import com.csii.upp.constant.ErrorTyp;
import com.csii.upp.constant.ExpHandleState;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.InnerRtxnTyp;
import com.csii.upp.constant.InteralFlag;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.extend.BatchcheckerrorExtendDAO;
import com.csii.upp.dao.extend.InnerfundtransHistExtendDAO;
import com.csii.upp.dao.generate.InnerfundtransDAO;
import com.csii.upp.dao.generate.InnerfundtranshistDAO;
import com.csii.upp.dao.generate.OveralltransDAO;
import com.csii.upp.dao.generate.OveralltranshistDAO;
import com.csii.upp.dao.generate.TransexceptionregDAO;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Batchcheckerror;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.dto.generate.InnerfundtransExample;
import com.csii.upp.dto.generate.Innerfundtranshist;
import com.csii.upp.dto.generate.Overalltrans;
import com.csii.upp.dto.generate.Overalltranshist;
import com.csii.upp.dto.generate.Transexceptionreg;
import com.csii.upp.dto.router.ReqSysHead;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.service.BasePayService;
import com.csii.upp.service.exception.fundction.RtxnExceptionFunction;
import com.csii.upp.supportor.DefaultSupportor;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.BeanUtils;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 路由通道服务
 * 
 * @author 徐锦
 * 
 */
public abstract class RouterService extends BasePayService {

	protected void formatException(RespSysHead output) throws PeException {
		if (output.isTimeout()) {
			throw new PeException(DictErrors.TRANS_TIMEOUT);
		} else {
			throw new PeException(DictErrors.DOWN_SYS_ERROR,
					new Object[] { output.getFundchannelcd(),
							output.getReturncode() == null ? ResponseCode.FAILURE : output.getReturncode(),
							output.getReturnmsg() });
		}
	}

	/**
	 * 发送资金交易到目的系统
	 * 
	 * @param inputData
	 * @param inputObj
	 * @param outputClazz
	 * @return
	 * @throws PeException
	 */
	protected <T extends RespSysHead> T handleFundRtxn(String innerRtxnTypCd, InputFundData input, ReqSysHead inputObj,
			Class<T> outputClazz) throws PeException {
		input.setTranscode(innerRtxnTypCd);
		insertInnerfundtrans(input);
		T output = send(inputObj, outputClazz);
		updateInnerfundtrans(input, output);
		return output;
	}

	/**
	 * 发送非资金交易到目的系统
	 * 
	 * @param inputData
	 * @param inputObj
	 * @param outputClazz
	 * @return
	 * @throws PeException
	 */
	protected <T extends RespSysHead> T handleNonFundRtxn(ReqSysHead inputObj, Class<T> outputClazz)
			throws PeException {
		return send(inputObj, outputClazz);
	}

	/**
	 * 初始化资金流水
	 * 
	 * @param input
	 */
	protected Innerfundtrans initInnerfundtrans(InputFundData input) {

		Innerfundtrans record = new Innerfundtrans();
		record.setInnerfundtransnbr(input.getInnerfundtransnbr());
		record.setPayeracctnbr(input.getPayeracctnbr());
		record.setPayeeacctnbr(input.getPayeeacctnbr());
		record.setOveralltransnbr(input.getOveralltransnbr());
		record.setFundchannelcode(input.getFundchannelcode());
		record.setUppersysnbr(input.getUppersysnbr());
		record.setUppertransdate(input.getUppertransdate());
		record.setUppertranstime(input.getUppertranstime());
		record.setUppertransnbr(input.getUppertransnbr());
		record.setTransdate(input.getTransdate());
		record.setTranscode(input.getTranscode());
		record.setPayername(input.getPayername());
		record.setPayeename(input.getPayeename());
		record.setCurrencycd(input.getCurrencycd());
		record.setTransamt(input.getTransamt());
		record.setTranstime(input.getTranstime());
		record.setFeeamt(input.getFeeamt());
		record.setOriginnertransnbr(input.getOriginnertransnbr());
		record.setTransstatus(TransStatus.INIT);
		record.setCheckstatus(CheckStatus.Init);
		if(record.getTranstime() == null){
			record.setTranstime(DateUtil.getCurrentDateTime());
		}
		record.setCheckdataflag(input.getCheckdataflag());
		record.setPointdataflag(InteralFlag.YES.equals(input.getPointdataflag())?InteralFlag.YES:InteralFlag.NO);
		return record;
	}

	/**
	 * 插入资金流水
	 * 
	 * @param input
	 */
	protected Innerfundtrans insertInnerfundtrans(InputFundData input) {
		// 初始化资金流水
		Innerfundtrans funRtxn = this.initInnerfundtrans(input);
		this.insertInnerfundtrans(funRtxn);
		input.setInnerfundtransnbr(funRtxn.getInnerfundtransnbr());
		input.setTransnbr(funRtxn.getInnerfundtransnbr());
		return funRtxn;
	}

	/**
	 * 插入资金流水
	 * 
	 * @param input
	 */
	protected Innerfundtrans insertInnerfundtrans(Innerfundtrans funRtxn) {

		try {
			InnerfundtransDAO.insert(funRtxn);
		} catch (SQLException e) {
			throw new PeRuntimeException("Insert Innerfundtrans Table Failed.", e);
		}
		return funRtxn;
	}

	/**
	 * 更新资金流水
	 * 
	 * @param input
	 */
	protected void updateInnerfundtrans(InputFundData input, RespSysHead output) {
		Innerfundtrans fundRtxn = new Innerfundtrans();
		fundRtxn.setInnerfundtransnbr(input.getInnerfundtransnbr());
		fundRtxn.setCheckstatus(input.getCheckstatus());
		fundRtxn.setDowntransnbr(output.getDownrtxnnbr());
		fundRtxn.setDowntransdate(output.getDownrtxndate());
		fundRtxn.setCleardate(output.getDownrtxndate());
		fundRtxn.setDowntranstime(output.getDownrtxntime());
		fundRtxn.setReturncode(output.getReturncode());
		fundRtxn.setReturnmsg(output.getReturnmsg());
		fundRtxn.setTransstatus(output.getRtxnstate());
		fundRtxn.setOriginnertransnbr(input.getOriginnertransnbr());
		fundRtxn.setDownsysnbr(output.getDownsysnbr());
		this.updateInnerfundtrans(fundRtxn);
	}

	/**
	 * 更新资金流水
	 * 
	 * @param input
	 */
	protected void updateInnerfundtrans(Innerfundtrans fundRtxn) {
		try {
			if (InnerfundtransDAO.updateByPrimaryKeySelective(fundRtxn) == 0) {
				throw new PeRuntimeException("Update Innerfundtrans Failed for unknown reason.");
			}
		} catch (SQLException e) {
			throw new PeRuntimeException("Update Innerfundtrans Table Failed.", e);
		}
	}
	/**
	 * 积分消费超时更新原总资金流水
	 * 
	 * @param input
	 * @throws PeException 
	 */
	protected void updateOveralltrans(InputFundData input) throws PeException {
		if (!StringUtil.isStringEmpty(input.getOveralltransnbr())) {
			// 更新总交易流水状态
			Overalltrans record = new Overalltrans();
			record.setOveralltransnbr(input.getOveralltransnbr());
			record.setOveralltransstatus(TransStatus.FAILURE);
			try {
				OveralltransDAO.updateByPrimaryKeySelective(record);
			} catch (SQLException e) {
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
			input.setTransstatus(TransStatus.FAILURE);
			((PaymService) DefaultSupportor.getService(FundChannelCode.PAYM.toLowerCase())).notifyPayResult(input);
		}
	}
	/**
	 * 撤销成功更新原消费资金流水
	 * 
	 * @param input
	 */
	protected void updateIntegalInnerfundtrans(InputFundData input) {
		Innerfundtrans fundRtxn = new Innerfundtrans();
		fundRtxn.setInnerfundtransnbr(input.getOriginnertransnbr());
		fundRtxn.setTransstatus(input.getTransstatus());
		this.updateInnerfundtrans(fundRtxn);
	}
	/**
	 * 资金支付成功更新积分清算日期
	 * 
	 * @param input
	 */
	protected void updateIntegalClearDate(InputFundData input,RespSysHead output) {
		Innerfundtrans fundRtxn = new Innerfundtrans();
		fundRtxn.setInnerfundtransnbr(input.getOriginnertransnbr());
		fundRtxn.setCleardate(output.getDownrtxndate());
		this.updateInnerfundtrans(fundRtxn);
	}
	/**
	 * 撤销成功更新消费资金流水
	 * 
	 * @param input
	 */
	protected void updateIntegalCancelInnerfundtrans(InputFundData input, RespSysHead output) {
		Innerfundtrans fundRtxnOrig = new Innerfundtrans();
		fundRtxnOrig.setInnerfundtransnbr(input.getOriginnertransnbr());
		fundRtxnOrig.setTransstatus(input.getTransstatus());
		if(!StringUtil.isStringEmpty(input.getTransstatus())){
			this.updateInnerfundtrans(fundRtxnOrig);
		}
		Innerfundtrans fundRtxn = new Innerfundtrans();
		fundRtxn.setInnerfundtransnbr(input.getNote());
		fundRtxn.setTransstatus(output.getRtxnstate());
		this.updateInnerfundtrans(fundRtxn);
	}
	/**
	 * 获取原消费积分资金流水
	 * 
	 * @param input
	 * @throws PeException 
	 */
	protected InputFundData getOrigInnerfundtrans(InputFundData input) throws PeException {
		// 获取原资金流水
		String oveAllTransNbr=input.getOveralltransnbr();
		List<Innerfundtrans> innerfundtrans=null;
		if(!StringUtil.isStringEmpty(oveAllTransNbr)){
			InnerfundtransExample example=new InnerfundtransExample();
			example.createCriteria().andOveralltransnbrEqualTo(oveAllTransNbr).andTranscodeEqualTo(InnerRtxnTyp.PointConsume);
			try {
				innerfundtrans= InnerfundtransDAO.selectByExample(example);
			} catch (SQLException e) {
				throw new PeException(DictErrors.DATABASE_EXCEPTION);
			}
			if(innerfundtrans.size()>0){
				input.setInteralFlag(InteralFlag.YES);
				Innerfundtrans funRtxn=innerfundtrans.get(0);
				input.setOriginnertransnbr(funRtxn.getInnerfundtransnbr());
				input.setBranchNo(funRtxn.getPayeeacctnbr());
			}else{
				input.setInteralFlag(InteralFlag.NO);
			}
		}else{
				throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		return input;
	}
	protected InputFundData getPointOrigInnerfundtrans(InputFundData input) throws PeException {
		// 获取原资金流水
		String innerFundTransNbr=input.getInnerfundtransnbr();
		List<Innerfundtrans> innerfundtrans=null;
		List<Innerfundtrans> orgiInnerfundtrans=null;
		if(!StringUtil.isStringEmpty(innerFundTransNbr)){
			InnerfundtransExample example=new InnerfundtransExample();
			example.createCriteria().andInnerfundtransnbrEqualTo(innerFundTransNbr);
			try {
				innerfundtrans= InnerfundtransDAO.selectByExample(example);
			} catch (SQLException e) {
				throw new PeException(DictErrors.DATABASE_EXCEPTION);
			}
			if(innerfundtrans.size()>0){
				input.setInteralFlag(InteralFlag.YES);
				Innerfundtrans funRtxn=innerfundtrans.get(0);
				if(InnerRtxnTyp.PointConsume.equals(funRtxn.getTranscode())){
					input.setOriginnertransnbr(funRtxn.getInnerfundtransnbr());
					input.setBranchNo(funRtxn.getPayeeacctnbr());
				}else if(InnerRtxnTyp.PointCancel.equals(funRtxn.getTranscode())){
					input.setOriginnertransnbr(funRtxn.getOriginnertransnbr());
					input.setNote(funRtxn.getInnerfundtransnbr());
					input.setBranchNo(funRtxn.getPayeracctnbr());
				}else if(InnerRtxnTyp.CoreExWipeout.equals(funRtxn.getTranscode())){
					input.setOriginnertransnbr(funRtxn.getOriginnertransnbr());
					input.setNote(funRtxn.getInnerfundtransnbr());
					input.setTransamt(funRtxn.getTransamt());
					InnerfundtransExample exampleOrig=new InnerfundtransExample();
					exampleOrig.createCriteria().andInnerfundtransnbrEqualTo(funRtxn.getOriginnertransnbr());
					try {
						orgiInnerfundtrans= InnerfundtransDAO.selectByExample(exampleOrig);
					} catch (SQLException e) {
						throw new PeException(DictErrors.DATABASE_EXCEPTION);
					}
					Innerfundtrans funRtxnOrig=orgiInnerfundtrans.get(0);
					input.setDowntransnbr(funRtxnOrig.getDowntransnbr());
				}
			}else{
				input.setInteralFlag(InteralFlag.NO);
			}
		}else{
				throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		return input;
	}
	/**
	 * 获取原抵扣消费资金流水
	 * 
	 * @param input
	 * @throws PeException 
	 */
	protected InputFundData getOrigIntegalfundtrans(InputFundData input) throws PeException {
		// 获取原资金流水
		String oveAllTransNbr=input.getOveralltransnbr();
		List<Innerfundtrans> innerfundtrans=null;
		if(!StringUtil.isStringEmpty(oveAllTransNbr)){
			InnerfundtransExample example=new InnerfundtransExample();
			example.createCriteria().andOveralltransnbrEqualTo(oveAllTransNbr).andTranscodeEqualTo(InnerRtxnTyp.CoreIntegalPay);
			try {
				innerfundtrans= InnerfundtransDAO.selectByExample(example);
			} catch (SQLException e) {
				throw new PeException(DictErrors.DATABASE_EXCEPTION);
			}
			if(innerfundtrans.size()>0){
				input.setInteralFlag(InteralFlag.YES);
				Innerfundtrans funRtxn=innerfundtrans.get(0);
				input.setOriginnertransnbr(funRtxn.getInnerfundtransnbr());
				input.setTransamt(funRtxn.getTransamt());
				if(StringUtil.isStringEmpty(input.getDowntransnbr())){
					input.setDowntransnbr(funRtxn.getDowntransnbr());
				}
			}else{
				input.setInteralFlag(InteralFlag.NO);
			}
		}else{
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		return input;
	}
	protected void insertTransexceptionreg(InputFundData input, RtxnExceptionFunction ex) {
		Transexceptionreg record = new Transexceptionreg();
		record.setOveralltransnbr(input.getOveralltransnbr());
		record.setInnerfundtransnbr(input.getInnerfundtransnbr());
		record.setTransdate(input.getTransdate());
		record.setTranstime(input.getTranstime());
		record.setExphandlestatus(ExpHandleState.PREHANDLE);
		record.setRetrytimes(0L);
		record.setMaxhandletimes(this.getExpHandleMaxTimes());
		record.setExptransdatasnap(BeanUtils.beanToXmlString(input));
		record.setFundchannelcode(ex.getFundChanelCd());
		record.setExphandletranscode(ex.getRtxnCode());
		record.setExphandlertransdesc(ex.getRtxnDesc());
		try {
			record.setExpseqnbr(IDGenerateFactory.generateSeqId());
			TransexceptionregDAO.insert(record);
		} catch (SQLException e) {
			throw new PeRuntimeException("Insert Transexceptionreg Table Failed.", e);
		}
	}
	
	// / 重构原insertTransexceptionreg方法，由交易场景自己构造交易快照
	protected void insertTransexceptionreg(InputFundData input, String Exprtxndatasnap, RtxnExceptionFunction ex) {
		Transexceptionreg record = new Transexceptionreg();
		record.setOveralltransnbr(input.getOveralltransnbr());
		record.setInnerfundtransnbr(input.getInnerfundtransnbr());
		record.setTransdate(input.getTransdate());
		record.setTranstime(input.getTranstime());
		record.setExphandlestatus(ExpHandleState.PREHANDLE);
		record.setRetrytimes(0L);
		record.setMaxhandletimes(this.getExpHandleMaxTimes());
		record.setExptransdatasnap(Exprtxndatasnap);
		record.setFundchannelcode(ex.getFundChanelCd());
		record.setExphandletranscode(ex.getRtxnCode());
		record.setExphandlertransdesc(ex.getRtxnDesc());
		record.setCheckdataflag(input.getCheckdataflag());
		try {
			record.setExpseqnbr(IDGenerateFactory.generateSeqId());
			TransexceptionregDAO.insert(record);
		} catch (SQLException e) {
			throw new PeRuntimeException("Insert Transexceptionreg Table Failed.", e);
		}
	}

	protected void insertBatchcheckerror(InputFundData input, RespSysHead output) {
		Batchcheckerror checkError = new Batchcheckerror();
		checkError.setCheckerrortyp(ErrorTyp.ARTPROCESS);
		checkError.setTransstatus(output.getRtxnstate());
		checkError.setErrorstatus(ErrorState.PRECHECK);
		checkError.setCheckstatus(CheckStatus.UNCHECKED);
		checkError.setInnerfundtransnbr(input.getInnerfundtransnbr());
		checkError.setTransdate(input.getTransdate());
		checkError.setTranscode(input.getTranscode());
		checkError.setPayeeacctnbr(input.getPayeeacctnbr());
		checkError.setPayeename(input.getPayeename());
		checkError.setPayeracctnbr(input.getPayeracctnbr());
		checkError.setPayername(input.getPayername());
		checkError.setTransamt(input.getTransamt());
		checkError.setCurrencycd(input.getCurrencycd());
		checkError.setFundchannelcode(input.getFundchannelcode());
		BatchcheckerrorExtendDAO.insertBatchcheckerror(checkError);
	}

	/*
	 * 获取原交易流水号
	 */
	protected String setOriginnertransnbrByinnerNbr(String innerRtxnnbr) {
		return InnerfundtransHistExtendDAO.getOrigInnerRtxnNbrByinner(innerRtxnnbr);
	}

	/*
	 * 修改原交易流水号和总流水状态
	 */
	protected void updateOrigInnerRtxnNbr(String innerRtxnNbr, String OveralltransNbr) {
		String origInnerRtxnNbr = setOriginnertransnbrByinnerNbr(innerRtxnNbr);
		Innerfundtranshist hist = new Innerfundtranshist();
		hist.setInnerfundtransnbr(innerRtxnNbr);
		hist.setOriginnertransnbr(origInnerRtxnNbr);
		Overalltranshist overhist = new Overalltranshist();
		overhist.setOveralltransnbr(OveralltransNbr);
		overhist.setOveralltransstatus(TransStatus.SUCCESS);
		try {
			InnerfundtranshistDAO.updateByPrimaryKeySelective(hist);
			OveralltranshistDAO.updateByPrimaryKeySelective(overhist);
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}
}
