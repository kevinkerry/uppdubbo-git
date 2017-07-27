package com.csii.upp.dto.generate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UppersysinfoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table UPPERSYSINFO
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table UPPERSYSINFO
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table UPPERSYSINFO
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UPPERSYSINFO
     *
     * @mbggenerated
     */
    public UppersysinfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UPPERSYSINFO
     *
     * @mbggenerated
     */
    protected UppersysinfoExample(UppersysinfoExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
        this.distinct = example.distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UPPERSYSINFO
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UPPERSYSINFO
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UPPERSYSINFO
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UPPERSYSINFO
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UPPERSYSINFO
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UPPERSYSINFO
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UPPERSYSINFO
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
     * This method corresponds to the database table UPPERSYSINFO
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
     * This method corresponds to the database table UPPERSYSINFO
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UPPERSYSINFO
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
     * This class corresponds to the database table UPPERSYSINFO
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

        public Criteria andSystemcodeIsNull() {
            addCriterion("SYSTEMCODE is null");
            return (Criteria) this;
        }

        public Criteria andSystemcodeIsNotNull() {
            addCriterion("SYSTEMCODE is not null");
            return (Criteria) this;
        }

        public Criteria andSystemcodeEqualTo(String value) {
            addCriterion("SYSTEMCODE =", value, "systemcode");
            return (Criteria) this;
        }

        public Criteria andSystemcodeNotEqualTo(String value) {
            addCriterion("SYSTEMCODE <>", value, "systemcode");
            return (Criteria) this;
        }

        public Criteria andSystemcodeGreaterThan(String value) {
            addCriterion("SYSTEMCODE >", value, "systemcode");
            return (Criteria) this;
        }

        public Criteria andSystemcodeGreaterThanOrEqualTo(String value) {
            addCriterion("SYSTEMCODE >=", value, "systemcode");
            return (Criteria) this;
        }

        public Criteria andSystemcodeLessThan(String value) {
            addCriterion("SYSTEMCODE <", value, "systemcode");
            return (Criteria) this;
        }

        public Criteria andSystemcodeLessThanOrEqualTo(String value) {
            addCriterion("SYSTEMCODE <=", value, "systemcode");
            return (Criteria) this;
        }

        public Criteria andSystemcodeLike(String value) {
            addCriterion("SYSTEMCODE like", value, "systemcode");
            return (Criteria) this;
        }

        public Criteria andSystemcodeNotLike(String value) {
            addCriterion("SYSTEMCODE not like", value, "systemcode");
            return (Criteria) this;
        }

        public Criteria andSystemcodeIn(List<String> values) {
            addCriterion("SYSTEMCODE in", values, "systemcode");
            return (Criteria) this;
        }

        public Criteria andSystemcodeNotIn(List<String> values) {
            addCriterion("SYSTEMCODE not in", values, "systemcode");
            return (Criteria) this;
        }

        public Criteria andSystemcodeBetween(String value1, String value2) {
            addCriterion("SYSTEMCODE between", value1, value2, "systemcode");
            return (Criteria) this;
        }

        public Criteria andSystemcodeNotBetween(String value1, String value2) {
            addCriterion("SYSTEMCODE not between", value1, value2, "systemcode");
            return (Criteria) this;
        }

        public Criteria andSystemnameIsNull() {
            addCriterion("SYSTEMNAME is null");
            return (Criteria) this;
        }

        public Criteria andSystemnameIsNotNull() {
            addCriterion("SYSTEMNAME is not null");
            return (Criteria) this;
        }

        public Criteria andSystemnameEqualTo(String value) {
            addCriterion("SYSTEMNAME =", value, "systemname");
            return (Criteria) this;
        }

        public Criteria andSystemnameNotEqualTo(String value) {
            addCriterion("SYSTEMNAME <>", value, "systemname");
            return (Criteria) this;
        }

        public Criteria andSystemnameGreaterThan(String value) {
            addCriterion("SYSTEMNAME >", value, "systemname");
            return (Criteria) this;
        }

        public Criteria andSystemnameGreaterThanOrEqualTo(String value) {
            addCriterion("SYSTEMNAME >=", value, "systemname");
            return (Criteria) this;
        }

        public Criteria andSystemnameLessThan(String value) {
            addCriterion("SYSTEMNAME <", value, "systemname");
            return (Criteria) this;
        }

        public Criteria andSystemnameLessThanOrEqualTo(String value) {
            addCriterion("SYSTEMNAME <=", value, "systemname");
            return (Criteria) this;
        }

        public Criteria andSystemnameLike(String value) {
            addCriterion("SYSTEMNAME like", value, "systemname");
            return (Criteria) this;
        }

        public Criteria andSystemnameNotLike(String value) {
            addCriterion("SYSTEMNAME not like", value, "systemname");
            return (Criteria) this;
        }

        public Criteria andSystemnameIn(List<String> values) {
            addCriterion("SYSTEMNAME in", values, "systemname");
            return (Criteria) this;
        }

        public Criteria andSystemnameNotIn(List<String> values) {
            addCriterion("SYSTEMNAME not in", values, "systemname");
            return (Criteria) this;
        }

        public Criteria andSystemnameBetween(String value1, String value2) {
            addCriterion("SYSTEMNAME between", value1, value2, "systemname");
            return (Criteria) this;
        }

        public Criteria andSystemnameNotBetween(String value1, String value2) {
            addCriterion("SYSTEMNAME not between", value1, value2, "systemname");
            return (Criteria) this;
        }

        public Criteria andSysstatusIsNull() {
            addCriterion("SYSSTATUS is null");
            return (Criteria) this;
        }

        public Criteria andSysstatusIsNotNull() {
            addCriterion("SYSSTATUS is not null");
            return (Criteria) this;
        }

        public Criteria andSysstatusEqualTo(String value) {
            addCriterion("SYSSTATUS =", value, "sysstatus");
            return (Criteria) this;
        }

        public Criteria andSysstatusNotEqualTo(String value) {
            addCriterion("SYSSTATUS <>", value, "sysstatus");
            return (Criteria) this;
        }

        public Criteria andSysstatusGreaterThan(String value) {
            addCriterion("SYSSTATUS >", value, "sysstatus");
            return (Criteria) this;
        }

        public Criteria andSysstatusGreaterThanOrEqualTo(String value) {
            addCriterion("SYSSTATUS >=", value, "sysstatus");
            return (Criteria) this;
        }

        public Criteria andSysstatusLessThan(String value) {
            addCriterion("SYSSTATUS <", value, "sysstatus");
            return (Criteria) this;
        }

        public Criteria andSysstatusLessThanOrEqualTo(String value) {
            addCriterion("SYSSTATUS <=", value, "sysstatus");
            return (Criteria) this;
        }

        public Criteria andSysstatusLike(String value) {
            addCriterion("SYSSTATUS like", value, "sysstatus");
            return (Criteria) this;
        }

        public Criteria andSysstatusNotLike(String value) {
            addCriterion("SYSSTATUS not like", value, "sysstatus");
            return (Criteria) this;
        }

        public Criteria andSysstatusIn(List<String> values) {
            addCriterion("SYSSTATUS in", values, "sysstatus");
            return (Criteria) this;
        }

        public Criteria andSysstatusNotIn(List<String> values) {
            addCriterion("SYSSTATUS not in", values, "sysstatus");
            return (Criteria) this;
        }

        public Criteria andSysstatusBetween(String value1, String value2) {
            addCriterion("SYSSTATUS between", value1, value2, "sysstatus");
            return (Criteria) this;
        }

        public Criteria andSysstatusNotBetween(String value1, String value2) {
            addCriterion("SYSSTATUS not between", value1, value2, "sysstatus");
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
     * This class corresponds to the database table UPPERSYSINFO
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}