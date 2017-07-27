package com.csii.upp.payment.action.start;

import java.sql.SQLException;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.DeptTransCtrlStatus;
import com.csii.upp.dao.generate.DepttransctrlDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Depttransctrl;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.StringUtil;

/**
 * 机构支付方式限制检查
 * 
 * @author xujin
 * 
 */
public class CheckDeptPayTypLimitAction extends PaymentOnlineAction {

	@Override
	public void execute(Context ctx) throws PeException {
		InputPaymentData inputData = (InputPaymentData) ctx.getData(Dict.INPUT_PAYMENT_DATA);
		log.info(new StringBuilder().append("支付方式[").append(inputData.getPaytypcd()).append("]").append("支付平台流水[")
				.append(inputData.getTransseqnbr()).append("]").append("商户号[").append(inputData.getMernbr()).append("]")
				.append("商户流水[").append(inputData.getMerseqnbr()).append("]").append("商户时间[")
				.append(ctx.getData(Dict.MER_TRANS_DATE_TIME)).append("]").append("交易代码[")
				.append(inputData.getTranscode()).append("] 机构支付方式限制检查!").toString());

		// 验证机构支付方式限制
		Depttransctrl deptTransCtrl;
		try {
			deptTransCtrl = DepttransctrlDAO.selectByPrimaryKey(inputData.getMerdevdeptnbr(), inputData.getPaytypcd(),
					inputData.getChannelnbr());
			if (deptTransCtrl == null || DeptTransCtrlStatus.N.equals(deptTransCtrl.getStatus())) {
				throw new PeException(DictErrors.DEPT_PAY_TYP_ERROR);
			}
			if(StringUtil.isObjectEmpty(deptTransCtrl.getPertranslimit())){
				throw new PeException(DictErrors.PER_TRANS_LIMIT_NULL);
			}
			if (inputData.getTransamt().compareTo(deptTransCtrl.getPertranslimit()) > 0) {
				throw new PeException(DictErrors.PER_TRANS_LIMIT_OVER);
			}
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}

	}
}
