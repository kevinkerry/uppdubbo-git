/*
 * Copyright 2005-2016 Client Service International, Inc. All rights reserved.
 * CSII PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * project: csii-upp-dto
 * create: 2016年2月16日 下午7:24:57
 * vc: $Id: $
 */
package com.csii.upp.dto.router.core;

import java.util.Date;

import com.csii.upp.constant.CoreTransCode;
import com.csii.upp.dto.extend.InputFundData;

/**
 * TODO 请填写注释.
 * @author chen chao 
 * @version $Revision:$
 */
public class ReqPaymIssueFile extends ReqCoreHead {

	/**
	 * @param data
	 */
	public ReqPaymIssueFile(InputFundData data) {
		super(data);
		this.setTransCode(CoreTransCode.SENDPAYMISSUEFILE);
//		this.setHostTransactionCode(CoreTransCode.SENDPAYMISSUEFILE);
		this.setFilename(data.getFilename());
		this.setIP(data.getIP());
		this.setPort(data.getPort());
		this.setUserName(data.getUserName());
		this.setPassword(data.getPassword());
		this.setRemotePath(data.getRemotePath());
		this.setClearDate(data.getCleardate());
		this.setFileaccepter(data.getFileaccepter());
		this.setReceiver(data.getFileaccepter());
		this.setLocalPath(data.getLocalPath());
	}
	private String filename;
	private String IP;
	private String Port;
	private String UserName;
	private String Password;
	private String RemotePath;
	private Date clearDate;
	private String fileaccepter;//报文接收方
	private String LocalPath;
	private String hostTransactionCode;//506801 通知前置给商户发送对账文件和结算文件
	
	public String getLocalPath() {
		return LocalPath;
	}
	public void setLocalPath(String localPath) {
		LocalPath = localPath;
	}
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	/**
	 * @return the iP
	 */
	public String getIP() {
		return IP;
	}
	/**
	 * @param iP the iP to set
	 */
	public void setIP(String iP) {
		IP = iP;
	}
	/**
	 * @return the port
	 */
	public String getPort() {
		return Port;
	}
	/**
	 * @param port the port to set
	 */
	public void setPort(String port) {
		Port = port;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return UserName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		UserName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return Password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		Password = password;
	}
	/**
	 * @return the remotePath
	 */
	public String getRemotePath() {
		return RemotePath;
	}
	/**
	 * @param remotePath the remotePath to set
	 */
	public void setRemotePath(String remotePath) {
		RemotePath = remotePath;
	}
	/**
	 * @return the clearDate
	 */
	
	/**
	 * @return the fileaccepter
	 */
	public String getFileaccepter() {
		return fileaccepter;
	}
	public Date getClearDate() {
		return clearDate;
	}
	public void setClearDate(Date clearDate) {
		this.clearDate = clearDate;
	}
	/**
	 * @param fileaccepter the fileaccepter to set
	 */
	public void setFileaccepter(String fileaccepter) {
		this.fileaccepter = fileaccepter;
	}
	/**
	 * @return the hostTransactionCode
	 */
	public String getHostTransactionCode() {
		return hostTransactionCode;
	}
	/**
	 * @param hostTransactionCode the hostTransactionCode to set
	 */
	public void setHostTransactionCode(String hostTransactionCode) {
		this.hostTransactionCode = hostTransactionCode;
	}
	
	

}
