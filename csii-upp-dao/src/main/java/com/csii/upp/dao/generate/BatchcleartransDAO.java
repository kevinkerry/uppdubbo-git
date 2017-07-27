package com.csii.upp.dao.generate;

import java.sql.SQLException;
import java.util.List;

import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dto.generate.Batchcleartrans;
import com.csii.upp.dto.generate.BatchcleartransExample;

public class BatchcleartransDAO extends BasePayDAO {
	/**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHCLEARTRANS
     *
     * @mbggenerated
     */
    public static int countByExample(BatchcleartransExample example) throws SQLException {
        Integer count = (Integer)  getSqlMap().queryForObject("BATCHCLEARTRANS.countByExample", example);
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHCLEARTRANS
     *
     * @mbggenerated
     */
    public static int deleteByExample(BatchcleartransExample example) throws SQLException {
        int rows = getSqlMap().delete("BATCHCLEARTRANS.deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHCLEARTRANS
     *
     * @mbggenerated
     */
    public static int deleteByPrimaryKey(String transseqnbr) throws SQLException {
        Batchcleartrans _key = new Batchcleartrans();
        _key.setTransseqnbr(transseqnbr);
        int rows = getSqlMap().delete("BATCHCLEARTRANS.deleteByPrimaryKey", _key);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHCLEARTRANS
     *
     * @mbggenerated
     */
    public static void insert(Batchcleartrans record) throws SQLException {
        getSqlMap().insert("BATCHCLEARTRANS.insert", record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHCLEARTRANS
     *
     * @mbggenerated
     */
    public static void insertSelective(Batchcleartrans record) throws SQLException {
        getSqlMap().insert("BATCHCLEARTRANS.insertSelective", record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHCLEARTRANS
     *
     * @mbggenerated
     */
    @SuppressWarnings("unchecked")
    public static List<Batchcleartrans> selectByExample(BatchcleartransExample example) throws SQLException {
        List<Batchcleartrans> list = getSqlMap().queryForList("BATCHCLEARTRANS.selectByExample", example);
        return list;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHCLEARTRANS
     *
     * @mbggenerated
     */
    public static Batchcleartrans selectByPrimaryKey(String transseqnbr) throws SQLException {
        Batchcleartrans _key = new Batchcleartrans();
        _key.setTransseqnbr(transseqnbr);
        Batchcleartrans record = (Batchcleartrans) getSqlMap().queryForObject("BATCHCLEARTRANS.selectByPrimaryKey", _key);
        return record;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHCLEARTRANS
     *
     * @mbggenerated
     */
    public static int updateByExampleSelective(Batchcleartrans record, BatchcleartransExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMap().update("BATCHCLEARTRANS.updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHCLEARTRANS
     *
     * @mbggenerated
     */
    public static int updateByExample(Batchcleartrans record, BatchcleartransExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMap().update("BATCHCLEARTRANS.updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHCLEARTRANS
     *
     * @mbggenerated
     */
    public static int updateByPrimaryKeySelective(Batchcleartrans record) throws SQLException {
        int rows = getSqlMap().update("BATCHCLEARTRANS.updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHCLEARTRANS
     *
     * @mbggenerated
     */
    public static int updateByPrimaryKey(Batchcleartrans record) throws SQLException {
        int rows = getSqlMap().update("BATCHCLEARTRANS.updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table BATCHCLEARTRANS
     *
     * @mbggenerated
     */
    protected static class UpdateByExampleParms extends BatchcleartransExample {
        private Object record;

        public UpdateByExampleParms(Object record, BatchcleartransExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}