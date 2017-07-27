package com.csii.upp.batch.appl.base;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.constant.CheckFileDealFlag;
import com.csii.upp.constant.CheckFlag;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dao.extend.SysinfoExtendDAO;
import com.csii.upp.dao.generate.ChecknoticeDAO;
import com.csii.upp.dao.generate.InnercheckapplyDAO;
import com.csii.upp.dto.generate.Checknotice;
import com.csii.upp.dto.generate.ChecknoticeExample;
import com.csii.upp.dto.generate.Innercheckapply;
import com.csii.upp.dto.generate.InnercheckapplyExample;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.StringUtil;

/**
 * 对账文件申请入口
 * 
 * @author xujin
 *
 */
public abstract class BaseApplyFileAppl extends BaseAppl {
	/**
	 * 资金渠道代码
	 * 
	 * @return
	 */
	protected abstract String getFundChannelCd(Map<String, Object> argMaps);

	/**
	 * 申请对账文件
	 * 
	 * @return
	 */
	protected abstract void applyCheckFile(Innercheckapply innercheckapply, Map<String, Object> maps);

	@Override
	protected void runAppl(Object entry, Map<String, Object> maps) throws SQLException {

		if (this.isCoreCheckData(maps)) {// 核心对账
			// 如果核心未日切，不发申请
			// Date coreCheckDate = getCoreAccountDate(maps);
			Date checkDate = this.getCheckDate(maps);
			// if(coreCheckDate!=null){
			// //获取支付平台当前时间
			// //当前时间>核心日期
			// if(checkDate.after(coreCheckDate)){
			// log.debug("核心日期["+coreCheckDate+"]尚未日切，退出对账文件申请");
			// return;
			// }
			// }else{
			// log.error("无法获取核心账务日期,申请对账文件程序终止！");
			// return ;
			// }

			// 如果已经收到对账文件通知，不发申请
			// 判断是否核心
			boolean coreCheckFileApplied = isCoreCheckFileApplied(checkDate, FundChannelCode.CORE);
			if (coreCheckFileApplied) {// 文件已存在，不再获取
				log.debug("核心对账文件已存在，日期[" + checkDate + "]!");
				return;
			}
		}

		String fundChannelCd = getFundChannelCd(maps);
		Innercheckapply innercheckapply = new Innercheckapply();
		innercheckapply.setUppersysnbr(FundChannelCode.FDPS);
		innercheckapply.setDownsysnbr(fundChannelCd);
		innercheckapply.setCheckflag(CheckFlag.CHECKFLAG_WORK);
		innercheckapply.setDealcode(CheckFileDealFlag.UnDeal);
		// innercheckapply.setDealcode(CheckFileDealFlag.Dealed);
		innercheckapply.setDealmsg("申请已提交");
		innercheckapply.setDealmsg("初始化");
		innercheckapply.setFilename("IPP_1.txt");
		innercheckapply.setCheckstartdate(SysinfoExtendDAO.getSysInfo().getPrevpostdate());
		innercheckapply.setCheckenddate(SysinfoExtendDAO.getSysInfo().getPrevpostdate());
		innercheckapply.setCheckapplynbr(IDGenerateFactory.generateSeqId());
		InnercheckapplyDAO.insertSelective(innercheckapply);
		try {
			/*
			 * POC测试添加 注掉下面部分代码
			 */
			// 申请对账文件
			// this.applyCheckFile(innercheckapply, maps);
			if (!FundChannelCode.UNIONPAY.equals(innercheckapply.getDownsysnbr())) {
				// this.handleCheckApply(innercheckapply);
			}
		} catch (Exception e) {
			throw new PeRuntimeException(e.getMessage());
		}
	}

	protected void handleCheckApply(final Innercheckapply innercheckapply) {
		try {
			if (StringUtil.isStringEmpty(innercheckapply.getFilename())) {
				innercheckapply.setFilename("无文件");
				innercheckapply.setDealcode(CheckFileDealFlag.Dealed);
			}
			InnercheckapplyExample example = new InnercheckapplyExample();
			example.createCriteria().andDownsysnbrEqualTo(innercheckapply.getDownsysnbr())
					.andCheckstartdateEqualTo(innercheckapply.getCheckstartdate())
					.andFilenameEqualTo(innercheckapply.getFilename());
			InnercheckapplyDAO.deleteByExample(example);
			if(StringUtil.isStringEmpty(innercheckapply.getCheckapplynbr())){
				innercheckapply.setCheckapplynbr(IDGenerateFactory.generateSeqId());
			}
			InnercheckapplyDAO.insertSelective(innercheckapply);
		} catch (SQLException e) {
			throw new PeRuntimeException("Inser/Update innercheckapply Table Failed.", e);
		}
	}

	/**
	 * 格式化申请对账文件接口返回信息
	 * 
	 * @param returnCode
	 * @param returnMsg
	 * @return
	 */
	protected String formatReturnMsg(String returnCode, String returnMsg) {
		String msg = returnCode == null ? "" : "[" + returnCode + "]" + returnMsg;
		return msg;
	}

	/**
	 * 0O3104 无对应渠道的往来账信息(与人行对账完成)，无需再次申请 0O3102 无对账信息(人行没有下发对账文件[节假日])，无需再次申请
	 * 
	 * @param returnCode
	 * @param checkapply
	 * @return
	 */
	protected void valReturnCode(String returnCode, Innercheckapply innercheckapply) {
		if (FundChannelCode.UNIONPAY.equals(innercheckapply.getDownsysnbr())) {
		} else {
			returnCode = returnCode.substring(2, returnCode.length());
			if (!"0O3102".equals(returnCode) && !"0O3104".equals(returnCode)) {
				throw new PeRuntimeException(innercheckapply.getDealmsg());
			}
			innercheckapply.setCheckflag(CheckFlag.CHECKFLAG_UNWORK);
			innercheckapply.setDealcode(CheckFileDealFlag.Dealed);
		}
	}

	/**
	 * 
	 * @param checkDate
	 * @param fundChannelCd
	 * @return
	 * @author wangtao 判断柜面核心对账文件是否已经申请到
	 */
	private boolean isCoreCheckFileApplied(Date checkDate, String fundChannelCd) {
		ChecknoticeExample example = new ChecknoticeExample();
		example.createCriteria().andCheckdateEqualTo(checkDate).andFundchannelcodeEqualTo(fundChannelCd);
		List<Checknotice> checknotices;
		try {
			checknotices = ChecknoticeDAO.selectByExample(example);
		} catch (SQLException e) {
			throw new PeRuntimeException("Get Innercheckapply Failed.");
		}
		if (checknotices.isEmpty()) {
			return false;
		}
		return true;

	}

}
