package com.csii.upp.transport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.pe.common.util.CsiiUtils;
import com.csii.pe.service.comm.CommunicationException;
import com.csii.pe.service.comm.Transport;
import com.csii.upp.constant.ConstCore;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.constant.TransStatus;

/**
 * 不发送报文，用于测试
 * 
 * @author 徐锦
 *
 */
public class DummyTransport implements Transport {
	private Transport transport;
	
	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	@Override
	public Object submit(Object arg0) throws CommunicationException {
//		try{
//		transport.submit(arg0);
//		}catch(Exception e){	
//		}
		Map<String, Object> dataMap = (Map) arg0;
		Map respMap = new HashMap();
		String fundChannelCd =(String)dataMap.get("srvChnlId");
		// 快钱
		if (FundChannelCode.BILL99.equals(fundChannelCd)) {
			dataMap.put(
					"resultStatus",
					getRandam().equals("SUCCESS") ? ResponseCode.SUCCESS
							: ResponseCode.FAILURE);
			//dataMap.put("ResponseHeader", respMap);
		}
        //二代支付 
		else if (FundChannelCode.CNAPS2.equals(fundChannelCd)) {
			dataMap.put("resultStatus",
					getRandam().equals("SUCCESS") ?ResponseCode.SUCCESS
							: ResponseCode.FAILURE);
			//dataMap.put("ResponseHeader", respMap);
		} 
		
		//中金支付
		else if (FundChannelCode.CICC.equals(fundChannelCd)) {
			if(dataMap.get("transCode").equals("cicc")){
				dataMap.put("resultStatus",ConstCore.SUCCESS);
				dataMap.put("rtxnstate","0");
				dataMap.put("transStatus","0");
			}
			//dataMap.put("ResponseHeader", respMap);
		}
		
		//通联支付
		else if (FundChannelCode.QSOP.equals(fundChannelCd)) {
			if(dataMap.get("transCode").equals("518308")){
				dataMap.put("resultStatus", ConstCore.SUCCESS);
				dataMap.put("downrtxnnbr", "1645");
				dataMap.put("downrtxndate", "2020-10-03");
				dataMap.put("rtxnstate", "0");
			}
			//dataMap.put("ResponseHeader", respMap);
		}
		// 核心 
		else if (FundChannelCode.CORE.equals(fundChannelCd)||FundChannelCode.FDPS.equals(fundChannelCd)) {
			if(dataMap.get("transCode").equals("queryDirectAcctInfo")){
				Map<String,String> returnMap=new HashMap<String,String>();
				returnMap.put("retCode", getRandam().equals("SUCCESS") ? "0000": ResponseCode.FAILURE);
				returnMap.put("acctname", "李四");
				returnMap.put("curracctstatcd", "ACT");
				dataMap.put("postDate","2016-03-07");
				dataMap.put("acctinfo",returnMap);
				dataMap.put("BaseResponse",returnMap);
			
			}else if(dataMap.get("transCode").equals("SVR_CARD10006006")){//poc测日间异常使用
				dataMap.put("resultStatus", "0");//返回失败
				dataMap.put("downrtxnnbr", "1645");
				dataMap.put("downrtxndate", "2020-10-03");
				dataMap.put("rtxnstate", "0");	
				//积分扣营销账户资金
			}else if(dataMap.get("transCode").equals("502380")||dataMap.get("transCode").equals("502420")){
				dataMap.put("resultStatus", ConstCore.SUCCESS);
				dataMap.put("downrtxnnbr", "1645");
				dataMap.put("downrtxndate", "2020-10-03");
				dataMap.put("rtxnstate", "0");	
			//统一支付挡板
			}else if(dataMap.get("transCode").equals("518308")){
				dataMap.put("resultStatus", ConstCore.SUCCESS);
				dataMap.put("downrtxnnbr", "1645");
				dataMap.put("downrtxndate", "2020-10-03");
				dataMap.put("rtxnstate", "0");	
			//营销资金撤销消费
			}else if(dataMap.get("transCode").equals("990009")){
				dataMap.put("resultStatus", ConstCore.SUCCESS);
				dataMap.put("downrtxnnbr", "1645");
				dataMap.put("downrtxndate", "2020-10-03");
				dataMap.put("recStatus", "1");
				dataMap.put("rtxnstate", "0");
			}else if(dataMap.get("transCode").equals("720310")||dataMap.get("transCode").equals("413147")){
				dataMap.put("resultStatus", "0");
				dataMap.put("returndate", "2020-10-03");
				dataMap.put("downrtxndate", "2020-10-03");
				dataMap.put("rtxnstate", "0");
			}else if(dataMap.get("transCode").equals("SVR_CARD10006006")){
				dataMap.put("resultStatus",
						getRandam().equals("SUCCESS") ?ConstCore.SUCCESS
								: ResponseCode.FAILURE);
			}else if(dataMap.get("transCode").equals("30011")){
				dataMap.put("resultStatus",
						getRandam().equals("SUCCESS") ?ConstCore.SUCCESS
								: ResponseCode.FAILURE);
			}else if(dataMap.get("transCode").equals("506683")){
				dataMap.put("resultStatus",
						getRandam().equals("SUCCESS") ?ConstCore.SUCCESS
								: ResponseCode.FAILURE);
				List<String> PayerPhoneNoList = Arrays.asList("13552535506","15556504097","15555481897","13811111112");
				dataMap.put("payerAcctNbr", "6230910199020777777");
				dataMap.put("payerAcctStatus", "01");
				dataMap.put("payerCardExpiredDate","0808");;
				dataMap.put("payerIdNbr", "340823199104110413");
				dataMap.put("List", PayerPhoneNoList);
				dataMap.put("payerIdTypCd", "101");
				dataMap.put("payerAcctDeptNbr", "80666");
				dataMap.put("custCifNbr", "007");
				dataMap.put("payerAcctName", "李信友");
				
				dataMap.put("acctLossStatus", "1");
				dataMap.put("acctStatusWord", "00000001");
				dataMap.put("acctFreezeFlag", "009");
				dataMap.put("acctNature", "0011");
				dataMap.put("cardStatus", "1");
				dataMap.put("cardStatusWord", "00115");
			}else if( dataMap.get("transCode").equals("3002"))
			{
				dataMap.put("resultStatus",
						getRandam().equals("SUCCESS") ?ConstCore.SUCCESS
								: ResponseCode.FAILURE);
				List<String> PayerPhoneNoList = Arrays.asList("13552535506");
				dataMap.put("payerAcctNbr", "6230910699000071501");
				dataMap.put("payerAcctStatus", "01");
				dataMap.put("payerIdNbr", "111111111111111111");
				dataMap.put("List", PayerPhoneNoList);
				dataMap.put("payerIdTypCd", "01");
				dataMap.put("payerAcctDeptNbr", "80666");
				dataMap.put("custCifNbr", "007");
				dataMap.put("payerCardCvv2", "234");
				dataMap.put("payerAcctName", "lzl");
				dataMap.put("payerCardExpiredDate", "1111");
			}else if(dataMap.get("transCode").equals("508880")){
				/*if(dataMap.get("payercardpwd").equals("MDAwMDAxNDABAgAAAWgAAACkAACxPQzZzDaOZhhljrdyV8PPmNGPRjsgt4xEVGUWh4lcRgSh46Neu9TzKpF/73daG0YeUiHdDM0d65WfcHkTBN4xrTkvEVcZwksGPIF7vivJwjgX0YyvWkvScm/E0eKvZYZhZnntAjsNog1128NXfE9YAmfx3r5G7woeoO91oZCVnjAwMDAwMDIxm0ZUDWPgHgcc0b2nupUK+oTdEF8x")){
					dataMap.put("resultStatus",
							getRandam().equals("SUCCESS") ?ConstCore.SUCCESS
									: ResponseCode.FAILURE);
					dataMap.put("payerAcctName","李信友");
				}else*/
					dataMap.put("resultStatus",ConstCore.SUCCESS);
					dataMap.put("payerAcctName","lzl");
			}else if (dataMap.get("transCode").equals("3004")){
				dataMap.put("resultStatus",
						getRandam().equals("SUCCESS") ?ConstCore.SUCCESS
								: ResponseCode.FAILURE);
				dataMap.put("payercardpwd", "111111");
				dataMap.put("payerCardCvv2","234");
				dataMap.put("payerCardExpiredDate","1111");
				dataMap.put("payerIdNbr","111111111111111111");
				dataMap.put("payerAcctName","李信友");
				dataMap.put("payerIdTypCd","01");
				dataMap.put("resultStatus",ResponseCode.FAILURE);
			}else if(dataMap.get("transCode").equals("502310") || dataMap.get("transCode").equals("518304")){
				dataMap.put("resultStatus",
						getRandam().equals("SUCCESS") ?ConstCore.SUCCESS
								: ResponseCode.FAILURE);

			}else if(dataMap.get("transCode").equals("230110")){
				dataMap.put("resultStatus",
						getRandam().equals("SUCCESS") ?ConstCore.SUCCESS
								: ResponseCode.FAILURE);
				dataMap.put("openDeptId", "987654321123456");

			}else if(dataMap.get("transCode").equals("413146")){
				dataMap.put("resultStatus",
						getRandam().equals("SUCCESS") ?ConstCore.SUCCESS
								: ResponseCode.FAILURE);
				dataMap.put("transDate", "2016-02-25");
			}else if(dataMap.get("transCode").equals("502320") || dataMap.get("transCode").equals("518305")){
				dataMap.put("resultStatus",
						getRandam().equals("SUCCESS") ?ConstCore.SUCCESS
								: ResponseCode.FAILURE);
			}else{
				dataMap.put("resultStatus", ConstCore.SUCCESS);
				dataMap.put("downrtxnnbr", "1645");
				dataMap.put("downrtxndate", "2020-10-03");
				dataMap.put("rtxnstate", "0");
				
			}

		}
		// 积分网关
				else if (FundChannelCode.JFWG.equals(fundChannelCd)) {
					if(dataMap.get("transCode").equals("queryPoint")){
						dataMap.put("cmdId","3a6aa078-93be-4e5d-b904-2e34570421c1");
						dataMap.put("occuDate","20170214134847545");
						dataMap.put("protocolVersion", "1.0");
						dataMap.put("responseResult", "11");
						dataMap.put("responseResultInfo", "查询成功");
						dataMap.put("requestId", "201702141111648910");
						Map<String,String> record = new HashMap<String,String>();
						Map<String,String> record2 = new HashMap<String,String>();
						Map<String,String> record3 = new HashMap<String,String>();
						List<Map> PointRecords = new ArrayList<Map>();
						record.put("branchNo", "801000");
						record.put("branchName","杭州联合银行");
						record.put("integralTotal","100");
						record.put("clientNo","81045276654");
						PointRecords.add(record);
						record2.put("branchNo", "802000");
						record2.put("branchName","萧山农商行");
						record2.put("integralTotal","200");
						record2.put("clientNo","81045276654");
						PointRecords.add(record2);
						record3.put("branchNo", "803000");
						record3.put("branchName","余杭农商行");
						record3.put("integralTotal","300");
						record3.put("clientNo","81045276654");
						PointRecords.add(record3);
						dataMap.put("pointRecords", PointRecords);
						/*dataMap.put("branchNo", "801000");
						dataMap.put("branchName","杭州西湖");
						dataMap.put("integralTotal","2000");
						dataMap.put("clientNo","8050000000");
						dataMap.put("integralThisTime","李信友");*/
						dataMap.put("resultStatus",ResponseCode.SUCCESS);
						dataMap.put("rtxnstate",TransStatus.SUCCESS);
					}else if(dataMap.get("transCode").equals("consumePoint")){
						dataMap.put("cmdId", "4d8e6a81-db98-4a6f-90f0-a7e6898b6243");
						dataMap.put("occuDate", "20170215090942071");
						dataMap.put("protocolVersion", "1.0");
						dataMap.put("responseResult", "46771502");
						dataMap.put("responseResultInfo", "消费成功");
						dataMap.put("requestId", "201702151111653112");
					}else if(dataMap.get("transCode").equals("cancelConsume")){
						dataMap.put("cmdId", "64bbad17-7598-4de3-8420-d1c99351c5a4");
						dataMap.put("occuDate", "20170215091843018");
						dataMap.put("protocolVersion", "1.0");
						dataMap.put("responseResult", "10");
						dataMap.put("responseResultInfo", "撤销消费成功");
						dataMap.put("requestId", "201702151111653141");
					}
					
		}
				else if (FundChannelCode.ALIPAYCODE.equals(fundChannelCd)||"ALIPAY".equals(fundChannelCd)) {
					if(dataMap.get("txnCode").equals("8012")){
						dataMap.put("respCode","01");
						dataMap.put("respDesc", "支付宝支付");
						dataMap.put("txnSeqId", "668899");
						dataMap.put("payType", "12");
						/*dataMap.put("branchNo", "801000");
						dataMap.put("branchName","杭州西湖");
						dataMap.put("integralTotal","2000");
						dataMap.put("clientNo","8050000000");
						dataMap.put("integralThisTime","李信友");*/
						dataMap.put("txnTime","20180612221133");
						dataMap.put("returnHtml","http://60.190.244.46:8003/merchant/gb/index.jsp?cont=11515184894&sssss=46546456464&cbkljkjk=nn");
					}
					if(dataMap.get("txnCode").equals("8002")){
						dataMap.put("respCode","02");
						dataMap.put("respDesc", "支付宝被扫您的请求");
						dataMap.put("txnSeqId", "668899");
						dataMap.put("payType", "15");
						/*dataMap.put("branchNo", "801000");
						dataMap.put("branchName","杭州西湖");
						dataMap.put("integralTotal","2000");
						dataMap.put("clientNo","8050000000");
						dataMap.put("integralThisTime","李信友");*/
						dataMap.put("txnTime","20180612221133");
						dataMap.put("buyerLogonId","wang3940515");
						dataMap.put("receiptAmount","000000000123");
						dataMap.put("alipayPayDate","20190612221133");
						dataMap.put("alipayTradeNo","020888888888");
						//dataMap.put("codeUrl","http://60.190.244.46:8003/merchant/gb/index.jsp");
					}
					if(dataMap.get("txnCode").equals("3001")){
						dataMap.put("respCode","02");
						dataMap.put("respDesc", "支付宝查询成功");
						dataMap.put("txnSeqId", "668899");
						dataMap.put("payType", "15");
						/*dataMap.put("branchNo", "801000");
						dataMap.put("branchName","杭州西湖");
						dataMap.put("integralTotal","2000");
						dataMap.put("clientNo","8050000000");
						dataMap.put("integralThisTime","李信友");*/
						dataMap.put("txnTime","20180612221133");
						dataMap.put("refoundAmount","000000000123");
						dataMap.put("alipayTradeNo","020888888888");
						//dataMap.put("codeUrl","http://60.190.244.46:8003/merchant/gb/index.jsp");
					}
					if(dataMap.get("txnCode").equals("8010")){
						dataMap.put("respCode","02");
						dataMap.put("respDesc", "支付宝疑惑查询成功");
						dataMap.put("txnSeqId", "668899");
						dataMap.put("payType", "15");
						/*dataMap.put("branchNo", "801000");
						dataMap.put("branchName","杭州西湖");
						dataMap.put("integralTotal","2000");
						dataMap.put("clientNo","8050000000");
						dataMap.put("integralThisTime","李信友");*/
						dataMap.put("txnTime","20180612221133");
						//dataMap.put("codeUrl","http://60.190.244.46:8003/merchant/gb/index.jsp");
					}
					if(dataMap.get("txnCode").equals("8003")){
						//8003新增
						//8005修改
						dataMap.put("respCode","02");
						dataMap.put("respDesc", "支付宝商户同步成功");
						dataMap.put("channel", "6002");
						dataMap.put("merId","AB201609230000046968");
						dataMap.put("alipayMerchantId","zhifubaoruzhubiaoshifu");
					}
					if(dataMap.get("txnCode").equals("8005")){
						//8003新增
						//8005修改
						dataMap.put("respCode","02");
						dataMap.put("respDesc", "支付宝商户修改成功");
						dataMap.put("channel", "6002");
						dataMap.put("merId","AB201609230000046968");
						dataMap.put("alipayMerchantId","zhifubaoruzhubiaoshifu");
					}
			}
				else if (FundChannelCode.WECHATCODE.equals(fundChannelCd)) {
					if(dataMap.get("txnCode").equals("1008")){
						dataMap.put("respCode","00");
						dataMap.put("rtxnstate","5");
						
						/*dataMap.put("branchNo", "801000");
						dataMap.put("br5565anchName","杭州西湖");
						dataMap.put("integralTotal","2000");
						dataMap.put("clientNo","8050000000");
						dataMap.put("integralThisTime","李信友");*/
						dataMap.put("txnTime","20180612221133");
						dataMap.put("codeUrl","http://60.190.244.46:8003/paygate/FS03.do?fdfdfd=ppppp&ddd=897987897878");
					}if(dataMap.get("txnCode").equals("1002")){
						dataMap.put("respCode",
								getRandam().equals("SUCCESS") ?"02"
										: "03");
						dataMap.put("respDesc", "微信被扫请求");
						dataMap.put("txnSeqId", "668899");
						dataMap.put("payType", "15");
						/*dataMap.put("branchNo", "801000");
						dataMap.put("branchName","杭州西湖");
						dataMap.put("integralTotal","2000");
						dataMap.put("clientNo","8050000000");
						dataMap.put("integralThisTime","李信友");*/
						dataMap.put("txnTime","20180612221133");
						dataMap.put("wxPayTime","20190612221133");
						dataMap.put("wxOrderNo","02062626333");
						//dataMap.put("codeUrl","http://60.190.244.46:8003/paygate/FS03.do");
					}
					if(dataMap.get("txnCode").equals("3001")){
						dataMap.put("respCode","02");
						dataMap.put("respDesc", "微信查询成功");
						dataMap.put("txnSeqId", "668899");
						dataMap.put("payType", "15");
						/*dataMap.put("branchNo", "801000");
						dataMap.put("branchName","杭州西湖");
						dataMap.put("integralTotal","2000");
						dataMap.put("clientNo","8050000000");
						dataMap.put("integralThisTime","李信友");*/
						dataMap.put("txnTime","20180612221133");
						//dataMap.put("codeUrl","http://60.190.244.46:8003/merchant/gb/index.jsp");
					}
					if(dataMap.get("txnCode").equals("2004")){
						dataMap.put("respCode","02");
						dataMap.put("respDesc", "微信退货成功");
						dataMap.put("txnSeqId", "668899");
						dataMap.put("payType", "15");
						/*dataMap.put("branchNo", "801000");
						dataMap.put("branchName","杭州西湖");
						dataMap.put("integralTotal","2000");
						dataMap.put("clientNo","8050000000");
						dataMap.put("integralThisTime","李信友");*/
						dataMap.put("txnTime","20180612221133");
						dataMap.put("refoundAmount","000000000123");
						dataMap.put("wxOrderNo","0208889999999");
						//dataMap.put("codeUrl","http://60.190.244.46:8003/merchant/gb/index.jsp");
					}
					if(dataMap.get("txnCode").equals("5001")){
						//5001新增
						//5002修改
						dataMap.put("respCode","02");
						dataMap.put("respDesc", "微信商户同步成功");
						dataMap.put("mchId", "998877665544332211");
						dataMap.put("subMchId","199811183039");
					}
					if(dataMap.get("txnCode").equals("5002")){
						//5001新增
						//5002修改
						dataMap.put("respCode","02");
						dataMap.put("respDesc", "微信商户修改成功");
						dataMap.put("mchId", "998877665544332211");
						dataMap.put("subMchId","199811183039");
					}
			}
		// 超级网银
		else if (FundChannelCode.IBPS.equals(fundChannelCd)) {
			if (dataMap.get("transCode").equals("123456")){
				dataMap.put("returncode","000000");
				dataMap.put("rtxnstate","0");
			}
			/*dataMap.put("resultStatus",
					getRandam().equals("SUCCESS") ?ResponseCode.SUCCESS
							: ResponseCode.FAILURE);
			dataMap.put("rtxnstate", getRandam().equals("SUCCESS") ?TransStatus.SUCCESS
					: TransStatus.FAILURE);*/
		}
		//大同城
		else if (FundChannelCode.DPC.equals(fundChannelCd)) {
			dataMap.put("resultStatus",
					getRandam().equals("SUCCESS") ? ResponseCode.SUCCESS
							: ResponseCode.FAILURE);
			//dataMap.put("ResponseHeader", respMap);
		}
		//大额支付
		else if (FundChannelCode.HVPS.equals(fundChannelCd)) {
			dataMap.put("resultStatus",
					getRandam().equals("SUCCESS") ? ResponseCode.SUCCESS
							: ResponseCode.FAILURE);
			//dataMap.put("ResponseHeader", respMap);
		}
		//人行代收付 
		else if (FundChannelCode.PBC.equals(fundChannelCd)) {
			dataMap.put("resultStatus",
					getRandam().equals("SUCCESS") ?ResponseCode.SUCCESS
							: ResponseCode.FAILURE);
			//dataMap.put("ResponseHeader", respMap);
		}
		//银联 
		else if (FundChannelCode.UNIONPAY.equals(fundChannelCd)) {
		
			if(dataMap.get("txnType").equals("77")){
				dataMap.put("rtxnstate","5");
				dataMap.put("retCode","0");
			}else if(dataMap.get("txnType").equals("00")){
				dataMap.put("rtxnstate","0");
				dataMap.put("retCode","0");
				dataMap.put("origRespCode","00");
			}else if(dataMap.get("txnType").equals("01")){	//消费
				dataMap.put("downrtxnnbr","888888");
				dataMap.put("downrtxndate",CsiiUtils.getCurrentDate());
				dataMap.put("downrtxntime",CsiiUtils.getCurrentDateTime());
				dataMap.put("returncode","0000");
				dataMap.put("returnmsg","交易已受理");
				dataMap.put("rtxnstate","5");
				dataMap.put("downsysnbr","union");
			}
		}
		//账户系统
		else if (FundChannelCode.EACCOUNT.equals(fundChannelCd)) {
			
			if(dataMap.get("transCode").equals("AcctOuterWithdrawal")){
				dataMap.put("resultStatus","0");
				dataMap.put("rtxnstate","0");
				dataMap.put("retCode","0");
				
			}else if(dataMap.get("transCode").equals("AcctRecharge")){
				dataMap.put("resultStatus", ConstCore.SUCCESS);
				dataMap.put("downrtxnnbr", "2645");
				dataMap.put("downrtxndate", "2020-10-03");
				dataMap.put("rtxnstate", "0");	
				dataMap.put("resultStatus","0");
				dataMap.put("rtxnstate","0");
				dataMap.put("retCode","0");
			}else if(dataMap.get("transCode").equals("AcctItlWithdraw")){
				dataMap.put("resultStatus", ConstCore.SUCCESS);
				dataMap.put("downrtxnnbr", "3645");
				dataMap.put("downrtxndate", "2020-10-03");
				dataMap.put("rtxnstate", "0");	
				dataMap.put("resultStatus", "0000");
			}else if(dataMap.get("transCode").equals("AcctInnerWithdrawal")){
				dataMap.put("resultStatus", ConstCore.SUCCESS);
				dataMap.put("downrtxnnbr", "4645");
				dataMap.put("downrtxndate", "2020-10-03");
				dataMap.put("rtxnstate", "0");	
				dataMap.put("resultStatus", "0000");
			}else if(dataMap.get("transCode").equals("QueryEcAcctTxn")){
				dataMap.put("resultStatus", ConstCore.SUCCESS);
				dataMap.put("downrtxnnbr", "2645");
				dataMap.put("downrtxndate", "2020-10-03");
				dataMap.put("rtxnstate", "0");	
				dataMap.put("resultStatus","0");
				dataMap.put("rtxnstate","0");
				dataMap.put("retCode","0");
			}
			
		/*	if(dataMap.get("transCode").equals("queryDirectAcctInfo")){
				Map<String,String> returnMap=new HashMap<String,String>();
				returnMap.put("retCode", getRandam().equals("SUCCESS") ? "0000": ResponseCode.FAILURE);
				returnMap.put("acctname", "李四");
				returnMap.put("curracctstatcd", "ACT");
				dataMap.put("postDate","2016-03-07");
				dataMap.put("acctinfo",returnMap);
				dataMap.put("BaseResponse",returnMap);
			//积分扣营销账户资金
			}else if(dataMap.get("transCode").equals("502380")){
				dataMap.put("resultStatus", ConstCore.SUCCESS);
				dataMap.put("downrtxnnbr", "1645");
				dataMap.put("downrtxndate", "2020-10-03");
				dataMap.put("rtxnstate", "0");	
			//统一支付挡板
			}else if(dataMap.get("transCode").equals("518308")){
				dataMap.put("resultStatus", ConstCore.SUCCESS);
				dataMap.put("downrtxnnbr", "1645");
				dataMap.put("downrtxndate", "2020-10-03");
				dataMap.put("rtxnstate", "0");	
			//营销资金撤销消费
			}else if(dataMap.get("transCode").equals("990009")){
				dataMap.put("resultStatus", ConstCore.SUCCESS);
				dataMap.put("downrtxnnbr", "1645");
				dataMap.put("downrtxndate", "2020-10-03");
				dataMap.put("rtxnstate", "0");
			}else if(dataMap.get("transCode").equals("720310")){
				dataMap.put("resultStatus", "0");
				dataMap.put("returndate", "2020-10-03");
				dataMap.put("downrtxndate", "2020-10-03");
				dataMap.put("rtxnstate", "0");
			}
			else{
				dataMap.put("resultStatus", ConstCore.SUCCESS);
				dataMap.put("downrtxnnbr", "1645");
				dataMap.put("downrtxndate", "2020-10-03");
				dataMap.put("rtxnstate", "0");
			}
			*/
		}
//
//		dataMap.put("rtxndate", DateUtil.formatDate((Date) dataMap.get("rtxndate"), "yyyyMMdd"));
//		dataMap.put("rtxntime", DateUtil.formatDate((Date) dataMap.get("rtxntime"), "yyyyMMddhhmmss"));
		return dataMap;
	}

	private String getRandam() {
		int random = (int) (Math.random() * 10) + 5;
		if (random % 2 == 0) {
			return "SUCCESS";// 成功
		} else {
			return "SUCCESS";// 失败
		}
	}

}
