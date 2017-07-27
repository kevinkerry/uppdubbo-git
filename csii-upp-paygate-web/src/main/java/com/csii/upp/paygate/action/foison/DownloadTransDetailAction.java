/**
 * 
 */
package com.csii.upp.paygate.action.foison;

import java.util.Map;

import com.csii.pe.action.Action;
import com.csii.pe.action.Executable;
import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.constant.TransTypCd;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqQuerySignInfoList;
import com.csii.upp.paygate.action.PayGateAction;

/**
 * @author lixinyou 
 *  查询交易明细结果下载
 *
 */
public class DownloadTransDetailAction extends PayGateAction {

	@Override
	public void execute(Context context) throws PeException {
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		Map resultMap = this.sendPaymenTransport(new ReqQuerySignInfoList(inputData));
		
		if(!ResponseCode.SUCCESS.equals(resultMap.get(Dict.RESP_CODE))){
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		Map<String,Action> map = this.getActions();
		context.setData(Dict.BEGIN_DATE, inputData.getBeginDate());
		context.setData(Dict.END_DATE, inputData.getEndDate());
		context.setData(Dict.TRANS_LIST, resultMap.get(Dict.TRANS_LIST));
		context.setData("Download", "1");
		if(TransTypCd.PAY.equals(inputData.getTranstypcd())){
			if(map.get("pay") instanceof Action){
				((Executable)map.get("pay")).execute(context);
			}
		}else if(TransTypCd.RETURN.equals(inputData.getTranstypcd())){
			if(map.get("draw") instanceof Action){
				((Executable)map.get("draw")).execute(context);
			}
		}
	}

	public Map<String,Action> getActions() {
		return actions;
	}

	public void setActions(Map<String,Action> actions) {
		this.actions = actions;
	}

	private Map<String,Action> actions;
}
