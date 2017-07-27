package com.csii.upp.batch.appl.paym;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.upp.batch.appl.base.BaseCheckFileParseAppl;
import com.csii.upp.constant.CheckStatus;
import com.csii.upp.constant.ConstFdps;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dao.generate.BatchdownsystransDAO;
import com.csii.upp.dao.generate.OnlinetransDAO;
import com.csii.upp.dao.generate.OnlinetranshistDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.generate.Batchdownsystrans;
import com.csii.upp.dto.generate.Onlinetrans;
import com.csii.upp.dto.generate.Onlinetranshist;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * FDPS对账文件解析
 * 
 * @author GFQ
 *
 */
public class CheckFileParseFDPSAppl extends BaseCheckFileParseAppl {

	@Override
	protected String getFundChannelCd(Map<String, Object> argMaps) {
		return FundChannelCode.FDPS;
	}

	@Override
	protected void insertData(List<Map<String, Object>> rtxnMaps, Map<String, Object> argMaps, String fileName)
			throws PeException {
		for (Map<String, Object> map : rtxnMaps) {
			try {
				Batchdownsystrans batchdownsystrans = BatchdownsystransDAO.selectByPrimaryKey(StringUtil.parseObjectToString(map.get("downsystransnbr")));
				if (batchdownsystrans == null) {
					Onlinetrans onlinetrans = OnlinetransDAO.selectByPrimaryKey(StringUtil.parseObjectToString(map.get("transseqnbr")));
					if (onlinetrans == null) {
						Onlinetranshist onlinetranshist = OnlinetranshistDAO.selectByPrimaryKey(StringUtil.parseObjectToString(map.get("transseqnbr")));
						if (onlinetranshist != null) {
							Batchdownsystrans record = new Batchdownsystrans();
							record.setDownsystransnbr(StringUtil.parseObjectToString(map.get("downsystransnbr")));
							record.setTransseqnbr(StringUtil.parseObjectToString(map.get("transseqnbr")));
							record.setTransdate(DateUtil.DateFormat_To_Date(map.get("transdate")));
							record.setDownsysdate(DateUtil.DateFormat_To_Date(map.get("downsysdate")));
							record.setTranscode(StringUtil.parseObjectToString(map.get("transcode")));
							record.setCustcifnbr(onlinetranshist.getCustcifnbr());
							record.setPayeracctnbr(StringUtil.parseObjectToString(map.get("payeracctnbr")));
							record.setPayeraccttypcd(onlinetranshist.getPayeraccttypcd());
							record.setPayeracctdeptnbr(onlinetranshist.getPayeracctdeptnbr());
							record.setPayeracctname(onlinetranshist.getPayeracctname());
							record.setPayeeacctnbr(map.get("payeeacctnbr").toString());
							record.setPayeeacctdeptnbr(onlinetranshist.getPayeeacctdeptnbr());
							record.setPayeeaccttypcd(onlinetranshist.getPayeeaccttypcd());
							record.setPayeeacctname(onlinetranshist.getPayeeacctname());
							record.setPayeracctkind(onlinetranshist.getPayeracctkind());
							record.setPayeeacctkind(onlinetranshist.getPayeeacctkind());
							record.setPayercardtypcd(onlinetranshist.getPayercardtypcd());
							record.setPayeecardtypcd(onlinetranshist.getPayeecardtypcd());
							record.setTransamt(StringUtil.parseBigDecimal(map.get("transamt")));
							record.setCurrencycd(onlinetranshist.getCurrencycd());
							record.setTransstatus(StringUtil.parseObjectToString(map.get("transstatus")));
							record.setCheckstatus(CheckStatus.PRECHECK);//
							record.setDepartmentnbr(onlinetranshist.getDepartmentnbr());
							record.setMemo1(onlinetranshist.getMemo1());
							record.setMemo2(onlinetranshist.getMemo2());
							BatchdownsystransDAO.insertSelective(record);
						}
					}else {
						Batchdownsystrans record = new Batchdownsystrans();
						record.setDownsystransnbr(StringUtil.parseObjectToString(map.get("downsystransnbr")));
						record.setTransseqnbr(StringUtil.parseObjectToString(map.get("transseqnbr")));
						record.setTransdate(DateUtil.DateFormat_To_Date(map.get("transdate")));
						record.setDownsysdate(DateUtil.DateFormat_To_Date(map.get("downsysdate")));
						record.setTranscode(StringUtil.parseObjectToString(map.get("transcode")));
						record.setCustcifnbr(onlinetrans.getCustcifnbr());
						record.setPayeracctnbr(StringUtil.parseObjectToString(map.get("payeracctnbr")));
						record.setPayeraccttypcd(onlinetrans.getPayeraccttypcd());
						record.setPayeracctdeptnbr(onlinetrans.getPayeracctdeptnbr());
						record.setPayeracctname(onlinetrans.getPayeracctname());
						record.setPayeeacctnbr(map.get("payeeacctnbr").toString());
						record.setPayeeacctdeptnbr(onlinetrans.getPayeeacctdeptnbr());
						record.setPayeeaccttypcd(onlinetrans.getPayeeaccttypcd());
						record.setPayeeacctname(onlinetrans.getPayeeacctname());
						record.setPayeracctkind(onlinetrans.getPayeracctkind());
						record.setPayeeacctkind(onlinetrans.getPayeeacctkind());
						record.setPayercardtypcd(onlinetrans.getPayercardtypcd());
						record.setPayeecardtypcd(onlinetrans.getPayeecardtypcd());
						record.setTransamt(StringUtil.parseBigDecimal(map.get("transamt")));
						record.setCurrencycd(onlinetrans.getCurrencycd());
						record.setTransstatus(StringUtil.parseObjectToString(map.get("transstatus")));
						record.setCheckstatus(CheckStatus.PRECHECK);//
						record.setDepartmentnbr(onlinetrans.getDepartmentnbr());
						record.setMemo1(onlinetrans.getMemo1());
						record.setMemo2(onlinetrans.getMemo2());
						BatchdownsystransDAO.insertSelective(record);
					}
				}
			} catch (SQLException e) {
				throw new PeException(e);
			}
		}
	}

	@Override
	protected String getFileType() {
		return ConstFdps.FILETYPE;
	}

	@Override
	protected boolean isDownloadFile() {
		return false;
	}

	@Override
	protected List<String> getCheckFileName(Map<String, Object> argMaps) {
		String batchtypcd = StringUtil.parseObjectToString(argMaps.get(Dict.XBBZ));
		return this.getUperSysCheckNotice(this.getFundChannelCd(argMaps), this.getCheckDate(argMaps),batchtypcd);
	}
}
