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
 * 银联网关支付和B2B回调类
 * 
 * @author WHD
 *
 */
public class UnionPayWGAfterAction extends PayOnlineAction {

	@Override
	public void execute(Context ctx) throws PeException {
		log.info("UnionPayWGAfterAction--------UnionNotifation Success");
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
			innerfundtrans.setTransstatus(TransStatus.SUCCESS);
			innerfundtrans.setReturncode(respobj.getRespCode());
			innerfundtrans.setReturnmsg(respobj.getRespMsg());
			innerfundtrans.setDowntransnbr(respobj.getQueryId());
			innerfundtrans.setPayeracctnbr(respobj.getAccNo());
			innerfundtrans.setCleardate(DateUtil.DateTimeFormat_To_Date(respobj.getSettleDate(), DateFormatCode.DATE_FORMATTER3));
			InnerfundtransDAO.updateByPrimaryKeySelective(innerfundtrans);

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
				return;
			}
			
			OverallrequestregExample overallrequestregExample = new OverallrequestregExample();
			overallrequestregExample.createCriteria().andUppertransnbrEqualTo(innerfundtrans.getUppertransnbr())
					.andUppersysnbrEqualTo(innerfundtrans.getUppersysnbr());
			List<Overallrequestreg> overallrequestregs = OverallrequestregDAO.selectByExample(overallrequestregExample);
			
			InputFundData inputFundData = InputFundData.parseInputData(overallrequestregs.get(0));
			inputFundData.setOveralltransnbr(innerfundtrans.getOveralltransnbr());
			inputFundData.setBizType(respobj.getBizType());
			inputFundData.setCheckdataflag(FundChannelCode.UNIONPAY);
			inputFundData
					.setPayeracctnbr(FundchannelDAO.selectByPrimaryKey(FundChannelCode.UNIONPAY).getSettleacctnbr());
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
