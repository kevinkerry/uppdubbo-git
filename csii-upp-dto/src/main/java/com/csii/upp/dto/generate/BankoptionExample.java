package com.csii.upp.dto.generate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankoptionExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table BANKOPTION1
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table BANKOPTION1
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table BANKOPTION1
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BANKOPTION1
     *
     * @mbggenerated
     */
    public BankoptionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BANKOPTION1
     *
     * @mbggenerated
     */
    protected BankoptionExample(BankoptionExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
        this.distinct = example.distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BANKOPTION1
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BANKOPTION1
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BANKOPTION1
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BANKOPTION1
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BANKOPTION1
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BANKOPTION1
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BANKOPTION1
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
     * This method corresponds to the database table BANKOPTION1
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
     * This method corresponds to the database table BANKOPTION1
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BANKOPTION1
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
     * This class corresponds to the database table BANKOPTION1
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

        public Criteria andBankoptioncdIsNull() {
            addCriterion("BANKOPTIONCD is null");
            return (Criteria) this;
        }

        public Criteria andBankoptioncdIsNotNull() {
            addCriterion("BANKOPTIONCD is not null");
            return (Criteria) this;
        }

        public Criteria andBankoptioncdEqualTo(String value) {
            addCriterion("BANKOPTIONCD =", value, "bankoptioncd");
            return (Criteria) this;
        }

        public Criteria andBankoptioncdNotEqualTo(String value) {
            addCriterion("BANKOPTIONCD <>", value, "bankoptioncd");
            return (Criteria) this;
        }

        public Criteria andBankoptioncdGreaterThan(String value) {
            addCriterion("BANKOPTIONCD >", value, "bankoptioncd");
            return (Criteria) this;
        }

        public Criteria andBankoptioncdGreaterThanOrEqualTo(String value) {
            addCriterion("BANKOPTIONCD >=", value, "bankoptioncd");
            return (Criteria) this;
        }

        public Criteria andBankoptioncdLessThan(String value) {
            addCriterion("BANKOPTIONCD <", value, "bankoptioncd");
            return (Criteria) this;
        }

        public Criteria andBankoptioncdLessThanOrEqualTo(String value) {
            addCriterion("BANKOPTIONCD <=", value, "bankoptioncd");
            return (Criteria) this;
        }

        public Criteria andBankoptioncdLike(String value) {
            addCriterion("BANKOPTIONCD like", value, "bankoptioncd");
            return (Criteria) this;
        }

        public Criteria andBankoptioncdNotLike(String value) {
            addCriterion("BANKOPTIONCD not like", value, "bankoptioncd");
            return (Criteria) this;
        }

        public Criteria andBankoptioncdIn(List<String> values) {
            addCriterion("BANKOPTIONCD in", values, "bankoptioncd");
            return (Criteria) this;
        }

        public Criteria andBankoptioncdNotIn(List<String> values) {
            addCriterion("BANKOPTIONCD not in", values, "bankoptioncd");
            return (Criteria) this;
        }

        public Criteria andBankoptioncdBetween(String value1, String value2) {
            addCriterion("BANKOPTIONCD between", value1, value2, "bankoptioncd");
            return (Criteria) this;
        }

        public Criteria andBankoptioncdNotBetween(String value1, String value2) {
            addCriterion("BANKOPTIONCD not between", value1, value2, "bankoptioncd");
            return (Criteria) this;
        }

        public Criteria andBankorgnbrIsNull() {
            addCriterion("BANKORGNBR is null");
            return (Criteria) this;
        }

        public Criteria andBankorgnbrIsNotNull() {
            addCriterion("BANKORGNBR is not null");
            return (Criteria) this;
        }

        public Criteria andBankorgnbrEqualTo(Long value) {
            addCriterion("BANKORGNBR =", value, "bankorgnbr");
            return (Criteria) this;
        }

        public Criteria andBankorgnbrNotEqualTo(Long value) {
            addCriterion("BANKORGNBR <>", value, "bankorgnbr");
            return (Criteria) this;
        }

        public Criteria andBankorgnbrGreaterThan(Long value) {
            addCriterion("BANKORGNBR >", value, "bankorgnbr");
            return (Criteria) this;
        }

        public Criteria andBankorgnbrGreaterThanOrEqualTo(Long value) {
            addCriterion("BANKORGNBR >=", value, "bankorgnbr");
            return (Criteria) this;
        }

        public Criteria andBankorgnbrLessThan(Long value) {
            addCriterion("BANKORGNBR <", value, "bankorgnbr");
            return (Criteria) this;
        }

        public Criteria andBankorgnbrLessThanOrEqualTo(Long value) {
            addCriterion("BANKORGNBR <=", value, "bankorgnbr");
            return (Criteria) this;
        }

        public Criteria andBankorgnbrIn(List<Long> values) {
            addCriterion("BANKORGNBR in", values, "bankorgnbr");
            return (Criteria) this;
        }

        public Criteria andBankorgnbrNotIn(List<Long> values) {
            addCriterion("BANKORGNBR not in", values, "bankorgnbr");
            return (Criteria) this;
        }

        public Criteria andBankorgnbrBetween(Long value1, Long value2) {
            addCriterion("BANKORGNBR between", value1, value2, "bankorgnbr");
            return (Criteria) this;
        }

        public Criteria andBankorgnbrNotBetween(Long value1, Long value2) {
            addCriterion("BANKORGNBR not between", value1, value2, "bankorgnbr");
            return (Criteria) this;
        }

        public Criteria andBankoptiondescIsNull() {
            addCriterion("BANKOPTIONDESC is null");
            return (Criteria) this;
        }

        public Criteria andBankoptiondescIsNotNull() {
            addCriterion("BANKOPTIONDESC is not null");
            return (Criteria) this;
        }

        public Criteria andBankoptiondescEqualTo(String value) {
            addCriterion("BANKOPTIONDESC =", value, "bankoptiondesc");
            return (Criteria) this;
        }

        public Criteria andBankoptiondescNotEqualTo(String value) {
            addCriterion("BANKOPTIONDESC <>", value, "bankoptiondesc");
            return (Criteria) this;
        }

        public Criteria andBankoptiondescGreaterThan(String value) {
            addCriterion("BANKOPTIONDESC >", value, "bankoptiondesc");
            return (Criteria) this;
        }

        public Criteria andBankoptiondescGreaterThanOrEqualTo(String value) {
            addCriterion("BANKOPTIONDESC >=", value, "bankoptiondesc");
            return (Criteria) this;
        }

        public Criteria andBankoptiondescLessThan(String value) {
            addCriterion("BANKOPTIONDESC <", value, "bankoptiondesc");
            return (Criteria) this;
        }

        public Criteria andBankoptiondescLessThanOrEqualTo(String value) {
            addCriterion("BANKOPTIONDESC <=", value, "bankoptiondesc");
            return (Criteria) this;
        }

        public Criteria andBankoptiondescLike(String value) {
            addCriterion("BANKOPTIONDESC like", value, "bankoptiondesc");
            return (Criteria) this;
        }

        public Criteria andBankoptiondescNotLike(String value) {
            addCriterion("BANKOPTIONDESC not like", value, "bankoptiondesc");
            return (Criteria) this;
        }

        public Criteria andBankoptiondescIn(List<String> values) {
            addCriterion("BANKOPTIONDESC in", values, "bankoptiondesc");
            return (Criteria) this;
        }

        public Criteria andBankoptiondescNotIn(List<String> values) {
            addCriterion("BANKOPTIONDESC not in", values, "bankoptiondesc");
            return (Criteria) this;
        }

        public Criteria andBankoptiondescBetween(String value1, String value2) {
            addCriterion("BANKOPTIONDESC between", value1, value2, "bankoptiondesc");
            return (Criteria) this;
        }

        public Criteria andBankoptiondescNotBetween(String value1, String value2) {
            addCriterion("BANKOPTIONDESC not between", value1, value2, "bankoptiondesc");
            return (Criteria) this;
        }

        public Criteria andBankoptiononynIsNull() {
            addCriterion("BANKOPTIONONYN is null");
            return (Criteria) this;
        }

        public Criteria andBankoptiononynIsNotNull() {
            addCriterion("BANKOPTIONONYN is not null");
            return (Criteria) this;
        }

        public Criteria andBankoptiononynEqualTo(String value) {
            addCriterion("BANKOPTIONONYN =", value, "bankoptiononyn");
            return (Criteria) this;
        }

        public Criteria andBankoptiononynNotEqualTo(String value) {
            addCriterion("BANKOPTIONONYN <>", value, "bankoptiononyn");
            return (Criteria) this;
        }

        public Criteria andBankoptiononynGreaterThan(String value) {
            addCriterion("BANKOPTIONONYN >", value, "bankoptiononyn");
            return (Criteria) this;
        }

        public Criteria andBankoptiononynGreaterThanOrEqualTo(String value) {
            addCriterion("BANKOPTIONONYN >=", value, "bankoptiononyn");
            return (Criteria) this;
        }

        public Criteria andBankoptiononynLessThan(String value) {
            addCriterion("BANKOPTIONONYN <", value, "bankoptiononyn");
            return (Criteria) this;
        }

        public Criteria andBankoptiononynLessThanOrEqualTo(String value) {
            addCriterion("BANKOPTIONONYN <=", value, "bankoptiononyn");
            return (Criteria) this;
        }

        public Criteria andBankoptiononynLike(String value) {
            addCriterion("BANKOPTIONONYN like", value, "bankoptiononyn");
            return (Criteria) this;
        }

        public Criteria andBankoptiononynNotLike(String value) {
            addCriterion("BANKOPTIONONYN not like", value, "bankoptiononyn");
            return (Criteria) this;
        }

        public Criteria andBankoptiononynIn(List<String> values) {
            addCriterion("BANKOPTIONONYN in", values, "bankoptiononyn");
            return (Criteria) this;
        }

        public Criteria andBankoptiononynNotIn(List<String> values) {
            addCriterion("BANKOPTIONONYN not in", values, "bankoptiononyn");
            return (Criteria) this;
        }

        public Criteria andBankoptiononynBetween(String value1, String value2) {
            addCriterion("BANKOPTIONONYN between", value1, value2, "bankoptiononyn");
            return (Criteria) this;
        }

        public Criteria andBankoptiononynNotBetween(String value1, String value2) {
            addCriterion("BANKOPTIONONYN not between", value1, value2, "bankoptiononyn");
            return (Criteria) this;
        }

        public Criteria andBankoptionvalueIsNull() {
            addCriterion("BANKOPTIONVALUE is null");
            return (Criteria) this;
        }

        public Criteria andBankoptionvalueIsNotNull() {
            addCriterion("BANKOPTIONVALUE is not null");
            return (Criteria) this;
        }

        public Criteria andBankoptionvalueEqualTo(String value) {
            addCriterion("BANKOPTIONVALUE =", value, "bankoptionvalue");
            return (Criteria) this;
        }

        public Criteria andBankoptionvalueNotEqualTo(String value) {
            addCriterion("BANKOPTIONVALUE <>", value, "bankoptionvalue");
            return (Criteria) this;
        }

        public Criteria andBankoptionvalueGreaterThan(String value) {
            addCriterion("BANKOPTIONVALUE >", value, "bankoptionvalue");
            return (Criteria) this;
        }

        public Criteria andBankoptionvalueGreaterThanOrEqualTo(String value) {
            addCriterion("BANKOPTIONVALUE >=", value, "bankoptionvalue");
            return (Criteria) this;
        }

        public Criteria andBankoptionvalueLessThan(String value) {
            addCriterion("BANKOPTIONVALUE <", value, "bankoptionvalue");
            return (Criteria) this;
        }

        public Criteria andBankoptionvalueLessThanOrEqualTo(String value) {
            addCriterion("BANKOPTIONVALUE <=", value, "bankoptionvalue");
            return (Criteria) this;
        }

        public Criteria andBankoptionvalueLike(String value) {
            addCriterion("BANKOPTIONVALUE like", value, "bankoptionvalue");
            return (Criteria) this;
        }

        public Criteria andBankoptionvalueNotLike(String value) {
            addCriterion("BANKOPTIONVALUE not like", value, "bankoptionvalue");
            return (Criteria) this;
        }

        public Criteria andBankoptionvalueIn(List<String> values) {
            addCriterion("BANKOPTIONVALUE in", values, "bankoptionvalue");
            return (Criteria) this;
        }

        public Criteria andBankoptionvalueNotIn(List<String> values) {
            addCriterion("BANKOPTIONVALUE not in", values, "bankoptionvalue");
            return (Criteria) this;
        }

        public Criteria andBankoptionvalueBetween(String value1, String value2) {
            addCriterion("BANKOPTIONVALUE between", value1, value2, "bankoptionvalue");
            return (Criteria) this;
        }

        public Criteria andBankoptionvalueNotBetween(String value1, String value2) {
            addCriterion("BANKOPTIONVALUE not between", value1, value2, "bankoptionvalue");
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

        public Criteria andEditbyapplynIsNull() {
            addCriterion("EDITBYAPPLYN is null");
            return (Criteria) this;
        }

        public Criteria andEditbyapplynIsNotNull() {
            addCriterion("EDITBYAPPLYN is not null");
            return (Criteria) this;
        }

        public Criteria andEditbyapplynEqualTo(String value) {
            addCriterion("EDITBYAPPLYN =", value, "editbyapplyn");
            return (Criteria) this;
        }

        public Criteria andEditbyapplynNotEqualTo(String value) {
            addCriterion("EDITBYAPPLYN <>", value, "editbyapplyn");
            return (Criteria) this;
        }

        public Criteria andEditbyapplynGreaterThan(String value) {
            addCriterion("EDITBYAPPLYN >", value, "editbyapplyn");
            return (Criteria) this;
        }

        public Criteria andEditbyapplynGreaterThanOrEqualTo(String value) {
            addCriterion("EDITBYAPPLYN >=", value, "editbyapplyn");
            return (Criteria) this;
        }

        public Criteria andEditbyapplynLessThan(String value) {
            addCriterion("EDITBYAPPLYN <", value, "editbyapplyn");
            return (Criteria) this;
        }

        public Criteria andEditbyapplynLessThanOrEqualTo(String value) {
            addCriterion("EDITBYAPPLYN <=", value, "editbyapplyn");
            return (Criteria) this;
        }

        public Criteria andEditbyapplynLike(String value) {
            addCriterion("EDITBYAPPLYN like", value, "editbyapplyn");
            return (Criteria) this;
        }

        public Criteria andEditbyapplynNotLike(String value) {
            addCriterion("EDITBYAPPLYN not like", value, "editbyapplyn");
            return (Criteria) this;
        }

        public Criteria andEditbyapplynIn(List<String> values) {
            addCriterion("EDITBYAPPLYN in", values, "editbyapplyn");
            return (Criteria) this;
        }

        public Criteria andEditbyapplynNotIn(List<String> values) {
            addCriterion("EDITBYAPPLYN not in", values, "editbyapplyn");
            return (Criteria) this;
        }

        public Criteria andEditbyapplynBetween(String value1, String value2) {
            addCriterion("EDITBYAPPLYN between", value1, value2, "editbyapplyn");
            return (Criteria) this;
        }

        public Criteria andEditbyapplynNotBetween(String value1, String value2) {
            addCriterion("EDITBYAPPLYN not between", value1, value2, "editbyapplyn");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table BANKOPTION1
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}