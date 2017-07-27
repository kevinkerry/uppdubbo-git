package com.csii.upp.fundprocess.action.callback;

import java.sql.SQLException;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.ConstUnionPay;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.generate.InnerfundtransDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.dto.router.unionpay.RespDsjyyb;
import com.csii.upp.fundprocess.action.PayOnlineAction;
/**
 * 银联代付后半部分(返回成功后入账)
 * @author WHD
 *
 */
public class UnionPayDFAfterAction extends PayOnlineAction {

	@Override
	public void execute(Context ctx) throws PeException {
		 
		RespDsjyyb respobj = this.buildRespOjb(ctx);
		
		String innerrtxnnbr = respobj.getOrderId();
		try {
			if (ConstUnionPay.STATUS_SUCCESS.equals(respobj.getRespCode()))  
			{
				Innerfundtrans Innerfundtrans = InnerfundtransDAO.selectByPrimaryKey(innerrtxnnbr);
				Innerfundtrans.setTransdate(respobj.getDownrtxndate());
				Innerfundtrans.setTransstatus(TransStatus.SUCCESS);
				InnerfundtransDAO.updateByPrimaryKeySelective(Innerfundtrans);
				//用于更新总交易流水
				ctx.setData(Dict.OVERALL_TRANS_NBR, Innerfundtrans.getOveralltransnbr());
			}
		    
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
	}
}

