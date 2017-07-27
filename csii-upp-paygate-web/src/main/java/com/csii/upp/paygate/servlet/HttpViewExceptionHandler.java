package com.csii.upp.paygate.servlet;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import com.csii.pe.accesscontrol.dc.ResubmitControlItem;
import com.csii.pe.accesscontrol.dc.ResubmitException;
import com.csii.pe.channel.http.Constants;
import com.csii.pe.channel.http.servlet.ExceptionHandler;
import com.csii.pe.channel.ws.srv.InvokerExceptionHandler;
import com.csii.pe.core.Context;
import com.csii.pe.core.Messageable;
import com.csii.pe.core.OriginalDataInterface;
import com.csii.pe.core.PeException;
import com.csii.pe.core.TransactionConfig;
import com.csii.pe.core.User;
import com.csii.pe.core.ValidationMessage;

public class HttpViewExceptionHandler implements ExceptionHandler {

	private Map mapping;
	private String defaultErrorView;
	private String defaultPublicErrorView;
	private String defaultAjaxErrorView;
	private String exceptionAttribute;
	private String exceptionMessageCodeAttribute;
	private String exceptionMessageAttribute;
	private Log log;
	private boolean cacheDataForErrorPage;
	private boolean backToInputForValidationError;
	private Map messageCodeMapping;
	private String defaultMappingCode;
	private int maxErrorMessageLength;
	private String errorMessageEncoding;
	private Map uncaughtExceptionCodeMapping;
	private String defaultUncaughtExcetpionCode;
	private InvokerExceptionHandler exceptionHandler;

	public HttpViewExceptionHandler() {
		defaultErrorView = "defaultError";
		defaultPublicErrorView = "defaultError";
		defaultAjaxErrorView = "validationError";
		exceptionAttribute = "_exception";
		exceptionMessageCodeAttribute = "_exceptionMessageCode";
		exceptionMessageAttribute = "_exceptionMessage";
		log = LogFactory.getLog(getClass());
		cacheDataForErrorPage = false;
		backToInputForValidationError = true;
		maxErrorMessageLength = 0;
		errorMessageEncoding = "UTF-8";
		defaultUncaughtExcetpionCode = "pe.error.uncaught";
	}

	protected String resolveViewName(Exception exception, Context context) {
		if (exception instanceof Messageable) {
			String s = ((Messageable) exception).getMessageKey();
			if (s != null && context != null) {
				TransactionConfig transactionconfig = context
						.getTransactionConfig();
				if (transactionconfig != null
						&& transactionconfig.getChannels() != null) {
					Map map = (Map) transactionconfig.getChannels().get("http");
					if (map != null) {
						String s1 = (String) map.get(s);
						if (s1 != null)
							return s1;
					}
				}
				if (mapping != null)
					return (String) mapping.get(s);
			}
		}
		return null;
	}

