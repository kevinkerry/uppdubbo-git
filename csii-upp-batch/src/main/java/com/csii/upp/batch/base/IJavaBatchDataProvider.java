package com.csii.upp.batch.base;

import java.util.Map;
///数据分组应用的接口，所有的分组应用实现此接口
public interface IJavaBatchDataProvider {
	Object[]  getBatchData (Map<String ,Object> argMaps);
}
