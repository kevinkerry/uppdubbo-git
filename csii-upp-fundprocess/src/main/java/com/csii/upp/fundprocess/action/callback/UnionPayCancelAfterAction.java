package com.csii.upp.fundprocess.action.callback;

import java.sql.SQLException;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.generate.InnerfundtransDAO;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.dto.router.unionpay.RespDsjyyb;
import com.csii.upp.fundprocess.action.PayOnlineAction;
import com.csii.upp.util.DateUtil;

/**
 * 他行网银退货成功后回调
 * 
 * @author gaoqi
 *
 */
public class UnionPayCancelAfterAction extends PayOnlineAction {

	@Override
	public void execute(Context ctx) throws PeException {
		log.info("UnionPayCancelAfterAction--------UnionNotifation Success");
		
		RespDsjyyb respobj = this.buildRespOjb(ctx);
		String innerrtxnnbr = respobj.getOrderId();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			// 收到银联通知即表示成功
			Innerfundtrans innerfundtrans = InnerfundtransDAO.selectByPrimaryKey(innerrtxnnbr);
			if(TransStatus.SUCCESS.equals(innerfundtrans.getTransstatus())){
				return;
			}
			innerfundtrans.setTransstatus(TransStatus.SUCCESS);
			innerfundtrans.setReturncode(respobj.getRespCode());
			innerfundtrans.setReturnmsg(respobj.getRespMsg());
			innerfundtrans.setDowntransnbr(respobj.getOrigQryId());
			innerfundtrans.setCleardate(DateUtil.DateTimeFormat_To_Date(respobj.getSettleDate(), DateFormatCode.DATE_FORMATTER3));
			InnerfundtransDAO.updateByPrimaryKeySelective(innerfundtrans);
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
	}
}
