package com.csii.upp.dao.generate;

import java.sql.SQLException;
import java.util.List;

import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dto.generate.Custbindcardinfo;
import com.csii.upp.dto.generate.CustbindcardinfoExample;

public class CustbindcardinfoDAO extends BasePayDAO {
	/**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CUSTBINDCARDINFO
     *
     * @mbggenerated
     */
    public static int countByExample(CustbindcardinfoExample example) throws SQLException {
        Integer count = (Integer)  getSqlMap().queryForObject("CUSTBINDCARDINFO.countByExample", example);
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CUSTBINDCARDINFO
     *
     * @mbggenerated
     */
    public static int deleteByExample(CustbindcardinfoExample example) throws SQLException {
        int rows = getSqlMap().delete("CUSTBINDCARDINFO.deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CUSTBINDCARDINFO
     *
     * @mbggenerated
     */
    public static int deleteByPrimaryKey(String custnbr) throws SQLException {
        Custbindcardinfo _key = new Custbindcardinfo();
        _key.setCustnbr(custnbr);
        int rows = getSqlMap().delete("CUSTBINDCARDINFO.deleteByPrimaryKey", _key);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CUSTBINDCARDINFO
     *
     * @mbggenerated
     */
    public static void insert(Custbindcardinfo record) throws SQLException {
        getSqlMap().insert("CUSTBINDCARDINFO.insert", record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CUSTBINDCARDINFO
     *
     * @mbggenerated
     */
    public static void insertSelective(Custbindcardinfo record) throws SQLException {
        getSqlMap().insert("CUSTBINDCARDINFO.insertSelective", record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CUSTBINDCARDINFO
     *
     * @mbggenerated
     */
    @SuppressWarnings("unchecked")
    public static List<Custbindcardinfo> selectByExample(CustbindcardinfoExample example) throws SQLException {
        List<Custbindcardinfo> list = getSqlMap().queryForList("CUSTBINDCARDINFO.selectByExample", example);
        return list;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CUSTBINDCARDINFO
     *
     * @mbggenerated
     */
    public static Custbindcardinfo selectByPrimaryKey(String custnbr) throws SQLException {
        Custbindcardinfo _key = new Custbindcardinfo();
        _key.setCustnbr(custnbr);
        Custbindcardinfo record = (Custbindcardinfo) getSqlMap().queryForObject("CUSTBINDCARDINFO.selectByPrimaryKey", _key);
        return record;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CUSTBINDCARDINFO
     *
     * @mbggenerated
     */
    public static int updateByExampleSelective(Custbindcardinfo record, CustbindcardinfoExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMap().update("CUSTBINDCARDINFO.updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CUSTBINDCARDINFO
     *
     * @mbggenerated
     */
    public static int updateByExample(Custbindcardinfo record, CustbindcardinfoExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMap().update("CUSTBINDCARDINFO.updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CUSTBINDCARDINFO
     *
     * @mbggenerated
     */
    public static int updateByPrimaryKeySelective(Custbindcardinfo record) throws SQLException {
        int rows = getSqlMap().update("CUSTBINDCARDINFO.updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CUSTBINDCARDINFO
     *
     * @mbggenerated
     */
    public static int updateByPrimaryKey(Custbindcardinfo record) throws SQLException {
        int rows = getSqlMap().update("CUSTBINDCARDINFO.updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table CUSTBINDCARDINFO
     *
     * @mbggenerated
     */
    protected static class UpdateByExampleParms extends CustbindcardinfoExample {
        private Object record;

        public UpdateByExampleParms(Object record, CustbindcardinfoExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}