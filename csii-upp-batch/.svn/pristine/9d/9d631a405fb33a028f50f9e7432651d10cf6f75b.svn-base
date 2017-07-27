package com.csii.upp.batch.appl.cups;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.batch.appl.base.BaseAppl;
import com.csii.upp.constant.CheckFileDealFlag;
import com.csii.upp.constant.CheckFlag;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.dao.generate.CheckapplyDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.generate.Checkapply;
import com.csii.upp.dto.generate.CheckapplyExample;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 银联对账文件申请
 * 
 * @author 徐锦
 * 
 */
public class CheckFileApplyCupsAppl extends BaseAppl {
	private List<String> FIlENAMES = Arrays.asList("INDyyMMdd99FCP",
			"INDyyMMdd01ICOM", "INDyyMMdd99IERR", "INDyyMMdd32IPED",
			"INDyyMMdd32IERRPED", "INDyyMMdd99ILFEE");

	@Override
	protected void runAppl(Object entry, Map<String, Object> maps) {

		String fundChannelCd = StringUtil.parseObjectToString(maps
				.get(Dict.FCNM));

		// 对账日期
		Date checkDate = this.getCheckDate(maps);
		for (String fileNameFormat : FIlENAMES) {
			// 调用申请服务 返回结果(包含字段 处理码 处理结果，文件名) 现在服务未知所有下面的返回数据暂时写的测试值
			String dealmsg = "申请已提交";

			// TODO 文件路径INN15012288ZM_104110554991350
			String filename = fileNameFormat.replace(DateFormatCode.DATE_FORMATTER5, DateUtil
					.Date_To_DateTimeFormat(checkDate,
							DateFormatCode.DATE_FORMATTER5));

			Checkapply checkapply = new Checkapply();
			checkapply.setFundchannelcode(fundChannelCd);
			checkapply.setCheckdate(checkDate);
			checkapply.setCheckflag(CheckFlag.CHECKFLAG_WORK);
			checkapply.setDealmsg(dealmsg);
			checkapply.setFilename(filename);
			checkapply.setDealcode(CheckFileDealFlag.UnDeal);
			try {
				CheckapplyExample example = new CheckapplyExample();
				example.createCriteria().andCheckdateEqualTo(checkapply.getCheckdate())
						.andFundchannelcodeEqualTo(checkapply.getFundchannelcode())
						.andFilenameEqualTo(checkapply.getFilename());
				List<Checkapply> checkapplys=CheckapplyDAO.selectByExample(example);
				if (checkapplys==null||checkapplys.isEmpty()) {
					CheckapplyDAO.insertSelective(checkapply);
				}else{
					CheckapplyDAO.updateByPrimaryKeySelective(checkapply);
				}
			} catch (SQLException e) {
				throw new PeRuntimeException("Inser/Update Checkapply Table Failed.", e);
			}
		}
	}

}
