package com.csii.upp.payment.action.mer;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.csii.ibs.action.IbsQueryAction;
import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dao.generate.CertinfoDAO;
import com.csii.upp.dto.generate.Certinfo;

public class MerCertUploadAction extends IbsQueryAction {
	@Override
	public void execute(Context context) throws PeException {

		Certinfo record = new Certinfo();
		record.setCertNbr(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
		record.setRelationNbr((String) context.getData("merId"));
		record.setCertType("1");// 商户证书
		record.setCreateDate(new Date());
		record.setMerCert((String) context.getData("merCert"));
		record.setModifyDate(new Date());
		record.setMerStatus((String) context.getData("merCertStatus"));// 未审核状态
		try {
			CertinfoDAO.insertSelective(record);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
