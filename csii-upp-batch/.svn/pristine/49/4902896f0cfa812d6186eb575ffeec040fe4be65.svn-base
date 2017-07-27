package com.csii.upp.batch.appl.beps;

import java.util.Map;

import com.csii.upp.batch.appl.base.BaseApplyFileAppl;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dto.generate.Innercheckapply;

/**
 * 小额对账文件申请
 * 
 * @author xujin
 * 
 */
public class CheckFileApplyBepsAppl extends BaseApplyFileAppl {
	@Override
	protected String getFundChannelCd(Map<String, Object> argMaps) {
		// TODO Auto-generated method stub
		return FundChannelCode.BEPS;
	}

//	@Override
//	protected void applyCheckFile(Checkapply checkapply) {
//		// 申请对账文件
//		BepsService bepsService = (BepsService) this
//				.getRouterService(checkapply.getFundchannelcode().toLowerCase());
//		RespBepsCheck output = new RespBepsCheck();
//		InputFundProcessData input = new InputFundProcessData();
//		input.setFundChannelCd(checkapply.getFundchannelcode());
//		input.setRtxnDate(checkapply.getCheckdate());
//		try {
//			output = bepsService.bepsCheck(input);
//		} catch (Exception e1) {
//			log.error("beps check file apply error:", e1);
//		}
//		checkapply.setFilename(this.getCheckFileName(output.getFilepath(),
//				output.getFileName()));
//		checkapply.setReceivenum(output.getBepsrcvtotal() == null ? null : Long
//				.parseLong(output.getBepsrcvtotal()));
//		checkapply.setSendnum(output.getBepssendtotal() == null ? null : Long
//				.parseLong(output.getBepssendtotal()));
//		checkapply.setTotalnum(output.getBepstotalnum() == null ? null : Long
//				.parseLong(output.getBepstotalnum()));
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

	/* (non-Javadoc)
	 * @see com.csii.upp.batch.appl.base.BaseApplyFileAppl#applyCheckFile(com.csii.upp.dto.generate.Innercheckapply)
	 */
	@Override
	protected void applyCheckFile(Innercheckapply innercheckapply, Map<String, Object> argMaps) {
		// TODO 自动生成的方法存根
		
	}
}
