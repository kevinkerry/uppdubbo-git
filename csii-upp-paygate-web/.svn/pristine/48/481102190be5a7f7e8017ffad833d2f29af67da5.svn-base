package com.csii.upp.paygate.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.action.BaseAction;

/**
 * 
 * context的key首字母转为小写
 * @author 徐锦
 * 
 */
public class LowerCaseKeyAction extends BaseAction {

	@SuppressWarnings("unchecked")
	@Override
	public void execute(Context context) throws PeException {
		context.setDataMap(this.transferResultMap(context.getDataMap()));
	}
	
	
	private Map transferResultMap(Map<String, Object> resultMap) {
		Map<String, Object> tmpMap=new HashMap<String, Object>();
		for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
			String key=entry.getKey();
			Object value=entry.getValue();
			if (Character.isUpperCase(key.charAt(0))) {
				String firstOneKey = (new StringBuilder()).append(Character.toLowerCase(key.charAt(0))).append(key.substring(1))
						.toString();
				if(value instanceof Map){
					tmpMap.put(firstOneKey, this.transferResultMap((Map)value));
				}if(value instanceof List){
					List<Map> list=(List<Map>)value;
					List<Object> result = new ArrayList<Object>();
					for (Map map : list) {
						result.add(this.transferResultMap(map));
					}
					tmpMap.put(firstOneKey, result);
				}else{
					tmpMap.put(firstOneKey, value);
				}
			}
		}
		resultMap.putAll(tmpMap);;
		return resultMap;
	}


}