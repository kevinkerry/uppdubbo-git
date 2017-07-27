package com.csii.upp.fundprocess.action.online;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.fundprocess.action.PayOnlineAction;

/**
 * 实时贷记:通过资金通道，互联网账户核心电子账户资金实时流出至其他机构
 * 
 * @author 徐锦
 * 
 */
public class RealTimeCreditRtxnAction extends PayOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		InputFundData input = new InputFundData(context.getDataMap());
		// 先获得贷方通道路由得到科目账户外部识别号
	/*	getCreditRouter(input);
		context.setData(Dict.CHECK_CARD_PWD_FLAG, input.getCheckdataflag());*/
//		if (!AcctTypCd.INNER.equals(input.getPayeraccttypcd())) {
//			// 验证付款账户状态是否正常，是否已激活且已认证等（调电子账户查询接口)
//			// RespSysHead outdata =
//			// getCoreService().rtdtdrReTrave(input);//为测试晋商渠道加的方法，后续删掉
//			this.getDBankService(input).validatePayerAcctInfo(input);
//			
//		}
		// 借方:电子账户单笔借记
		RespSysHead withdraw = getCoreService().rtdtdr1(input);
		
		if (withdraw.isSuccess()) {
			// 贷方记账:如果是本行就调用老核心入账,否则调用第三方入账
			RespSysHead rtctdr = getDBankService(input).rtctdr(input);
			if (rtctdr.isSuccess()) {
				
			}
		}
	}

}
