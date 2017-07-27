/**
 * 
 */
package com.csii.upp.paygate.servlet;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

import com.csii.pe.core.PeException;
import com.csii.pe.core.PeRuntimeException;
import com.csii.pe.service.comm.CommunicationException;
import com.csii.pe.transform.TransformException;
import com.csii.pe.transform.Transformer;
import com.csii.pe.transform.TransformerFactory;
import com.csii.upp.http.UPPSSLProtocolSocketFactory;

/**
 * @author lixinyou 检查支付交易中的商户是否存在
 */
public class CheckMerExist implements InitializingBean {

	private boolean checkFlag;
	private String filePath;
	private TransformerFactory transformerFactory;
	private String parserResolverName;
	private Log log = LogFactory.getLog(CheckMerExist.class);
	private Properties properties;

	public CheckMerExist() {
		properties = new Properties();
		setCheckFlag(false);
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("开始加载商户信息!");
		BufferedReader bufReader = new BufferedReader(new FileReader(filePath));
		this.properties.load(bufReader);
	}

	public Map parserPalain(Object plain) {
		Transformer transformer = transformerFactory.getTransformer(parserResolverName);
		Map data = null;
		try {
			data = (Map) transformer.parse(new ByteArrayInputStream(((String) plain).getBytes()), null);
		} catch (TransformException e) {
			log.error(e.getMessage());
		}
		return data;
	}

	public StringBuilder checkIsExist(HttpServletRequest request) throws PeException {
		StringBuilder form = null;
		if (this.properties == null) {
			throw new PeRuntimeException("saving merchant properties is not exist!");
		}
		String plain = request.getParameter(Const.PLAIN);
		String signature = request.getParameter(Const.SIGNATURE);
		log.debug(">>>>>>>plain>>>>>>>>"+plain);
		log.debug(">>>>>>>signature>>>>>>>>"+signature);
		Map inputData = this.parserPalain(plain);
		if (inputData == null) {
			throw new PeException("plain can not null!");
		}
		if(inputData.get(Const.PARENT_MERCHANT_ID) != null){
			if(!this.properties.contains(inputData.get(Const.PARENT_MERCHANT_ID))){
				return transmitepay(inputData,form,request);
			}
		}else if (!this.properties.contains(inputData.get(Const.MERCHANT_ID))) {
			return transmitepay(inputData,form,request);
		}
		return form;
	}

	private StringBuilder transmitepay(Map inputData,StringBuilder form,HttpServletRequest request) throws PeException {
		//发1.0
		// if 是 IPER IPEM
			String transId = (String) inputData.get("TransId");
			log.debug(">>>>>>>>>>transId>>>>>>" + transId);
			if ("IPER".equals(transId) || "IPEM".equals(transId)) {
				form = new StringBuilder();
				form.append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=" + "UTF-8"
						+ "\"/></head><body>");
				form.append("<form id = \"dispatcher\" action=\"" + properties.getProperty("pp.url")
						+ "\" method=\"post\">");

				form.append("<input type=\"hidden\" name=\"" + Const.PLAIN + "\" id=\"" + Const.PLAIN + "\" value=\""
						+ request.getParameter(Const.PLAIN) + "\"/>");
				form.append("<input type=\"hidden\" name=\"" + Const.SIGNATURE + "\" id=\"" + Const.SIGNATURE
						+ "\" value=\"" + request.getParameter(Const.SIGNATURE) + "\"/>");
				System.out.println(form);
				form.append("</form>");
				form.append("<script type=\"text/javascript\">");
				form.append("document.getElementById(\"dispatcher\").submit();");

				form.append("</script>");
				form.append("</body>");

				form.append("</html>");
				log.debug(form);
				return form;
			} else {
				return this.submit(request);
			}

		
	}
	public StringBuilder submit(HttpServletRequest request) throws CommunicationException {

		URL url = null;
		String stringurl = properties.getProperty("pp.url2");
		log.debug(">>>>>>>>>>stringurl>>>>>>>>>>>" + stringurl);
		try {
			url = new URL(stringurl);
		} catch (MalformedURLException e1) {
			log.debug(e1.getMessage());
		}

		HttpClient httpClient = new HttpClient();
		// 设置http客户端连接超时时间
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(60000);
		// 设置http或者https协议
		initProtocol(httpClient, url);
		PostMethod postMethod = new PostMethod(stringurl);
		postMethod.setRequestHeader("Content-type", request.getContentType());
		postMethod.setRequestHeader(HttpMethodParams.HTTP_CONTENT_CHARSET, request.getCharacterEncoding());
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		postMethod.addParameter(Const.PLAIN, request.getParameter(Const.PLAIN));
		postMethod.addParameter(Const.SIGNATURE, request.getParameter(Const.SIGNATURE));
		try {
			int resultStatus = 0;
			resultStatus = httpClient.executeMethod(postMethod);
			log.debug(">>>>>>>>>>>>开始发送>>>>>>>>>>>>>>");
			byte[] httpResponse = postMethod.getResponseBody();

			if (resultStatus == 200) {
				log.info("http请求成功!" + postMethod.getStatusLine());
			} else
				log.info("http请求失败!" + postMethod.getStatusLine());
			log.debug(">>>>>>>>>>>>RequestCharacterEncoding>>>>>>>>>>>>>>>>>>"+request.getCharacterEncoding());
			StringBuilder buf = new StringBuilder(new String(httpResponse, request.getCharacterEncoding()));
			log.debug(">>>>>>>>buf>>>>>>>>>" + buf);
			return buf;
		} catch (Exception e) {
			log.error(">>>>>>>>>发送异常>>>>>>>>>>>>>>>" + e.getMessage());
			throw new CommunicationException(e);
		} finally {
			postMethod.releaseConnection();
		}
	}

	/**
	 * 初始化http和https协议
	 * 
	 * @param httpClient
	 * @param httpRequest
	 * @throws CommunicationException
	 */
	protected void initProtocol(HttpClient httpClient, URL url) throws CommunicationException {
		try {
			String protocol = url.getProtocol();
			if ("http".equalsIgnoreCase(protocol)) {
				log.debug(">>>>>>protocol>>>>>>" + protocol);
				log.debug(">>>>>>Host>>>>>>>>" + url.getHost());
				log.debug(">>>>>>Port>>>>>>>>" + url.getPort());
				httpClient.getHostConfiguration().setHost(url.getHost(), url.getPort());
			} else if ("https".equalsIgnoreCase(protocol)) {
				@SuppressWarnings("deprecation")
				Protocol httpsProtocol = new Protocol(protocol, new UPPSSLProtocolSocketFactory(), 443);
				httpClient.getHostConfiguration().setHost(url.getHost(), url.getPort(), httpsProtocol);
			}
		} catch (Exception e) {
			throw new CommunicationException(e);
		}
	}

	
	public TransformerFactory getTransformerFactory() {
		return transformerFactory;
	}

	public void setTransformerFactory(TransformerFactory transformerFactory) {
		this.transformerFactory = transformerFactory;
	}

	public String getParserResolverName() {
		return parserResolverName;
	}

	public void setParserResolverName(String parserResolverName) {
		this.parserResolverName = parserResolverName;
	}

	public boolean getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(boolean checkFlag) {
		this.checkFlag = checkFlag;
	}
}
