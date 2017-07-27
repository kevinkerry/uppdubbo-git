package com.csii.upp.fundprocess.action.online;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.SettleTyp;
import com.csii.upp.dao.generate.FundchannelsettleDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.generate.Fundchannelsettle;
import com.csii.upp.dto.generate.FundchannelsettleExample;
import com.csii.upp.fundprocess.action.PayOnlineAction;
import com.csii.upp.util.DateUtil;

public class QueryDateTotalAmtAction extends PayOnlineAction {
	@Override
	public void execute(Context context) throws PeException {

		BigDecimal preUnClearAmt = new BigDecimal(0);
		BigDecimal unionPayClearAmt = new BigDecimal(0);
		BigDecimal coreClearAmt = new BigDecimal(0);
		BigDecimal unClearAmt = new BigDecimal(0);
		try {
			Date beginTime = DateUtil.DateFormat_To_Date(DateUtil.Date_To_DateTimeFormat(
					DateUtil.Object_To_Date(context.getString("beginTime")), DateFormatCode.DATE_FORMATTER3));
			Date endTime = DateUtil.DateFormat_To_Date(DateUtil.Date_To_DateTimeFormat(
					DateUtil.Object_To_Date(context.getString("endTime")), DateFormatCode.DATE_FORMATTER3));

			FundchannelsettleExample example = new FundchannelsettleExample();
			example.createCriteria().andCleardateEqualTo(DateUtil.addDate(beginTime, -1))
					.andSettletypEqualTo(SettleTyp.NotClear);
			List<Fundchannelsettle> fundchannelsettles = FundchannelsettleDAO.selectByExample(example);
			if (fundchannelsettles != null && fundchannelsettles.size() > 0) {
				for(Fundchannelsettle fundchannelsettle : fundchannelsettles){
					preUnClearAmt = preUnClearAmt.add(fundchannelsettle.getTransamt());
				}
			}

			example = new FundchannelsettleExample();
			example.createCriteria().andCleardateBetween(beginTime, endTime).andSettletypEqualTo(SettleTyp.UnionPayClear);
			fundchannelsettles = FundchannelsettleDAO.selectByExample(example);
			if (fundchannelsettles != null && fundchannelsettles.size() > 0) {
				for(Fundchannelsettle fundchannelsettle : fundchannelsettles){
					unionPayClearAmt = unionPayClearAmt.add(fundchannelsettle.getTransamt());
				}
			}

			example = new FundchannelsettleExample();
			example.createCriteria().andCleardateBetween(beginTime, endTime).andSettletypEqualTo(SettleTyp.CoreClear);
			fundchannelsettles = FundchannelsettleDAO.selectByExample(example);
			if (fundchannelsettles != null && fundchannelsettles.size() > 0) {
				for(Fundchannelsettle fundchannelsettle : fundchannelsettles){
					coreClearAmt = coreClearAmt.add(fundchannelsettle.getTransamt());
				}
			}
			
			example = new FundchannelsettleExample();
			example.createCriteria().andCleardateBetween(beginTime, endTime).andSettletypEqualTo(SettleTyp.NotClear);
			fundchannelsettles = FundchannelsettleDAO.selectByExample(example);
			if (fundchannelsettles != null && fundchannelsettles.size() > 0) {
				for(Fundchannelsettle fundchannelsettle : fundchannelsettles){
					unClearAmt = unClearAmt.add(fundchannelsettle.getTransamt());
				}
			}

			context.setData(Dict.PRE_UN_CLEAR_AMT, preUnClearAmt);// 上日未清算资金
			context.setData(Dict.UNION_PAY_CLEAR_AMT, unionPayClearAmt);// 本日银联清算资金
			context.setData(Dict.CORE_CLEAR_AMT, coreClearAmt);// 本日核心清算资金
			context.setData(Dict.UN_CLEAR_AMT, unClearAmt);// 未清算资金
		} catch (SQLException e) {
			throw new PeException(e);
		}

	}
}
