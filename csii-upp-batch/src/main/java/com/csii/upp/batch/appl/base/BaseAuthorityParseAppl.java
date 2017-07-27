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
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dao.extend.OverallrequestregExtendDAO;
import com.csii.upp.dao.generate.AuthorityDAO;
import com.csii.upp.dao.generate.InnercheckapplyDAO;
import com.csii.upp.dao.generate.OverallrequestregDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Authority;
import com.csii.upp.dto.generate.Innercheckapply;
import com.csii.upp.dto.generate.InnercheckapplyExample;
import com.csii.upp.dto.generate.Overallrequestreg;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.BeanUtils;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 对账文件解析应用调用入口
 * 
 * @author fgw
 * 
 */
public abstract class BaseAuthorityParseAppl extends BaseAppl {

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
	protected abstract void insertData(List<Map<String, Object>> rtxnMaps,
			Map<String, Object> argMaps, String fileName) throws PeException;

	protected abstract String getFileType();

	protected abstract boolean isDownloadFile();

	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps) {
		String fundChannelCd = getFundChannelCd(argMaps);
		try {
			Date checkDate = this.getCheckDate(argMaps);
	        Map map = new HashMap();
			InnercheckapplyExample example = new InnercheckapplyExample();
			String dealdate = DateUtil.Date_To_DateTimeFormat(new Date(),DateFormatCode.DATE_FORMATTER1);
			InnercheckapplyExample innerexample = new InnercheckapplyExample();
			innerexample.setOrderByClause("checkapplynbr DESC");
			innerexample.createCriteria().andDownsysnbrEqualTo(FundChannelCode.AUTH).andDealcodeEqualTo(CheckFileDealFlag.UnDeal);
			List<Innercheckapply> innerlist = null;
			try {
				innerlist = InnercheckapplyDAO.selectByExample(innerexample);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(innerlist != null && innerlist.size() > 0) {
				String fileurl = innerlist.get(0).getMemo3();
				String filename = innerlist.get(0).getFilename();
				String localPath = StringUtil.parseObjectToString(this.getApplBean().getCheckFileParserLocalPathMap().get(fundChannelCd.toLowerCase()));	
				// 解析对账文件
				final List<Map<String, Object>> maps = this.parseFile(fundChannelCd.toLowerCase(), localPath,filename);
				// 入库
				insertData(maps, argMaps, filename);
				updateCheckApplyState(fundChannelCd,CheckFileDealFlag.Dealed);
			}
		}catch (PeException e) {
			throw new PeRuntimeException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	protected List<Map<String, Object>> parseFile(String fundChannelCd,
			String filePath, String fileName) throws PeException {
		if (fileName.indexOf("/") != -1) {
			fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		InputStreamReader sourceIn = null;
		BufferedReader sourceBufferedReader = null;
		try {
			String fileFormatId = fundChannelCd;
			boolean isSkipNotFile = false;
			FileFormat fileFormat = this.getApplBean().getCheckFileParser()
					.getFileFormat(fileFormatId);
			try {
				sourceIn = new InputStreamReader(new FileInputStream(filePath
						+ fileName), fileFormat.getEncoding());
				sourceBufferedReader = new BufferedReader(sourceIn);
			} catch (Exception e) {
				log.error(StringUtil.buildString("read ", filePath + fileName,
						" file Exception:", e.getMessage()));
				if (isSkipNotFile) {
					return list;
				} else {
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
	 * 插入资金归集表
	 * 
	 * @param map
	 * @throws PeException
	 */
	protected void insertUserPaytypsigninfo(Map<String, Object> map) throws PeException {
		try {
			Authority record = this.getObjectMapMarshaller().unMarshall(
					Authority.class, map);
	    	record.setRtxnseq(IDGenerateFactory.generateSeqId());
			AuthorityDAO.insert(record);
		} catch (Exception e) {
			throw new PeRuntimeException(e.getMessage());
		}
	}

	private void updateCheckApplyState(String fundChannelCd,String dealState) {
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
