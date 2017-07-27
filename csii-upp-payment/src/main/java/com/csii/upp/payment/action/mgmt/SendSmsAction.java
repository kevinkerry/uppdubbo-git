package com.csii.upp.payment.action.mgmt;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.pe.service.comm.Transport;
import com.csii.upp.constant.DEPDBNBR;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.constant.SendStatus;
import com.csii.upp.dao.generate.DeptinfoDAO;
import com.csii.upp.dao.generate.OnlinesmsinfoDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.generate.Deptinfo;
import com.csii.upp.dto.generate.Onlinesmsinfo;
import com.csii.upp.dto.generate.OnlinesmsinfoExample;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.DateUtil;

/**
*  发送短信内容到短信平台
* @auther fgq Email:f_xust@163.com
* @version 创建时间：2016年2月18日 下午7:51:42
* 
*/
public class SendSmsAction extends PaymentOnlineAction{
	private Transport transport;
	public void setTransport(Transport transport) {
		this.transport = transport;
	}
	
	private String encoding;

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
	private long validateTime;

	public void setValidateTime(long validateTime) {
		this.validateTime = validateTime;
	}

	@Override
	public void execute(Context context) throws PeException {
		this.initSms(context);
		this.preSendSms(context);
		this.sendSms(context);		
	}
	/**
	 * 短信初始化
	 * @param context
	 * @throws PeException
	 * @throws SQLException 
	 */
	@SuppressWarnings("unused")
	private void initSms(Context context) throws PeException
	{
		String phone = (String) context.getData(Dict.PAYER_PHONE_NO);
		String amount = (String) context.getData(Dict.TRANS_AMT);
		String transType = (String) context.getData(Dict.TRANS_TYP_CD);
		String smscode = this.getRandom();
		String deptnbr = context.getString(Dict.PAYER_ACCT_DEPT_NBR);
		StringBuilder smsContent = new StringBuilder();
		
		String payAcctNo = "";
		String deptName = "";
		if(!DEPDBNBR.ZJRCU.equals(deptnbr)){
			if(deptnbr!=null&&!"".equals(deptnbr.trim())&&!deptnbr.endsWith("000"))
				deptnbr = deptnbr.substring(0, deptnbr.length()-3)+"000";
			Deptinfo selectByPrimaryKey = null;
			try {
				selectByPrimaryKey = DeptinfoDAO.selectByPrimaryKey(deptnbr);
			} catch (SQLException e) {
				throw new PeException(e);
			}
			if(selectByPrimaryKey!=null)
			{
				deptName = selectByPrimaryKey.getDeptname();
			}
			if(selectByPrimaryKey==null){
				throw new PeException(DictErrors.DEPTNOTEXISTS);
			}
		}else{
			deptName="浙江农信";
		}
		
		if(PaymTransCode.PayTrans.equals(transType))
		{
			payAcctNo = (String) context.getData(Dict.PAYER_ACCT_NBR);
			smsContent.append(deptName + "提醒：您正在付款，验证码为：").append(smscode)
			.append("，付款卡尾号：")
			.append(payAcctNo.substring(payAcctNo.length() - 4))
			.append(",金额：").append(amount).append("，请勿泄露。");
		}
		else if(PaymTransCode.AddSignInfo.equals(transType))
		{
			payAcctNo = (String) context.getData(Dict.PAYER_ACCT_NBR);
			smsContent.append(deptName + "提醒：您尾号为")
			.append(payAcctNo.substring(payAcctNo.length() - 4))
			.append("的卡申请开通丰收e支付，验证码为：").append(smscode)
			.append("，请勿泄露。");
		}
		else if (PaymTransCode.QuerySignInfoList.equals(transType)) 
		{
			payAcctNo = (String) context.getData(Dict.PAYER_ACCT_NBR);
			smsContent.append(deptName + "提醒：您尾号为")
					.append(payAcctNo.substring(payAcctNo.length() - 4))
					.append("的卡申请查询丰收e支付交易明细").append("，验证码为：")
					.append(smscode).append("，请勿泄露。");
		} else if (PaymTransCode.HoldAcctStatus.equals(transType)) 
		{
			payAcctNo = (String) context.getData(Dict.PAYER_ACCT_NBR);
			smsContent.append(deptName + "提醒：您尾号为")
					.append(payAcctNo.substring(payAcctNo.length() - 4))
					.append("的卡申请冻结丰收e支付").append("，验证码为：")
					.append(smscode).append("，请勿泄露。");
		}else if (PaymTransCode.UpdateTransLimit.equals(transType)) 
		{			
			payAcctNo = (String) context.getData(Dict.PAYER_ACCT_NBR);
			smsContent.append(deptName + "提醒：您尾号为")
					.append(payAcctNo.substring(payAcctNo.length() - 4))
					.append("的卡申请调整丰收e支付交易限额").append("，验证码为：")
					.append(smscode).append("，请勿泄露。");
		} else if (PaymTransCode.UnHoldAcctStatus.equals(transType)) 
		{			
			payAcctNo = (String) context.getData(Dict.PAYER_ACCT_NBR);
			smsContent.append(deptName + "提醒：您尾号为")
					.append(payAcctNo.substring(payAcctNo.length() - 4))
					.append("的卡申请解冻丰收e支付").append("，验证码为：").append(smscode)
					.append("，请勿泄露。");
		} else if (PaymTransCode.CancelSignStatus.equals(transType)) {
			payAcctNo = (String) context.getData(Dict.PAYER_ACCT_NBR);			
			smsContent.append(deptName + "提醒：您尾号为")
					.append(payAcctNo.substring(payAcctNo.length() - 4))
					.append("的卡申请注销丰收e支付").append("，验证码为：").append(smscode)
					.append("，请勿泄露。");
		}else if("RegisterAndEpay".equals(transType)){
			payAcctNo = (String) context.getData(Dict.PAYER_ACCT_NBR);
			smsContent.append(deptName + "提醒：")
			.append("验证码为：")
			.append(smscode)
			.append("，请勿泄露。")
			.append("您尾号为")
			.append(payAcctNo.substring(payAcctNo.length() - 4))
			.append("的卡正在丰收e支付注册并支付，金额：")
			.append(amount)
			.append("。");

		}
		context.setData(Dict.SMS_CODE, smscode);
		context.setData(Dict.SMS_CONTENT, smsContent.toString());
		log.info("短信验证码为"+smscode);
	}
	
