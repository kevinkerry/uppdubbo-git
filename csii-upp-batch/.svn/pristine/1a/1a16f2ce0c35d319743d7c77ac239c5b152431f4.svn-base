package com.csii.upp.batch.appl.base;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.batch.supportor.BatchUtil;
import com.csii.upp.batch.xml.format.FileFormat;
import com.csii.upp.constant.CheckFileDealFlag;
import com.csii.upp.constant.CheckStatus;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.extend.BatchdownsystransExtendDAO;
import com.csii.upp.dao.extend.DownsysfundtransExtendDAO;
import com.csii.upp.dao.extend.OverallrequestregExtendDAO;
import com.csii.upp.dao.extend.SysinfoExtendDAO;
import com.csii.upp.dao.generate.ChecknoticeDAO;
import com.csii.upp.dao.generate.InnercheckapplyDAO;
import com.csii.upp.dao.generate.OverallrequestregDAO;
import com.csii.upp.dao.generate.PreprocessfundtransDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Batchdownsystrans;
import com.csii.upp.dto.generate.Checknotice;
import com.csii.upp.dto.generate.ChecknoticeExample;
import com.csii.upp.dto.generate.Downsysfundtrans;
import com.csii.upp.dto.generate.Innercheckapply;
import com.csii.upp.dto.generate.InnercheckapplyExample;
import com.csii.upp.dto.generate.Overallrequestreg;
import com.csii.upp.dto.generate.Preprocessfundtrans;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.BeanUtils;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.FtpConfigFactory;
import com.csii.upp.util.SftpUtil;
import com.csii.upp.util.StringUtil;

/**
 * 对账文件解析应用调用入口
 * 
 * @author xujin
 * 
 */
public abstract class BaseCheckFileParseAppl extends BaseAppl {

	/**
	 * 服务上的对账文件名
	 * 
	 * @return
	 */
	protected abstract List<String> getCheckFileName(Map<String, Object> argMaps);

	/**
	 * 资金渠道代码
	 * 
	 * @return
	 */
	protected abstract String getFundChannelCd(Map<String, Object> argMaps);

	/**
	 * 入库
	 * 
	 * @return
	 */
	protected abstract void insertData(List<Map<String, Object>> rtxnMaps,Map<String, Object> argMaps,
			String fileName) throws PeException;

	protected abstract String getFileType();

