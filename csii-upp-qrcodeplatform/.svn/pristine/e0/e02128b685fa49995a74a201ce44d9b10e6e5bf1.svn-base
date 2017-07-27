package com.csii.upp.qrcodeplatform.sequence;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.csii.pe.service.Service;
import com.csii.upp.qrcodeplatform.action.util.StringUtil;
/**
 * 
 * @author 徐锦
 *
 */
public class DefaultSupportor  implements ApplicationContextAware {
	private static ApplicationContext appContext;

	private static UppSnowflakeIdWorker seqNbrFactory;
	
	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		appContext = context;
	}

	
	public static String generateSeqNbr() {
		if (seqNbrFactory == null) {
			seqNbrFactory = (UppSnowflakeIdWorker) appContext
					.getBean("seqNbrFactory");
		}
		return StringUtil.parseObjectToString(seqNbrFactory.generate());
	}
}
