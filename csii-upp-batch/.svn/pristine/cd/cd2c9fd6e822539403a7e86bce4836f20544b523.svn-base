package com.csii.upp.batch.appl.wechat;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.csii.upp.batch.appl.base.BaseCheckAppl;
import com.csii.upp.constant.CheckStatus;
import com.csii.upp.constant.ErrorTyp;
import com.csii.upp.constant.InnerRtxnTyp;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.generate.OveralltranshistDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.CheckUpdateStatus;
import com.csii.upp.dto.extend.PreClearCheckTrans;
import com.csii.upp.dto.generate.Innercheckapply;
import com.csii.upp.dto.generate.Overalltranshist;
import com.csii.upp.util.StringUtil;

public class CheckWeChatAppl extends BaseCheckAppl {

	@Override
	protected List<Innercheckapply> getInnerCheckApply(Map<String, Object> argMaps) {
		Date checkDate = this.getCheckDate(argMaps);
		String fundChannelCd = StringUtil.parseObjectToString(argMaps.get(Dict.FCNM));
		return this.getCheckApplyForCheck(fundChannelCd, checkDate);
	}

	protected CheckUpdateStatus checkDownRtxnNotExist(PreClearCheckTrans preClearCheckTrans, Date intervalDate,String checkdataflag) {
		CheckUpdateStatus updState = new CheckUpdateStatus();
		if (InnerRtxnTyp.WeChatQrCodeActivePay .equals(preClearCheckTrans.getTranscode())) {
			// 支付交易
			if (TransStatus.FAILURE.equals(preClearCheckTrans.getTransstatus())) {
				updState.setInnerCheckStatus(CheckStatus.CHECKED);
				updState.setInnerTransStatus(TransStatus.FAILURE);
				updState.setOverallCheckStatus(CheckStatus.CHECKED);
				updState.setOverallTransStatus(TransStatus.FAILURE);
			} else if (preClearCheckTrans.getTransdate().before(intervalDate)) {
				// 如果内部待清算流水交是intervalDate前(包括)的数据，设置内部带清算交易流水交易状态为失败，总交易为失败，对账状态为已经对平
				if (TransStatus.PROCESSING.equals(preClearCheckTrans.getTransstatus())
						|| TransStatus.TIMEOUT.equals(preClearCheckTrans.getTransstatus())) {
					updState.setInnerCheckStatus(CheckStatus.CHECKED);
					updState.setInnerTransStatus(TransStatus.FAILURE);
					updState.setOverallCheckStatus(CheckStatus.CHECKED);
					updState.setOverallTransStatus(TransStatus.FAILURE);
				} else if (TransStatus.SUCCESS.equals(preClearCheckTrans.getTransstatus())) {
					updState.setInnerCheckStatus(CheckStatus.UNCHECKED);
					updState.setOverallCheckStatus(CheckStatus.UNCHECKED);
					updState.setCheckErrorType(ErrorTyp.DOWNNOTEXIST);
				}
			}
		} else if (InnerRtxnTyp.WeChatRefoundTrans.equals(preClearCheckTrans.getTranscode())) {
			if (TransStatus.FAILURE.equals(preClearCheckTrans.getTransstatus())) {
				updState.setInnerCheckStatus(CheckStatus.CHECKED);
				updState.setInnerTransStatus(TransStatus.FAILURE);
				updState.setOverallCheckStatus(CheckStatus.CHECKED);
				updState.setOverallTransStatus(TransStatus.FAILURE);
				updState.setCheckErrorType(ErrorTyp.PRERVCOREPROCESS);
			} else if (preClearCheckTrans.getTransdate().before(intervalDate)) {
				// 如果内部待清算流水交是intervalDate前(包括)的数据，设置内部带清算交易流水交易状态为失败，总交易为失败，对账状态为已经对平
				if (TransStatus.TIMEOUT.equals(preClearCheckTrans.getTransstatus())
						|| TransStatus.PROCESSING.equals(preClearCheckTrans.getTransstatus())) {
					updState.setInnerCheckStatus(CheckStatus.CHECKED);
					updState.setInnerTransStatus(TransStatus.FAILURE);
					updState.setOverallCheckStatus(CheckStatus.CHECKED);
					updState.setOverallTransStatus(TransStatus.FAILURE);
					updState.setCheckErrorType(ErrorTyp.PRERVCOREPROCESS);
				} else if (TransStatus.SUCCESS.equals(preClearCheckTrans.getTransstatus())) {
					updState.setInnerCheckStatus(CheckStatus.UNCHECKED);
					updState.setOverallCheckStatus(CheckStatus.UNCHECKED);
					updState.setCheckErrorType(ErrorTyp.DOWNNOTEXIST);
				}
			}
		}
		return updState;
	}

	@Override
	protected CheckUpdateStatus checkDownRtxnExist(PreClearCheckTrans preClearCheckTrans,String checkdataflag) {
		CheckUpdateStatus updState = new CheckUpdateStatus();
		Overalltranshist overalltranshist = null;
		try {
			overalltranshist = OveralltranshistDAO.selectByPrimaryKey(preClearCheckTrans.getOveralltransnbr());
		} catch (SQLException e) {
			log.error(e);
		}
		// 检查金额和账号关键字是否一致
		if (this.isKeyFieldEqual(preClearCheckTrans)) {
			if (InnerRtxnTyp.WeChatQrCodeActivePay .equals(preClearCheckTrans.getTranscode())) {
				// 支付交易
				if (TransStatus.SUCCESS.equals(preClearCheckTrans.getTransstatus())) {						
				updState.setInnerCheckStatus(CheckStatus.CHECKED);
				updState.setDownCheckStatus(CheckStatus.CHECKED);
				updState.setInnerTransStatus(TransStatus.SUCCESS);
				updState.setOverallCheckStatus(CheckStatus.CHECKED);
			} else if (TransStatus.PROCESSING.equals(preClearCheckTrans.getTransstatus())
						|| TransStatus.TIMEOUT.equals(preClearCheckTrans.getTransstatus())
						|| TransStatus.FAILURE.equals(preClearCheckTrans.getTransstatus())) {
					try {
						updState.setOverallTransStatus(TransStatus.SUCCESS);
						updState.setInnerCheckStatus(CheckStatus.CHECKED);
						updState.setDownCheckStatus(CheckStatus.CHECKED);
						updState.setInnerTransStatus(TransStatus.SUCCESS);
						updState.setOverallCheckStatus(CheckStatus.CHECKED);
					} catch (Exception e) {
						log.error(e.getMessage());
					}
				}
			} else if (InnerRtxnTyp.WeChatRefoundTrans.equals(preClearCheckTrans.getTranscode())) {
				// 退货交易
				updState.setOverallCheckStatus(CheckStatus.CHECKED);
				updState.setOverallTransStatus(TransStatus.SUCCESS);
				updState.setInnerCheckStatus(CheckStatus.CHECKED);
				updState.setDownCheckStatus(CheckStatus.CHECKED);
				updState.setInnerTransStatus(TransStatus.SUCCESS);
			}
		} else {
			// 关键域不匹配
			updState.setInnerCheckStatus(CheckStatus.UNCHECKEDKEY);
			updState.setDownCheckStatus(CheckStatus.UNCHECKEDKEY);
			updState.setCheckErrorType(ErrorTyp.ARTPROCESS);
			updState.setOverallCheckStatus(CheckStatus.UNCHECKEDKEY);
		}

		return updState;
	}
}
