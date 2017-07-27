package com.csii.upp.batch.appl.core;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.batch.appl.base.BaseApplyFileAppl;
import com.csii.upp.constant.CheckFileDealFlag;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dao.generate.InnercheckapplyDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Innercheckapply;
import com.csii.upp.dto.generate.InnercheckapplyExample;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.service.fundprocess.CoreService;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 老核心对账文件申请
 * 
 * @author chen chao
 * 
 */
public class CheckFileApplyCoreAppl extends BaseApplyFileAppl {

	@Override
	protected String getFundChannelCd(Map<String, Object> argMaps) {
		return FundChannelCode.CORE;
	}

	@Override
	protected void applyCheckFile(Innercheckapply innercheckapply, Map<String, Object> maps) {
		Date checkDate = this.getCheckDate(maps);
		if (!this.isCoreCheckData(maps)) {
			try {
				InnercheckapplyExample innercheckapplyExample = new InnercheckapplyExample();
				innercheckapplyExample.createCriteria().andDownsysnbrEqualTo(FundChannelCode.CORE).andCheckstartdateEqualTo(checkDate)
					.andDealcodeEqualTo(CheckFileDealFlag.Checked);
				List<Innercheckapply> innercheckapplies = InnercheckapplyDAO.selectByExample(innercheckapplyExample);
				if(innercheckapplies == null || innercheckapplies.size() < 1){
					throw new PeRuntimeException("核心【" + checkDate + "】日期批量未跑批失败，银联批量不能启动");
				}
			} catch (Exception e) {
				throw new PeRuntimeException(e);
			}
			checkDate = DateUtil.addDate(checkDate, 1);
		}
		innercheckapply.setCheckstartdate(checkDate);
		innercheckapply.setCheckenddate(checkDate);
		// 申请对账文件
		innercheckapply.setFilename(new StringBuilder().append(getFundChannelCd(maps)+DateUtil.Date_To_DateFormat(checkDate)).append(".txt").toString());
		CoreService coreService = (CoreService) this.getRouterService(innercheckapply.getDownsysnbr().toLowerCase());
		InputFundData data = new InputFundData();
		data.setTransdate(checkDate);
		data.setTransnbr(innercheckapply.getCheckapplynbr());
		data.setCheckdataflag(StringUtil.parseObjectToString(maps.get(Dict.DZBZ)));
		RespSysHead output = null;
		try {
			output = coreService.applyCheckFile(data);
		} catch (PeException e1) {
			throw new PeRuntimeException(e1);
		}
		innercheckapply.setCheckapplynbr(data.getTransnbr());
		// 如果申请失败抛出异常
		if (!output.isSuccess() || output == null) {
			innercheckapply.setDealmsg(this.formatReturnMsg(output.getReturncode(), output.getReturnmsg()));
			throw new PeRuntimeException(innercheckapply.getDealmsg());
		}
	}
}
