/**
 * 
 */
package com.csii.upp.fundprocess.action.payment;

import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dao.generate.InnerfundtransDAO;
import com.csii.upp.dao.generate.InnerfundtranshistDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.dto.generate.InnerfundtransExample;
import com.csii.upp.dto.generate.Innerfundtranshist;
import com.csii.upp.dto.generate.InnerfundtranshistExample;
import com.csii.upp.fundprocess.action.PayOnlineAction;

/**
 * @author gaoqi
 * @description 确认虚账户支付交易
 *
 */
public class ConfirmedVirtualAcctPayAction extends PayOnlineAction {

	/* (non-Javadoc)
	 * @see com.csii.pe.action.Executable#execute(com.csii.pe.core.Context)
	 */
	@Override
	public void execute(Context context) throws PeException {
		// TODO Auto-generated method stub
		InputFundData input = new InputFundData(context.getDataMap());
		input.setCheckdataflag(FundChannelCode.CORE);
		context.setData(Dict.CHECK_DATA_FLAG, input.getCheckdataflag());
		InnerfundtransExample example = new InnerfundtransExample();
		example.createCriteria().andUppertransnbrEqualTo(input.getOriguppertransnbr());
		List<Innerfundtrans> innerFundTrans = null;
		try {
			innerFundTrans= InnerfundtransDAO.selectByExample(example);
			
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		if(innerFundTrans.isEmpty()){
			InnerfundtranshistExample examplehist = new InnerfundtranshistExample();
			examplehist.createCriteria().andUppertransnbrEqualTo(input.getOriguppertransnbr());
			List<Innerfundtranshist> innerFundTranshist = null;
			try{
				innerFundTranshist=InnerfundtranshistDAO.selectByExample(examplehist);
			}catch(SQLException e){
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
			if(innerFundTranshist.isEmpty()){
				throw new PeException(DictErrors.QUERY_ORIG_NOT_EXISTS);
			}else{
				input.setOriginnertransnbr(innerFundTranshist.get(0).getInnerfundtransnbr());
			}
		}else{
			input.setOriginnertransnbr(innerFundTrans.get(0).getInnerfundtransnbr());
		}
		getCoreService().confirmedVirtualAcctPay(input);
	}
}