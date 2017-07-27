/*
 * Copyright 2005-2016 Client Service International, Inc. All rights reserved.
 * CSII PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * project: csii-upp-batch
 * create: 2016年2月16日 下午2:56:44
 * vc: $Id: $
 */
package com.csii.upp.batch.appl.paym;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.batch.appl.base.BaseAppl;
import com.csii.upp.batch.supportor.BatchUtil;
import com.csii.upp.batch.xml.format.FileFormat;
import com.csii.upp.constant.CheckStatus;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.FileNameSuffix;
import com.csii.upp.constant.IsParentMer;
import com.csii.upp.constant.Separator;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.constant.TransTypCd;
import com.csii.upp.constant.UploadFlag;
import com.csii.upp.dao.generate.BatchcleartransDAO;
import com.csii.upp.dao.generate.MeracctinfoDAO;
import com.csii.upp.dao.generate.OnlinefileinfoDAO;
import com.csii.upp.dto.generate.Batchcleartrans;
import com.csii.upp.dto.generate.BatchcleartransExample;
import com.csii.upp.dto.generate.Meracctinfo;
import com.csii.upp.dto.generate.MeracctinfoExample;
import com.csii.upp.dto.generate.Onlinefileinfo;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.FileHandler;

/**
 * Paym生成商户下发文件.
 * @author chen chao 
 * @version $Revision:$
 */
public class MerchantFileAppl extends BaseAppl {

	/* (non-Javadoc)
	 * @see com.csii.upp.batch.appl.base.BaseAppl#runAppl(java.lang.Object, java.util.Map)
	 */
	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps) throws Exception {
		String filePath = this.getApplBean().getMerchantFileFormatLocalPath();
		FileFormat format = this.getApplBean().getIssueFileFormat().getFileFormat(this.getIssueFileFormatFileId());
		//获取一级商户信息
		MeracctinfoExample merExample = new MeracctinfoExample();
		merExample.createCriteria().andIsparentmerEqualTo(IsParentMer.Y);
		List<Meracctinfo> meracctList = MeracctinfoDAO.selectByExample(merExample);
		for (Meracctinfo meracctinfo : meracctList) {
			//获取下发文件数据
			BatchcleartransExample bctExample = new BatchcleartransExample();
			bctExample.createCriteria().andMernbrEqualTo(meracctinfo.getMernbr()).andCheckstatusEqualTo(CheckStatus.CHECKED).andTransstatusNotEqualTo(TransStatus.FAILURE);
			List<Batchcleartrans> merchantList = BatchcleartransDAO.selectByExample(bctExample);

			if (!merchantList.isEmpty()) {
				List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
				for (Batchcleartrans batchcleartrans : merchantList) {
					Map<String, Object> map = new HashMap<String, Object>();
					if (TransStatus.SUCCESS.equals(batchcleartrans.getTransstatus()) || TransStatus.REVOKED.equals(batchcleartrans.getTransstatus()) 
							|| TransStatus.SUB_WITHDRAW.equals(batchcleartrans.getTransstatus()) || TransStatus.ALL_WITHDRAW.equals(batchcleartrans.getTransstatus())) {
						map.put("transstatus", "Y");
					}
					if (TransTypCd.PAY.equals(batchcleartrans.getTranstypcd())) {
						map.put("transcode", "ZF01");
					}else if (TransTypCd.RETURN.equals(batchcleartrans.getTranstypcd())) {
						map.put("transcode", "ZF02");
					}else if (TransTypCd.WTH.equals(batchcleartrans.getTranstypcd())) {
						map.put("transcode", "ZF03");
					}
					map.put("cleardate",batchcleartrans.getCleardate() );
					map.put("mertransdatetime",batchcleartrans.getMertransdatetime());
					map.put("merseqnbr", batchcleartrans.getMerseqnbr());
					map.put("transseqnbr", batchcleartrans.getTransseqnbr());
					map.put("mernbr", batchcleartrans.getMernbr());
					map.put("transamt", batchcleartrans.getTransamt().setScale(2, BigDecimal.ROUND_HALF_UP));
					map.put("memo1", "");
					map.put("memo2", "");
					resultList.add(map);
				}
				
				//文件命名
				String fileName = new StringBuilder(format.getPrefix()).append(Separator.FILENAME_SEPARATOR).append(meracctinfo.getMernbr()).
						append(Separator.FILENAME_SEPARATOR).append(DateUtil.Date_To_DateTimeFormat(this.getCheckDate(argMaps), DateFormatCode.DATE_FORMATTER3)).
						append(FileNameSuffix.FILENAMESUFFIX).append(format.getSuffix()).toString();
				//文件相对路径
				String fileURI = new StringBuilder(filePath).append(DateUtil.Date_To_DateTimeFormat(this.getCheckDate(argMaps), DateFormatCode.DATE_FORMATTER3)).append("/").toString();
				//文件路径
				//String filelocal = new StringBuilder(fileURI).toString();
				
				//生成文件
				this.writeIssueFile(resultList, format, fileURI, fileName);
				
				//向互联网前置发送信息
//				if (MerPlatformNbr.FSEG.equals(meracctinfo.getMerplatformnbr()) || MerPlatformNbr.FSJ.equals(meracctinfo.getMerplatformnbr())) {
//					InputFundData data = new InputFundData();
//					CoreService coreService = (CoreService) this.getRouterService(FundChannelCode.CORE.toLowerCase());
//					coreService.sendPaymIssueFile(data);
//				}else {
//					this.uploadFile(fileName, filePath, meracctinfo.getMerplatformnbr());
//				}
				
				
				//插表OnlineFileInfo批量文件信息通知表
				Onlinefileinfo of = new Onlinefileinfo();
				of.setFilenbr(IDGenerateFactory.generateSeqId());
				of.setBatchnbr("1");
				of.setFilepath(fileURI);
				of.setFilename(fileName);
				of.setFileparsedate(this.getCheckDate(argMaps));
				of.setFilememo(meracctinfo.getMerplatformnbr());//文件摘要存放平台号
				of.setCleardate(this.getCheckDate(argMaps));
				of.setUploadflag(UploadFlag.Int);
				OnlinefileinfoDAO.insertSelective(of);
			}
		}
	}
	/**
	 * 获取对账文件模板
	 * @return
	 */
	protected String getIssueFileFormatFileId() {
		return getApplBean().getMerchantFileFormatPaym();
	}
	/**
	 * 生成下发文件
	 */
	private void writeIssueFile(List<Map<String, Object>> resultList, FileFormat format,
			String filelocal, String fileName){
		FileOutputStream out = null;
		try {
			String encoding = format.getEncoding();
			String lineSeparator = format.getLineSeparator();
			FileHandler.createFile(filelocal, fileName);
			out = new FileOutputStream(filelocal + fileName, true);
			for (Map<String, Object> map : resultList) {
				String standardString = BatchUtil.getFormatString(map, format);
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
}
