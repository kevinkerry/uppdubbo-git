package com.csii.upp.payment.event.handler;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csii.pe.core.PeException;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.InteralFlag;
import com.csii.upp.constant.NotifyStatus;
import com.csii.upp.constant.NotifyTypCd;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.dao.generate.MeracctinfoDAO;
import com.csii.upp.dao.generate.MernotifiyregDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.extend.SubTransData;
import com.csii.upp.dto.generate.Meracctinfo;
import com.csii.upp.dto.generate.Mernotifiyreg;
import com.csii.upp.event.EventHandler;
import com.csii.upp.http.HttpRequest;
import com.csii.upp.http.HttpResponse;
import com.csii.upp.payment.helper.XmlSignatureHelper;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 商户异步通知
 * 
 * @author 徐锦
 *
 */
public class NotifyMerResultEventHandler implements EventHandler<NotifyMerResultEvent> {
	protected static Log log = LogFactory.getLog(NotifyMerResultEventHandler.class);

	@SuppressWarnings("deprecation")
	@Override
	public void handler(NotifyMerResultEvent event) {
		Map paramMap = event.getParamMap();
		InputPaymentData inputData = (InputPaymentData) paramMap.get(Dict.INPUT_PAYMENT_DATA);
		Map<String, Object> merResult = new HashMap<String, Object>();
		
		// 备用字段1：商户结果通知地址
		String URL = inputData.getMemo1();
		if (!StringUtil.isObjectEmpty(URL)) {
			URL = URL.trim();
		}
		String plainData = null;
		String signData = null;
		HttpResponse httpResponse = null;
		
		//特殊商户异步通知的格式不一样是一个个参数，不是连一起发过去
		if (!StringUtil.isStringEmpty(inputData.getMernbr()) && inputData.getMernbr().startsWith("88")) {
			merResult.put("encoding", URLEncoder.encode("UTF-8"));
			merResult.put("merId", URLEncoder.encode(inputData.getMernbr()));
			merResult.put("orderId", URLEncoder.encode(inputData.getMerseqnbr()));
			merResult.put("orderAmt", new BigDecimal(URLEncoder.encode(String.valueOf(inputData.getTransamt()))).multiply(new BigDecimal(100)));
			merResult.put("payType", URLEncoder.encode(inputData.getChannelnbr()));
			merResult.put("bizType", URLEncoder.encode(inputData.getBizType()));
			merResult.put("requestModel", URLEncoder.encode(inputData.getRequestModel()));	//支付
			merResult.put("txnTime", URLEncoder.encode(String.valueOf(DateUtil.Date_To_DateTimeFormat(inputData.getTranstime(), DateFormatCode.DATETIME_FORMATTER3))));
			merResult.put("tradeCode", URLEncoder.encode(inputData.getTradeCode()));
			merResult.put("respCode", URLEncoder.encode("000000".equals(paramMap.get(Dict.RESP_CODE))?"00":"99"));
			merResult.put("respMsg", URLEncoder.encode("000000".equals(paramMap.get(Dict.RESP_CODE))?"成功":"失败"));
			merResult.put("tradeRespCode", URLEncoder.encode((String) paramMap.get(Dict.RESP_CODE)));
			merResult.put("tradeRespMsg", URLEncoder.encode((String) paramMap.get(Dict.RESP_MESSAGE)));
			merResult.put("tradeNo", URLEncoder.encode(inputData.getTransseqnbr()));
			plainData = packPlain(merResult);
			try{
				signData = xmlSignature.xmlsignPlain(plainData);
				log.info("财政网明文："+ plainData );
				log.info("财政网密文："+ signData );
			} catch (PeException e) {
				log.error("财政网异步商户结果通知加签明文失败!", e);
			}
			merResult.put("signature", signData);
			
			try {
				httpResponse = this.resultMapSend(URL, merResult, event);
			} catch (PeException e) {
				log.error("财政网异步商户结果通知失败!", e);
			}
		} else {

			merResult.put("transId", "PSNR");
			merResult.put("merchantId", inputData.getMernbr());
			merResult.put("merSeqNo", inputData.getMerseqnbr());
			merResult.put("merDateTime", inputData.getMertransdatetime());
			merResult.put("transSeqNo", inputData.getTransseqnbr());
			merResult.put("clearDate", inputData.getCleardate());
			merResult.put("transAmt", inputData.getTransamt());
			merResult.put("respCode", paramMap.get(Dict.RESP_CODE));
			merResult.put("remark1", "remark1");
			merResult.put("remark2", "remark2");
			merResult.put("Mernbr", inputData.getMernbr());
			if(PayTypCd.OTHACTSCAN.equals(inputData.getPaytypcd())
					||PayTypCd.OTHPASSCAN.equals(inputData.getPaytypcd())){
				merResult.put("payType",inputData.getScancodemode());
				merResult.put("payAccessType",inputData.getCodetypcd());
				merResult.put("receiptAmount", inputData.getTransamt());
				merResult.put("transAmt", inputData.getTransamt().add(inputData.getDiscountableamt()));
			}
			// 积分支付通知商户内容
			if (InteralFlag.YES.equals(inputData.getInteralflag())) {
				merResult.put(Dict.INTERAL_FLAG, inputData.getInteralflag());
				List<Map<String, Object>> MerTransDetailList = new ArrayList<Map<String, Object>>();
				for (SubTransData subTrans : inputData.getOnlineSubTransList()) {
					Map<String, Object> subTransMap = new HashMap<String, Object>();
					subTransMap.put(Dict.MER_SEQ_NBR, subTrans.getMerseqnbr());
					subTransMap.put(Dict.MER_NBR, subTrans.getMernbr());
					subTransMap.put(Dict.TRANS_AMT, subTrans.getTransamt());
					subTransMap.put(Dict.DEDUCT_AMT, subTrans.getDeductamt());
					subTransMap.put(Dict.REAL_AMT, subTrans.getRealamt());
					MerTransDetailList.add(subTransMap);
					merResult.put("merTransDetailList", MerTransDetailList);
				}
			}
			if (StringUtil.isStringEmpty((String) paramMap.get(Dict.MER_PLATFORM_NBR))) {
				Meracctinfo merAcct = null;
				try {
					merAcct = MeracctinfoDAO.selectByPrimaryKey(inputData.getMernbr());
					if (merAcct == null) {
						throw new PeException(DictErrors.MERCHANT_NOT_EXIST);
					}
				} catch (Exception e) {
					log.error("*******异步商户结果通知查询商户失败!*********");
				}
				merResult.put(Dict.MER_PLATFORM_NBR, merAcct.getMerplatformnbr());
			} else {
				merResult.put(Dict.MER_PLATFORM_NBR, paramMap.get(Dict.MER_PLATFORM_NBR));
			}
			
			
			try {
				plainData = xmlSignature.format(merResult,formatName);
				signData = xmlSignature.xmltransformsign(merResult,formatName);
				httpResponse = this.resultSend(URL, signData, event);
			} catch (PeException e) {
				log.error("异步商户结果通知失败!", e);
			}
		}
		
		Mernotifiyreg notifyRecord = new Mernotifiyreg();

		if (httpResponse.getResultStatus() == 200) {
			notifyRecord.setNotifystatus(NotifyStatus.SUCCESS);
			log.info("异步商户结果通知成功!");
		} else {
			notifyRecord.setNotifystatus(NotifyStatus.FAILURE);
		}

		try {
			notifyRecord.setMernbr(inputData.getMernbr());
			notifyRecord.setTransseqnbr(inputData.getTransseqnbr());
			notifyRecord.setSignature(signData);
			notifyRecord.setNotifytype(NotifyTypCd.MerNotify);
			notifyRecord.setTransdate(inputData.getTransdate());
			notifyRecord.setPlain(plainData);
			notifyRecord.setMerurl(URL);
			notifyRecord.setSendtimes(new Long(1));
			MernotifiyregDAO.insert(notifyRecord);
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

	private HttpResponse resultSend(String URL, String signData, NotifyMerResultEvent event) throws PeException {
		HttpRequest httpRequest = new HttpRequest("text/xml; charset=UTF-8", signData.getBytes(), URL,null);
		log.info("******异步通知商户结果开始******");
		log.info("******异步通知商户地址：" + URL);
		HttpResponse httpResponse = event.getNotifyService().httpsend(httpRequest);
		log.info("******异步通知商户结果结束******");
		return httpResponse;
	}
	
	//财政网结果通知
	private HttpResponse resultMapSend(String URL, Map<String, Object> merResultMap, NotifyMerResultEvent event) throws PeException {
		HttpRequest httpRequest = new HttpRequest("application/x-www-form-urlencoded",null, URL,merResultMap);
		log.info("******财政网异步通知商户结果开始******");
		log.info("******财政网异步通知商户地址：" + URL);
		HttpResponse httpResponse = event.getNotifyService().httpsend(httpRequest);
		log.info("******财政网异步通知商户结果结束******");
		return httpResponse;
	}
	
	/**
	 * 验签规则，按非空字段升序
	 */
	private String packPlain(Map<String, Object> plainMap) {
		List<Map.Entry<String, Object>> list = new ArrayList<Map.Entry<String,Object>>(plainMap.entrySet()); 
		//排序  可能有问题
		Collections.sort(list,new Comparator<Map.Entry<String, Object>>() {
			@Override
			public int compare(Entry<String, Object> o1, Entry<String, Object> o2) {
				
				return o1.getKey().compareTo(o2.getKey());
			}
		});
		StringBuilder sb = new StringBuilder();
		for(Entry<String, Object> entry : list){
			sb.append("<").append(entry.getKey()).append(">").append(entry.getValue()).append("</").append(entry.getKey()).append(">");
		}
		return sb.toString();
	}


	private String formatName;
	private XmlSignatureHelper xmlSignature;

	public String getFormatName() {
		return formatName;
	}

	public void setFormatName(String formatName) {
		this.formatName = formatName;
	}

	public XmlSignatureHelper getXmlSignature() {
		return xmlSignature;
	}

	public void setXmlSignature(XmlSignatureHelper xmlSignature) {
		this.xmlSignature = xmlSignature;
	}

	@Override
	public Class<NotifyMerResultEvent> getAcceptedEventType() {
		return NotifyMerResultEvent.class;
	}
	
	

}
