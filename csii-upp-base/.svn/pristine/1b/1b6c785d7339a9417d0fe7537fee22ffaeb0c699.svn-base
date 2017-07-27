package com.csii.upp.supportor;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.csii.pe.service.Service;
import com.csii.upp.event.EventManager;
import com.csii.upp.marshaller.ObjectMapMarshaller;
/**
 * 
 * @author 徐锦
 *
 */
public class DefaultSupportor  implements ApplicationContextAware {
	private static ApplicationContext appContext;
	private static EventManager eventManager;
	private static ObjectMapMarshaller objectMapMarshaller;
	private static Map serviceMapping;

	
	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		appContext = context;
	}

	public static EventManager getEventManager() {
		if (eventManager == null) {
			eventManager = (EventManager) appContext.getBean("eventManager");
		}
		return eventManager;
	}

	public static ObjectMapMarshaller getObjectMapMarshaller() {
		if (objectMapMarshaller == null) {
			objectMapMarshaller = (ObjectMapMarshaller) appContext
					.getBean("objectMapMarshaller");
		}
		return objectMapMarshaller;
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Service getService(String serviceId) {
		if (serviceMapping == null) {
			if(appContext.containsBean("fundProcessServiceMapping")){
				serviceMapping= (Map)appContext.getBean("fundProcessServiceMapping");
			}
			if(appContext.containsBean("paymServiceMapping")){
				Map paymMap=(Map)appContext.getBean("paymServiceMapping");
				if(serviceMapping==null){
					serviceMapping= paymMap;
				}else{
					serviceMapping.putAll(paymMap);
				}
			}
		}
		return (Service)serviceMapping.get(serviceId);
	}
}
