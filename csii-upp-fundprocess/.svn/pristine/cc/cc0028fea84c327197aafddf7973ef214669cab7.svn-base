package com.csii.upp.fundprocess.xml;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.csii.pe.transform.TransformException;
import com.csii.pe.transform.object.ObjectTransformer;
import com.csii.upp.dict.Dict;
import com.csii.upp.util.StringUtil;

/**
 * 响应码处理
 * 
 * @author xujin
 *
 */
public class CupsReturnTransformer implements ObjectTransformer {
	public static final String ERROR_SPLIT = ";";
	private Properties respCodeMapping;

	public void setRespCodeMapping(String configPath) throws Exception {
		respCodeMapping = new Properties();
		respCodeMapping.load(getClass().getResourceAsStream(configPath));
	}

	@Override
	public Object format(Object arg0, Map arg1) throws TransformException {
		Map map = (Map) arg0;
		String respCode=StringUtil.parseObjectToString(map.get(Dict.RESP_CODE));
		String code="91";
		for (Entry entry : respCodeMapping.entrySet()) {
			String value=(String) entry.getValue();
			String key=(String)entry.getKey();
			if (respCode.equals(key)) {
				int index = value.indexOf(ERROR_SPLIT);
				if (index > 0) {
					code = value.substring(0, index);
					break;
				}
			}
		}
		map.put(Dict.RESP_CODE,code);
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
