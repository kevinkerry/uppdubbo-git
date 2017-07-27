package com.csii.upp.payment.action.start;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.pe.service.comm.Transport;
import com.csii.upp.constant.InnerCardFlag;
import com.csii.upp.dao.generate.CardbininfoDAO;
import com.csii.upp.dao.generate.DeptacctinfoDAO;
import com.csii.upp.dao.generate.DoubtfulinfoDAO;
import com.csii.upp.dao.generate.MeracctinfoDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Cardbininfo;
import com.csii.upp.dto.generate.Deptacctinfo;
import com.csii.upp.dto.generate.Doubtfulinfo;
import com.csii.upp.dto.generate.Meracctinfo;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.StringUtil;

/**
 * 电信诈骗验证检查
 * 
 * @author tongzongbing
 * 
 */
public class TelecomCheatAction extends PaymentOnlineAction {
	private Transport transport;
	private boolean flag;
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}
	
	public Transport getTransport() {
		return transport;
	}

	@Override
	public void execute(Context ctx) throws PeException {
		log.info("**************************电信诈骗验证开始******************************");
		if(flag){
			this.send(ctx);
		}
		log.info("**************************电信诈骗验证结束******************************");
	}
	
	public void doubtfulRecord(Context ctx,Deptacctinfo deptAcctInfo,Map map) throws PeException{
		InputPaymentData inputData = new InputPaymentData(ctx.getDataMap());
		Doubtfulinfo data = new Doubtfulinfo();
		data.setMernbr(map.get(Dict.SUB_MERCHANT_ID).toString());
		data.setMerseqnbr(map.get(Dict.SUB_MER_SEQ_NO).toString());
		data.setPayeeacctdeptnbr(deptAcctInfo.getAcctdeptnbr());
		data.setPayeeacctname(deptAcctInfo.getAcctname());
		data.setPayeeacctnbr(deptAcctInfo.getAcctnbr());
		data.setPayeracctdeptnbr(inputData.getPayeracctdeptnbr());
		data.setPayeracctname(inputData.getPayeracctname());
		data.setPayeracctnbr(inputData.getPayeracctnbr());
		try {
			DoubtfulinfoDAO.insert(data);
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_TIMEOUT);
		}
	}
	
	public void send(Context ctx) throws PeException{
		String cardBin = ctx.getData(Dict.PAYER_ACCT_NBR).toString().substring(0, 6);
		Cardbininfo cardBinInfo = null;
		try {
			cardBinInfo = CardbininfoDAO.selectByPrimaryKey(cardBin);
		}  catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		List<Map> transList = (List<Map>) ctx.getData(Dict.MER_TRANS_LIST);
		//判断本行卡他行卡
		if(InnerCardFlag.InnerCardFlag_inner.equals(cardBinInfo.getInnercardflag())){
			for(Map map : transList){
				String merNbr = map.get(Dict.SUB_MERCHANT_ID).toString();
				String merSeqNbr = map.get(Dict.SUB_MER_SEQ_NO).toString();
				if(StringUtil.isStringEmpty(merNbr)){
					throw new PeException(DictErrors.VALUE_NOT_EMPTY,new Object[] { Dict.MER_NBR});
				}
				if(StringUtil.isStringEmpty(merSeqNbr)){
					throw new PeException(DictErrors.VALUE_NOT_EMPTY,new Object[] { Dict.MER_SEQ_NBR});
				}
				this.action(ctx, map);
			}
		}
	}
	
	public void action(Context ctx, Map map) throws PeException{
		InputPaymentData inputData = new InputPaymentData(ctx.getDataMap());
		Deptacctinfo deptAcctInfo = null;
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put(Dict.TRANS_ID, "DXZP");
		Meracctinfo meracctinfo = null;
		try {
			meracctinfo = MeracctinfoDAO.selectByPrimaryKey(map.get(Dict.SUB_MERCHANT_ID).toString());
			if(meracctinfo == null){
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
			String deptNbr = meracctinfo.getMerdevelopdeptnbr();
			String inneracctcfmmode = meracctinfo.getInneracctcfmmode();
			deptAcctInfo = DeptacctinfoDAO.selectByPrimaryKey(deptNbr, inneracctcfmmode);
			if(deptAcctInfo == null){
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
		} catch (SQLException e1) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		if(StringUtil.isStringEmpty(deptAcctInfo.getAcctnbr()) || StringUtil.isStringEmpty(inputData.getPayeracctnbr())){
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		log.info(new StringBuilder().append("交易时间[")
				.append(inputData.getTransTime()).append("]")
				.append("转出账号[")
				.append(inputData.getPayeracctnbr()).append("]")
				.append("转出账户名[")
				.append(inputData.getPayeracctname()).append("]")
				.append("转入账号[")
				.append(deptAcctInfo.getAcctnbr()).append("]")
				.append("转入账号户名[")
				.append(deptAcctInfo.getAcctname()).append("]")
				.append("交易金额[")
				.append(inputData.getTransAmount()).append("]")
				.append("币种[")
				.append(inputData.getCuurrency()).append("]电信诈骗验证===============*******").toString());
		reqMap.put("transTime", inputData.getTransTime());
		reqMap.put("trsferONum", inputData.getPayeracctnbr());
		reqMap.put("trsferINum", deptAcctInfo.getAcctnbr());
		reqMap.put("transAmount", inputData.getTransAmount());
		reqMap.put("cuurrency", inputData.getCuurrency());
		
		Map resultMap = null;
		try {
			resultMap = (Map) this.getTransport().submit(reqMap);
			
		} catch (Exception e) {
			log.error("电信诈骗验证结果：超时", e);
			throw new PeException(DictErrors.TELECOM_NET_ERROR);
		}
		
		if(resultMap.isEmpty()){
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		if("2".equals(resultMap.get("outNumState"))){
			//付款账号涉案记录
			this.doubtfulRecord(ctx,deptAcctInfo,map);
			log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>电信诈骗验证结果：付款账号涉案>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			throw new PeException(DictErrors.PAYER_NBR_IN_BLACK);
		}
		if("2".equals(resultMap.get("inNumState"))){
			//收款账号涉案记录
			this.doubtfulRecord(ctx,deptAcctInfo,map);
			log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>电信诈骗验证结果：收款账号涉案>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			throw new PeException(DictErrors.PAYEE_NBR_IN_BLACK);
		}
		if("1".equals(resultMap.get("inNumState")) || "1".equals(resultMap.get("outNumState"))){
			//可疑账号记录
			this.doubtfulRecord(ctx,deptAcctInfo,map);
			log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>电信诈骗验证结果：可疑>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
	}
}
