package com.csii.upp.batch.appl.eaccount;

import java.util.List;
import java.util.Map;

import com.csii.upp.batch.appl.base.BaseIssueFileAppl;
import com.csii.upp.constant.ConstEAccount;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dao.extend.SysinfoExtendDAO;
import com.csii.upp.dto.IssueFile;
import com.csii.upp.dto.generate.Sysinfo;

/**
 * 电子账户文件下发
 * 
 * @author 徐锦
 * 
 */
public class IssueFileEAccountAppl extends BaseIssueFileAppl {

	@Override
	protected String getIssueFileFormatFileId() {
		return this.getApplBean().getIssueFileFormatEAccountFileId();
	}

	@Override
	protected Sysinfo getSysInfo() {
		return SysinfoExtendDAO.getSysInfo(FundChannelCode.EACCOUNT);
	}

	@Override
	protected List<IssueFile> getIssueFileList(Map<String, Object> argMaps) {
//		List<IssueFile> issueFiles = new ArrayList<IssueFile>();
//		// 通过通道码和交易日期获得下发文件信息
//		issueFiles.addAll(OveralltransExtendDAO.getEAccountIssueFile(
//				sysInfo.getPrevpostdate(), uppSysNbr));
//		return issueFiles;
		return null;
	}

	@Override
	protected String getFileType() {
		return ConstEAccount.FILETYPE;
	}

	@Override
	protected void insertFileInfo(String fileName, String filePath,Map<String, Object> argMaps) {
		// TODO Auto-generated method stub
		
	}
}
