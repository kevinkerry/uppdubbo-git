package com.csii.upp.payment.action.start;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.MerTransCtrlStatus;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.dao.generate.MertransctrlDAO;
import com.csii.upp.dao.generate.MertranslimitDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Mertransctrl;
import com.csii.upp.dto.generate.Mertranslimit;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.StringUtil;

/**
 * 商户支付方式交易限制检查
 * 
 * @author xujin
 * 
 */
public class CheckMerPayTypTransLimitAction extends PaymentOnlineAction {

	@Override
	public void execute(Context ctx) throws PeException {
		InputPaymentData inputData = (InputPaymentData) ctx.getData(Dict.INPUT_PAYMENT_DATA);
		if (PayTypCd.OTHCYBER.equals(inputData.getPaytypcd())||PayTypCd.OTHACTSCAN.equals(inputData.getPaytypcd())||
				PayTypCd.OTHPASSCAN.equals(inputData.getPaytypcd())) {
			return;
		}
		log.info(new StringBuilder().append("支付方式[").append(inputData.getPaytypcd()).append("]").append("支付平台流水[")
				.append(inputData.getTransseqnbr()).append("]").append("商户号[").append(inputData.getMernbr()).append("]")
				.append("商户流水[").append(inputData.getMerseqnbr()).append("]").append("商户时间[")
				.append(ctx.getData(Dict.MER_TRANS_DATE_TIME)).append("]").append("交易代码[")
				.append(inputData.getTranscode()).append("] 商户支付方式交易限制检查!").toString());
		try {
			// 验证商户交易控制
			Mertransctrl mertransctrl = MertransctrlDAO.selectByPrimaryKey(inputData.getChannelnbr(),
					inputData.getMernbr(), inputData.getTranstypcd(), inputData.getPaytypcd(),
					inputData.getPayercardtypcd());
			if (mertransctrl == null||MerTransCtrlStatus.N.equals(mertransctrl.getStatus())) {
				throw new PeException(DictErrors.MER_TRANS_CTR_ERROR);
			}

			// 验证商户支付方式交易限制
			Mertranslimit mertranslimit = MertranslimitDAO.selectByPrimaryKey(inputData.getMernbr(),
					inputData.getTranstypcd(), inputData.getPaytypcd(), inputData.getPayercardtypcd(),
					inputData.getChannelnbr());
			// 商户支付方式交易限制信息不能为空
			if (mertranslimit == null) {
				throw new PeException(DictErrors.MER_TRANS_LIMIT_ERROR);
			}
			BigDecimal perTransLimit = mertranslimit.getPertranslimit();
			if (StringUtil.isObjectEmpty(perTransLimit)) {
				throw new PeException(DictErrors.PER_TRANS_LIMIT_NULL);
			}
			// 商户支付方式单笔交易限额不能超限
			if (inputData.getTransamt().compareTo(perTransLimit) > 0) {
				throw new PeException(DictErrors.PER_TRANS_LIMIT_OVER);
			}
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}

	}
}
