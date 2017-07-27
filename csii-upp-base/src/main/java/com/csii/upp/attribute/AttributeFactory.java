package com.csii.upp.attribute;

/**
 * @author xujin
 */
import java.util.List;
import java.util.Map;

public interface AttributeFactory {

	@SuppressWarnings("rawtypes")
	public Map<String, Attribute> getAttributesMap(Class clazz);

	@SuppressWarnings("rawtypes")
	public List<Attribute> getAttributes(Class clazz);
}
