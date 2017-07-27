package com.csii.upp.dao.generate;

import java.sql.SQLException;
import java.util.List;

import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dto.generate.Preprocessfundtrans;
import com.csii.upp.dto.generate.PreprocessfundtransExample;

public class PreprocessfundtransDAO extends BasePayDAO {
	/**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PREPROCESSFUNDTRANS
     *
     * @mbggenerated
     */
    public static int countByExample(PreprocessfundtransExample example) throws SQLException {
        Integer count = (Integer)  getSqlMap().queryForObject("PREPROCESSFUNDTRANS.countByExample", example);
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PREPROCESSFUNDTRANS
     *
     * @mbggenerated
     */
    public static int deleteByExample(PreprocessfundtransExample example) throws SQLException {
        int rows = getSqlMap().delete("PREPROCESSFUNDTRANS.deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PREPROCESSFUNDTRANS
     *
     * @mbggenerated
     */
    public static int deleteByPrimaryKey(String processtransnbr) throws SQLException {
        Preprocessfundtrans _key = new Preprocessfundtrans();
        _key.setProcesstransnbr(processtransnbr);
        int rows = getSqlMap().delete("PREPROCESSFUNDTRANS.deleteByPrimaryKey", _key);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PREPROCESSFUNDTRANS
     *
     * @mbggenerated
     */
    public static void insert(Preprocessfundtrans record) throws SQLException {
        getSqlMap().insert("PREPROCESSFUNDTRANS.insert", record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PREPROCESSFUNDTRANS
     *
     * @mbggenerated
     */
    public static void insertSelective(Preprocessfundtrans record) throws SQLException {
        getSqlMap().insert("PREPROCESSFUNDTRANS.insertSelective", record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PREPROCESSFUNDTRANS
     *
     * @mbggenerated
     */
    @SuppressWarnings("unchecked")
    public static List<Preprocessfundtrans> selectByExample(PreprocessfundtransExample example) throws SQLException {
        List<Preprocessfundtrans> list = getSqlMap().queryForList("PREPROCESSFUNDTRANS.selectByExample", example);
        return list;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PREPROCESSFUNDTRANS
     *
     * @mbggenerated
     */
    public static Preprocessfundtrans selectByPrimaryKey(String processtransnbr) throws SQLException {
        Preprocessfundtrans _key = new Preprocessfundtrans();
        _key.setProcesstransnbr(processtransnbr);
        Preprocessfundtrans record = (Preprocessfundtrans) getSqlMap().queryForObject("PREPROCESSFUNDTRANS.selectByPrimaryKey", _key);
        return record;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PREPROCESSFUNDTRANS
     *
     * @mbggenerated
     */
    public static int updateByExampleSelective(Preprocessfundtrans record, PreprocessfundtransExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMap().update("PREPROCESSFUNDTRANS.updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PREPROCESSFUNDTRANS
     *
     * @mbggenerated
     */
    public static int updateByExample(Preprocessfundtrans record, PreprocessfundtransExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMap().update("PREPROCESSFUNDTRANS.updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PREPROCESSFUNDTRANS
     *
     * @mbggenerated
     */
    public static int updateByPrimaryKeySelective(Preprocessfundtrans record) throws SQLException {
        int rows = getSqlMap().update("PREPROCESSFUNDTRANS.updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PREPROCESSFUNDTRANS
     *
     * @mbggenerated
     */
    public static int updateByPrimaryKey(Preprocessfundtrans record) throws SQLException {
        int rows = getSqlMap().update("PREPROCESSFUNDTRANS.updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PREPROCESSFUNDTRANS
     *
     * @mbggenerated
     */
    protected static class UpdateByExampleParms extends PreprocessfundtransExample {
        private Object record;

        public UpdateByExampleParms(Object record, PreprocessfundtransExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}