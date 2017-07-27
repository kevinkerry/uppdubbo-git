package com.csii.upp.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.NoSuchMessageException;

import com.csii.pe.channel.ws.srv.InvokerExceptionHandler;
import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.util.StringUtil;

/**
 * 
 * @author xujin 异常信息解析获得异常码、异常类型和异常描述
 * 
 */
public class UppExceptionHandler implements InvokerExceptionHandler, ApplicationContextAware {

	public static final String PE_ERROR_UNDEFINED = "pe.error.undefined";
	
	public static final String ERROR_SPLIT = ";";
	
	public static final List<String> resps = new ArrayList<String>(Arrays.asList("100003", "100072", "100105", "100106"));

	Log log = LogFactory.getLog(getClass());
	
	private ApplicationContext applicationContext = null;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@SuppressWarnings("unchecked")
	public void handleException(Exception exception, Context context) {
		log.error("EXCEPTION CAUGHT", exception);
		String errCode = null;
		String msg = null;
		Throwable throwable = exception.getCause();
		String blend = null;
		if (throwable == null) {
			Object[] temp = null;
			String key = null;
			boolean flag = false;
			if (exception instanceof PeException) {
				PeException e = (PeException) exception;
				temp = e.getArgs();
				key = e.getMessageKey();
				flag = true;
			} else if (exception instanceof PeRuntimeException) {
				PeRuntimeException e = (PeRuntimeException) exception;
				temp = e.getArgs();
				key = e.getMessageKey();
				flag = true;
			}
			if (flag) {
				List list = new ArrayList();
				for (Object arg : temp) {
					try {
						String strArg = StringUtil.parseObjectToString(arg);
						String message = applicationContext.getMessage(strArg, null,Locale.CHINA);
						list.add(message);
					} catch (NoSuchMessageException ex) {
						list.add(arg);
					}
				}
				try {
					blend = applicationContext.getMessage(key, list.toArray(), Locale.CHINA);
				} catch (Exception ex) {
					errCode = ResponseCode.FAILURE;
					msg = exception.getMessage();
				}
			} else {
				blend = applicationContext.getMessage(DictErrors.TRANS_EXCEPTION,new Object[] { exception.getMessage() }, Locale.CHINA);
			}
		} else if (exception instanceof PeRuntimeException && throwable != null
				&& throwable.getClass().equals(PeRuntimeException.class) && throwable.getCause() == null) {
			try {
				Object[] args = (Object[]) (PeRuntimeException.class.getMethod("getArgs", null)).invoke(throwable, null);
				String key = (String) (PeRuntimeException.class.getMethod("getMessageKey", null)).invoke(throwable,null);
				List list = new ArrayList();
				for (Object arg : args) {
					try {
						String message = applicationContext.getMessage(StringUtil.parseObjectToString(arg), null,Locale.CHINA);
						list.add(message);
					} catch (NoSuchMessageException ex) {
						list.add(arg);
					}
				}
				blend = applicationContext.getMessage(key, list.toArray(), Locale.CHINA);
			} catch (Exception ex) {
				errCode = ResponseCode.FAILURE;
				msg = exception.getMessage();
			}
		} else if (exception instanceof PeRuntimeException && throwable != null
				&& throwable.getClass().equals(PeException.class) && throwable.getCause() == null) {
			// 数据库事务操作中必须抛出PeRuntimeException,所以这种情况下将 peExcepion
			// 包装在runtimeException中；
			try {
				Object[] args = (Object[]) (PeException.class.getMethod("getArgs", null)).invoke(throwable, null);
				String key = (String) (PeException.class.getMethod("getMessageKey", null)).invoke(throwable, null);
				List list = new ArrayList();
				for (Object arg : args) {
					try {
						String message = applicationContext.getMessage(StringUtil.parseObjectToString(arg), null,Locale.CHINA);
						list.add(message);
					} catch (NoSuchMessageException ex) {
						list.add(arg);
					}
				}
				blend = applicationContext.getMessage(key, list.toArray(), Locale.CHINA);
			} catch (Exception ex) {
				errCode = ResponseCode.FAILURE;
				msg = exception.getMessage();
			}
		} else {
			blend = applicationContext.getMessage(DictErrors.TRANS_EXCEPTION, new Object[] { exception.getMessage() },Locale.CHINA);
		}

		if (!StringUtil.isStringEmpty(blend)) {
			int index = blend.indexOf(ERROR_SPLIT);
			if (index > 0) {
				errCode = blend.substring(0, index);
				msg = blend.substring(index + 1);
			} else {
				errCode = blend;
			}
		}
		this.log.error("***************ERROR CODE**************:" + errCode);
		this.log.error("***************ERROR MSG**************:" + msg);

		context.setData(Dict.RESP_CODE, errCode);
		context.setData(Dict.RESP_MESSAGE, msg);
	}

}
