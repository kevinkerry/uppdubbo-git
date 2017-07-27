package com.csii.upp.transport.restful.ws;

import com.sun.jersey.api.client.config.DefaultClientConfig;

public class RestClientConfig extends DefaultClientConfig {
	
	private int connectTimeout;
	private int readTimeout;
	private int threadPoolSize;
	private boolean redireactFlag;
	
	public int getConnectTimeout() {
		return connectTimeout;
	}
	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}
	public int getReadTimeout() {
		return readTimeout;
	}
	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}
	public int getThreadPoolSize() {
		return threadPoolSize;
	}
	public void setThreadPoolSize(int threadPoolSize) {
		this.threadPoolSize = threadPoolSize;
	}
	public boolean isRedireactFlag() {
		return redireactFlag;
	}
	public void setRedireactFlag(boolean redireactFlag) {
		this.redireactFlag = redireactFlag;
	}
	
	
}
