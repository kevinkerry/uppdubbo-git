package com.csii.upp.dto.generate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardbininfoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table CARDBININFO
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table CARDBININFO
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table CARDBININFO
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CARDBININFO
     *
     * @mbggenerated
     */
    public CardbininfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CARDBININFO
     *
     * @mbggenerated
     */
    protected CardbininfoExample(CardbininfoExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
        this.distinct = example.distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CARDBININFO
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CARDBININFO
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CARDBININFO
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CARDBININFO
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CARDBININFO
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CARDBININFO
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CARDBININFO
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CARDBININFO
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CARDBININFO
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CARDBININFO
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table CARDBININFO
     *
     * @mbggenerated
     */
    protected abstract static class GeneratedCriteria {
        protected List<String> criteriaWithoutValue;

        protected List<Map<String, Object>> criteriaWithSingleValue;

        protected List<Map<String, Object>> criteriaWithListValue;

        protected List<Map<String, Object>> criteriaWithBetweenValue;

        protected GeneratedCriteria() {
            super();
            criteriaWithoutValue = new ArrayList<String>();
            criteriaWithSingleValue = new ArrayList<Map<String, Object>>();
            criteriaWithListValue = new ArrayList<Map<String, Object>>();
            criteriaWithBetweenValue = new ArrayList<Map<String, Object>>();
        }

        public boolean isValid() {
            return criteriaWithoutValue.size() > 0
                || criteriaWithSingleValue.size() > 0
                || criteriaWithListValue.size() > 0
                || criteriaWithBetweenValue.size() > 0;
        }

        public List<String> getCriteriaWithoutValue() {
            return criteriaWithoutValue;
        }

        public List<Map<String, Object>> getCriteriaWithSingleValue() {
            return criteriaWithSingleValue;
        }

        public List<Map<String, Object>> getCriteriaWithListValue() {
            return criteriaWithListValue;
        }

        public List<Map<String, Object>> getCriteriaWithBetweenValue() {
            return criteriaWithBetweenValue;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteriaWithoutValue.add(condition);
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("value", value);
            criteriaWithSingleValue.add(map);
        }

        protected void addCriterion(String condition, List<? extends Object> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("values", values);
            criteriaWithListValue.add(map);
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            List<Object> list = new ArrayList<Object>();
            list.add(value1);
            list.add(value2);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("values", list);
            criteriaWithBetweenValue.add(map);
        }

        public Criteria andCardbinnbrIsNull() {
            addCriterion("CARDBINNBR is null");
            return (Criteria) this;
        }

        public Criteria andCardbinnbrIsNotNull() {
            addCriterion("CARDBINNBR is not null");
            return (Criteria) this;
        }

        public Criteria andCardbinnbrEqualTo(String value) {
            addCriterion("CARDBINNBR =", value, "cardbinnbr");
            return (Criteria) this;
        }

        public Criteria andCardbinnbrNotEqualTo(String value) {
            addCriterion("CARDBINNBR <>", value, "cardbinnbr");
            return (Criteria) this;
        }

        public Criteria andCardbinnbrGreaterThan(String value) {
            addCriterion("CARDBINNBR >", value, "cardbinnbr");
            return (Criteria) this;
        }

        public Criteria andCardbinnbrGreaterThanOrEqualTo(String value) {
            addCriterion("CARDBINNBR >=", value, "cardbinnbr");
            return (Criteria) this;
        }

        public Criteria andCardbinnbrLessThan(String value) {
            addCriterion("CARDBINNBR <", value, "cardbinnbr");
            return (Criteria) this;
        }

        public Criteria andCardbinnbrLessThanOrEqualTo(String value) {
            addCriterion("CARDBINNBR <=", value, "cardbinnbr");
            return (Criteria) this;
        }

        public Criteria andCardbinnbrLike(String value) {
            addCriterion("CARDBINNBR like", value, "cardbinnbr");
            return (Criteria) this;
        }

        public Criteria andCardbinnbrNotLike(String value) {
            addCriterion("CARDBINNBR not like", value, "cardbinnbr");
            return (Criteria) this;
        }

        public Criteria andCardbinnbrIn(List<String> values) {
            addCriterion("CARDBINNBR in", values, "cardbinnbr");
            return (Criteria) this;
        }

        public Criteria andCardbinnbrNotIn(List<String> values) {
            addCriterion("CARDBINNBR not in", values, "cardbinnbr");
            return (Criteria) this;
        }

        public Criteria andCardbinnbrBetween(String value1, String value2) {
            addCriterion("CARDBINNBR between", value1, value2, "cardbinnbr");
            return (Criteria) this;
        }

        public Criteria andCardbinnbrNotBetween(String value1, String value2) {
            addCriterion("CARDBINNBR not between", value1, value2, "cardbinnbr");
            return (Criteria) this;
        }

        public Criteria andBankcodeIsNull() {
            addCriterion("BANKCODE is null");
            return (Criteria) this;
        }

        public Criteria andBankcodeIsNotNull() {
            addCriterion("BANKCODE is not null");
            return (Criteria) this;
        }

        public Criteria andBankcodeEqualTo(String value) {
            addCriterion("BANKCODE =", value, "bankcode");
            return (Criteria) this;
        }

        public Criteria andBankcodeNotEqualTo(String value) {
            addCriterion("BANKCODE <>", value, "bankcode");
            return (Criteria) this;
        }

        public Criteria andBankcodeGreaterThan(String value) {
            addCriterion("BANKCODE >", value, "bankcode");
            return (Criteria) this;
        }

        public Criteria andBankcodeGreaterThanOrEqualTo(String value) {
            addCriterion("BANKCODE >=", value, "bankcode");
            return (Criteria) this;
        }

        public Criteria andBankcodeLessThan(String value) {
            addCriterion("BANKCODE <", value, "bankcode");
            return (Criteria) this;
        }

        public Criteria andBankcodeLessThanOrEqualTo(String value) {
            addCriterion("BANKCODE <=", value, "bankcode");
            return (Criteria) this;
        }

        public Criteria andBankcodeLike(String value) {
            addCriterion("BANKCODE like", value, "bankcode");
            return (Criteria) this;
        }

        public Criteria andBankcodeNotLike(String value) {
            addCriterion("BANKCODE not like", value, "bankcode");
            return (Criteria) this;
        }

        public Criteria andBankcodeIn(List<String> values) {
            addCriterion("BANKCODE in", values, "bankcode");
            return (Criteria) this;
        }

        public Criteria andBankcodeNotIn(List<String> values) {
            addCriterion("BANKCODE not in", values, "bankcode");
            return (Criteria) this;
        }

        public Criteria andBankcodeBetween(String value1, String value2) {
            addCriterion("BANKCODE between", value1, value2, "bankcode");
            return (Criteria) this;
        }

        public Criteria andBankcodeNotBetween(String value1, String value2) {
            addCriterion("BANKCODE not between", value1, value2, "bankcode");
            return (Criteria) this;
        }

        public Criteria andCardbinnameIsNull() {
            addCriterion("CARDBINNAME is null");
            return (Criteria) this;
        }

        public Criteria andCardbinnameIsNotNull() {
            addCriterion("CARDBINNAME is not null");
            return (Criteria) this;
        }

        public Criteria andCardbinnameEqualTo(String value) {
            addCriterion("CARDBINNAME =", value, "cardbinname");
            return (Criteria) this;
        }

        public Criteria andCardbinnameNotEqualTo(String value) {
            addCriterion("CARDBINNAME <>", value, "cardbinname");
            return (Criteria) this;
        }

        public Criteria andCardbinnameGreaterThan(String value) {
            addCriterion("CARDBINNAME >", value, "cardbinname");
            return (Criteria) this;
        }

        public Criteria andCardbinnameGreaterThanOrEqualTo(String value) {
            addCriterion("CARDBINNAME >=", value, "cardbinname");
            return (Criteria) this;
        }

        public Criteria andCardbinnameLessThan(String value) {
            addCriterion("CARDBINNAME <", value, "cardbinname");
            return (Criteria) this;
        }

        public Criteria andCardbinnameLessThanOrEqualTo(String value) {
            addCriterion("CARDBINNAME <=", value, "cardbinname");
            return (Criteria) this;
        }

        public Criteria andCardbinnameLike(String value) {
            addCriterion("CARDBINNAME like", value, "cardbinname");
            return (Criteria) this;
        }

        public Criteria andCardbinnameNotLike(String value) {
            addCriterion("CARDBINNAME not like", value, "cardbinname");
            return (Criteria) this;
        }

        public Criteria andCardbinnameIn(List<String> values) {
            addCriterion("CARDBINNAME in", values, "cardbinname");
            return (Criteria) this;
        }

        public Criteria andCardbinnameNotIn(List<String> values) {
            addCriterion("CARDBINNAME not in", values, "cardbinname");
            return (Criteria) this;
        }

        public Criteria andCardbinnameBetween(String value1, String value2) {
            addCriterion("CARDBINNAME between", value1, value2, "cardbinname");
            return (Criteria) this;
        }

        public Criteria andCardbinnameNotBetween(String value1, String value2) {
            addCriterion("CARDBINNAME not between", value1, value2, "cardbinname");
            return (Criteria) this;
        }

        public Criteria andCardlenIsNull() {
            addCriterion("CARDLEN is null");
            return (Criteria) this;
        }

        public Criteria andCardlenIsNotNull() {
            addCriterion("CARDLEN is not null");
            return (Criteria) this;
        }

        public Criteria andCardlenEqualTo(Long value) {
            addCriterion("CARDLEN =", value, "cardlen");
            return (Criteria) this;
        }

        public Criteria andCardlenNotEqualTo(Long value) {
            addCriterion("CARDLEN <>", value, "cardlen");
            return (Criteria) this;
        }

        public Criteria andCardlenGreaterThan(Long value) {
            addCriterion("CARDLEN >", value, "cardlen");
            return (Criteria) this;
        }

        public Criteria andCardlenGreaterThanOrEqualTo(Long value) {
            addCriterion("CARDLEN >=", value, "cardlen");
            return (Criteria) this;
        }

        public Criteria andCardlenLessThan(Long value) {
            addCriterion("CARDLEN <", value, "cardlen");
            return (Criteria) this;
        }

        public Criteria andCardlenLessThanOrEqualTo(Long value) {
            addCriterion("CARDLEN <=", value, "cardlen");
            return (Criteria) this;
        }

        public Criteria andCardlenIn(List<Long> values) {
            addCriterion("CARDLEN in", values, "cardlen");
            return (Criteria) this;
        }

        public Criteria andCardlenNotIn(List<Long> values) {
            addCriterion("CARDLEN not in", values, "cardlen");
            return (Criteria) this;
        }

        public Criteria andCardlenBetween(Long value1, Long value2) {
            addCriterion("CARDLEN between", value1, value2, "cardlen");
            return (Criteria) this;
        }

        public Criteria andCardlenNotBetween(Long value1, Long value2) {
            addCriterion("CARDLEN not between", value1, value2, "cardlen");
            return (Criteria) this;
        }

        public Criteria andDrcrflagIsNull() {
            addCriterion("DRCRFLAG is null");
            return (Criteria) this;
        }

        public Criteria andDrcrflagIsNotNull() {
            addCriterion("DRCRFLAG is not null");
            return (Criteria) this;
        }

        public Criteria andDrcrflagEqualTo(String value) {
            addCriterion("DRCRFLAG =", value, "drcrflag");
            return (Criteria) this;
        }

        public Criteria andDrcrflagNotEqualTo(String value) {
            addCriterion("DRCRFLAG <>", value, "drcrflag");
            return (Criteria) this;
        }

        public Criteria andDrcrflagGreaterThan(String value) {
            addCriterion("DRCRFLAG >", value, "drcrflag");
            return (Criteria) this;
        }

        public Criteria andDrcrflagGreaterThanOrEqualTo(String value) {
            addCriterion("DRCRFLAG >=", value, "drcrflag");
            return (Criteria) this;
        }

        public Criteria andDrcrflagLessThan(String value) {
            addCriterion("DRCRFLAG <", value, "drcrflag");
            return (Criteria) this;
        }

        public Criteria andDrcrflagLessThanOrEqualTo(String value) {
            addCriterion("DRCRFLAG <=", value, "drcrflag");
            return (Criteria) this;
        }

        public Criteria andDrcrflagLike(String value) {
            addCriterion("DRCRFLAG like", value, "drcrflag");
            return (Criteria) this;
        }

        public Criteria andDrcrflagNotLike(String value) {
            addCriterion("DRCRFLAG not like", value, "drcrflag");
            return (Criteria) this;
        }

        public Criteria andDrcrflagIn(List<String> values) {
            addCriterion("DRCRFLAG in", values, "drcrflag");
            return (Criteria) this;
        }

        public Criteria andDrcrflagNotIn(List<String> values) {
            addCriterion("DRCRFLAG not in", values, "drcrflag");
            return (Criteria) this;
        }

        public Criteria andDrcrflagBetween(String value1, String value2) {
            addCriterion("DRCRFLAG between", value1, value2, "drcrflag");
            return (Criteria) this;
        }

        public Criteria andDrcrflagNotBetween(String value1, String value2) {
            addCriterion("DRCRFLAG not between", value1, value2, "drcrflag");
            return (Criteria) this;
        }

        public Criteria andInnercardflagIsNull() {
            addCriterion("INNERCARDFLAG is null");
            return (Criteria) this;
        }

        public Criteria andInnercardflagIsNotNull() {
            addCriterion("INNERCARDFLAG is not null");
            return (Criteria) this;
        }

        public Criteria andInnercardflagEqualTo(String value) {
            addCriterion("INNERCARDFLAG =", value, "innercardflag");
            return (Criteria) this;
        }

        public Criteria andInnercardflagNotEqualTo(String value) {
            addCriterion("INNERCARDFLAG <>", value, "innercardflag");
            return (Criteria) this;
        }

        public Criteria andInnercardflagGreaterThan(String value) {
            addCriterion("INNERCARDFLAG >", value, "innercardflag");
            return (Criteria) this;
        }

        public Criteria andInnercardflagGreaterThanOrEqualTo(String value) {
            addCriterion("INNERCARDFLAG >=", value, "innercardflag");
            return (Criteria) this;
        }

        public Criteria andInnercardflagLessThan(String value) {
            addCriterion("INNERCARDFLAG <", value, "innercardflag");
            return (Criteria) this;
        }

        public Criteria andInnercardflagLessThanOrEqualTo(String value) {
            addCriterion("INNERCARDFLAG <=", value, "innercardflag");
            return (Criteria) this;
        }

        public Criteria andInnercardflagLike(String value) {
            addCriterion("INNERCARDFLAG like", value, "innercardflag");
            return (Criteria) this;
        }

        public Criteria andInnercardflagNotLike(String value) {
            addCriterion("INNERCARDFLAG not like", value, "innercardflag");
            return (Criteria) this;
        }

        public Criteria andInnercardflagIn(List<String> values) {
            addCriterion("INNERCARDFLAG in", values, "innercardflag");
            return (Criteria) this;
        }

        public Criteria andInnercardflagNotIn(List<String> values) {
            addCriterion("INNERCARDFLAG not in", values, "innercardflag");
            return (Criteria) this;
        }

        public Criteria andInnercardflagBetween(String value1, String value2) {
            addCriterion("INNERCARDFLAG between", value1, value2, "innercardflag");
            return (Criteria) this;
        }

        public Criteria andInnercardflagNotBetween(String value1, String value2) {
            addCriterion("INNERCARDFLAG not between", value1, value2, "innercardflag");
            return (Criteria) this;
        }

        public Criteria andDatelastmaintIsNull() {
            addCriterion("DATELASTMAINT is null");
            return (Criteria) this;
        }

        public Criteria andDatelastmaintIsNotNull() {
            addCriterion("DATELASTMAINT is not null");
            return (Criteria) this;
        }

        public Criteria andDatelastmaintEqualTo(Date value) {
            addCriterion("DATELASTMAINT =", value, "datelastmaint");
            return (Criteria) this;
        }

        public Criteria andDatelastmaintNotEqualTo(Date value) {
            addCriterion("DATELASTMAINT <>", value, "datelastmaint");
            return (Criteria) this;
        }

        public Criteria andDatelastmaintGreaterThan(Date value) {
            addCriterion("DATELASTMAINT >", value, "datelastmaint");
            return (Criteria) this;
        }

        public Criteria andDatelastmaintGreaterThanOrEqualTo(Date value) {
            addCriterion("DATELASTMAINT >=", value, "datelastmaint");
            return (Criteria) this;
        }

        public Criteria andDatelastmaintLessThan(Date value) {
            addCriterion("DATELASTMAINT <", value, "datelastmaint");
            return (Criteria) this;
        }

        public Criteria andDatelastmaintLessThanOrEqualTo(Date value) {
            addCriterion("DATELASTMAINT <=", value, "datelastmaint");
            return (Criteria) this;
        }

        public Criteria andDatelastmaintIn(List<Date> values) {
            addCriterion("DATELASTMAINT in", values, "datelastmaint");
            return (Criteria) this;
        }

        public Criteria andDatelastmaintNotIn(List<Date> values) {
            addCriterion("DATELASTMAINT not in", values, "datelastmaint");
            return (Criteria) this;
        }

        public Criteria andDatelastmaintBetween(Date value1, Date value2) {
            addCriterion("DATELASTMAINT between", value1, value2, "datelastmaint");
            return (Criteria) this;
        }

        public Criteria andDatelastmaintNotBetween(Date value1, Date value2) {
            addCriterion("DATELASTMAINT not between", value1, value2, "datelastmaint");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table CARDBININFO
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}