package com.csii.upp.service;

import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.csii.pe.channel.stream.IdentityResolver;
import com.csii.pe.core.PeException;
import com.csii.pe.service.Service;
import com.csii.pe.service.comm.Transport;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.constant.RouterResult;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.router.ReqSysHead;
import com.csii.upp.dto.router.RespAppHead;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.marshaller.ObjectMapMarshaller;
import com.csii.upp.util.StringUtil;

/**
 * 服务类基类
 * 
 * @author 徐锦
 *
 */
public abstract class BasePayService implements Service{
	protected final Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IdentityResolver dubboConsumerIdResolver;
	
	/**
	 * 日间异常最大处理次数
	 */
	private Long expHandleMaxTimes;
	/**
	 * 报文映射配置文件路径
	 */
	private Properties mappingConfig;
	/**
	 * 报文发送组件
	 */
	private Transport transport;
	@Autowired
	private ObjectMapMarshaller objectMapMarshaller;
	public ObjectMapMarshaller getObjectMapMarshaller() {
		return objectMapMarshaller;
	}

	public Long getExpHandleMaxTimes() {
		return expHandleMaxTimes;
	}

	public void setExpHandleMaxTimes(Long expHandleMaxTimes) {
		this.expHandleMaxTimes = expHandleMaxTimes;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	public Transport getTransport() {
		return transport;
	}
	
	public void setMappingConfig(String configPath) throws Exception {
		mappingConfig = new Properties();
		mappingConfig.load(getClass().getResourceAsStream(configPath));
	}
	
	/**
	 * 发送请求数据到目的系统
	 * 
	 * @param request
	 *            请求数据
	 * @param outputClazz
	 *            响应实体类
	 * @return
	 * @throws PeException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected <T extends RespSysHead> T send(ReqSysHead request,
			Class<T> outputClazz) throws PeException {
		if (StringUtil.isObjectEmpty(request)) {
			throw new PeException("Input object is null");
		}
		try {
			Map requestMap=objectMapMarshaller.marshall(request);
			String trsIdStr=dubboConsumerIdResolver.getIdentity(requestMap);
			requestMap.put(Dict.SERVC_ID, trsIdStr);
			return (T) objectMapMarshaller.unMarshall(outputClazz, (Map) this.getTransport()
					.submit(requestMap),mappingConfig);
		} catch (Throwable e) {
			log.error("rev info error:", e);
			// 超时处理
			T output = null;
			try {
				output = outputClazz.newInstance();
				output.setResult(RouterResult.TIMEOUT);
				output.setRtxnstate(TransStatus.TIMEOUT);
				output.setReturnmsg(e.getMessage());
			} catch (Exception e1) {
				log.error("rev info error:", e1);
				throw new PeException("Create Object:" + outputClazz
						+ "Failed. ", e1);
			}
			return output;
		}
	}
	
	protected void formatException(RespAppHead output) throws PeException {
		if(output.isTimeout()){
			throw new PeException(DictErrors.TRANS_TIMEOUT);
		}else if(output.isFailure()){
			if (ResponseCode.DEBITCARDNOTEXIST.equals(output.getReturncode())){
				throw new PeException(DictErrors.DEBIT_CARD_NBR_NOT_EXIST);
			}
			if (ResponseCode.CREDITCARDNOTEXIST.equals(output.getReturncode())){
				throw new PeException(DictErrors.CREDIT_CARD_NBR_NOT_EXIST);
			}
			if (ResponseCode.POINTLESS.equals(output.getReturncode())){
				throw new PeException(DictErrors.CUS_POINT_LESS);
			}
			if (ResponseCode.CLIENTNOERROR.equals(output.getReturncode())){
				throw new PeException(DictErrors.CUS_INFOL_ERRRO);
			}
			throw new PeException(DictErrors.ERROR_CONTENT, new Object[] {output.getReturnmsg()});
		}
	}
	
}
