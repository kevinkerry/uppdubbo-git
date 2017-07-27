package com.csii.upp.fundprocess.action.callback;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.generate.FundchannelDAO;
import com.csii.upp.dao.generate.InnerfundtransDAO;
import com.csii.upp.dao.generate.OverallrequestregDAO;
import com.csii.upp.dao.generate.OveralltransDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.dto.generate.Overallrequestreg;
import com.csii.upp.dto.generate.OverallrequestregExample;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.dto.router.unionpay.RespDsjyyb;
import com.csii.upp.fundprocess.action.PayOnlineAction;
import com.csii.upp.fundprocess.action.event.handler.NotifyPaymentResultEvent;
import com.csii.upp.supportor.DefaultSupportor;
import com.csii.upp.util.DateUtil;

/**
 * 他行快捷支付回调类
 * 
 * @author GAOQI
 *
 */
public class UnionPayOtherBankQuickPayAfterAction extends PayOnlineAction {

	@Override
	public void execute(Context ctx) throws PeException {
		log.info("UnionPayOtherBankQuickPayAfterAction--------UnionNotifation Success");
		RespDsjyyb respobj = this.buildRespOjb(ctx);
		String innerrtxnnbr = respobj.getOrderId();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			// 收到银联通知即表示成功
			Innerfundtrans innerfundtrans = InnerfundtransDAO.selectByPrimaryKey(innerrtxnnbr);
			if (TransStatus.SUCCESS.equals(innerfundtrans.getTransstatus())) {
				return;
			}
			innerfundtrans.setTransstatus(TransStatus.SUCCESS);//交易状态
			innerfundtrans.setReturncode(respobj.getRespCode());//交易返回码
			innerfundtrans.setReturnmsg(respobj.getRespMsg());//交易返回信息
			innerfundtrans.setDowntransnbr(respobj.getQueryId());//后台流水号
			innerfundtrans.setPayeracctnbr(respobj.getAccNo());//付款账号
			innerfundtrans.setCleardate(DateUtil.DateTimeFormat_To_Date(respobj.getSettleDate(), DateFormatCode.DATE_FORMATTER3));//清算日期
			InnerfundtransDAO.updateByPrimaryKeySelective(innerfundtrans);
			//判断总交易流水状态如果为失败。发起撤消交易
			if (TransStatus.FAILURE.equals(OveralltransDAO.selectByPrimaryKey(innerfundtrans.getOveralltransnbr()).getOveralltransstatus())) {
				Map<String, Object> map = this.getObjectMapMarshaller().marshall(innerfundtrans);
				InputFundData inputCancelData = new InputFundData(map);
				
				inputCancelData.setOriginnertransnbr(innerrtxnnbr);
				inputCancelData.setInnerfundtransnbr(null);
				inputCancelData.setTranstime(DateUtil.getCurrentDateTime());
				inputCancelData.setTransdate(this.getPostDate());
				inputCancelData.setDowntransnbr(innerfundtrans.getDowntransnbr());
				inputCancelData.setBizType(respobj.getBizType());
				inputCancelData.setPayeeacctnbr(respobj.getAccNo());
				inputCancelData.setCheckdataflag(FundChannelCode.UNIONPAY);
				
				this.getUnionPayService().cancelTrans(inputCancelData);
				ctx.setData(Dict.TRANS_STATUS, TransStatus.FAILURE);
				return;
			}
			
			OverallrequestregExample overallrequestregExample = new OverallrequestregExample();
			overallrequestregExample.createCriteria().andUppertransnbrEqualTo(innerfundtrans.getUppertransnbr())
					.andUppersysnbrEqualTo(innerfundtrans.getUppersysnbr());
			List<Overallrequestreg> overallrequestregs = OverallrequestregDAO.selectByExample(overallrequestregExample);
			
			InputFundData inputFundData = InputFundData.parseInputData(overallrequestregs.get(0));
			inputFundData
					.setPayeracctnbr(FundchannelDAO.selectByPrimaryKey(innerfundtrans.getFundchannelcode()).getSettleacctnbr());
			inputFundData.setOveralltransnbr(innerfundtrans.getOveralltransnbr());
			inputFundData.setBizType(respobj.getBizType());
			inputFundData.setCheckdataflag(innerfundtrans.getFundchannelcode());
			inputFundData.setPayercardtypcd(null);
			
			RespSysHead respHead = this.getCoreService().coreInnerTransfer(inputFundData);
			
			InputFundData inputData2Payment = new InputFundData();
			if (respHead.isSuccess()) {
				inputData2Payment.setTransstatus(TransStatus.SUCCESS);
			} else if (respHead.isFailure()) {
				inputData2Payment.setTransstatus(TransStatus.FAILURE);
				
				Map<String, Object> map = this.getObjectMapMarshaller().marshall(innerfundtrans);
				InputFundData inputCancelData = new InputFundData(map);
				
				inputCancelData.setOriginnertransnbr(innerrtxnnbr);
				inputCancelData.setInnerfundtransnbr(null);
				inputCancelData.setTranstime(DateUtil.getCurrentDateTime());
				inputCancelData.setTransdate(this.getPostDate());
				inputCancelData.setDowntransnbr(innerfundtrans.getDowntransnbr());
				inputCancelData.setBizType(respobj.getBizType());
				inputCancelData.setPayeeacctnbr(respobj.getAccNo());
				inputCancelData.setCheckdataflag(FundChannelCode.UNIONPAY);
				
				this.getUnionPayService().cancelTrans(inputCancelData);
				ctx.setData(Dict.TRANS_STATUS, TransStatus.FAILURE);
			}else {
				ctx.setData(Dict.TRANS_STATUS, TransStatus.PROCESSING);
				inputData2Payment.setTransstatus(TransStatus.PROCESSING);
			}
			// 通知Payment进行相应的处理
			inputData2Payment.setUppertransnbr(innerfundtrans.getUppertransnbr());
			inputData2Payment.setPayeracctnbr(respobj.getAccNo());
			inputData2Payment.setPayercardtypcd(respobj.getPayCardType());
			inputData2Payment.setBizType(respobj.getBizType());
			inputData2Payment.setCleardate(respHead.getDownrtxndate());
			inputData2Payment.setOveralltransnbr(innerfundtrans.getOveralltransnbr());
			
			NotifyPaymentResultEvent notifyPaymentResultEvent = new NotifyPaymentResultEvent();
			notifyPaymentResultEvent.setInputData2Payment(inputData2Payment);
			notifyPaymentResultEvent.setPaymService(this.getPaymService());
			DefaultSupportor.getEventManager().doService(notifyPaymentResultEvent);
			// 用于更新总交易流水
			ctx.setData(Dict.OVERALL_TRANS_NBR, innerfundtrans.getOveralltransnbr());
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
	}
}
