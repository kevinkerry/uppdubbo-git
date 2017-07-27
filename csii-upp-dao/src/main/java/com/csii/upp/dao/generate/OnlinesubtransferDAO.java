package com.csii.upp.dao.generate;

import java.sql.SQLException;
import java.util.List;

import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dto.generate.Onlinesubtransfer;
import com.csii.upp.dto.generate.OnlinesubtransferExample;

public class OnlinesubtransferDAO extends BasePayDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ONLINESUBTRANSFER
     *
     * @mbggenerated
     */
    public static int countByExample(OnlinesubtransferExample example) throws SQLException {
        Integer count = (Integer)  getSqlMap().queryForObject("ONLINESUBTRANSFER.countByExample", example);
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ONLINESUBTRANSFER
     *
     * @mbggenerated
     */
    public static int deleteByExample(OnlinesubtransferExample example) throws SQLException {
        int rows = getSqlMap().delete("ONLINESUBTRANSFER.deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ONLINESUBTRANSFER
     *
     * @mbggenerated
     */
    public static int deleteByPrimaryKey(String subtransseqnbr) throws SQLException {
        Onlinesubtransfer _key = new Onlinesubtransfer();
        _key.setSubtransseqnbr(subtransseqnbr);
        int rows = getSqlMap().delete("ONLINESUBTRANSFER.deleteByPrimaryKey", _key);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ONLINESUBTRANSFER
     *
     * @mbggenerated
     */
    public static void insert(Onlinesubtransfer record) throws SQLException {
        getSqlMap().insert("ONLINESUBTRANSFER.insert", record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ONLINESUBTRANSFER
     *
     * @mbggenerated
     */
    public static void insertSelective(Onlinesubtransfer record) throws SQLException {
        getSqlMap().insert("ONLINESUBTRANSFER.insertSelective", record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ONLINESUBTRANSFER
     *
     * @mbggenerated
     */
    @SuppressWarnings("unchecked")
    public static List<Onlinesubtransfer> selectByExample(OnlinesubtransferExample example) throws SQLException {
        List<Onlinesubtransfer> list = getSqlMap().queryForList("ONLINESUBTRANSFER.selectByExample", example);
        return list;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ONLINESUBTRANSFER
     *
     * @mbggenerated
     */
    public static Onlinesubtransfer selectByPrimaryKey(String subtransseqnbr) throws SQLException {
        Onlinesubtransfer _key = new Onlinesubtransfer();
        _key.setSubtransseqnbr(subtransseqnbr);
        Onlinesubtransfer record = (Onlinesubtransfer) getSqlMap().queryForObject("ONLINESUBTRANSFER.selectByPrimaryKey", _key);
        return record;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ONLINESUBTRANSFER
     *
     * @mbggenerated
     */
    public static int updateByExampleSelective(Onlinesubtransfer record, OnlinesubtransferExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMap().update("ONLINESUBTRANSFER.updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ONLINESUBTRANSFER
     *
     * @mbggenerated
     */
    public static int updateByExample(Onlinesubtransfer record, OnlinesubtransferExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMap().update("ONLINESUBTRANSFER.updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ONLINESUBTRANSFER
     *
     * @mbggenerated
     */
    public static int updateByPrimaryKeySelective(Onlinesubtransfer record) throws SQLException {
        int rows = getSqlMap().update("ONLINESUBTRANSFER.updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ONLINESUBTRANSFER
     *
     * @mbggenerated
     */
    public static int updateByPrimaryKey(Onlinesubtransfer record) throws SQLException {
        int rows = getSqlMap().update("ONLINESUBTRANSFER.updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table ONLINESUBTRANSFER
     *
     * @mbggenerated
     */
    protected static class UpdateByExampleParms extends OnlinesubtransferExample {
        private Object record;

        public UpdateByExampleParms(Object record, OnlinesubtransferExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}