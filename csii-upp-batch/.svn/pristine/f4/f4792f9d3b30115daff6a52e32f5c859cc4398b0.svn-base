package com.csii.upp.batch.appl;

import java.util.Map;

import com.csii.upp.batch.base.IJavaBatchDataProvider;

public class SimpleTestGroupAppl implements IJavaBatchDataProvider{
	@Override
	public Object[] getBatchData(Map<String, Object> argMaps) {
		int count=10000;
		Object[] objects = new Object[count];
		for (int i = 0; i < count; i++) {
			objects[i] = i;
		}
		return objects;
	}
}
