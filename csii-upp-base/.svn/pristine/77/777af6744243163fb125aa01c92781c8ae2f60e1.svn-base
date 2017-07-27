package com.csii.upp.attribute;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

import com.csii.pe.core.PeException;
import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 
 * @classname Attribute
 * @description TODO(存放对象的属性特性并验证属性值是否满足特性)
 * @author xujin
 * 
 */
public class Attribute {

	private String name;

	private Method getter;

	private Method setter;
	/**
	 * 如果该对象描述的是一个List，则该属性不应为空
	 */
	@SuppressWarnings("rawtypes")
	private Class elementClass;

	@SuppressWarnings("rawtypes")
	private Class type;

	private boolean required = false;

	private String[] checkConstraint;

	private int[] decimal = { 20, 2 };

	private String checkConstraintStr;

	private String dateFormat;

	@SuppressWarnings("rawtypes")
	public Attribute(String name, Method _getter, Method _setter, Class type) {
		this.name = name;
		this.getter = _getter;
		this.setter = _setter;
		this.type = type;
	}

	@SuppressWarnings("rawtypes")
	public Attribute(String name, Method _getter, Method _setter, Class type,
			Class _elementClass) {
		this(name, _getter, _setter, type);
		this.elementClass = _elementClass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@SuppressWarnings("rawtypes")
	public Class getType() {
		return type;
	}

	@SuppressWarnings("rawtypes")
	public void setClassOb(Class classOb) {
		this.type = classOb;
	}

	public Object getValue(Object target) {
		try {
			return getGetter().invoke(target, null);
		} catch (IllegalArgumentException e) {
			throw new PeRuntimeException(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new PeRuntimeException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new PeRuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 对Timestamp类型和BigDecimal类型应做特别处理： BigDecimal类型的精度统一为2位
	 * 
	 * @param target
	 * @param _value
	 * @throws PeException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	public void setValue(Object target, Object _value) throws PeException {
		try {
			if (_value == null) {
				setter.invoke(target, _value);
				return;
			}

			Class valueType = _value.getClass();

			if (valueType == String.class) {
				_value = _value.toString().trim();
				if (_value.equals("")) {
					return;
				}
			}

			if (valueType != type && valueType == String.class) {
				if (type == Date.class) {
					try {
						if (StringUtil.isStringEmpty(this.getDateFormat())) {
							setter.invoke(target,
									DateUtil.Object_To_Date(_value));
						} else {
							setter.invoke(target, DateUtil
									.DateTimeFormat_To_Date(_value,
											this.getDateFormat()));
						}
					} catch (Exception e) {
						throw new PeException(name
								+ " information input value is "
								+ _value.toString()
								+ ",input values are not legitimate");
					}
				} else if (type == Time.class) {
					String[] str = _value.toString().split(":");

					if (str.length == 3) {
						Time t = new Time(Integer.valueOf(str[0]),
								Integer.valueOf(str[1]),
								Integer.valueOf(str[2]));
						setter.invoke(target, t);
					} else {
						throw new PeException(name
								+ " information input value is "
								+ _value.toString()
								+ ",input values are not legitimate");
					}

				} else {
					Constructor cons = type.getConstructor(_value.getClass());

					Object value = null;
					try {
						value = cons.newInstance(_value);
					} catch (Exception e) {
						throw new PeException(name
								+ " information input value is "
								+ _value.toString()
								+ ",input values are not legitimate");
					}
					if (type == BigDecimal.class) {
						BigDecimal temp = ((BigDecimal) value);
						if (decimal != null) {
							if (temp.precision() > decimal[0]
									|| temp.scale() > decimal[1]
									|| (temp.precision() - temp.scale()) > (decimal[0] - decimal[1])) {
								throw new PeException(name
										+ " information input value is "
										+ _value.toString()
										+ ",input values are not legitimate");
							}
						}
						value = temp;
					}
					setter.invoke(target, value);
				}
			}else if(valueType == Long.class&&type==String.class){
				_value = _value.toString();
				setter.invoke(target, _value);
			}
			else {
				setter.invoke(target, _value);
			}
		} catch (SecurityException e) {
			throw new PeRuntimeException(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			throw new PeRuntimeException(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			throw new PeRuntimeException(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new PeRuntimeException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new PeRuntimeException(e.getMessage(), e);
		}
	}

	private Method getGetter() {
		return getter;
	}

	public void setGetter(Method function) {
		getter = function;
	}

	public Method getSetter() {
		return setter;
	}

	public void setSetter(Method function) {
		setter = function;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<[name=" + name).append("][type=" + type)
				.append("][getter=" + getter.getName())
				.append("][setter=" + setter.getName())
				.append("][eObjClass=" + elementClass)
				.append("][isRequired = " + isRequired())
				.append("][dateFormat = " + getDateFormat())
				.append("][decimal[] = " + getDecimal())
				.append("][checkConstraint[] = " + getCheckConstraint())
				.append("]>");
		return sb.toString();
	}

	@SuppressWarnings("rawtypes")
	public void setElementClass(Class elementClass) {
		this.elementClass = elementClass;
	}

	@SuppressWarnings("rawtypes")
	public Class getElementClass() {
		return elementClass;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean _required) {
		required = _required;
	}

	public String[] getCheckConstraint() {
		return checkConstraint;
	}

	public void setCheckConstraint(String[] checkConstraint) {
		this.checkConstraint = checkConstraint;
		if (checkConstraint != null && checkConstraint.length > 0) {
			StringBuilder sb = new StringBuilder();
			for (String str : checkConstraint) {
				sb.append(str + "、");
			}
			checkConstraintStr = sb.substring(0, sb.length() - 1);
		}
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getCheckConstraintStr() {

		return checkConstraintStr;
	}

	public int[] getDecimal() {
		return decimal;
	}

	public void setDecimal(int[] decimal) {
		this.decimal = decimal;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws SecurityException,
			NoSuchMethodException {
		String _value = "0.434222";
		Class type = BigDecimal.class;
		Constructor cons = type.getConstructor(_value.getClass());

		int[] decimal = { 12, 6 };

		Object value = null;
		BigDecimal v = null;
		try {
			value = cons.newInstance(_value);
		} catch (Exception e) {

		}
		if (type == BigDecimal.class) {
			BigDecimal temp = ((BigDecimal) value);
			if (temp.precision() > decimal[0]
					|| temp.scale() > decimal[1]
					|| (temp.precision() - temp.scale()) > (decimal[0] - decimal[1])) {
				System.out.println("error");
			}

			v = ((BigDecimal) value);

		}
		System.out.println(v.precision());
		System.out.println(v.scale());

	}

}
