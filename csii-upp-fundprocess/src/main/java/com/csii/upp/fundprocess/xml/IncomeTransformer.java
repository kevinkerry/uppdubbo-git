package com.csii.upp.fundprocess.xml;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.csii.pe.transform.TransformException;
import com.csii.pe.transform.object.ObjectTransformer;
import com.csii.upp.util.StringUtil;

/**
 * 获取SYS_HEAD中的交易名称
 * 
 * @author 徐锦
 *
 */
public class IncomeTransformer implements ObjectTransformer {
	private Properties fieldMapping;
	private Properties flatMapping;
	private String idParameterName = "rtxncode";
	private String msgtype = "MESSAGE_TYPE";
	private String msgcode = "MESSAGE_CODE";
	private String servicecode = "SERVICE_CODE";

	public void setFieldMapping(String configPath) throws Exception {
		fieldMapping = new Properties();
		fieldMapping.load(getClass().getResourceAsStream(configPath));
	}

	public void setFlatMapping(String configPath) throws Exception {
		flatMapping = new Properties();
		flatMapping.load(getClass().getResourceAsStream(configPath));
	}

	public void setIdParameterName(String idParameterName) {
		this.idParameterName = idParameterName;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public void setMsgcode(String msgcode) {
		this.msgcode = msgcode;
	}

	public void setServicecode(String servicecode) {
		this.servicecode = servicecode;
	}

	private String getIdentity(Map map) {
			return StringUtil.contactString(map.get(servicecode),
					StringUtil.contactString(map.get(msgtype), 
					StringUtil.contactString(map.get(msgcode),
							("BEPS".equals(map.get("FORMAT"))||
							"HVPS".equals(map.get("FORMAT"))) ? map.get("FORMAT") : null))
				);
	}


	@Override
	public Object format(Object arg0, Map arg1) throws TransformException {
		Map map = (Map) arg0;

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

		map.put(idParameterName, getIdentity(map));

		return map;
	}

	@Override
	public Object parse(Object arg0, Map arg1) throws TransformException {
		return arg0;
	}

	@Override
	public String getName() {
		return null;
	}

}