	protected abstract boolean isDownloadFile();

	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps) {
		String fundChannelCd = getFundChannelCd(argMaps);
		try {
			Date checkDate = this.getCheckDate(argMaps);
			String serverFileName = null;
			List<String> serverFileNameList = this.getCheckFileName(argMaps);
			if(serverFileNameList == null)
				return;
			for (int i = 0; i < serverFileNameList.size(); i++) {
				serverFileName = serverFileNameList.get(i);
				String localPath = StringUtil.parseObjectToString(this
						.getApplBean().getCheckFileParserLocalPathMap()
						.get(fundChannelCd.toLowerCase()));
				log.info("serverFileName:" + serverFileName + ", localPath:" + localPath);
				if (this.isDownloadFile()) {
					log.info(StringUtil.buildString("download check file:",
							serverFileName));
					// 下载失败直接返回
					if (!SftpUtil.downloadFile(serverFileName, localPath,
							checkDate,
							FtpConfigFactory.getConfig(this.getFileType()))) {
						throw new PeRuntimeException(
								"download check file error");
					}
				}
				log.info(StringUtil.buildString("parse check file:",
						serverFileName));
				//二维码前置区分支付宝微信对账文件
				if(FundChannelCode.QRCODE.equals(fundChannelCd)){
					//通过文件名后三位
					String serverFileNameFlag=serverFileName.substring(serverFileName.length()-3, serverFileName.length());
					if("ZFB".equals(serverFileNameFlag)){
						fundChannelCd=FundChannelCode.ALIPAYCODE;
					}else{
						fundChannelCd=FundChannelCode.WECHATCODE;
					}
					log.info(new StringBuilder().append("二维码前置对账文件[").append(serverFileName).append("]").append("[根据文件名后缀").
							append(serverFileNameFlag).append("]").append("已区分"));
				}
				// 解析对账文件 poc测试修改，paym对账时才加载对账文件
				if(FundChannelCode.FDPS.equals(fundChannelCd.toUpperCase())){
					final List<Map<String, Object>> maps = this.parseFile(fundChannelCd.toLowerCase(), localPath,
							serverFileName, DateUtil.Date_To_DateFormat(checkDate));
					// 入库
					insertData(maps, argMaps,serverFileName);					
				}else{}
				//二维码前置渠道
				if(FundChannelCode.ALIPAYCODE.equals(fundChannelCd)||
						FundChannelCode.WECHATCODE.equals(fundChannelCd)){
					fundChannelCd=FundChannelCode.QRCODE;
				}

			}
			if(!FundChannelCode.FDPS.equals(fundChannelCd.toUpperCase())){
				//poc 将innerfundtrans表数据直接转入downsysfundtrans表
				Map map1 = new HashMap();
				map1.put("fundChannelCd", fundChannelCd);
				DownsysfundtransExtendDAO.InsertDownsysfundtransFromInnerfundtrans(map1);
										
			}

			if (serverFileNameList.size() > 0 ) {
				updateCheckApplyState(fundChannelCd, checkDate,
						CheckFileDealFlag.Dealed);
			}
		} catch (PeException e) {
			throw new PeRuntimeException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	protected List<Map<String, Object>> parseFile(String fundChannelCd,
			String filePath, String fileName, String checkDate)
			throws PeException {
		if (fileName.indexOf("/") != -1) {
			fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		InputStreamReader sourceIn = null;
		BufferedReader sourceBufferedReader = null;
		try {
			String fileFormatId = fundChannelCd;
			boolean isSkipNotFile=false;
			FileFormat fileFormat = this.getApplBean().getCheckFileParser()
					.getFileFormat(fileFormatId);
			try {
				sourceIn = new InputStreamReader(new FileInputStream(filePath
						+ fileName), fileFormat.getEncoding());
				sourceBufferedReader = new BufferedReader(sourceIn);
			} catch (Exception e) {
				log.error(StringUtil.buildString("read ", filePath + fileName,
						" file Exception:", e.getMessage()));
				if(isSkipNotFile){
					return list;
				}else{
					throw new PeRuntimeException(e.getMessage());
				}
			}
			String tmpLine = null;
			int num = 0;
			String line = sourceBufferedReader.readLine();
			while (line != null) {
				tmpLine = line;
				line = sourceBufferedReader.readLine();
				num++;
				if (fileFormat.isSkipBeginLine() && num == 1) {
					continue;
				}
				if (fileFormat.isSkipEndLine() && line == null) {
					break;
				}
				if ("".equals(tmpLine)) {
					continue;
				}
				try {
					StringBuilder err = new StringBuilder("");
					Map<String, Object> parsedMap = BatchUtil.getFormateMap(
							tmpLine, fileFormat, err);
					if (parsedMap == null)
						throw new PeException("recored formate error:" + err);
					parsedMap.put("cleardate", checkDate);
					list.add(parsedMap);
				} catch (Exception e) {
					log.error(StringUtil.buildString("parse check file error:",
							e.getMessage()));
					throw new PeRuntimeException(e.getMessage());
				}
			}
		} catch (Exception e) {
			log.error(StringUtil.buildString("read ", filePath + fileName,
					" file Exception:", e.getMessage()));
			throw new PeRuntimeException(e.getMessage());
		} finally {
			try {
				if (sourceBufferedReader != null)
					sourceBufferedReader.close();
			} catch (IOException e) {
			}
			if (sourceIn != null)
				try {
					sourceIn.close();
				} catch (IOException e) {
				}
		}
		return list;
	}

	/**
	 * 插入下游对账流水
	 * 
	 * @param map
	 * @throws PeException
	 */
	protected void insertDownsysfundtrans(Map<String, Object> map)
			throws PeException {
		try {
			Downsysfundtrans record = this.getObjectMapMarshaller().unMarshall(
					Downsysfundtrans.class, map);
			// 排重
			if (DownsysfundtransExtendDAO.getDownsysfundtransCnt(record
					.getInnerfundtransnbr()) < 1) {
				if (StringUtil.isObjectEmpty(record.getTransstatus())) {
					record.setTransstatus(TransStatus.SUCCESS);
				}
				if (StringUtil.isObjectEmpty(record.getTransdate())) {
					record.setTransdate(record.getCleardate());
				}
				record.setFundchannelcode(getFundChannelCd(map));
				record.setCheckstatus(CheckStatus.PRECHECK);
				DownsysfundtransExtendDAO.insertDownsysfundtrans(record);
			}
		} catch (Exception e) {
			throw new PeRuntimeException(e.getMessage());
		}
	}

	/**
	 * 插入后台交易明细流水
	 * 
	 * @param map
	 * @throws PeException
	 */
	protected void insertBatchDownSysTrans(Map<String, Object> map)
			throws PeException {
		try {
			Batchdownsystrans record = this.getObjectMapMarshaller().unMarshall(
					Batchdownsystrans.class, map);
			// 排重
			if (BatchdownsystransExtendDAO.getBatchdownsystransCnt(record.getDownsystransnbr()) < 1) {
				if (StringUtil.isObjectEmpty(record.getTransstatus())) {
					record.setTransstatus(TransStatus.SUCCESS);
				}
				if (StringUtil.isObjectEmpty(record.getTransdate())) {
					record.setTransdate(SysinfoExtendDAO.getSysInfo()
							.getPrevpostdate());
				}
				record.setCheckstatus(CheckStatus.PRECHECK);
				BatchdownsystransExtendDAO.insertBatchDownSysTrans(record);
			}
		} catch (Exception e) {
			throw new PeRuntimeException(e.getMessage());
		}
	}
	
	
	/**
	 * 插入待处理资金流水
	 * 
	 * @param map
	 * @throws PeException
	 */
	protected void insertPreprocessfundtrans(Map<String, Object> map,
			String processState) throws PeException {
		try {
			Preprocessfundtrans record = this.getObjectMapMarshaller()
					.unMarshall(Preprocessfundtrans.class, map);
			record.setDealstatus(processState);
			PreprocessfundtransDAO.insert(record);
		} catch (Exception e) {
			throw new PeRuntimeException(e.getMessage());
		}
	}

	private void updateCheckApplyState(String fundChannelCd, Date checkDate,
			String dealState) {
		InnercheckapplyExample example = new InnercheckapplyExample();
		example.createCriteria().andDownsysnbrEqualTo(fundChannelCd)
				.andDealcodeEqualTo(CheckFileDealFlag.UnDeal);
		List<Innercheckapply> checkapplys;
		try {
			checkapplys = InnercheckapplyDAO.selectByExample(example);
		} catch (SQLException e) {
			throw new PeRuntimeException("Get Innercheckapply Failed.");
		}
		for (int i = 0; i < checkapplys.size(); i++) {
			Innercheckapply innercheckappl = checkapplys.get(i);
			innercheckappl.setDealcode(dealState);
			innercheckappl.setDealmsg("申请已处理");
			try {
				InnercheckapplyDAO.updateByPrimaryKeySelective(innercheckappl);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}

	}

	/**
	 * 查找对账通知表获得对账文件名
	 * 
	 * @return
	 */
	protected String getCheckNoticeFileName(String fundChannelCd, Date checkDate) {
		ChecknoticeExample example = new ChecknoticeExample();
		example.createCriteria().andCheckdateEqualTo(checkDate)
				.andFundchannelcodeEqualTo(fundChannelCd);
		List<Checknotice> checknotices;
		try {
			checknotices = ChecknoticeDAO.selectByExample(example);
		} catch (SQLException e) {
			throw new PeRuntimeException("Get Innercheckapply Failed.");
		}
		if (checknotices.isEmpty()) {
			throw new PeRuntimeException("Get Innercheckapply is null:"
					+ fundChannelCd);
		}
		return checknotices.get(0).getFilename();
	}

	/**
	 * 来账需要补账时，插入总交易快照，正常交易已经排重
	 * 
	 * @param map
	 */
	@SuppressWarnings("rawtypes")
	protected void insertOverallRequestReg(Map paramMap) {
		try {
			String uppersysnbr = StringUtil.parseObjectToString(paramMap
					.get(Dict.FUND_CHANNEL_CD));
			String upperrtxnnbr = StringUtil.parseObjectToString(paramMap
					.get(Dict.DOWN_TRANS_NBR));
			Date upperrtxndate = DateUtil.DateFormat_To_Date(paramMap
					.get(Dict.DOWN_TRANS_DATE));
			String sendBankCode = StringUtil.parseObjectToString(paramMap
					.get("send_bank_code"));
			if (OverallrequestregExtendDAO.getOverallrequestregCnt(
					upperrtxnnbr, upperrtxndate, uppersysnbr) < 1) {
				Overallrequestreg record = new Overallrequestreg();
				record.setUppertransnbr(upperrtxnnbr);
				record.setUppertransdate(upperrtxndate);
				record.setUppersysnbr(uppersysnbr);
				InputFundData input = new InputFundData();
				input.setPayerbanknbr(sendBankCode);
				input.setPayeracctdeptnbr(sendBankCode);
				input.setCheckdataflag(uppersysnbr);
				input.setMsgid(StringUtil.parseObjectToString(paramMap
						.get(Dict.MSG_ID)));
				record.setUpperregsnap(BeanUtils.beanToXmlString(input));
				record.setOverallreqnbr(IDGenerateFactory.generateSeqId());
				OverallrequestregDAO.insertSelective(record);
			}
		} catch (Exception e) {
			throw new PeRuntimeException(e.getMessage());
		}
	}
}
