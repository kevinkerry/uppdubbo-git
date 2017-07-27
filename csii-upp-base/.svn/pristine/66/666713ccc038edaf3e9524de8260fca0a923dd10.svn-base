package com.csii.upp.dubbo;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.csii.pe.service.comm.CommunicationException;
import com.csii.pe.service.comm.Transport;
import com.csii.upp.marshaller.ObjectMapMarshaller;
import com.csii.upp.util.StringUtil;

public class DubboTransport implements Transport, ApplicationContextAware, InitializingBean {
	private static Logger logger = LoggerFactory.getLogger(DubboTransport.class);
	private Map<String, Def> mapping;
	private Map<String, String> map;
	private String trsCodeName;
	
	private ObjectMapMarshaller objectMapMarshaller;
	
	private ApplicationContext applicationContext;
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DubboTransport() {
		this.mapping = new HashMap();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object submit(Object data) throws CommunicationException {
		objectMapMarshaller = (ObjectMapMarshaller) this.applicationContext.getBean("objectMapMarshaller");
		Map ctx = (Map) data;
		String trsCode = (String) ctx.get(this.trsCodeName);
		if (trsCode == null) {
			throw new IllegalArgumentException("TrsCode is null");
		}
		Def def = (Def) this.mapping.get(trsCode);
		if (def == null) {
			throw new IllegalArgumentException(new StringBuilder().append("TrsCode ").append(trsCode)
					.append(" not mapping to a dubbo service").toString());
		}
	    Class[] paramTypes = def.getMethod().getParameterTypes();
	    Object[] params = new Object[paramTypes.length];
		try {
		    for (int i = 0; i < paramTypes.length; ++i){
		    	logger.debug(StringUtil.buildString("############开始打印Dubbo Transport发送请求信息(",paramTypes[i].toString(),")############"));
		        params[i] = objectMapMarshaller.unMarshall(paramTypes[i], ctx);
		        logger.debug(StringUtil.buildString("############结束打印Dubbo Transport发送请求信息(",paramTypes[i].toString(),")############"));
		    }
			Object result = def.getMethod().invoke(def.getBean(), params);
			logger.debug(StringUtil.buildString("############开始打印Dubbo Transport收到响应信息(",result.getClass().toString(),")############"));
			Map resultMap=((result == null) ? new HashMap() : objectMapMarshaller.marshall(result));
			logger.debug(StringUtil.buildString("############结束打印Dubbo Transport收到响应信息(",result.getClass().toString(),")############"));
			return resultMap;
		} catch (Exception e) {
			throw new CommunicationException(e.getMessage(), e);
		}
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@SuppressWarnings("rawtypes")
	public void afterPropertiesSet() throws Exception {
		if (this.trsCodeName == null) {
			throw new IllegalArgumentException("TrsCodeName is null");
		}
		if (this.map == null) {
			throw new IllegalArgumentException("Mapping is null");
		}
		for (Map.Entry en : this.map.entrySet()) {
			String methodDef = ((String) en.getKey()).trim();
			String trsCode = ((String) en.getValue()).trim();
			String[] strarray = methodDef.split(":");
			String beanName = strarray[0].trim();
			if (!(this.applicationContext.containsBean(beanName))) {
				throw new IllegalArgumentException(
						new StringBuilder().append("bean ").append(beanName).append(" not exists").toString());
			}
			Object bean = this.applicationContext.getBean(beanName);
			Class clazz = this.applicationContext.getType(beanName);

			Method method = null;
			if (strarray.length > 1) {
				String methodName = strarray[1].trim();

				int start = methodName.indexOf("(");
				int end = methodName.indexOf(")");
				if ((start < 0) || (end < 0)) {
					Method[] methods = bean.getClass().getMethods();
					for (Method md : methods) {
						if (md.getName().equals(methodName)) {
							method = md;
							break;
						}
					}
					if (method == null)
						throw new IllegalArgumentException(new StringBuilder().append("class ").append(clazz.getName())
								.append(" not exists method ").append(methodName).toString());
				} else {
					String name = methodName.substring(0, start).trim();
					String paramStr = methodName.substring(start + 1, end).trim();

					String[] params = paramStr.split(",");
					Class[] paramTypes = new Class[params.length];

					for (int i = 0; i < params.length; ++i) {
						String param = params[i].trim();
						try {
							paramTypes[i] = Class.forName(param, false, this.applicationContext.getClassLoader());
						} catch (ClassNotFoundException e) {
							throw new RuntimeException(e);
						}
					}
					method = bean.getClass().getMethod(name, paramTypes);
				}
			} else {
				method = clazz.getMethods()[0];
				if (logger.isDebugEnabled()) {
					StringBuilder sb = new StringBuilder("TrsCode ").append(trsCode).append(" -> ")
							.append(clazz.getName()).append(":").append(method.getName()).append("(");
					Class[] paramTypes = method.getParameterTypes();
					for (int i = 0; i < paramTypes.length; ++i) {
						if (i > 0) {
							sb.append(",");
						}
						sb.append(paramTypes[i].getName());
					}
					sb.append(")");
					logger.debug(sb.toString());
				}
			}
			this.mapping.put(trsCode, new Def(bean, method));
		}
	}

	public void setTrsCodeMapping(Map<String, String> map) {
		this.map = map;
	}

	public void setTrsCodeName(String trsCodeName) {
		this.trsCodeName = trsCodeName;
	}

	private static class Def {
		private Object bean;
		private Method method;

		public Def(Object bean, Method method) {
			this.bean = bean;
			this.method = method;
		}

		public Object getBean() {
			return this.bean;
		}

		public Method getMethod() {
			return this.method;
		}
	}
}