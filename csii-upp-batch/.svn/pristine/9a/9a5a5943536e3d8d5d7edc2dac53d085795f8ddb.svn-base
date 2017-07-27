package com.csii.upp.batch.xml.format;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csii.pe.core.PeException;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

public class FieldParser{
	private static final Log log = LogFactory.getLog(FieldParser.class);
	Map<String, Method> methods = new HashMap<String, Method>();

	public FieldParser() {
		Method[] m = FieldParser.class.getMethods();
		for (int i = 0; i < m.length; i++) {
			Method mm = m[i];
			methods.put(mm.getName(), mm);

		}
	}

	
	@SuppressWarnings("rawtypes")
	public String parser(Field field, Map sourcemap) {
		String parserName = field.getParser();
		Method method = methods.get(parserName);
		try {
			return (String)method.invoke(this, field, sourcemap);
		} catch (IllegalArgumentException e) {
			log
					.error("source data convert erors,the arguments errors date is:"
							+ field.getName()
							+ " mehtod is:"
							+ field.getParser()
							+"source is:"+sourcemap.get(field.getName())
							+"default is:"+field.getDefaultValue());
		} catch (IllegalAccessException e) {
			log
					.error("source data convert erors,the IllegalAccess errors date is:"
							+ field.getName()
							+ " mehtod is:"
							+ field.getParser()
							+"source is:"+sourcemap.get(field.getName())
							+"--default is:"+field.getDefaultValue());
		} catch (InvocationTargetException e) {
			log
					.error("source data convert erors,the InvocationTarget errors date is:"
							+ field.getName()
							+ " mehtod is:"
							+ field.getParser()
							+"--source is:"+sourcemap.get(field.getName())
							+"--default is:"+field.getDefaultValue());
		}
		return null;

	}
	
	public String trim(Field field, final Map<String, String> sourceMap) {
		String name = field.getName();
		String v = sourceMap.get(name);
		String strTrim=v.trim();
		if(StringUtil.isStringEmpty(strTrim)){
			return strTrim;
		}
		return strTrim;
	}
	
	public String rigthTrim(Field field, final Map<String, String> sourceMap) {
		String name = field.getName();
		String v = sourceMap.get(name);
		String strTrim=v.trim();
		if(StringUtil.isStringEmpty(strTrim)){
			return strTrim;
		}
		return v.substring(0, v.lastIndexOf(strTrim)+strTrim.length());
	}
	
	public String addYear(Field field, final Map<String, String> sourceMap) {
		String name = field.getName();
		String v = sourceMap.get(name);
		
		return String.valueOf(DateUtil.getYear())+v;
	}
	
	public String addRightYear(Field field, final Map<String, String> sourceMap) throws PeException {
		String name = field.getName();
		String v = sourceMap.get(name).substring(0, 4);
		String result = String.valueOf(DateUtil.getYear()) + v;
		Date oldDate = DateUtil.DateFormat_To_Date(result);
		Date nowDate = DateUtil
				.DateFormat_To_Date(DateUtil.Date_To_DateTimeFormat(new Date(), DateUtil.DATE_FORMATTER3.get()));
		if (oldDate.after(nowDate)) {
			result = String.valueOf(DateUtil.getYear() - 1) + v;
		}
		return result;
	}
	
	public String formatAmt(Field field, final Map<String, String> sourceMap) {
		String name = field.getName();
		String v = sourceMap.get(name);
		int decimal=field.getDecimal();
		String digit="1";
		for(int i=0;i<decimal;i++){
			digit=digit+"0";
		}
		return String.valueOf(Double.parseDouble(v)/(Integer.valueOf(digit)));
	}
	
	public String formatSymbolAmt(Field field, final Map<String, String> sourceMap) {
		String name = field.getName();
		String v = sourceMap.get(name);
		v=v.replace("C", "").replace("D", "").replace(" ", "");
		int decimal=field.getDecimal();
		String digit="1";
		for(int i=0;i<decimal;i++){
			digit=digit+"0";
		}
		return String.valueOf(Double.parseDouble(v)/(Integer.valueOf(digit)));
	}
 
	
	public static void main(String [] args){
		String v="              58 301500586";
	    System.out.println(v.trim()); 
		
	}
	
}
