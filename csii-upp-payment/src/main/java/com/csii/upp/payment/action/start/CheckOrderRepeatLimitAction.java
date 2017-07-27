package com.csii.upp.payment.action.start;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.TransId;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.constant.YN;
import com.csii.upp.dao.generate.OnlinesubtransDAO;
import com.csii.upp.dao.generate.OnlinesubtranshistDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.generate.Onlinesubtrans;
import com.csii.upp.dto.generate.OnlinesubtransExample;
import com.csii.upp.dto.generate.Onlinesubtranshist;
import com.csii.upp.dto.generate.OnlinesubtranshistExample;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.StringUtil;

/**
 * 修改订单状态为处理中
 * @author WY
 *
 */
public class CheckOrderRepeatLimitAction  extends PaymentOnlineAction {

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Context context) throws PeException {
		log.info(new StringBuilder()
		.append("商户流水[").append(context.getData(Dict.MER_SEQ_NBR)).append("]")
		.append("商户时间[").append(context.getData(Dict.MER_TRANS_DATE_TIME)).append("]")
		.append("交易代码[").append(context.getData(Dict.TRANS_CODE)).append("] 子订单防重复检验").toString());
		@SuppressWarnings("unchecked")
		List<Map> merSubTransMaps = (List<Map>) context.getData(Dict.MER_TRANS_LIST);
		for (Iterator iterator = merSubTransMaps.iterator(); iterator
				.hasNext();) {
			Map subMap = (Map) iterator.next();
			//根据商户号 商户流水号 商户日期查询是否已有该条子订单交易
			String mernbr=StringUtil.parseObjectToString(subMap.get(Dict.SUB_MERCHANT_ID));
			String merseqnbr=StringUtil.parseObjectToString(subMap.get(Dict.SUB_MER_SEQ_NO));
			//Date mertransdate=DateUtil.DateFormat_To_Date(
					//DateUtil.Date_To_DateTimeFormat(DateUtil.Object_To_Date(subMap.get(Dict.SUB_MER_DATE_TIME)), DateFormatCode.DATE_FORMATTER1));
			if(StringUtil.isStringEmpty(mernbr)||StringUtil.isStringEmpty(merseqnbr)){
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
			OnlinesubtransExample example=new OnlinesubtransExample();
			List<String> statusList = new ArrayList<String>(
					Arrays.asList(TransStatus.FAILURE));
			example.createCriteria().andMernbrEqualTo(mernbr).andMerseqnbrEqualTo(merseqnbr).
			andTransstatusNotIn(statusList);
			try {
					List<Onlinesubtrans> onlinesubtrans=OnlinesubtransDAO.selectByExample(example);
					String status = null;
					if(onlinesubtrans.isEmpty()){
						OnlinesubtranshistExample examplehist=new OnlinesubtranshistExample();
						examplehist.createCriteria().andMernbrEqualTo(mernbr).andMerseqnbrEqualTo(merseqnbr).
						andTransstatusNotIn(statusList);
						List<Onlinesubtranshist> onlinesubtranshist=OnlinesubtranshistDAO.selectByExample(examplehist);
						if (!onlinesubtranshist.isEmpty()) {
							status = onlinesubtranshist.get(0).getTransstatus();
						    this.throwOrderInfoException(context, status);
						}
					}else{
						status = onlinesubtrans.get(0).getTransstatus();
						this.throwOrderInfoException(context, status);
					}
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
	
		}
	protected void throwOrderInfoException(Context context, String status) throws PeException {
		// 设置不更新订单
		context.setData(Dict.UPD_ORDERYN, YN.N);
		if (TransStatus.SUCCESS.equals(status)) {
			if(TransId.IPSR.equals(context.getString(Dict.TRANS_ID))){
				log.info("****************OrderInfo 订单处理成功！**********");
				throw new PeException(DictErrors.RETURN_INFO_OK);
			}
			log.info("****************OrderInfo 订单处理成功！**********");
			throw new PeException(DictErrors.ORDER_INFO_OK);
		}else if(TransStatus.ALL_WITHDRAW.equals(status)||TransStatus.SUB_WITHDRAW.equals(status)){
			log.info("****************OrderInfo 订单已退货！**********");
			throw new PeException(DictErrors.ORDER_INFO_OK);
		} else if(TransStatus.INIT.equals(status)|| TransStatus.PROCESSING.equals(status)||TransStatus.TIMEOUT.equals(status)) {
			log.info("****************OrderInfo 订单处理中！**********");
			throw new PeException(DictErrors.ORDER_INFO_HANDLE);
		} else if (TransStatus.REVOKED.equals(status)) {
			log.info("****************OrderInfo 订单取消！**********");
			throw new PeException(DictErrors.ORDER_INFO_CANCEL);
		}
	}

}
