package com.csii.upp.batch.supportor;

import java.util.Map;

import com.csii.upp.batch.appl.eaccount.ErrorCategory;
import com.csii.upp.batch.xml.format.FileParser;

public class ApplBean {
	//对账文件解析模板路径
	private String checkFileParserPath;
	//对账文件解析模板类
	private FileParser checkFileParser;
	//对账文件本地保存路径 
	@SuppressWarnings("rawtypes")
	private Map checkFileParserLocalPathMap;
	//下发文件组装模板ID
	private String issueFileFormatEAccountFileId;
	//下发文件组装模板ID
	private String issueFileFormatOtherFileId;
	//下发文件组装模板路径
	private String issueFileFormatPath;
	//下发文件生成本地保存路径
	private String issueFileFormatLocalPath;
	//下发文件组装模板类
	private FileParser issueFileFormat;
	//paym生成商户对账文件下发文件模板
	private String merchantFileFormatPaym;
	//paym生成商户积分对账文件下发文件模板 added by wangtao 20161107
	private String merchantPointFileFormatPaym;
	//paym结算文件摸版
	private String settleFileFormatPaym;
	//获取商户对账文件路径
	private String merchantFileFormatLocalPath;
	//paym结算文件本地保存路径
	private String clearbalanceFileFormatLocalPath;
	//fdps对账下发文件本地保存路径
	private String checkFileGenerateLocalPath; 
	//fdps对账下发文件模板
	private String issueFileFormatFdps;
	//商户结算文件路径
	private String merSettleFinalPath;
	//商户结算明细路径
	private String submerSettDetailPath;
	//商户结算交易明细路径
	private String submerTransDetailPath;
	//差错类型归类
	private ErrorCategory errorTypCat;
	
	public ErrorCategory getErrorTypCat() {
		return errorTypCat;
	}


	public void setErrorTypCat(ErrorCategory errorTypCat) {
		this.errorTypCat = errorTypCat;
	}


	public String getSubmerSettDetailPath() {
		return submerSettDetailPath;
	}


	public void setSubmerSettDetailPath(String submerSettDetailPath) {
		this.submerSettDetailPath = submerSettDetailPath;
	}


	public String getSubmerTransDetailPath() {
		return submerTransDetailPath;
	}


	public void setSubmerTransDetailPath(String submerTransDetailPath) {
		this.submerTransDetailPath = submerTransDetailPath;
	}


	public String getMerSettleFinalPath() {
		return merSettleFinalPath;
	}


	public void setMerSettleFinalPath(String merSettleFinalPath) {
		this.merSettleFinalPath = merSettleFinalPath;
	}


	public String getIssueFileFormatFdps() {
		return issueFileFormatFdps;
	}


	public void setIssueFileFormatFdps(String issueFileFormatFdps) {
		this.issueFileFormatFdps = issueFileFormatFdps;
	}


	public String getCheckFileGenerateLocalPath() {
		return checkFileGenerateLocalPath;
	}


	public void setCheckFileGenerateLocalPath(String checkFileGenerateLocalPath) {
		this.checkFileGenerateLocalPath = checkFileGenerateLocalPath;
	}



	public String getClearbalanceFileFormatLocalPath() {
		return clearbalanceFileFormatLocalPath;
	}


	public void setClearbalanceFileFormatLocalPath(String clearbalanceFileFormatLocalPath) {
		this.clearbalanceFileFormatLocalPath = clearbalanceFileFormatLocalPath;
	}


	public String getMerchantFileFormatPaym() {
		return merchantFileFormatPaym;
	}


	public void setMerchantFileFormatPaym(String merchantFileFormatPaym) {
		this.merchantFileFormatPaym = merchantFileFormatPaym;
	}


	public String getMerchantFileFormatLocalPath() {
		return merchantFileFormatLocalPath;
	}


	public void setMerchantFileFormatLocalPath(String merchantFileFormatLocalPath) {
		this.merchantFileFormatLocalPath = merchantFileFormatLocalPath;
	}


	public String getSettleFileFormatPaym() {
		return settleFileFormatPaym;
	}


	public void setSettleFileFormatPaym(String settleFileFormatPaym) {
		this.settleFileFormatPaym = settleFileFormatPaym;
	}

	public String getCheckFileParserPath() {
		return checkFileParserPath;
	}


	public void setCheckFileParserPath(String checkFileParserPath) {
		this.checkFileParserPath = checkFileParserPath;
	}


	public FileParser getCheckFileParser() {
		return checkFileParser;
	}


	public void setCheckFileParser(FileParser checkFileParser) {
		this.checkFileParser = checkFileParser;
	}


	@SuppressWarnings("rawtypes")
	public Map getCheckFileParserLocalPathMap() {
		return checkFileParserLocalPathMap;
	}


	@SuppressWarnings("rawtypes")
	public void setCheckFileParserLocalPathMap(Map checkFileParserLocalPathMap) {
		this.checkFileParserLocalPathMap = checkFileParserLocalPathMap;
	}

	public String getIssueFileFormatEAccountFileId() {
		return issueFileFormatEAccountFileId;
	}


	public void setIssueFileFormatEAccountFileId(
			String issueFileFormatEAccountFileId) {
		this.issueFileFormatEAccountFileId = issueFileFormatEAccountFileId;
	}


	public String getIssueFileFormatOtherFileId() {
		return issueFileFormatOtherFileId;
	}


	public void setIssueFileFormatOtherFileId(String issueFileFormatOtherFileId) {
		this.issueFileFormatOtherFileId = issueFileFormatOtherFileId;
	}


	public String getIssueFileFormatPath() {
		return issueFileFormatPath;
	}


	public void setIssueFileFormatPath(String issueFileFormatPath) {
		this.issueFileFormatPath = issueFileFormatPath;
	}


	public String getIssueFileFormatLocalPath() {
		return issueFileFormatLocalPath;
	}


	public void setIssueFileFormatLocalPath(String issueFileFormatLocalPath) {
		this.issueFileFormatLocalPath = issueFileFormatLocalPath;
	}


	public FileParser getIssueFileFormat() {
		return issueFileFormat;
	}


	public void setIssueFileFormat(FileParser issueFileFormat) {
		this.issueFileFormat = issueFileFormat;
	}
	
	public String getMerchantPointFileFormatPaym() {
		return merchantPointFileFormatPaym;
	}


	public void setMerchantPointFileFormatPaym(String merchantPointFileFormatPaym) {
		this.merchantPointFileFormatPaym = merchantPointFileFormatPaym;
	}	


	public void init() {
		checkFileParser = new FileParser();
		checkFileParser.setXmlFile(this.getCheckFileParserPath());
		issueFileFormat = new FileParser();
		issueFileFormat.setXmlFile(this.getIssueFileFormatPath());
	}

}
