package com.csii.upp.batch.appl.base;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.batch.supportor.BatchUtil;
import com.csii.upp.batch.xml.format.FileFormat;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.dao.generate.UppersysinfoDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.IssueFile;
import com.csii.upp.dto.generate.Sysinfo;
import com.csii.upp.dto.generate.Uppersysinfo;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.FileHandler;
import com.csii.upp.util.StringUtil;

/**
 * 下发文件应用入口
 * 
 * @author 徐锦
 * 
 */
public abstract class BaseIssueFileAppl extends BaseAppl {
	/**
	 * 获得文件模板ID
	 * 
	 * @return
	 */
	protected abstract String getIssueFileFormatFileId();

	/**
	 * 获得下发系统信息
	 * 
	 * @return
	 */
	protected abstract Sysinfo getSysInfo();

	/**
	 * 获得下发数据
	 * 
	 * @param sysInfo
	 * @param uppSysNbr
	 * @return
	 */
	protected abstract List<IssueFile> getIssueFileList(Map<String, Object> argMaps) throws Exception;
	
	protected abstract String getFileType();
	/**
	 * 保存文件信息
	 * @return
	 */
	protected abstract void insertFileInfo(String fileName,String filePath,Map<String, Object> argMaps) throws Exception;

	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps) throws Exception{
		Sysinfo sysInfo = this.getSysInfo();
		if (sysInfo == null) {
			return;
		}
		FileFormat format = this.getApplBean().getIssueFileFormat()
				.getFileFormat(this.getIssueFileFormatFileId());
		String issueFileLocalPath = this.getApplBean()
				.getIssueFileFormatLocalPath();
		String checkDataFlag = StringUtil.parseObjectToString(argMaps.get(Dict.DZBZ));
		String fileName = checkDataFlag + "_" + format.getPrefix()
				+ DateUtil.Date_To_DateTimeFormat(sysInfo.getPostdate(),
						DateFormatCode.DATE_FORMATTER3) + format.getSuffix();

//		List<Uppersysinfo> upperSysInfos = getUppersysinfo();
//		for (Uppersysinfo upperSysInfo : upperSysInfos) {
//			String uppSysNbr = upperSysInfo.getSystemcode();
//			String localIssueFileName = uppSysNbr
//					+ "_"
//					+ DateUtil.Date_To_DateTimeFormat(DateUtil
//							.getCurrentDateTime(),DateFormatCode.DATETIME_FORMATTER3) + fileName;
			List<IssueFile> issueFiles = this.getIssueFileList(argMaps);
//			if (issueFiles.isEmpty() || issueFiles.size() < 1) {
//				continue;
//			}
			// 生成下发文件
			this.writeIssueFile(issueFiles, format, issueFileLocalPath,
					fileName);
			// 下发文件上传到服务器
//			if (!SftpUtil.uploadFile(localIssueFileName, issueFileLocalPath,
//					FtpConfigFactory.getConfig(this.getFileType()))) {
//				throw new PeRuntimeException(StringUtil.buildString(
//						"upload issue file:", localIssueFileName, " error"));
//			}
			this.insertFileInfo(fileName,issueFileLocalPath,argMaps);
		}
//	}

	/**
	 * 
	 * @param issueFiles
	 * @param format
	 * @param issueFileLocalPath
	 * @param issueFileName
	 */
	private void writeIssueFile(List<IssueFile> issueFiles, FileFormat format,
			String issueFileLocalPath, String issueFileName) {
		FileOutputStream out = null;
		try {
			String encoding = format.getEncoding();
			String lineSeparator = format.getLineSeparator();
			FileHandler.createFile(issueFileLocalPath, issueFileName);
			out = new FileOutputStream(issueFileLocalPath + issueFileName, true);
			for (IssueFile issueFile : issueFiles) {
				String standardString = BatchUtil.getFormatString(this
						.getObjectMapMarshaller().marshall(issueFile), format);
				FileHandler.writeRecorde(standardString + lineSeparator, out,
						encoding);

			}
		} catch (Exception e) {
			throw new PeRuntimeException(e.getMessage());
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
			}
		}
	}

	private List<Uppersysinfo> getUppersysinfo() {
		List<Uppersysinfo> uppersysinfos = null;
		try {
			uppersysinfos = UppersysinfoDAO.selectByExample(null);
		} catch (SQLException e) {
			throw new PeRuntimeException("Get Uppersysinfo Table Failed.");
		}
		if (uppersysinfos.isEmpty()) {
			throw new PeRuntimeException("Uppersysinfo can not be found.");
		}
		return uppersysinfos;
	}

}
