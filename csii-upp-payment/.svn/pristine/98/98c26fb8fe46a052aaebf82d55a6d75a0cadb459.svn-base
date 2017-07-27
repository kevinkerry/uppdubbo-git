package com.csii.upp.payment.action.query;

import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.constant.TransTypCd;
import com.csii.upp.dao.generate.OnlinesubtransferDAO;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Onlinesubtransfer;
import com.csii.upp.dto.generate.OnlinesubtransferExample;
import com.csii.upp.dto.router.fundprocess.RespQueryBatchTransfer4TimeOut;
import com.csii.upp.payment.action.PaymentOnlineAction;

public class QueryTransferAction extends PaymentOnlineAction{

	public void execute(Context context) throws PeException {
		String transcode=PaymTransCode.BatchTransfer;
		String transtycd=TransTypCd.WTH;
		String transstatus=TransStatus.PROCESSING;
		try{
		OnlinesubtransferExample example=new OnlinesubtransferExample();
		example.createCriteria().andTranscodeEqualTo(transcode).andTranstypcdEqualTo(transtycd)
			.andTransstatusEqualTo(transstatus);
		List<Onlinesubtransfer> onlinesubtransfers=OnlinesubtransferDAO.selectByExample(example);
		if (onlinesubtransfers.size() > 0 && onlinesubtransfers.get(0) != null) {
			for(Onlinesubtransfer onlinesubtransfer:onlinesubtransfers){
				InputPaymentData input = new InputPaymentData();
				input.setTransseqnbr(onlinesubtransfer.getSubtransseqnbr());
				RespQueryBatchTransfer4TimeOut respQBTT = (RespQueryBatchTransfer4TimeOut) this.getFDPSService()
						.queryBatchTransfer4TimeOut(input);
				if(TransStatus.SUCCESS.equals(respQBTT.getTransStatus())){
					onlinesubtransfer.setTransstatus(TransStatus.SUCCESS);
				}else if (TransStatus.FAILURE.equals(respQBTT.getTransStatus())) {
					onlinesubtransfer.setTransstatus(TransStatus.FAILURE);
				}
				OnlinesubtransferDAO.updateByPrimaryKey(onlinesubtransfer);
			}
		}
		}catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
	}
	
}
