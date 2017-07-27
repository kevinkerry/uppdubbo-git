package com.csii.upp.marshaller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csii.pe.core.PeException;
import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.attribute.Attribute;
import com.csii.upp.attribute.AttributeFactory;
import com.csii.upp.idto.IDto;
import com.csii.upp.util.StringUtil;

/**
 * 
 * @classname DefaultObjectMapMarshaller
 * @description TODO(对象解析成map或map解析成Object的实现类)
 * @author xujin
 * @date 2014-1-7
 * 
 */

public class DefaultObjectMapMarshaller implements ObjectMapMarshaller {

	protected final static Log log = LogFactory.getLog(ObjectMapMarshaller.class);

	private AttributeFactory attributeFactory;

	public AttributeFactory getAttributeFactory() {
		return attributeFactory;
	}

	public void setAttributeFactory(AttributeFactory attributeLoadFactory) {
		this.attributeFactory = attributeLoadFactory;
	}

	/**
	 * 对象解析成map
	 * 
	 * @param object
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, Object> marshall(Object object) throws PeException {
		if (StringUtil.isObjectEmpty(object)) {
			return null;
		}
		try {
			Map<String, Object> data = new HashMap<String, Object>();
			List<Attribute> list = attributeFactory.getAttributes(object.getClass());
			String key = null;
			for (Attribute attribute : list) {
				key = attribute.getName();
				Object value = getValue(attribute, object);
				if (StringUtil.isObjectEmpty(value))
					continue;

				// 如果required注解进行属性的非空检查
				if (StringUtil.isObjectEmpty(value) || StringUtil.isStringEmpty(value.toString())) {
					if (attribute.isRequired()) {
						throw new PeException(key + " information is losing type, input values are not legitimate");
					} else {
						continue;
					}
				}

				// 如果有值就验证域唯一值
				String[] checkConstraint = attribute.getCheckConstraint();
				if (checkConstraint != null && checkConstraint.length > 0) {
					String str = (String) value;
					if (!StringUtil.isStringEmpty(str)) {
						boolean ischeck = false;
						for (String constraint : checkConstraint) {
							if (str.equals(constraint)) {
								ischeck = true;
								break;
							}
						}
						if (!ischeck) {
							throw new PeException(key + " information input value:" + str + ", must be a value in "
									+ attribute.getCheckConstraintStr() + " input");
						}
					}
				}
				if (value instanceof List) {
					Class elementClazz = attribute.getElementClass();
					if (elementClazz == null)
						put(data, attribute.getName(), value);
					else
						put(data, attribute.getName(), marshall((List) value));
				} else if (value instanceof IDto) {
					put(data, attribute.getName(), marshall(value));
				} else {
					put(data, attribute.getName(), value);
				}

			}
			return data;
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new PeRuntimeException(e.getMessage());
		}
	}

	/**
	 * 把map解析成Object （无论map中key是否存在，只要value为空，转化为对象时其对应的属性值即为空）
	 * 
	 * @param <T>
	 * @param clazz
	 * @param map
	 * @param 交易操作类型
	 *            对应于ActionCode枚举值 不能为空
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> T unMarshall(Class<T> clazz, Map<String, Object> map, Properties mapping) throws PeException {
		if (StringUtil.isObjectEmpty(map))
			return null;
		this.addMapping(map, mapping);
		try {
			T instance = clazz.newInstance();
			List<Attribute> list = attributeFactory.getAttributes(clazz);
			String key = null;
			Object value = null;
			Class attributeClass = null;
			for (Attribute attribute : list) {
				key = attribute.getName();
				attributeClass = attribute.getType();
				value = get(map, key);

				if (java.util.List.class.isAssignableFrom(attributeClass)) {
					Class elementClazz = attribute.getElementClass();
					if (elementClazz == null) {
						attribute.setValue(instance, value);
						continue;
					}

					attribute.setValue(instance, unMarshall(elementClazz, (List) value, mapping));
					continue;
				}
				if (IDto.class.isAssignableFrom(attributeClass)) {
					attribute.setValue(instance, unMarshall(attributeClass, (Map<String, Object>) value, mapping));
					continue;
				}
				attribute.setValue(instance, value);
			}
			return instance;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new PeRuntimeException(e.getMessage(), e);
		}
	}

	private Object getValue(Attribute attribute, Object target) throws ClassNotFoundException, SecurityException,
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		return attribute.getValue(target);
	}

	private String getValueAsString(Attribute attribute, Object target)
			throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		return getValue(attribute, target).toString();
	}

	public List<Map<String, Object>> marshall(List list) throws PeException {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (Object object : list) {
			result.add(marshall(object));
		}
		return result;
	}

	public <T> List<T> unMarshall(Class<T> clazz, List<Map<String, Object>> list, Properties mapping)
			throws PeException {
		List<T> result = new ArrayList<T>();
		for (Map<String, Object> map : list) {
			result.add(unMarshall(clazz, map, mapping));
		}
		return result;
	}

	private void put(Map map, String key, Object value) {
		log.debug(StringUtil.buildString(key, "=",value));
		map.put(key, value);
	}

	private Object get(Map map, String key) {
		Object value=map.get(key);
		log.debug(StringUtil.buildString(key, "=",value));
		return map.get(key);
	}

	private void addMapping(Map map, Properties mapping) {
		if (mapping != null) {
			Enumeration<?> allKey = mapping.propertyNames();
			while (allKey.hasMoreElements()) {
				String key = (String) allKey.nextElement();
				Object value = map.get(key);
				if (!StringUtil.isObjectEmpty(value)) {
					map.put(mapping.get(key), value);
				}
			}
		}
	}

	@Override
	public <T> T unMarshall(Class<T> clazz, Map<String, Object> map) throws PeException {
		return unMarshall(clazz, map, null);
	}
}
