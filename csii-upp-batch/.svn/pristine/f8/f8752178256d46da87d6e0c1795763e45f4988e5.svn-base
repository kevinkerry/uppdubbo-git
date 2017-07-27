/*
 * 2006-3-22 Field.java
 */
package com.csii.upp.batch.xml.format;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Field implements Serializable {
	public static final int TYPE_STRING = 0;
	public static final int TYPE_AMOUNT = 1;
	public static final int TYPE_DATE = 2;
	public static final int TYPE_DATETIME = 3;
	public static final int TYPE_NUMBER = 4;
	public static final int TYPE_GROUP = 5;
	public static final int TYPE_TIME=6;
	private List<Group> groups = new ArrayList<Group>();
	
	private static final long serialVersionUID = -84206172814831820L;
	public static final String[] TYPE_STRINGS =
		new String[] { "string", "amount", "date", "datetime", "number","group" };

	private String name;
	private int length;
	private int decimal;
	private int type;
	private String formater;
	private String parser;
	private String formula;//compute formular
	private String pattern;//format pattern 
    private boolean isDesert;//
    private String defaultValue;//deault value
    private String source;
    private String depend;
    private boolean isMandatory;//
    
    public String getDepend() {
		return depend;
	}
	public void setDepend(String depend) {
		this.depend = depend;
	}
	public String getSource(){
    	return source;
    }
    public void setSource(String o){
    	this.source=o;
    }
    
	
	
	public boolean isDesert() {
		return isDesert;
	}
	
	public void setDesert(boolean isDesert) {
		this.isDesert = isDesert;
	}
	
	public boolean isMandatory() {
		return isMandatory;
	}
	
	public void setMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}
	
	public Field(String name) {
		this.name = name;
	}

	public Field(String name, int length) {
		this.name = name;
		this.length = length;
	}
	public void addGroup(Group group) {
		groups.add(group);
	}

	public List<Group> getGroups() {
		return groups;
	}

	public static int parseType(String type) {
		for (int i = 0; i < TYPE_STRINGS.length; i++) {
			if (TYPE_STRINGS[i].equalsIgnoreCase(type)) {
				return i;
			}
		}

		return TYPE_STRING;
	}

	public String typeString() {
		return TYPE_STRINGS[type];
	}

	public int getDecimal() {
		return decimal;
	}

	public String getFormater() {
		return formater;
	}

	public int getLength() {
		return length;
	}

	public String getName() {
		return name;
	}

	public int getType() {
		return type;
	}

	public void setDecimal(int i) {
		if (i < 0) {
			throw new IllegalArgumentException("decimal length must be more than 0.");
		}
		decimal = i;
	}

	public void setFormater(String string) {
		formater = string;
	}

	public void setLength(int i) {
		if (i < 0) {
			throw new IllegalArgumentException("length must be more than 0.");
		}
		length = i;
	}

	public void setType(int i) {
		if (i < 0 || i >= TYPE_STRINGS.length) {
			throw new IllegalArgumentException("Type must be 0-" + (TYPE_STRINGS.length - 1) + ".");
		}
		type = i;
	}

	public String getParser() {
		return parser;
	}

	public void setParser(String string) {
		parser = string;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String string) {
		formula = string;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String string) {
		pattern = string;
	}

	public boolean isString() {
		return type == TYPE_STRING;
	}
	public boolean isNumber() {
		return type == TYPE_NUMBER;
	}
	public boolean isAmount() {
		return type == TYPE_AMOUNT;
	}
	public boolean isDate() {
		return type == TYPE_DATE;
	}
	public boolean isDatetime() {
		return type == TYPE_DATETIME;
	}

	public boolean hasFormula() {
		return formula != null && formula.trim().length() > 0;
	}

	public boolean hasFormater() {
		return formater != null && formater.trim().length() > 0;
	}

	public boolean hasParser() {
		return parser != null && parser.trim().length() > 0;
	}

	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append("<field ");
		buf.append("name=").append(name).append(",");
		buf.append("length=").append(length).append(",");
		buf.append("decimal=").append(decimal).append(",");
		buf.append("type=").append(TYPE_STRINGS[type]).append(",");
		buf.append("pattern=").append(pattern).append(",");
		buf.append("formater=").append(formater).append(",");
		buf.append("parser=").append(parser).append(",");
		buf.append("formula=").append(formula).append(",");;
		buf.append("source=").append(this.source).append(",");
		buf.append("depend=").append(this.depend).append(",");
		buf.append("defaultValue=").append(this.defaultValue).append(",");
		buf.append("isMandatory=").append(this.isMandatory).append(",");
		buf.append("isDesert=").append(this.isDesert);
		buf.append("/>");
		return buf.toString();
	}

	
	public String getDefaultValue() {
		return defaultValue;
	}
	
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
}
