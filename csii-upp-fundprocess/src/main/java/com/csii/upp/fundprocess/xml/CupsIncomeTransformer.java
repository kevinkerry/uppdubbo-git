package com.csii.upp.fundprocess.xml;

import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.pe.transform.TransformException;
import com.csii.pe.transform.object.ObjectTransformer;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dao.generate.OveralltransDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.generate.Overalltrans;
import com.csii.upp.dto.generate.OveralltransExample;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 银联机构公共输入要素转换处理
 * 
 * @author 徐锦
 * 
 */
public class CupsIncomeTransformer implements ObjectTransformer {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object format(Object arg0, Map arg1) throws TransformException {
		Map map = (Map) arg0;
		String year=StringUtil.parseObjectToString(
				DateUtil.getYear());
		String upperRtxnTime = year.concat((String) map.get("rtxnTime"));
		// 上游交易时间
		map.put(Dict.REQ_TIME, upperRtxnTime);
		// 上游交易日期
		try {
			map.put(Dict.REQ_DATE, DateUtil.Date_To_DateTimeFormat(
					DateUtil.DateTimeFormat_To_Date(upperRtxnTime),
					DateFormatCode.DATE_FORMATTER3));
		} catch (PeException e1) {
			throw new TransformException(e1);
		}

		// 上游系统号
		map.put(Dict.CHNL_ID, FundChannelCode.CUPS);
		map.put(Dict.CURRENCY_CD, "CNY");
		// 交易金额处理
		if (StringUtil.isObjectEmpty(map.get(Dict.TRANS_AMT))) {
			map.put(Dict.TRANS_AMT, "000000000000"); // 防止交易金额取空，发生异常
		}
		double tranAmt = Double.parseDouble(String.valueOf(map
				.get(Dict.TRANS_AMT)));
		tranAmt = tranAmt / 100;
		map.put(Dict.TRANS_AMT, tranAmt);// 填充交易金额
		// 原始数据元
		if (map.get("origData") != null
				&& String.valueOf(map.get("origData")).trim().length() == 42) {
			String origDataElement = StringUtil.parseObjectToString(map
					.get("origData")); // 获得90#上笔交易的信息
			String origMsgTyp = origDataElement.substring(0, 4); // 原始报文消息类型
			String origSysTraNumber = origDataElement.substring(4, 10); // 原交易系统跟踪号
			String origDateTime = origDataElement.substring(10, 20); // 原始交易的日期时间
			String origAcquInstituIdCd = origDataElement.substring(23, 31); // 原交易受理机构标识码
			String origiForwInstituIdCd = origDataElement.substring(34, 42); // 原交易发送机构标识码
			
			String origUpperRtxnNbr=origMsgTyp.concat(origSysTraNumber).concat(origDateTime)
					.concat(origAcquInstituIdCd)
					.concat(origiForwInstituIdCd);
			try {
				// 原上游交易流水号
				map.put(Dict.ORIG_UPPER_TRANS_NBR,origUpperRtxnNbr);
				// 原上游系统号
				map.put(Dict.ORIG_UPPER_SYS_NBR, FundChannelCode.CUPS);
				String origUpperRtxnDate=DateUtil.Date_To_DateTimeFormat(DateUtil.DateTimeFormat_To_Date(year+origDateTime),DateFormatCode.DATE_FORMATTER3);
				// 原上游交易日期
				map.put(Dict.ORIG_UPPER_TRANS_DATE, origUpperRtxnDate);
				//获得原总流水信息
				OveralltransExample example = new OveralltransExample();
				example.createCriteria().andUppertransnbrEqualTo(origUpperRtxnNbr)
						.andUppertransdateEqualTo(DateUtil.DateFormat_To_Date(origUpperRtxnDate))
						.andUppersysnbrEqualTo(FundChannelCode.CUPS);

				List<Overalltrans> Overalltranss = OveralltransDAO.selectByExample(example);
				if (Overalltranss == null || Overalltranss.size() < 1) {
					return map;
				}
				Overalltrans origOveralltrans = Overalltranss.get(0);
				map.put(Dict.ORIG_OVERRALL_TRANS_NBR,origOveralltrans.getOrigoveralltransnbr());
				map.put(Dict.ORIG_OVERRALL_DATA,origOveralltrans);
			} catch (Exception e) {
			}
		}
		return map;
	}

	@Override
	public Object parse(Object arg0, Map arg1) throws TransformException {
		return arg0;
	}

	@Override
	public String getName() {
		return null;
	}

}
