package com.csii.upp.dao.extend;

import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dao.generate.BatchcheckerrorDAO;
import com.csii.upp.dto.generate.Batchcheckerror;

public class BatchcheckerrorExtendDAO extends BasePayDAO {
	
	public static void insertBatchcheckerror(Batchcheckerror checkError){
		try {
			BatchcheckerrorDAO.insertSelective(checkError);
		} catch (SQLException e) {
			throw new PeRuntimeException("Insert Batchcheckerror Table Failed.", e);
		}
	}

	@SuppressWarnings("rawtypes")
	public static List getCheckerrors() {
		return (List) getSqlMap().queryForList("BATCHCHECKERROREXTEND.getBatchCheckErrors");
	}

}
