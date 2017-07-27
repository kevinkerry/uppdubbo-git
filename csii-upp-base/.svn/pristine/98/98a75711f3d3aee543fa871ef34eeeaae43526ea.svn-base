package com.csii.upp.core;

import java.util.Map;

import org.slf4j.MDC;

import com.csii.pe.core.PeException;

public class LoggingInterceptor {
	private String[] loggingFields;

	public void setLoggingFields(String[] loggingFields) {
		if (loggingFields != null) {
			this.loggingFields = new String[loggingFields.length];
			for (int i = 0; i < loggingFields.length; ++i) {
				String basename = loggingFields[i];
				this.loggingFields[i] = basename.trim();
			}
		} else {
			this.loggingFields = new String[0];
		}
	}

	public void preChain(Map<?, ?> Map) throws PeException {
		for (int i = 0; i < this.loggingFields.length; i++) {
			String name = this.loggingFields[i];
			Object v = Map.get(name);
			if(v!=null){
				MDC.put(name, v.toString());
			}
		}
	}

	public void cleanUp() {
		MDC.clear();
	}
}
