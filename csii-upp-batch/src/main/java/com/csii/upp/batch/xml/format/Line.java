/*
 * 2005-2-2 Group.java 
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
public class Line implements Serializable {
	private List<Field> fields = new ArrayList<Field>();
	private static final long serialVersionUID = 9119648827282373995L;
	public void addField(Field field) {
		fields.add(field);
	}

	public List<Field> getFields() {
		return fields;
	}

	public int getLength() {
		int len = 0;
		for (Iterator<Field> it = fields.iterator(); it.hasNext();) {
			Field field = (Field) it.next();
			len += field.getLength();
		}
		return len;
	}

	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append("<line>").append("\n");
		for (Iterator<Field> it2 = fields.iterator(); it2.hasNext();) {
			buf.append("\t\t").append(it2.next()).append("\n");
		}
		buf.append("</line>");
		return buf.toString();
	}

}