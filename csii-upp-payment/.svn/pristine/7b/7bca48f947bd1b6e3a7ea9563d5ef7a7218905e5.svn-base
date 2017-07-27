package com.csii.upp.payment.action.start;

import java.sql.SQLException;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.PayStatus;
import com.csii.upp.dao.generate.OnlineorderinfoDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.generate.Onlineorderinfo;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 修改订单状态为处理中
 * @author xujin
 *
 */
public class UpdOrderStatusAction  extends PaymentOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		log.info(new StringBuilder()
		.append("支付方式[").append(context.getData(Dict.PAY_TYP_CD)).append("]")
		.append("商户流水[").append(context.getData(Dict.MER_SEQ_NBR)).append("]")
		.append("商户时间[").append(context.getData(Dict.MER_TRANS_DATE_TIME)).append("]")
		.append("交易代码[").append(context.getData(Dict.TRANS_CODE)).append("] 修改订单为处理中!").toString());
		Onlineorderinfo record=new Onlineorderinfo();
		record.setMernbr(context.getString(Dict.MER_NBR));
		record.setMerseqnbr(context.getString(Dict.MER_SEQ_NBR));
		record.setPaystatus(PayStatus.PAY_STATUS_HAND);
		try {
			Onlineorderinfo order=OnlineorderinfoDAO.selectByPrimaryKey(record.getMernbr(), record.getMerseqnbr());
			if(PayStatus.PAY_STATUS_NO.equals(order.getPaystatus())
					||PayStatus.PAY_STATUS_FAIL.equals(order.getPaystatus())){
				if(StringUtil.isStringEmpty(context.getString(Dict.CHANNEL_NBR))){
					context.setData(Dict.CHANNEL_NBR,order.getChannelnbr());
				}
				//网银系统修改商户交易时间,这里需要转换回来
				context.setData(Dict.MER_TRANS_DATE_TIME, DateUtil.Date_To_DateTimeFormat(order.getMertranstime(),DateFormatCode.DATETIME_FORMATTER3));
				OnlineorderinfoDAO.updateByPrimaryKeySelective(record);
			}else{
				this.throwOrderInfoException(context,order.getPaystatus());
			}
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
	}

}
