package com.csii.upp.fundprocess.action.common;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.CheckStatus;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.InteralFlag;
import com.csii.upp.constant.OveralltransTyp;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.generate.OveralltransDAO;
import com.csii.upp.dao.generate.UppersysinfoDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.generate.Overalltrans;
import com.csii.upp.dto.generate.OveralltransExample;
import com.csii.upp.dto.generate.Uppersysinfo;
import com.csii.upp.fundprocess.action.PayOnlineAction;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.BeanUtils;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 输入交易通用处理
 * 
 * @author 徐锦
 * 
 */
public class InputAction extends PayOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		context.setData(Dict.TRANS_ID, context.getTransactionId());
		String reqTime=context.getString(Dict.REQ_TIME);
		context.setData(Dict.REQ_DATE, reqTime.substring(0, 8));
		validateUpperSysInfo(context);
		validateTransData(context);
		insertTransData(context);
	}

	/**
	 * 插入交易流水
	 * 
	 * @param context
	 * @throws PeException
	 */
	private void insertTransData(Context context) throws PeException {
		try {
			Overalltrans record = new Overalltrans();
			/**
			 * 当交易码为STXN时做转换用于对账 author:xujin
			 */
			String OveralltransTypCd = context.getString(Dict.TRANS_ID);
			String upperSysNbr = context.getString(Dict.CHNL_ID);
			if (OveralltransTyp.STXN.equals(OveralltransTypCd)) {
				if (FundChannelCode.BEPS.equals(upperSysNbr)) {
					OveralltransTypCd = OveralltransTyp.STXNBEPS;
				} else if (FundChannelCode.IBPS.equals(upperSysNbr)) {
					OveralltransTypCd = OveralltransTyp.STXNIBPS;
				} else if (FundChannelCode.HVPS.equals(upperSysNbr)) {
					OveralltransTypCd = OveralltransTyp.STXNHVPS;
				} else if (FundChannelCode.DPC.equals(upperSysNbr)) {
					OveralltransTypCd = OveralltransTyp.STXNDPC;
				}
			}
			//临时加的，后期优化
			if(OveralltransTyp.UPAY.equals(OveralltransTypCd) && "6227181050008888".equals(context.getString(Dict.PAYER_ACCT_NBR))){
				//单笔代收
				OveralltransTypCd="SGCT";
			}else if(OveralltransTyp.BTTF.equals(OveralltransTypCd) && "6227181050008888".equals(context.getString(Dict.PAYER_ACCT_NBR))){
				//单笔代发
				OveralltransTypCd="SGPM";
			}
			
			record.setOveralltranstypcd(OveralltransTypCd);
			String postDate =context.getString(Dict.POST_DATE);
			if(StringUtil.isStringEmpty(postDate)){
				record.setTransdate(this.getPostDate());
				context.setData(Dict.POST_DATE, DateUtil.Date_To_DateFormat(record.getTransdate()));
			}else{
				record.setTransdate(DateUtil.DateFormat_To_Date(postDate));
			}
			record.setTranstime(DateUtil.getCurrentDateTime());
			record.setOveralltransstatus(TransStatus.INIT);
			record.setUppertransnbr(context.getString(Dict.REQ_SEQ_NO));
			record.setUppertransdate(DateUtil.DateFormat_To_Date(context.getString(Dict.REQ_DATE)));
			record.setUppertranstime(DateUtil.DateTimeFormat_To_Date(context.getString(Dict.REQ_TIME)));
			record.setUppersysnbr(upperSysNbr);
			record.setOrigoveralltransnbr(context.getString(Dict.ORIG_OVERRALL_TRANS_NBR));
			record.setTransamt(new BigDecimal(context.getString(Dict.TRANS_AMT)));
			record.setPayeeacctnbr(context.getString(Dict.PAYEE_ACCT_NBR));
			record.setPayeeacctname(context.getString(Dict.PAYEE_ACCT_NAME));
			record.setPayeracctnbr(context.getString(Dict.PAYER_ACCT_NBR));
			record.setPayeracctname(context.getString(Dict.PAYER_ACCT_NAME));
			record.setCheckstatus(CheckStatus.Init);
			record.setCurrencycd(context.getString(Dict.CURRENCY_CD));
			record.setPayeeacctlist(BeanUtils.beanToXmlString(context.getData(Dict.PAYEE_ACCT_LIST)));
			record.setPointdataflag((InteralFlag.YES.equals(context.getString(Dict.INTERAL_FLAG))?InteralFlag.YES:InteralFlag.NO));
	    	record.setOveralltransnbr(IDGenerateFactory.generateRtxnNbr());
			OveralltransDAO.insertSelective(record);
			context.setData(Dict.OVERALL_TRANS_NBR, record.getOveralltransnbr());
			context.setData(Dict.TRAN_TIME, record.getTranstime());
		} catch (Exception e) {
			throw new PeException(DictErrors.DATABASE_EXCEPTION, e);
		}
	}

	/**
	 * 流水信息校验，防止重复交易
	 * 
	 * @param context
	 * @throws PeException
	 */
	private void validateTransData(Context context) throws PeException {
		OveralltransExample example = new OveralltransExample();

		example.createCriteria().andUppertransnbrEqualTo(context.getString(Dict.REQ_SEQ_NO))
				.andUppertransdateEqualTo(DateUtil.DateFormat_To_Date(context.getString(Dict.REQ_DATE)))
				/**
				 * 上游系统发过来的流水号、交易日期和渠道号就可以确定唯一性，
				 * 这里去掉交易时间是因为冲正交易通过原流水号、原交易日期和原渠道号就可以确定唯一 author:xujin
				 */
				// .andUpperrtxntimeEqualTo(
				// DateUtil.DateTimeFormat_To_Date(context
				// .getString(Dictionary.UpperRtxnTime)))
				.andUppersysnbrEqualTo(context.getString(Dict.CHNL_ID));
		try {
			List<Overalltrans> Overalltranss = OveralltransDAO.selectByExample(example);
			if (Overalltranss.size() > 1) {
				throw new PeException(DictErrors.DUPLICATED_REQ);
			}
		} catch (SQLException e) {
			throw new PeException(DictErrors.DATABASE_EXCEPTION, e);
		}
	}

	/**
	 * 上游系统校验
	 * 
	 * @param context
	 * @throws PeException
	 */
	private void validateUpperSysInfo(Context context) throws PeException {
		try {
			String upperSysNbr = context.getString(Dict.CHNL_ID);
			Uppersysinfo result = UppersysinfoDAO.selectByPrimaryKey(upperSysNbr);
			if (result == null) {
				throw new PeException(DictErrors.UPPER_SYS_NBR_INVALID);
			}
		} catch (SQLException e) {
			throw new PeException(DictErrors.DATABASE_EXCEPTION, e);
		}
	}
}