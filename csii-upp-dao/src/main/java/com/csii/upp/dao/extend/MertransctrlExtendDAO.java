package com.csii.upp.dao.extend;

import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dto.generate.Mertransctrl;

public class MertransctrlExtendDAO  extends BasePayDAO {
	public static List<String> queryMerPayTyp(String merNbr,String status) throws PeException  {
		Mertransctrl record=new Mertransctrl();
		record.setMernbr(merNbr);
		record.setStatus(status);
		return (List<String>)getSqlMap().queryForList("MERTRANSCTRLEXTEND.queryMerPayTyp", record);
	}

	public static List<Map> queryPointMer(String payTypCd,String status) throws PeException  {
		Mertransctrl record=new Mertransctrl();
		record.setPaytypcd(payTypCd);
		record.setStatus(status);
		return (List<Map>)getSqlMap().queryForList("MERTRANSCTRLEXTEND.queryPointMer", record);
	}	
	
}
