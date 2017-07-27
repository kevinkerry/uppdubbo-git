package com.csii.upp.channel;

import java.util.Iterator;
import java.util.Map;

import com.csii.pe.channel.ws.DefaultServiceManager;
import com.csii.pe.channel.ws.ServiceManagerAware;
import com.csii.pe.channel.ws.client.CustomWebServiceGateway;

public class WebServiceManager extends DefaultServiceManager {

	@Override
	public void afterPropertiesSet() throws Exception {
		Map<String, ServiceManagerAware> awares = getApplicationContext()
				.getBeansOfType(ServiceManagerAware.class);
		for (Iterator<ServiceManagerAware> it = awares.values().iterator(); it
				.hasNext();) {
			ServiceManagerAware service = it.next();
			if (!(service instanceof CustomWebServiceGateway)) {
				service.setServiceManager(this);
			}
		}
	}

}
