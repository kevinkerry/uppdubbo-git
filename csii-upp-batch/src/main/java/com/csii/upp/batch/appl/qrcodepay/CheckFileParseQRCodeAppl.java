package com.csii.upp.batch.appl.qrcodepay;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.upp.batch.appl.base.BaseCheckFileParseAppl;
import com.csii.upp.constant.ConstCore;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dict.Dict;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 核心对账文件解析
 * 
 * @author 徐锦
 *
 */
public class CheckFileParseQRCodeAppl extends BaseCheckFileParseAppl {

	@Override
	protected String getFundChannelCd(Map<String, Object> argMaps) {
		return FundChannelCode.QRCODE;
	}

	@Override
	protected void insertData(List<Map<String, Object>> rtxnMaps,Map<String, Object> argMaps, String fileName) throws PeException {
		for (Map<String, Object> map : rtxnMaps) {
			Date clearDate = this.getCheckDate(argMaps);
			//微信对账文件流水的交易状态都是成功
			String timeformat=(String) map.get("transdate")+(String) map.get("transtime");
			map.put(Dict.TRANSSTATUS, TransStatus.SUCCESS);
			map.put("transtime",DateUtil.DateTimeFormat_To_Date(timeformat,DateFormatCode. DATETIME_FORMATTER3));
			map.put("feeamt", StringUtil.parseBigDecimal(map.get("feeamt")));
			map.put("transferfeeamt",StringUtil.parseBigDecimal(map.get("transferfeeamt")));
			map.put(Dict.FUNDCHANNELCODE, FundChannelCode.QRCODE);
			map.put("cleardate", clearDate);
			this.insertDownsysfundtrans(map);
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
