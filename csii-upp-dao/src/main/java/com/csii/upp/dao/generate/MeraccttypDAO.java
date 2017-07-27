package com.csii.upp.dao.generate;

import java.sql.SQLException;
import java.util.List;

import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dto.generate.Meraccttyp;
import com.csii.upp.dto.generate.MeraccttypExample;

public class MeraccttypDAO extends BasePayDAO {
	 /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MERACCTTYP
     *
     * @mbggenerated
     */
    public static int countByExample(MeraccttypExample example) throws SQLException {
        Integer count = (Integer)  getSqlMap().queryForObject("MERACCTTYP.countByExample", example);
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MERACCTTYP
     *
     * @mbggenerated
     */
    public static int deleteByExample(MeraccttypExample example) throws SQLException {
        int rows = getSqlMap().delete("MERACCTTYP.deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MERACCTTYP
     *
     * @mbggenerated
     */
    public static int deleteByPrimaryKey(String accttypcd) throws SQLException {
        Meraccttyp _key = new Meraccttyp();
        _key.setAccttypcd(accttypcd);
        int rows = getSqlMap().delete("MERACCTTYP.deleteByPrimaryKey", _key);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MERACCTTYP
     *
     * @mbggenerated
     */
    public static void insert(Meraccttyp record) throws SQLException {
        getSqlMap().insert("MERACCTTYP.insert", record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MERACCTTYP
     *
     * @mbggenerated
     */
    public static void insertSelective(Meraccttyp record) throws SQLException {
        getSqlMap().insert("MERACCTTYP.insertSelective", record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MERACCTTYP
     *
     * @mbggenerated
     */
    @SuppressWarnings("unchecked")
    public static List<Meraccttyp> selectByExample(MeraccttypExample example) throws SQLException {
        List<Meraccttyp> list = getSqlMap().queryForList("MERACCTTYP.selectByExample", example);
        return list;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MERACCTTYP
     *
     * @mbggenerated
     */
    public static Meraccttyp selectByPrimaryKey(String accttypcd) throws SQLException {
        Meraccttyp _key = new Meraccttyp();
        _key.setAccttypcd(accttypcd);
        Meraccttyp record = (Meraccttyp) getSqlMap().queryForObject("MERACCTTYP.selectByPrimaryKey", _key);
        return record;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MERACCTTYP
     *
     * @mbggenerated
     */
    public static int updateByExampleSelective(Meraccttyp record, MeraccttypExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMap().update("MERACCTTYP.updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MERACCTTYP
     *
     * @mbggenerated
     */
    public static int updateByExample(Meraccttyp record, MeraccttypExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMap().update("MERACCTTYP.updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MERACCTTYP
     *
     * @mbggenerated
     */
    public static int updateByPrimaryKeySelective(Meraccttyp record) throws SQLException {
        int rows = getSqlMap().update("MERACCTTYP.updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MERACCTTYP
     *
     * @mbggenerated
     */
    public static int updateByPrimaryKey(Meraccttyp record) throws SQLException {
        int rows = getSqlMap().update("MERACCTTYP.updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table MERACCTTYP
     *
     * @mbggenerated
     */
    protected static class UpdateByExampleParms extends MeraccttypExample {
        private Object record;

        public UpdateByExampleParms(Object record, MeraccttypExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}