package com.csii.upp.paygate.servlet;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

public class QrCodeJsonContextResolver extends ApplicationObjectSupport implements ContextResolver{

	private String defaultTransaction;
	private String charset;
	
	public String getDefaultTransaction() {
		return defaultTransaction;
	}

	public void setDefaultTransaction(String defaultTransaction) {
		this.defaultTransaction = defaultTransaction;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}
	
	private Context creatConext(String transactionId, HttpServletRequest httpservletrequest, Locale locale) {	
		Context context = new LocalServletContext(transactionId,httpservletrequest,locale, null);
		if(context instanceof TransactionConfigAware) {
			TransactionConfig transactionconfig = (TransactionConfig)getApplicationContext().getBean(defaultTransaction);
			((TransactionConfigAware)context).setTransactionConfig(transactionconfig);
		}
		return context;
	}

	@Override
	public Context resolveContext(HttpServletRequest paramHttpServletRequest, Locale paramLocale,
			IdentityResolver paramIdentityResolver) throws PeException {
		Context context = creatConext(null, paramHttpServletRequest, paramLocale);
//		readTOContext(context,paramHttpServletRequest);
//		logger.info("REQUEST MESSAGE:" + jsonReq);QREM
		try {
			paramHttpServletRequest.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		handle(context, paramHttpServletRequest);
		logger.info("The Context is :" + context);
		return context;
	}
	

	private void handle(Context context, HttpServletRequest httpServletRequest) throws PeException {
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
//			ObjectMapper objMapper = new ObjectMapper();
//			objMapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
//			objMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
//			Map jsonMap = objMapper.readValue(jsonStr, Map.class);
//			Object plain = jsonMap.get(Const.PLAIN);
//			if(plain == null || !(plain instanceof /*Map*/String)) {
//				logger.error("Plain域为空或不是标准的Json格式报文字符串!");
//				throw new PeException("plain_illegal_argument");
//			}
//			String plainStr = (String) plain; // 签名明文串
			// 解析明文串 -- 标准json格式报文
//			Map pJsonMap = objMapper.readValue(jsonStr, Map.class);
//			pJsonMap.put(Const.TRANS_ID, pJsonMap.get(Const.TRANS_NAME)); // 默认交易代码以 transName 字段为准
			Map pJsonMap = null;
			pJsonMap = new HashMap<String, String>();
			pJsonMap.put("TransName", httpServletRequest.getParameter("TransName"));
			pJsonMap.put("MerchantName", httpServletRequest.getParameter("MerchantName"));
			pJsonMap.put("TransAmt", httpServletRequest.getParameter("TransAmt"));
			pJsonMap.put("code", httpServletRequest.getParameter("code"));
			pJsonMap.put("merNbr", httpServletRequest.getParameter("merNbr"));
			context.setDataMap(pJsonMap);
			
			
//			Map dataMap = (Map) plain;
//			context.setDataMap(dataMap);
			
//			String plainStr = readPlain(jsonStr);
			context.setData(Const.PLAIN, "<Finance><Message></Message></Finance>");
			
			String signature = "123";
			if(signature == null) {
				throw new PeException("plain_illegal_argument");
			}
			context.setData(Const.SIGNATURE, signature);
			
			String transName = (String) pJsonMap.get(Const.TRANS_NAME);
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

	
	public static Map resolveJsonWithoutQuote(String str1){
		Map resultMap = new HashMap<String, String>();
		String[] count = str1.split(",");
		for(int i=0;i<count.length;i++){
			if(count[i].startsWith("{")){
				count[i] = count[i].substring(1, count[i].length());
			}
			if(count[i].endsWith("}")){
				count[i] = count[i].substring(0, count[i].length()-1);
			}

			String[] content = count[i].split(":");
			resultMap.put(content[0], content[1]);
		}
		
		return resultMap;
	}
	
	@Override
	public TrsFlowContext resolveTrsFlowContext(HttpServletRequest paramHttpServletRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void beforeContextDisposal(HttpServletRequest paramHttpServletRequest, Context paramContext)
			throws PeException {
		// TODO Auto-generated method stub
		
	}

	
}
