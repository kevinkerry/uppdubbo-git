package com.csii.upp.batch.appl.bill99;

import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.upp.batch.appl.base.BaseCheckFileParseAppl;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.util.StringUtil;

public class CheckFileParseBill99Appl extends BaseCheckFileParseAppl {

 
	@Override
	protected String getFundChannelCd(Map<String, Object> argMaps) {
		// TODO Auto-generated method stub
		return FundChannelCode.BILL99;
	}

	@Override
	protected void insertData(List<Map<String, Object>> rtxnMaps,Map<String, Object> argMaps,String fileName)
			throws PeException {
		log.info(StringUtil.buildString(getFundChannelCd(rtxnMaps.get(0))," Insert ", rtxnMaps.size(),
				" data into Downsysfundtrans table"));
		for (Map<String, Object> map : rtxnMaps) {
			this.insertDownsysfundtrans(map);
		}
	}

	@Override
	protected String getFileType() {
		// TODO Auto-generated method stub
		return null;
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
