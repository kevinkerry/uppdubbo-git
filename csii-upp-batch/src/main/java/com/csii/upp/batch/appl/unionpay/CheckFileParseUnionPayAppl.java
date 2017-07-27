package com.csii.upp.batch.appl.unionpay;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.upp.batch.appl.base.BaseCheckFileParseAppl;
import com.csii.upp.constant.ConstUnionPay;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dict.Dict;
import com.csii.upp.util.StringUtil;

/**
 * 银联对账文件下载、解析、入库
 * @author 李超
 *
 */
public class CheckFileParseUnionPayAppl  extends BaseCheckFileParseAppl{
	
	private String fundChannelCd;
	
	@Override
	protected String getFundChannelCd(Map<String, Object> argMaps) {
		fundChannelCd = fundChannelCd != null ? fundChannelCd : StringUtil.parseObjectToString(argMaps.get(Dict.FCNM));
		return fundChannelCd;
	}

	@Override
	protected void insertData(List<Map<String, Object>> rtxnMaps, Map<String, Object> argMaps,String fileName)
			throws PeException {
		Date clearDate = this.getCheckDate(argMaps);
		for (Map<String, Object> map : rtxnMaps) {
			//银联对账文件流水的交易状态都是成功
			map.put(Dict.TRANSSTATUS, TransStatus.SUCCESS);
			map.put(Dict.FUNDCHANNELCODE, fundChannelCd);
			map.put("cleardate", clearDate);
			this.insertDownsysfundtrans(map);
		}
		
	}

	@Override
	protected String getFileType() {
		return ConstUnionPay.FILE_TYPE;
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
