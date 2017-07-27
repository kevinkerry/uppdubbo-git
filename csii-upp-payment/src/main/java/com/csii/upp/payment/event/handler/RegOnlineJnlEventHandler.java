package com.csii.upp.payment.event.handler;

import java.net.InetAddress;
import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csii.upp.dao.generate.OnlinejnlDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.generate.Onlinejnl;
import com.csii.upp.event.EventHandler;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.StringUtil;
/**
 * 异步登记交易流水
 * @author 徐锦
 *
 */
public class RegOnlineJnlEventHandler implements EventHandler<RegOnlineJnlEvent>{
	protected static Log log = LogFactory.getLog(RegOnlineJnlEventHandler.class);
	
	private String xmlBodyName;
	
	public void setXmlBodyName(String xmlBodyName) {
		this.xmlBodyName = xmlBodyName;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void handler(RegOnlineJnlEvent event) {
		Map paramMap =event.getParamMap();
		Onlinejnl jnl=new Onlinejnl();
		jnl.setTransdate(new Date());
		jnl.setTranscode(StringUtil.parseObjectToString(paramMap.get(Dict.TRANS_CODE)));
		jnl.setRecvdatetime((Date)paramMap.get(Dict.RECV_DATE_TIME));
		jnl.setRespdatetime(new Date());
		jnl.setRespcode(StringUtil.parseObjectToString(paramMap.get(Dict.RESP_CODE)));
		String xmlBody=StringUtil.parseObjectToString(paramMap.get(xmlBodyName));
		jnl.setTransdata(xmlBody);
		jnl.setUppersysnbr(StringUtil.parseObjectToString(paramMap.get(Dict.SYSTEM_CODE)));
		jnl.setTransseqnbr(IDGenerateFactory.generateSeqId());
		try {
			jnl.setIpaddress(InetAddress.getLocalHost().getHostAddress());
			OnlinejnlDAO.insertSelective(jnl);
		} catch (Exception e) {
			jnl.setTransdata(null);
			try {
				OnlinejnlDAO.insertSelective(jnl);
			} catch (Exception e1) {
				log.error("******异步登记交易流水出错：",e);
			}
		}
	}
	
	@Override
	public Class<RegOnlineJnlEvent> getAcceptedEventType() {
		return RegOnlineJnlEvent.class;
	}

}
