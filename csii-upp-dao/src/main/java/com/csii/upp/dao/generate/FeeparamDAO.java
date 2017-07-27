package com.csii.upp.dao.generate;

import java.sql.SQLException;
import java.util.List;

import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dto.generate.Feeparam;
import com.csii.upp.dto.generate.FeeparamExample;

public class FeeparamDAO extends BasePayDAO {
	/**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FEEPARAM
     *
     * @mbggenerated
     */
    public static int countByExample(FeeparamExample example) throws SQLException {
        Integer count = (Integer)  getSqlMap().queryForObject("FEEPARAM.countByExample", example);
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FEEPARAM
     *
     * @mbggenerated
     */
    public static int deleteByExample(FeeparamExample example) throws SQLException {
        int rows = getSqlMap().delete("FEEPARAM.deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FEEPARAM
     *
     * @mbggenerated
     */
    public static int deleteByPrimaryKey(String feenbr,String seqnbr) throws SQLException {
        Feeparam _key = new Feeparam();
        _key.setFeenbr(feenbr);
        _key.setSeqnbr(seqnbr);
        int rows = getSqlMap().delete("FEEPARAM.deleteByPrimaryKey", _key);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FEEPARAM
     *
     * @mbggenerated
     */
    public static void insert(Feeparam record) throws SQLException {
        getSqlMap().insert("FEEPARAM.insert", record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FEEPARAM
     *
     * @mbggenerated
     */
    public static void insertSelective(Feeparam record) throws SQLException {
        getSqlMap().insert("FEEPARAM.insertSelective", record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FEEPARAM
     *
     * @mbggenerated
     */
    @SuppressWarnings("unchecked")
    public static List<Feeparam> selectByExample(FeeparamExample example) throws SQLException {
        List<Feeparam> list = getSqlMap().queryForList("FEEPARAM.selectByExample", example);
        return list;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FEEPARAM
     *
     * @mbggenerated
     */
    public static Feeparam selectByPrimaryKey(String feenbr,String seqnbr) throws SQLException {
        Feeparam _key = new Feeparam();
        _key.setFeenbr(feenbr);
        _key.setSeqnbr(seqnbr);
        Feeparam record = (Feeparam) getSqlMap().queryForObject("FEEPARAM.selectByPrimaryKey", _key);
        return record;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FEEPARAM
     *
     * @mbggenerated
     */
    public static int updateByExampleSelective(Feeparam record, FeeparamExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMap().update("FEEPARAM.updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FEEPARAM
     *
     * @mbggenerated
     */
    public static int updateByExample(Feeparam record, FeeparamExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMap().update("FEEPARAM.updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FEEPARAM
     *
     * @mbggenerated
     */
    public static int updateByPrimaryKeySelective(Feeparam record) throws SQLException {
        int rows = getSqlMap().update("FEEPARAM.updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FEEPARAM
     *
     * @mbggenerated
     */
    public static int updateByPrimaryKey(Feeparam record) throws SQLException {
        int rows = getSqlMap().update("FEEPARAM.updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table FEEPARAM
     *
     * @mbggenerated
     */
    protected static class UpdateByExampleParms extends FeeparamExample {
        private Object record;

        public UpdateByExampleParms(Object record, FeeparamExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}