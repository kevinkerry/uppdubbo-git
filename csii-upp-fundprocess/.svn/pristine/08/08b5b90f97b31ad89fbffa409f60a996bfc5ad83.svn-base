package com.csii.upp.fundprocess.xml;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.csii.pe.core.PeException;
import com.csii.pe.transform.TransformException;
import com.csii.pe.transform.object.ObjectTransformer;
import com.csii.upp.util.DateUtil;

/**
 * 获取SYS_HEAD中的交易名称
 * 
 * @author 徐锦
 *
 */
public class ReturnTransformer implements ObjectTransformer {
	private Properties typeMapping;

	public void setTypeMapping(String configPath) throws Exception {
		typeMapping = new Properties();
		typeMapping.load(getClass().getResourceAsStream(configPath));
	}

	@Override
	public Object format(Object arg0, Map arg1) throws TransformException {
		Map map = (Map) arg0;

		Object value;
		for (Entry entry : typeMapping.entrySet()) {
			try {
				if ("DATE".equalsIgnoreCase((String) entry.getValue())) {
						map.put(entry.getKey(),
								DateUtil.DateFormat_To_Date(map.get(entry.getKey())));
	
				} else if ("TIMESTAMP".equalsIgnoreCase((String) entry.getValue())) {
					map.put(entry.getKey(), DateUtil.DateTimeFormat_To_Date(map
							.get(entry.getKey())));
				}
			} catch (PeException e) {
				throw new TransformException(e);
			}
		}

		return map;
	}

	@Override
	public Object parse(Object arg0, Map arg1) throws TransformException {
		return arg0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
