package com.csii.upp.dao.generate;

import java.sql.SQLException;
import java.util.List;

import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dto.generate.Batchclearsubtrans;
import com.csii.upp.dto.generate.BatchclearsubtransExample;

public class BatchclearsubtransDAO extends BasePayDAO {
	 /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHCLEARSUBTRANS
     *
     * @mbggenerated
     */
    public static int countByExample(BatchclearsubtransExample example) throws SQLException {
        Integer count = (Integer)  getSqlMap().queryForObject("BATCHCLEARSUBTRANS.countByExample", example);
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHCLEARSUBTRANS
     *
     * @mbggenerated
     */
    public static int deleteByExample(BatchclearsubtransExample example) throws SQLException {
        int rows = getSqlMap().delete("BATCHCLEARSUBTRANS.deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHCLEARSUBTRANS
     *
     * @mbggenerated
     */
    public static int deleteByPrimaryKey(String subtransseqnbr, String transseqnbr) throws SQLException {
        Batchclearsubtrans _key = new Batchclearsubtrans();
        _key.setSubtransseqnbr(subtransseqnbr);
        _key.setTransseqnbr(transseqnbr);
        int rows = getSqlMap().delete("BATCHCLEARSUBTRANS.deleteByPrimaryKey", _key);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHCLEARSUBTRANS
     *
     * @mbggenerated
     */
    public static void insert(Batchclearsubtrans record) throws SQLException {
        getSqlMap().insert("BATCHCLEARSUBTRANS.insert", record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHCLEARSUBTRANS
     *
     * @mbggenerated
     */
    public static void insertSelective(Batchclearsubtrans record) throws SQLException {
        getSqlMap().insert("BATCHCLEARSUBTRANS.insertSelective", record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHCLEARSUBTRANS
     *
     * @mbggenerated
     */
    @SuppressWarnings("unchecked")
    public static List<Batchclearsubtrans> selectByExample(BatchclearsubtransExample example) throws SQLException {
        List<Batchclearsubtrans> list = getSqlMap().queryForList("BATCHCLEARSUBTRANS.selectByExample", example);
        return list;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHCLEARSUBTRANS
     *
     * @mbggenerated
     */
    public static Batchclearsubtrans selectByPrimaryKey(String subtransseqnbr, String transseqnbr) throws SQLException {
        Batchclearsubtrans _key = new Batchclearsubtrans();
        _key.setSubtransseqnbr(subtransseqnbr);
        _key.setTransseqnbr(transseqnbr);
        Batchclearsubtrans record = (Batchclearsubtrans) getSqlMap().queryForObject("BATCHCLEARSUBTRANS.selectByPrimaryKey", _key);
        return record;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHCLEARSUBTRANS
     *
     * @mbggenerated
     */
    public static int updateByExampleSelective(Batchclearsubtrans record, BatchclearsubtransExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMap().update("BATCHCLEARSUBTRANS.updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHCLEARSUBTRANS
     *
     * @mbggenerated
     */
    public static int updateByExample(Batchclearsubtrans record, BatchclearsubtransExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMap().update("BATCHCLEARSUBTRANS.updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHCLEARSUBTRANS
     *
     * @mbggenerated
     */
    public static int updateByPrimaryKeySelective(Batchclearsubtrans record) throws SQLException {
        int rows = getSqlMap().update("BATCHCLEARSUBTRANS.updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHCLEARSUBTRANS
     *
     * @mbggenerated
     */
    public static int updateByPrimaryKey(Batchclearsubtrans record) throws SQLException {
        int rows = getSqlMap().update("BATCHCLEARSUBTRANS.updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table BATCHCLEARSUBTRANS
     *
     * @mbggenerated
     */
    protected static class UpdateByExampleParms extends BatchclearsubtransExample {
        private Object record;

        public UpdateByExampleParms(Object record, BatchclearsubtransExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}