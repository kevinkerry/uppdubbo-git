/*
 *  2005-2-2 Group.java 
 */
package com.csii.upp.batch.xml.format;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Li Guo Qing
 * @version 1.0
 */
public class Group  implements Serializable{
	private String name;
	private int length;
	private String rule;
	private List<Field> fields = new ArrayList<Field>();
	private static final long serialVersionUID = -623439504608360071L;
	public Group(String name) {
		this.name = name;
	}

	public void addField(Field field) {
		fields.add(field);
	}

	/**
	 * @return
	 */
	public List<Field> getFields() {
		return fields;
	}

	/**
	 * @return
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public String getRule() {
		return rule;
	}

	/**
	 * @param i
	 */
	public void setLength(int i) {
		length = i;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * @param string
	 */
	public void setRule(String string) {
		rule = string;
	}

	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append("group[");
		buf.append("name=").append(name).append(",");
		buf.append("length=").append(length).append("\n");
		buf.append("rule=").append(rule).append("\n");
		for (Iterator<Field> it = fields.iterator(); it.hasNext(); buf.append(it.next()).append("\n"));
		buf.append("]");
		return buf.toString();
	}

}
