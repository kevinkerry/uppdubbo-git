package com.csii.upp.batch.appl.cnaps2;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.upp.batch.appl.base.BaseCheckFileParseAppl;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.ProcessState;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dict.Dict;
import com.csii.upp.util.StringUtil;

/**
 * 二代对账文件解析
 * 
 * @author 徐锦
 * 
 */
public class CheckFileParseCnaps2Appl extends BaseCheckFileParseAppl {

	private final List<String> successState = Arrays.asList("PR03", "PR04");
	private final List<String> failureState = Arrays.asList("PR08", "PR09",
			"PR21", "PR22", "PR23", "PR24", "PR25", "PR32", "PR91", "PRB9");
	//待确认交易类型
	private final List<String> preProcRtxn = Arrays.asList("LZ", "TK");

 

	@Override
	protected String getFundChannelCd(Map<String, Object> argMaps) {
		return FundChannelCode.CNAPS2;
	}

	@Override
	protected void insertData(List<Map<String, Object>> rtxnMaps,Map<String, Object> argMaps,String fileName)
			throws PeException {
		for (Map<String, Object> map : rtxnMaps) {
			//对人行处理状态进行过滤只插入失败和成功的流水
			String rtxnState=StringUtil.parseObjectToString(map.get(Dict.TRANSSTATUS));
			
			//只有PR03-已轧差;PR04-已清算属于成功状态
			if(successState.contains(rtxnState)){
				rtxnState=StringUtil.parseObjectToString(TransStatus.SUCCESS);
			}else if(failureState.contains(rtxnState)){
				rtxnState=StringUtil.parseObjectToString(TransStatus.FAILURE);
			}else{
				continue;
			}
			map.put(Dict.TRANSSTATUS, rtxnState);
			//交易类型分区来账、往账和退款：来账和退款交易插入待处理资金流水
			String rtxnTypCd=StringUtil.parseObjectToString(map.get(Dict.TRANSCODE));
			if(preProcRtxn.contains(rtxnTypCd)){
				if(TransStatus.SUCCESS.equals(StringUtil.parseLong(rtxnState))){
					this.insertPreprocessfundtrans(map,ProcessState.PREPROC);
				}
			}else{
				this.insertDownsysfundtrans(map);
			}
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
