package com.csii.upp.batch.appl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.csii.upp.batch.appl.base.BaseIssueFileAppl;
import com.csii.upp.constant.ConstCore;
import com.csii.upp.dao.extend.SysinfoExtendDAO;
import com.csii.upp.dto.IssueFile;
import com.csii.upp.dto.generate.Sysinfo;
import com.csii.upp.util.DateUtil;

/**
 * 除电子账户外其他文件下发
 * 
 * @author 徐锦
 * 
 */
public class IssueFileOtherAppl extends BaseIssueFileAppl {

	@Override
	protected String getIssueFileFormatFileId() {
		return this.getApplBean().getIssueFileFormatOtherFileId();
	}

	@Override
	protected Sysinfo getSysInfo() {
		Date rtxnDate = SysinfoExtendDAO.getSysInfo().getPostdate();
		// 获得最小对账日期记录
		Sysinfo sysInfo = SysinfoExtendDAO.getMinSysInfo();
		if(sysInfo==null){
			return null;
		}
		Date minCheckDate = sysInfo.getPostdate();
		// 交易日期－最小对账日期大于2就不下发直接返回
		if (rtxnDate.after(DateUtil.addDate(minCheckDate, 2))) {
			return null;
		}
		return sysInfo;
	}

	@Override
	protected List<IssueFile> getIssueFileList(Map<String, Object> argMaps) {
//		Date prevCheckDate = sysInfo.getPrevpostdate();
//		Date minCheckDate = sysInfo.getPostdate();
//		List<IssueFile> issueFiles = new ArrayList<IssueFile>();
//		// 通过通道码和交易日期获得下发文件信息
//		while (prevCheckDate.before(minCheckDate)) {
//			issueFiles.addAll(OveralltransExtendDAO.getOtherIssueFile(
//					prevCheckDate, uppSysNbr));
//			prevCheckDate = DateUtil.addDate(prevCheckDate, 1);
//		}
//		return issueFiles;
		return null;
	}

	@Override
	protected String getFileType() {
		return ConstCore.FILETYPE;
	}

	@Override
	protected void insertFileInfo(String fileName, String filePath,Map<String, Object> argMaps) {
		// TODO Auto-generated method stub
		
	}
}
