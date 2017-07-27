package com.csii.upp.payment.action.post;

import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dao.generate.MeracctinfoDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Meracctinfo;
import com.csii.upp.dto.generate.MeracctinfoExample;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.payment.event.handler.BatchTransferEvent;
import com.csii.upp.supportor.DefaultSupportor;
import com.csii.upp.util.StringUtil;

/**
 * 批量转账
 * 
 * @author zhubenle
 * 
 */
public class BatchTransferPaymentAction extends PaymentOnlineAction {

	@Override
	public void execute(Context ctx) throws PeException {
		InputPaymentData paymentData = new InputPaymentData(ctx.getDataMap());
		String mernbr=null;
		String setAcct=null;
		String transerDeptNbr=null;
		mernbr=paymentData.getMernbr();
		List<Meracctinfo> Meracctinfos=null;
		if(!StringUtil.isStringEmpty(mernbr)){
			MeracctinfoExample examplemac=new MeracctinfoExample();
			examplemac.createCriteria().andSupermernbrEqualTo(mernbr);
			try {
				Meracctinfos=MeracctinfoDAO.selectByExample(examplemac);
			} catch (SQLException e) {
				throw new PeException(DictErrors.DATABASE_EXCEPTION);
			}
			if(Meracctinfos.size()>0){
				setAcct=Meracctinfos.get(0).getSettleacctnbr();
				transerDeptNbr=Meracctinfos.get(0).getMerdevelopdeptnbr();
				ctx.setData(Dict.TRANSER_DEPT_NBR, transerDeptNbr);
			/*	if(!setAcct.equals(StringUtil.parseObjectToString(ctx.getDataMap().get(Dict.SETTLE_ACCT_NBR)))){
					throw new PeException("付款账户与商户结算账号不匹配");
				}*/
			}else{
				throw new PeException("商户账务信息不存在");
			}
		}else{
			throw new PeException("商户信息不存在");
		} 
		BatchTransferEvent event = new BatchTransferEvent();
		event.setParamMap(ctx.getDataMap());
		event.setFdpsService(this.getFDPSService());
		DefaultSupportor.getEventManager().doService(event);
	}
}
