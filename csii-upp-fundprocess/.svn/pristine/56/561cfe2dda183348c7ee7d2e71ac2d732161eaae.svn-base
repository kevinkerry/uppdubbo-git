package com.csii.upp.fundprocess.xml;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.csii.pe.transform.Parser;
import com.csii.pe.transform.TransformException;

public class AfterParser implements Parser {
	private Properties fieldMapping;
	private Properties flatMapping;

	public void setFieldMapping(String configPath) throws Exception {
		fieldMapping = new Properties();
		fieldMapping.load(getClass().getResourceAsStream(configPath));
	}

	public void setFlatMapping(String configPath) throws Exception {
		flatMapping = new Properties();
		flatMapping.load(getClass().getResourceAsStream(configPath));
	}

	@Override
	public Object parse(Object paramObject, Map paramMap)
			throws TransformException {
		Map map = (Map) paramObject;

		Object value;
		for (Entry entry : flatMapping.entrySet()) {
			if (map.get(entry.getKey()) instanceof Map) {
				map.putAll((Map) (map.get(entry.getKey())));
			}
		}

		for (Entry entry : fieldMapping.entrySet()) {
			if (entry.getValue() instanceof String) {
				value = map.get(entry.getValue());
				if (value != null)
					map.put(entry.getKey(), value);
			}
		}

		return map;
	}

}
