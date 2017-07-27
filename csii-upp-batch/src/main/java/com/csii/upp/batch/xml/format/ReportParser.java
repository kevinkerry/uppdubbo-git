/*
 * 2005-3-26 ReportParser.java
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

public class ReportParser extends DefaultHandler {
	private static final Log log = LogFactory.getLog(ReportParser.class);
	private String lastTag;
	private String lastValue;
	private Report lastReport;
	private Field lastField;
	private String lastId;
	private Stack<String> stack;

	private Map<String, Report> reports = new LinkedHashMap<String, Report>();

	public void characters(char[] ch, int start, int length) throws SAXException {
		if (lastValue == null) {
			lastValue = new String(ch, start, length);
		} else {
			lastValue = lastValue + new String(ch, start, length);
		}
	}

	public void endDocument() throws SAXException {
		stack = null;
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		String eName = localName;

		if ("".equals(eName)) {
			eName = qName;
		}

		if (!eName.equalsIgnoreCase((String) (stack.peek()))) {
			throw new SAXException("No End Tag");
		}
		if (TagAttrNames.TAG_REPORTS.equalsIgnoreCase(eName)) {
		} else if (TagAttrNames.TAG_REPORT.equalsIgnoreCase(eName)) {
			reports.put(lastReport.getId(), lastReport);
			lastReport = null;
			lastValue = null;
			lastId = null;
		} else if (TagAttrNames.TAG_DESCRIPTION.equalsIgnoreCase(eName)) {
			lastReport.setDescription(lastValue);
			lastValue = null;
			lastId = null;
		} else if (TagAttrNames.TAG_SQL.equalsIgnoreCase(eName)) {
			lastReport.addSql(lastId, lastValue);
			lastId = null;
			lastValue = null;
		} else if (TagAttrNames.TAG_FIELD.equalsIgnoreCase(eName)) {
			lastReport.addField(lastField);
			lastField = null;
			lastValue = null;
			lastId = null;
		}

		stack.pop();
		lastTag = null;
	}

	public void startDocument() throws SAXException {
		stack = new Stack<String>();
		stack.push(null);
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes)
		throws SAXException {
		String eName = localName;

		if ("".equals(eName)) {
			eName = qName;
		}

		stack.push(eName);
		lastTag = eName;
		if (TagAttrNames.TAG_REPORTS.equalsIgnoreCase(lastTag)) {

		} else if (TagAttrNames.TAG_DESCRIPTION.equalsIgnoreCase(lastTag)) {
		} else if (TagAttrNames.TAG_REPORT.equalsIgnoreCase(lastTag)) {
			lastReport =
				new Report(
					attributes.getValue(TagAttrNames.ATTR_ID),
					Report.parsePeriod(attributes.getValue(TagAttrNames.ATTR_PERIOD)),
					Report.parseLevel(attributes.getValue(TagAttrNames.ATTR_LEVEL)));
			lastReport.setBranchFieldName(attributes.getValue(TagAttrNames.ATTR_BRANCHFIELD));
			lastReport.setOrgFieldName(attributes.getValue(TagAttrNames.ATTR_ORGFIELD));
			lastReport.setCurrency(attributes.getValue(TagAttrNames.ATTR_CURRENCY));
			lastReport.setDataFormatCode(attributes.getValue(TagAttrNames.ATTR_DATA_FORMAT_CODE));
			lastReport.setCollection(
				Boolean.valueOf(attributes.getValue(TagAttrNames.ATTR_COLLECTION)).booleanValue());
		} else if (TagAttrNames.TAG_SQL.equalsIgnoreCase(lastTag)) {
			lastId = attributes.getValue(TagAttrNames.ATTR_ID);
			lastValue = null;
		} else if (TagAttrNames.TAG_FIELD.equalsIgnoreCase(lastTag)) {
			lastField = new Field(attributes.getValue(TagAttrNames.ATTR_NAME));
			lastField.setLength(Integer.parseInt(attributes.getValue(TagAttrNames.ATTR_LENGTH)));
			int decimal = 0;
			try {
				decimal = Integer.parseInt(attributes.getValue(TagAttrNames.ATTR_DECIMAL));
			} catch (Throwable t) {
			}
			lastField.setDecimal(decimal);
			lastField.setType(Field.parseType(attributes.getValue(TagAttrNames.ATTR_TYPE)));
			lastField.setFormater(attributes.getValue(TagAttrNames.ATTR_FORMATER));
			lastField.setPattern(attributes.getValue(TagAttrNames.ATTR_PATTERN));
			lastField.setFormula(attributes.getValue(TagAttrNames.ATTR_FORMULA));
		}
	}

	public Map<String, Report> getReports() {
		return this.reports;
	}

	public Report getReport(String reportId) {
		return (Report) getReports().get(reportId);
	}

	private synchronized void parse(String xmlFile) {
		try {
			log.info("parsing xml file " + xmlFile);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(
				Thread.currentThread().getContextClassLoader().getResource(xmlFile).toString(),
				this);
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

//		ReportParser handler = new ReportParser();
//		handler.setXmlFile("config/report.xml");
//		for (Iterator it = handler.getReports().values().iterator(); it.hasNext();) {
//			System.out.println(it.next());
//		}

	}

}
