package com.csii.upp.paygate.action;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.csii.pe.channel.stream.IdentityResolver;
import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.pe.core.SessionUpdatableContext;
import com.csii.pe.service.comm.Transport;
import com.csii.upp.action.BaseAction;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.ReqSysHead;
import com.csii.upp.dto.router.paym.ReqQueryIntegral;
import com.csii.upp.dto.router.paym.ReqValidateSms;
import com.csii.upp.util.StringUtil;

import edu.emory.mathcs.backport.java.util.Collections;

/**
 * 交易处理基类
 * 
 * @author xujin
 * 
 */
public abstract class PayGateAction extends BaseAction {
	@Autowired
	private IdentityResolver dubboConsumerIdResolver;



	private Transport getPaymentTransport() {
		return (Transport) getService(FundChannelCode.PAYM.toLowerCase());
	}
	


	private Transport getfundTransport() {
		return (Transport) getService(FundChannelCode.FDPS.toLowerCase());
	}
   
	private Transport getBatchTransport() {
		return (Transport) getService(FundChannelCode.BATCH.toLowerCase());
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
	public Map sendPaymenTransport(ReqSysHead request) throws PeException {

		Map resultMap = null;
		try {
			Map<String, Object> requestMap=this.getObjectMapMarshaller().marshall(request);
			String trsIdStr=dubboConsumerIdResolver.getIdentity(requestMap);
			requestMap.put(Dict.SERVC_ID, trsIdStr);
			resultMap= (Map)getPaymentTransport().submit(requestMap);
		} catch (Exception e) {
			log.error("发送Payment超时", e);
			// 超时处理
			resultMap=new HashMap<String, Object>();
			resultMap.put(Dict.RESP_CODE, ResponseCode.TIMEOUT);
			resultMap.put(Dict.RESP_MESSAGE, "通讯超时，交易结果未明");
		}
		return resultMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map sendfundTransport(ReqSysHead request) throws PeException {
		Map resultMap = null;
		try {
			Map requestMap=this.getObjectMapMarshaller().marshall(request);
			String trsIdStr=dubboConsumerIdResolver.getIdentity(requestMap);
			requestMap.put(Dict.SERVC_ID, trsIdStr);
			resultMap = (Map) getfundTransport().submit(requestMap);
		} catch (Exception e) {
			log.error("发送FundProcess超时", e);
			// 超时处理
			resultMap=new HashMap();
			resultMap.put(Dict.RESP_CODE, ResponseCode.TIMEOUT);
			resultMap.put(Dict.RESP_MESSAGE, "通讯超时，交易结果未明");
		}
		return resultMap;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map sendBatchTransport(ReqSysHead request) throws PeException {
		Map resultMap = null;
		try {
			resultMap = (Map) getBatchTransport().submit(
					this.getObjectMapMarshaller().marshall(request));
		} catch (Exception e) {
			log.error("发送Batch超时", e);
			// 超时处理
			resultMap=new HashMap();
			resultMap.put(Dict.RESP_CODE, ResponseCode.TIMEOUT);
			resultMap.put(Dict.RESP_MESSAGE, "通讯超时，交易结果未明");
		}
		return  resultMap;
	}

	/**
	 * 验证应答状态：如果不是正常的就抛出异常
	 * @param resultMap
	 * @throws PeException
	 */
	@SuppressWarnings("rawtypes")
	protected void valRespStatus(Map resultMap) throws PeException {
		String respCode=StringUtil.parseObjectToString(resultMap.get(Dict.RESP_CODE));
		if(!ResponseCode.SUCCESS.equals(respCode)){
			throw new PeException(DictErrors.ERROR_CONTENT,
					new Object[] { StringUtil.parseObjectToString(resultMap.get(Dict.RESP_MESSAGE))});
		}
	}
	/**
	 * 短信验证码验证请求
	 * @param context
	 * @throws PeException
	 */
	protected Map validateSms(Context context)  throws PeException
	{
		InputPaygateData inputData=new InputPaygateData(context.getDataMap());
		Map hostMap = this.sendPaymenTransport(new ReqValidateSms(inputData));
		return hostMap;
	}
	
	/**
	 * 手机wap密码键盘顺序组装
	 * @param context
	 */
	protected String convertPassword(String pwd,Map<String, Character> number){
		if(StringUtil.isStringEmpty(pwd)){
			return pwd;
		}
        StringBuilder password = new StringBuilder();
		for (Map.Entry<String, Character> entry : number.entrySet()) {
			password.append(entry.getValue());
		}
		return password.append(pwd).toString();
	}
	
	/**
	 * 验证卡状态信息
	 * @param resultMap
	 * @throws PeException
	 */
	protected void validateCardStatusInfo(Map resultMap) throws PeException {
		String respCode = (String) resultMap.get(Dict.RESP_CODE);
		String payerCardTypCd = (String) resultMap.get(Dict.PAYER_CARD_TYP_CD);
		if (!ResponseCode.SUCCESS.equals(respCode)) {
			return;
		}
		String cardStatCd = StringUtil.parseObjectToString(resultMap
				.get(Dict.CARD_STATUS));// 卡状态
		String respMsg=null;
		/*if (payerCardTypCd.equals(CardTypCd.DEBIT)) {
			String payerAcctStatusd = StringUtil.parseObjectToString(resultMap
					.get(Dict.PAYER_ACCT_STATUS));// 账户状态
			String acctLossStatusd = StringUtil.parseObjectToString(resultMap
					.get(Dict.ACCT_LOSS_STATUS));// 账户挂失状态
			String acctStatusWordd = StringUtil.parseObjectToString(resultMap
					.get(Dict.ACCT_STATUS_WORD));// 账户状态字
			String cardStatusWordd = StringUtil.parseObjectToString(resultMap
					.get(Dict.CARD_STATUS_WORD));//卡状态字
			if ("2".equals(payerAcctStatusd)) {
				respMsg="借记卡：该账户为注销账户";
			}else if ("3".equals(payerAcctStatusd)) {
				respMsg="借记卡：该账户为新账户";
			}else if (!"1".equals(acctLossStatusd)
					||cardStatusWordd.charAt(2) != '1'
					||cardStatusWordd.charAt(3) != '1') {
				respMsg="借记卡：该账户或卡已挂失";
			}else if ('0'!=acctStatusWordd.charAt(0)
					||'0'!=acctStatusWordd.charAt(1)) {
				respMsg="借记卡：该账户全部冻结";
			}else if ('0'!=acctStatusWordd.charAt(2)
					|| '0'!=cardStatusWordd.charAt(0)) {
				respMsg="借记卡：该账户或卡全部止付";
			}else if (!"1".equals(cardStatCd)) {
				respMsg="借记卡：该卡卡状态异常";
			}else if ('0'!=cardStatusWordd.charAt(1)) {
				respMsg="借记卡：该卡为伪冒卡";
			}else if ('0'!=acctStatusWordd.charAt(4)) {
				respMsg="借记卡：该账户质押";
			}else if ('0'!=acctStatusWordd.charAt(5)) {
				respMsg="借记卡：该账户为资信证明";
			}else if ('0'!=acctStatusWordd.charAt(6)) {
				respMsg="借记卡：该账户为不动户";
			}
		} else if (payerCardTypCd.equals(CardTypCd.CREDIT)) {
			if (StringUtil.isStringEmpty(cardStatCd)){
				respMsg=null;
			}else if ("A".equals(cardStatCd)) {
				respMsg="信用卡：该卡未激活";
			} else if ("D".equals(cardStatCd)) {
				respMsg="信用卡：该账户冻结";
			} else if ("H".equals(cardStatCd)) {
				respMsg="信用卡：该账户止付";
			} else if ("H1".equals(cardStatCd)) {
				respMsg="信用卡：该账户资金控制";
			} else if ("H2".equals(cardStatCd)) {
				respMsg="信用卡：该账户逾期分期止付";
			} else if ("I".equals(cardStatCd)) {
				respMsg="信用卡：该卡为伪冒卡";
			} else if ("L1".equals(cardStatCd)) {
				respMsg="信用卡：该卡客户挂失";
			} else if ("L2".equals(cardStatCd)) {
				respMsg="信用卡：该卡银行主动挂失";
			} else if ("L3".equals(cardStatCd)) {
				respMsg="信用卡：该卡为换卡而挂失";
			} else if ("NX".equals(cardStatCd)) {
				respMsg="信用卡：该卡到期未续卡";
			} else if ("Q".equals(cardStatCd)) {
				respMsg="信用卡：该账户已销户";
			} else if ("T".equals(cardStatCd)) {
				respMsg="信用卡：该卡已销卡";
			} else if ("V".equals(cardStatCd)) {
				respMsg="信用卡：该卡新卡激活，旧卡失效";
			} else if ("W".equals(cardStatCd)) {
				respMsg="信用卡：该账户呆账核销";
			} else if ("WQ".equals(cardStatCd)) {
				respMsg="信用卡：该账户呆账核销清户";
			} else if ("X".equals(cardStatCd)) {
				respMsg="信用卡：该账户停计息费";
			} else if ("X1".equals(cardStatCd)) {
				respMsg="信用卡：该账户分期停计息费";
			}
		}*/
		if(!StringUtil.isStringEmpty(respMsg)){
			resultMap.put(Dict.RESP_CODE,ResponseCode.FAILURE);
			resultMap.put(Dict.RESP_MESSAGE,respMsg);
		}
	}
	
	/**
     * 查询商户积分，获取商户积分结果集。
	 * @throws PeException 
     */
	public List<Map<String, Object>> QueryPointRecords(Context context) throws PeException{
		List<Map<String, Object>> PointList = new ArrayList<Map<String, Object>>();
		//判断是否是积分支付，不是则不发查询
		String payTypeCdStr=context.getString(Dict.PAY_TYPE_CD_STR);
		if(!StringUtil.isStringEmpty(payTypeCdStr)){
			if(payTypeCdStr.indexOf("5") < 0){
				return PointList;
			}
		}
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		Map result = this.sendPaymenTransport(new ReqQueryIntegral(inputData));		
		if(!ResponseCode.SUCCESS.equals(result.get(Dict.RESP_CODE))){
			//查询失败
			context.setData(Dict.POINT_RESP_FLAG, "1");
			return PointList;
		}
		PointList = (List<Map<String, Object>>) result.get(Dict.POINT_RECORDS);
		BigDecimal AmtLimitThisTime = BigDecimal.valueOf(Double.parseDouble(context.getString(Dict.TRANS_AMT))*0.5);
		for(int i=PointList.size()-1;i >= 0;i--){
			BigDecimal IntegralTotal = StringUtil.parseBigDecimal(PointList.get(i).get(Dict.INTEGRAL_TOTAL));
			BigDecimal AmtThisTime = IntegralTotal;
			if(AmtThisTime.compareTo(new BigDecimal("0")) == 0){
				PointList.remove(i);
			}else{
				//取本此可抵扣金额的最大值和积分可抵扣金额两者中的较大者
				if (AmtThisTime.compareTo(AmtLimitThisTime) == 1){
					AmtThisTime = AmtLimitThisTime;
				}
				//四舍五入
				AmtThisTime = AmtThisTime.setScale(0, RoundingMode.HALF_UP).setScale(2);
				PointList.get(i).put(Dict.AMT_THIS_TIME, AmtThisTime);
				//修改总积分抵扣金额精度  0
				PointList.get(i).put(Dict.INTEGRAL_TOTAL,IntegralTotal.setScale(2, RoundingMode.HALF_UP));
			}
		}
		if(null==PointList || PointList.size()==0){
			//客户名下没有积分
			context.setData(Dict.POINT_RESP_FLAG, "2");
			return PointList;
		}
		listMapSort(PointList);
		return PointList;
	}
	
	/**
	 * 对积分结果集的List<Map>进行排序。
	 * 按照本次可抵扣金额从大到小进行排序，如果本次可抵扣金额相同，则按照行社机构号进行排序。
	 */
	private void listMapSort(List<Map<String,Object>> list){
		Collections.sort(list,new Comparator<Map<String,Object>>(){
			@Override
			public int compare(Map<String,Object> o1,Map<String,Object> o2){
				BigDecimal data1 = StringUtil.parseBigDecimal(o1.get(Dict.AMT_THIS_TIME));
				BigDecimal data2 = StringUtil.parseBigDecimal(o2.get(Dict.AMT_THIS_TIME));
				int branchNo1 = Integer.parseInt(o1.get(Dict.BRANCH_NO).toString());
				int branchNo2 = Integer.parseInt(o2.get(Dict.BRANCH_NO).toString());
				if(data1.compareTo(data2) == 1){
					return -1;
				}else if(data1.compareTo(data2) == 0){
					if(branchNo1 > branchNo2){
						return 1;
					}else{
						return -1;
					}
				}else{
					return 1;
				}
			}
		});
	}
	public void validateTimeStampToken(Context context) throws PeException {
		String timeStampToken = context.getString("timeStampToken");
		String timeStampTokenSession = (String) context.getSessionAttribute("timeStampToken");
		if(StringUtil.isStringEmpty(timeStampTokenSession) || !timeStampTokenSession.equals(timeStampToken)){
			((SessionUpdatableContext)context).setSessionAttribute("timeStampToken", timeStampToken);
		}else {
			throw new PeException("重复提交错误");
		}
	}
	
	//校验表单重复提交手机端
	public boolean validateTimeStampTokenMobile(Context context) throws PeException {
		String timeStampToken = context.getString("timeStampToken");
		String timeStampTokenSession = (String) context.getSessionAttribute("timeStampToken");
		if(StringUtil.isStringEmpty(timeStampTokenSession) || !timeStampTokenSession.equals(timeStampToken)){
			((SessionUpdatableContext)context).setSessionAttribute("timeStampToken", timeStampToken);
			return true;
		}else {
			context.setData(Dict.RESP_MESSAGE, "重复提交错误");
			context.setState(99999);
			return false;
		}
	}
	protected String createCodeByte(String codeUrl) throws PeException{
		/*Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, 0); 
		BitMatrix matrix = null;
		byte[] buffer = null;
		String base64=null;
		try {
			matrix = new MultiFormatWriter().encode(codeUrl, BarcodeFormat.QR_CODE, 110, 110,hints);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(matrix, "png", stream);
			buffer= stream.toByteArray();
			BASE64Encoder encoder =new BASE64Encoder();
		    base64=encoder.encode(buffer);
			} catch (Exception e) {
			
		}*/
		return null;
	}
}
