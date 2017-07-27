/*
 *  2005-3-23 Report.java
 */
package com.csii.upp.batch.xml.format;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Report implements Serializable {

	public String getDataFormatCode() {
		return this.dataFormatCode;
	}
	public static final int PERIOD_DAY = 0;
	public static final int PERIOD_MONTH = 1;
	public static final int PERIOD_QUARTER = 2;
	public static final int PERIOD_HALFYEAR = 3;
	public static final int PERIOD_YEAR = 4;
	public static final int LEVEL_GENERAL = 0;
	public static final int LEVEL_BRANCH = 1;
	public static final int LEVEL_ORG = 2;
	public static final int LEVEL_HQ = 3;

	protected static final String CUR_ALL = "999";
	protected static final String CUR_NONE = "000";
	protected static final String CUR_USD = "AAA";
	protected static final String CUR_RMB = "BBB";
	protected static final String CUR_BEN = "CCC";
	
	public static final String SQL_GET_DATA="getData";
	public static final String SQL_GET_COLLECTION="getCollection";
	public static final String SQL_GET_COLLECTION_SUM="getCollectionSum";
	public static final String SQL_GET_ORG="getOrg";
	public static final String SQL_GET_SUM="getSum";

	private String id;
	private int period;
	private int level;//���?��
//
//	private String sql;
	private String branchFieldName;
	private String orgFieldName;
	private String currency;
	private String description;
	private String dataFormatCode;
	/**
	 * @param dataFormatCode Ҫ���õ� dataFormatCode��
	 */
	public void setDataFormatCode(String dataFormatCode) {
		this.dataFormatCode = dataFormatCode;
	}
	private List<Field> fields = new ArrayList<Field>();
	private Map<String, String> sqls = new LinkedHashMap<String, String>();
	private boolean collection;
	private static final long serialVersionUID = 5941142562103836110L;
	public Report(String id, int period, int level) {
		this.id = id;
		this.period = period;
		this.level = level;
	}

	public void addField(Field field) {
		fields.add(field);
	}

	public void addSql(String id, String sql) {
		sqls.put(id, sql);
	}

	public List<Field> getFields() {
		return this.fields;
	}

	public String getSql(String id) {
		return (String)sqls.get(id);
	}

	public static int parsePeriod(String period) {
		if ("day".equalsIgnoreCase(period))
			return PERIOD_DAY;
		if ("month".equalsIgnoreCase(period))
			return PERIOD_MONTH;
		if ("quarter".equalsIgnoreCase(period))
			return PERIOD_QUARTER;
		if ("halfyear".equalsIgnoreCase(period))
			return PERIOD_HALFYEAR;
		if ("year".equalsIgnoreCase(period))
			return PERIOD_YEAR;
		return PERIOD_DAY;

	}

	public static int parseLevel(String level) {
		if ("general".equalsIgnoreCase(level))
			return LEVEL_GENERAL;
		if ("headBranch".equalsIgnoreCase(level))
			return LEVEL_HQ;
		if ("branch".equalsIgnoreCase(level))
			return LEVEL_BRANCH;
		if ("org".equalsIgnoreCase(level))
			return LEVEL_ORG;
		return LEVEL_BRANCH;
	}
	/**
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @return
	 */
	public int getPeriod() {
		return period;
	}

	/**
	 * @return
	 */
	public String getSql() {
		String id = (String)sqls.keySet().iterator().next();
		return (String)sqls.get(id);
	}

	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append("report(" + description + "){");
		buf.append("id=").append(id).append(",");
		buf.append("period=").append(period).append(",");
		buf.append("level=").append(level).append(",");
		buf.append("collection=").append(collection).append(",");
		buf.append("branchFieldName=").append(branchFieldName).append(",");
		buf.append("orgFieldName=").append(orgFieldName).append(",");
		buf.append("currency=").append(currency).append("\n");
		buf.append("dataFormatCode=").append(this.dataFormatCode).append("\n");
		for (Iterator<String> it = sqls.keySet().iterator(); it.hasNext();) {
			String id = (String)it.next();
			String sql = (String)sqls.get(id);
			buf.append("\tsql(").append(id).append(")=").append(sql).append("\n");
		}

		for (Iterator<Field> it = fields.iterator(); it.hasNext();) {
			buf.append("\t");
			buf.append(it.next());
			buf.append("\n");
		}
		buf.append("}");
		return buf.toString();
	}

	/**
	 * @return
	 */
	public String getBranchFieldName() {
		return branchFieldName;
	}

	/**
	 * @return
	 */
	public String getOrgFieldName() {
		return orgFieldName;
	}

	/**
	 * @param string
	 */
	public void setBranchFieldName(String string) {
		branchFieldName = string;
	}

	/**
	 * @param string
	 */
	public void setOrgFieldName(String string) {
		orgFieldName = string;
	}

	/**
	 * @return
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param string
	 */
	public void setCurrency(String string) {
		if (string == null || string.trim().length() == 0) {
			string = CUR_NONE;
		}
		currency = string;
	}

	/**
	 * �Ƿ��ȫ�л��ܱ�
	 * @return
	 */
	public boolean isCollection() {
		return collection;
	}

	/**
	 * @param b
	 */
	public void setCollection(boolean b) {
		collection = b;
	}

	/**
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param string
	 */
	public void setDescription(String string) {
		description = string;
	}

}
