package com.csii.upp.dao.generate;

import java.sql.SQLException;
import java.util.List;

import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dto.generate.Quetyp;
import com.csii.upp.dto.generate.QuetypExample;

public class QuetypDAO extends BasePayDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUETYP
	 * @mbggenerated
	 */
	public static int countByExample(QuetypExample example) throws SQLException {
		Integer count = (Integer) getSqlMap().queryForObject(
				"QUETYP.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUETYP
	 * @mbggenerated
	 */
	public static int deleteByExample(QuetypExample example) throws SQLException {
		int rows = getSqlMap().delete("QUETYP.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUETYP
	 * @mbggenerated
	 */
	public static int deleteByPrimaryKey(String quetypcd) throws SQLException {
		Quetyp _key = new Quetyp();
		_key.setQuetypcd(quetypcd);
		int rows = getSqlMap().delete("QUETYP.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUETYP
	 * @mbggenerated
	 */
	public static void insert(Quetyp record) throws SQLException {
		getSqlMap().insert("QUETYP.insert", record);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUETYP
	 * @mbggenerated
	 */
	public static void insertSelective(Quetyp record) throws SQLException {
		getSqlMap().insert("QUETYP.insertSelective", record);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUETYP
	 * @mbggenerated
	 */
	@SuppressWarnings("unchecked")
	public static List<Quetyp> selectByExample(QuetypExample example)
			throws SQLException {
		List<Quetyp> list = getSqlMap().queryForList("QUETYP.selectByExample",
				example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUETYP
	 * @mbggenerated
	 */
	public static Quetyp selectByPrimaryKey(String quetypcd) throws SQLException {
		Quetyp _key = new Quetyp();
		_key.setQuetypcd(quetypcd);
		Quetyp record = (Quetyp) getSqlMap().queryForObject(
				"QUETYP.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUETYP
	 * @mbggenerated
	 */
	public static int updateByExampleSelective(Quetyp record, QuetypExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMap().update("QUETYP.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUETYP
	 * @mbggenerated
	 */
	public static int updateByExample(Quetyp record, QuetypExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMap().update("QUETYP.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUETYP
	 * @mbggenerated
	 */
	public static int updateByPrimaryKeySelective(Quetyp record) throws SQLException {
		int rows = getSqlMap().update("QUETYP.updateByPrimaryKeySelective",
				record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUETYP
	 * @mbggenerated
	 */
	public static int updateByPrimaryKey(Quetyp record) throws SQLException {
		int rows = getSqlMap().update("QUETYP.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table QUETYP
	 * @mbggenerated
	 */
	protected static class UpdateByExampleParms extends QuetypExample {
		private Object record;

		public UpdateByExampleParms(Object record, QuetypExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}