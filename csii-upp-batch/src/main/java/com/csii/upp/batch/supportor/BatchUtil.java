package com.csii.upp.batch.supportor;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csii.upp.batch.xml.format.Field;
import com.csii.upp.batch.xml.format.FieldParser;
import com.csii.upp.batch.xml.format.FileFormat;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

public class BatchUtil {
	protected static Log log = LogFactory.getLog(BatchUtil.class);

	protected final static String DELIMITER = "&&";

	public static String getFormatString(Map<String, Object> formatMap,
			FileFormat fileFormat) {
		List<Field> standardFields = fileFormat.getFields();
		StringBuilder buf = new StringBuilder();
		for (Field field : standardFields) {
			if (!field.isDesert()) {
				Object value;
				if (field.isDate()) {
					if (StringUtil.isStringEmpty(field.getPattern())) {
						value = DateUtil.Date_To_DateFormat((Date) formatMap
								.get(field.getName()));
					} else {
						value = DateUtil.Date_To_DateTimeFormat(
								(Date) formatMap.get(field.getName()),
								field.getPattern());
					}
				} else if (field.isDatetime()) {
					if (StringUtil.isStringEmpty(field.getPattern())) {
						value = DateUtil
								.Date_To_DateTimeFormat((Date) formatMap
										.get(field.getName()));
					} else {
						value = DateUtil.Date_To_DateTimeFormat(
								(Date) formatMap.get(field.getName()),
								field.getPattern());
					}
				} else {
					value = StringUtil.parseObjectToString(formatMap
							.get(field.getName()));
				}

				if (StringUtil.isObjectEmpty(value)) {
					value = StringUtil.isStringEmpty(field.getDefaultValue())?"":field.getDefaultValue();
				}
				buf.append(fileFormat.getDelimiter()).append(value);
			}
		}
		return buf.substring(fileFormat.getDelimiter().length());
	}

	/*
	 * input value :line must not be NULL;
	 */
	@SuppressWarnings("rawtypes")
	public static Map getFormateMap(String line, FileFormat formate,
			StringBuilder err) {
		// 没有数据分隔符
		if (StringUtil.isStringEmpty(formate.getDelimiter())) {
			return getLengthFormateMap(line, formate);
		}
		List<Field> sourceFields = formate.getFields();
		Map<String, String> map = new HashMap<String, String>();
		line = StringUtil.replace(line, formate.getDelimiter(), DELIMITER);
		String[] str = line.split(DELIMITER, -1);
		if (str.length != sourceFields.size()) {
			String[] standardLine = getFormateArray(str, sourceFields.size());
			if (standardLine == null) {
				log.error(StringUtil.buildString(
						"Field number error, parsing failed,error line:", line));
				err.append("Field number error, parsing failed");
				return null;
			} else {
				log.warn(StringUtil.buildString(
						"File does not match the number of fields,line:", line));
				str = standardLine;
			}

		}
		FieldParser fieldParser=BatchApplSupportor.getFieldParser();
		for (int i = 0; i < sourceFields.size(); i++) {
			Field field = sourceFields.get(i);
			if (field.isDesert())
				continue;
			String key = field.getName();
			String value = str[i];
			value = getValueWithoutKey(value);
			if (field.isMandatory() && StringUtil.isStringEmpty(value)) {
				log.error(StringUtil.buildString("The field for the ", key,
						" cannot be empty,error line:", line));
				err.append(StringUtil.buildString("The field for the ", key,
						" cannot be empty"));
				return null;
			}
			if (field.getLength() > 0 && field.getLength() < value.length()) {
				log.error(StringUtil.buildString("Field is ", key,
						" long,error line:", line));
				err.append(StringUtil.buildString("Field is ", key, " long"));
				return null;
			}
			map.put(key, value);
			if(!StringUtil.isStringEmpty(field.getParser())){
				map.put(field.getName(),fieldParser.parser(field, map));
			}
		}
		return map;
	}

	@SuppressWarnings("rawtypes")
	public static Map getLengthFormateMap(String line1, FileFormat formate) {
		String line = line1;
		byte[] lineArray = null;
		try {
			lineArray = line.getBytes(formate.getEncoding());
		} catch (UnsupportedEncodingException e1) {
			log.error("error",e1);
		}

		List<Field> sourceFields = formate.getFields();
		Map<String, String> map = new HashMap<String, String>();
		FieldParser fieldParser=BatchApplSupportor.getFieldParser();
		int start = 0;
		int end = 0;
		log.debug("__________________________begin No data delimiter parse__________________________________");
		for (int i = 0; i < sourceFields.size(); i++) {
			Field field = sourceFields.get(i);
			int length = field.getLength();
			try {
				end = start + length;
				int num = 0;
				byte[] childChar = new byte[length];
				for (int k = start; k < end; k++) {
					childChar[num] = lineArray[k];
					num++;
				}
				start = end;
				map.put(field.getName(),
						new String(childChar, formate.getEncoding()));
				if(!StringUtil.isStringEmpty(field.getParser())){
					map.put(field.getName(),fieldParser.parser(field, map));
				}
				log.trace(field.getSource() + ":"
						+ new String(childChar, formate.getEncoding()));

			} catch (Exception e) {
				log.error("recorde:" + line1 + " length is not enough!");
			}
		}
		log.debug("__________________________End No data delimiter parse__________________________________");
		return map;
	}

	public static String[] getFormateArray(String[] str, int size) {
		String[] returnArray = new String[size];
		int strLength = str.length;
		if (str.length > size) {
			int k = -1;
			for (int i = 0; i < size; i++) {
				k = k + 1;
				if (k == strLength)
					return null;
				if (!isNeedAdd(str[k])) {
					returnArray[i] = getValueWithoutKey(str[k]);
				} else {
					String tmp = str[k];
					while (true) {
						k = k + 1;
						if (k == strLength)
							return null;
						tmp = tmp + ";" + str[k];
						if (!isNeedAdd(tmp)) {
							returnArray[i] = getValueWithoutKey(tmp);
							break;
						}
					}
				}
			}
			return returnArray;
		}
		return null;
	}

	public static boolean isNeedAdd(String value) {
		if (value != null) {
			if (value.startsWith("\"") && !value.endsWith("\""))
				return true;
		}
		return false;
	}

	public static String getValueWithoutKey(String value) {
		return getValue(value).replace("\"", "");
	}

	public static String getValue(String value) {
		if (value != null) {
			if (value.startsWith("\""))
				value = value.substring(1, value.length());
			if (value.endsWith("\""))
				value = value.substring(0, value.length() - 1);
		} else
			value = "";

		return value.trim();
	}
}
