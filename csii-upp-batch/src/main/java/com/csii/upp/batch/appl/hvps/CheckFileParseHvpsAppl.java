package com.csii.upp.batch.appl.hvps;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.upp.batch.appl.base.BaseCheckFileParseAppl;
import com.csii.upp.constant.ConstHvps;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.InnerRtxnTyp;
import com.csii.upp.constant.ProcessState;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dict.Dict;
import com.csii.upp.util.StringUtil;

public class CheckFileParseHvpsAppl extends BaseCheckFileParseAppl {

	private final List<String> successState = Arrays.asList("PR03", "PR04");

	// private final List<String> failureState = Arrays.asList("06O", "07O",
	// "09O", "10O", "13O", "14O", "17O", "19O", "20O", "23O","24O",
	// "25O", "26O", "27O", "32O", "33O", "34O", "35O","38O", "45O",
	// "46O", "47O", "48O");
	// 汇兑业务 退汇
	// private final List<String> preProcRtxn = Arrays.asList("A100", "A105");
	private final List<String> preProcRtxn = Arrays.asList("A105");

	@Override
	protected String getFundChannelCd(Map<String, Object> argMaps) {
		return FundChannelCode.HVPS;
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
			map.put(Dict.FUND_CHANNEL_CD, FundChannelCode.HVPS);
			map.put(Dict.TRANSSTATUS, rtxnState);
			String direction = StringUtil.parseObjectToString(map
					.get(Dict.DIRECTION));
			if ("I".equals(direction)) {
				map.put(Dict.TRANSCODE, InnerRtxnTyp.HvpsDebitRtxn); // 赋值为来帐
			}
			if ("O".equals(direction)) {
				map.put(Dict.TRANSCODE, InnerRtxnTyp.HvpsCreditRtxn); // 赋值为往账
			}
			// 交易类型分区来账、往账和退款：来账和退款交易插入待处理资金流水
			String rtxnTypCd = StringUtil.parseObjectToString(map
					.get(Dict.BUSITYPE));
			if ("I".equals(map.get(Dict.DIRECTION))) {
				map.put(Dict.DOWN_TRANS_NBR,
						map.get(Dict.INNERFUNDTRANSNBR));
				map.put(Dict.DOWNTRANSDATE,
						map.get(Dict.TRANSDATE));
				this.insertOverallRequestReg(map);
				if (preProcRtxn.contains(rtxnTypCd)) {
					if (TransStatus.SUCCESS.equals(StringUtil
							.parseLong(rtxnState))) {
						this.insertPreprocessfundtrans(map, ProcessState.PREPROC);
					}
				} else {
					this.insertDownsysfundtrans(map);
				}

			} else {
				this.insertDownsysfundtrans(map);
			}
		}
	}

	@Override
	protected String getFileType() {
		return ConstHvps.FILE_TYPE;
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
