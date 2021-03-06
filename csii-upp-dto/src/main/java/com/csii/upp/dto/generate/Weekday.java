package com.csii.upp.dto.generate;

import java.util.Date;

public class Weekday {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column WEEKDAY.WEEKDAYCD
	 * @mbggenerated
	 */
	private String weekdaycd;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column WEEKDAY.WEEKDAYNAME
	 * @mbggenerated
	 */
	private String weekdayname;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column WEEKDAY.SEQ
	 * @mbggenerated
	 */
	private Long seq;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column WEEKDAY.FEDBUSNDAYYN
	 * @mbggenerated
	 */
	private String fedbusndayyn;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column WEEKDAY.DATELASTMAINT
	 * @mbggenerated
	 */
	private Date datelastmaint;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column WEEKDAY.WEEKDAYCD
	 * @return  the value of WEEKDAY.WEEKDAYCD
	 * @mbggenerated
	 */
	public String getWeekdaycd() {
		return weekdaycd;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column WEEKDAY.WEEKDAYCD
	 * @param weekdaycd  the value for WEEKDAY.WEEKDAYCD
	 * @mbggenerated
	 */
	public void setWeekdaycd(String weekdaycd) {
		this.weekdaycd = weekdaycd;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column WEEKDAY.WEEKDAYNAME
	 * @return  the value of WEEKDAY.WEEKDAYNAME
	 * @mbggenerated
	 */
	public String getWeekdayname() {
		return weekdayname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column WEEKDAY.WEEKDAYNAME
	 * @param weekdayname  the value for WEEKDAY.WEEKDAYNAME
	 * @mbggenerated
	 */
	public void setWeekdayname(String weekdayname) {
		this.weekdayname = weekdayname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column WEEKDAY.SEQ
	 * @return  the value of WEEKDAY.SEQ
	 * @mbggenerated
	 */
	public Long getSeq() {
		return seq;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column WEEKDAY.SEQ
	 * @param seq  the value for WEEKDAY.SEQ
	 * @mbggenerated
	 */
	public void setSeq(Long seq) {
		this.seq = seq;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column WEEKDAY.FEDBUSNDAYYN
	 * @return  the value of WEEKDAY.FEDBUSNDAYYN
	 * @mbggenerated
	 */
	public String getFedbusndayyn() {
		return fedbusndayyn;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column WEEKDAY.FEDBUSNDAYYN
	 * @param fedbusndayyn  the value for WEEKDAY.FEDBUSNDAYYN
	 * @mbggenerated
	 */
	public void setFedbusndayyn(String fedbusndayyn) {
		this.fedbusndayyn = fedbusndayyn;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column WEEKDAY.DATELASTMAINT
	 * @return  the value of WEEKDAY.DATELASTMAINT
	 * @mbggenerated
	 */
	public Date getDatelastmaint() {
		return datelastmaint;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column WEEKDAY.DATELASTMAINT
	 * @param datelastmaint  the value for WEEKDAY.DATELASTMAINT
	 * @mbggenerated
	 */
	protected void setDatelastmaint(Date datelastmaint) {
		this.datelastmaint = datelastmaint;
	}
}