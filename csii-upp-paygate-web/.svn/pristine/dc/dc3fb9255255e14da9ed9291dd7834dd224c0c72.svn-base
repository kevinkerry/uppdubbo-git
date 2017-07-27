package com.csii.upp.paygate.servlet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.ApplicationObjectSupport;

import com.csii.pe.channel.http.IdentityResolver;
import com.csii.pe.channel.http.LocalServletContext;
import com.csii.pe.channel.http.servlet.ContextResolver;
import com.csii.pe.common.util.Base64;
import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.pe.core.TransactionConfig;
import com.csii.pe.core.TransactionConfigAware;
import com.csii.pe.transform.Transformer;
import com.csii.pe.transform.TransformerFactory;
import com.csii.pe.trsflow.TrsFlowContext;
import com.csii.pe.validation.ValidationException;
import com.csii.upp.constant.ChannelNbr;
import com.csii.upp.constant.TransId;
import com.csii.upp.util.StringUtil;

public class MainContextResolver extends ApplicationObjectSupport implements ContextResolver {

	private String defaultTransaction;

	private TransformerFactory transformerFactory;

	private String parserResolverName;

	private String defaultEncoding;

	/**
	 * 创建context
	 */
	private Context creatConext(String transactionId, HttpServletRequest request, Locale locale) {
		Context context = new LocalServletContext(transactionId, request, locale, null);
		if (context instanceof TransactionConfigAware) {
			TransactionConfig transactionconfig = (TransactionConfig) getApplicationContext().getBean(defaultTransaction);
			((TransactionConfigAware) context).setTransactionConfig(transactionconfig);
		}
		return context;
	}

	@Override
	public Context resolveContext(HttpServletRequest request, Locale locale, IdentityResolver identityresolver) throws PeException {
		Context context = creatConext(null, request, locale);

		resolveContext(context, request, locale);
		return context;
	}

	protected void resolveContext(Context context, HttpServletRequest request, Locale locale) throws PeException {
		String test = request.getParameter("testValue");
		byte[] data = readtest(context, request, locale);
		parse(context, data, request, locale);

	}

	private byte[] readtest(Context context, HttpServletRequest request, Locale locale) throws PeException {

		String Plain = handlerPlain(request);
		String requestHead = request.getCharacterEncoding();
		String Signature = request.getParameter(Const.SIGNATURE);

		return Plain == null ? read(context, request, locale) : Plain.getBytes();

	}

	/**
	 * 将请求数据读取至字节数组
	 */
	private byte[] read(Context context, HttpServletRequest request, Locale locale) throws PeException {
		context.setData("merCertUploadTrans", "yes");
		ByteArrayOutputStream baos = null;
		int bufferSize = 1024;
		try {
			baos = new ByteArrayOutputStream();
			InputStream is = request.getInputStream();
			byte[] bytes = new byte[bufferSize];
			int i = 0;
			while ((i = is.read(bytes)) > 0) {
				baos.write(bytes, 0, i);
			}
		} catch (IOException ex) {
			throw new ValidationException("transaction error", ex);
		} finally {
			try {
				if (baos != null) {
					baos.close();
				}
			} catch (IOException ex) {
				throw new ValidationException("transaction error", ex);
			}
		}
		String result = baos.toString();
		// if (MiscUtil.isNullOrEmpty(result) && !result.startsWith("<?xml")) {
		if (StringUtil.isStringEmpty(result) || (!StringUtil.isStringEmpty(result) && !result.startsWith("<Finance>"))) {
			String trsData = request.getParameter("transData");
			// TODO:压力测试修改，其他环境中要放开119行代码，并去掉此行byte[] msgSignature =
			// trsData.getBytes(); 2014-05-09
			byte[] msgSignature = Base64.base64ToByteArray(trsData);
			// byte[] msgSignature = trsData.getBytes();
			try {
				result = StringUtil.byteToString(msgSignature);
			} catch (Exception e) {
				throw new ValidationException("transaction error", e);
			}
		}
		return result == null ? null : result.getBytes();
	}

