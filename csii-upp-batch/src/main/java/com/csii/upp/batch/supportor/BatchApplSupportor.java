package com.csii.upp.batch.supportor;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.csii.upp.batch.xml.format.FieldParser;
/**
 * 
 * @author 徐锦
 *
 */
public class BatchApplSupportor implements ApplicationContextAware {
	private static ApplicationContext appContext;
	private static ApplBean applBean;
	private static FieldParser fieldParser;

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		appContext = context;
	}
	
	public static ApplBean getApplBean() {
		if (applBean == null) {
			applBean = (ApplBean)appContext.getBean("applBean");
		}
		return applBean;
	}
	
	public static FieldParser getFieldParser() {
		if (fieldParser == null) {
			fieldParser = (FieldParser)appContext.getBean("fieldParser");
		}
		return fieldParser;
	}

}
