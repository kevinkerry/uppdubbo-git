package com.csii.upp.dto.router.eaccount;

import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.ReqAppHead;

/**
 * 直销银行账务系统服务调用入口参数
 * 
 * @author 徐锦
 *
 */
public class ReqEAccountHead extends ReqAppHead {
	private BaseRequest BaseRequest;

	public BaseRequest getBaseRequest() {
		return BaseRequest;
	}

	public void setBaseRequest(BaseRequest baseRequest) {
		this.BaseRequest = baseRequest;
	}

	public ReqEAccountHead(InputFundData data) {
		super(data);
		this.setChnlId(FundChannelCode.PAY);
		data.setFundchannelcode(FundChannelCode.EACCOUNT);
		this.setSrvChnlId(FundChannelCode.EACCOUNT);
		BaseRequest = new BaseRequest();
		BaseRequest.setWorkDate(data.getTransdate());
//		BaseRequest.setSourceId(FundChannelCode.FDPS);
		BaseRequest.setSourceId(FundChannelCode.IBPS);
		// 南京POC增加SLY
		BaseRequest.setSeqNo(String.valueOf(System.currentTimeMillis()));
		BaseRequest.setBranchNbr(data.getBranchNbr());
		BaseRequest.setBankOrgNbr(data.getBankOrgNbr());
		BaseRequest.setSeqNo(this.getReqSeqNo());
	}

}
