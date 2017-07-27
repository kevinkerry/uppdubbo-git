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

public class VirtualRefoundTransAction extends PayOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		InputFundData input = new InputFundData(context.getDataMap());
		input.setCheckdataflag(FundChannelCode.CORE);
		context.setData(Dict.CHECK_DATA_FLAG, input.getCheckdataflag());
		InnerfundtransExample example = new InnerfundtransExample();
		example.createCriteria().andUppertransnbrEqualTo(input.getOriguppertransnbr());
		List<Innerfundtrans> innerFundTransList =null;
		List<Innerfundtranshist> innerFundTransHistList = null;
		try {
			innerFundTransList = InnerfundtransDAO.selectByExample(example);
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		//因为允许退货今日之前的交易，所以如果当日innerFundTrans表查询为空，則查询innerFundTransHist表
		if (innerFundTransList.isEmpty()) {
			InnerfundtranshistExample histExample = new InnerfundtranshistExample();
			histExample.createCriteria().andUppertransnbrEqualTo(input.getOriguppertransnbr());
			try {
				innerFundTransHistList = InnerfundtranshistDAO.selectByExample(histExample);
				input.setOriginnertransnbr(innerFundTransHistList.get(0).getInnerfundtransnbr());
				input.setOrigtranstime(innerFundTransHistList.get(0).getTranstime());				
			} catch (SQLException e) {
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
		}else{
			input.setOriginnertransnbr(innerFundTransList.get(0).getInnerfundtransnbr());
			input.setOrigtranstime(innerFundTransList.get(0).getTranstime());
		}
		this.getCoreService().virtualRefoundTrans(input);
	}
}
