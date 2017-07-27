package com.csii.upp.dao.generate;

import java.sql.SQLException;
import java.util.List;

import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dto.generate.Queapplparam;
import com.csii.upp.dto.generate.QueapplparamExample;

public class QueapplparamDAO extends BasePayDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUEAPPLPARAM
	 * @mbggenerated
	 */
	public static int countByExample(QueapplparamExample example) throws SQLException {
		Integer count = (Integer) getSqlMap().queryForObject(
				"QUEAPPLPARAM.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUEAPPLPARAM
	 * @mbggenerated
	 */
	public static int deleteByExample(QueapplparamExample example) throws SQLException {
		int rows = getSqlMap().delete("QUEAPPLPARAM.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUEAPPLPARAM
	 * @mbggenerated
	 */
	public static int deleteByPrimaryKey(Long quenbr, Long quesubnbr)
			throws SQLException {
		Queapplparam _key = new Queapplparam();
		_key.setQuenbr(quenbr);
		_key.setQuesubnbr(quesubnbr);
		int rows = getSqlMap().delete("QUEAPPLPARAM.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUEAPPLPARAM
	 * @mbggenerated
	 */
	public static void insert(Queapplparam record) throws SQLException {
		getSqlMap().insert("QUEAPPLPARAM.insert", record);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUEAPPLPARAM
	 * @mbggenerated
	 */
	public static void insertSelective(Queapplparam record) throws SQLException {
		getSqlMap().insert("QUEAPPLPARAM.insertSelective", record);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUEAPPLPARAM
	 * @mbggenerated
	 */
	@SuppressWarnings("unchecked")
	public static List<Queapplparam> selectByExample(QueapplparamExample example)
			throws SQLException {
		List<Queapplparam> list = getSqlMap().queryForList(
				"QUEAPPLPARAM.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUEAPPLPARAM
	 * @mbggenerated
	 */
	public static Queapplparam selectByPrimaryKey(Long quenbr, Long quesubnbr)
			throws SQLException {
		Queapplparam _key = new Queapplparam();
		_key.setQuenbr(quenbr);
		_key.setQuesubnbr(quesubnbr);
		Queapplparam record = (Queapplparam) getSqlMap().queryForObject(
				"QUEAPPLPARAM.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUEAPPLPARAM
	 * @mbggenerated
	 */
	public static int updateByExampleSelective(Queapplparam record,
			QueapplparamExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMap().update("QUEAPPLPARAM.updateByExampleSelective",
				parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUEAPPLPARAM
	 * @mbggenerated
	 */
	public static int updateByExample(Queapplparam record, QueapplparamExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMap().update("QUEAPPLPARAM.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUEAPPLPARAM
	 * @mbggenerated
	 */
	public static int updateByPrimaryKeySelective(Queapplparam record)
			throws SQLException {
		int rows = getSqlMap().update(
				"QUEAPPLPARAM.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUEAPPLPARAM
	 * @mbggenerated
	 */
	public static int updateByPrimaryKey(Queapplparam record) throws SQLException {
		int rows = getSqlMap().update("QUEAPPLPARAM.updateByPrimaryKey",
				record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table QUEAPPLPARAM
	 * @mbggenerated
	 */
	protected static class UpdateByExampleParms extends QueapplparamExample {
		private Object record;

		public UpdateByExampleParms(Object record, QueapplparamExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}