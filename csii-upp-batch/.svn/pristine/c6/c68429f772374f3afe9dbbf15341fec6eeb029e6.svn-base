package com.csii.upp.batch.appl.ibps;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.upp.batch.appl.base.BaseCheckFileParseAppl;
import com.csii.upp.constant.ConstIbps;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.InnerRtxnTyp;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dict.Dict;
import com.csii.upp.util.StringUtil;

/**
 * 超级网银对账文件解析
 * 
 * @author 姜星
 * 
 */
public class CheckFileParseIbpsAppl extends BaseCheckFileParseAppl {
	private final List<String> successState = Arrays.asList("PR03", "PR04",
			"PR08");

	// private final List<String> successState = Arrays.asList("0I", "0O");
	// private final List<String> failureState = Arrays.asList("7I", "dI",
	// "9I", "bI", "8I", "7O", "bO", "4O", "dO", "8O");
	// 汇兑
	// private final List<String> preProcRtxn = Arrays.asList();

	@Override
	protected String getFundChannelCd(Map<String, Object> argMaps) {
		return FundChannelCode.IBPS;
	}

	@Override
	protected void insertData(List<Map<String, Object>> rtxnMaps,Map<String, Object> argMaps,
			String fileName) throws PeException {
		for (Map<String, Object> map : rtxnMaps) {
			// 对人行处理状态进行过滤只插入失败和成功的流水
			String rtxnState = StringUtil.parseObjectToString(map
					.get(Dict.TRANSSTATUS));

			// 只有PR03-已轧差;PR04-已清算属于成功状态
			if (successState.contains(rtxnState)) {
				rtxnState = StringUtil.parseObjectToString(TransStatus.SUCCESS);
			} else {
				rtxnState = StringUtil.parseObjectToString(TransStatus.FAILURE);
			}

			map.put(Dict.FUND_CHANNEL_CD, FundChannelCode.IBPS);
			map.put(Dict.TRANSSTATUS, rtxnState);
			if ("I".equals(StringUtil.parseObjectToString(map
					.get(Dict.DIRECTION)))) {
				map.put(Dict.TRANSCODE, InnerRtxnTyp.IbpsDebitRtxn); // 赋值为来帐
				map.put(Dict.DOWN_TRANS_NBR,
						map.get(Dict.INNERFUNDTRANSNBR));
				map.put(Dict.DOWNTRANSDATE,
						map.get(Dict.TRANSDATE));
				this.insertOverallRequestReg(map);
			}
			if ("O".equals(StringUtil.parseObjectToString(map
					.get(Dict.DIRECTION)))) {
				map.put(Dict.TRANSCODE, InnerRtxnTyp.IbpsCreditRtxn); // 赋值为往账
			}
			this.insertDownsysfundtrans(map); // 来帐往账都入下游资金流水

		}
	}

	@Override
	protected String getFileType() {
		return ConstIbps.FILE_TYPE;
	}

	@Override
	protected boolean isDownloadFile() {
		return true;
	}

	@Override
	protected List<String> getCheckFileName(Map<String, Object> argMaps) {
		// TODO Auto-generated method stub
		return null;
	}
}
