package com.csii.upp.payment.action.query;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.NotifyStatus;
import com.csii.upp.constant.NotifyTypCd;
import com.csii.upp.dao.extend.SysinfoExtendDAO;
import com.csii.upp.dao.generate.MernotifiyregDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.generate.Mernotifiyreg;
import com.csii.upp.dto.generate.MernotifiyregExample;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.payment.event.handler.NotifyDZKNResultEvent;
import com.csii.upp.supportor.DefaultSupportor;
/**
 * 定时发送电子口岸通知
 * @author xujin
 *
 */
public class SendMerNotifyAction extends PaymentOnlineAction {

	@Override
	public void execute(Context arg0) throws PeException {
		Date postdate = SysinfoExtendDAO.getSysInfo().getPostdate();
		MernotifiyregExample example = new MernotifiyregExample();
		example.createCriteria().andNotifystatusEqualTo(NotifyStatus.FAILURE).andTransdateEqualTo(postdate)
				.andNotifytypeEqualTo(NotifyTypCd.ElecPortNotify);
		example.setOrderByClause("TRANSSEQNBR asc");
		try {
			List<Mernotifiyreg> list = MernotifiyregDAO.selectByExample(example);
			if (list.isEmpty()) {
				return;
			}
			for(Mernotifiyreg reg:list){
				NotifyDZKNResultEvent event = new NotifyDZKNResultEvent();
				Map paramMap=new HashMap();
				paramMap.put(Dict.INPUT_PAYMENT_DATA, reg);
				event.setScheduleNotify(true);
				event.setParamMap(paramMap);
				event.setNotifyService(this.getNotifyService());
				DefaultSupportor.getEventManager().doService(event);
			}

		} catch (Exception e) {
			log.error(e);
		}
	}

}
