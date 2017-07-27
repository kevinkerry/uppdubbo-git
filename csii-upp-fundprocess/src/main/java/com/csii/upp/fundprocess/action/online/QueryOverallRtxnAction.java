package com.csii.upp.fundprocess.action.online;

import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.generate.InnerfundtransDAO;
import com.csii.upp.dao.generate.OveralltransDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.dto.generate.InnerfundtransExample;
import com.csii.upp.dto.generate.Overalltrans;
import com.csii.upp.dto.generate.OveralltransExample;
import com.csii.upp.dto.router.unionpay.RespDsjyCx;
import com.csii.upp.fundprocess.action.PayOnlineAction;

/**
 * @author JIANGXING
 * 
 */
public class QueryOverallRtxnAction extends PayOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		OveralltransExample example = new OveralltransExample();
		example.createCriteria().andUppertransnbrEqualTo(context.getString(Dict.ORIG_OVERRALL_TRANS_NBR));
		List<Overalltrans> list = null;
		try {
			list = OveralltransDAO.selectByExample(example);
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		if (list.size() != 0) {
			Overalltrans oln = list.get(0);
			if ("RTDT".equals(oln.getOveralltranstypcd()) && TransStatus.SUCCESS.equals(oln.getOveralltransstatus())) {
				InnerfundtransExample iexample = new InnerfundtransExample();
				iexample.createCriteria().andOveralltransnbrEqualTo(oln.getOveralltransnbr())
						.andFundchannelcodeEqualTo(FundChannelCode.UNIONPAY);
				List<Innerfundtrans> ilist = null;
				try {
					ilist = InnerfundtransDAO.selectByExample(iexample);
				} catch (SQLException e) {
					log.error(e.getMessage());
				}
				if (ilist.size() != 0) {
					Innerfundtrans ifn = ilist.get(0);
					if (!TransStatus.SUCCESS.equals(ifn.getTransstatus())) {
						InputFundData input = new InputFundData(context.getDataMap());
						input.setOrigdowntransnbr(ifn.getDowntransnbr());
						input.setTranstime(ifn.getTranstime());
						input.setOriginnertransnbr(ifn.getInnerfundtransnbr());
						int i = 0;
						int loopCnt = 4;
						RespDsjyCx withdraw = this.getUnionPayService().dsjyCx(input);
						while (i < loopCnt && ("03".equals(withdraw.getOrigRespCode())
								|| "05".equals(withdraw.getOrigRespCode()))) {
							try {
								Thread.sleep((int) Math.scalb(500, i));
							} catch (InterruptedException e) {
								log.error(e.getMessage());
							}
							withdraw = this.getUnionPayService().dsjyCx(input);
							i++;
						}
						if (!("00".equals(withdraw.getOrigRespCode()) || "A6".equals(withdraw.getOrigRespCode()))) {
							oln.setReturncode("CUPS" + withdraw.getOrigRespCode());
							oln.setReturnmsg(withdraw.getOrigRespMsg());
							oln.setOveralltransstatus(TransStatus.FAILURE);
							ifn.setTransstatus(TransStatus.FAILURE);
							ifn.setReturncode(withdraw.getOrigRespCode());
							ifn.setReturnmsg(withdraw.getOrigRespMsg());
							try {
								OveralltransDAO.updateByPrimaryKey(oln);
								InnerfundtransDAO.updateByPrimaryKey(ifn);
							} catch (SQLException e) {
								log.error(e.getMessage());
							}
						}
					}
				}
			}
		}
		context.setData("overalltransinfo", this.getObjectMapMarshaller().marshall(list));
	}
}
