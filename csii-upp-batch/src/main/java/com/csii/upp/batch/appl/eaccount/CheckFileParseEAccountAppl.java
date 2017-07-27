package com.csii.upp.batch.appl.eaccount;

import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.upp.batch.appl.base.BaseCheckFileParseAppl;
import com.csii.upp.constant.ConstEAccount;
import com.csii.upp.constant.FundChannelCode;

/**
 * 电子账户对账文件解析
 * 
 * @author 徐锦
 *
 */
public class CheckFileParseEAccountAppl extends BaseCheckFileParseAppl {

	@Override
	protected String getFundChannelCd(Map<String, Object> argMaps) {
		return FundChannelCode.EACCOUNT;
	}

	@Override
	protected void insertData(List<Map<String, Object>> rtxnMaps,Map<String, Object> argMaps,String fileName)
			throws PeException {
//		log.info(StringUtil.buildString(getFundChannelCd(rtxnMaps.get(0)), " Insert ",
//				rtxnMaps.size(), " data into Downsysfundtrans table"));
		for (Map<String, Object> map : rtxnMaps) {
			this.insertDownsysfundtrans(map);
		}
	}

	@Override
	protected String getFileType() {
		return ConstEAccount.FILETYPE;
	}
	
	@Override
	protected boolean isDownloadFile() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected List<String> getCheckFileName(Map<String, Object> argMaps) {
		// TODO Auto-generated method stub
		return null;
	}
}
