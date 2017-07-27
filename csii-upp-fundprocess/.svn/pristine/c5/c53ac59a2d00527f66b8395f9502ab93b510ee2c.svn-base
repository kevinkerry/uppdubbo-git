package com.csii.upp.fundprocess.action.online;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.constant.CheckStatus;
import com.csii.upp.constant.DepartmentNbr;
import com.csii.upp.constant.InteralFlag;
import com.csii.upp.constant.ProcStatus;
import com.csii.upp.constant.ProcStep;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.generate.InnerfundtransDAO;
import com.csii.upp.dao.generate.MeracctinfoDAO;
import com.csii.upp.dao.generate.OnlinesubtransDAO;
import com.csii.upp.dao.generate.OnlinetransDAO;
import com.csii.upp.dao.generate.OveralltransDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.dto.generate.InnerfundtransExample;
import com.csii.upp.dto.generate.Meracctinfo;
import com.csii.upp.dto.generate.Onlinesubtrans;
import com.csii.upp.dto.generate.OnlinesubtransExample;
import com.csii.upp.dto.generate.Onlinetrans;
import com.csii.upp.dto.generate.Overalltrans;
import com.csii.upp.fundprocess.action.PayOnlineAction;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.DateUtil;

public class QRCodeCreateOrderAction extends PayOnlineAction{

	private static Logger logger = LoggerFactory.getLogger(QRCodeCreateOrderAction.class);    //日志记录
	  
	@Override
	public void execute(Context context) throws PeException {
		
		
		String transnbr=IDGenerateFactory.generateRtxnNbr();
		String merSeqNbr=IDGenerateFactory.generateSeqId();
		String overalltransnbr = context.getString(Dict.OVERALL_TRANS_NBR);
		//插入交易订单
		insertOnlineSubTrans(transnbr,merSeqNbr,context);
		insertOnlineTrans(transnbr, merSeqNbr, context);
		//插入资金流水
		insertOverAllTrans(transnbr, context);
		insertInnerFundTrans(transnbr, context);
		
		// TODO Auto-generated method stub
		String url="http://qr.csiidev.com:8080/QCode/qcode?transamt="+context.getString("TransAmt")
					+"&code="+context.getString("code")+"&url="+context.getString("url");
		JSONObject  json=new JSONObject();
		JSONObject jsonResult=httpPost(url, json, false);
//		JSONObject jsonResult = new JSONObject();
//		jsonResult.put("prepay_id", "112233445566");
//		jsonResult.put("signature", "abcdefghijklmn");
//		jsonResult.put("nonce_str", "111222333");
//		jsonResult.put("timestamp", "20170");
//		jsonResult.put("paySign", "lllzzzkkkk");
		jsonResult.put("RespCode", "0000");
		
		if(jsonResult.get("RespCode").equals("0000")){
			context.setData("prepay_id", jsonResult.get("prepay_id"));
			context.setData("signature", jsonResult.get("signature"));
			context.setData("nonce_str", jsonResult.get("nonce_str"));
			context.setData("timestamp", jsonResult.get("timestamp"));
			context.setData("paySign", jsonResult.get("paySign"));
			
			context.setData(Dict.TRANS_STATUS, TransStatus.PROCESSING);
			context.setData(Dict.RESP_CODE, "0000");
			//更新innerfundtrans流水
			updateTrans(overalltransnbr,transnbr,"5");  //交易处理中
		}else{
			//更新流水
			updateTrans(overalltransnbr,transnbr,"1");  //交易失败
		}
		log.info("二维码平台返回成功！更新交易状态");
		
		//更新
		
	}
	
//	public static void main(String[] args){
//		String url="http://150.221.75.70:8080/QCode/qcode";
//		JSONObject  json=new JSONObject();
//		
//		JSONObject jsonResult=httpPost(url, json, false);
//		System.out.println(jsonResult.toJSONString());
//	}
	
