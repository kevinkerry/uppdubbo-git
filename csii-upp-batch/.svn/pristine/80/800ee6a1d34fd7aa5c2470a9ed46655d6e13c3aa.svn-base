package com.csii.upp.batch.appl.unionpay;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.csii.pe.core.PeException;
import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.batch.appl.base.BaseApplyFileAppl;
import com.csii.upp.constant.CheckFileDealFlag;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dao.generate.InnercheckapplyDAO;
import com.csii.upp.dao.generate.SysinfoDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Innercheckapply;
import com.csii.upp.dto.router.unionpay.RespCheckFileApply;
import com.csii.upp.service.fundprocess.UnionPayService;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 银联对账文件申请
 * 
 * @author 李超
 * 
 */
public class CheckFileApplyUnionPayAppl extends BaseApplyFileAppl {

	@Override
	protected String getFundChannelCd(Map<String, Object> argMaps) {
		return StringUtil.parseObjectToString(argMaps.get(Dict.FCNM));
	}

	@Override
	protected void applyCheckFile(final Innercheckapply innercheckapply, final Map<String, Object> maps) {
		final String fundChannelCd = innercheckapply.getDownsysnbr();

		final Date checkFDPSDate = this.getCheckDate(maps);
		Date checkDate = null;
		try {
			checkDate = SysinfoDAO.selectByPrimaryKey(fundChannelCd).getPrevpostdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		final Date checkDateFinal = checkDate;

		log.info("申请文件商户号MEID：" + maps.get("MEID").toString());
		
		getTransactionTemplate().execute(new TransactionCallback() {

			@Override
			public Object doInTransaction(TransactionStatus arg0) {
				List<String> meids = Arrays.asList(maps.get("MEID").toString().split(","));
				for(String meid : meids){
					InputFundData data = new InputFundData();
					data.setTransdate(checkDateFinal);
					data.setMerId(meid);
					data.setTranstime(DateUtil.getCurrentDateTime());

					RespCheckFileApply output = null;
					UnionPayService unionPayService = (UnionPayService) getRouterService(fundChannelCd.toLowerCase());
					try {
						output = (RespCheckFileApply) unionPayService.applyCheckFile(data);
					} catch (PeException e) {
						log.error("unionpay check file apply error:", e);
					}
					//"00"代表受理，"98"代表无文件
					if (output == null || (!"00".equals(output.getRespCode()) && !"98".equals(output.getRespCode()))) {
						innercheckapply.setDealmsg(formatReturnMsg(output.getReturncode(), output.getReturnmsg()));
						throw new PeRuntimeException(innercheckapply.getDealmsg());
					}

					innercheckapply.setCheckstartdate(checkFDPSDate);
					innercheckapply.setCheckenddate(checkFDPSDate);
					innercheckapply.setUppersysnbr(FundChannelCode.FDPS);
					if (output.getFileName() != null) {
							String filename = "INN"
									+ DateUtil.Date_To_DateTimeFormat(checkDateFinal, DateFormatCode.DATE_FORMATTER3).substring(2)
									+ "88ZM_" + data.getMerId();
							log.info("银联申请的对账文件名：" + filename);
						
							innercheckapply.setFilename(filename);
					}else {
						innercheckapply.setFilename("无文件");
						innercheckapply.setDealcode(CheckFileDealFlag.Dealed);
					}
					//不是最后一个，就在这里插入，最后一个在父类里面插入
					try {
						if (StringUtil.isStringEmpty(innercheckapply.getCheckapplynbr())) {
							innercheckapply.setCheckapplynbr(IDGenerateFactory.generateSeqId());
						}
						InnercheckapplyDAO.insertSelective(innercheckapply);
						innercheckapply.setCheckapplynbr(null);
					} catch (SQLException e) {
						log.error(e);
					}
				}
				return null;
			}
		});
	}
}
