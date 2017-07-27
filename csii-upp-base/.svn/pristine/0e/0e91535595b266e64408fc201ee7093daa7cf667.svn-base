package com.csii.upp.action;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csii.ibs.action.AbstractIbsAction;
import com.csii.pe.action.Executable;
import com.csii.pe.service.Service;
import com.csii.upp.marshaller.ObjectMapMarshaller;
import com.csii.upp.supportor.DefaultSupportor;

/**
 * 交易处理基类
 * 
 * @author xujin
 * 
 */
public abstract class BaseAction extends AbstractIbsAction implements
		Executable {
	protected Log log = LogFactory.getLog(this.getClass());
	

	@Resource(name ="objectMapMarshaller")
	private ObjectMapMarshaller objectMapMarshaller;


	public ObjectMapMarshaller getObjectMapMarshaller() {
		return objectMapMarshaller;
	}


	protected Service getRouterService(String fundChannelCd) {
		return (Service) DefaultSupportor.getService(fundChannelCd.toLowerCase());
	}
	
	protected Service getFeeService() {
		return (Service) DefaultSupportor.getService("fee");
	}
}