	public Object process(ApplicationContext applicationcontext,
			HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse, Locale locale,
			Context context, Exception exception) throws PeException {

		String s = resolveViewName(exception, context);
		if (backToInputForValidationError
				&& (exception instanceof ValidationMessage)) {
			if (log.isDebugEnabled())
				log.debug(exception);
			if (s != null) {
				httpservletrequest.setAttribute("_viewReferer", s);
			} else {
				String s1 = httpservletrequest.getHeader("PE-AJAX");
				if (s1 != null)
					httpservletrequest.setAttribute("_viewReferer",
							defaultAjaxErrorView);
			}
			String s2 = (String) httpservletrequest
					.getAttribute("_viewReferer");
			if (s2 != null && (context instanceof OriginalDataInterface)) {
				Map map = ((OriginalDataInterface) context).getOriginalData();
				Map map4 = resolverRejectMessages(applicationcontext,
						httpservletrequest, locale, exception, context);
				map.putAll(map4);
				return map;
			}
		} else if (exception instanceof ResubmitException) {
			ResubmitControlItem resubmitcontrolitem = (ResubmitControlItem) context
					.getData("_resubmitControlItem");
			httpservletrequest.setAttribute("_viewReferer",
					resubmitcontrolitem.getViewName());
			return resubmitcontrolitem.getModel();
		}
		if (cacheDataForErrorPage) {
			log.error("", exception);
			if (s == null) {
				if (context != null) {
					User user = context.getUser();
					if (user == null || user.isLogout())
						httpservletrequest.setAttribute("_viewReferer",
								defaultPublicErrorView);
					else
						httpservletrequest.setAttribute("_viewReferer",
								defaultErrorView);
				} else {
					httpservletrequest.setAttribute("_viewReferer",
							defaultErrorView);
				}
			} else {
				httpservletrequest.setAttribute("_viewReferer", s);
			}
			if (context instanceof OriginalDataInterface) {
				Object obj;
				if (context != null)
					obj = context.getDataMap();
				else
					obj = new HashMap(3);
				Map map1 = resolverRejectMessages(applicationcontext,
						httpservletrequest, locale, exception, context);
				((Map) (obj)).putAll(map1);
				Map map5 = ((OriginalDataInterface) context).getOriginalData();
				map5.put("_viewReferer",
						httpservletrequest.getParameter("_viewReferer"));
				((Map) (obj)).put("_dataMap", map5);
				return obj;
			}
			Object obj1;
			if (context != null) // chb 添加 如果没有该交易，context.getDataMap()会抛null异常
			{
				TransactionConfig transactionconfig = context
						.getTransactionConfig();
				if (transactionconfig == null)
					obj1 = new HashMap(3);
				else
					obj1 = context.getDataMap();
			} else
				obj1 = new HashMap(3);
			Map map2 = resolverRejectMessages(applicationcontext,
					httpservletrequest, locale, exception, context);
			((Map) (obj1)).putAll(map2);
			return obj1;
		}
		if (s == null) {
			if (context != null) {
				User user1 = context.getUser();
				if (user1 == null || user1.isLogout())
					httpservletrequest.setAttribute("_viewReferer",
							defaultPublicErrorView);
				else
					httpservletrequest.setAttribute("_viewReferer",
							defaultErrorView);
			} else {
				httpservletrequest.setAttribute("_viewReferer",
						defaultErrorView);
			}
		} else {
			httpservletrequest.setAttribute("_viewReferer", s);
		}
		log.error(httpservletrequest.getAttribute("_viewReferer"), exception);
		Object obj2;
		if (context != null) // chb 添加 如果没有该交易，context.getDataMap()会抛null异常
		{
			TransactionConfig transactionconfig = context
					.getTransactionConfig();
			if (transactionconfig == null)
				obj2 = new HashMap(3);
			else
				obj2 = context.getDataMap();
			int state = context.getState();
			if(state>0){
				httpservletrequest.setAttribute(
						Constants.TRANSACTION_APPLICATION_CONTEXT_ATTRIBUTE,
						transactionconfig.getApplicationContext());
				String viewName = null;
				Map channelsetting = (Map) context.getTransactionConfig().getChannels()
						.get("http");

				if(state==99999){
					viewName =((String) channelsetting.get("error"));
				}else{
					viewName =((String) channelsetting.get("success" + state));
				}
				httpservletrequest.setAttribute("_viewReferer", viewName);
			}
		} else
			obj2 = new HashMap(3);
		Map map3 = resolverRejectMessages(applicationcontext,
				httpservletrequest, locale, exception, context);
		((Map) (obj2)).putAll(map3);
		return obj2;
	}

	protected Map resolverRejectMessages(ApplicationContext applicationcontext,
			HttpServletRequest httpservletrequest, Locale locale,
			Exception exception, Context context) {
		HashMap hashmap = new HashMap(3);
		hashmap.put(exceptionAttribute, exception);
		this.exceptionHandler.handleException(exception, context);
		hashmap.put(exceptionMessageCodeAttribute,
				context.getString(exceptionMessageCodeAttribute));
		hashmap.put(exceptionMessageAttribute,
				context.getString(exceptionMessageAttribute));
		return hashmap;
	}

