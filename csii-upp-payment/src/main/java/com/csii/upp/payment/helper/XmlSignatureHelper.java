/**
 * 
 */
package com.csii.upp.payment.helper;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csii.pe.core.PeException;
import com.csii.pe.transform.Transformer;
import com.csii.pe.transform.TransformerFactory;
import com.csii.pe.transform.stream.xml.XmlStreamParser;
import com.csii.upp.constant.MerPlatformNbr;
import com.csii.upp.dict.Dict;
import com.csii.upp.signature.UppSignature;

/**
 * @author lixinyou
 *
 */
public class XmlSignatureHelper {
	private TransformerFactory transformFactory;
	private UppSignature signature;
	private XmlStreamParser xmlParser;
	private Log log = LogFactory.getLog(XmlSignatureHelper.class);

	private List<String> merplatformNbrList = new ArrayList<String>(
			Arrays.asList(MerPlatformNbr.FSEG, MerPlatformNbr.FSJ));

	public String xmltransformsign(Object obj,String transID) throws PeException {
		@SuppressWarnings("unchecked")
		Map<String, Object> dataMap = (Map<String, Object>) obj;
		String stringPlain = this.format(dataMap,transID);
		String signData = this.xmlsign(stringPlain, dataMap);
		String signPlainData = stringPlain + signData;
		// 外部电商通知报文需要Finance节点，内部电商和o2o不需要
		if (null != dataMap.get(Dict.MER_PLATFORM_NBR)
				&& !merplatformNbrList.contains(dataMap.get(Dict.MER_PLATFORM_NBR))) {
			signPlainData = "<Finance>" + signPlainData + "</Finance>";
		}
		return signPlainData;
	}

	public String format(Map<String, Object> dataMap,String transID) throws PeException {
		Transformer transformer = transformFactory
				.getTransformer(transID);
		byte[] bytePalin = (byte[]) transformer.format(dataMap, dataMap);
		String stringPlain = null;
		try {
			stringPlain = new String(bytePalin, "UTF-8");
			stringPlain = stringPlain.substring(0, stringPlain.length()-2);
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
		}
		return stringPlain;
	}

	public Map parse(byte[] data) throws PeException {
//		Transformer transformer = transformFactory.getTransformer("xmlStreamParser");
		Map resultMap = (Map) xmlParser.parse(new ByteArrayInputStream(data), null);
		if (null == resultMap) {
			throw new PeException("parse_packte_errors");
		}
		log.debug("解析的报文" + resultMap);
		return resultMap;
	}

	public String xmlsign(String stringPlain, Map<String, Object> dataMap) throws PeException {
		log.debug("******待签名报文******" + stringPlain);
		String signData = signature.sign(stringPlain);
		signData = "<Signature>" + signData + "</Signature>";
		log.debug("******已签名报文******：\n" + signData);
		return signData;
	}
	
	public String xmlsignPlain(String stringPlain) throws PeException {
		log.debug("******财政网待签名报文******" + stringPlain);
		String signData = signature.sign(stringPlain);
		log.debug("******财政网已签名报文******：\n" + signData);
		return signData;
	}

	public TransformerFactory getTransformFactory() {
		return transformFactory;
	}

	public void setTransformFactory(TransformerFactory transformFactory) {
		this.transformFactory = transformFactory;
	}

	public UppSignature getSignature() {
		return signature;
	}

	public void setSignature(UppSignature signature) {
		this.signature = signature;
	}

	public XmlStreamParser getXmlParser() {
		return xmlParser;
	}

	public void setXmlParser(XmlStreamParser xmlParser) {
		this.xmlParser = xmlParser;
	}
}
