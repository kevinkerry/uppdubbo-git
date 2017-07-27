package com.csii.upp.paygate.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.support.ApplicationObjectSupport;

import com.csii.pe.channel.http.IdentityResolver;
import com.csii.pe.channel.http.LocalServletContext;
import com.csii.pe.channel.http.servlet.ContextResolver;
import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.pe.core.TransactionConfig;
import com.csii.pe.core.TransactionConfigAware;
import com.csii.pe.trsflow.TrsFlowContext;
import com.csii.upp.constant.ChannelNbr;
import com.csii.upp.util.StringUtil;

public class AppJsonContextResolver extends ApplicationObjectSupport implements ContextResolver {

	private String defaultTransaction;
	private String charset;

	private Context creatConext(String transactionId, HttpServletRequest httpservletrequest, Locale locale) {	
		Context context = new LocalServletContext(transactionId,httpservletrequest,locale, null);
		if(context instanceof TransactionConfigAware) {
			TransactionConfig transactionconfig = (TransactionConfig)getApplicationContext().getBean(defaultTransaction);
			((TransactionConfigAware)context).setTransactionConfig(transactionconfig);
		}
		return context;
	}
	
	public Context resolveContext(HttpServletRequest httpservletrequest, Locale locale, IdentityResolver identityresolver)
			throws PeException {
		// TODO Auto-generated method stub
		Context context = creatConext(null, httpservletrequest, locale);
		String jsonReq = readJsonReq(httpservletrequest);
		logger.info("REQUEST MESSAGE:" + jsonReq);
		handle(context, jsonReq);
		logger.info("RETURN RESOLVECONTEXT:" + context);
		return context;
	}
	
	private void handle(Context context, String jsonStr) throws PeException {
		// TODO Auto-generated method stub
		try {
//			int startIdx = jsonStr.indexOf("\"" + com.csii.pp.core.Constants.APP_SIGN_TAG);
//			int endIdx = jsonStr.lastIndexOf(com.csii.pp.core.Constants.APP_SIGN_TAG + "\"");hss
//			logger.debug("startIdx=" + startIdx + ", endIdx=" + endIdx);
//			String plainStr = jsonStr.substring(startIdx + 1, endIdx);
//			jsonStr = jsonStr.replaceAll("\"" + com.csii.pp.core.Constants.APP_SIGN_TAG, ""); // 替换掉特殊签名标记，获取标准json报文
//			jsonStr = jsonStr.replaceAll(com.csii.pp.core.Constants.APP_SIGN_TAG + "\"", ""); // 替换掉特殊签名标记，获取标准json报文
//			logger.debug("JsonStrAfterHandle:\n" + jsonStr);
//			
			ObjectMapper objMapper = new ObjectMapper();
			objMapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
			objMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
			Map jsonMap = objMapper.readValue(jsonStr, Map.class);
			Object plain = jsonMap.get(Const.PLAIN);
			if(plain == null || !(plain instanceof /*Map*/String)) {
				logger.error("Plain域为空或不是标准的Json格式报文字符串!");
				throw new PeException("plain_illegal_argument");
			}
			String plainStr = (String) plain; // 签名明文串
			// 解析明文串 -- 标准json格式报文
			Map pJsonMap = objMapper.readValue(plainStr, Map.class);
			pJsonMap.put(Const.TRANS_ID, jsonMap.get(Const.TRANS_NAME)); // 默认交易代码以 transName 字段为准
			context.setDataMap(pJsonMap);
			
//			Map dataMap = (Map) plain;
//			context.setDataMap(dataMap);
			
//			String plainStr = readPlain(jsonStr);
			context.setData(Const.PLAIN, plainStr);
			
			String signature = (String) jsonMap.get(Const.SIGNATURE);
			if(signature == null) {
				throw new PeException("plain_illegal_argument");
			}
			context.setData(Const.SIGNATURE, signature);
			
			String transName = (String) jsonMap.get(Const.TRANS_NAME);
			if(StringUtil.isStringEmpty(transName)) {
				throw new PeException("undefined_transaction");
			}
			context.setData(Const.TRANS_NAME, transName);
			context.setTransactionId(transName);
			context.setData(Const.CHANNEL_NBR , ChannelNbr.APP);
		} catch (PeException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new PeException("transaction error", ex);
		}
		
	}

	/**
	 * 获取签名明文串，过期，不使用
	 * @param jsonStr
	 * @return
	 */
	@Deprecated
	private String readPlain(String jsonStr) {
		// TODO Auto-generated method stub
		int leftIndex = jsonStr.indexOf("{");
		//System.out.println("leftIndex:"+leftIndex);
		int rightIndex = jsonStr.lastIndexOf("}");
		//System.out.println("rightIndex:"+rightIndex);
		if(rightIndex<=leftIndex){
			return "{" + jsonStr + "}";
		}
		if(leftIndex == -1 || rightIndex == -1) {
			return "{" + jsonStr + "}";
		} 
		
		jsonStr = jsonStr.substring(leftIndex + 1, rightIndex);
		return readPlain(jsonStr);
	}

	private String readJsonReq(HttpServletRequest httpservletrequest) throws PeException{
		String result = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int bufferSize = 1024;
		try {
			InputStream is = httpservletrequest.getInputStream();
			byte[] bytes = new byte[bufferSize];
			int i = 0;
			while ((i = is.read(bytes)) > 0) {
				baos.write(bytes, 0, i);
			}
			result = baos.toString(charset);
		} catch (IOException ex) {
			throw new PeException("transaction error", ex);
		} finally {
			try {
				baos.close();
			} catch (IOException ex) {
				throw new PeException("transaction error", ex);
			}
		}
		return result;
	}
	
	public void setDefaultTransaction(String defaultTransaction) {
		this.defaultTransaction = defaultTransaction;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	@Override
	public void beforeContextDisposal(HttpServletRequest arg0, Context arg1)
			throws PeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TrsFlowContext resolveTrsFlowContext(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
