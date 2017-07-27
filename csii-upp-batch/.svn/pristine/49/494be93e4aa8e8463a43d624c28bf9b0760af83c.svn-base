/*
 *  2005-1-18 Format.java 
 */
package com.csii.upp.batch.xml.format;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.csii.upp.util.StringUtil;





/**
 * 
 * 
 * @author Li Guo Qing
 * @version 1.0
 */
public class FileFormat implements Serializable {
	private static final long serialVersionUID = 748819326087561555L;

	private static String[] ESCAPE1 = new String[] { "\\n", "\\t", "\\r" };

	private static String[] ESCAPE2 = new String[] { "\n", "\t", "\r" };

	private String id;

	private String delimiter="";

	private String encoding="GBK"; 

	private List<Field> fields = new ArrayList<Field>();

	private String stringSymbol; 

	private int skipLine; 

	private boolean noDot=false;
	
	private boolean skipBeginLine=false;
	
	private boolean skipEndLine=false;

	private String prefix; 

	private String suffix; 

	private boolean branch;

	private String lineSeparator;

	private List<Line> lines = new ArrayList<Line>();

	public FileFormat(String id, String delimiter) {
		this.id = id;
		this.delimiter = delimiter;
	}

	public void addField(Field field) {
		fields.add(field);
	}

	public List<Field> getFields() {
		return fields;
	}

	
	public int getLength() {
		int len = 0;
		if(fields != null && !fields.isEmpty()){
			for(Field field:fields){
				len += field.getLength();
			}
		}
		return len;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public boolean hasDelimiter() {
		return delimiter != null && delimiter.length() > 0;
	}

	public String getId() {
		return id;
	}

	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append("<file ");
		buf.append("id=").append(id).append(",");
		buf.append("encoding=").append(encoding).append(",");
		buf.append("stringSymbol=").append(stringSymbol).append(",");
		buf.append("skipLine=").append(skipLine).append(",");
		buf.append("noDot=").append(noDot).append(",");
		buf.append("skipBeginLine=").append(skipBeginLine).append(",");
		buf.append("skipEndLine=").append(skipEndLine).append(",");
		buf.append("prefix=").append(prefix).append(",");
		buf.append("suffix=").append(suffix).append(",");
		buf.append("branch=").append(branch).append(",");
		String str = lineSeparator;
		if (str != null) {
			for (int i = 0; i < ESCAPE1.length; i++) {
				str = StringUtil.replace(str, ESCAPE2[i], ESCAPE1[i]);
			}
		}
		buf.append("lineSeparator=").append(str).append(",");
		buf.append("delimiter=").append(delimiter).append("/>\n");
		for (Iterator<Line> it = lines.iterator(); it.hasNext();) {			
			buf.append("\t").append(it.next()).append("\n");			
		}
		for (Iterator<Field> it = fields.iterator(); it.hasNext();) {
			buf.append("\t").append(it.next()).append("\n");
		}
		buf.append("</file>");
		return buf.toString();
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String string) {
		encoding = string;
	}

	public boolean hasEncoding() {
		return !StringUtil.isStringEmpty(encoding);
	}

	public String getStringSymbol() {
		return stringSymbol;
	}

	public void setStringSymbol(String string) {
		stringSymbol = string;
	}

	public boolean hasStringSymbol() {
		return stringSymbol != null && stringSymbol.length() > 0;
	}

	public int getSkipLine() {
		return skipLine;
	}

	public void setSkipLine(int i) {
		if (i < 0) {
			throw new IllegalArgumentException("skip lines must be more than 0");
		}
		skipLine = i;
	}

	public boolean isNoDot() {
		return noDot;
	}

	public void setNoDot(boolean b) {
		noDot = b;
	}

	public boolean isSkipBeginLine() {
		return skipBeginLine;
	}

	public void setSkipBeginLine(boolean skipBeginLine) {
		this.skipBeginLine = skipBeginLine;
	}

	public boolean isSkipEndLine() {
		return skipEndLine;
	}

	public void setSkipEndLine(boolean skipEndLine) {
		this.skipEndLine = skipEndLine;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setPrefix(String string) {
		prefix = string;
	}

	public void setSuffix(String string) {
		suffix = string;
	}

	public boolean isBranch() {
		return branch;
	}

	public void setBranch(boolean branch) {
		this.branch = branch;
	}

	public String getLineSeparator() {
		return lineSeparator;
	}

	public void setLineSeparator(String lineSeparator) {
		if (lineSeparator != null) {
			for (int i = 0; i < ESCAPE1.length; i++) {
				lineSeparator = StringUtil.replace(lineSeparator, ESCAPE1[i],
						ESCAPE2[i]);
			}
		}
		this.lineSeparator = lineSeparator;
	}

	public boolean hasLineSeparator() {
		return lineSeparator != null && lineSeparator.length() > 0;
	}

	public List<Line> getLines() {
		return lines;
	}

	public void addLine(Line line) {
		lines.add(line);
	}
	/**
	 * @param string
	 */
	public void setDelimiter(String string) {
		delimiter = string;
	}

}