package com.csii.upp.payment.action.mgmt;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.InnerCardFlag;
import com.csii.upp.constant.SendStatus;
import com.csii.upp.dao.generate.OnlinesmsinfoDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Onlinesmsinfo;
import com.csii.upp.dto.generate.OnlinesmsinfoExample;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.DateUtil;

/**
 * 短信验证码验证
 * 
 * @auther fgq email:f_xust@163.com
 * @version 创建时间：2016年2月24日 上午9:21:12
 * 
 */
public class ValidateSmsAction extends PaymentOnlineAction {

	private long smsTimeOut;

	public void setSmsTimeOut(long smsTimeOut) {
		this.smsTimeOut = smsTimeOut;
	}

	@Override
	public void execute(Context context) throws PeException {
		InputPaymentData input = new InputPaymentData(context.getDataMap());
		// 判断卡类型
		queryCardType(input);
		if(InnerCardFlag.InnerCardFlag_other.equals(input.getInnerCardFlag())){
			this.checkSmsCode(context,smsTimeOut);
			return;
		}
//		RespQueryCardInfo hostInfo = (RespQueryCardInfo) getFDPSService().queryCardInfo(input);
		this.checkSmsCode(context,smsTimeOut);
		context.setData(Dict.PAYER_CARD_TYP_CD, input.getPayercardtypcd());
		/*context.setData(Dict.CUST_CIF_NBR, hostInfo.getCustCifNbr());
		context.setData(Dict.PAYER_ACCT_DEPT_NBR,hostInfo.getPayerAcctDeptNbr());
		context.setData(Dict.PAYER_ACCT_STATUS,hostInfo.getPayerAcctStatus());
		context.setData(Dict.ACCT_LOSS_STATUS,hostInfo.getAcctLossStatus());
		context.setData(Dict.ACCT_STATUS_WORD,hostInfo.getAcctStatusWord());
		context.setData(Dict.CARD_STATUS_WORD,hostInfo.getCardStatusWord());
		context.setData(Dict.CARD_STATUS,hostInfo.getCardStatus());*/
		
		context.setData(Dict.CUST_CIF_NBR, "10133060219861020152X");
		context.setData(Dict.PAYER_ACCT_DEPT_NBR,"891010");
		context.setData(Dict.PAYER_ACCT_STATUS,"1");
		context.setData(Dict.ACCT_LOSS_STATUS,"1");
		context.setData(Dict.ACCT_STATUS_WORD,"0011000000000000");
		context.setData(Dict.CARD_STATUS_WORD,"0011000000000000");
		context.setData(Dict.CARD_STATUS,"1");
	}
	
	/**
	 * 验证短信码
	 * @param phoneNo
	 * @param acctNbr
	 * @param tranType
	 * @param smsCode
	 * @param timeOut
	 * @throws PeException
	 */
	protected void checkSmsCode(Context context,long smsTimeOut) throws PeException{
		String phoneNo = context.getString(Dict.PAYER_PHONE_NO);
		String acctNbr = context.getString(Dict.PAYER_ACCT_NBR);
		String tranType = context.getString(Dict.TRANS_TYP_CD);
		String smsCode = context.getString(Dict.SMS_CODE);
		String smsSqenbr = context.getString(Dict.SMS_SQENBR);
		Timestamp transTime = new Timestamp(DateUtil.getCurrentDateTime().getTime());
		Timestamp beginTime = new Timestamp(transTime.getTime() - smsTimeOut);
		/*
		 * 查询短信验证码表
		 */
		OnlinesmsinfoExample example = new OnlinesmsinfoExample();
		example.createCriteria().andAcctnbrEqualTo(acctNbr)
								.andTranstimeBetween(beginTime, transTime)
								.andPhoneEqualTo(phoneNo)
								.andTranscodeEqualTo(tranType)
								.andSendstatusEqualTo(SendStatus.SUCCESS)
								.andSeqnbrEqualTo(smsSqenbr);
		try {
			List<Onlinesmsinfo> smsList = OnlinesmsinfoDAO.selectByExample(example);
			if(smsList.size() < 1)
			{
				throw new PeException(DictErrors.INVALID_SMS_CODE);
			}
			if(smsList.size() > 1)
			{
				throw new PeException(DictErrors.SMS_CODE_SYS_EXCEPTION);
			}
			
			/* 修改已验证短信状态*/
			 
			Onlinesmsinfo sms = smsList.get(0);
			sms.setSendstatus(SendStatus.DISABLED);
			if(sms!=null&&!sms.getAcctnbr().equals(context.getString(Dict.PAYER_ACCT_NBR)))
				throw new PeException(DictErrors.CARD_SMS_ERROR);
			OnlinesmsinfoDAO.updateByExampleSelective(sms, example);
			if(!smsCode.equals(sms.getCode()))
			{
				throw new PeException(DictErrors.INPUT_SMS_CODE_ERROR);
			}						
			
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
	}
	

}
