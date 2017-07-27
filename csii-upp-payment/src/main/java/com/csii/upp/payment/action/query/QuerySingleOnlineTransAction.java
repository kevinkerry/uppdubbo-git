package com.csii.upp.payment.action.query;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.InteralFlag;
import com.csii.upp.constant.MerPlatformNbr;
import com.csii.upp.constant.OrderInfoStatus;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.QueryOrderStatus;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.constant.TransTypCd;
import com.csii.upp.dao.generate.MeracctinfoDAO;
import com.csii.upp.dao.generate.OnlineorderinfoDAO;
import com.csii.upp.dao.generate.OnlineorderinfohistDAO;
import com.csii.upp.dao.generate.OnlinesubtransDAO;
import com.csii.upp.dao.generate.OnlinetransDAO;
import com.csii.upp.dao.generate.OnlinetranshistDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.generate.Meracctinfo;
import com.csii.upp.dto.generate.Onlineorderinfo;
import com.csii.upp.dto.generate.Onlineorderinfohist;
import com.csii.upp.dto.generate.Onlinesubtrans;
import com.csii.upp.dto.generate.OnlinesubtransExample;
import com.csii.upp.dto.generate.Onlinetrans;
import com.csii.upp.dto.generate.OnlinetransExample;
import com.csii.upp.dto.generate.Onlinetranshist;
import com.csii.upp.dto.generate.OnlinetranshistExample;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 单笔查询
 * 
 * @author zgb
 *
 */
