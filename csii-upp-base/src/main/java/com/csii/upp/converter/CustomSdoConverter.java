package com.csii.upp.converter;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.apache.tuscany.sdo.SimpleAnyTypeDataObject;

import com.csii.pe.channel.ws.databinding.sdo.SdoConverter;
import com.csii.pe.channel.ws.databinding.sdo.SdoTypeUtils;
import com.csii.pe.channel.ws.databinding.sdo.SdoUtils;

import commonj.sdo11.DataObject;
import commonj.sdo11.Property;
import commonj.sdo11.Type;

public class CustomSdoConverter implements SdoConverter {
	private SdoConverter.Mapping mapping;
	private boolean useDataToMapDefault;
	private boolean useMapToDataDefault;
	private boolean outputNull;
	private boolean emptyAsNull;

	public void setDataFlatting(boolean flatting) {
		if (this.mapping != null)
			throw new IllegalArgumentException("mapping has been setted by setMapping.");
		if (flatting)
			this.mapping = FLATTING;
		else
			this.mapping = CASCADE;
	}

	public void setMapping(SdoConverter.Mapping mapping) {
		this.mapping = mapping;
	}

	private SdoConverter.Mapping getMapping() {
		if (this.mapping == null)
			return FLATTING;
		return this.mapping;
	}

	public void setUseToDataDefault(boolean def) {
		this.useDataToMapDefault = def;
	}

	public void setUseToSdoDefault(boolean def) {
		this.useMapToDataDefault = def;
	}

	public void setOutputNull(boolean o) {
		this.outputNull = o;
	}

	public void setEmptyAsNull(boolean emptyAsNull) {
		this.emptyAsNull = emptyAsNull;
	}

	public Map toData(DataObject obj) {
		Map map = new HashMap();
		if (obj == null)
			return map;
		Stack stack = new Stack();
		toData(obj, map, stack);
		getMapping().setDataMap(null, map, null, null);
		return map;
	}

	private void toData(DataObject obj, Map map, Stack stack) {
		Type type = obj.getType();
		List properties = type.getProperties();
		int i = 0;
		for (int size = properties.size(); i < size; ++i) {
			Property property = (Property) properties.get(i);
			String name = property.getName();

			if (property.isMany()) {
				List values = obj.getList(i);
				if ((values != null) && (values.size() > 0)) {
					List list = new ArrayList();
					stack.push(name);
					int v = 0;
					for (int count = values.size(); v < count; ++v) {
						Object value = values.get(v);

						if (!(property.isContainment())) {
							value = toDataValue(value, property);
							if (value == null) {
								continue;
							}
							list.add(value);
						} else {
							if (value == null)
								continue;
							Map inner = new HashMap();
							list.add(inner);
							toData((DataObject) value, inner, stack);
						}
					}

					stack.pop();
					getMapping().setDataValue(name, map, list, stack);
				}
			} else {
				Object value = (obj.isSet(i)) ? obj.get(i) : null;

				if (!(property.isContainment())) {
					value = toDataValue(value, property);
					if (value == null)
						continue;
					getMapping().setDataValue(name, map, value, stack);
				} else if (value != null) {
					if (value instanceof SimpleAnyTypeDataObject) {
						value = ((SimpleAnyTypeDataObject) value).getValue();
						if (value != null)
							getMapping().setDataValue(name, map, value, stack);
					} else {
						Map inner = new HashMap();
						stack.push(name);
						toData((DataObject) value, inner, stack);
						stack.pop();
						getMapping().setDataMap(name, map, inner, stack);
					}
				}
			}
		}
	}

	private Object toDataValue(Object value, Property property) {
		if (isNullValue(value)) {
			if (useDataToMapDefault) {
				value = property.getDefault();
			}
		} else {
			value = SdoTypeUtils.toData(property.getType(), value);
		}
		return value;
	}

	private boolean isNullValue(Object value) {
		return value == null || (emptyAsNull && "".equals(value));
	}

	public DataObject toSdo(Map map, DataObject obj) {
		if ((obj == null) || (map == null))
			return null;
		Stack stack = new Stack();
		map = getMapping().getSdoMap(null, map, null);
		toSdo(obj, map, stack);
		return obj;
	}

	private void toSdo(DataObject obj, Map map, Stack stack) {
		Type type = obj.getType();
		List properties = type.getProperties();
		int i = 0;
		for (int size = properties.size(); i < size; ++i) {
			Property property = (Property) properties.get(i);
			String name = property.getName();

			if (property.isMany()) {
				List values = (List) getMapping().getSdoValue(name, map, stack);
				if ((values != null) && (!(values.isEmpty()))) {
					List list = new ArrayList();
					stack.push(name);
					int v = 0;
					for (int count = values.size(); v < count; ++v) {
						Object value = values.get(v);

						if (!(property.isContainment())) {
							value = toSdoValue(value, property);
							if ((value == null) && (!(this.outputNull))) {
								continue;
							}
							list.add(value);
						} else if (value != null) {
							DataObject obj1 = getMapping().createObject((Map) value, obj, property);
							list.add(obj1);
							toSdo(obj1, (Map) value, stack);
						} else {
							if (!(this.outputNull))
								continue;
							DataObject obj1 = obj.createDataObject(property);
							list.add(obj1);
						}
					}

					stack.pop();
					obj.setList(i, list);
				}
			} else {
				Object value = map;

				if (!(property.isContainment())) {
					value = getMapping().getSdoValue(name, map, stack);
					value = toSdoValue(value, property);
					if ((value == null) && (!(this.outputNull)))
						continue;
					obj.set(name, value);
				} else if ((property.getType().isAbstract()) && ("DataObject".equals(property.getType().getName()))) {
					value = getMapping().getSdoValue(name, map, stack);
					if (value != null) {
						DataObject obj1 = SdoUtils.createDataTypeWrapper(value.getClass(), value);
						obj.set(name, obj1);
					} else if (this.outputNull) {
						obj.set(name, null);
					}
				} else {
					value = getMapping().getSdoMap(name, map, stack);
					if (value != null) {
						DataObject obj1 = getMapping().createObject((Map) value, obj, property);
						obj.set(name, obj1);
						stack.push(name);
						toSdo(obj1, (Map) value, stack);
						stack.pop();
					} else {
						if (!(this.outputNull))
							continue;
						DataObject obj1 = obj.createDataObject(property);
						obj.set(name, obj1);
					}
				}
			}
		}
	}

	private Object toSdoValue(Object value, Property property) {
		if ((value == null) && (this.useMapToDataDefault))
			value = property.getDefault();
		if (value != null) {
			if (value instanceof oracle.sql.TIMESTAMP) {
				value = SdoTypeUtils.toDso(property.getType(), getDate(value));
			} else {
				value = SdoTypeUtils.toDso(property.getType(), value);
			}

		}
		return value;
	}

	private Date getDate(Object value) {
		Timestamp timestamp = null;
		try {
			timestamp = (Timestamp) value;
		} catch (Exception e) {
			timestamp = getOracleTimestamp(value);
		}
		if (timestamp != null)
			return new Date(timestamp.getTime());
		else
			return null;
	}

	private Timestamp getOracleTimestamp(Object value) {
		try {
			Class clz = value.getClass();
			Method m = clz.getMethod("timestampValue", null);
			return (Timestamp) m.invoke(value, null);

		} catch (Exception e) {
			return null;
		}
	}
}