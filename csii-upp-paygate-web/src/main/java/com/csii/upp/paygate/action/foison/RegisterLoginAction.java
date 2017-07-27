package com.csii.upp.paygate.action.foison;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.pe.validation.ValidationException;
import com.csii.upp.constant.CardTypCd;
import com.csii.upp.constant.InnerCardFlag;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.constant.TransId;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqConfirmCancelSignInfo;
import com.csii.upp.dto.router.paym.ReqQueryCardFlag;
import com.csii.upp.dto.router.paym.ReqQueryReOtherPayStatus;
import com.csii.upp.dto.router.paym.ReqRegistOtherPay;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

public class RegisterLoginAction extends PayGateAction {
	private boolean ignoreCase;

	public boolean isIgnoreCase() {
		return ignoreCase;
	}

	public void setIgnoreCase(boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}
	@Override
	public void execute(Context context) throws PeException {
		//增加页面个时间戳标志
		long timeStampToken =  DateUtil.getCurrentDateTime().getTime();
		context.setData("timeStampToken", timeStampToken);
		
		// 先验证图形验证码
		String token = (String) context.getSessionAttribute("_ImgTokenName");
		String imageTokenId = (String) context.getData("_vTokenName");
		String transId=context.getString(Dict.TRANS_ID);
		if ( token == null || imageTokenId == null) {
			if(StringUtil.isStringEmpty(transId)){
				context.setState(10);
				throw new ValidationException("validation.input_mistake_token");
				
			}
			if(transId.equals(TransId.IPEM)){
				context.setData(Dict.RESP_MESSAGE, "validation.input_mistake_token");
				context.setState(10);
				return;
			}else{
				throw new ValidationException("validation.input_mistake_token");
			}
		}
		if (this.ignoreCase) {
			if (!imageTokenId.equalsIgnoreCase(token)) {
				if(StringUtil.isStringEmpty(transId)){
					context.setState(10);
					throw new ValidationException("validation.input_mistake_token");
				}
				if(transId.equals(TransId.IPEM)){
					context.setData(Dict.RESP_MESSAGE, "validation.input_mistake_token");
					context.setState(10);
					return;
				}else{
					throw new ValidationException("validation.input_mistake_token");
				}
			}
		} else {
			if (!imageTokenId.equals(token)) {
				if(StringUtil.isStringEmpty(transId)){
					context.setState(10);
					throw new ValidationException("validation.input_mistake_token");
				}
				if(transId.equals(TransId.IPEM)){
					context.setData(Dict.RESP_MESSAGE, "validation.input_mistake_token");
					context.setState(10);
					return;
				}else{
					throw new ValidationException("validation.input_mistake_token");
				}
			}
		}
		// 组装查询卡信息报文
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		queryCardFlag(inputData);
		if (PayTypCd.INTEL.equals(inputData.getPaytypcd())) {
			RegisterOther(context,inputData);
		} else {
			RegisterInner(context,inputData);
		}
	}

	// TODO 跨行智能支付注册
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void RegisterOther(Context context,	InputPaygateData inputData) throws PeException {
		inputData.setTransId(TransId.OQP1);
		Map queryResultMap = this.sendPaymenTransport(new ReqQueryReOtherPayStatus(inputData));
		String openStatus = (String) queryResultMap.get(Dict.OPEN_STATUS);
		//有记录，注册或冻结
		if ("2".equals(openStatus)) {
			queryResultMap.put(Dict.RESP_MESSAGE,"该卡已注册");
			context.setDataMap(queryResultMap);
			context.setState(10);
			return;
		}else if("1".equals(openStatus)){
			queryResultMap.put(Dict.RESP_MESSAGE,"签约状态为冻结，不可做该操作");
			context.setDataMap(queryResultMap);
			context.setState(10);
			return;
		}else if("3".equals(openStatus)){
			queryResultMap.put(Dict.RESP_MESSAGE,"签约关系已存在，您可以再次点击选择重新注册！");
			context.setDataMap(queryResultMap);
			context.setState(10);
			return;
		}
		//无记录或者银联未开通
		inputData.setFrontCallBackUrl("CLPA.do");
		Map resultMap = this.sendPaymenTransport(new ReqRegistOtherPay(inputData));
		if (ResponseCode.SUCCESS.equals(resultMap.get(Dict.RESP_CODE))) {
			context.setState(3);
			String returnFrom = StringUtil.parseObjectToString(resultMap.get(Dict.RETURN_FORM));
			context.setData(Dict.RETURN_FORM, returnFrom);
		} else {
			context.setDataMap(resultMap);
			context.setState(10);
		}

	}

	// TODO 丰收e支付注册
	public void RegisterInner(Context context,InputPaygateData inputData) throws PeException {

		// 查询输入、本地、核心手机号是否匹配，进行下一步操作
		Map hostMap = this.sendPaymenTransport(new ReqConfirmCancelSignInfo(inputData));
		// 验证卡状态信息
		this.validateCardStatusInfo(hostMap);
		String respCode = (String) hostMap.get(Dict.RESP_CODE);
		// 根据卡类型跳转不同的页面
		String payerCardTypCd = (String) hostMap.get(Dict.PAYER_CARD_TYP_CD);
		if (!ResponseCode.SUCCESS.equals(respCode)) {
			// 如果payment返回不成功 判断是否是特定的错误码
			if (ResponseCode.EXITDATA.equals(respCode)) {
				// 如果是跳转到通知
				context.setDataMap(hostMap);
				context.setState(2);
				return;
			} else {
				context.setState(10);
			}

		} else {
			// 返回成功
			// 判断借记卡和贷记卡
			if (CardTypCd.DEBIT.equals(payerCardTypCd)) {
				context.setState(0);
			} else if (CardTypCd.CREDIT.equals(payerCardTypCd)) {
				Calendar cal = Calendar.getInstance();
				int currentYear = Integer.valueOf(String.valueOf(cal.get(Calendar.YEAR)).substring(2, 4));
				List<String> yearList = new ArrayList<String>();
				for (int i = 0; i < 20; i++) {
					yearList.add(String.valueOf((currentYear + i) % 100));
				}
				context.setData("YearList", yearList);
				context.setState(1);
			}
		}
		context.setDataMap(hostMap);
		context.setData(Dict.CUST_CIF_NBR, hostMap.get(Dict.CUST_CIF_NBR));
		context.setData(Dict.PAYER_ACCT_DEPT_NBR, hostMap.get(Dict.PAYER_ACCT_DEPT_NBR));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public InputPaygateData queryCardFlag(InputPaygateData FlagMap) throws PeException {
		// 查询卡标识——它行,本行
		@SuppressWarnings("rawtypes")
		Map cardFlagMap = this.sendPaymenTransport(new ReqQueryCardFlag(FlagMap));
		if (InnerCardFlag.InnerCardFlag_other.equals(cardFlagMap.get(Dict.INNER_CARD_FLAG)))
			FlagMap.setPaytypcd(PayTypCd.INTEL);
		else
			FlagMap.setPaytypcd(PayTypCd.FOSION);
		return FlagMap;
	}
	
}
