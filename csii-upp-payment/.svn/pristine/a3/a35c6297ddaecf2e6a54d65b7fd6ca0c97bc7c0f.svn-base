package com.csii.upp.payment.action.syn;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csii.pe.core.PeException;
import com.csii.pe.core.PeRuntimeException;
import com.csii.pe.service.comm.Transport;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.constant.SignTypCd;
import com.csii.upp.dao.generate.CusttransctrlDAO;
import com.csii.upp.dao.generate.UserpaytypsigninfoDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Custtransctrl;
import com.csii.upp.dto.generate.CusttransctrlExample;
import com.csii.upp.dto.generate.Userpaytypsigninfo;
import com.csii.upp.dto.generate.UserpaytypsigninfoExample;
import com.csii.upp.event.EventHandler;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.FileHandler;
import com.csii.upp.util.StringUtil;

public class SynSignExitEventHandler implements EventHandler<SynSignExitEvent> {
	protected static Log log = LogFactory.getLog(SynSignExitEventHandler.class);

	@SuppressWarnings("rawtypes")
	@Override
	public void handler(SynSignExitEvent event) {
		// Map paramMap = event.getParamMap();
		InputPaymentData inputData = null;
		try {
			inputData = new InputPaymentData(event.getParamMap());
		} catch (PeException e2) {
			log.error("同步1.0失败", e2);
		}
		if(StringUtil.isStringEmpty(inputData.getPayeracctnbr())){
			return ;
		}
		String transCode = inputData.getTranscode();
		UserpaytypsigninfoExample example = new UserpaytypsigninfoExample();
		example.createCriteria().andSigncardnbrEqualTo(inputData.getPayeracctnbr())
				.andSigntypcdEqualTo(SignTypCd.USER_PAY_TYP).andPaytypcdEqualTo(PayTypCd.FOSION);
		List<Userpaytypsigninfo> signinfo = null;
		try {
			signinfo = UserpaytypsigninfoDAO.selectByExample(example);
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		if (signinfo.isEmpty()) {
			log.error("签约信息为空!");
			return;
		}
		Userpaytypsigninfo userpaytypsigninfo = signinfo.get(0);
		// 查询客户交易控制表
		CusttransctrlExample example1 = new CusttransctrlExample();
		example1.createCriteria().andSignnbrEqualTo(userpaytypsigninfo.getSignnbr()).andSigntypcdEqualTo(SignTypCd.USER_PAY_TYP);
		List<Custtransctrl> custCtrl = null;
		try {
			custCtrl = CusttransctrlDAO.selectByExample(example1);
		} catch (SQLException e1) {
			log.error("error",e1);
		}
		if (custCtrl.isEmpty()) {
			log.error("交易控制信息为空!");
			return;
		}
		Custtransctrl custtransctrl = custCtrl.get(0);
		Map<String, Object> merResult = new HashMap<String, Object>();

		merResult.put("TransId", "SYFS");
		merResult.put("SignOpenDeptId", userpaytypsigninfo.getSigndeptnbr());
		merResult.put("AcctNo", inputData.getPayeracctnbr());
		DecimalFormat decimal = new DecimalFormat("0.00");
		if (transCode.equals("UPP10003")) {
			merResult.put("SynType", "FS11");
			merResult.put(Dict.TRANS_AMT, inputData.getTransamt());
			merResult.put("PayDate", DateUtil.Date_To_DateTimeFormat(inputData.getTransdate(), DateFormatCode.DATE_FORMATTER3));
			merResult.put("ModifyUser", "");
			merResult.put("ModifyDate", DateUtil.Date_To_DateTimeFormat(custtransctrl.getDatelastmaint(), DateFormatCode.DATE_FORMATTER3));
		} else if (transCode.equals("UPP10014")) {
			merResult.put("SynType", "FS03");
			merResult.put("PerDayLimit", decimal.format(custtransctrl.getPerdaylimit()));
			merResult.put("PerTransLimit", decimal.format(custtransctrl.getPertranslimit()));
			merResult.put("ModifyUser", "");
			merResult.put("ModifyDate", DateUtil.Date_To_DateTimeFormat(custtransctrl.getDatelastmaint(), DateFormatCode.DATE_FORMATTER3));
		} else if (transCode.equals("UPP10019")) {
			merResult.put("SynType", "FS07");
			merResult.put("ModifyUser", "");
			merResult.put("ModifyDate", DateUtil.Date_To_DateTimeFormat(custtransctrl.getDatelastmaint(), DateFormatCode.DATE_FORMATTER3));
			merResult.put("CloseUser", "");
			merResult.put("CloseDate", DateUtil.Date_To_DateTimeFormat(custtransctrl.getDatelastmaint(), DateFormatCode.DATE_FORMATTER3));
		} else if (transCode.equals("UPP10005")) {
			merResult.put("SynType", "FS04");
			merResult.put("ModifyUser", "");
			merResult.put("ModifyDate", DateUtil.Date_To_DateTimeFormat(custtransctrl.getDatelastmaint(), DateFormatCode.DATE_FORMATTER3));
		} else if (transCode.equals("UPP10006")) { //解冻
			merResult.put("SynType", "FS05");
			merResult.put("ModifyUser", "");
			merResult.put("ModifyDate", DateUtil.Date_To_DateTimeFormat(custtransctrl.getDatelastmaint(), DateFormatCode.DATE_FORMATTER3));
		} else if (transCode.equals("UPP10001")) {

			merResult.put("SynType", "FS01");
			merResult.put("ModifyUser", "");
			merResult.put("ModifyDate", DateUtil.Date_To_DateTimeFormat(custtransctrl.getDatelastmaint(), DateFormatCode.DATE_FORMATTER3));
			merResult.put("OpenUser", "");
			merResult.put("OpenDate", DateUtil.Date_To_DateTimeFormat(custtransctrl.getDatelastmaint(), DateFormatCode.DATE_FORMATTER3));
			merResult.put("SignUserName", userpaytypsigninfo.getSignname());
			merResult.put("SignRecommendedNo", userpaytypsigninfo.getTeller());
			merResult.put("SignPhone", userpaytypsigninfo.getSignmobile());
			merResult.put("PerDayLimit", decimal.format(custtransctrl.getPerdaylimit()));
			merResult.put("PerTransLimit", decimal.format(custtransctrl.getPertranslimit()));
			merResult.put("SignId", userpaytypsigninfo.getSignnbr());
			merResult.put("CifNo", userpaytypsigninfo.getUsernbr());
			merResult.put("SignOpenDeptId",userpaytypsigninfo.getSigndeptnbr());
		} else {
			return;
		}
		Map resultMap = null;
		try {
			resultMap = (Map) this.getTransport().submit(merResult);
		} catch (Exception e) {
			log.error("同步1.0失败", e);
		}
		if (resultMap == null || !ResponseCode.SUCCESS.equals(resultMap.get(Dict.RESP_CODE))) {
			this.writeIssueFile(merResult);
		}

	}

	/**
	 * 生成文件
	 * @param merResult
	 */
	private void writeIssueFile(Map<String, Object> merResult) {
		FileOutputStream out = null;
		try {
			String lineSeparator = "|";
			String fileName = (String) merResult.get("SynType") +IDGenerateFactory.generateSeqId();
			FileHandler.createFile(localPath, fileName);
			out = new FileOutputStream(localPath + fileName, true);
			String line = "";
			for (Map.Entry<String, Object> entry : merResult.entrySet()) {
				if(!StringUtil.isObjectEmpty(entry.getValue())){
					line = line + entry.getKey() + "=" + entry.getValue() + lineSeparator;
				}
			}
			FileHandler.writeRecorde(line, out, "UTF-8");
		
		} catch (Exception e) {
			throw new PeRuntimeException(e.getMessage());
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
			}
		}
	}

	@Override
	public Class<SynSignExitEvent> getAcceptedEventType() {
		return SynSignExitEvent.class;
	}

	private Transport transport;
	private String localPath;
	
	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

}