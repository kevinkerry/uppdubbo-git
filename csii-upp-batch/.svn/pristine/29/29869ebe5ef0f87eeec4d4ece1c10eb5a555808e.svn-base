package com.csii.upp.batch.appl.beps;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.upp.batch.appl.base.BaseCheckFileParseAppl;
import com.csii.upp.constant.ConstBeps;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.InnerRtxnTyp;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dict.Dict;
import com.csii.upp.util.StringUtil;

public class CheckFileParseBepsAppl extends BaseCheckFileParseAppl {
	
	private final List<String> successState = Arrays.asList("PR03","PR04");
//	private final List<String> failureState = Arrays.asList("06O", "07O",
//			"09O", "10O", "13O", "14O", "17O", "19O", "20O", "23O","24O",
//			 "25O", "26O", "27O", "32O", "33O", "34O", "35O","38O", "45O",
//			 "46O", "47O", "48O");
	//汇兑业务  退汇
	//private final List<String> preProcRtxn = Arrays.asList("A100", "A105");
	//private final List<String> preProcRtxn = Arrays.asList( ""); //由于汇兑业务在此无法确认是否在支付做过交易，因此无法再这里解决
	@Override
	protected String getFundChannelCd(Map<String, Object> argMaps) {
		return FundChannelCode.BEPS;
	}

	@Override
	protected void insertData(List<Map<String, Object>> rtxnMaps,Map<String, Object> argMaps,String fileName)
			throws PeException {
		for (Map<String, Object> map : rtxnMaps) {
			// 对人行处理状态进行过滤只插入失败和成功的流水
			String rtxnState = StringUtil.parseObjectToString(map
					.get(Dict.TRANSSTATUS));
			//只有PR03-已轧差;PR04-已清算属于成功状态
			if(successState.contains(rtxnState)){
				rtxnState=StringUtil.parseObjectToString(TransStatus.SUCCESS);
			}
			else{
				rtxnState=StringUtil.parseObjectToString(TransStatus.FAILURE);
			}
			map.put(Dict.FUNDCHANNELCODE, FundChannelCode.BEPS);
			map.put(Dict.TRANSSTATUS, rtxnState);
			if("I".equals(StringUtil.parseObjectToString(map.get(Dict.DIRECTION))))
			{
				map.put(Dict.TRANSCODE, InnerRtxnTyp.BepsDebitRtxn); //赋值为来帐
				this.insertOverallRequestReg(map);
			}else if("O".equals(StringUtil.parseObjectToString(map.get(Dict.DIRECTION))))
			{
				map.put(Dict.TRANSCODE,InnerRtxnTyp.BepsCreditRtxn ); //赋值为往账 
			}
			this.insertDownsysfundtrans(map);
		}
	}

	@Override
	protected String getFileType() {
		return ConstBeps.FILE_TYPE;
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
