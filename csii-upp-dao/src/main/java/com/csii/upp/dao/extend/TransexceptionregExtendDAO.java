package com.csii.upp.dao.extend;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dao.generate.TransexceptionregDAO;
import com.csii.upp.dto.generate.Transexceptionreg;
import com.csii.upp.dto.generate.TransexceptionregExample;

public class TransexceptionregExtendDAO  extends BasePayDAO {
	
	public static Transexceptionreg getTransexceptionreg(String expSeqNbr) {
		Transexceptionreg rtxnExp;
		try {
			rtxnExp=TransexceptionregDAO.selectByPrimaryKey(expSeqNbr);
		} catch (SQLException e) {
			return null;
		}
		return rtxnExp;
	}
	
	public static List<Transexceptionreg> getTransexceptionreg(List<String> list, Date postdate) {
		TransexceptionregExample example = new TransexceptionregExample();
		//List<Long> list = new ArrayList<Long>(Arrays.asList(1L,2L,5L));
		example.createCriteria().andExphandlestatusIn(list).andTransdateEqualTo(postdate);
		example.setOrderByClause("EXPSEQNBR asc");
		List<Transexceptionreg> rtxnExps;
		try {
			rtxnExps=TransexceptionregDAO.selectByExample(example);
		} catch (SQLException e) {
			throw new PeRuntimeException("Get Transexceptionreg Table Failed.",
					e);
		}
		return rtxnExps;
	}
	
	public static void updateTransexceptionreg(Transexceptionreg rtxnExcep) {
		try {
			if (TransexceptionregDAO.updateByPrimaryKeySelective(rtxnExcep) == 0) {
				throw new PeRuntimeException(
						"Update Transexceptionreg Failed for unknown reason.");
			}
		} catch (SQLException e) {
			throw new PeRuntimeException("Update Transexceptionreg Table Failed.",
					e);
		}
	}
}
