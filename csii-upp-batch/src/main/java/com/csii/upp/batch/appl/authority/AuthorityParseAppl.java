package com.csii.upp.batch.appl.authority;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.upp.batch.appl.base.BaseAuthorityParseAppl;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.util.StringUtil;

/**
 * 
 *    @author fgw
 *		资金归集（本行借记卡、他行卡）
 */
public class AuthorityParseAppl extends BaseAuthorityParseAppl {
	
	protected String getFundChannelCd(Map<String, Object> argMaps) {
		return FundChannelCode.AUTH;
	}

	@Override
	protected void insertData(List<Map<String, Object>> rtxnMaps,Map<String, Object> argMaps, String fileName) throws PeException {
		
		
		String bacthno=String.valueOf(new Date().getTime());
		int i=1;
		for (Map<String, Object> map : rtxnMaps) {
		    
		    String seq=bacthno+String.format("%04d", i);
			map.put("customname", StringUtil.parseObjectToString(map.get("customname")));
			map.put("certtyp", StringUtil.parseObjectToString(map.get("certtyp")));
			map.put("certno", StringUtil.parseObjectToString(map.get("certno")));
			map.put("cardtyp", StringUtil.parseObjectToString(map.get("cardtyp")));
			map.put("cardno", StringUtil.parseObjectToString(map.get("cardno")));
			map.put("customtyp", StringUtil.parseObjectToString(map.get("customtyp")));
			map.put("phoneno", StringUtil.parseObjectToString(map.get("phoneno")));
			map.put("transtatus", TransStatus.INIT);
			map.put("batch", bacthno);
			map.put("rtxnseq",seq);
			map.put("transdate",this.getPostDate());
		    this.insertUserPaytypsigninfo(map);
			i++;
			
			
		}
	}

	@Override
	protected String getFileType() {
		return null;
	}

	@Override
	protected boolean isDownloadFile() {
		return false;
	}

	@Override
	protected List<String> getCheckFileName(Map<String, Object> argMaps) {
		return this.getInnerCheckApply(this.getFundChannelCd(argMaps));
	}
}
