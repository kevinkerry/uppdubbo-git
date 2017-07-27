package com.csii.upp.paygate.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.ObjectMapper;

import com.csii.pe.channel.http.servlet.StreamView;
import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.signature.UppSignature;

public class HttpJsonStreamView extends StreamView {
	
	public HttpJsonStreamView() {
		super();
		setContentField("Content");
	}

	private String charset;
	private UppSignature signature;

	public void setSignature(UppSignature signature) {
		this.signature = signature;
	}

	public void render(String viewReferer, Object model, Locale locale,
			HttpServletRequest request, HttpServletResponse response) {
		super.preventCaching(response);
		
		String resp = null;
		try {
			Map respMap = new HashMap();
			Map dataMap = new HashMap((Map) model);
			Map content = (Map) dataMap.get(getContentField());
			if(content == null) {
				throw new PeRuntimeException("invalid_packet_data");
			}
			
			//remove req
			content.remove(Const.PLAIN);
			content.remove(Const.SIGNATURE);
			content.remove(Const.TRANS_NAME);
			
			ObjectMapper objMapper = new ObjectMapper();
			objMapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
			objMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
			String jsonPlain2 = objMapper.writeValueAsString(content);
			log.debug("RESPONSE PLAIN:" + jsonPlain2);
			String signature = (String) dataMap.get(Const.SIGNATURE);
//			if(signature == null || signature.equals("")) {
				signature = this.signature.sign(jsonPlain2);
//			}
			respMap.put(Const.PLAIN, jsonPlain2);
			respMap.put(Const.SIGNATURE, signature);
			respMap.put(Const.TRANS_NAME, dataMap.get(Const.TRANS_NAME));
			resp = objMapper.writeValueAsString(respMap);
//			resp = writePlain(resp, jsonPlain2);
			log.info("RESPONSE MESSAGE:" + resp);
		} catch (Exception ex) {
			log.error("format response packet error.", ex);
		} finally {
			ServletOutputStream output = null;
			try {
				byte[] respbytes = resp.getBytes(charset);
				output = response.getOutputStream();
				response.setContentLength(respbytes.length);
				response.setContentType(getContentType());
				output.write(respbytes);
				output.flush();
			} catch (IOException ex) {
				log.error("response error.", ex);
			} finally {
				if (output != null) {
					try {
						output.close();
					} catch (IOException ex) {
						log.error("output stream close error.", ex);
					}
				}
			}
		}
	}

	@Deprecated
	private String writePlain(String resp, String plain) {
		// TODO Auto-generated method stub
		int quol = resp.indexOf("{");
		//plain = "\"" + Constants.APP_SIGN_TAG  + plain + Constants.APP_SIGN_TAG + "\""; // hssapp 签名明文串添加前后两个特殊标记
		plain = "\"Plain\":" + plain;
		return resp.substring(0, quol + 1) + plain + "," + resp.substring(quol + 1);
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}
}
