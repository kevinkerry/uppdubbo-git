package com.csii.upp.batch.action;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.action.BaseAction;
import com.csii.upp.dao.generate.FeeparamDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.generate.Feeparam;
import com.csii.upp.dto.generate.FeeparamExample;
import com.csii.upp.service.FeeService;
import com.csii.upp.util.StringUtil;

/**
 * 计算手续费
 * 
 * @author 姜星
 *
 */
public class CalculateFeeAction extends BaseAction {

	@Override
	public void execute(Context arg0) throws PeException {
		FeeService feeService = (FeeService) this.getFeeService();
		String feeNbr = arg0.getString(Dict.FEE_NBR);
		BigDecimal transAmt = arg0.getBigDecimal(Dict.TRANS_AMT);
		Map map = null;
		try {
			FeeparamExample example = new FeeparamExample();
			example.createCriteria().andFeenbrEqualTo(feeNbr);
			example.setOrderByClause("SEQNBR");
			List<Feeparam> fpList = FeeparamDAO.selectByExample(example);
			map = feeService.calculateFee(fpList, transAmt, null);
		} catch (Exception e) {
			log.error("error",e);
		}
		arg0.setData("feeAmt", StringUtil.parseBigDecimal(map.get(Dict.FEE_AMT)));
	}
}
