package com.csii.upp.fundprocess.action.online;

import java.sql.SQLException;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.YN;
import com.csii.upp.dao.generate.InnerfundtransDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.fundprocess.action.PayOnlineAction;

/**
 * 人行代收后半部分(返回成功后入账)
 * 
 * @author WHD
 *
 */
public class PbcCollectionAfterAction extends PayOnlineAction {

	@Override
	public void execute(Context ctx) throws PeException {
		String innerrtxnnbr = ctx.getString("Innerfundtrans");
		try {
			Innerfundtrans Innerfundtrans = InnerfundtransDAO.selectByPrimaryKey(innerrtxnnbr);
			Map<String, Object> map = this.getObjectMapMarshaller().marshall(Innerfundtrans);
			map.put("rtxndate", ctx.getData(Dict.TRANS_DATE));
			InputFundData input = new InputFundData(map);
			String successFlag = ctx.getString("SUCCESSFLAG");
			if (successFlag.equals(YN.Y)) {
				RespSysHead deposite = getDBankService(input).rtdtcr(input);
				ctx.setData(Dict.CHECK_CARD_PWD_FLAG, FundChannelCode.PBC);
				if (deposite.isSuccess()) {

				}
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

}