	/**
	 * 短信发送检查
	 * @param context
	 * @throws PeException
	 */
	@SuppressWarnings("unchecked")
	private void preSendSms(Context context) throws PeException
	{		
		String acctNbr = (String) context.getData(Dict.PAYER_ACCT_NBR);
		String phoneNo = (String) context.getData(Dict.PAYER_PHONE_NO);
		String transCode = (String) context.getData(Dict.TRANS_TYP_CD);
		String smsCode = (String) context.getData(Dict.SMS_CODE);
		String seqNbr = (String) IDGenerateFactory.generateSeqId();
		Timestamp transDateTime = new Timestamp(DateUtil.getCurrentDateTime().getTime());
		context.setData(Dict.TRAN_TIME,transDateTime);
		Date transData = DateUtil.getDate();		
		Timestamp beginTime = this.rolTimestamp(transDateTime, validateTime);		
		/*
		 * 检查发送短信验证是否在有效期内
		 */		
		List status = new ArrayList();
		status.add(SendStatus.UNSEND);
		status.add(SendStatus.DISABLED);
		status.add(SendStatus.SUCCESS);
		OnlinesmsinfoExample example = new OnlinesmsinfoExample();
		example.createCriteria().andAcctnbrEqualTo(acctNbr)
								.andTranstimeBetween(beginTime, transDateTime)
								.andPhoneEqualTo(phoneNo)
								.andTranscodeEqualTo(transCode)
								.andSendstatusIn(status);		
		try {
			List<Onlinesmsinfo> smsList = OnlinesmsinfoDAO.selectByExample(example);
			if(smsList.size()>0 || !smsList.isEmpty())
			{
				for(Onlinesmsinfo sms : smsList)
				{
					if(transDateTime.getTime()-(DateUtil.dataToTimeStamp(sms.getTranstime())).getTime() < 30 * 1000)
					{ 
						throw new PeException(DictErrors.NOT_SEND_AGAIN_IN_SHORT_TIME);
					}
					sms.setSendstatus(SendStatus.DISABLED);
					OnlinesmsinfoDAO.updateByPrimaryKeySelective(sms);
				}
			}
			
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		/*
		 * 短信验证信息入库
		 */
		Onlinesmsinfo param = new Onlinesmsinfo();
		param.setSeqnbr(seqNbr);
		param.setPhone(phoneNo);
		param.setTransdate(transData);
		param.setTranstime(transDateTime);
		param.setTranscode(transCode);
		param.setAcctnbr(acctNbr);
		param.setCode(smsCode);
		param.setSendstatus(SendStatus.UNSEND);
		param.setExpirdatetime(beginTime);
		try {
			OnlinesmsinfoDAO.insert(param);
			context.setData(Dict.SMS_SQENBR, seqNbr);
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}		
		
	}
	/**
	 * 连接短信平台，发送短信
	 * @param context
	 * @throws PeException
	 */
	private void sendSms(Context context) throws PeException
	{	
		
		StringBuilder bodySb = new StringBuilder();
		String phoneNo = (String) context.getData(Dict.PAYER_PHONE_NO);
		String deptNbr = (String) context.getData(Dict.PAYER_ACCT_DEPT_NBR);
		String smsContent = (String) context.getData(Dict.SMS_CONTENT);		
		String transCode = (String) context.getData(Dict.TRANS_TYP_CD);
		Timestamp transTime = (Timestamp) context.getData(Dict.TRAN_TIME);
		/*
		 * 短信格式编码
		 */
		String code = "";
		if(PaymTransCode.PayTrans.equals(transCode))
		{
			code = "ZFDYZM12000";
		}else if(PaymTransCode.AddSignInfo.equals(transCode))
		{
			code = "ZFDYZM13000";
		}else if(PaymTransCode.QuerySignInfoList.equals(transCode))
		{
			code = "ZFDYZM14000";
		}else if(PaymTransCode.UpdateTransLimit.equals(transCode))
		{
			code = "ZFDYZM15000";
		}else if(PaymTransCode.UnHoldAcctStatus.equals(transCode))
		{
			code = "ZFDYZM16000";
		}else if(PaymTransCode.CancelSignInfo.equals(transCode))
		{
			code = "ZFDYZM17000";
		}else if("RegisterAndEpay".equals(transCode)){
			code = "ZFDYZM18000";
		}
		OnlinesmsinfoExample example = new OnlinesmsinfoExample();
		example.createCriteria().andPhoneEqualTo(phoneNo).andTranscodeEqualTo(transCode).andTranstimeEqualTo(transTime);
		Onlinesmsinfo record = new Onlinesmsinfo();
		record.setSendstatus(SendStatus.SUCCESS);
		try {
			OnlinesmsinfoDAO.updateByExampleSelective(record, example);
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		
		/*if(deptNbr.length() > 3){
			deptNbr = deptNbr.substring(0, 3);
		}*/
		
		bodySb.append("01|")
			.append(phoneNo).append("|")
			.append(code).append("|11|")
			.append(deptNbr).append("|")
			.append(smsContent)
			.append("|\r\n");
		byte[] bodyBt=null;
		byte[] headBt=new byte[512];
		try {
			bodyBt=bodySb.toString().getBytes(encoding);
			String fileName = "mb" + DateUtil.Date_To_DateTimeFormat(new Date(),DateFormatCode.DATETIME_FORMATTER3) + (new Random()).nextLong()+".txt";
			StringBuilder headSb = new StringBuilder();
			headSb.append(fileName + "|").append(Integer.toString(bodyBt.length) + "|");
			byte[] tmpHeadBt = headSb.toString().getBytes(encoding);
			System.arraycopy(tmpHeadBt, 0, headBt, 0, tmpHeadBt.length);
		} catch (UnsupportedEncodingException e1) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}

		byte[] sendBt=new byte[headBt.length+bodyBt.length];
		System.arraycopy(headBt, 0, sendBt, 0, headBt.length);
		System.arraycopy(bodyBt, 0, sendBt, headBt.length, bodyBt.length);
		/*String res="";
		try {
			byte[] result = (byte[])transport.submit(sendBt);
			res =new String(result);
		}catch (Exception ex) {
			log.error("发送短信平台出错："+ex.getMessage());
		}*/
	
		/*	String resStatus = res.substring(0, 2);
			if("00".equals(resStatus)){
//			InputPaymentData input = new InputPaymentData(context.getDataMap());
//			queryCardType(input);
//			if(CardTypCd.DEBIT.equals(input.getPayercardtypcd())){
//				
//			}
			
			 * 更新短信状态：成功
			 
			OnlinesmsinfoExample example = new OnlinesmsinfoExample();
			example.createCriteria().andPhoneEqualTo(phoneNo).andTranscodeEqualTo(transCode).andTranstimeEqualTo(transTime);
			Onlinesmsinfo record = new Onlinesmsinfo();
			record.setSendstatus(SendStatus.SUCCESS);
			try {
				OnlinesmsinfoDAO.updateByExampleSelective(record, example);
			} catch (SQLException e) {
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
		}else{
//			InputPaymentData input = new InputPaymentData(context.getDataMap());
//			queryCardType(input);
//			if(CardTypCd.DEBIT.equals(input.getPayercardtypcd())){
//				
//			}
			
			
			 * 更新短信状态：失败
			 
			OnlinesmsinfoExample example = new OnlinesmsinfoExample();
			example.createCriteria().andPhoneEqualTo(phoneNo).andTranscodeEqualTo(transCode).andTranstimeEqualTo(transTime);
			Onlinesmsinfo record = new Onlinesmsinfo();
			record.setSendstatus(SendStatus.FAILURE);
			try {
				OnlinesmsinfoDAO.updateByExampleSelective(record, example);
			} catch (SQLException e) {
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
		}		*/
	}
	
	private Timestamp rolTimestamp(Timestamp transTime, long rolTime)
	{
		long currentTime = transTime.getTime();
		Timestamp beginTime = new Timestamp(currentTime - rolTime);		
		return beginTime;
		
	}
	private String getRandom() {
		int smsCode = (int) ((Math.random()*9+1)*100000);
		return String.valueOf(smsCode);
	}

	

}