	/**
	 * 将字符数组解析为context
	 */
	private void parse(Context context, byte[] data, HttpServletRequest request, Locale locale) throws PeException {
		String message = null;

		try {
			message = new String(data, this.defaultEncoding);
		} catch (UnsupportedEncodingException ex) {
			throw new ValidationException("transaction error", ex);
		}
		String signature = request.getParameter(Const.SIGNATURE);

		String flag = (String) context.getData(Const.MER_CENT_UPLOAD_TRANS);

		// 财政网签名字段不一样
		if (StringUtil.isStringEmpty(signature) && ((null != flag) && !flag.equals("yes"))) {
			// 回车换行的转换
			signature = request.getParameter("signature").replaceAll("&#13;&#10;", "\r\n");
		}

		if (StringUtil.isStringEmpty(signature) && ((null != flag) && !flag.equals("yes"))) {
			throw new ValidationException("数字签名不能为空");
		}
		context.setData(Const.SIGNATURE, signature);
		context.setData(Const.PLAIN, message);
		// modified 2013-08-27
		// if (MiscUtil.isNullOrEmpty(message) || !message.startsWith("<?xml"))
		// {
		if (StringUtil.isStringEmpty(message)) {
			context.setData("AliPayMessageId", "MessageError");
			throw new ValidationException("transaction error");
		}

		// if (debug) {
		logger.info("REQUEST MESSAGE:" + message);
		// }
		Map requestData = resolverContext(data, request);

		if (requestData == null) {
			throw new ValidationException("transaction error");
		}
		context.setDataMap(requestData);
		String str = (String) requestData.get("AppointedPayAcctNo");
		context.setData("PayerAcctNbr", requestData.get("AppointedPayAcctNo"));
		context.setData("CifNo", requestData.get("CifNo"));
		// context.setData("CifNo", "111111");
		String transId = (String) context.getData(Const.TRANS_ID);
		context.setTransactionId(transId);
		if (TransId.IPEM.equals(transId)) {
			context.setData(Const.CHANNEL_NBR, ChannelNbr.WAP);
		} else {
			context.setData(Const.CHANNEL_NBR, ChannelNbr.PC);
		}
	}

	public void setDefaultTransaction(String defaultTransaction) {
		this.defaultTransaction = defaultTransaction;
	}

	public void setTransformerFactory(TransformerFactory transformerFactory) {
		this.transformerFactory = transformerFactory;
	}

	public void setParserResolverName(String parserResolverName) {
		this.parserResolverName = parserResolverName;
	}

	public void setDefaultEncoding(String defaultEncoding) {
		this.defaultEncoding = defaultEncoding;
	}

	@Override
	public TrsFlowContext resolveTrsFlowContext(HttpServletRequest paramHttpServletRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void beforeContextDisposal(HttpServletRequest paramHttpServletRequest, Context paramContext) throws PeException {
		// TODO Auto-generated method stub
		System.out.println("aaaa");

	}

	// 解析成Map
	private Map resolverContext(byte[] data, HttpServletRequest request) throws PeException {
		Map requestData = resolverData(request);
		if ((requestData != null) && !requestData.isEmpty()) {
			return requestData;
		} else {
			Transformer transformer = transformerFactory.getTransformer(parserResolverName);
			requestData = (Map) transformer.parse(new ByteArrayInputStream(data), null);
			return requestData;
		}

	}

	public Map resolverData(HttpServletRequest request) throws PeException {
		return null;
	}

	// 特殊商户接口处理
	private String handlerPlain(HttpServletRequest request) throws PeException {
		String plain = handerSpecialPlain(request);
		if (!StringUtil.isStringEmpty(plain)) {
			return plain;
		} else {
			return request.getParameter(Const.PLAIN);
		}
	}

	public String handerSpecialPlain(HttpServletRequest request) throws PeException {
		return null;
	}

}
