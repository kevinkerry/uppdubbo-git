package com.csii.upp.dao.generate;

import java.sql.SQLException;
import java.util.List;

import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dto.generate.Batchdownsystranshist;
import com.csii.upp.dto.generate.BatchdownsystranshistExample;

public class BatchdownsystranshistDAO extends BasePayDAO {
	/**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHDOWNSYSTRANSHIST
     *
     * @mbggenerated
     */
    public static int countByExample(BatchdownsystranshistExample example) throws SQLException {
        Integer count = (Integer)  getSqlMap().queryForObject("BATCHDOWNSYSTRANSHIST.countByExample", example);
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHDOWNSYSTRANSHIST
     *
     * @mbggenerated
     */
    public static int deleteByExample(BatchdownsystranshistExample example) throws SQLException {
        int rows = getSqlMap().delete("BATCHDOWNSYSTRANSHIST.deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHDOWNSYSTRANSHIST
     *
     * @mbggenerated
     */
    public static int deleteByPrimaryKey(String downsystransnbr) throws SQLException {
        Batchdownsystranshist _key = new Batchdownsystranshist();
        _key.setDownsystransnbr(downsystransnbr);
        int rows = getSqlMap().delete("BATCHDOWNSYSTRANSHIST.deleteByPrimaryKey", _key);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHDOWNSYSTRANSHIST
     *
     * @mbggenerated
     */
    public static void insert(Batchdownsystranshist record) throws SQLException {
        getSqlMap().insert("BATCHDOWNSYSTRANSHIST.insert", record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHDOWNSYSTRANSHIST
     *
     * @mbggenerated
     */
    public static void insertSelective(Batchdownsystranshist record) throws SQLException {
        getSqlMap().insert("BATCHDOWNSYSTRANSHIST.insertSelective", record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHDOWNSYSTRANSHIST
     *
     * @mbggenerated
     */
    @SuppressWarnings("unchecked")
    public static List<Batchdownsystranshist> selectByExample(BatchdownsystranshistExample example) throws SQLException {
        List<Batchdownsystranshist> list = getSqlMap().queryForList("BATCHDOWNSYSTRANSHIST.selectByExample", example);
        return list;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHDOWNSYSTRANSHIST
     *
     * @mbggenerated
     */
    public static Batchdownsystranshist selectByPrimaryKey(String downsystransnbr) throws SQLException {
        Batchdownsystranshist _key = new Batchdownsystranshist();
        _key.setDownsystransnbr(downsystransnbr);
        Batchdownsystranshist record = (Batchdownsystranshist) getSqlMap().queryForObject("BATCHDOWNSYSTRANSHIST.selectByPrimaryKey", _key);
        return record;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHDOWNSYSTRANSHIST
     *
     * @mbggenerated
     */
    public static int updateByExampleSelective(Batchdownsystranshist record, BatchdownsystranshistExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMap().update("BATCHDOWNSYSTRANSHIST.updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHDOWNSYSTRANSHIST
     *
     * @mbggenerated
     */
    public static int updateByExample(Batchdownsystranshist record, BatchdownsystranshistExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMap().update("BATCHDOWNSYSTRANSHIST.updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHDOWNSYSTRANSHIST
     *
     * @mbggenerated
     */
    public static int updateByPrimaryKeySelective(Batchdownsystranshist record) throws SQLException {
        int rows = getSqlMap().update("BATCHDOWNSYSTRANSHIST.updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHDOWNSYSTRANSHIST
     *
     * @mbggenerated
     */
    public static int updateByPrimaryKey(Batchdownsystranshist record) throws SQLException {
        int rows = getSqlMap().update("BATCHDOWNSYSTRANSHIST.updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table BATCHDOWNSYSTRANSHIST
     *
     * @mbggenerated
     */
    protected static class UpdateByExampleParms extends BatchdownsystranshistExample {
        private Object record;

        public UpdateByExampleParms(Object record, BatchdownsystranshistExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}