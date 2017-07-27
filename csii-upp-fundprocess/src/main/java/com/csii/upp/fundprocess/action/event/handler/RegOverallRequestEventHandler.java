package com.csii.upp.fundprocess.action.event.handler;

import java.net.InetAddress;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csii.upp.dao.generate.OverallrequestregDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Overallrequestreg;
import com.csii.upp.event.EventHandler;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.BeanUtils;
import com.csii.upp.util.DateUtil;

/**
 * 异步登记总交易流水
 * 
 * @author 徐锦
 *
 */
public class RegOverallRequestEventHandler implements EventHandler<RegOverallRequestEvent> {
	protected static Log log = LogFactory.getLog(RegOverallRequestEventHandler.class);

	@Override
	public void handler(RegOverallRequestEvent event) {
		Map paramMap = event.getParamMap();
		try {
			Overallrequestreg record = new Overallrequestreg();
			String postDate = (String) paramMap.get(Dict.POST_DATE);
			record.setTransdate(DateUtil.DateFormat_To_Date(postDate));
			record.setIpaddress(InetAddress.getLocalHost().getHostAddress());
			record.setUppertransnbr((String) paramMap.get(Dict.REQ_SEQ_NO));
			record.setUppersysnbr((String) paramMap.get(Dict.CHNL_ID));
			record.setUppertransdate(DateUtil.DateFormat_To_Date(paramMap.get(Dict.REQ_DATE)));
			InputFundData input = new InputFundData(paramMap);
			String snap = BeanUtils.beanToXmlString(input);
			record.setUpperregsnap(snap);
			record.setOverallreqnbr(IDGenerateFactory.generateSeqId());
			OverallrequestregDAO.insertSelective(record);
		} catch (Exception e) {
			log.error("******异步登记总交易流水：", e);
		}
	}

	@Override
	public Class<RegOverallRequestEvent> getAcceptedEventType() {
		return RegOverallRequestEvent.class;
	}

}
