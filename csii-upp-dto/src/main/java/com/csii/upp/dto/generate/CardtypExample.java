package com.csii.upp.dto.generate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardtypExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table CARDTYP
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table CARDTYP
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table CARDTYP
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CARDTYP
     *
     * @mbggenerated
     */
    public CardtypExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CARDTYP
     *
     * @mbggenerated
     */
    protected CardtypExample(CardtypExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
        this.distinct = example.distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CARDTYP
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CARDTYP
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CARDTYP
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CARDTYP
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CARDTYP
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CARDTYP
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CARDTYP
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
     * This method corresponds to the database table CARDTYP
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
     * This method corresponds to the database table CARDTYP
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CARDTYP
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
     * This class corresponds to the database table CARDTYP
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

        public Criteria andCardtypcdIsNull() {
            addCriterion("CARDTYPCD is null");
            return (Criteria) this;
        }

        public Criteria andCardtypcdIsNotNull() {
            addCriterion("CARDTYPCD is not null");
            return (Criteria) this;
        }

        public Criteria andCardtypcdEqualTo(String value) {
            addCriterion("CARDTYPCD =", value, "cardtypcd");
            return (Criteria) this;
        }

        public Criteria andCardtypcdNotEqualTo(String value) {
            addCriterion("CARDTYPCD <>", value, "cardtypcd");
            return (Criteria) this;
        }

        public Criteria andCardtypcdGreaterThan(String value) {
            addCriterion("CARDTYPCD >", value, "cardtypcd");
            return (Criteria) this;
        }

        public Criteria andCardtypcdGreaterThanOrEqualTo(String value) {
            addCriterion("CARDTYPCD >=", value, "cardtypcd");
            return (Criteria) this;
        }

        public Criteria andCardtypcdLessThan(String value) {
            addCriterion("CARDTYPCD <", value, "cardtypcd");
            return (Criteria) this;
        }

        public Criteria andCardtypcdLessThanOrEqualTo(String value) {
            addCriterion("CARDTYPCD <=", value, "cardtypcd");
            return (Criteria) this;
        }

        public Criteria andCardtypcdLike(String value) {
            addCriterion("CARDTYPCD like", value, "cardtypcd");
            return (Criteria) this;
        }

        public Criteria andCardtypcdNotLike(String value) {
            addCriterion("CARDTYPCD not like", value, "cardtypcd");
            return (Criteria) this;
        }

        public Criteria andCardtypcdIn(List<String> values) {
            addCriterion("CARDTYPCD in", values, "cardtypcd");
            return (Criteria) this;
        }

        public Criteria andCardtypcdNotIn(List<String> values) {
            addCriterion("CARDTYPCD not in", values, "cardtypcd");
            return (Criteria) this;
        }

        public Criteria andCardtypcdBetween(String value1, String value2) {
            addCriterion("CARDTYPCD between", value1, value2, "cardtypcd");
            return (Criteria) this;
        }

        public Criteria andCardtypcdNotBetween(String value1, String value2) {
            addCriterion("CARDTYPCD not between", value1, value2, "cardtypcd");
            return (Criteria) this;
        }

        public Criteria andCardtypdescIsNull() {
            addCriterion("CARDTYPDESC is null");
            return (Criteria) this;
        }

        public Criteria andCardtypdescIsNotNull() {
            addCriterion("CARDTYPDESC is not null");
            return (Criteria) this;
        }

        public Criteria andCardtypdescEqualTo(String value) {
            addCriterion("CARDTYPDESC =", value, "cardtypdesc");
            return (Criteria) this;
        }

        public Criteria andCardtypdescNotEqualTo(String value) {
            addCriterion("CARDTYPDESC <>", value, "cardtypdesc");
            return (Criteria) this;
        }

        public Criteria andCardtypdescGreaterThan(String value) {
            addCriterion("CARDTYPDESC >", value, "cardtypdesc");
            return (Criteria) this;
        }

        public Criteria andCardtypdescGreaterThanOrEqualTo(String value) {
            addCriterion("CARDTYPDESC >=", value, "cardtypdesc");
            return (Criteria) this;
        }

        public Criteria andCardtypdescLessThan(String value) {
            addCriterion("CARDTYPDESC <", value, "cardtypdesc");
            return (Criteria) this;
        }

        public Criteria andCardtypdescLessThanOrEqualTo(String value) {
            addCriterion("CARDTYPDESC <=", value, "cardtypdesc");
            return (Criteria) this;
        }

        public Criteria andCardtypdescLike(String value) {
            addCriterion("CARDTYPDESC like", value, "cardtypdesc");
            return (Criteria) this;
        }

        public Criteria andCardtypdescNotLike(String value) {
            addCriterion("CARDTYPDESC not like", value, "cardtypdesc");
            return (Criteria) this;
        }

        public Criteria andCardtypdescIn(List<String> values) {
            addCriterion("CARDTYPDESC in", values, "cardtypdesc");
            return (Criteria) this;
        }

        public Criteria andCardtypdescNotIn(List<String> values) {
            addCriterion("CARDTYPDESC not in", values, "cardtypdesc");
            return (Criteria) this;
        }

        public Criteria andCardtypdescBetween(String value1, String value2) {
            addCriterion("CARDTYPDESC between", value1, value2, "cardtypdesc");
            return (Criteria) this;
        }

        public Criteria andCardtypdescNotBetween(String value1, String value2) {
            addCriterion("CARDTYPDESC not between", value1, value2, "cardtypdesc");
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
     * This class corresponds to the database table CARDTYP
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}