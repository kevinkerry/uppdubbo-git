package com.csii.upp.paygate.action;

import java.io.ByteArrayInputStream;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.pe.transform.Transformer;
import com.csii.pe.transform.TransformerFactory;
import com.csii.pe.validation.ValidationException;
import com.csii.upp.action.BaseAction;
import com.csii.upp.paygate.servlet.Const;
import com.csii.upp.util.StringUtil;

/**
 * 请求明文转化为MAP交易处理
 * 
 * @author 徐锦
 * 
 */
public class ParsePlainAction extends BaseAction {
	private TransformerFactory transformerFactory;

	private String parserResolverName;

	private String defaultEncoding;

	public TransformerFactory getTransformerFactory() {
		return transformerFactory;
	}

	public void setTransformerFactory(TransformerFactory transformerFactory) {
		this.transformerFactory = transformerFactory;
	}

	public String getParserResolverName() {
		return parserResolverName;
	}

	public void setParserResolverName(String parserResolverName) {
		this.parserResolverName = parserResolverName;
	}

	public String getDefaultEncoding() {
		return defaultEncoding;
	}

	public void setDefaultEncoding(String defaultEncoding) {
		this.defaultEncoding = defaultEncoding;
	}


	@Override
	public void execute(Context context) throws PeException {
		String plain = null;
		if (!StringUtil.isStringEmpty(context.getString("PlainContext")) && !"null".equals(context.getString("PlainContext")) ) {
			plain = context.getString("PlainContext").replaceAll("&", "&amp;");
		}else{
			plain = context.getString(Const.PLAIN);
		}

	//	plain = "<Finance><Message><TransId>IPER</TransId><MerchantId>882016122900000</MerchantId><MerSeqNo>0002017010400000021</MerSeqNo><OrderId>0002017010400000021</OrderId><TransAmt>10</TransAmt><MerDateTime>20170104144832</MerDateTime><MerURL>http://tpay.zjzwfw.gov.cn/ggzfpt/notifyUrl.htm</MerURL><MerURL1>http://tpay.zjzwfw.gov.cn/ggzfpt/returnUrl.htm?payListNo=000201701040000022&payType=01</MerURL1><MerTransList><MerTransDetail><SubMerchantId>GG882016122900000</SubMerchantId><SubMerDateTime>20170104144832</SubMerDateTime><SubMerSeqNo>G0002017010400000021</SubMerSeqNo><SubTransAmt>10</SubTransAmt></MerTransDetail></MerTransList></Message></Finance>";
	//	plain = "<Finance><Message><TransId>IPER</TransId><MerchantId>010020150521152412</MerchantId><MerSeqNo>wsl20170104142302</MerSeqNo><OrderId>DS2015052514</OrderId><TransAmt>5.00</TransAmt><MerDateTime>20170104142302</MerDateTime><MerURL>http://158.222.25.107:8080/merchant/*.do?TransName=prePWDReq</MerURL><MerURL1>http://tpay.zjzwfw.gov.cn/ggzfpt/returnUrl.htm?payListNo=000201701040000022&amp;payType=01</MerURL1><MerTransList><MerTransDetail><SubMerchantId>AA201506110000000292</SubMerchantId><SubMerDateTime>20170104142302</SubMerDateTime><SubMerSeqNo>wsla20170104142302</SubMerSeqNo><SubTransAmt>2.00</SubTransAmt></MerTransDetail></MerTransList></Message></Finance>";
		Transformer transformer = transformerFactory
				.getTransformer(parserResolverName);
		Map data = (Map) transformer.parse(
				new ByteArrayInputStream(plain.getBytes()), null);

		if (data == null) {
			throw new ValidationException("transaction error");
		}
		context.setDataMap(data);
	}


}