package com.csii.upp.dao.extend;

import java.sql.SQLException;

import com.csii.pe.core.PeException;
import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dto.generate.Channellimitrout;
import com.csii.upp.dto.generate.Fundchannel;

public class ChannellimitroutExtendDAO extends BasePayDAO {
	/**
	 * 资金通道路由计算
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 * @throws SQLException 
	 */
	public static Fundchannel queryChannelRouter(Channellimitrout channelRout)
			throws SQLException {
		Fundchannel record = (Fundchannel) queryForObject("CHANNELLIMITROUT.queryChannelRouter", channelRout);
		return record;
	}


}