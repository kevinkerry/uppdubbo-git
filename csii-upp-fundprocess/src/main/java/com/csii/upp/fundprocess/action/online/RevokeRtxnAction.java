package com.csii.upp.fundprocess.action.online;

import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.OveralltransTyp;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.extend.InnerfundtransExtendDAO;
import com.csii.upp.dao.generate.InnerfundtransDAO;
import com.csii.upp.dao.generate.OveralltransDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.dto.generate.Overalltrans;
import com.csii.upp.dto.generate.OveralltransExample;
import com.csii.upp.fundprocess.action.PayOnlineAction;
import com.csii.upp.service.fundprocess.EAccountService;

/**
 * 冲正交易:通过资金通道，进行原交易的冲正交易
 * 
 * @author xujin
 *
 */
public class RevokeRtxnAction extends PayOnlineAction {

	@Override
	public void execute(Context arg0) throws PeException {
		InputFundData input = new InputFundData(arg0.getDataMap());
		try {
			Overalltrans origOveralltrans =(Overalltrans) arg0.getData(Dict.ORIG_OVERRALL_DATA);
			if(origOveralltrans==null){
				OveralltransExample example = new OveralltransExample();
				example.createCriteria().andUppertransnbrEqualTo(input.getOriguppertransnbr())
						.andUppertransdateEqualTo(input.getOriguppertransdate())
						.andUppersysnbrEqualTo(input.getOriguppersysnbr());
				List<Overalltrans> Overalltranss = OveralltransDAO.selectByExample(example);
	
				if (Overalltranss == null || Overalltranss.size() < 1) {
					throw new PeException(DictErrors.QUERY_ORIG_NOT_EXISTS);
				}
				origOveralltrans=Overalltranss.get(0);
			}

			//原交易已冲正就不能再冲正
			if (TransStatus.REVOKED.equals(origOveralltrans.getOveralltransstatus())) {
				throw new PeException(DictErrors.ORIG_TRANS_REVOKE);
			}

			//查询Overalltrans是否有已冲正的记录
			OveralltransExample example = new OveralltransExample();
			example.createCriteria().andOrigoveralltransnbrEqualTo(input.getOriguppertransnbr());
			List<Overalltrans> Overalltranss = OveralltransDAO.selectByExample(example);
			for (Overalltrans Overalltrans : Overalltranss) {
				//上次冲正未明确失败则不允许再次冲正
				if(TransStatus.INIT.equals(Overalltrans.getOveralltransstatus())){
					throw new PeException(DictErrors.DUPLICATED_REQ);
				}
			}
			
			/**
			 * 1.原消费交易时,如果交易状态成功做电子账户冲正
			 * 2.原消费撤销交易时不管交易状态是否成功都要做电子账户冲正保证资金安全
			 */
			if (OveralltransTyp.CONS.equals(origOveralltrans.getOveralltranstypcd()))
			{
				List<Innerfundtrans> InnerfundtransList = InnerfundtransExtendDAO
						.getInnerfundtransByOveralltransNbr(origOveralltrans.getOveralltransnbr());
				//原消费交易有日间异常处理这样会有2条以上记录存在
				Innerfundtrans eAccountFundRtxn = InnerfundtransList.get(0);
				if (!TransStatus.SUCCESS.equals(eAccountFundRtxn.getTransstatus())) {
					throw new PeException(DictErrors.REVOKE_TRANS_FAILED);
				}
//				if(eAccountFundRtxn.getTransamt().compareTo(input.getTransamt())!=0){
//					throw new PayException(BusinessCode.TRANAMT_ERROR);
//				}
				input.getInputDataByOrigInnerfundtrans(eAccountFundRtxn, true);
				input.setTransdate(eAccountFundRtxn.getTransdate());
				EAccountService service=getDBankService(input);
				arg0.setData(Dict.CHECK_CARD_PWD_FLAG, input.getCheckdataflag());
				service.revokeAcctPayment(input);
				// 冲正成功的话 ，更新原交易主流水交易状态为已冲正
				origOveralltrans.setOveralltransstatus(TransStatus.REVOKED);
				OveralltransDAO.updateByPrimaryKeySelective(origOveralltrans);
			}else if(OveralltransTyp.CONC.equals(origOveralltrans.getOveralltranstypcd())){
				List<Innerfundtrans> InnerfundtransList = InnerfundtransExtendDAO
						.getInnerfundtransByOveralltransNbr(origOveralltrans.getOveralltransnbr());
				//原消费撤销交易有日间异常处理这样会有2条以上记录存在
				Innerfundtrans eAccountFundRtxn = InnerfundtransList.get(0);
//				if(eAccountFundRtxn.getTransamt().compareTo(input.getTransamt())!=0){
//					throw new PayException(BusinessCode.TRANAMT_ERROR);
//				}
				input.getInputDataByOrigInnerfundtrans(eAccountFundRtxn, true);
				input.setTransdate(eAccountFundRtxn.getTransdate());
				EAccountService service=getDBankService(input);
				arg0.setData(Dict.CHECK_CARD_PWD_FLAG, input.getCheckdataflag());
				service.revokeAcctRefundment(input);
				if (!TransStatus.SUCCESS.equals(eAccountFundRtxn.getTransstatus())) {
					Innerfundtrans origInnerfundtrans=new Innerfundtrans();
					origInnerfundtrans.setInnerfundtransnbr(eAccountFundRtxn.getInnerfundtransnbr());
					origInnerfundtrans.setTransstatus(TransStatus.SUCCESS);
					InnerfundtransDAO.updateByPrimaryKeySelective(origInnerfundtrans);
				}
				// 冲正成功的话 ，更新原消费撤销交易状态为已冲正并更新原消费撤销交易的原消费交易为正常
				origOveralltrans.setOveralltransstatus(TransStatus.REVOKED);
				OveralltransDAO.updateByPrimaryKeySelective(origOveralltrans);
				Innerfundtrans consumeInnerRtxn=InnerfundtransDAO.selectByPrimaryKey(eAccountFundRtxn.getOriginnertransnbr());
				Overalltrans consumeOveralltrans=OveralltransDAO.selectByPrimaryKey(consumeInnerRtxn.getOveralltransnbr());
				consumeOveralltrans.setOveralltransstatus(TransStatus.SUCCESS);
				OveralltransDAO.updateByPrimaryKeySelective(consumeOveralltrans);
			}else{
				throw new PeException(DictErrors.TRANS_TYPE_INVALID);
			}
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}

	}
}
