package com.csii.upp.tcp;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.csii.pe.transform.Parser;
import com.csii.pe.transform.TransformException;

public class AfterParser implements Parser {
	private String xmlBodyName;
	private Parser xmlParser;
	private String charset;

	public void setXmlBodyName(String xmlBodyName) {
		this.xmlBodyName = xmlBodyName;
	}

	public void setXmlParser(Parser xmlParser) {
		this.xmlParser = xmlParser;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public Object parse(Object object, Map context) throws TransformException {
		Map result = (Map) object;

		try {
			byte[] xmlBytes = result.get(xmlBodyName).toString()
					.getBytes(charset);
			result.putAll((Map) xmlParser.parse(new ByteArrayInputStream(xmlBytes), result));
			result.remove(xmlBodyName);
		} catch (UnsupportedEncodingException e) {
			throw new TransformException(e);
		}

		return result;
	}

}