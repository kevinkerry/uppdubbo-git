package com.csii.upp.transport;

import java.util.Map;

import com.csii.pe.channel.stream.IdentityResolver;
import com.csii.upp.util.StringUtil;

public class DubboConsumerIdResolver implements IdentityResolver {

	private String idParameterName;
	private Map<String, Object> IdMapping;

	public String getIdParameterName() {
		return idParameterName;
	}

	public void setIdParameterName(String idParameterName) {
		this.idParameterName = idParameterName;
	}

	public Map<String, Object> getIdMapping() {
		return IdMapping;
	}

	public void setIdMapping(Map<String, Object> idMapping) {
		IdMapping = idMapping;
	}

	@Override
	public String getIdentity(Map arg0) {
		IdentityResolver identityResolver = (IdentityResolver) IdMapping
				.get(StringUtil.parseObjectToString(arg0.get(this.idParameterName)).toLowerCase());
		return identityResolver==null?null:identityResolver.getIdentity(arg0);
	}

}
