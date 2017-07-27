package com.csii.upp.payment.action.query;

import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.CodeTypCd;
import com.csii.upp.constant.PayStatus;
import com.csii.upp.constant.ScanCodeMode;
import com.csii.upp.constant.TransId;
import com.csii.upp.dao.generate.MerbaseinfoDAO;
import com.csii.upp.dao.generate.OnlineorderinfoDAO;
import com.csii.upp.dao.generate.OnlineorderinfohistDAO;
import com.csii.upp.dao.generate.OnlinetransDAO;
import com.csii.upp.dao.generate.OnlinetranshistDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.generate.Merbaseinfo;
import com.csii.upp.dto.generate.Onlineorderinfo;
import com.csii.upp.dto.generate.Onlineorderinfohist;
import com.csii.upp.dto.generate.Onlinetrans;
import com.csii.upp.dto.generate.OnlinetransExample;
import com.csii.upp.dto.generate.Onlinetranshist;
import com.csii.upp.dto.generate.OnlinetranshistExample;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;
/**
 * 查询二维码订单状态获取订单号
 * 
 * @author WY
 *
 */
public class QueryQrCodeStatusAction extends PaymentOnlineAction {

	@SuppressWarnings("unchecked")
	@Override
	public void execute(Context context) throws PeException {
		//校验商户接入类型
		String payAccessType=context.getString(Dict.PAY_ACCESS_TYPE);
		if (StringUtil.isObjectEmpty(payAccessType)) {
			throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.PAY_ACCESS_TYPE });
		}
		if (StringUtil.isObjectEmpty(context.getString(Dict.PRO_BODY))) {
			throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.BODY });
		}
		//商户接入是微信或全部默认先生成微信二维码
		if(StringUtil.isStringEmpty(context.getString(Dict.CODE_TYP_CD))){
			//主扫默认微信
			if(CodeTypCd.WECHATANDALIPAY.equals(payAccessType)
					&&!ScanCodeMode.PASSIVE.equals(context.getString(Dict.SCAN_CODE_MODE))
					&&!TransId.CQCD.equals(context.getString(Dict.TRANS_ID))){
				payAccessType=CodeTypCd.ALIPAY;
			}
			//二维码类型为微信
			if(CodeTypCd.WECHAT.equals(payAccessType)){
				context.setData(Dict.CODE_TYP_CD,CodeTypCd.WECHAT);
				if(ScanCodeMode.PASSIVE.equals(context.getString(Dict.SCAN_CODE_MODE))){
					//被扫校验授权码
					if (StringUtil.isObjectEmpty(context.getString(Dict.AUTH_CODE))) {
						throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.AUTH_CODE });
					}
				}else{
					//微信主扫必填校验字段
					if (StringUtil.isObjectEmpty(context.getString(Dict.PRODUCT_ID))) {
						throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.PRODUCT_ID });
					}
				}
			}
			//二维码类型为支付宝
			else if(CodeTypCd.ALIPAY.equals(payAccessType)){
				context.setData(Dict.CODE_TYP_CD,CodeTypCd.ALIPAY);
				//支付宝必填订单标题
				if (StringUtil.isObjectEmpty(context.getString(Dict.SUBJECT))) {
					throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.SUBJECT });
				}
				//支付宝必填商户名编号
				if (StringUtil.isObjectEmpty(context.getString(Dict.STORE_ID))) {
					throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.STORE_ID });
				}
				if(ScanCodeMode.PASSIVE.equals(context.getString(Dict.SCAN_CODE_MODE))){
					//支付宝必填支付场景
					if (StringUtil.isObjectEmpty(context.getString(Dict.SCENE))) {
						throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.SCENE });
					}
					//被扫校验授权码
					if (StringUtil.isObjectEmpty(context.getString(Dict.AUTH_CODE))) {
						throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.AUTH_CODE });
					}
				}
			}else{
				throw new PeException(DictErrors.SUBMIT_INFO_ERROR);
			}
		}		
		//获取商户号，商户流水号
		String merNbr = StringUtil.toStringAndTrim(context.getString(Dict.MER_NBR));
		String merSeqNo = StringUtil.toStringAndTrim(context.getData(Dict.MER_SEQ_NBR));
		String codeTypeCd=context.getString(Dict.CODE_TYP_CD);
		Date MerDate=DateUtil.DateTimeFormat_To_Date((context.getData(Dict.MER_TRANS_DATE_TIME)));
		List<Map> merSubTransMaps = (List<Map>) context.getData(Dict.MER_TRANS_LIST);
		//校验二级商户是否开通支付宝和微信
		if (StringUtil.isObjectEmpty(merSubTransMaps)) {
			throw new PeException(DictErrors.VALUE_NOT_EMPTY,new Object[] { Dict.MER_TRANS_LIST});
		}
		//取第一条数据
		int count=0;
		for (Iterator iterator = merSubTransMaps.iterator(); iterator
				.hasNext();) {
			count++;
			Map subMap = (Map) iterator.next();
			String mernbr=(String) subMap.get(Dict.SUB_MERCHANT_ID);
			try {
				Merbaseinfo merbaseinfo=MerbaseinfoDAO.selectByPrimaryKey(mernbr);
				if(null!=merbaseinfo){
					if(CodeTypCd.WECHAT.equals(codeTypeCd)){
						if(!"0".equals(merbaseinfo.getWechatproxystatus())){
							throw new PeException(DictErrors.SUB_MER_NOT_THIRD_SYCH);
						}
						context.setData(Dict.WECHATPROXYSTATUS, "0");
						if(count==1){
							context.setData(Dict.SUB_MERCHANT_ID, merbaseinfo.getProxymernbr());
							context.setData(Dict.THIRD_MER_NBR, merbaseinfo.getWechatsubmerchantid());
						}
						
					}
					if(CodeTypCd.ALIPAY.equals(codeTypeCd)){
						if(!"0".equals(merbaseinfo.getAlipayproxystatus())){
							throw new PeException(DictErrors.SUB_MER_NOT_THIRD_SYCH);
						}
						if("0".equals(merbaseinfo.getWechatproxystatus())){
							context.setData(Dict.WECHATPROXYSTATUS, "0");
						}
						context.setData(Dict.ALIPAYPROXYSTATUS, "0");
						if(count==1){
							context.setData(Dict.SUB_MERCHANT_ID, merbaseinfo.getMernbr());
							context.setData(Dict.THIRD_MER_NBR, merbaseinfo.getAlipaymerchantid());
						}
					}
					if(count==1){
						context.setData(Dict.MER_NAME, merbaseinfo.getMershortname());
						
					}
					
				}else{
					throw new PeException(DictErrors.MER_NOT_FOUND_SUB_MER,new Object[]{mernbr});
				}
			} catch (SQLException e) {
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
		}
		if(ScanCodeMode.PASSIVE.equals(context.getString(Dict.SCAN_CODE_MODE))){
			//被扫无需查原订单号
			return;
		}
		//判断订单状态如果无此订单或订单状态明确为不成功，可以直接返回，状态为待扫码需要查询内部资金流水号
		try {
			String status = null;
			String transSeqNbr = null;
			String qrCodeOrderNbr=null;
			Onlineorderinfo order=OnlineorderinfoDAO.selectByPrimaryKey(merNbr, merSeqNo);
			if(order!=null){
				status=order.getPaystatus();
				transSeqNbr=order.getTransseqnbr();
			}else{
				Onlineorderinfohist orderHist = OnlineorderinfohistDAO.selectByPrimaryKey(merSeqNo, merNbr);
				if(orderHist!=null){
					status=orderHist.getPaystatus();
					transSeqNbr=orderHist.getTransseqnbr();
				}
				return;
			}
			if(!StringUtil.isStringEmpty(status)){
				this.throwOrderInfoException(status);
			}
			//如果查询有订单信息，获取原交易的二维码订单号
			if(!StringUtil.isStringEmpty(transSeqNbr)){
				OnlinetransExample example=new OnlinetransExample();
				example.createCriteria().andMerseqnbrEqualTo(merSeqNo).andMernbrEqualTo(merNbr).
				andCodetypcdEqualTo(codeTypeCd).andMertransdateEqualTo(MerDate);
				List<Onlinetrans> onlinetrans=null;
				List<Onlinetranshist> onlinetranshist=null;
				try {
					onlinetrans =OnlinetransDAO.selectByExample(example);
					if(!onlinetrans.isEmpty()){
						qrCodeOrderNbr=onlinetrans.get(0).getQrcodeordernbr();
					}else{
						OnlinetranshistExample examplehis=new OnlinetranshistExample();
						examplehis.createCriteria().andMerseqnbrEqualTo(merSeqNo).andMernbrEqualTo(merNbr).
						andCodetypcdEqualTo(codeTypeCd).andMertransdateEqualTo(MerDate);
						onlinetranshist=OnlinetranshistDAO.selectByExample(examplehis);
						if(!onlinetranshist.isEmpty()){
							qrCodeOrderNbr=onlinetranshist.get(0).getQrcodeordernbr();
						}
						return;
					}
					
				} catch (SQLException e) {
					throw new PeException(DictErrors.TRANS_EXCEPTION);
				}
				context.setData(Dict.QRCODEORDERNBR, qrCodeOrderNbr);
			}
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		
		
	}
	//订单防重复校验
	protected void throwOrderInfoException(String status) throws PeException {
		if (PayStatus.PAY_STATUS_OK.equals(status)) {
			log.info("****************OrderInfo 订单已完成！**********");
			throw new PeException(DictErrors.ORDER_INFO_OK);
		} else if (PayStatus.PAY_STATUS_HAND.equals(status)) {
			log.info("****************OrderInfo 订单处理中！**********");
			throw new PeException(DictErrors.ORDER_INFO_HANDLE);
		} else if (PayStatus.PAY_STATUS_CANCEL.equals(status)) {
			log.info("****************OrderInfo 订单取消！**********");
			throw new PeException(DictErrors.ORDER_INFO_CANCEL);
		}
	}

}
