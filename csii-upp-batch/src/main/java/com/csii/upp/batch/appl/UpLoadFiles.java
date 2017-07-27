package com.csii.upp.batch.appl;

import java.util.List;
import java.util.Map;

import com.csii.upp.batch.appl.base.BaseAppl;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.MerPlatformNbr;
import com.csii.upp.constant.UploadFlag;
import com.csii.upp.dao.generate.MerplatformsettingDAO;
import com.csii.upp.dao.generate.OnlinefileinfoDAO;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Merplatformsetting;
import com.csii.upp.dto.generate.Onlinefileinfo;
import com.csii.upp.dto.generate.OnlinefileinfoExample;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.service.fundprocess.CoreService;
import com.csii.upp.util.FtpConfig;
import com.csii.upp.util.SftpUtil;
import com.csii.upp.util.StringUtil;

public class UpLoadFiles extends BaseAppl {

	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps) throws Exception {
		//获取对账日生成文件信息
		OnlinefileinfoExample ofExample = new OnlinefileinfoExample();
		ofExample.createCriteria().andUploadflagEqualTo(UploadFlag.Int);
		List<Onlinefileinfo> onlineList = OnlinefileinfoDAO.selectByExample(ofExample);
		String flag = null;
		Onlinefileinfo onfile = new Onlinefileinfo();
		//推送文件
		for (Onlinefileinfo onlinefileinfo : onlineList) {
			if (MerPlatformNbr.FSEG.equals(onlinefileinfo.getFilememo()) || MerPlatformNbr.FSJ.equals(onlinefileinfo.getFilememo())) {
				InputFundData data = new InputFundData();
				Merplatformsetting merplat = MerplatformsettingDAO.selectByPrimaryKey(onlinefileinfo.getFilememo());
				data.setFilename(onlinefileinfo.getFilename());
				data.setIP(merplat.getFtpip());
				data.setPort(merplat.getFtpport());
				data.setUserName(merplat.getFtpusername());
				data.setPassword(merplat.getFtppassword());
				data.setRemotePath(merplat.getFileurl());
				data.setCleardate(onlinefileinfo.getCleardate());
				data.setLocalPath(onlinefileinfo.getFilepath());
				if (MerPlatformNbr.FSEG.equals(onlinefileinfo.getFilememo())) {
					data.setFileaccepter("1309");
				}else if (MerPlatformNbr.FSJ.equals(onlinefileinfo.getFilememo())) {
					data.setFileaccepter("1310");
				}
				CoreService coreService = (CoreService) this.getRouterService(FundChannelCode.CORE.toLowerCase());
				RespSysHead output  = coreService.sendPaymIssueFile(data);
				if (output.isSuccess()) {
					onfile.setFilenbr(onlinefileinfo.getFilenbr());
					onfile.setUploadflag(UploadFlag.Success);
					onfile.setBatchnbr(onlinefileinfo.getBatchnbr());
					OnlinefileinfoDAO.updateByPrimaryKeySelective(onfile);
				}
			}else {
				flag = this.uploadFile(onlinefileinfo.getFilename(), onlinefileinfo.getFilepath(), onlinefileinfo.getFilememo());
				if (flag == null) {
					onfile.setFilenbr(onlinefileinfo.getFilenbr());
					onfile.setUploadflag(UploadFlag.Success);
					onfile.setBatchnbr(onlinefileinfo.getBatchnbr());
					OnlinefileinfoDAO.updateByPrimaryKeySelective(onfile);
				} else 
				{
					onfile.setFilenbr(onlinefileinfo.getFilenbr());
					onfile.setUploadflag(flag);
					onfile.setBatchnbr(onlinefileinfo.getBatchnbr());
					OnlinefileinfoDAO.updateByPrimaryKeySelective(onfile);
				}
			}
		}
	}
	/**
	 * 文件推送方法
	 * @param localIssueFileName
	 * @param issueFileLocalPath
	 * @param merPlatformNbr
	 */
	private String uploadFile(String localIssueFileName,String issueFileLocalPath,String merPlatformNbr) throws Exception{
		// 下发文件上传到服务器
		String flag = null;
		FtpConfig config=new FtpConfig();
		Merplatformsetting merPlatformSetting=null;
		
		merPlatformSetting=MerplatformsettingDAO.selectByPrimaryKey(merPlatformNbr);
	
		if(merPlatformSetting==null){
			flag=UploadFlag.Failure;
		}
		config.setMode(merPlatformSetting.getFiletransfermode());
		config.setHost(merPlatformSetting.getFtpip());
		config.setPort(StringUtil.toInt(merPlatformSetting.getFtpport()));
		config.setUserName(merPlatformSetting.getFtpusername());
		config.setPassword(merPlatformSetting.getFtppassword());
		config.setWorkingDirectory(merPlatformSetting.getFileurl());
		try{
			if (!SftpUtil.uploadFile(localIssueFileName, issueFileLocalPath,config)) {
				flag=UploadFlag.Failure;
			}			
		}catch(Exception e){
			flag=UploadFlag.Failure;
			log.error("文件["+localIssueFileName+"],路径["+issueFileLocalPath+"]发送失败！平台号["+merPlatformNbr+"]");
			e.printStackTrace();
		}

		return flag;
	}
}
