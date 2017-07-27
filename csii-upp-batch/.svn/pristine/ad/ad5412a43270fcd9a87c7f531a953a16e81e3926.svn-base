package com.csii.upp.batch.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.action.BaseAction;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.InnerRtxnTyp;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.generate.InnerfundtransDAO;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.dto.generate.InnerfundtransExample;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.service.fundprocess.AliPayService;
import com.csii.upp.service.fundprocess.WechatService;
/**
 * 
 * 支付宝微信退货查询
 * @author wy
 *
 */
public class QueryQrCodeRefStatusAction extends BaseAction {

	@Override
	public void execute(Context context) throws PeException {
		//查询支付宝微信支付状态为处理中的流水
		String transstatus=TransStatus.PROCESSING;
		List<String> transcodeList = new ArrayList<String>(
				Arrays.asList(InnerRtxnTyp.AlipayRedoTrans,  InnerRtxnTyp.WeChatRedoTrans));
		try{
			InnerfundtransExample example = new InnerfundtransExample();
			example.createCriteria().andTranscodeIn(transcodeList).andTransstatusEqualTo(transstatus);
			List<Innerfundtrans> innerfundtranss = InnerfundtransDAO.selectByExample(example);
			for(Innerfundtrans innerfundtrans:innerfundtranss){
				InputFundData input = new InputFundData();
				input.setFundchannelcode(innerfundtrans.getFundchannelcode());
				input.setInnerfundtransnbr(innerfundtrans.getInnerfundtransnbr());
				input.setOveralltransnbr(innerfundtrans.getOveralltransnbr());
				input.setTranstime(innerfundtrans.getTranstime());
				input.setPayeeacctnbr(innerfundtrans.getPayeeacctnbr());
				RespSysHead output=null;
				AliPayService aliPayService=null;
				WechatService wechatService=null;
				if(FundChannelCode.ALIPAYCODE.equals(innerfundtrans.getFundchannelcode())){
					aliPayService=(AliPayService)this.getRouterService(FundChannelCode.ALIPAYCODE.toLowerCase());
					output=aliPayService.alipayQrcodeRefoundTimeOut(input);
				}else if(FundChannelCode.WECHATCODE.equals(innerfundtrans.getFundchannelcode())){
					wechatService=(WechatService)this.getRouterService(FundChannelCode.WECHATCODE.toLowerCase());
					output=wechatService.weChatQrcodeRefoundTimeOut(input);
				}
			}
			
		
		}catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		
	}
	
}
