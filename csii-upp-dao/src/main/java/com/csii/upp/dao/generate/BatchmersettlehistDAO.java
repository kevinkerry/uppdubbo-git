package com.csii.upp.dao.generate;

import java.sql.SQLException;
import java.util.List;

import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dto.generate.Batchmersettlehist;
import com.csii.upp.dto.generate.BatchmersettlehistExample;

public class BatchmersettlehistDAO extends BasePayDAO {
	 /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHMERSETTLEHIST
     *
     * @mbggenerated
     */
    public static int countByExample(BatchmersettlehistExample example) throws SQLException {
        Integer count = (Integer)  getSqlMap().queryForObject("BATCHMERSETTLEHIST.countByExample", example);
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHMERSETTLEHIST
     *
     * @mbggenerated
     */
    public static int deleteByExample(BatchmersettlehistExample example) throws SQLException {
        int rows = getSqlMap().delete("BATCHMERSETTLEHIST.deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHMERSETTLEHIST
     *
     * @mbggenerated
     */
    public static int deleteByPrimaryKey(String settleseqnbr) throws SQLException {
        Batchmersettlehist _key = new Batchmersettlehist();
        _key.setSettleseqnbr(settleseqnbr);
        int rows = getSqlMap().delete("BATCHMERSETTLEHIST.deleteByPrimaryKey", _key);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHMERSETTLEHIST
     *
     * @mbggenerated
     */
    public static void insert(Batchmersettlehist record) throws SQLException {
        getSqlMap().insert("BATCHMERSETTLEHIST.insert", record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHMERSETTLEHIST
     *
     * @mbggenerated
     */
    public static void insertSelective(Batchmersettlehist record) throws SQLException {
        getSqlMap().insert("BATCHMERSETTLEHIST.insertSelective", record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHMERSETTLEHIST
     *
     * @mbggenerated
     */
    @SuppressWarnings("unchecked")
    public static List<Batchmersettlehist> selectByExample(BatchmersettlehistExample example) throws SQLException {
        List<Batchmersettlehist> list = getSqlMap().queryForList("BATCHMERSETTLEHIST.selectByExample", example);
        return list;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHMERSETTLEHIST
     *
     * @mbggenerated
     */
    public static Batchmersettlehist selectByPrimaryKey(String settleseqnbr) throws SQLException {
        Batchmersettlehist _key = new Batchmersettlehist();
        _key.setSettleseqnbr(settleseqnbr);
        Batchmersettlehist record = (Batchmersettlehist) getSqlMap().queryForObject("BATCHMERSETTLEHIST.selectByPrimaryKey", _key);
        return record;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHMERSETTLEHIST
     *
     * @mbggenerated
     */
    public static int updateByExampleSelective(Batchmersettlehist record, BatchmersettlehistExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMap().update("BATCHMERSETTLEHIST.updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHMERSETTLEHIST
     *
     * @mbggenerated
     */
    public static int updateByExample(Batchmersettlehist record, BatchmersettlehistExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMap().update("BATCHMERSETTLEHIST.updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHMERSETTLEHIST
     *
     * @mbggenerated
     */
    public static int updateByPrimaryKeySelective(Batchmersettlehist record) throws SQLException {
        int rows = getSqlMap().update("BATCHMERSETTLEHIST.updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BATCHMERSETTLEHIST
     *
     * @mbggenerated
     */
    public static int updateByPrimaryKey(Batchmersettlehist record) throws SQLException {
        int rows = getSqlMap().update("BATCHMERSETTLEHIST.updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table BATCHMERSETTLEHIST
     *
     * @mbggenerated
     */
    protected static class UpdateByExampleParms extends BatchmersettlehistExample {
        private Object record;

        public UpdateByExampleParms(Object record, BatchmersettlehistExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}