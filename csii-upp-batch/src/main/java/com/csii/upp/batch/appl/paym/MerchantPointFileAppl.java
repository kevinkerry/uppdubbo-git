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
import com.csii.upp.constant.DepartmentNbr;
import com.csii.upp.constant.FileNameSuffix;
import com.csii.upp.constant.InteralFlag;
import com.csii.upp.constant.MerTransCtrlStatus;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.Separator;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.constant.TransTypCd;
import com.csii.upp.constant.UploadFlag;
import com.csii.upp.dao.extend.BatchclearsubtransExtendDAO;
import com.csii.upp.dao.extend.MertransctrlExtendDAO;
import com.csii.upp.dao.generate.OnlinefileinfoDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.generate.Onlinefileinfo;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.FileHandler;

/**
 * Paym生成商户积分对账文件.
 * @author wangtao 
 * @version $Revision:$
 */
public class MerchantPointFileAppl extends BaseAppl {

	/* (non-Javadoc)
	 * @see com.csii.upp.batch.appl.base.BaseAppl#runAppl(java.lang.Object, java.util.Map)
	 */
	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps) throws Exception {
		String filePath = this.getApplBean().getMerchantFileFormatLocalPath();//与商户对帐文件共用同一目录
		FileFormat format = this.getApplBean().getIssueFileFormat().getFileFormat(this.getIssueFileFormatFileId());
		
		//获取支持积分支付的一级商户信息
		List<Map> pointMers = MertransctrlExtendDAO.queryPointMer(PayTypCd.POINT, MerTransCtrlStatus.Y);
		if(pointMers!=null && !pointMers.isEmpty()){
			for(Map mernbrMap : pointMers){//根据商户生成对帐文件，没有数据生成空文件	
				//获取商户支付方式
				List<String> payTypCDList = MertransctrlExtendDAO.queryMerPayTyp(mernbrMap.get("mernbr").toString(), MerTransCtrlStatus.Y);				
				String departmentNbr = getOtherBankMerchant(payTypCDList);
				if(!departmentNbr.equals(argMaps.get(Dict.XBBZ))){
					log.debug("商户["+mernbrMap.get("mernbr")+"]不支持当前对账级别["+argMaps.get(Dict.XBBZ)+"]");
					continue;
				}
				List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
				//根据商户号查询积分交易
				List<Map> batchClearSubTranss = BatchclearsubtransExtendDAO.getBatchclearsubtransByCheckStatus(InteralFlag.YES, CheckStatus.CHECKED, mernbrMap.get("mernbr").toString());
				for(Map batchClearSubTrans:batchClearSubTranss){
					Map<String, Object> map = new HashMap<String, Object>();
					if (TransTypCd.PAY.equals(batchClearSubTrans.get("transtypcd"))) {
						map.put("transcode", "ZF01");
					}else if (TransTypCd.RETURN.equals(batchClearSubTrans.get("transtypcd"))) {
						map.put("transcode", "ZF02");
					}else if (TransTypCd.WTH.equals(batchClearSubTrans.get("transtypcd"))) {
						map.put("transcode", "ZF03");
					}	
					map.put("cleardate",batchClearSubTrans.get("cleardate"));
					map.put("mertransdatetime",batchClearSubTrans.get("mertransdatetime"));
					map.put("supermerseqnbr",batchClearSubTrans.get("supermerseqnbr"));
					map.put("mernbr",batchClearSubTrans.get("mernbr"));
					map.put("merseqnbr",batchClearSubTrans.get("merseqnbr"));
					BigDecimal transamt = new BigDecimal(batchClearSubTrans.get("transamt").toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
					map.put("transamt",transamt);
					BigDecimal deductamt = new BigDecimal(batchClearSubTrans.get("deductamt").toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
					map.put("deductamt",deductamt);
					BigDecimal realamt = new BigDecimal(batchClearSubTrans.get("realamt").toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
					map.put("realamt",realamt);
					if (TransStatus.SUCCESS.equals(batchClearSubTrans.get("transstatus")) || TransStatus.REVOKED.equals(batchClearSubTrans.get("transstatus")) 
							|| TransStatus.SUB_WITHDRAW.equals(batchClearSubTrans.get("transstatus")) || TransStatus.ALL_WITHDRAW.equals(batchClearSubTrans.get("transstatus"))) {
						map.put("transstatus", "Y");
					}else if (TransStatus.FAILURE.equals(batchClearSubTrans.get("transstatus"))) {
						map.put("transstatus", "N");
					}
					map.put("memo1",batchClearSubTrans.get("memo1"));
					map.put("memo2",batchClearSubTrans.get("memo1"));
					resultList.add(map);
				}
				//
				//文件命名
				String fileName = new StringBuilder(format.getPrefix()).append(Separator.FILENAME_SEPARATOR).append(mernbrMap.get("mernbr")).
						append(Separator.FILENAME_SEPARATOR).append(DateUtil.Date_To_DateTimeFormat(this.getCheckDate(argMaps), DateFormatCode.DATE_FORMATTER3)).
						append(FileNameSuffix.FILENAMESUFFIX).append(format.getSuffix()).toString();
				//文件相对路径
				String fileURI = new StringBuilder(filePath).append(DateUtil.Date_To_DateTimeFormat(this.getCheckDate(argMaps), DateFormatCode.DATE_FORMATTER3)).append("/").toString();
				//生成文件
				this.writeIssueFile(resultList, format, fileURI, fileName);
				
				//插表OnlineFileInfo批量文件信息通知表
				Onlinefileinfo of = new Onlinefileinfo();
				of.setFilenbr(IDGenerateFactory.generateSeqId());
				of.setBatchnbr("1");
				of.setFilepath(fileURI);
				of.setFilename(fileName);
				of.setFileparsedate(this.getCheckDate(argMaps));
				of.setFilememo(mernbrMap.get("merplatformnbr").toString());//文件摘要存放平台号
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
		return getApplBean().getMerchantPointFileFormatPaym();
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
	
	/**
	 * 判断是否支持他行交易
	 * @param payTypCDList
	 * @return 1:支持他行商户 0：只支持本行商户
	 */
	private String getOtherBankMerchant(List<String> payTypCDList){
		for(String payTypCD :payTypCDList){
			if(PayTypCd.INTEL.equals(payTypCD) || PayTypCd.OTHCYBER.equals(payTypCD)){
				return DepartmentNbr.THIRD;
			}
		}
		return DepartmentNbr.OUR;
		
	}
}
