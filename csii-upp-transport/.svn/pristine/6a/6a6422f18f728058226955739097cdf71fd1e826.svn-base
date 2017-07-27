package com.csii.upp.transport;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csii.pe.core.PeRuntimeException;
import com.csii.pe.service.comm.CommunicationException;
import com.csii.pe.service.comm.Transport;
import com.csii.upp.util.StringUtil;
import com.unionpay.acp.sdk.SDKConfig;
import com.unionpay.acp.sdk.SDKConstants;
import com.unionpay.acp.sdk.SDKUtil;
import com.unionpay.acp.sdk.SecureUtil;

public class UnionPayTransport implements Transport {
	private static final Log log = LogFactory.getLog(UnionPayTransport.class);
	private String version;
	private String encoding;
	private String signMethod;
	private String accessType;
	private String channelType;
	private String currencyCode;
	private String baseBackUrl;
	private String baseFrontUrl;
	private String merId;
	private String downloadPath;
	private String queryId;
	private HttpClientImpl httpClient;

	public String getBaseFrontUrl() {
		return baseFrontUrl;
	}

	public void setBaseFrontUrl(String baseFrontUrl) {
		this.baseFrontUrl = baseFrontUrl;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getSignMethod() {
		return signMethod;
	}

	public void setSignMethod(String signMethod) {
		this.signMethod = signMethod;
	}

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getBaseBackUrl() {
		return baseBackUrl;
	}

	public void setBaseBackUrl(String baseBackUrl) {
		this.baseBackUrl = baseBackUrl;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getDownloadPath() {
		return downloadPath;
	}

	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public HttpClientImpl getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(HttpClientImpl httpClient) {
		this.httpClient = httpClient;
	}

	public void setAcpSdkConfig(String configPath) throws Exception {
		Properties acpSdkConfig = new Properties();
		acpSdkConfig.load(getClass().getResourceAsStream(configPath));
		SDKConfig.getConfig().loadProperties(acpSdkConfig);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object submit(Object arg0) throws CommunicationException {
		Map contentData = new HashMap((Map) arg0);
		contentData.remove("transDate");
		contentData.remove("transTime");
		if(!"00".equals(contentData.get("txnType"))){
			//查询银联交易状态
			contentData.put("orderId", contentData.get("transNbr"));
		}
		contentData.remove("transNbr");
		contentData.remove("queryId");
		BigDecimal txnAmt = (BigDecimal)contentData.get("txnAmt");
		if (txnAmt != null && !"".equals(contentData.get("txnAmt").toString())) {
			String InttxnAmtx = (txnAmt.multiply(new BigDecimal(100))).toString();
			if (InttxnAmtx.indexOf(".") != -1) {
				contentData.put("txnAmt", InttxnAmtx.substring(0, InttxnAmtx.indexOf(".")));
			} else {
				contentData.put("txnAmt", InttxnAmtx);
			}
		}
		
		
		contentData.put(SDKConstants.param_version, version);
		contentData.put(SDKConstants.param_encoding, encoding);
		contentData.put("signMethod", signMethod);
		contentData.put(SDKConstants.param_accessType, accessType);
		contentData.put("channelType", channelType);
		contentData.put(SDKConstants.param_currencyCode, currencyCode);
		if (contentData.get(SDKConstants.param_backUrl) != null)
			contentData.put(SDKConstants.param_backUrl, baseBackUrl
					+ contentData.get(SDKConstants.param_backUrl));
		
		if("00".equals(contentData.get("txnType"))){
			//查询银联交易状态
			contentData.remove(SDKConstants.param_backUrl);
			contentData.remove(SDKConstants.param_frontUrl);
			contentData.put("url", SDKConfig.getConfig().getSingleQueryUrl());
		}
		
		String url = contentData.get("url") != null ? (String) contentData
				.get("url") : contentData.get("fileType") != null ? SDKConfig
				.getConfig().getFileTransUrl() : SDKConfig.getConfig()
				.getBackRequestUrl();
		contentData.remove("url");
				
		return submitUrl(signData(contentData), url);
	}

	/**
	 * java main方法 数据提交 提交到后台
	 * 
	 * @param contentData
	 * @return 返回报文 map
	 * @throws CommunicationException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, String> submitUrl(Map<String, String> submitFromData,
			String requestUrl) throws CommunicationException {
		log.info("银联交易：请求地址->" + requestUrl);
		log.info("银联交易：请求数据-> " + submitFromData.toString());
		Map requestMap = new HashMap(submitFromData);
		Map<String, String> resData;
		/**
		 * 发送
		 */
		try {
			String resultString = httpClient.send(submitFromData,
					(String) submitFromData.get("encoding"),requestUrl);
			/**
			 * 验证签名
			 */
			if (!StringUtil.isStringEmpty(resultString)) {
				// 将返回结果转换为map
				resData = SDKUtil.convertResultStringToMap(resultString);
				if (SDKUtil.validate(resData,
						(String) submitFromData.get("encoding"))) {
					log.info("验证签名成功");
					deCodeFileContent(resData);
				} else {
					log.error("验证签名失败");
					throw new CommunicationException();
				}
				// 打印返回报文
				log.info("打印返回报文：" + resultString);
			} else {
				resData = new HashMap<String, String>();
			}
			resData.putAll(requestMap);
		} catch (Exception e) {
			log.error("发送报文失败", e);
			throw new CommunicationException(e);
		}
		return resData;
	}

	/**
	 * java main方法 数据提交 　　 对数据进行签名
	 * 
	 * @param contentData
	 * @return　签名后的map对象
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> signData(Map<String, ?> contentData) {
		Entry<String, ?> obj = null;
		Map<String, String> submitFromData = new HashMap<String, String>();
		for (Iterator<?> it = contentData.entrySet().iterator(); it.hasNext();) {
			obj = (Entry<String, ?>) it.next();
			String value = obj.getValue() == null ? null : obj.getValue()
					.toString();
			if (!StringUtil.isStringEmpty(value)) {
				// 对value值进行去除前后空处理
				submitFromData.put(obj.getKey(), value.trim());
				log.debug(obj.getKey() + "-->" + String.valueOf(value));
			}
		}
		/**
		 * 签名
		 */
		try {
			SDKUtil.sign(submitFromData,
					(String) submitFromData.get("encoding"));
		} catch (Exception e) {
			log.error("银联交易：数据签名异常", e);
			throw new PeRuntimeException(e);
		}
		return submitFromData;
	}

	/**
	 * 解析返回文件
	 */
	public void deCodeFileContent(Map<String, String> resData) {
		// 解析返回文件
		String fileContent = resData.get(SDKConstants.param_fileContent);
		if (!StringUtil.isStringEmpty(fileContent)) {
			try {
				byte[] fileArray = SecureUtil.inflater(SecureUtil
						.base64Decode(fileContent.getBytes((String) resData
								.get("encoding"))));
				String root = downloadPath;
				String filePath = null;
				if (SDKUtil.isEmpty(resData.get("fileName"))) {
					filePath = root + resData.get("merId")
							+ "_" + resData.get("batchNo") + "_"
							+ resData.get("txnTime") + ".txt";
				} else {
					filePath = root + resData.get("fileName");
				}
				log.debug("file path:" + filePath);
				File file = new File(filePath);
				File dir = file.getParentFile();
				if (dir.exists()) {
					if (file.exists()) {
						file.delete();
					}
				} else {
					dir.mkdirs();
				}
				file.createNewFile();
				FileOutputStream out = new FileOutputStream(file);
				out.write(fileArray, 0, fileArray.length);
				out.flush();
				out.close();
			} catch (Exception e) {
				log.error("解析文件失败", e);
			}
		}
	}

	/**
	 * Unionpay 前台交易提交
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String createHtml(Map contentData) {
		Map hiddens = signData(contentData);
		StringBuilder sf = new StringBuilder();
		sf.append("<form id = \"otherBank\" action=\""
				+ SDKConfig.getConfig().getFrontRequestUrl()
				+ "\" method=\"post\">");
		if (null != hiddens && 0 != hiddens.size()) {
			Set<Entry<String, String>> set = hiddens.entrySet();
			Iterator<Entry<String, String>> it = set.iterator();
			while (it.hasNext()) {
				Entry<String, String> ey = it.next();
				String key = ey.getKey();
				String value = ey.getValue();
				sf.append("<input type=\"hidden\" name=\"" + key + "\" id=\""
						+ key + "\" value=\"" + value + "\"/>");
			}
		}
		sf.append("</form>");
		return sf.toString();
	}
}
