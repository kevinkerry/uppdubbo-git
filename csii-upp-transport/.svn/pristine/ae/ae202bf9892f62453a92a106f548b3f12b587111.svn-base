/**
 * 
 */
package com.csii.upp.transport;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpMethodRetryHandler;
import org.apache.commons.httpclient.NoHttpResponseException;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csii.pe.service.comm.CommunicationException;
import com.csii.pe.service.comm.Transport;
import com.csii.upp.http.HttpRequest;
import com.csii.upp.http.HttpResponse;
import com.csii.upp.http.UPPSSLProtocolSocketFactory;

/**
 * @author lixinyou 使用HttpClient实现http通信，用于商户通知
 *
 */
public class HttpTransPort implements Transport {

	private boolean isProxy;//是否代理标志
	private String proxyIP;// 代理服务器IP
	private int proxyPort;// 代理服务器端口
	private int connectTimeOut;// 连接超时时间
	
	Log logger = LogFactory.getLog(HttpTransPort.class);

	public HttpTransPort() {
		
		ProtocolSocketFactory pSF = new UPPSSLProtocolSocketFactory();
		Protocol.registerProtocol("https", new Protocol("https", pSF, 443));
	}
	
	@Override
	public Object submit(Object request) throws CommunicationException {
		HttpClient httpClient = new HttpClient();
		// 设置http客户端连接超时时间
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(connectTimeOut);
		if (isProxy) {
			// 设置http请求代理IP和Port
			httpClient.getHostConfiguration().setProxy(proxyIP, proxyPort);
		}
		HttpRequest httpRequest = (HttpRequest) request;
		//设置http或者https协议
//		initProtocol(httpClient, httpRequest);
		HttpMethodRetryHandler myretryhandler = new HttpMethodRetryHandler() {
			public boolean retryMethod(
				final HttpMethod method,
				final IOException exception,
				int executionCount) {
				if (executionCount >= 5) {
					// Do not retry if over max retry count
					return false;
				}
				if (exception instanceof NoHttpResponseException) {
					// Retry if the server dropped connection on us
					return true;
				}
				if (!method.isRequestSent()) {
					// Retry if the request has not been sent fully or
					// if it's OK to retry methods that have been sent
					return true;
				}
				// otherwise do not retry
				return false;
			}
		};
		PostMethod postMethod = new PostMethod(httpRequest.getUrl());
		postMethod.setRequestHeader("Content-type", httpRequest.getContentType());

		if (null != httpRequest.getData()) {
			postMethod.setRequestEntity(new ByteArrayRequestEntity(httpRequest.getData()));
		} else if(null != httpRequest.getRequestMap()){
			for(Map.Entry<String, Object> entry : httpRequest.getRequestMap().entrySet()){
				
				postMethod.setParameter(entry.getKey(), String.valueOf(entry.getValue()));
			}
		}
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, myretryhandler);

		try {
			int resultStatus = 0;
			resultStatus = httpClient.executeMethod(postMethod);

			byte[] httpResponse = postMethod.getResponseBody();
			if (resultStatus == 200) {
				logger.info("http请求成功!" + postMethod.getStatusLine());
			} else
				logger.info("http请求失败!" + postMethod.getStatusLine());
			return new HttpResponse(resultStatus, httpResponse);
		} catch (Exception e) {
			throw new CommunicationException(e);
		} finally {
			postMethod.releaseConnection();
		}
	}
	
	
	/**
	 * 初始化http和https协议
	 * @param httpClient
	 * @param httpRequest
	 * @throws CommunicationException
	 */
	protected void initProtocol(HttpClient httpClient, HttpRequest httpRequest) throws CommunicationException{
		try{
			URL url = new URL(httpRequest.getUrl());
			String protocol = url.getProtocol();
			if("http".equalsIgnoreCase(protocol)){
				httpClient.getHostConfiguration().setHost(url.getHost(), url.getPort());
			}else if("https".equalsIgnoreCase(protocol)){
				@SuppressWarnings("deprecation")
				Protocol httpsProtocol = new Protocol(protocol, new UPPSSLProtocolSocketFactory(), 443);
				httpClient.getHostConfiguration().setHost(url.getHost(), url.getPort(), httpsProtocol);
			}
		}catch(Exception e){
			throw new CommunicationException(e);
		}
	}

	public int getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}

	public String getProxyIP() {
		return proxyIP;
	}

	public void setProxyIP(String proxyIP) {
		this.proxyIP = proxyIP;
	}

	public int getConnectTimeOut() {
		return connectTimeOut;
	}

	public void setConnectTimeOut(int connectTimeOut) {
		this.connectTimeOut = connectTimeOut;
	}

	public boolean getIsProxy() {
		return isProxy;
	}

	public void setIsProxy(boolean isProxy) {
		this.isProxy = isProxy;
	}
}
