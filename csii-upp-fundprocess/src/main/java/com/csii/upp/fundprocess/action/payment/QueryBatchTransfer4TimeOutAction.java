package com.csii.upp.fundprocess.action.payment;

import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.constant.TranserStatus;
import com.csii.upp.dao.generate.InnerfundtransDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.dto.generate.InnerfundtransExample;
import com.csii.upp.dto.router.core.RespCoreQueryRtxn;
import com.csii.upp.fundprocess.action.PayOnlineAction;
import com.csii.upp.util.StringUtil;


/**
 * @author zhubenle
 * @description 批量转账超时状态交易的查询
 *
 */
public class QueryBatchTransfer4TimeOutAction extends PayOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		InputFundData input = new InputFundData(context.getDataMap());
		try {
			InnerfundtransExample example = new InnerfundtransExample();
			example.createCriteria().andUppertransnbrEqualTo(input.getUppertransnbr());
			List<Innerfundtrans> innerfundtranss = InnerfundtransDAO.selectByExample(example);
			if(innerfundtranss.size() > 0){
				Innerfundtrans innerfundtrans = innerfundtranss.get(0);
				input.setTransdate(innerfundtrans.getTransdate());
				input.setTranstime(innerfundtrans.getTranstime());
				input.setInnerfundtransnbr(innerfundtrans.getInnerfundtransnbr());
				input.setOveralltransnbr(innerfundtrans.getOveralltransnbr());
				RespCoreQueryRtxn resp=null;
				String result= null;
				if(FundChannelCode.CORE.equals(innerfundtrans.getFundchannelcode())){
					resp=this.getCoreService().queryBatchTransferState(input);
					if(TransStatus.SUCCESS.equals(resp.getRtxnstate())){
						if(!StringUtil.isStringEmpty(resp.getOrigSeqNbr())){
							result=TranserStatus.SUCCESS;
						}else{
							result=TranserStatus.FAILURE;
						}
					}
				}else if (FundChannelCode.HVPS.equals(innerfundtrans.getFundchannelcode())) {
					result=this.getHvpsService().queryBatchTransferState(input).getTransStatus();
				}else if (FundChannelCode.BEPS.equals(innerfundtrans.getFundchannelcode())) {
					result=this.getBepsService().queryBatchTransferState(input).getTransStatus();
				}
				context.setData(Dict.TRANS_STATUS, TranserStatus.SUCCESS.equals(result)?TransStatus.SUCCESS:
						(TranserStatus.FAILURE.equals(result)?TransStatus.FAILURE:TransStatus.PROCESSING));
				
			}
		} catch (Exception e) {
			throw new PeException(e);
		}
	}
}