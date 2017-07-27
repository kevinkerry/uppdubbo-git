package com.csii.upp.dto.router.paym.test;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqPaymHead;
import com.csii.upp.util.DateUtil;
/**
 * 生产测试
 * @author WY
 *
 */
public class ReqTest extends ReqPaymHead{
	
	private String merTransDate;
	
	
	public String getMerTransDate() {
		return merTransDate;
	}


	public void setMerTransDate(String merTransDate) {
		this.merTransDate = merTransDate;
	}


	public ReqTest(InputPaygateData data) {
		super(data);
		this.setTransCode("UPP20160");
		this.setMerTransDate(DateUtil.Date_To_DateTimeFormat(data.getMertransdate(),DateFormatCode.DATE_FORMATTER3));
		}
	
}
