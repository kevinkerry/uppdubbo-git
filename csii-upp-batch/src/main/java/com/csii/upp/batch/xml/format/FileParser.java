/*
 * 2006-3-21 FormatParser.java 
 */
package com.csii.upp.batch.xml.format;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.csii.upp.util.StringUtil;


public class FileParser extends DefaultHandler {
	private static final Log log = LogFactory.getLog(FileParser.class);

	private Stack<Object> stack;

	private String lastTag;

	private FileFormat lastFileFormat;

	private Group lastGroup;

	private Field lastField;

	private String lastValue;

	private Line lastLine;

	private boolean inGroup = false;

	private boolean inLine = false;

	private Map<Object, FileFormat> fileFormats = new LinkedHashMap<Object, FileFormat>();

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (lastValue == null) {
			lastValue = new String(ch, start, length);
		} else {
			lastValue = lastValue + new String(ch, start, length);
		}
	}

	public void endDocument() throws SAXException {
		stack = null;
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		String eName = localName;

		if ("".equals(eName)) {
			eName = qName;
		}

		if (!eName.equalsIgnoreCase((String) (stack.peek()))) {
			throw new SAXException("No End Tag");
		}
		if (TagAttrNames.TAG_FILE.equalsIgnoreCase(eName)) {
			fileFormats.put(lastFileFormat.getId(), lastFileFormat);
			lastFileFormat = null;
			lastValue = null;
		} else if (TagAttrNames.TAG_GROUP.equalsIgnoreCase(eName)) {
			 lastField.addGroup(lastGroup); 
			 lastGroup = null; 
			 lastValue =null; 
			 inGroup = false;
		} else if (TagAttrNames.TAG_LINE.equalsIgnoreCase(eName)) {
			lastFileFormat.addLine(lastLine);
			lastLine = null;
			lastValue = null;
			inLine = false;
		} else if (TagAttrNames.TAG_RULE.equalsIgnoreCase(eName)) {
			lastGroup.setRule(lastValue);
			lastValue = null;
		} else if (TagAttrNames.TAG_FIELD.equalsIgnoreCase(eName)) {
			if (inGroup) {
			}else if (inLine) {
				lastLine.addField(lastField);
			} else {
				lastFileFormat.addField(lastField);
				lastField = null;
			}
			lastValue = null;
		}
		stack.pop();
		lastTag = null;

	}

	public void startDocument() throws SAXException {
		stack = new Stack<Object>();
		stack.push(null);
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		String eName = localName;

		if ("".equals(eName)) {
			eName = qName;
		}

		stack.push(eName);
		lastTag = eName;

		if (TagAttrNames.TAG_FILE.equalsIgnoreCase(lastTag)) {
			lastFileFormat = new FileFormat(attributes
					.getValue(TagAttrNames.ATTR_ID), attributes
					.getValue(TagAttrNames.ATTR_DELIMITER));
			lastFileFormat.setEncoding(attributes
					.getValue(TagAttrNames.ATTR_ENCODING));
			lastFileFormat.setStringSymbol(attributes
					.getValue(TagAttrNames.ATTR_STRINGSYMBOL));
			lastFileFormat.setSkipLine(StringUtil.toInt(attributes
					.getValue(TagAttrNames.ATTR_SKIPLINE)));
			lastFileFormat.setSkipBeginLine(new Boolean(attributes
					.getValue(TagAttrNames.ATTR_SKIPBEGINLINE)).booleanValue());
			lastFileFormat.setSkipEndLine(new Boolean(attributes
					.getValue(TagAttrNames.ATTR_SKIPENDLINE)).booleanValue());
			lastFileFormat.setNoDot(new Boolean(attributes
					.getValue(TagAttrNames.ATTR_NODOT)).booleanValue());
			lastFileFormat.setPrefix(attributes
					.getValue(TagAttrNames.ATTR_PREFIX));
			lastFileFormat.setSuffix(attributes
					.getValue(TagAttrNames.ATTR_SUFFIX));
			lastFileFormat.setLineSeparator(attributes
					.getValue(TagAttrNames.ATTR_LINE_SEPARATOR));
		} else if (TagAttrNames.TAG_GROUP.equalsIgnoreCase(lastTag)) {
			  lastGroup = new Group(attributes.getValue(TagAttrNames.ATTR_NAME));
			  inGroup = true;
			 
		} else if (TagAttrNames.TAG_LINE.equalsIgnoreCase(lastTag)) {
			lastLine = new Line();
			inLine = true;
		} else if (TagAttrNames.TAG_RULE.equalsIgnoreCase(lastTag)) {
		} else if (TagAttrNames.TAG_FIELD.equalsIgnoreCase(lastTag)) {
			Field field = new Field(
					attributes.getValue(TagAttrNames.ATTR_NAME), StringUtil
							.toInt(attributes
									.getValue(TagAttrNames.ATTR_LENGTH)));
			field.setType(Field.parseType(attributes
					.getValue(TagAttrNames.ATTR_TYPE)));
			field.setPattern(attributes.getValue(TagAttrNames.ATTR_PATTERN));
			field.setDecimal(StringUtil.toInt(attributes
					.getValue(TagAttrNames.ATTR_DECIMAL)));
			field.setParser(attributes.getValue(TagAttrNames.ATTR_PARSER));
			field.setFormater(attributes.getValue(TagAttrNames.ATTR_FORMATER));
			field.setFormula(attributes.getValue(TagAttrNames.ATTR_FORMULA));
			field.setDesert(StringUtil.toBoolean(attributes.getValue(TagAttrNames.TAG_DESERT)));
			field.setMandatory(StringUtil.toBoolean(attributes.getValue(TagAttrNames.TAG_MANDATORY)));
			field.setSource(attributes.getValue(TagAttrNames.SOURCE));
			field.setDepend(attributes.getValue(TagAttrNames.DEPEND));
			//field.setAdjustFormat(CsiiObjectUtils.toBoolean(attributes.getValue(TagAttrNames.TAG_ADJUST_FORMAT)));
			field.setDefaultValue(attributes.getValue(TagAttrNames.TAG_DEFAULT_VALUE));
			//lastField = field;
			if (inGroup) {
				lastGroup.addField(field);
			}else{
				lastField=field;
			}
		}

	}

	private synchronized void parse(String xmlFile) {
		try {
			log.info("parsing xml file " + xmlFile);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(Thread.currentThread().getContextClassLoader()
					.getResource(xmlFile).toString(), this);
		} catch (FactoryConfigurationError e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (SAXException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	
	public Map<Object, FileFormat> getFileFormats() {
		return this.fileFormats;
	}

	public FileFormat getFileFormat(String fileId) {
		return (FileFormat) getFileFormats().get(fileId);
	}

	public void setXmlFile(String file) {
		if (file.startsWith(File.separator)) {
			file = file.substring(File.separator.length());
		}
		if (file.startsWith("/")) {
			file = file.substring(1);
		}

		parse(file);
	}
	public static void main(String[] args) {
//		System.out.println(new Date());
//		FileParser fileParser = new FileParser();
//		fileParser.setXmlFile("/META-INF/convert/eaccount.xml");
//		List<Field> list1 = fileParser.getFileFormat("COREPERSON").getFields();
//		List<Field> list2 = fileParser.getFileFormat("PERSONLOAN").getFields();
//		
//		list1.addAll(list2);
//		System.out.println(new Date());
	}
	

}