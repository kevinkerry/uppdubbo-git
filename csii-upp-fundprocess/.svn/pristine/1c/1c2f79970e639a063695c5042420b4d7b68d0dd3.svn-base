package com.csii.upp.fundprocess.action.callback;

import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.ErrorState;
import com.csii.upp.constant.ErrorTyp;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.InnerRtxnTyp;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.generate.BatchcheckerrorDAO;
import com.csii.upp.dao.generate.InnerfundtransDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Batchcheckerror;
import com.csii.upp.dto.generate.BatchcheckerrorExample;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.dto.router.unionpay.RespDsjyyb;
import com.csii.upp.fundprocess.action.PayOnlineAction;
import com.csii.upp.util.DateUtil;

/**
 * 他行网银退货成功后回调
 * 
 * @author gaoqi
 *
 */
public class UnionPayRefundAfterAction extends PayOnlineAction {

	@Override
	public void execute(Context ctx) throws PeException {
		log.info("UnionPayOtherBankQuickPayAfterAction--------UnionNotifation Success");

		RespDsjyyb respobj = this.buildRespOjb(ctx);
		String innerrtxnnbr = respobj.getOrderId();
		Batchcheckerror record = null;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			// 收到银联通知即表示成功
			Innerfundtrans innerfundtrans = InnerfundtransDAO.selectByPrimaryKey(innerrtxnnbr);
			BatchcheckerrorExample example = new BatchcheckerrorExample();
			example.createCriteria().andOveralltransnbrEqualTo(innerfundtrans.getOveralltransnbr())
					.andFundchannelcodeEqualTo(FundChannelCode.CORE).andCheckerrortypEqualTo(ErrorTyp.PRERVCOREPROCESS);
			List<Batchcheckerror> batchcheckerror = BatchcheckerrorDAO.selectByExample(example);
			if (batchcheckerror != null && batchcheckerror.size() > 0) {
				String errornbr = batchcheckerror.get(0).getErrornbr();
				try {
					record = BatchcheckerrorDAO.selectByPrimaryKey(errornbr);
				} catch (SQLException e) {
					log.error("PrebackProcess Error:" + e.getMessage());
				}
				record.setErrorstatus(ErrorState.SUCCESS);
				if (record != null) {
					try {
						BatchcheckerrorDAO.updateByPrimaryKeySelective(record);
					} catch (SQLException e1) {
						log.error(e1.getMessage());
					}
				}
			}
			if (TransStatus.SUCCESS.equals(innerfundtrans.getTransstatus())) {
				return;
			}
			innerfundtrans.setTransstatus(TransStatus.SUCCESS);
			innerfundtrans.setReturncode(respobj.getRespCode());
			innerfundtrans.setReturnmsg(respobj.getRespMsg());
			innerfundtrans.setDowntransnbr(respobj.getOrigQryId());
			innerfundtrans.setCleardate(
					DateUtil.DateTimeFormat_To_Date(respobj.getSettleDate(), DateFormatCode.DATE_FORMATTER3));
			InnerfundtransDAO.updateByPrimaryKeySelective(innerfundtrans);
			
			if(InnerRtxnTyp.UnionPayRefoundTrans.equals(innerfundtrans.getTranscode())){
				// 通知Payment进行相应的处理
				InputFundData inputData2Payment = new InputFundData();
				inputData2Payment.setUppertransnbr(innerfundtrans.getUppertransnbr());
				inputData2Payment.setPayeracctnbr(respobj.getAccNo());
				inputData2Payment.setPayercardtypcd(respobj.getPayCardType());
				inputData2Payment.setTransstatus(TransStatus.SUCCESS);
				this.getPaymService().notifyPayResult(inputData2Payment);
				// 用于更新总交易流水
				ctx.setData(Dict.OVERALL_TRANS_NBR, innerfundtrans.getOveralltransnbr());
			}else if(InnerRtxnTyp.UnionPayInnerRefoundTrans.equals(innerfundtrans.getTranscode())){
				BatchcheckerrorExample batchcheckerrorExample = new BatchcheckerrorExample();
				batchcheckerrorExample.createCriteria().andOveralltransnbrEqualTo(innerfundtrans.getOveralltransnbr())
					.andFundchannelcodeEqualTo(FundChannelCode.UNIONPAY);
				batchcheckerrorExample.setOrderByClause("datelastmaint desc");
				List<Batchcheckerror> batchcheckerrors = BatchcheckerrorDAO.selectByExample(batchcheckerrorExample);
				Batchcheckerror re = batchcheckerrors.get(0);
				re.setErrorstatus(ErrorState.SUCCESS);
				BatchcheckerrorDAO.updateByExample(re, batchcheckerrorExample);
			}
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
	}
}