	protected String translateFieldName(ApplicationContext applicationcontext,
			String s, Locale locale, Context context) {
		int j = s.indexOf('[');
		if (j > 0) {
			String s1 = s.substring(0, j);
			String s2 = getMessage(applicationcontext, s1, null, s1, locale,
					context);
			String s3 = applicationcontext.getMessage("[", null, "No.", locale);
			String s4 = applicationcontext.getMessage("]", null, "Record",
					locale);
			String s5 = applicationcontext.getMessage(".", null, " ", locale);
			int k = s.indexOf(']');
			if (k > 0 && k > j)
				s2 = s3
						+ (Integer.parseInt(s.substring(j + 1, k)) + 1)
						+ s4
						+ s2
						+ s5
						+ translateFieldName(applicationcontext,
								s.substring(k + 2), locale, context);
			else
				s2 = s2 + s.substring(j);
			return s2;
		} else {
			return getFieldNameMessage(applicationcontext, s, locale, context);
		}
	}

	protected String getFieldNameMessage(ApplicationContext applicationcontext,
			String s, Locale locale, Context context) {
		return applicationcontext.getMessage(s, null, s, locale);
	}

	protected String getMessage(ApplicationContext applicationcontext,
			String s, Object aobj[], String s1, Locale locale, Context context) {
		return applicationcontext.getMessage(s, aobj, s1, locale);
	}

	protected String getMessage(ApplicationContext applicationcontext,
			String s, Object aobj[], Locale locale, Context context) {
		return applicationcontext.getMessage(s, aobj, locale);
	}

	public void setMapping(Map mapping) {
		this.mapping = mapping;
	}

	public void setDefaultErrorView(String defaultErrorView) {
		this.defaultErrorView = defaultErrorView;
	}

	public void setDefaultPublicErrorView(String defaultPublicErrorView) {
		this.defaultPublicErrorView = defaultPublicErrorView;
	}

	public void setDefaultAjaxErrorView(String defaultAjaxErrorView) {
		this.defaultAjaxErrorView = defaultAjaxErrorView;
	}

	public void setExceptionAttribute(String exceptionAttribute) {
		this.exceptionAttribute = exceptionAttribute;
	}

	public void setExceptionMessageCodeAttribute(
			String exceptionMessageCodeAttribute) {
		this.exceptionMessageCodeAttribute = exceptionMessageCodeAttribute;
	}

	public void setExceptionMessageAttribute(String exceptionMessageAttribute) {
		this.exceptionMessageAttribute = exceptionMessageAttribute;
	}

	public void setCacheDataForErrorPage(boolean cacheDataForErrorPage) {
		this.cacheDataForErrorPage = cacheDataForErrorPage;
	}

	public void setBackToInputForValidationError(
			boolean backToInputForValidationError) {
		this.backToInputForValidationError = backToInputForValidationError;
	}

	public void setMessageCodeMapping(Map messageCodeMapping) {
		this.messageCodeMapping = messageCodeMapping;
	}

	public void setDefaultMappingCode(String defaultMappingCode) {
		this.defaultMappingCode = defaultMappingCode;
	}

	public void setMaxErrorMessageLength(int maxErrorMessageLength) {
		this.maxErrorMessageLength = maxErrorMessageLength;
	}

	public void setErrorMessageEncoding(String errorMessageEncoding) {
		this.errorMessageEncoding = errorMessageEncoding;
	}

	public void setUncaughtExceptionCodeMapping(Map uncaughtExceptionCodeMapping) {
		this.uncaughtExceptionCodeMapping = uncaughtExceptionCodeMapping;
	}

	public void setDefaultUncaughtExcetpionCode(
			String defaultUncaughtExcetpionCode) {
		this.defaultUncaughtExcetpionCode = defaultUncaughtExcetpionCode;
	}

	public void setExceptionHandler(InvokerExceptionHandler exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}

}
