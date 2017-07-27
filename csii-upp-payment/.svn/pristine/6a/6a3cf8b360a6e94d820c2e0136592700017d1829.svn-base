package com.csii.upp.payment.event.handler;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csii.pe.core.PeException;
import com.csii.pe.service.comm.Transport;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.NotifyStatus;
import com.csii.upp.constant.NotifyTypCd;
import com.csii.upp.dao.generate.MernotifiyregDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Mernotifiyreg;
import com.csii.upp.event.EventHandler;
import com.csii.upp.http.HttpRequest;
import com.csii.upp.http.HttpResponse;
import com.csii.upp.payment.helper.XmlSignatureHelper;
import com.csii.upp.service.payment.NotifyService;
import com.csii.upp.signature.UppSignature;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

public class NotifyDZKNResultEventHandler implements
		EventHandler<NotifyDZKNResultEvent> {
	protected static Log log = LogFactory
			.getLog(NotifyDZKNResultEventHandler.class);

	private Transport transport;
	private String formatName;
	private UppSignature signature;
	private int notifyTime;
	private String messageTypCd;
	private String userId;
	private String secret;
	private String URL;
	private String source;
	private XmlSignatureHelper xmlSignature;
	private int deplayTime = 3000;

	@Override
	public void handler(NotifyDZKNResultEvent event) {
		// true:表示实时发送失败进行定时发送；false:实时发送
		if (event.isScheduleNotify()) {
			this.scheduleNotify(event);
		} else {
			this.realTimeNotify(event);
		}
	}

	private void scheduleNotify(NotifyDZKNResultEvent event) {
		Map paramMap = event.getParamMap();
		Mernotifiyreg notifyRecord = (Mernotifiyreg) paramMap
				.get(Dict.INPUT_PAYMENT_DATA);
		boolean result = false;
		try {
			result = this.resultSend(notifyRecord, event);
			if (result) {
				Mernotifiyreg record = new Mernotifiyreg();
				record.setTransseqnbr(notifyRecord.getTransseqnbr());
				record.setNotifystatus(NotifyStatus.SUCCESS);
				MernotifiyregDAO.updateByPrimaryKeySelective(record);
			}
		} catch (Exception e) {
			log.error(e);
		}
	}

	private void realTimeNotify(NotifyDZKNResultEvent event) {
		Map paramMap = event.getParamMap();
		InputPaymentData inputData = (InputPaymentData) paramMap
				.get(Dict.INPUT_PAYMENT_DATA);
		Map<String, Object> merResult = new HashMap<String, Object>();
		merResult.put("transTime", inputData.getTranstime());
		merResult.put(Dict.MER_NBR, inputData.getMernbr());
		if(null == inputData.getMerseqnbr()){
			merResult.put(Dict.MER_SEQ_NBR, inputData.getTransseqnbr());
		}else
			merResult.put(Dict.MER_SEQ_NBR, inputData.getMerseqnbr());
		merResult.put(Dict.TRANS_SEQ_NBR, inputData.getTransseqnbr());
		merResult.put(Dict.TRANS_AMT,
				(BigDecimal) paramMap.get(Dict.TOTAL_TRANS_AMT));
		merResult.put(Dict.PAYER_ID_NBR, inputData.getPayeridnbr());
		merResult.put(Dict.PAYER_ACCT_NAME, inputData.getPayeracctname());
		String stringPlain = null;
		String signData = null;
		String timestamp = DateUtil.Date_To_DateTimeFormat(
				inputData.getTranstime(), DateFormatCode.DATETIME_FORMATTER1);
		try {
			stringPlain = xmlSignature.format(merResult,this.getMessageTypCd());
			String preSign = this.getUserId() + this.getSecret() + timestamp;
			signData = signature.md5Sign(preSign);
		} catch (PeException e) {
			log.error(e.getMessage());
		}

		String URL = this.getURL();
		URL = URL + "?userid=" + this.getUserId();
		try {
			URL = URL + "&timestamp=" + URLEncoder.encode(timestamp, "UTF-8")
					+ "&sign=" + signData + "&xmlstr="
					+ URLEncoder.encode(stringPlain, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("UnsupportedEncodingException");
		}
		URL = URL + "&msgtype=" + this.getMessageTypCd();
		Mernotifiyreg notifyRecord = new Mernotifiyreg();
		notifyRecord.setMernbr(inputData.getMernbr());
		notifyRecord.setTransseqnbr(inputData.getTransseqnbr());
		notifyRecord.setSignature(signData);
		notifyRecord.setNotifytype(NotifyTypCd.ElecPortNotify);
		notifyRecord.setTransdate(inputData.getTransdate());
		notifyRecord.setPlain(stringPlain);
		notifyRecord.setMerurl(URL);
		notifyRecord.setSendtimes(new Long(this.getNotifyTime()));
		boolean result = false;
		try {
			result = this.resultSend(notifyRecord, event);
		} catch (InterruptedException e) {
		}
		if (result) {
			notifyRecord.setNotifystatus(NotifyStatus.SUCCESS);
		} else {
			notifyRecord.setNotifystatus(NotifyStatus.FAILURE);
		}
		try {
			MernotifiyregDAO.insert(notifyRecord);
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

	private boolean resultSend(Mernotifiyreg notifyRecord,
			NotifyDZKNResultEvent event) throws InterruptedException {
		String url = notifyRecord.getMerurl();
		log.info("******电子口岸通知请求参数URL******" + url);
		HttpRequest httpRequest = new HttpRequest("text/xml; charset=UTF-8",
				 notifyRecord.getPlain().getBytes() , url, null);
		NotifyService notifyService = event.getNotifyService();

		int sendTime = 0;
		int maxSendTime = StringUtil.parseInteger(notifyRecord.getSendtimes());
		boolean result = false;
		HttpResponse httpResponse = null;
		log.info("******异步电子口岸通知开始******");
		while (sendTime < maxSendTime) {
			try {
				sendTime++;
				log.info("后台【第 " + sendTime + "次】发送电子口岸通知");
				httpResponse = notifyService.httpsend(httpRequest);
				Map respMap = xmlSignature.parse(httpResponse.getData());
				if (null != respMap.get("Result")
						&& "T".equals(respMap.get("Result"))) {
					result = true;
					log.info("异步*电子口岸通知成功!");
					break;
				}
				log.info("异步*电子口岸通知失败!");
				if (deplayTime > 0) {
					Thread.sleep(deplayTime);
				}
			} catch (Exception e) {
				log.error("异步*电子口岸通知失败!", e);
				if (deplayTime > 0) {
					Thread.sleep(deplayTime);
				}
			}
		}
		log.info("******异步电子口岸通知结束******");
		return result;
	}

	@Override
	public Class<NotifyDZKNResultEvent> getAcceptedEventType() {
		return NotifyDZKNResultEvent.class;
	}

	public String getMessageTypCd() {
		return messageTypCd;
	}

	public void setMessageTypCd(String messageTypCd) {
		this.messageTypCd = messageTypCd;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public int getNotifyTime() {
		return notifyTime;
	}

	public void setNotifyTime(int notifyTime) {
		this.notifyTime = notifyTime;
	}

	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

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

	public UppSignature getSignature() {
		return signature;
	}

	public void setSignature(UppSignature signature) {
		this.signature = signature;
	}

}
