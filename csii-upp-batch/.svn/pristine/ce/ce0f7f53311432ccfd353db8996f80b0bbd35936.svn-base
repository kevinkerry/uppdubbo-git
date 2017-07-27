package com.csii.upp.batch.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.action.BaseAction;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.OveralltransTyp;
import com.csii.upp.constant.SendStatus;
import com.csii.upp.dao.generate.FundchannelsettleDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Fundchannelsettle;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.dto.router.core.RespCoreQueryRtxn;
import com.csii.upp.service.fundprocess.CoreService;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

public class SettleFeeAction extends BaseAction {

	@Override
	public void execute(Context arg0) throws PeException {
		String sendStatus = StringUtil.parseObjectToString(arg0.getData(Dict.SEND_STATUS));
		CoreService coreService = (CoreService) this.getRouterService(FundChannelCode.CORE.toLowerCase());
		InputFundData inputdata = new InputFundData();
		if (SendStatus.DISABLED.equals(sendStatus)) {
			// 查询
			Fundchannelsettle settle = new Fundchannelsettle();
			settle.setSettleseqnbr(arg0.getString(Dict.SETTLE_SEQ_NBR));
			inputdata.setInnerfundtransnbr(arg0.getString(Dict.INNER_FUND_TRANS_NBR));
			inputdata.setTransdate(
					DateUtil.DateTimeFormat_To_Date(arg0.getData(Dict.TRANS_DATE), DateFormatCode.DATE_FORMATTER1));
			RespCoreQueryRtxn output = (RespCoreQueryRtxn) coreService.queryBatchTransferState(inputdata);
			if (!output.isTimeout()) {
				if (output.isSuccess() && output.getOrigSeqNbr() != null) {
					settle.setSendstatus(SendStatus.SUCCESS);
				} else {
					settle.setSendstatus(SendStatus.FAILURE);
				}
				try {
					FundchannelsettleDAO.updateByPrimaryKeySelective(settle);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else if (SendStatus.FAILURE.equals(sendStatus) || SendStatus.UNSEND.equals(sendStatus)) {
			inputdata.setTransid(OveralltransTyp.BTTF);
			inputdata.setTransamt(StringUtil.parseBigDecimal(arg0.getData(Dict.TRANS_AMT)));
			inputdata.setPayeracctnbr(StringUtil.parseObjectToString(arg0.getData(Dict.PAYER_ACCT_NBR)));
			Map<String, String> payeeAcctMap = new HashMap<String, String>();
			payeeAcctMap.put(Dict.PAYEE_ACCT_NBR, StringUtil.parseObjectToString(arg0.getData(Dict.PAYEE_ACCT_NBR)));
			payeeAcctMap.put(Dict.SUB_TRANS_AMT, StringUtil.parseObjectToString(arg0.getData(Dict.TRANS_AMT)));
			List<Map<String, String>> payeeAcctList = new ArrayList<Map<String, String>>();
			payeeAcctList.add(payeeAcctMap);
			inputdata.setPayeeAcctList(payeeAcctList);
			RespSysHead outdata = coreService.settleMerchant(inputdata);
			try {
				Fundchannelsettle record = FundchannelsettleDAO.selectByPrimaryKey(arg0.getString(Dict.SETTLE_SEQ_NBR));
				if (outdata.isSuccess()) {
					record.setSendstatus(SendStatus.SUCCESS);
				} else if (outdata.isFailure()) {
					record.setSendstatus(SendStatus.FAILURE);
				} else if (outdata.isTimeout()) {
					record.setSendstatus(SendStatus.DISABLED);
				}
				record.setMemo(inputdata.getTransnbr());
				FundchannelsettleDAO.updateByPrimaryKey(record);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
