package com.csii.upp.dto.router.alipacode;

import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.ScanCodeMode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dto.router.RespAppHead;
import com.csii.upp.util.StringUtil;
/**
 * 二维码支付宝接收头
 * @author wy
 *
 */
public class RespAlipayCodePreHead extends RespAppHead {
	private String receiptAmount;
	private String buyerLogonId;
	private String  payType;
	
	@Override
	public void setResultStatus(String resultStatus) {
		this.setFundchannelcd(FundChannelCode.ALIPAYCODE);
		if (!StringUtil.isStringEmpty(resultStatus)) {
			this.setReturncode(resultStatus);
			if(TransStatus.SUCCESS.equals(getfunTransStatus(resultStatus))){
				setRtxnstate(TransStatus.SUCCESS);
			}else if(TransStatus.TIMEOUT.equals(getfunTransStatus(resultStatus))) {
				setRtxnstate(TransStatus.TIMEOUT);
			} else if (TransStatus.PROCESSING.equals(getfunTransStatus(resultStatus))) {
				setRtxnstate(TransStatus.PROCESSING);
			}else {
				setRtxnstate(TransStatus.FAILURE);
			}
		}
	}
	/**
	 * 判断交易最终状态
	 * 
	 * @param context
	 * @return
	 */
	protected String getfunTransStatus(String resultStatus) {
		if ("02".equals(resultStatus)) {
			return TransStatus.SUCCESS;
		} else if ("04".equals(resultStatus)) {
			return TransStatus.TIMEOUT;
		} else if ("01".equals(resultStatus)||"08".equals(resultStatus)||"10".equals(resultStatus)) {
			return TransStatus.PROCESSING;
		}else {
			return TransStatus.FAILURE;
		}
	}
	public String getReceiptAmount() {
		return receiptAmount;
	}
	public void setReceiptAmount(String receiptAmount) {
		this.receiptAmount = receiptAmount;
	}
	public String getBuyerLogonId() {
		return buyerLogonId;
	}
	public void setBuyerLogonId(String buyerLogonId) {
		this.buyerLogonId = buyerLogonId;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		if(ScanCodeMode.ACTIVE.equals(payType)&&
				TransStatus.SUCCESS.equals(this.getRtxnstate())){
			setRtxnstate(TransStatus.PENDING);
		}
		this.payType = payType;
	}
}
