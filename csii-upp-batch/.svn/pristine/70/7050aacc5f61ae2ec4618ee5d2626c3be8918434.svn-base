package com.csii.upp.batch.event.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csii.upp.constant.CurrencyCode;
import com.csii.upp.constant.ErrorState;
import com.csii.upp.constant.ErrorTyp;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.OveralltransTyp;
import com.csii.upp.constant.SendStatus;
import com.csii.upp.dao.generate.BatchcheckerrorDAO;
import com.csii.upp.dao.generate.FundchannelsettleDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Batchcheckerror;
import com.csii.upp.dto.generate.Fundchannelsettle;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.event.EventHandler;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.StringUtil;

public class TransferDeptFeeHandler implements EventHandler<TransferDeptFeeEvent> {
	protected static Log log = LogFactory.getLog(TransferDeptFeeHandler.class);

	@Override
	public void handler(TransferDeptFeeEvent event) {
		try {
			Fundchannelsettle fundchannelsettle = event.getFundchannelsettle();
			InputFundData inputdata = new InputFundData();
			inputdata.setTransid(OveralltransTyp.BTTF);
			inputdata.setTransamt(fundchannelsettle.getTransamt());
			inputdata.setPayeracctnbr(fundchannelsettle.getPayersettleacctnbr());
			Map<String, String> payeeAcctMap = new HashMap<String, String>();
			payeeAcctMap.put(Dict.PAYEE_ACCT_NBR, StringUtil.parseObjectToString(fundchannelsettle.getPayeesettleacctnbr()));
			payeeAcctMap.put(Dict.SUB_TRANS_AMT, StringUtil.parseObjectToString(fundchannelsettle.getTransamt()));
			List<Map<String, String>> payeeAcctList = new ArrayList<Map<String, String>>();
			payeeAcctList.add(payeeAcctMap);
			inputdata.setPayeeAcctList(payeeAcctList);
			RespSysHead outdata = event.getCoreService().settleMerchant(inputdata);
			Fundchannelsettle record = FundchannelsettleDAO.selectByPrimaryKey(fundchannelsettle.getSettleseqnbr());
			if (outdata.isSuccess()) {
				record.setSendstatus(SendStatus.SUCCESS);
			} else if (outdata.isFailure()) {
				record.setSendstatus(SendStatus.FAILURE);
			} else if (outdata.isTimeout()) {
				record.setSendstatus(SendStatus.DISABLED);
			}
			record.setMemo(inputdata.getTransnbr());
			if(!outdata.isSuccess()){
				FundchannelsettleDAO.updateByPrimaryKey(record);// 清算金额手续费金额转账超时或者失败则增加日终差错
				Batchcheckerror recordError = new Batchcheckerror();
				recordError.setTransdate(fundchannelsettle.getTransdate());
				recordError.setTranscode(OveralltransTyp.BTTF);
				recordError.setInnerfundtransnbr(inputdata.getInnerfundtransnbr());
				recordError.setPayeracctnbr(inputdata.getPayeracctnbr());
				recordError.setPayeeacctnbr( StringUtil.parseObjectToString(fundchannelsettle.getPayeesettleacctnbr()));
				recordError.setTransamt(fundchannelsettle.getTransamt());
				recordError.setTransstatus(outdata.getRtxnstate());
				recordError.setFundchannelcode(FundChannelCode.CORE);
				recordError.setErrorstatus(ErrorState.PRECHECK);
				recordError.setCurrencycd(CurrencyCode.CNY);
				recordError.setErrornbr(IDGenerateFactory.generateSeqId());
				if(!StringUtil.isStringEmpty(fundchannelsettle.getSettletyp())){
					if(outdata.isFailure())
						recordError.setCheckerrortyp(ErrorTyp.QRCODEFEEAMTFAIL);
					else
						recordError.setCheckerrortyp(ErrorTyp.QRCODEFEEAMTTIMEOUT);
				}else{
					if(outdata.isFailure())
						recordError.setCheckerrortyp(ErrorTyp.QRCODETRANSFERAMTFAIL);
					else
						recordError.setCheckerrortyp(ErrorTyp.QRCODETRANSFERAMTTIMEOUT);
				}
				BatchcheckerrorDAO.insertSelective(recordError);
			}
			
		} catch (Exception e) {
			log.error("TransferDeptFeeHandler ERROR", e);
		}
	}
	
	@Override
	public Class<TransferDeptFeeEvent> getAcceptedEventType() {
		return TransferDeptFeeEvent.class;
	}

}
