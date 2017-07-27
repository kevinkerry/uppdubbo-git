package com.csii.upp.fundprocess.resolver;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import com.csii.pe.channel.http.IdentityResolver;
import com.csii.pe.channel.http.LocalServletContext;
import com.csii.pe.channel.http.servlet.StreamContextResolver;
import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.pe.core.PeRuntimeException;

/**
 * 解析URL格式参数
 * 
 * @author 徐锦
 *
 */
public class URLContextResolver extends StreamContextResolver {
	protected Context createContext(HttpServletRequest request, Locale locale,
			IdentityResolver idResolver) throws PeException {
		byte[] stream = getStream(request);

		if (stream == null) {
			return super.createContext(request, locale, idResolver);
		}

		List<NameValuePair> values = URLEncodedUtils.parse(new String(stream),
				Charset.forName(request.getCharacterEncoding()));

		Map<String, String> map = new HashMap<String, String>();

		for (NameValuePair pair : values) {
			map.put(pair.getName(), pair.getValue());
		}

		String transactionId = idResolver.getIdentity(request, map);
		if (transactionId == null) {
			return super.createContext(request, locale, idResolver);
		}

		return new LocalServletContext(transactionId, map, request, null,
				locale, this.trsFlowManager);
	}

	private byte[] getStream(HttpServletRequest request) {
		int contentLength = request.getContentLength();
		if (contentLength <= 0)
			return null;

		byte[] content = new byte[contentLength];

		int offset = 0;
		while (offset < contentLength) {
			try {
				int realLength = request.getInputStream().read(content, offset,
						contentLength - offset);
				if (realLength == -1) {
					return null;
				}
				offset += realLength;
			} catch (IOException e) {
				throw new PeRuntimeException("request_isnot_a_valid_stream");
			}

		}

		return content;
	}
}
