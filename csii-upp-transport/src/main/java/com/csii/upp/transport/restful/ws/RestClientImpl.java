package com.csii.upp.transport.restful.ws;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;

public class RestClientImpl {
	
	private RestClientConfig config;
	private Client client;
	
	
	public void initClient(){
		config.getProperties().put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, config.isRedireactFlag());
		config.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT, config.getConnectTimeout());
		config.getProperties().put(ClientConfig.PROPERTY_READ_TIMEOUT, config.getReadTimeout());
		config.getProperties().put(ClientConfig.PROPERTY_THREADPOOL_SIZE, config.getThreadPoolSize());
		client = Client.create(config);
	}
	
	public RestClientConfig getConfig() {
		return config;
	}

	public void setConfig(RestClientConfig config) {
		this.config = config;
	}
	
	public Client getClient() {
		return client;
	}

}