	public static void updateTrans(String overalltransnbr,String transnbr,String status) {
		// TODO Auto-generated method stub
		try {
			Onlinetrans onlinetrans = OnlinetransDAO.selectByPrimaryKey(transnbr);
			onlinetrans.setTransstatus(status);
			OnlinetransDAO.updateByPrimaryKey(onlinetrans);
			
			OnlinesubtransExample example1 = new OnlinesubtransExample();
			example1.createCriteria().andTransseqnbrEqualTo(transnbr);
			List<Onlinesubtrans> onlinesubtrans = OnlinesubtransDAO.selectByExample(example1);
			for(int i=0;i<onlinesubtrans.size();i++){
				Onlinesubtrans onlinesubtrans2 = onlinesubtrans.get(i);
				onlinesubtrans2.setTransstatus(status);
				OnlinesubtransDAO.updateByPrimaryKey(onlinesubtrans2);
			}
			
			InnerfundtransExample example = new InnerfundtransExample();
			example.createCriteria().andOveralltransnbrEqualTo(overalltransnbr);
			List<Innerfundtrans> innerfundtrans = InnerfundtransDAO.selectByExample(example);
			for(int i=0;i<innerfundtrans.size();i++){
				Innerfundtrans innerfundtrans2 = innerfundtrans.get(i);
				innerfundtrans2.setTransstatus(status);
				InnerfundtransDAO.updateByPrimaryKey(innerfundtrans2);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void insertOnlineTrans(String transSeqNbr,String merSeqNbr, Context context){
		Onlinetrans record = new Onlinetrans();
		
		record.setTransseqnbr(transSeqNbr);
		record.setMernbr(context.getString("merNbr"));
		record.setMerseqnbr(merSeqNbr);
		record.setTranstypcd("00");
		record.setCheckstatus(CheckStatus.Init);
		record.setTransstatus(TransStatus.INIT);
		record.setTransdate(new Date());
		record.setPaytypcd("8");
		record.setDepartmentnbr("1");
		record.setPayercardtypcd("1");
		record.setPayeeacctnbr("80500001262141000178");
		record.setPayeeacctname("资金中间往来账户");
		record.setPayeeacctkind("0");
		record.setPayeeaccttypcd("2");
		record.setInteralflag("0");
		record.setTranstime(DateUtil.getCurrentDateTime());
		record.setTransamt(new BigDecimal(context.getString("TransAmt")));
		Meracctinfo merAcct =null;
		try {
			merAcct = MeracctinfoDAO.selectByPrimaryKey(context.getString("merNbr"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 商户开户行
		record.setMeropendeptnbr(merAcct.getMeropendeptnbr());
		// 商户发展行
		record.setMerdevdeptnbr(merAcct.getMerdevelopdeptnbr());
		//获得清算机构编号
		record.setDepartmentnbr(DepartmentNbr.THIRD);
		
		try {
			OnlinetransDAO.insert(record);
		} catch (Exception e) {
			// TODO: handle exception
			logger.debug("出错",e);
		}
	}
	
	public static void insertOnlineSubTrans(String transSeqNbr,String merSeqNbr, Context context){
		Onlinesubtrans record1 = new Onlinesubtrans();
		
		record1.setTransseqnbr(transSeqNbr);
		record1.setDepartmentnbr("1");
		record1.setSubtransseqnbr(IDGenerateFactory.generateRtxnNbr());
		record1.setTranscode("UPP10003");
		record1.setTransamt(new BigDecimal(context.getString("TransAmt")));
		record1.setMernbr(context.getString("merNbr"));
		record1.setMerseqnbr(merSeqNbr);
		record1.setTransamt1(BigDecimal.ZERO);
		record1.setRefundedamt(BigDecimal.ZERO);
		record1.setUnrefundamt(new BigDecimal(context.getString("TransAmt")));
		record1.setFeeamt(BigDecimal.ZERO);
		record1.setCurrencycd("CNY");
		record1.setTranstypcd("00");
		record1.setInteralflag("0");
		record1.setMertransdate(new Date());
		record1.setMertransdatetime(DateUtil.getCurrentDateTime());
		record1.setTranstime(DateUtil.getCurrentDateTime());
		record1.setProcstatus(ProcStatus.PENDING);
		record1.setTransstatus(TransStatus.INIT);
		record1.setProcstep(ProcStep.Init);
		record1.setTransdate(new Date());
		try {
			String feenbr=MeracctinfoDAO.selectByPrimaryKey(context.getString("merNbr")).getFeenbr();
			record1.setFeenbr(feenbr);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			record1.setSubtransseqnbr(IDGenerateFactory.generateRtxnNbr());
			OnlinesubtransDAO.insert(record1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void insertOverAllTrans(String transSeqNbr, Context context){
		Overalltrans record = new Overalltrans();
		
		String overAllTransNbr = context.getString("OverallTransNbr");
		
		try {
			record = OveralltransDAO.selectByPrimaryKey(overAllTransNbr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		record.setUppertransnbr(transSeqNbr);
		record.setUppertransdate(record.getTransdate());
		record.setUppertranstime(record.getTranstime());
		record.setOrigoveralltransnbr(context.getString(Dict.ORIG_OVERRALL_TRANS_NBR));
		record.setTransamt(new BigDecimal(context.getString(Dict.TRANS_AMT)));
		record.setPayeeacctnbr("80500001262141000178");
		record.setPayeeacctname("资金中间往来账户");
		record.setPayeracctnbr(context.getString(Dict.PAYER_ACCT_NBR));
		record.setPayeracctname(context.getString(Dict.PAYER_ACCT_NAME));
		record.setCheckstatus(CheckStatus.Init);
		record.setCurrencycd("CNY");
		record.setPointdataflag(InteralFlag.NO);
		record.setCheckdataflag("WXZF");
		
		try {
			OveralltransDAO.updateByPrimaryKey(record);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void insertInnerFundTrans(String TransNbr, Context context){
		Innerfundtrans record = new Innerfundtrans();
		record.setInnerfundtransnbr(IDGenerateFactory.generateInnerFundTransNbr());
		record.setPayeeacctnbr("80500001262141000178");
		record.setOveralltransnbr(context.getString("OverallTransNbr"));
		record.setFundchannelcode("WXZF");
		record.setUppersysnbr(context.getString(Dict.CHNL_ID));
		record.setUppertransdate(new Date());
		record.setUppertranstime(DateUtil.getCurrentDateTime());
		record.setUppertransnbr(TransNbr);
		record.setTransdate(new Date());
		record.setTranscode("QRCO");
		record.setPayeename("资金中间往来账户");
		record.setCurrencycd("CNY");
		record.setTransamt(new BigDecimal(context.getString("TransAmt")));
		record.setTranstime(DateUtil.getCurrentDateTime());
		record.setFeeamt(BigDecimal.ZERO);
		record.setOriginnertransnbr(null);
		record.setTransstatus(TransStatus.INIT);
		record.setCheckstatus(CheckStatus.Init);
		if(record.getTranstime() == null){
			record.setTranstime(DateUtil.getCurrentDateTime());
		}
		record.setCheckdataflag("WXZF");
		record.setPointdataflag(InteralFlag.NO);
		
		try {
			record.setCleardate(DateUtil.DateFormat_To_Date("2020-10-03"));
			InnerfundtransDAO.insert(record);
		} catch (Exception e) {
			throw new PeRuntimeException("Insert Innerfundtrans Table Failed.", e);
		}
	}
	
	public static JSONObject httpPost(String url,JSONObject jsonParam, boolean noNeedResponse){
	        //post请求返回结果
	        DefaultHttpClient httpClient = new DefaultHttpClient();
	        JSONObject jsonResult = null;
	        HttpPost method = new HttpPost(url);
	        try {
	            if (null != jsonParam) {
	                //解决中文乱码问题
	                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
	                entity.setContentEncoding("UTF-8");
	                entity.setContentType("application/json");
	                method.setEntity(entity);
	            }
	            HttpResponse result = httpClient.execute(method);
	            url = URLDecoder.decode(url, "UTF-8");
	            /**请求发送成功，并得到响应**/
	            if (result.getStatusLine().getStatusCode() == 200) {
	                String str = "";
	                try {
	                    /**读取服务器返回过来的json字符串数据**/
	                    str = EntityUtils.toString(result.getEntity());
	                    if (noNeedResponse) {
	                        return null;
	                    }
	                    /**把json字符串转换成json对象**/
	                    jsonResult = JSONObject.parseObject(str);
	                } catch (Exception e) {
	                    logger.error("post请求提交失败:" + url, e);
	                }
	            }
	        } catch (IOException e) {
	        	logger.error("post请求提交失败:" + url, e);
	        }
	        return jsonResult;
	    }

}
