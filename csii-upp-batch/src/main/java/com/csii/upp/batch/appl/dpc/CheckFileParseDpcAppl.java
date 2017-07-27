package com.csii.upp.batch.appl.dpc;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.upp.batch.appl.base.BaseCheckFileParseAppl;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.InnerRtxnTyp;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dict.Dict;
import com.csii.upp.util.StringUtil;

/**
 * 大同城对账文件解析
 * 
 * @author DreamsHunter
 * 
 **/
public class CheckFileParseDpcAppl extends BaseCheckFileParseAppl {

	/*
	 * 00 录入待复核01 录入已删除02 提出待应答03 发送成功04 已退汇05 发送失败06 待入账07 已入账08 已扣款09 已拒绝10
	 * 已冲正11 排队16 已入账91 已冲正
	 */
	private final List<String> successState = Arrays.asList("03", "07");

	// private final List<String> failureState = Arrays.asList("00", "01", "02",
	// "04", "05", "06", "07", "08", "09", "10", "16", "91");
	// 待确认交易类型
	// private final List<String> preProcRtxn = Arrays.asList("LZ", "TK");

	@Override
	protected String getFundChannelCd(Map<String, Object> argMaps) {
		return FundChannelCode.DPC;
	}

	@Override
	protected void insertData(List<Map<String, Object>> rtxnMaps,Map<String, Object> argMaps,String fileName)throws PeException {
		for (Map<String, Object> map : rtxnMaps) {
			// 对人行处理状态进行过滤只插入失败和成功的流水(如要求他们日此提供 ，此步骤忽略)
			String rtxnState = StringUtil.parseObjectToString(map.get(Dict.TRANSSTATUS));

			// 只有PR03-已轧差;PR04-已清算属于成功状态
			if (successState.contains(rtxnState)) {
				rtxnState = StringUtil.parseObjectToString(TransStatus.SUCCESS);
			} else {
				rtxnState = StringUtil.parseObjectToString(TransStatus.FAILURE);
			}

			map.put(Dict.FUND_CHANNEL_CD, FundChannelCode.DPC);
			map.put(Dict.TRANSSTATUS, rtxnState);
			// 交易类型分区来账、往账和退款：来账和退款交易插入待处理资金流水
			String direction = StringUtil.parseObjectToString(map.get(Dict.DIRECTION));
			//TODO 往来标志需要写
			if ("往来标志？".equals(direction)) {
				// if
				// (RtxnState.SUCCESS.equals(StringUtil.parseLong(rtxnState))) {
				// this.insertPreprocessfundtrans(map);
				// }
				map.put(Dict.TRANSCODE, InnerRtxnTyp.DpcDebitRtxn); // 赋值为来帐
			} else if ("往来标志？".equals(direction)) {
				map.put(Dict.TRANSCODE, InnerRtxnTyp.DpcPPCreditRtxn); // 赋值为往账
			}
			this.insertDownsysfundtrans(map);
		}
	}

	@Override
	protected List<String> getCheckFileName(Map<String, Object> argMaps) {
		return this.getInnerCheckApply(this.getFundChannelCd(argMaps));
	}

	@Override
	protected String getFileType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean isDownloadFile() {
		return true;
	}
}
