package com.csii.upp.fundprocess.action.online;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.extend.InnerfundtransExtendDAO;
import com.csii.upp.dao.generate.InnerfundtransDAO;
import com.csii.upp.dao.generate.OveralltransDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.dto.generate.InnerfundtransExample;
import com.csii.upp.dto.generate.Overalltrans;
import com.csii.upp.fundprocess.action.PayOnlineAction;
import com.csii.upp.service.fundprocess.EAccountService;
/**
 *@ClassName:&{type_name}
 *@Description:消费退货
 *@author :xujin
 *@date：2015-08-13
 */
public class CupsConsumeReturnAction extends PayOnlineAction{

	@Override
	public void execute(Context arg0) throws PeException {
		InputFundData input = new InputFundData(arg0.getDataMap());
		try {
			//获得原总流水信息
			Overalltrans origOveralltrans =(Overalltrans) arg0.getData(Dict.ORIG_OVERRALL_DATA);
			if (origOveralltrans == null) {
				throw new PeException(DictErrors.QUERY_ORIG_NOT_EXISTS);
			}	
			if (TransStatus.SUCCESS.equals(origOveralltrans.getOveralltransstatus())
					||TransStatus.REVOKED.equals(origOveralltrans.getOveralltransstatus())) {
				List<Innerfundtrans> InnerfundtransList = InnerfundtransExtendDAO
						.getInnerfundtransByOveralltransNbr(origOveralltrans.getOveralltransnbr());
				//原消费交易有日间异常处理这样会有2条以上记录存在
				Innerfundtrans eAccountFundRtxn = InnerfundtransList.get(0);
				/**
				 * 消费退货允许部分退货，这里要验证退货总金额不大于原消费金额
				 */
				//获得已退货的交易
				InnerfundtransExample example = new InnerfundtransExample();
				example.createCriteria().andOriginnertransnbrEqualTo(eAccountFundRtxn.getInnerfundtransnbr())
				.andFundchannelcodeEqualTo(FundChannelCode.EACCOUNT)
				.andTransstatusEqualTo(TransStatus.SUCCESS);
				List<Innerfundtrans> returnRtxns = InnerfundtransDAO.selectByExample(example);
				BigDecimal retSumAmt=BigDecimal.ZERO;//已退货总金额
				for (Innerfundtrans item : returnRtxns) {
					retSumAmt=retSumAmt.add(item.getTransamt());
				}
				if(input.getTransamt().add(retSumAmt).compareTo(eAccountFundRtxn.getTransamt())>0){
					throw new PeException(DictErrors.PURC_AMT_LIMIT);
				}
				
				// 判断验证收款账户是否存在（调电子账户查询接口)
				EAccountService service=this.getDBankService(input);
				arg0.setData(Dict.CHECK_CARD_PWD_FLAG, input.getCheckdataflag());
				service.validatePayeeAcctInfo(input);
				input.setOriginnertransnbr(eAccountFundRtxn.getInnerfundtransnbr());
				service.consumeCancel(input);
				// 撤销成功的话 ，更新原交易主流水交易状态为已撤销
				origOveralltrans.setOveralltransstatus(TransStatus.REVOKED);
				OveralltransDAO.updateByPrimaryKeySelective(origOveralltrans);
			} else {
				throw new PeException(DictErrors.REVOKE_TRANS_FAILED);
			}
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
	}

}
