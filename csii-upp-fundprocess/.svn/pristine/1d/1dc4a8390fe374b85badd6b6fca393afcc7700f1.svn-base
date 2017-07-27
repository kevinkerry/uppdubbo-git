package com.csii.upp.fundprocess.action.payment;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.CodeTypCd;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.InnerRtxnTyp;
import com.csii.upp.constant.InteralFlag;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.generate.InnerfundtransDAO;
import com.csii.upp.dao.generate.InnerfundtranshistDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.dto.generate.InnerfundtransExample;
import com.csii.upp.dto.generate.Innerfundtranshist;
import com.csii.upp.dto.generate.InnerfundtranshistExample;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.fundprocess.action.PayOnlineAction;
import com.csii.upp.service.fundprocess.router.CreditRouter;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * @author gaoqi
 * @description 用于对payment提供统一的退货交易
 *  
 */
public class RefoundTransAction extends PayOnlineAction {
	/**
	 * 
	 * 功能：对payment提供统一退货交易处理
	 * 作者：gaoqi
	 * @mender: liru(添加二维码扫码支付退货交易) 
	 * @param: Context
	 * @return: void
	 * @remack:1)根据上游交易流水号、资金通道代码、积分标志查询资金子交易流水表（INNERFUNDTRANS）
	 *         2)如当前表中无记录根据上游交易流水号、资金通道代码、积分标志查询资金流水表历史表（INNERFUNDTRANS）
	 *         3)根据不同交易码发往各系统做退货处理（支付宝、微信提供了退货与撤消功能。当前资金流水表中无记录是发退款、反之撤消）
	 */        
	@Override
	public void execute(Context context) throws PeException {
		InputFundData input = new InputFundData(context.getDataMap());
		String fundChannelCode=FundChannelCode.CORE;
		//渠道代码 
		if(CodeTypCd.ALIPAY.equals(input.getCodetypcd())){//支付宝扫码
			fundChannelCode=FundChannelCode.ALIPAYCODE;
		}
		if(CodeTypCd.WECHAT.equals(input.getCodetypcd())){//微信扫码
			fundChannelCode=FundChannelCode.WECHATCODE;
		}
		
		InnerfundtransExample example = new InnerfundtransExample();
		example.createCriteria().andUppertransnbrEqualTo(input.getOriguppertransnbr())
				.andFundchannelcodeEqualTo(fundChannelCode).andPointdataflagNotEqualTo(InteralFlag.YES);
		List<Innerfundtranshist> innerfundtranshistList = null;
		RespSysHead resp = null;
		String payeeAcctNbr=null;
		try {
			List<Innerfundtrans> innerFundTransList = InnerfundtransDAO.selectByExample(example);//子交易资金流水表
			if (innerFundTransList.isEmpty()) {//资金子交易流水表中无记录
				InnerfundtranshistExample histExample = new InnerfundtranshistExample();
				histExample.createCriteria().andUppertransnbrEqualTo(input.getOriguppertransnbr())
						.andFundchannelcodeEqualTo(fundChannelCode).andPointdataflagNotEqualTo(InteralFlag.YES);;
				innerfundtranshistList = InnerfundtranshistDAO.selectByExample(histExample);//查询历史表
				Innerfundtranshist innerfundtranshist = innerfundtranshistList.get(0);
				//String fundChannelCode = innerfundtranshist.getFundchannelcode();
				//input.setFundchannelcode(fundChannelCode);
				input.setOriginnertransnbr(innerfundtranshist.getInnerfundtransnbr());//内部交易流水号
				input.setOrigtranstime(innerfundtranshist.getTranstime());//交易日期
 
				if (InnerRtxnTyp.CoreInnerTransfer.equals(innerfundtranshist.getTranscode())) {
					input.setCheckdataflag(FundChannelCode.UNIONPAY);
					context.setData(Dict.CHECK_DATA_FLAG, input.getCheckdataflag());
					input.setPayeeacctnbr(innerfundtranshist.getPayeracctnbr());
					resp = this.getCoreService().refoundTrans4UnionPay(input);
					if (resp.isSuccess()) {
						histExample = new InnerfundtranshistExample();
						histExample.createCriteria().andUppertransnbrEqualTo(input.getOriguppertransnbr())
								.andFundchannelcodeEqualTo(FundChannelCode.UNIONPAY);
						innerfundtranshistList = InnerfundtranshistDAO.selectByExample(histExample);
						innerfundtranshist = innerfundtranshistList.get(0);

						input = new InputFundData(context.getDataMap());
						input.setBizType(innerfundtranshist.getTranscode());// 要被退货交易类型
						input.setOriginnertransnbr(innerfundtranshist.getInnerfundtransnbr());
						input.setDowntransnbr(innerfundtranshist.getDowntransnbr());
						input.setCheckdataflag(FundChannelCode.UNIONPAY);
						this.getUnionPayService().refoundTrans(input);
					}
				}else if(InnerRtxnTyp.AlipayQrCodeActivePay.equals(innerfundtranshist.getTranscode())||InnerRtxnTyp.AlipayQrCodePassivePay.equals(innerfundtranshist.getTranscode())) {//支付宝主扫被扫					
					input.setMerId(innerfundtranshist.getPayeeacctnbr());//商户号
					input.setOriginnertransnbr(innerfundtranshist.getInnerfundtransnbr());
					input.setOrigtranstime(innerfundtranshist.getTranstime());
					resp=this.getAliPayService().refoundTrans(input);//支付宝退款交易	
					if (resp.isReceived()) {
						// 返回交易已受理
						context.setData(Dict.TRANS_STATUS, TransStatus.PROCESSING);
					}
					
				}else if(InnerRtxnTyp.WeChatQrCodePassivePay.equals(innerfundtranshist.getTranscode())||InnerRtxnTyp.WeChatQrCodeActivePay.equals(innerfundtranshist.getTranscode())) {//微信主扫被扫	
					//参数设置	
					input.setMerId(innerfundtranshist.getPayeeacctnbr());//商户号
					input.setOriginnertransnbr(innerfundtranshist.getInnerfundtransnbr());
					input.setOrigtranstime(innerfundtranshist.getTranstime());
					resp=this.getWechatService().refoundTrans(input);//微信退款交易
					if (resp.isReceived()) {
						// 返回交易已受理
						context.setData(Dict.TRANS_STATUS, TransStatus.PROCESSING);
					}
				}else {//核心
					input.setCheckdataflag(FundChannelCode.CORE);
					context.setData(Dict.CHECK_DATA_FLAG, input.getCheckdataflag());
					payeeAcctNbr=input.getPayeeacctnbr();
					if(!StringUtil.isStringEmpty(input.getInteralFlag())&&InteralFlag.YES.equals(input.getInteralFlag())){
						if (!StringUtil.isObjectEmpty(input.getDeductAmt())
								&& (input.getDeductAmt().compareTo(BigDecimal.ZERO) > 0)) {
							getCoreService().coreIntegalPay(input);
							input.setInnerfundtransnbr(null);
							input.setTransnbr(null);
						}
					}
					input.setPointdataflag(InteralFlag.NO);
					input.setPayeeacctnbr(payeeAcctNbr);
					resp = ((CreditRouter) this.getRouterService(fundChannelCode)).refoundTrans(input);
					context.setData(Dict.HOST_CLEAR_DATE, DateUtil.Date_To_DateTimeFormat(resp.getDownrtxndate(),DateFormatCode.DATE_FORMATTER3));
				}

			} else {//子交易资金流水表中有记录
				Innerfundtrans innerfundtrans = innerFundTransList.get(0);
			//	String fundChannelCode = innerfundtrans.getFundchannelcode();
			//	input.setFundchannelcode(fundChannelCode);
				input.setOriginnertransnbr(innerfundtrans.getInnerfundtransnbr());
				input.setOrigtranstime(innerfundtrans.getTranstime());
				//资金通道判断 
				if (InnerRtxnTyp.CoreInnerTransfer.equals(innerfundtrans.getTranscode())) {//银联订单的双边账，银联成功核心内部转账
					input.setCheckdataflag(FundChannelCode.UNIONPAY);
					context.setData(Dict.CHECK_DATA_FLAG, input.getCheckdataflag());
					input.setPayeeacctnbr(innerfundtrans.getPayeracctnbr());
					resp = this.getCoreService().refoundTrans4UnionPay(input);
					if (resp.isSuccess()) {
						example = new InnerfundtransExample();
						example.createCriteria().andUppertransnbrEqualTo(input.getOriguppertransnbr())
								.andFundchannelcodeEqualTo(FundChannelCode.UNIONPAY);
						innerFundTransList = InnerfundtransDAO.selectByExample(example);
						innerfundtrans = innerFundTransList.get(0);
						input = new InputFundData(context.getDataMap());
						input.setBizType(innerfundtrans.getTranscode());// 要被退货交易类型
						input.setOriginnertransnbr(innerfundtrans.getInnerfundtransnbr());
						input.setDowntransnbr(innerfundtrans.getDowntransnbr());
						input.setCheckdataflag(FundChannelCode.UNIONPAY);
						this.getUnionPayService().refoundTrans(input);
					}
				//支付宝撤消交易 
				}else if(InnerRtxnTyp.AlipayQrCodeActivePay.equals(innerfundtrans.getTranscode())||InnerRtxnTyp.AlipayQrCodePassivePay.equals(innerfundtrans.getTranscode())) {//支付宝主扫被扫					
					//参数设置	
					input.setMerId(innerfundtrans.getPayeeacctnbr());//商户号
					input.setOriginnertransnbr(innerfundtrans.getInnerfundtransnbr());
					input.setOrigtranstime(innerfundtrans.getTranstime());
					resp=this.getAliPayService().redoTrans(input);//支付宝撤消交易
					if (resp.isReceived()) {
						// 返回交易已受理
						context.setData(Dict.TRANS_STATUS, TransStatus.PROCESSING);
					}
				//微信撤消交易	
				}else if(InnerRtxnTyp.WeChatQrCodePassivePay.equals(innerfundtrans.getTranscode())) {//微信被扫
					//参数设置	
					input.setMerId(innerfundtrans.getPayeeacctnbr());//商户号
					input.setOriginnertransnbr(innerfundtrans.getInnerfundtransnbr());
					input.setOrigtranstime(innerfundtrans.getTranstime());
					resp=this.getWechatService().redoTrans(input);//微信撤消交易（主扫只能退款不能做撤消交易）
					if (resp.isReceived()) {
						// 返回交易已受理
						context.setData(Dict.TRANS_STATUS, TransStatus.PROCESSING);
					}
				//微信退款交易	
				}else if(InnerRtxnTyp.WeChatQrCodeActivePay.equals(innerfundtrans.getTranscode())){
					//参数设置	
					input.setMerId(innerfundtrans.getPayeeacctnbr());//商户号
					input.setOriginnertransnbr(innerfundtrans.getInnerfundtransnbr());
					input.setOrigtranstime(innerfundtrans.getTranstime());
					resp=this.getWechatService().refoundTrans(input);//微信退款交易 
					if (resp.isReceived()) {
						// 返回交易已受理
						context.setData(Dict.TRANS_STATUS, TransStatus.PROCESSING);
					}
				}else {//核心
					input.setCheckdataflag(FundChannelCode.CORE);
					context.setData(Dict.CHECK_DATA_FLAG, input.getCheckdataflag());
					payeeAcctNbr=input.getPayeeacctnbr();
						if(!StringUtil.isStringEmpty(input.getInteralFlag())&&InteralFlag.YES.equals(input.getInteralFlag())){
							if (!StringUtil.isObjectEmpty(input.getDeductAmt())
									&& (input.getDeductAmt().compareTo(BigDecimal.ZERO) > 0)) {
								getCoreService().coreIntegalPay(input);
								input.setInnerfundtransnbr(null);
								input.setTransnbr(null);
							}
					}
					input.setPointdataflag(InteralFlag.NO);
					input.setPayeeacctnbr(payeeAcctNbr);
					resp = ((CreditRouter) this.getRouterService(fundChannelCode)).refoundTrans(input);
					context.setData(Dict.HOST_CLEAR_DATE, DateUtil.Date_To_DateTimeFormat(resp.getDownrtxndate(),DateFormatCode.DATE_FORMATTER3));
				}

			}
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
	}
	
}