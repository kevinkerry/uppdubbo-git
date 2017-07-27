package com.csii.upp.transport;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csii.pe.core.PeException;
import com.csii.pe.service.comm.CommunicationException;
import com.unionpay.acp.sdk.BaseHttpSSLSocketFactory;
import com.unionpay.acp.sdk.BaseHttpSSLSocketFactory.TrustAnyHostnameVerifier;
import com.unionpay.acp.sdk.LogUtil;

/**
 * 重写银联HttpClient增加代理配置
 * 
 * @author gaoyu
 *
 */
public class HttpClientImpl {
	private static final Log log = LogFactory.getLog(HttpClientImpl.class);

	/**
	 * 通信连接超时时间
	 */
	private int connectionTimeout;

	/**
	 * 通信读超时时间
	 */
	private int readTimeOut;
	/**
	 * 代理IP
	 */
	private String proxyIp;
	/**
	 * 代理端口
	 */
	private int proxyPort;
	
	
	
	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getReadTimeOut() {
		return readTimeOut;
	}

	public void setReadTimeOut(int readTimeOut) {
		this.readTimeOut = readTimeOut;
	}

	public String getProxyIp() {
		return proxyIp;
	}

	public void setProxyIp(String proxyIp) {
		this.proxyIp = proxyIp;
	}

	public int getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}

	/**
	 * 发送信息到服务端
	 * 
	 * @param data
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public String send(Map<String, String> data, String encoding,String requestUrl) throws Exception {
		try {
			HttpURLConnection httpURLConnection = createConnection(encoding,requestUrl);
			this.requestServer(httpURLConnection, this.getRequestParamString(data, encoding), encoding);
			return this.response(httpURLConnection, encoding);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * HTTP Post发送消息
	 *
	 * @param connection
	 * @param message
	 * @throws IOException
	 */
	private void requestServer(final URLConnection connection, String message, String encoder) throws Exception {
		PrintStream out = null;
		try {
			connection.connect();
			out = new PrintStream(connection.getOutputStream(), false, encoder);
			out.print(message);
			out.flush();
		} catch (Exception e) {
			throw e;
		} finally {
			if (null != out) {
				out.close();
			}
		}
	}

	/**
	 * 显示Response消息
	 *
	 * @param connection
	 * @param CharsetName
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	private String response(final HttpURLConnection connection, String encoding)
			throws URISyntaxException, IOException, Exception {
		InputStream in = null;
		StringBuilder sb = new StringBuilder(1024);
		BufferedReader br = null;
		try {
			int status= connection.getResponseCode();
			log.info("HTTP Return Status-Code:[" + status + "]");
			if (200 == connection.getResponseCode()) {
				in = connection.getInputStream();
				sb.append(new String(read(in), encoding));
				return sb.toString();
			} else {
				in = connection.getErrorStream();
				sb.append(new String(read(in), encoding));
				throw new CommunicationException("返回状态码：" + status+" ,返回信息:"+sb.toString());
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (null != br) {
				br.close();
			}
			if (null != in) {
				in.close();
			}
			if (null != connection) {
				connection.disconnect();
			}
		}
	}

	public static byte[] read(InputStream in) throws IOException {
		byte[] buf = new byte[1024];
		int length = 0;
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		while ((length = in.read(buf, 0, buf.length)) > 0) {
			bout.write(buf, 0, length);
		}
		bout.flush();
		return bout.toByteArray();
	}

	/**
	 * 创建连接
	 *
	 * @return
	 * @throws ProtocolException
	 */
	private HttpURLConnection createConnection(String encoding,String requestUrl) throws PeException {
		HttpURLConnection httpURLConnection = null;
		try {
			URL url = new URL(requestUrl);
			if (proxyIp != null && !proxyIp.equals("")) {
				log.info("代理ip-->" + proxyIp + ", 代理port-->" + proxyPort);
				Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyIp, proxyPort));

				httpURLConnection = (HttpURLConnection) url.openConnection(proxy);
				/*
				 * 设置用户名密码String encoded = new String(Base64.base64Encode(new
				 * String("username:password").getBytes()));
				 * httpURLConnection.setRequestProperty("Proxy-Authorization",
				 * "Basic " + encoded);
				 */
			} else {
				httpURLConnection = (HttpURLConnection) url.openConnection();
			}
			httpURLConnection.setConnectTimeout(this.connectionTimeout);// 连接超时时间
			httpURLConnection.setReadTimeout(this.readTimeOut);// 读取结果超时时间
			httpURLConnection.setDoInput(true); // 可读
			httpURLConnection.setDoOutput(true); // 可写
			httpURLConnection.setUseCaches(false);// 取消缓存
			httpURLConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=" + encoding);
			httpURLConnection.setRequestMethod("POST");
			if ("https".equalsIgnoreCase(url.getProtocol())) {
				HttpsURLConnection husn = (HttpsURLConnection) httpURLConnection;
				husn.setSSLSocketFactory(new BaseHttpSSLSocketFactory());
				husn.setHostnameVerifier(new TrustAnyHostnameVerifier());// 解决由于服务器证书问题导致HTTPS无法访问的情况
				return husn;
			}
		} catch (Exception e) {
			throw new PeException(e);
		}

		return httpURLConnection;
	}

	/**
	 * 将Map存储的对象，转换为key=value&key=value的字符
	 *
	 * @param requestParam
	 * @param coder
	 * @return
	 */
	private String getRequestParamString(Map<String, String> requestParam, String coder) {
		if (null == coder || "".equals(coder)) {
			coder = "UTF-8";
		}
		StringBuilder sf = new StringBuilder("");
		String reqstr = "";
		if (null != requestParam && 0 != requestParam.size()) {
			for (Entry<String, String> en : requestParam.entrySet()) {
				try {
					sf.append(en.getKey() + "=" + (null == en.getValue() || "".equals(en.getValue()) ? ""
							: URLEncoder.encode(en.getValue(), coder)) + "&");
				} catch (UnsupportedEncodingException e) {
					log.error(e.getMessage());
					return "";
				}
			}
			reqstr = sf.substring(0, sf.length() - 1);
		}
		LogUtil.writeLog("请求报文:[" + reqstr + "]");
		return reqstr;
	}
}