package com.csii.upp.fundprocess.action.callback;

import java.sql.SQLException;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.generate.InnerfundtransDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.dto.router.scancode.RespScancodeNotify;
import com.csii.upp.fundprocess.action.PayOnlineAction;
import com.csii.upp.fundprocess.action.event.handler.NotifyPaymentResultEvent;
import com.csii.upp.supportor.DefaultSupportor;
import com.csii.upp.util.DateUtil;

/**
 * 
 * 创建日期：2016年12月22日
 * Description:二维码前置支付结果通知处理类
 * @author liru
 * @mender: (文件的修改者，文件创建者之外的人)
 * @version: 1.0
 * @Remark: 扫码支付渠道包含：1.本行扫码支付2.微信扫码支付 3.支付宝扫码支付 。二维码前置支付结果通知包含微信扫码支付结果通知、支付宝扫码支付结果通知
 *      
 */
public class ScanCodePayNotifyAction extends PayOnlineAction {
	/**
	 * 
	 * 功能：接收扫码支付结果通知处理。
	 * 作者：liru
	 * @param: Context 
	 * @return: void
	 * @Remark: 1）更新资金子交易流水表状态  2）通知Payment进行相应的处理
	 */
	@Override
	public void execute(Context ctx) throws PeException {
		if(log.isInfoEnabled()){
			log.info("接收到二维码前置支付结果通知:" + ctx.getDataMap());
		}
		RespScancodeNotify respobj=buildRespInfo(ctx);//类型转换
		String innerrtxnnbr = respobj.getOrderId();//内部交易流水号
		try {
			Innerfundtrans innerfundtrans = InnerfundtransDAO.selectByPrimaryKey(innerrtxnnbr);//查询资金金流水表
			if(innerfundtrans==null){
				log.info("没有此支付订单信息，订单号："+innerrtxnnbr);
				return;
			}
			//如果状态为成交易成功（0）表示此订单已经处理完成，不再做处理
			if (TransStatus.SUCCESS.equals(innerfundtrans.getTransstatus())) {
				log.info("此订单已处理完成，重复推送，订单号："+innerrtxnnbr);
				return;
			}
			//更新资金子交易流水表状态
			innerfundtrans.setTransstatus(TransStatus.SUCCESS);//交易状态
			innerfundtrans.setReturncode(respobj.getRespCode());//交易返回码
			innerfundtrans.setReturnmsg(respobj.getRespMsg());//交易返回信息
			innerfundtrans.setDowntransnbr(respobj.getTransId());//下游流水号
			innerfundtrans.setPayeracctnbr(respobj.getPayerAcctNbr());//付款账号
			innerfundtrans.setCleardate(DateUtil.DateTimeFormat_To_Date(respobj.getClearDate(), DateFormatCode.DATE_FORMATTER3));//清算日期
			InnerfundtransDAO.updateByPrimaryKeySelective(innerfundtrans);
			// 通知Payment进行相应的处理
			InputFundData inputData2Payment = new InputFundData();
			inputData2Payment.setTransstatus(TransStatus.SUCCESS);//交易状态
			inputData2Payment.setUppertransnbr(innerfundtrans.getUppertransnbr());//上游交易流水号
			inputData2Payment.setPayeracctnbr(respobj.getPayerAcctNbr());
			//inputData2Payment.setBizType(respobj.getBizType());
			inputData2Payment.setOveralltransnbr(innerfundtrans.getOveralltransnbr());
			NotifyPaymentResultEvent notifyPaymentResultEvent = new NotifyPaymentResultEvent();
			notifyPaymentResultEvent.setInputData2Payment(inputData2Payment);
			notifyPaymentResultEvent.setPaymService(this.getPaymService());
			DefaultSupportor.getEventManager().doService(notifyPaymentResultEvent);
			// 用于更新总交易流水
			ctx.setData(Dict.OVERALL_TRANS_NBR, innerfundtrans.getOveralltransnbr());
		} catch (SQLException e) {
			if(log.isErrorEnabled()){
				log.error("二维码前置支付结果通知处理中发生异常！异常信息："+ e.getMessage());
			}
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		
	}
	
	protected RespScancodeNotify buildRespInfo(Context ctx) {
		RespScancodeNotify obj = new RespScancodeNotify();
		obj.setTransId(ctx.getString("transId"));//交易码
		obj.setMerId(ctx.getString("merId"));//商户号
		obj.setPayerAcctNbr(ctx.getString("payerAcctNbr"));//付款人账号
		obj.setPayerAcctName(ctx.getString("payerAcctName"));//付款人名
		obj.setPayAccessType(ctx.getString("payAccessType"));//支付接入类型
		obj.setPayType(ctx.getString("payType"));//支付类型
		obj.setTxnSeqId(ctx.getString("txnSeqId"));//二维码前置交易流水号
		obj.setTxnTime(ctx.getString("txnTime"));//二维码前置交易时间	
		obj.setClearDate(ctx.getString("clearDate"));//清算日期
		obj.setOrderId(ctx.getString("orderId"));//订单号
		obj.setOrderAmount(ctx.getString("orderAmount"));//交易金额
		obj.setFeeAmount(ctx.getString("feeAmount"));//手续费金额
		obj.setReceiptAmoun(ctx.getString("receiptAmoun"));//实收金额
		obj.setOrderTime(ctx.getString("orderTime")); //交易时间
		obj.setCurrencyType(ctx.getString("currencyType"));//币种
		obj.setTransType(ctx.getString("transType"));//交易类型
		obj.setRespCode(ctx.getString("respCode")); //响应结果
		obj.setRespMsg(ctx.getString("respMsg"));//应答描述
		obj.setRemark(ctx.getString("remark")); //备用字段	
		return obj;
	}


}
