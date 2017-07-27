package com.csii.upp.qrcodeplatform.action.base;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import com.csii.pe.chain.command.AbstractChannelCommand;
import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.pe.core.PeRuntimeException;
import com.csii.pe.validation.BreakableValidator;
import com.csii.pe.validation.FieldList;
import com.csii.pe.validation.ReturnValueValidator;
import com.csii.pe.validation.Style;
import com.csii.pe.validation.ValidationException;
import com.csii.pe.validation.Validator;
import com.csii.pe.validation.rule.Expr;
import com.csii.pe.validation.rule.OgnlExpr;

public class StyleValidationCommand extends AbstractChannelCommand {
	
	protected Log log = LogFactory.getLog(super.getClass());

	public Map<String, Validator> validators;

	private Expr expr = new OgnlExpr();

	protected boolean channelExecute(Context context, Map setting)
			throws PeException {
		Map fields = context.getTransactionConfig().getFields();

		if (fields == null) {
			return false;
		}

		for (Iterator it = fields.keySet().iterator(); it.hasNext();) {
			String fieldName = (String) it.next();
			Object fieldDefinition = fields.get(fieldName);
			context.setData(fieldName,doValidation(fieldName, fieldDefinition,
					context.getData(fieldName), context));
		}

		return false;
	}

	protected Object doValidation(String name, Object fieldDefinition,
			Object value, Context context) throws PeException {
		if ((fieldDefinition == null) || (fieldDefinition instanceof String)) {
			if ((fieldDefinition == null)
					|| (((String) fieldDefinition).length() == 0)) {
				return value;
			}

			String styleString = (String) fieldDefinition;

			Map attributes = null;

			int offset = styleString.indexOf(123);
			if (offset > 0) {
				attributes = new HashMap(5);

				int offset2 = styleString.indexOf(125);
				if (offset2 <= 0) {
					throw new PeException("Style_String_is_invalid:" + name);
				}

				String attrStr = styleString.substring(offset + 1, offset2);

				attributes = parseAttributes(attrStr);

				styleString = styleString.substring(0, offset);
			}

			ApplicationContext tcContext = context.getTransactionConfig()
					.getApplicationContext();
			Style style;
			if (tcContext.containsBean(styleString)) {
				style = (Style) tcContext.getBean(styleString);
			} else {
				style = (Style) getApplicationContext().getBean(styleString);
			}
			return styleValidate(style, name, value, attributes, context);
		}

		if (fieldDefinition instanceof Map) {
			Map currentMap = (Map) value;

			for (Iterator it = ((Map) fieldDefinition).keySet().iterator(); it
					.hasNext();) {
				Object key = it.next();
				StringBuffer fullName = new StringBuffer(name);
				if (name.length() > 0) {
					fullName.append('.');
				}
				fullName.append(key);
				Object object = doValidation(fullName.toString(),
						((Map) fieldDefinition).get(key), currentMap.get(key),
						context);
				currentMap.put(key, object);
			}

		} else if (fieldDefinition instanceof FieldList) {
			FieldList fieldList = (FieldList) fieldDefinition;
			String condition = fieldList.getCondition();
			List list = (List) value;
			Map fieldListDefinition = fieldList.getFields();
			int i = 0;
			for (Iterator listit = list.iterator(); listit.hasNext();) {
				Map currentMap = (Map) listit.next();

				if ((condition != null) && (condition.length() > 0)) {
					boolean b = ((Boolean) this.expr.getValue(condition, null,
							currentMap)).booleanValue();
					if (!(b)) {
						listit.remove();
						++i;
					}

				} else {
					String fullname = name + '[' + i + "]";

					doValidation(fullname, fieldListDefinition, currentMap,
							context);
					++i;
				}
			}
		}
		return value;
	}

	private Map parseAttributes(String attr) {
		if ((attr == null) || (attr.trim().length() == 0)) {
			return null;
		}
		Map ret = new HashMap(5);
		String[] attrs = StringUtils.delimitedListToStringArray(attr, ";");
		for (int i = 0; i < attrs.length; ++i) {
			if (attrs[i].trim().length() == 0)
				continue;
			String[] item = StringUtils.delimitedListToStringArray(attrs[i],
					"=");
			ret.put(item[0], item[1]);
		}
		return ret;
	}

	public Object styleValidate(Style style, String name, Object value,
			Map attributes, Context context) throws ValidationException {
		Map setting = style.getSetting();

		Map attrs;
		if (setting != null) {
			attrs = new HashMap(setting);
			if (attributes != null)
				attrs.putAll(attributes);
		} else {
			if (attributes != null) {
				attrs = attributes;
			} else
				throw new PeRuntimeException("style_with_empty_attributes");
		}
		return styleValidateInternal(style, name, value, attrs, context);
	}

	private Object styleValidateInternal(Style style, String name,
			Object value, Map attributes, Context context)
			throws ValidationException {
		if ((value != null) && (value.getClass().isArray())) {
			Object[] objectArray = new Object[Array.getLength(value)];
			for (int index = 0; index < Array.getLength(value); ++index) {
				Object item = Array.get(value, index);
				objectArray[index] = styleValidateInternal(style, name, item,
						attributes, context);
			}

			if (objectArray.length > 0) {
				try {
					Object returnArray = Array.newInstance(objectArray[0].getClass(), objectArray.length);
					System.arraycopy(objectArray, 0, returnArray, 0,objectArray.length);
					return returnArray;
				} catch (Exception e) {
					return objectArray;
				}

			}

			return objectArray;
		}
		int size;
		Object item;
		if ((value != null) && (value instanceof List)) {
			List values = (List) value;
			Object[] objectArray = new Object[values.size()];
			if (values.size() > 0) {
				int index = 0;
				for (size = values.size(); index < size; ++index) {
					item = values.get(index);
					objectArray[index] = styleValidateInternal(style, name,
							item, attributes, context);
				}
				try {
					Object returnArray = Array.newInstance(objectArray[0].getClass(), objectArray.length);
					System.arraycopy(objectArray, 0, returnArray, 0,objectArray.length);
					return returnArray;
				} catch (Exception e) {
					return objectArray;
				}
			}
			return objectArray;
		}

		Object val = value;
		for (Entry<String, Validator> entry : validators.entrySet()) {
			String shortName = entry.getKey();
			Validator validator = entry.getValue();
			String attributeValue = (String) attributes.get(shortName);
			if (attributeValue == null || validator == null) {
				continue;
			}
			String message = null;
			if (style.getMessages() != null) {
				message = (String) style.getMessages().get(shortName);
			}
			if (validator instanceof ReturnValueValidator) {
				val = ((ReturnValueValidator) validator).validate(name, val,
						attributes, message, context);
			} else {
				if (validator instanceof BreakableValidator) {
					boolean breakFlag = ((BreakableValidator) validator)
							.validate(name, val, attributes, message, context);
					if (!(breakFlag))
						continue;
					break;
				}

				this.log.debug("invalid validator:" + validator.getShortName()
						+ " name:" + name + " value:" + value);
			}

		}

		return val;
	}

	public void setValidators(Map map) {
		this.validators = map;
	}
}