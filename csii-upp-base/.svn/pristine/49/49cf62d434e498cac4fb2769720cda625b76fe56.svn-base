package com.csii.upp.marshaller;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.csii.pe.core.PeException;
/**
 * 
 * @classname	ObjectMapMarshaller
 * @description	TODO(对象解析成map或map解析成Object的接口)
 * @author		xujin
 *
 */
public interface ObjectMapMarshaller{
	/**
	 * 
	 * @description	TODO(对象转化为map)
	 * @param object
	 * @return
	 * @throws PeException 
	 */
	public Map<String, Object> marshall(Object object) throws PeException;
	
	/**
	 * 
	 * @description	TODO(对象转化为map的列表)
	 * @param list
	 * @return
	 * @throws PeException 
	 */
	public List<Map<String, Object>> marshall(List list) throws PeException;
	
	/**
	 * 
	 * unMarshall:将map转化为对象. <br/>
	 * @param clazz
	 * @param map
	 * @param actionCode
	 * @return
	 * @throws PeException
	 * @since JDK 1.6
	 */
	public <T> T unMarshall(Class<T> clazz, Map<String, Object> map) throws PeException;
	/**
	 * 
	 * unMarshall:将map转化为对象. <br/>
	 * @param clazz
	 * @param map
	 * @param actionCode
	 * @return
	 * @throws PeException
	 * @since JDK 1.6
	 */
	public <T> T unMarshall(Class<T> clazz, Map<String, Object> map, Properties mapping) throws PeException;
	/**
	 * 
	 * @description	TODO(将map转化为对象的列表)
	 * @param clazz
	 * @param list
	 * @param actionCode
	 * @return
	 * @throws PeException
	 */
	public <T> List<T> unMarshall(Class<T> clazz,List<Map<String,Object>> list, Properties mapping) throws PeException;
}
