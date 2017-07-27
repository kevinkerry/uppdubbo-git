package com.csii.upp.dubbo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.rpc.RpcContext;
import com.csii.pe.annotations.TrsCode;
import com.csii.pe.dubbo.adapter.DubboClientInfo;
import com.csii.pe.dubbo.adapter.DubboContext;
import com.csii.pe.dubbo.adapter.ServiceFactoryBean;
import com.csii.upp.marshaller.ObjectMapMarshaller;
import com.csii.upp.util.StringUtil;
/**
 * 
 * @author xujin
 *
 * @param <T>
 */
public class SimpleServiceFactoryBean<T> extends ServiceFactoryBean<T> {
	private static Logger logger = LoggerFactory.getLogger(SimpleServiceFactoryBean.class);
	private String trsCode;
	private Map<MethodDef, String> methodTrsCodeMap;
	private String mappingStr;
	private Map<String, String> trsCodeMapping;
	
	@Resource(name ="objectMapMarshaller")
	private ObjectMapMarshaller objectMapMarshaller;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SimpleServiceFactoryBean() {
		this.methodTrsCodeMap = new HashMap();
	}

	public void setTrsCode(String trsCode) {
		this.trsCode = trsCode;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public T getObject() throws Exception {
		Object obj = Proxy.newProxyInstance(getClassLoader(), new Class[] { getObjectType() }, new InvocationHandler() {
			public Object invoke(Object target, Method method, Object[] args) throws Throwable {
				String tc = SimpleServiceFactoryBean.this.trsCode;
				if (tc == null) {
					tc = SimpleServiceFactoryBean.this.getTrsCode(method);
				}
				if (tc == null) {
					TrsCode anno = (TrsCode) method.getAnnotation(TrsCode.class);
					if (anno != null) {
						tc = anno.value();
					}
				}
				if (tc == null) {
					throw new IllegalArgumentException(
							"service " + SimpleServiceFactoryBean.this.getObjectType().getName() + " method "
									+ method.getName() + " not set transaction code.");
				}
				
				Map data = new HashMap();
				if ((args != null) && (args.length != 0)) {
					for (Object bean : args) {
						if (bean != null && !(bean instanceof Map)) {
					    	logger.debug(StringUtil.buildString("############开始打印服务端收到请求信息(",bean.getClass().toString(),")############"));
							data.putAll(objectMapMarshaller.marshall(bean));
							logger.debug(StringUtil.buildString("############结束打印服务端收到请求信息(",bean.getClass().toString(),")############"));
						} else {
							data.putAll((Map) bean);
						}
					}
				}

				DubboContext ctx = new DubboContext(tc, data);

				RpcContext rpcContext = RpcContext.getContext();

				DubboClientInfo clientInfo = new DubboClientInfo(rpcContext.getRemoteHost(),
						rpcContext.getRemotePort());
				ctx.setClientInfo(clientInfo);

				SimpleServiceFactoryBean.this.getCoreController().execute(ctx);
				Class type = method.getReturnType();
				if (type == Void.class) {
					return null;
				}
				if(Map.class.isAssignableFrom(method.getReturnType())){
					return ctx.getDataMap();
				}else{
					logger.debug(StringUtil.buildString("############服务端发送响应信息(",method.getReturnType().toString(),")############"));
					return objectMapMarshaller.unMarshall(method.getReturnType(), ctx.getDataMap());
				}
			}
		});
		return (T) obj;
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();

		this.methodTrsCodeMap = new LinkedHashMap();
		if (this.mappingStr != null) {
			parse(this.mappingStr);
		}
		if (this.trsCodeMapping != null) {
			parse(this.trsCodeMapping);
		}
		if (logger.isDebugEnabled()) {
			StringBuilder sb = new StringBuilder();
			int index = 0;
			for (Map.Entry md : this.methodTrsCodeMap.entrySet()) {
				if (index > 0) {
					sb.append("\n");
				}
				sb.append(new StringBuilder().append("    ").append((String) md.getValue()).toString()).append("->")
						.append(md.getKey());
				++index;
			}
			logger.debug(new StringBuilder().append(getObjectType().getName()).append(" mapping:\n")
					.append(sb.toString()).toString());
		}
	}

	public void setMapping(String trsCodeMapping) {
		this.mappingStr = trsCodeMapping;
	}

	public void setTrsCodeMapping(Map<String, String> trsCodeMapping) {
		this.trsCodeMapping = trsCodeMapping;
	}

	private void parse(String trsCodeMapping) {
		if (trsCodeMapping == null) {
			return;
		}
		String[] mapping = trsCodeMapping.split(";");
		for (String str : mapping) {
			str = str.trim();
			String[] array = str.split("->");
			if (array.length != 2) {
				continue;
			}

			parse(array[0].trim(), array[1].trim());
		}
	}

	@SuppressWarnings("rawtypes")
	private void parse(Map<String, String> trsCodeMapping) {
		if (trsCodeMapping == null) {
			return;
		}
		for (Map.Entry en : trsCodeMapping.entrySet()) {
			String m = ((String) en.getKey()).trim();
			String t = ((String) en.getValue()).trim();
			parse(m, t);
		}
	}

	@SuppressWarnings("rawtypes")
	private void parse(String m, String t) {
		int start = m.indexOf("(");
		int end = m.indexOf(")");
		if ((start < 0) || (end < 0)) {
			for (Method method : getObjectType().getMethods()) {
				if (method.getName().equals(m)) {
					this.methodTrsCodeMap.put(new MethodDef(method.getName(), method.getParameterTypes()), t);
					break;
				}
			}
			return;
		}

		String name = m.substring(0, start).trim();
		String paramStr = m.substring(start + 1, end).trim();

		String[] params = paramStr.split(",");
		Class[] paramTypes = new Class[params.length];

		for (int i = 0; i < params.length; ++i) {
			String param = params[i].trim();
			try {
				paramTypes[i] = Class.forName(param, false, getClassLoader());
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}

		this.methodTrsCodeMap.put(new MethodDef(name, paramTypes), t);
	}

	private ClassLoader getClassLoader() {
		if (getApplicationContext() == null) {
			return Thread.currentThread().getContextClassLoader();
		}
		return getApplicationContext().getClassLoader();
	}

	@SuppressWarnings("rawtypes")
	private String getTrsCode(Method method) {
		if (this.methodTrsCodeMap == null) {
			return null;
		}
		for (Map.Entry en : this.methodTrsCodeMap.entrySet()) {
			MethodDef md = (MethodDef) en.getKey();
			if (md.match(method)) {
				return ((String) en.getValue());
			}
		}
		return null;
	}

	private static class MethodDef {
		public String name;
		public Class<?>[] paramTypes;

		private MethodDef(String name, Class<?>[] paramTypes) {
			this.name = name;
			this.paramTypes = paramTypes;
		}

		@SuppressWarnings("rawtypes")
		public boolean match(Method method) {
			if (!(this.name.equals(method.getName()))) {
				return false;
			}
			Class[] pts = method.getParameterTypes();
			if (this.paramTypes.length != pts.length) {
				return false;
			}
			for (int i = 0; i < this.paramTypes.length; ++i) {
				if (this.paramTypes[i] != pts[i]) {
					return false;
				}
			}
			return true;
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(this.name).append("(");
			if (this.paramTypes != null) {
				for (int i = 0; i < this.paramTypes.length; ++i) {
					if (i > 0) {
						sb.append(",");
					}
					sb.append(this.paramTypes[i].getName());
				}
			}
			sb.append(")");
			return sb.toString();
		}
	}
}