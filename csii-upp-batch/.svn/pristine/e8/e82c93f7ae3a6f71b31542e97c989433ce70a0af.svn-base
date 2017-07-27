package com.csii.upp.batch.appl.wechat;

import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.upp.batch.appl.base.BaseCheckFileParseAppl;

public class CheckFileParseWeChatAppl extends BaseCheckFileParseAppl {

	@Override
	protected List<String> getCheckFileName(Map<String, Object> argMaps) {	
		return this.getInnerCheckApply(this.getFundChannelCd(argMaps));
	}

	@Override
	protected String getFundChannelCd(Map<String, Object> argMaps) {
		return "WXZF";
	}

	@Override
	protected void insertData(List<Map<String, Object>> rtxnMaps, Map<String, Object> argMaps, String fileName)
			throws PeException {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getFileType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean isDownloadFile() {
		// TODO Auto-generated method stub
		return false;
	}

}
