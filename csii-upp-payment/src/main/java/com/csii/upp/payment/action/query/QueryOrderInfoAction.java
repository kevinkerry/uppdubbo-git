package com.csii.upp.payment.action.query;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dao.generate.MerbaseinfoDAO;
import com.csii.upp.dao.generate.OnlinetransDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.generate.Merbaseinfo;
import com.csii.upp.dto.generate.Onlinetrans;
import com.csii.upp.payment.action.PaymentOnlineAction;
/**
 * @author qgs
 *
 */
public class QueryOrderInfoAction extends PaymentOnlineAction{

	@Override
	public void execute(Context context) throws PeException {
		String transSeqNbr = context.getString(Dict.TRANS_SEQ_NBR);
		try {
			Onlinetrans record = OnlinetransDAO.selectByPrimaryKey(transSeqNbr);
			String mernbr = record.getMernbr();
			Merbaseinfo mer = MerbaseinfoDAO.selectByPrimaryKey(mernbr);
			String merName = mer.getMername();
			
			context.setData(Dict.MERCHANT_NAME, merName);
			context.setData(Dict.TRANS_AMT, record.getTransamt());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private BigDecimal maxTimes;

	public BigDecimal getMaxTimes() {
		return maxTimes;
	}

	public void setMaxTimes(BigDecimal maxTimes) {
		this.maxTimes = maxTimes;
	}
}
