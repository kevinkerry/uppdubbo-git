package com.csii.upp.dao.generate;

import java.sql.SQLException;
import java.util.List;

import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dto.generate.Fundchannelcleartranshistall;
import com.csii.upp.dto.generate.FundchannelcleartranshistallExample;

public class FundchannelcleartranshistallDAO extends BasePayDAO {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDCHANNELCLEARTRANSHISTALL
	 * @mbggenerated
	 */
	public static int countByExample(FundchannelcleartranshistallExample example)
			throws SQLException {
		Integer count = (Integer) getSqlMap().queryForObject(
				"FUNDCHANNELCLEARTRANSHISTALL.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDCHANNELCLEARTRANSHISTALL
	 * @mbggenerated
	 */
	public static int deleteByExample(FundchannelcleartranshistallExample example)
			throws SQLException {
		int rows = getSqlMap().delete(
				"FUNDCHANNELCLEARTRANSHISTALL.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDCHANNELCLEARTRANSHISTALL
	 * @mbggenerated
	 */
	public static int deleteByPrimaryKey(String cleartransnbr) throws SQLException {
		Fundchannelcleartranshistall _key = new Fundchannelcleartranshistall();
		_key.setCleartransnbr(cleartransnbr);
		int rows = getSqlMap().delete(
				"FUNDCHANNELCLEARTRANSHISTALL.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDCHANNELCLEARTRANSHISTALL
	 * @mbggenerated
	 */
	public static void insert(Fundchannelcleartranshistall record) throws SQLException {
		getSqlMap().insert("FUNDCHANNELCLEARTRANSHISTALL.insert", record);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDCHANNELCLEARTRANSHISTALL
	 * @mbggenerated
	 */
	public static void insertSelective(Fundchannelcleartranshistall record)
			throws SQLException {
		getSqlMap().insert("FUNDCHANNELCLEARTRANSHISTALL.insertSelective",
				record);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDCHANNELCLEARTRANSHISTALL
	 * @mbggenerated
	 */
	@SuppressWarnings("unchecked")
	public static List<Fundchannelcleartranshistall> selectByExample(
			FundchannelcleartranshistallExample example) throws SQLException {
		List<Fundchannelcleartranshistall> list = getSqlMap().queryForList(
				"FUNDCHANNELCLEARTRANSHISTALL.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDCHANNELCLEARTRANSHISTALL
	 * @mbggenerated
	 */
	public static Fundchannelcleartranshistall selectByPrimaryKey(String cleartransnbr)
			throws SQLException {
		Fundchannelcleartranshistall _key = new Fundchannelcleartranshistall();
		_key.setCleartransnbr(cleartransnbr);
		Fundchannelcleartranshistall record = (Fundchannelcleartranshistall) getSqlMap()
				.queryForObject(
						"FUNDCHANNELCLEARTRANSHISTALL.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDCHANNELCLEARTRANSHISTALL
	 * @mbggenerated
	 */
	public static int updateByExampleSelective(Fundchannelcleartranshistall record,
			FundchannelcleartranshistallExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMap().update(
				"FUNDCHANNELCLEARTRANSHISTALL.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDCHANNELCLEARTRANSHISTALL
	 * @mbggenerated
	 */
	public static int updateByExample(Fundchannelcleartranshistall record,
			FundchannelcleartranshistallExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMap().update(
				"FUNDCHANNELCLEARTRANSHISTALL.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDCHANNELCLEARTRANSHISTALL
	 * @mbggenerated
	 */
	public static int updateByPrimaryKeySelective(Fundchannelcleartranshistall record)
			throws SQLException {
		int rows = getSqlMap().update(
				"FUNDCHANNELCLEARTRANSHISTALL.updateByPrimaryKeySelective",
				record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FUNDCHANNELCLEARTRANSHISTALL
	 * @mbggenerated
	 */
	public static int updateByPrimaryKey(Fundchannelcleartranshistall record)
			throws SQLException {
		int rows = getSqlMap().update(
				"FUNDCHANNELCLEARTRANSHISTALL.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table FUNDCHANNELCLEARTRANSHISTALL
	 * @mbggenerated
	 */
	protected static class UpdateByExampleParms extends
			FundchannelcleartranshistallExample {
		private Object record;

		public UpdateByExampleParms(Object record,
				FundchannelcleartranshistallExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}