public class QuerySingleOnlineTransAction extends PaymentOnlineAction {
	@Override
	public void execute(Context context) throws PeException {
		log.info("********单笔查询开始********");
	/*	InputPaymentData input = new InputPaymentData(context.getDataMap());
*/
		log.info(new StringBuilder().append("商户号[").append(context.getData(Dict.MER_NBR)).append("]").append("原交易流水号[")
				.append(context.getData(Dict.MER_SEQ_NBR)).append("]").append("原交易日期[")
				.append(context.getData(Dict.MER_TRANS_DATE)).append("交易代码[").append(context.getData(Dict.TRANS_CODE))
				.append("******单笔查询*******"));

		String merNbr = (String) context.getData(Dict.MER_NBR);
		if (StringUtil.isObjectEmpty(merNbr)) {
			log.error("一级商户id为空");
			throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.MER_NBR });
		}

		String MerSeqNbr = (String) context.getData(Dict.MER_SEQ_NBR);
		if (StringUtil.isObjectEmpty(MerSeqNbr)) {
			log.error("商户流水号为空");
			throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.MER_SEQ_NBR });
		}

		Date MerDate = DateUtil.DateFormat_To_Date(context.getData(Dict.MER_TRANS_DATE));
		if (StringUtil.isObjectEmpty(MerDate)) {
			log.error("原交易日期不能为空");
			throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.ORIG_MER_DATE });
		}
		DecimalFormat decimal = new DecimalFormat("0.00");
		// 查询订单信息表 如果订单信息表中不存在就直接返回
		try {
			String Transseqnbr = null;
			Date Transdate = null;
			String Paystatus = null;
			Onlineorderinfohist orderinfoHt = null;
			Onlineorderinfo orderinfo = OnlineorderinfoDAO.selectByPrimaryKey(merNbr, MerSeqNbr);
			if (null == orderinfo) {
				orderinfoHt = OnlineorderinfohistDAO.selectByPrimaryKey(MerSeqNbr, merNbr);
				if (orderinfoHt == null) {
					// 订单信息不存在
					context.setData(Dict.TRANS_STATUS, QueryOrderStatus.ORDERNOTEXIST);
					context.setData(Dict.RESP_MESSAGE,"单笔实时查询，查无此订单！");
					context.setData(Dict.RESP_CODE, ResponseCode.SUCCESS);
					return;
				}
				// ht;
				if (!StringUtil.isStringEmpty(orderinfoHt.getPaystatus())) {
					Paystatus = orderinfoHt.getPaystatus();
				}

			}
			// ot;
			if (!StringUtil.isStringEmpty(orderinfo.getPaystatus())) {
				Paystatus = orderinfo.getPaystatus();
			}
			if (OrderInfoStatus.PAY_STATUS_HAND.equals(Paystatus)) {
				// 订单处理中
				context.setData(Dict.TRANS_STATUS, QueryOrderStatus.ORDERINHAND);
				context.setData(Dict.RESP_MESSAGE,"单笔实时查询，订单处理中！");
				context.setData(Dict.RESP_CODE, ResponseCode.SUCCESS);
				// 超时查证
				return;
			}
			if (OrderInfoStatus.PAY_STATUS_CANCEL.equals(Paystatus)) {
				context.setData(Dict.TRANS_STATUS, QueryOrderStatus.ORDERCANCEL);
				context.setData(Dict.RESP_MESSAGE,"单笔实时查询，订单已取消！");
				context.setData(Dict.RESP_CODE, ResponseCode.SUCCESS);
				return;
			}
			if (OrderInfoStatus.PAY_STATUS_NO.equals(Paystatus)) {
				context.setData(Dict.TRANS_STATUS, QueryOrderStatus.ORDERTOBEPAY);
				context.setData(Dict.RESP_MESSAGE,"单笔实时查询，订单待支付！");
				context.setData(Dict.RESP_CODE, ResponseCode.SUCCESS);
				return;
			}
			if (OrderInfoStatus.PAY_STATUS_FAIL.equals(Paystatus)) {
				context.setData(Dict.TRANS_STATUS, QueryOrderStatus.ORDERFAIL);
				context.setData(Dict.RESP_MESSAGE,"单笔实时查询，订单失败！");
				context.setData(Dict.RESP_CODE, ResponseCode.SUCCESS);
				return;
			}
			// 只有订单信息表中数据成功 ，进行下一步操作
			// 当前交易表和历史交易表
			List<Onlinetrans> resultlist = null;
			List<Onlinetranshist> resulttranshist = null;
			OnlinetransExample example = new OnlinetransExample();
			OnlinetranshistExample htexample = new OnlinetranshistExample();
			OnlinesubtransExample subexample =new OnlinesubtransExample();
			// 找到不为空的Transseqnbr和Transdate
			if (!StringUtil.isStringEmpty(orderinfo.getTransseqnbr())) {
				Transseqnbr = orderinfo.getTransseqnbr();
			} else {
				Transseqnbr = orderinfoHt.getTransseqnbr();
			}
			// orderinfo和orderinfoHt
			if (!StringUtil.isObjectEmpty(orderinfo.getTransdate())) {
				Transdate = orderinfo.getTransdate();
			} else {
				Transdate = orderinfoHt.getTransdate();
			}
			if (StringUtil.isObjectEmpty(Transseqnbr) || StringUtil.isObjectEmpty(Transdate)) {
				// 使用商户传入数据查询 orderinfo,orderinfoHt部分数据为空

				// 商户传入数据Mernbr,Merseqnbr,Mertransdate(交易时间=原交易时间)
				example.createCriteria().andMernbrEqualTo(merNbr).andMerseqnbrEqualTo(MerSeqNbr)
						.andMertransdateEqualTo(MerDate);
				List<Onlinetrans> onlinetrans = OnlinetransDAO.selectByExample(example);
				if (null != onlinetrans) {
					Onlinetrans result = null;
					result = filterSuccessOrder(onlinetrans);
 
					context.setData(Dict.MER_SEQ_NBR, result.getMerseqnbr());
					context.setData(Dict.TRANS_SEQ_NO, result.getTransseqnbr());
					context.setData(Dict.MER_NBR, result.getMernbr());
					context.setData(Dict.TRANS_DATE, DateUtil.Date_To_DateTimeFormat(result.getTransdate(), DateFormatCode.DATE_FORMATTER3));
					context.setData(Dict.CLEAR_DATE, DateUtil.Date_To_DateTimeFormat(result.getCleardate(), DateFormatCode.DATE_FORMATTER3));
					context.setData(Dict.TRANS_AMT, decimal.format(result.getTransamt()));
					context.setData(Dict.TRANS_AMT1, decimal.format(result.getRefundedamt()));
					//二维码增加字段
					if(PayTypCd.OTHACTSCAN.equals(result.getPaytypcd())
							||PayTypCd.OTHPASSCAN.equals(result.getPaytypcd())){
						context.setData(Dict.PAY_TYPE,result.getScancodemode());
						context.setData(Dict.PAY_ACCESS_TYPE,result.getCodetypcd());
						context.setData(Dict.RECEIPT_AMOUNT, decimal.format(result.getReceiptamt()));
					}
					context.setDataMap(context.getDataMap());
				} else {
					htexample.createCriteria().andMernbrEqualTo(merNbr).andMerseqnbrEqualTo(MerSeqNbr)
							.andMertransdateEqualTo(MerDate);
					List<Onlinetranshist> htOnlinetranshists = OnlinetranshistDAO.selectByExample(htexample);
					Onlinetranshist resulthist = null;
					resulthist = filterSuccessOrderhist(htOnlinetranshists);
					context.setData(Dict.MER_SEQ_NO, resulthist.getMerseqnbr());
					context.setData(Dict.TRANS_SEQ_NO, resulthist.getTransseqnbr());
					context.setData(Dict.MERCHANT_ID, resulthist.getMernbr());
					context.setData(Dict.TRANS_DATE, DateUtil.Date_To_DateTimeFormat(resulthist.getTransdate(), DateFormatCode.DATE_FORMATTER3));
					context.setData(Dict.CLEAR_DATE, DateUtil.Date_To_DateTimeFormat(resulthist.getCleardate(), DateFormatCode.DATE_FORMATTER3));
					context.setData(Dict.TRANS_AMT, decimal.format(resulthist.getTransamt()));
					context.setData(Dict.TRANS_AMT1, decimal.format(resulthist.getRefundedamt()));
					//二维码增加字段
					if(PayTypCd.OTHACTSCAN.equals(resulthist.getPaytypcd())
							||PayTypCd.OTHPASSCAN.equals(resulthist.getPaytypcd())){
						context.setData(Dict.PAY_TYPE,resulthist.getScancodemode());
						context.setData(Dict.PAY_ACCESS_TYPE,resulthist.getCodetypcd());
						context.setData(Dict.RECEIPT_AMOUNT, decimal.format(resulthist.getReceiptamt()));
					}
					context.setDataMap(context.getDataMap());

				}
			} else {
				example.createCriteria().andTransseqnbrEqualTo(Transseqnbr).andTransdateEqualTo(Transdate);
				resultlist = OnlinetransDAO.selectByExample(example);
				htexample.createCriteria().andTransseqnbrEqualTo(Transseqnbr).andTransdateEqualTo(Transdate);
				resulttranshist = OnlinetranshistDAO.selectByExample(htexample);
				if (resultlist.size() == 0) {
					if (resulttranshist.size() == 0) {
						context.setData(Dict.TRANS_STATUS, QueryOrderStatus.ORDERNOTEXIST);
						context.setData(Dict.RESP_MESSAGE,"单笔实时查询，查无此订单！");
						context.setData(Dict.RESP_CODE, ResponseCode.SUCCESS);
						
						log.info("订单信息不存在,单笔查询结束");
						return;

					}
					
				}
			}
			Meracctinfo meracctinfo = MeracctinfoDAO.selectByPrimaryKey(merNbr);
			if(MerPlatformNbr.FMRT.equals(meracctinfo.getMerplatformnbr())){
				String transtypd =null;
				if(resultlist.size()>0){
					Onlinetrans info = resultlist.get(0);
					transtypd = info.getTranstypcd();
					//区分退货和支付
					if(TransTypCd.PAY.equals(transtypd)){
						context.setData(Dict.ACCOUNT,info.getPayeracctnbr());
						context.setData(Dict.ACCT_NAME,info.getPayeracctname());
					}else if(TransTypCd.RETURN.equals(transtypd)){
						context.setData(Dict.ACCOUNT,info.getPayeeacctnbr());
						context.setData(Dict.ACCT_NAME,info.getPayeeacctname());
					}else{
						//
					}
					context.setData(Dict.MER_SEQ_NO, info.getMerseqnbr());
					context.setData(Dict.TRANS_SEQ_NO, info.getTransseqnbr());
					context.setData(Dict.MERCHANT_ID, info.getMernbr());
					context.setData(Dict.TRANS_DATE, DateUtil.Date_To_DateTimeFormat(info.getTransdate(), DateFormatCode.DATE_FORMATTER3));
					context.setData(Dict.CLEAR_DATE, DateUtil.Date_To_DateTimeFormat(info.getCleardate(), DateFormatCode.DATE_FORMATTER3));
					context.setData(Dict.TRANS_AMT, decimal.format(info.getTransamt()));
					context.setData(Dict.TRANS_AMT1, decimal.format(info.getRefundedamt()));
					context.setData(Dict.TRANS_STATUS,QueryOrderStatus.ORDERSUCCESS);
					context.setDataMap(context.getDataMap());
					
				}else if(resulttranshist.size()>0){
					Onlinetranshist infoist = resulttranshist.get(0);
					transtypd = infoist.getTranstypcd();
					//区分退货和支付
					if(TransTypCd.PAY.equals(transtypd)){
						context.setData(Dict.ACCOUNT,infoist.getPayeracctnbr());
						context.setData(Dict.ACCT_NAME,infoist.getPayeracctname());
					}else if(TransTypCd.RETURN.equals(transtypd)){
						context.setData(Dict.ACCOUNT,infoist.getPayeeacctnbr());
						context.setData(Dict.ACCT_NAME,infoist.getPayeeacctname());
					}else{
						//
					}
					context.setData(Dict.MER_SEQ_NO, infoist.getMerseqnbr());
					context.setData(Dict.TRANS_SEQ_NO, infoist.getTransseqnbr());
					context.setData(Dict.MERCHANT_ID, infoist.getMernbr());
					context.setData(Dict.TRANS_DATE, DateUtil.Date_To_DateTimeFormat(infoist.getTransdate(), DateFormatCode.DATE_FORMATTER3));
					context.setData(Dict.CLEAR_DATE, DateUtil.Date_To_DateTimeFormat(infoist.getCleardate(), DateFormatCode.DATE_FORMATTER3));
					context.setData(Dict.TRANS_AMT, decimal.format(infoist.getTransamt()));
					context.setData(Dict.TRANS_STATUS,QueryOrderStatus.ORDERSUCCESS);
					context.setData(Dict.TRANS_AMT1, decimal.format(infoist.getRefundedamt()));
					context.setDataMap(context.getDataMap());
				}
			}else{
				String transtypd =null;
				if(resultlist.size()>0){
					Onlinetrans info = resultlist.get(0);
					transtypd = info.getTranstypcd();
				
					context.setData(Dict.MER_SEQ_NO, info.getMerseqnbr());
					context.setData(Dict.TRANS_SEQ_NO, info.getTransseqnbr());
					context.setData(Dict.MERCHANT_ID, info.getMernbr());
					context.setData(Dict.TRANS_DATE, DateUtil.Date_To_DateTimeFormat(info.getTransdate(), DateFormatCode.DATE_FORMATTER3));
					context.setData(Dict.CLEAR_DATE, DateUtil.Date_To_DateTimeFormat(info.getCleardate(), DateFormatCode.DATE_FORMATTER3));
					context.setData(Dict.TRANS_AMT, decimal.format(info.getTransamt()));
					context.setData(Dict.TRANS_AMT1, decimal.format(info.getRefundedamt()));
					context.setData(Dict.PAY_TYPE,info.getScancodemode());
					//二维码增加字段
					if(PayTypCd.OTHACTSCAN.equals(info.getPaytypcd())
							||PayTypCd.OTHPASSCAN.equals(info.getPaytypcd())){
						context.setData(Dict.PAY_TYPE,info.getScancodemode());
						context.setData(Dict.PAY_ACCESS_TYPE,info.getCodetypcd());
						context.setData(Dict.RECEIPT_AMOUNT, decimal.format(info.getReceiptamt()));
					}
					//积分支付通知商户内容
					if(InteralFlag.YES.equals(info.getInteralflag())){
						context.setData(Dict.INTERAL_FLAG, info.getInteralflag());
						subexample.createCriteria().andTransseqnbrEqualTo(Transseqnbr).andTransdateEqualTo(Transdate);
						List<Onlinesubtrans> MerTransDetailList = new ArrayList<Onlinesubtrans>();
						List<Map<String,Object>> MerTranslList = new ArrayList<Map<String,Object>>();
						MerTransDetailList= OnlinesubtransDAO.selectByExample(subexample);
						for(Onlinesubtrans subTrans:MerTransDetailList){
							Map<String,Object> subTransMap = new HashMap<String,Object>();
							subTransMap.put(Dict.MER_SEQ_NBR, subTrans.getMerseqnbr());
							subTransMap.put(Dict.MER_NBR, subTrans.getMernbr());
							subTransMap.put(Dict.TRANS_AMT, subTrans.getTransamt());
							subTransMap.put(Dict.DEDUCT_AMT, subTrans.getDeductamt());
							subTransMap.put(Dict.REAL_AMT, subTrans.getRealamt());
							MerTranslList.add(subTransMap);
							context.setData(Dict.MER_TRANS_DETAIL_LIST, MerTranslList);
					    }
					}
					context.setDataMap(context.getDataMap());
					
				}else if(resulttranshist.size()>0){
					Onlinetranshist infoist = resulttranshist.get(0);
					transtypd = infoist.getTranstypcd();
				
					context.setData(Dict.MER_SEQ_NO, infoist.getMerseqnbr());
					context.setData(Dict.TRANS_SEQ_NO, infoist.getTransseqnbr());
					context.setData(Dict.MERCHANT_ID, infoist.getMernbr());
					context.setData(Dict.TRANS_DATE, DateUtil.Date_To_DateTimeFormat(infoist.getTransdate(), DateFormatCode.DATE_FORMATTER3));
					context.setData(Dict.CLEAR_DATE, DateUtil.Date_To_DateTimeFormat(infoist.getCleardate(), DateFormatCode.DATE_FORMATTER3));
					context.setData(Dict.TRANS_AMT, decimal.format(infoist.getTransamt()));
					context.setData(Dict.TRANS_STATUS,QueryOrderStatus.ORDERSUCCESS);
					context.setData(Dict.TRANS_AMT1, decimal.format(infoist.getRefundedamt()));
					//二维码增加字段
					if(PayTypCd.OTHACTSCAN.equals(infoist.getPaytypcd())
							||PayTypCd.OTHPASSCAN.equals(infoist.getPaytypcd())){
						context.setData(Dict.PAY_TYPE,infoist.getScancodemode());
						context.setData(Dict.PAY_ACCESS_TYPE,infoist.getCodetypcd());
						context.setData(Dict.RECEIPT_AMOUNT, decimal.format(infoist.getReceiptamt()));
					}
					//积分支付通知商户内容
					if(InteralFlag.YES.equals(infoist.getInteralflag())){
						context.setData(Dict.INTERAL_FLAG, infoist.getInteralflag());
						subexample.createCriteria().andTransseqnbrEqualTo(Transseqnbr).andTransdateEqualTo(Transdate);
						List<Onlinesubtrans> MerTransDetailList = new ArrayList<Onlinesubtrans>();
						List<Map<String,Object>> MerTranslList = new ArrayList<Map<String,Object>>();
						MerTransDetailList= OnlinesubtransDAO.selectByExample(subexample);
						for(Onlinesubtrans subTrans:MerTransDetailList){
							Map<String,Object> subTransMap = new HashMap<String,Object>();
							subTransMap.put(Dict.MER_SEQ_NBR, subTrans.getMerseqnbr());
							subTransMap.put(Dict.MER_NBR, subTrans.getMernbr());
							subTransMap.put(Dict.TRANS_AMT, subTrans.getTransamt());
							subTransMap.put(Dict.DEDUCT_AMT, subTrans.getDeductamt());
							subTransMap.put(Dict.REAL_AMT, subTrans.getRealamt());
							MerTranslList.add(subTransMap);
							context.setData(Dict.MER_TRANS_DETAIL_LIST, MerTranslList);
					    }
					}
					context.setDataMap(context.getDataMap());
				}
			}

		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
	}
	
	private Onlinetrans filterSuccessOrder(List<Onlinetrans> onlinetrans) {
		if (null == onlinetrans || onlinetrans.size() == 0) {
			return null;
		}
		if (1 == onlinetrans.size()) {
			return onlinetrans.get(0);
		}
		String lastSerialInfo = null; // 最后流水信息
		String curSerialInfo = null; // 当前流水信息
		Onlinetrans lastOrder = null;

		log.info("订单个数" + onlinetrans.size());
		for (Onlinetrans onlinetran : onlinetrans) {
			if (QueryOrderStatus.ORDERSUCCESS.equals(statusFormat(onlinetran.getTransstatus()))) {
				return onlinetran;
			}
			curSerialInfo = getSerialPKInfoFromOrder(onlinetran);
			if (null == lastSerialInfo) {
				lastSerialInfo = curSerialInfo;
				lastOrder = onlinetran;
			} else {
				if (curSerialInfo.compareTo(lastSerialInfo) > 0) { // 主键最大值，即为最后一笔流水
					lastSerialInfo = curSerialInfo;
					lastOrder = onlinetran;
				}
			}
		}
		if (null != lastOrder) {
			return lastOrder;
		}
		return onlinetrans.get(0);
	}

	private String getSerialPKInfoFromOrder(Onlinetrans onlinetran) {
		if (null == onlinetran) {
			return null;
		}
		String trsDate = DateUtil.Date_To_String(onlinetran.getTransdate());
		return trsDate + onlinetran.getTransseqnbr();
	}

	// orderinfo
	private Object statusFormat(String transstatus) {
		if (TransStatus.SUCCESS.equals(transstatus) || TransStatus.SUB_WITHDRAW.equals(transstatus)
				|| TransStatus.ALL_WITHDRAW.equals(transstatus)) {
			return QueryOrderStatus.ORDERSUCCESS;
		} else if (TransStatus.FAILURE.equals(transstatus)) {
			return QueryOrderStatus.ORDERFAIL;
		} else if (TransStatus.REVOKED.equals(transstatus)) {
			return QueryOrderStatus.ORDERCANCEL;
		} else if (TransStatus.TIMEOUT.equals(transstatus) || TransStatus.PROCESSING.equals(transstatus)
				||TransStatus.INIT.equals(transstatus)) {
			return QueryOrderStatus.ORDERINHAND;
		} else {
			// 不明状态 超时查证
			return QueryOrderStatus.ORDERINHAND;
		}

	}

	private Onlinetranshist filterSuccessOrderhist(List<Onlinetranshist> htOnlinetranshists) {
		if (null == htOnlinetranshists || htOnlinetranshists.size() == 0) {
			return null;
		}
		if (1 == htOnlinetranshists.size()) {
			return htOnlinetranshists.get(0);
		}

		String lastSerialInfohis = null; // 最后流水信息
		String curSerialInfohis = null; // 当前流水信息
		Onlinetranshist lastOrderhis = null;

		log.info("订单个数" + htOnlinetranshists.size());
		for (Onlinetranshist onlinetranhis : htOnlinetranshists) {
			if (QueryOrderStatus.ORDERSUCCESS.equals(statusFormat(onlinetranhis.getTransstatus()))) {
				return onlinetranhis;
			}
			curSerialInfohis = getSerialPKInfoFromOrder(onlinetranhis);
			if (null == lastSerialInfohis) {
				lastSerialInfohis = curSerialInfohis;
				lastOrderhis = onlinetranhis;
			} else {
				if (curSerialInfohis.compareTo(lastSerialInfohis) > 0) { // 主键最大值，即为最后一笔流水
					lastSerialInfohis = curSerialInfohis;
					lastOrderhis = onlinetranhis;
				}
			}
		}
		if (null != lastOrderhis) {
			return lastOrderhis;
		}
		return htOnlinetranshists.get(0);
	}

	private String getSerialPKInfoFromOrder(Onlinetranshist onlinetranhis) {
		if (null == onlinetranhis) {
			return null;
		}
		String trsDate = DateUtil.Date_To_String(onlinetranhis.getTransdate());
		return trsDate + onlinetranhis.getTransseqnbr();
	}

}
