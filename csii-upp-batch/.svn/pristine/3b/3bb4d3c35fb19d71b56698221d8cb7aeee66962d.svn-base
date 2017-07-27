package com.csii.upp.batch.appl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.batch.appl.base.BaseIssueFileAppl;
import com.csii.upp.constant.CheckFileDealFlag;
import com.csii.upp.constant.ConstPaym;
import com.csii.upp.constant.DealResult;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dao.extend.FundchannelExtendDAO;
import com.csii.upp.dao.extend.FundchannelcleartransExtendDAO;
import com.csii.upp.dao.extend.OveralltranshistExtendDAO;
import com.csii.upp.dao.extend.SysinfoExtendDAO;
import com.csii.upp.dao.generate.FundchannelDAO;
import com.csii.upp.dao.generate.UpersyschecknoticeDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.IssueFile;
import com.csii.upp.dto.generate.Fundchannel;
import com.csii.upp.dto.generate.Overalltranshist;
import com.csii.upp.dto.generate.Sysinfo;
import com.csii.upp.dto.generate.Upersyschecknotice;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.StringUtil;

/**
 * 生成对账文件并下发
 * 
 * @author chen chao
 *
 */
public class IssueCheckFilePaymAppl extends BaseIssueFileAppl {

	@Override
	protected String getIssueFileFormatFileId() {
		return this.getApplBean().getIssueFileFormatFdps();
	}

	@Override
	protected Sysinfo getSysInfo() {
		return SysinfoExtendDAO.getSysInfo();
	}

	@Override
	protected List<IssueFile> getIssueFileList(Map<String, Object> argMaps) throws Exception {
		String fundChannelCd = StringUtil.parseObjectToString(argMaps.get(Dict.FCNM));
		String checkDataFlag = StringUtil.parseObjectToString(argMaps.get(Dict.DZBZ));
		List<IssueFile> generateList = new ArrayList<IssueFile>();
		// 获取渠道类别
		Fundchannel channel = FundchannelDAO.selectByPrimaryKey(fundChannelCd);
		// 获取渠道类别下对账结果为未对账的数据
		int cnt = FundchannelExtendDAO.getFundchannelNoCheckedCnt(channel.getFundchanneltype(),
				this.getCheckDate(argMaps), CheckFileDealFlag.Checked);
		// 判断该渠道类别是否全部对账完成，如果有未对账的，则不生成渠道类别对账文件
		//poc不做次判断
		//if (cnt > 0) {
		if(false){
			log.info("该渠道类别下有通道未完成对账");
		} else {
			// 查询overalltranshist获取清算日期本渠道类别数据
			List<Overalltranshist> overallList = OveralltranshistExtendDAO
					.getOveralltranshistList(channel.getFundchanneltype(), checkDataFlag);
			for (Overalltranshist overalltranshist : overallList) {
				IssueFile issueFile = new IssueFile();
				issueFile.setRtxnnbr(overalltranshist.getOveralltransnbr());
				issueFile.setUpperrtxnnbr(overalltranshist.getUppertransnbr());
				issueFile.setUpperrtxndate(overalltranshist.getTransdate());
				issueFile.setRtxndate(overalltranshist.getUppertransdate());
				issueFile.setRtxntypcd(overalltranshist.getOveralltranstypcd());
				issueFile.setTransstatus(overalltranshist.getOveralltransstatus());
				issueFile.setTransamt(overalltranshist.getTransamt().toString());
				issueFile.setPayeracctnbr(overalltranshist.getPayeracctnbr());
				issueFile.setPayeeacctnbr(overalltranshist.getPayeeacctnbr());
				generateList.add(issueFile);
			}
		}
		return generateList;
	}

	@Override
	protected String getFileType() {
		return ConstPaym.FILETYPE;
	}

	@Override
	protected void insertFileInfo(final String fileName,final String filePath, Map<String, Object> argMaps) throws Exception {
		final String fundChannelCd = StringUtil.parseObjectToString(argMaps.get(Dict.FCNM));
		final String upperSysNbr = FundChannelCode.FDPS;
		final Date checkDate = this.getCheckDate(argMaps);
		// 通知信息插入upersyschecknotice
		final Fundchannel channel = FundchannelDAO.selectByPrimaryKey(fundChannelCd);
		getTransactionTemplate().execute(new TransactionCallback() {

			@Override
			public Object doInTransaction(TransactionStatus arg0) {
				try {
					Upersyschecknotice uperRecord = new Upersyschecknotice();
					uperRecord.setChecknoticenbr(IDGenerateFactory.generateSeqId());
					// uperRecord.setFilenbr();
					uperRecord.setUppersysnbr(upperSysNbr);
					uperRecord.setCheckstartdate(checkDate);
					uperRecord.setCheckenddate(checkDate);
					uperRecord.setBatchtypcd(channel.getFundchanneltype());
					uperRecord.setFilename(fileName);
					// uperRecord.setTotalnum();
					// uperRecord.setTotalamount();
					uperRecord.setFilepath(filePath);
					// uperRecord.setOrigproductnbr();
					// uperRecord.setCurrencycd();
					uperRecord.setDealresult(DealResult.UnDeal);
					// uperRecord.setMemo1();
					// uperRecord.setMemo2();
					// uperRecord.setMemo3();
					UpersyschecknoticeDAO.insertSelective(uperRecord);

					// 将fundchannelclealtrans（渠道台账表）已生成文件的数据移到历史表并删除移植数据
					Map<String, Object> para = new HashMap<String, Object>();
					para.put("fundchanneltype", channel.getFundchanneltype());
					FundchannelcleartransExtendDAO.insertFundchannelcleartransToHist(para);

					FundchannelcleartransExtendDAO.deleteFundchannelcleartransBeUsed(para);
				} catch (SQLException e) {
					throw new PeRuntimeException(e);
				}
				return null;
			}
		});
	}
}
