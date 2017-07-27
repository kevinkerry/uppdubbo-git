/**
 * 
 */
package com.csii.upp.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClientError;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.SecureProtocolSocketFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author lixinyou 安全套接字协议工厂类，用于生成简单的安全套接字协议实现类，实现https请求
 */
public class UPPSSLProtocolSocketFactory implements SecureProtocolSocketFactory {

	private SSLContext sslcontext = null;// 安全套接字协议的实现
	Log logger = LogFactory.getLog(UPPSSLProtocolSocketFactory.class);

	public UPPSSLProtocolSocketFactory() {
		super();
	}

	public SSLContext createSSLContext() {
		SSLContext sslContext = null;
		try {
			sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, new TrustManager[] { new TrustAnyTrustManager(null) },
					new java.security.SecureRandom());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new HttpClientError(e.toString());
		}
		return sslContext;
	}

	public SSLContext getSslcontext() {
		if (null == this.sslcontext) {
			this.sslcontext = this.createSSLContext();
		}
		return this.sslcontext;
	}

	@Override
	public Socket createSocket(String host, int port, InetAddress localAddress, int localPort)
			throws IOException, UnknownHostException {
		return getSslcontext().getSocketFactory().createSocket(host, port, localAddress, localPort);
	}

	@Override
	public Socket createSocket(String host, int port, InetAddress localAddress, int localPort,
			HttpConnectionParams httpConnectionParams)
					throws IOException, UnknownHostException, ConnectTimeoutException {
		if (httpConnectionParams == null) {
			throw new IllegalArgumentException("Parameters may not be null");
		}
		int timeout = httpConnectionParams.getConnectionTimeout();
		SocketFactory socketfactory = getSslcontext().getSocketFactory();
		if (timeout == 0) {
			return socketfactory.createSocket(host, port, localAddress, localPort);
		} else {
			Socket socket = socketfactory.createSocket();
			SocketAddress localaddr = new InetSocketAddress(localAddress, localPort);
			SocketAddress remoteaddr = new InetSocketAddress(host, port);
			socket.bind(localaddr);
			socket.connect(remoteaddr, timeout);
			return socket;
		}
	}

	@Override
	public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
		return getSslcontext().getSocketFactory().createSocket(host, port);
	}

	@Override
	public Socket createSocket(Socket socket, String host, int port, boolean autoClose)
			throws IOException, UnknownHostException {
		return getSslcontext().getSocketFactory().createSocket(socket, host, port, autoClose);
	}

	// 信任任何证书的私有实现类
	private static class TrustAnyTrustManager implements X509TrustManager {

		private X509TrustManager standardTrustManager = null;

		public TrustAnyTrustManager(KeyStore keystore) throws NoSuchAlgorithmException, KeyStoreException {
			super();
			TrustManagerFactory factory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			factory.init(keystore);
			TrustManager[] trustmanagers = factory.getTrustManagers();
			if (trustmanagers.length == 0) {
				throw new NoSuchAlgorithmException("no trust manager found");
			}
			this.standardTrustManager = (X509TrustManager) trustmanagers[0];
		}

		@Override
		public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
				throws CertificateException {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
				throws CertificateException {
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return this.standardTrustManager.getAcceptedIssuers();
		}

	}
}
