package com.csii.upp.batch.appl.core;

import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.upp.batch.appl.base.BaseCheckFileParseAppl;
import com.csii.upp.constant.ConstCore;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 核心对账文件解析
 * 
 * @author 徐锦
 *
 */
public class CheckFileParseCoreAppl extends BaseCheckFileParseAppl {

	@Override
	protected String getFundChannelCd(Map<String, Object> argMaps) {
		return FundChannelCode.CORE;
	}

	@Override
	protected void insertData(List<Map<String, Object>> rtxnMaps,Map<String, Object> argMaps, String fileName) throws PeException {
		for (Map<String, Object> map : rtxnMaps) {
			String cdFlag = StringUtil.parseObjectToString(map.get("BWOACDFG"));
			String acctNbr = StringUtil.parseObjectToString(map.get("BWOANUM"));
			// 1-借-付款账户，2-贷-收款账户
			if ("1".equals(cdFlag)) {
				map.put(Dict.PAYERACCTNBR, acctNbr);
			} else if ("2".equals(cdFlag)) {
				map.put(Dict.PAYEEACCTNBR, acctNbr);
			}
			map.put(Dict.DOWNTRANSNBR, StringUtil.parseObjectToString(map.get("BWOARMRK")));
			if (ConstCore.TRANSNO.equals((StringUtil.parseObjectToString(map.get(Dict.INNERFUNDTRANSNBR))).substring(0, 2))) {
				if(!this.isCoreCheckData(argMaps)){
					map.put(Dict.TRANSDATE, DateUtil.addDate(this.getCheckDate(argMaps), 1));
					map.put("cleardate", DateUtil.addDate(this.getCheckDate(argMaps), 1));
				}
				this.insertDownsysfundtrans(map);
			}
		}
	}

	@Override
	protected String getFileType() {
		return ConstCore.FILETYPE;
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
