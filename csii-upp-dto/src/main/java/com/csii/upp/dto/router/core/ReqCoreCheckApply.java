/**
 * 
 */
package com.csii.upp.dto.router.core;

import com.csii.upp.constant.CoreTransCode;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.DateUtil;

/**
 * @author chen chao 
 *
 */
public class ReqCoreCheckApply extends ReqCoreHead {
	private String clearDate;
	private String channelId;
	private String checkFileType;
	private String bwracncd;
	private String bwrabake;
	private String checkapplynbr;
	private String CheckDataFlag;
	
	



	public String getCheckDataFlag() {
		return CheckDataFlag;
	}


	public void setCheckDataFlag(String checkDataFlag) {
		CheckDataFlag = checkDataFlag;
	}


	public String getCheckapplynbr() {
		return checkapplynbr;
	}


	public void setCheckapplynbr(String checkapplynbr) {
		this.checkapplynbr = checkapplynbr;
	}


	/**
	 * @return the clearDate
	 */
	public String getClearDate() {
		return clearDate;
	}


	/**
	 * @param clearDate the clearDate to set
	 */
	public void setClearDate(String clearDate) {
		this.clearDate = clearDate;
	}


	/**
	 * @return the channelId
	 */
	public String getChannelId() {
		return channelId;
	}


	/**
	 * @param channelId the channelId to set
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}


	/**
	 * @return the checkFileType
	 */
	public String getCheckFileType() {
		return checkFileType;
	}


	/**
	 * @param checkFileType the checkFileType to set
	 */
	public void setCheckFileType(String checkFileType) {
		this.checkFileType = checkFileType;
	}


	/**
	 * @return the bwracncd
	 */
	public String getBwracncd() {
		return bwracncd;
	}


	/**
	 * @param bwracncd the bwracncd to set
	 */
	public void setBwracncd(String bwracncd) {
		this.bwracncd = bwracncd;
	}


	/**
	 * @return the bwrabake
	 */
	public String getBwrabake() {
		return bwrabake;
	}


	/**
	 * @param bwrabake the bwrabake to set
	 */
	public void setBwrabake(String bwrabake) {
		this.bwrabake = bwrabake;
	}
	

	public ReqCoreCheckApply(InputFundData data) {
		super(data);
		//this.setTransDate(DateUtil.DateTimeFormat_To_Date(data.getTransdate(), DateFormatCode.DATE_FORMATTER1));
		this.setCheckapplynbr(data.getTransnbr());
		this.setClearDate(DateUtil.Date_To_DateTimeFormat(data.getTransdate(), DateFormatCode.DATE_FORMATTER3));
		this.setTransCode(CoreTransCode.APPLYFILE);
		this.setCheckDataFlag(data.getCheckdataflag());
	}
}
