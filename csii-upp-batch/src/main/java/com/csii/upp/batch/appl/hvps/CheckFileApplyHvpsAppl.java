package com.csii.upp.batch.appl.hvps;

import java.util.Map;

import com.csii.upp.batch.appl.base.BaseApplyFileAppl;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dto.generate.Innercheckapply;
/**
 * 大额对账文件申请
 * @author xujin
 *
 */
public class CheckFileApplyHvpsAppl extends BaseApplyFileAppl {
	@Override
	protected String getFundChannelCd(Map<String, Object> argMaps) {
		// TODO Auto-generated method stub
		return FundChannelCode.HVPS;
	}

//	@Override
//	protected void applyCheckFile(Checkapply checkapply) {
//		// 申请对账文件
//		HvpsService hvpsService = (HvpsService) this.getRouterService(checkapply.getFundchannelcode().toLowerCase());
//		RespHvpsCheck output = new RespHvpsCheck();
//		InputFundProcessData input = new InputFundProcessData();
//		input.setFundChannelCd(checkapply.getFundchannelcode());
//		input.setRtxnDate(checkapply.getCheckdate());
//		try {
//			 output = hvpsService.hvpsCheck(input);
//		} catch (Exception e1) {
//			log.error("hvps check file apply error:", e1);
//		}
//		checkapply.setFilename(this.getCheckFileName(output.getFilepath(),
//				output.getFileName()));
//		checkapply.setReceivenum(output.getHvpsrcvtotal() == null ? null
//				: Long.parseLong(output.getHvpsrcvtotal()));
//		checkapply.setSendnum(output.getHvpssendtotal() == null ? null
//				: Long.parseLong(output.getHvpssendtotal()));
//		checkapply.setTotalnum(output.getHvpstotalnum() == null ? null
//				: Long.parseLong(output.getHvpstotalnum()));	
//		// 如果申请失败抛出异常
//		if (!output.isSuccess()) {
//			checkapply.setDealmsg(this.formatReturnMsg(output.getReturncode(),
//					output.getReturnmsg()));
//			/**
//			 * 0O3104 无对应渠道的往来账信息(与人行对账完成)，无需再次申请
//			 * 0O3102 无对账信息(人行没有下发对账文件[节假日])，无需再次申请
//			 */
//			this.valReturnCode(output.getReturncode(), checkapply);
//		}
//	}

	
	@Override
	protected void applyCheckFile(Innercheckapply innercheckapply, Map<String, Object> argMaps) {
		// TODO 自动生成的方法存根
		
	}
}
