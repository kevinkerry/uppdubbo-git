package com.csii.upp.dao.generate;

import java.sql.SQLException;
import java.util.List;

import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dto.generate.Bankinfo;
import com.csii.upp.dto.generate.BankinfoExample;

public class BankinfoDAO extends BasePayDAO {

	/**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BANKINFO
     *
     * @mbggenerated
     */
    public static int countByExample(BankinfoExample example) throws SQLException {
        Integer count = (Integer)  getSqlMap().queryForObject("BANKINFO.countByExample", example);
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BANKINFO
     *
     * @mbggenerated
     */
    public static int deleteByExample(BankinfoExample example) throws SQLException {
        int rows = getSqlMap().delete("BANKINFO.deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BANKINFO
     *
     * @mbggenerated
     */
    public static int deleteByPrimaryKey(String banknbr) throws SQLException {
        Bankinfo _key = new Bankinfo();
        _key.setBanknbr(banknbr);
        int rows = getSqlMap().delete("BANKINFO.deleteByPrimaryKey", _key);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BANKINFO
     *
     * @mbggenerated
     */
    public static void insert(Bankinfo record) throws SQLException {
        getSqlMap().insert("BANKINFO.insert", record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BANKINFO
     *
     * @mbggenerated
     */
    public static void insertSelective(Bankinfo record) throws SQLException {
        getSqlMap().insert("BANKINFO.insertSelective", record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BANKINFO
     *
     * @mbggenerated
     */
    @SuppressWarnings("unchecked")
    public static List<Bankinfo> selectByExample(BankinfoExample example) throws SQLException {
        List<Bankinfo> list = getSqlMap().queryForList("BANKINFO.selectByExample", example);
        return list;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BANKINFO
     *
     * @mbggenerated
     */
    public static Bankinfo selectByPrimaryKey(String banknbr) throws SQLException {
        Bankinfo _key = new Bankinfo();
        _key.setBanknbr(banknbr);
        Bankinfo record = (Bankinfo) getSqlMap().queryForObject("BANKINFO.selectByPrimaryKey", _key);
        return record;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BANKINFO
     *
     * @mbggenerated
     */
    public static int updateByExampleSelective(Bankinfo record, BankinfoExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMap().update("BANKINFO.updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BANKINFO
     *
     * @mbggenerated
     */
    public static int updateByExample(Bankinfo record, BankinfoExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMap().update("BANKINFO.updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BANKINFO
     *
     * @mbggenerated
     */
    public static int updateByPrimaryKeySelective(Bankinfo record) throws SQLException {
        int rows = getSqlMap().update("BANKINFO.updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BANKINFO
     *
     * @mbggenerated
     */
    public static int updateByPrimaryKey(Bankinfo record) throws SQLException {
        int rows = getSqlMap().update("BANKINFO.updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table BANKINFO
     *
     * @mbggenerated
     */
    protected static class UpdateByExampleParms extends BankinfoExample {
        private Object record;

        public UpdateByExampleParms(Object record, BankinfoExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}