package com.csii.upp.dto.generate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupplierExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table SUPPLIER
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table SUPPLIER
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table SUPPLIER
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUPPLIER
     *
     * @mbggenerated
     */
    public SupplierExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUPPLIER
     *
     * @mbggenerated
     */
    protected SupplierExample(SupplierExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
        this.distinct = example.distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUPPLIER
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUPPLIER
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUPPLIER
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUPPLIER
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUPPLIER
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUPPLIER
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUPPLIER
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
     * This method corresponds to the database table SUPPLIER
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
     * This method corresponds to the database table SUPPLIER
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUPPLIER
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
     * This class corresponds to the database table SUPPLIER
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

        public Criteria andSuppliernbrIsNull() {
            addCriterion("SUPPLIERNBR is null");
            return (Criteria) this;
        }

        public Criteria andSuppliernbrIsNotNull() {
            addCriterion("SUPPLIERNBR is not null");
            return (Criteria) this;
        }

        public Criteria andSuppliernbrEqualTo(String value) {
            addCriterion("SUPPLIERNBR =", value, "suppliernbr");
            return (Criteria) this;
        }

        public Criteria andSuppliernbrNotEqualTo(String value) {
            addCriterion("SUPPLIERNBR <>", value, "suppliernbr");
            return (Criteria) this;
        }

        public Criteria andSuppliernbrGreaterThan(String value) {
            addCriterion("SUPPLIERNBR >", value, "suppliernbr");
            return (Criteria) this;
        }

        public Criteria andSuppliernbrGreaterThanOrEqualTo(String value) {
            addCriterion("SUPPLIERNBR >=", value, "suppliernbr");
            return (Criteria) this;
        }

        public Criteria andSuppliernbrLessThan(String value) {
            addCriterion("SUPPLIERNBR <", value, "suppliernbr");
            return (Criteria) this;
        }

        public Criteria andSuppliernbrLessThanOrEqualTo(String value) {
            addCriterion("SUPPLIERNBR <=", value, "suppliernbr");
            return (Criteria) this;
        }

        public Criteria andSuppliernbrLike(String value) {
            addCriterion("SUPPLIERNBR like", value, "suppliernbr");
            return (Criteria) this;
        }

        public Criteria andSuppliernbrNotLike(String value) {
            addCriterion("SUPPLIERNBR not like", value, "suppliernbr");
            return (Criteria) this;
        }

        public Criteria andSuppliernbrIn(List<String> values) {
            addCriterion("SUPPLIERNBR in", values, "suppliernbr");
            return (Criteria) this;
        }

        public Criteria andSuppliernbrNotIn(List<String> values) {
            addCriterion("SUPPLIERNBR not in", values, "suppliernbr");
            return (Criteria) this;
        }

        public Criteria andSuppliernbrBetween(String value1, String value2) {
            addCriterion("SUPPLIERNBR between", value1, value2, "suppliernbr");
            return (Criteria) this;
        }

        public Criteria andSuppliernbrNotBetween(String value1, String value2) {
            addCriterion("SUPPLIERNBR not between", value1, value2, "suppliernbr");
            return (Criteria) this;
        }

        public Criteria andSuppliernameIsNull() {
            addCriterion("SUPPLIERNAME is null");
            return (Criteria) this;
        }

        public Criteria andSuppliernameIsNotNull() {
            addCriterion("SUPPLIERNAME is not null");
            return (Criteria) this;
        }

        public Criteria andSuppliernameEqualTo(String value) {
            addCriterion("SUPPLIERNAME =", value, "suppliername");
            return (Criteria) this;
        }

        public Criteria andSuppliernameNotEqualTo(String value) {
            addCriterion("SUPPLIERNAME <>", value, "suppliername");
            return (Criteria) this;
        }

        public Criteria andSuppliernameGreaterThan(String value) {
            addCriterion("SUPPLIERNAME >", value, "suppliername");
            return (Criteria) this;
        }

        public Criteria andSuppliernameGreaterThanOrEqualTo(String value) {
            addCriterion("SUPPLIERNAME >=", value, "suppliername");
            return (Criteria) this;
        }

        public Criteria andSuppliernameLessThan(String value) {
            addCriterion("SUPPLIERNAME <", value, "suppliername");
            return (Criteria) this;
        }

        public Criteria andSuppliernameLessThanOrEqualTo(String value) {
            addCriterion("SUPPLIERNAME <=", value, "suppliername");
            return (Criteria) this;
        }

        public Criteria andSuppliernameLike(String value) {
            addCriterion("SUPPLIERNAME like", value, "suppliername");
            return (Criteria) this;
        }

        public Criteria andSuppliernameNotLike(String value) {
            addCriterion("SUPPLIERNAME not like", value, "suppliername");
            return (Criteria) this;
        }

        public Criteria andSuppliernameIn(List<String> values) {
            addCriterion("SUPPLIERNAME in", values, "suppliername");
            return (Criteria) this;
        }

        public Criteria andSuppliernameNotIn(List<String> values) {
            addCriterion("SUPPLIERNAME not in", values, "suppliername");
            return (Criteria) this;
        }

        public Criteria andSuppliernameBetween(String value1, String value2) {
            addCriterion("SUPPLIERNAME between", value1, value2, "suppliername");
            return (Criteria) this;
        }

        public Criteria andSuppliernameNotBetween(String value1, String value2) {
            addCriterion("SUPPLIERNAME not between", value1, value2, "suppliername");
            return (Criteria) this;
        }

        public Criteria andSuppliertelphoneIsNull() {
            addCriterion("SUPPLIERTELPHONE is null");
            return (Criteria) this;
        }

        public Criteria andSuppliertelphoneIsNotNull() {
            addCriterion("SUPPLIERTELPHONE is not null");
            return (Criteria) this;
        }

        public Criteria andSuppliertelphoneEqualTo(String value) {
            addCriterion("SUPPLIERTELPHONE =", value, "suppliertelphone");
            return (Criteria) this;
        }

        public Criteria andSuppliertelphoneNotEqualTo(String value) {
            addCriterion("SUPPLIERTELPHONE <>", value, "suppliertelphone");
            return (Criteria) this;
        }

        public Criteria andSuppliertelphoneGreaterThan(String value) {
            addCriterion("SUPPLIERTELPHONE >", value, "suppliertelphone");
            return (Criteria) this;
        }

        public Criteria andSuppliertelphoneGreaterThanOrEqualTo(String value) {
            addCriterion("SUPPLIERTELPHONE >=", value, "suppliertelphone");
            return (Criteria) this;
        }

        public Criteria andSuppliertelphoneLessThan(String value) {
            addCriterion("SUPPLIERTELPHONE <", value, "suppliertelphone");
            return (Criteria) this;
        }

        public Criteria andSuppliertelphoneLessThanOrEqualTo(String value) {
            addCriterion("SUPPLIERTELPHONE <=", value, "suppliertelphone");
            return (Criteria) this;
        }

        public Criteria andSuppliertelphoneLike(String value) {
            addCriterion("SUPPLIERTELPHONE like", value, "suppliertelphone");
            return (Criteria) this;
        }

        public Criteria andSuppliertelphoneNotLike(String value) {
            addCriterion("SUPPLIERTELPHONE not like", value, "suppliertelphone");
            return (Criteria) this;
        }

        public Criteria andSuppliertelphoneIn(List<String> values) {
            addCriterion("SUPPLIERTELPHONE in", values, "suppliertelphone");
            return (Criteria) this;
        }

        public Criteria andSuppliertelphoneNotIn(List<String> values) {
            addCriterion("SUPPLIERTELPHONE not in", values, "suppliertelphone");
            return (Criteria) this;
        }

        public Criteria andSuppliertelphoneBetween(String value1, String value2) {
            addCriterion("SUPPLIERTELPHONE between", value1, value2, "suppliertelphone");
            return (Criteria) this;
        }

        public Criteria andSuppliertelphoneNotBetween(String value1, String value2) {
            addCriterion("SUPPLIERTELPHONE not between", value1, value2, "suppliertelphone");
            return (Criteria) this;
        }

        public Criteria andSupplieraddressIsNull() {
            addCriterion("SUPPLIERADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andSupplieraddressIsNotNull() {
            addCriterion("SUPPLIERADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andSupplieraddressEqualTo(String value) {
            addCriterion("SUPPLIERADDRESS =", value, "supplieraddress");
            return (Criteria) this;
        }

        public Criteria andSupplieraddressNotEqualTo(String value) {
            addCriterion("SUPPLIERADDRESS <>", value, "supplieraddress");
            return (Criteria) this;
        }

        public Criteria andSupplieraddressGreaterThan(String value) {
            addCriterion("SUPPLIERADDRESS >", value, "supplieraddress");
            return (Criteria) this;
        }

        public Criteria andSupplieraddressGreaterThanOrEqualTo(String value) {
            addCriterion("SUPPLIERADDRESS >=", value, "supplieraddress");
            return (Criteria) this;
        }

        public Criteria andSupplieraddressLessThan(String value) {
            addCriterion("SUPPLIERADDRESS <", value, "supplieraddress");
            return (Criteria) this;
        }

        public Criteria andSupplieraddressLessThanOrEqualTo(String value) {
            addCriterion("SUPPLIERADDRESS <=", value, "supplieraddress");
            return (Criteria) this;
        }

        public Criteria andSupplieraddressLike(String value) {
            addCriterion("SUPPLIERADDRESS like", value, "supplieraddress");
            return (Criteria) this;
        }

        public Criteria andSupplieraddressNotLike(String value) {
            addCriterion("SUPPLIERADDRESS not like", value, "supplieraddress");
            return (Criteria) this;
        }

        public Criteria andSupplieraddressIn(List<String> values) {
            addCriterion("SUPPLIERADDRESS in", values, "supplieraddress");
            return (Criteria) this;
        }

        public Criteria andSupplieraddressNotIn(List<String> values) {
            addCriterion("SUPPLIERADDRESS not in", values, "supplieraddress");
            return (Criteria) this;
        }

        public Criteria andSupplieraddressBetween(String value1, String value2) {
            addCriterion("SUPPLIERADDRESS between", value1, value2, "supplieraddress");
            return (Criteria) this;
        }

        public Criteria andSupplieraddressNotBetween(String value1, String value2) {
            addCriterion("SUPPLIERADDRESS not between", value1, value2, "supplieraddress");
            return (Criteria) this;
        }

        public Criteria andSuppliermailIsNull() {
            addCriterion("SUPPLIERMAIL is null");
            return (Criteria) this;
        }

        public Criteria andSuppliermailIsNotNull() {
            addCriterion("SUPPLIERMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andSuppliermailEqualTo(String value) {
            addCriterion("SUPPLIERMAIL =", value, "suppliermail");
            return (Criteria) this;
        }

        public Criteria andSuppliermailNotEqualTo(String value) {
            addCriterion("SUPPLIERMAIL <>", value, "suppliermail");
            return (Criteria) this;
        }

        public Criteria andSuppliermailGreaterThan(String value) {
            addCriterion("SUPPLIERMAIL >", value, "suppliermail");
            return (Criteria) this;
        }

        public Criteria andSuppliermailGreaterThanOrEqualTo(String value) {
            addCriterion("SUPPLIERMAIL >=", value, "suppliermail");
            return (Criteria) this;
        }

        public Criteria andSuppliermailLessThan(String value) {
            addCriterion("SUPPLIERMAIL <", value, "suppliermail");
            return (Criteria) this;
        }

        public Criteria andSuppliermailLessThanOrEqualTo(String value) {
            addCriterion("SUPPLIERMAIL <=", value, "suppliermail");
            return (Criteria) this;
        }

        public Criteria andSuppliermailLike(String value) {
            addCriterion("SUPPLIERMAIL like", value, "suppliermail");
            return (Criteria) this;
        }

        public Criteria andSuppliermailNotLike(String value) {
            addCriterion("SUPPLIERMAIL not like", value, "suppliermail");
            return (Criteria) this;
        }

        public Criteria andSuppliermailIn(List<String> values) {
            addCriterion("SUPPLIERMAIL in", values, "suppliermail");
            return (Criteria) this;
        }

        public Criteria andSuppliermailNotIn(List<String> values) {
            addCriterion("SUPPLIERMAIL not in", values, "suppliermail");
            return (Criteria) this;
        }

        public Criteria andSuppliermailBetween(String value1, String value2) {
            addCriterion("SUPPLIERMAIL between", value1, value2, "suppliermail");
            return (Criteria) this;
        }

        public Criteria andSuppliermailNotBetween(String value1, String value2) {
            addCriterion("SUPPLIERMAIL not between", value1, value2, "suppliermail");
            return (Criteria) this;
        }

        public Criteria andSupplierservicephoneIsNull() {
            addCriterion("SUPPLIERSERVICEPHONE is null");
            return (Criteria) this;
        }

        public Criteria andSupplierservicephoneIsNotNull() {
            addCriterion("SUPPLIERSERVICEPHONE is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierservicephoneEqualTo(String value) {
            addCriterion("SUPPLIERSERVICEPHONE =", value, "supplierservicephone");
            return (Criteria) this;
        }

        public Criteria andSupplierservicephoneNotEqualTo(String value) {
            addCriterion("SUPPLIERSERVICEPHONE <>", value, "supplierservicephone");
            return (Criteria) this;
        }

        public Criteria andSupplierservicephoneGreaterThan(String value) {
            addCriterion("SUPPLIERSERVICEPHONE >", value, "supplierservicephone");
            return (Criteria) this;
        }

        public Criteria andSupplierservicephoneGreaterThanOrEqualTo(String value) {
            addCriterion("SUPPLIERSERVICEPHONE >=", value, "supplierservicephone");
            return (Criteria) this;
        }

        public Criteria andSupplierservicephoneLessThan(String value) {
            addCriterion("SUPPLIERSERVICEPHONE <", value, "supplierservicephone");
            return (Criteria) this;
        }

        public Criteria andSupplierservicephoneLessThanOrEqualTo(String value) {
            addCriterion("SUPPLIERSERVICEPHONE <=", value, "supplierservicephone");
            return (Criteria) this;
        }

        public Criteria andSupplierservicephoneLike(String value) {
            addCriterion("SUPPLIERSERVICEPHONE like", value, "supplierservicephone");
            return (Criteria) this;
        }

        public Criteria andSupplierservicephoneNotLike(String value) {
            addCriterion("SUPPLIERSERVICEPHONE not like", value, "supplierservicephone");
            return (Criteria) this;
        }

        public Criteria andSupplierservicephoneIn(List<String> values) {
            addCriterion("SUPPLIERSERVICEPHONE in", values, "supplierservicephone");
            return (Criteria) this;
        }

        public Criteria andSupplierservicephoneNotIn(List<String> values) {
            addCriterion("SUPPLIERSERVICEPHONE not in", values, "supplierservicephone");
            return (Criteria) this;
        }

        public Criteria andSupplierservicephoneBetween(String value1, String value2) {
            addCriterion("SUPPLIERSERVICEPHONE between", value1, value2, "supplierservicephone");
            return (Criteria) this;
        }

        public Criteria andSupplierservicephoneNotBetween(String value1, String value2) {
            addCriterion("SUPPLIERSERVICEPHONE not between", value1, value2, "supplierservicephone");
            return (Criteria) this;
        }

        public Criteria andSupplierlicenseidIsNull() {
            addCriterion("SUPPLIERLICENSEID is null");
            return (Criteria) this;
        }

        public Criteria andSupplierlicenseidIsNotNull() {
            addCriterion("SUPPLIERLICENSEID is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierlicenseidEqualTo(String value) {
            addCriterion("SUPPLIERLICENSEID =", value, "supplierlicenseid");
            return (Criteria) this;
        }

        public Criteria andSupplierlicenseidNotEqualTo(String value) {
            addCriterion("SUPPLIERLICENSEID <>", value, "supplierlicenseid");
            return (Criteria) this;
        }

        public Criteria andSupplierlicenseidGreaterThan(String value) {
            addCriterion("SUPPLIERLICENSEID >", value, "supplierlicenseid");
            return (Criteria) this;
        }

        public Criteria andSupplierlicenseidGreaterThanOrEqualTo(String value) {
            addCriterion("SUPPLIERLICENSEID >=", value, "supplierlicenseid");
            return (Criteria) this;
        }

        public Criteria andSupplierlicenseidLessThan(String value) {
            addCriterion("SUPPLIERLICENSEID <", value, "supplierlicenseid");
            return (Criteria) this;
        }

        public Criteria andSupplierlicenseidLessThanOrEqualTo(String value) {
            addCriterion("SUPPLIERLICENSEID <=", value, "supplierlicenseid");
            return (Criteria) this;
        }

        public Criteria andSupplierlicenseidLike(String value) {
            addCriterion("SUPPLIERLICENSEID like", value, "supplierlicenseid");
            return (Criteria) this;
        }

        public Criteria andSupplierlicenseidNotLike(String value) {
            addCriterion("SUPPLIERLICENSEID not like", value, "supplierlicenseid");
            return (Criteria) this;
        }

        public Criteria andSupplierlicenseidIn(List<String> values) {
            addCriterion("SUPPLIERLICENSEID in", values, "supplierlicenseid");
            return (Criteria) this;
        }

        public Criteria andSupplierlicenseidNotIn(List<String> values) {
            addCriterion("SUPPLIERLICENSEID not in", values, "supplierlicenseid");
            return (Criteria) this;
        }

        public Criteria andSupplierlicenseidBetween(String value1, String value2) {
            addCriterion("SUPPLIERLICENSEID between", value1, value2, "supplierlicenseid");
            return (Criteria) this;
        }

        public Criteria andSupplierlicenseidNotBetween(String value1, String value2) {
            addCriterion("SUPPLIERLICENSEID not between", value1, value2, "supplierlicenseid");
            return (Criteria) this;
        }

        public Criteria andSupplierporationnameIsNull() {
            addCriterion("SUPPLIERPORATIONNAME is null");
            return (Criteria) this;
        }

        public Criteria andSupplierporationnameIsNotNull() {
            addCriterion("SUPPLIERPORATIONNAME is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierporationnameEqualTo(String value) {
            addCriterion("SUPPLIERPORATIONNAME =", value, "supplierporationname");
            return (Criteria) this;
        }

        public Criteria andSupplierporationnameNotEqualTo(String value) {
            addCriterion("SUPPLIERPORATIONNAME <>", value, "supplierporationname");
            return (Criteria) this;
        }

        public Criteria andSupplierporationnameGreaterThan(String value) {
            addCriterion("SUPPLIERPORATIONNAME >", value, "supplierporationname");
            return (Criteria) this;
        }

        public Criteria andSupplierporationnameGreaterThanOrEqualTo(String value) {
            addCriterion("SUPPLIERPORATIONNAME >=", value, "supplierporationname");
            return (Criteria) this;
        }

        public Criteria andSupplierporationnameLessThan(String value) {
            addCriterion("SUPPLIERPORATIONNAME <", value, "supplierporationname");
            return (Criteria) this;
        }

        public Criteria andSupplierporationnameLessThanOrEqualTo(String value) {
            addCriterion("SUPPLIERPORATIONNAME <=", value, "supplierporationname");
            return (Criteria) this;
        }

        public Criteria andSupplierporationnameLike(String value) {
            addCriterion("SUPPLIERPORATIONNAME like", value, "supplierporationname");
            return (Criteria) this;
        }

        public Criteria andSupplierporationnameNotLike(String value) {
            addCriterion("SUPPLIERPORATIONNAME not like", value, "supplierporationname");
            return (Criteria) this;
        }

        public Criteria andSupplierporationnameIn(List<String> values) {
            addCriterion("SUPPLIERPORATIONNAME in", values, "supplierporationname");
            return (Criteria) this;
        }

        public Criteria andSupplierporationnameNotIn(List<String> values) {
            addCriterion("SUPPLIERPORATIONNAME not in", values, "supplierporationname");
            return (Criteria) this;
        }

        public Criteria andSupplierporationnameBetween(String value1, String value2) {
            addCriterion("SUPPLIERPORATIONNAME between", value1, value2, "supplierporationname");
            return (Criteria) this;
        }

        public Criteria andSupplierporationnameNotBetween(String value1, String value2) {
            addCriterion("SUPPLIERPORATIONNAME not between", value1, value2, "supplierporationname");
            return (Criteria) this;
        }

        public Criteria andSupplierporationpapertypIsNull() {
            addCriterion("SUPPLIERPORATIONPAPERTYP is null");
            return (Criteria) this;
        }

        public Criteria andSupplierporationpapertypIsNotNull() {
            addCriterion("SUPPLIERPORATIONPAPERTYP is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierporationpapertypEqualTo(String value) {
            addCriterion("SUPPLIERPORATIONPAPERTYP =", value, "supplierporationpapertyp");
            return (Criteria) this;
        }

        public Criteria andSupplierporationpapertypNotEqualTo(String value) {
            addCriterion("SUPPLIERPORATIONPAPERTYP <>", value, "supplierporationpapertyp");
            return (Criteria) this;
        }

        public Criteria andSupplierporationpapertypGreaterThan(String value) {
            addCriterion("SUPPLIERPORATIONPAPERTYP >", value, "supplierporationpapertyp");
            return (Criteria) this;
        }

        public Criteria andSupplierporationpapertypGreaterThanOrEqualTo(String value) {
            addCriterion("SUPPLIERPORATIONPAPERTYP >=", value, "supplierporationpapertyp");
            return (Criteria) this;
        }

        public Criteria andSupplierporationpapertypLessThan(String value) {
            addCriterion("SUPPLIERPORATIONPAPERTYP <", value, "supplierporationpapertyp");
            return (Criteria) this;
        }

        public Criteria andSupplierporationpapertypLessThanOrEqualTo(String value) {
            addCriterion("SUPPLIERPORATIONPAPERTYP <=", value, "supplierporationpapertyp");
            return (Criteria) this;
        }

        public Criteria andSupplierporationpapertypLike(String value) {
            addCriterion("SUPPLIERPORATIONPAPERTYP like", value, "supplierporationpapertyp");
            return (Criteria) this;
        }

        public Criteria andSupplierporationpapertypNotLike(String value) {
            addCriterion("SUPPLIERPORATIONPAPERTYP not like", value, "supplierporationpapertyp");
            return (Criteria) this;
        }

        public Criteria andSupplierporationpapertypIn(List<String> values) {
            addCriterion("SUPPLIERPORATIONPAPERTYP in", values, "supplierporationpapertyp");
            return (Criteria) this;
        }

        public Criteria andSupplierporationpapertypNotIn(List<String> values) {
            addCriterion("SUPPLIERPORATIONPAPERTYP not in", values, "supplierporationpapertyp");
            return (Criteria) this;
        }

        public Criteria andSupplierporationpapertypBetween(String value1, String value2) {
            addCriterion("SUPPLIERPORATIONPAPERTYP between", value1, value2, "supplierporationpapertyp");
            return (Criteria) this;
        }

        public Criteria andSupplierporationpapertypNotBetween(String value1, String value2) {
            addCriterion("SUPPLIERPORATIONPAPERTYP not between", value1, value2, "supplierporationpapertyp");
            return (Criteria) this;
        }

        public Criteria andSupplierporationpapernbrIsNull() {
            addCriterion("SUPPLIERPORATIONPAPERNBR is null");
            return (Criteria) this;
        }

        public Criteria andSupplierporationpapernbrIsNotNull() {
            addCriterion("SUPPLIERPORATIONPAPERNBR is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierporationpapernbrEqualTo(String value) {
            addCriterion("SUPPLIERPORATIONPAPERNBR =", value, "supplierporationpapernbr");
            return (Criteria) this;
        }

        public Criteria andSupplierporationpapernbrNotEqualTo(String value) {
            addCriterion("SUPPLIERPORATIONPAPERNBR <>", value, "supplierporationpapernbr");
            return (Criteria) this;
        }

        public Criteria andSupplierporationpapernbrGreaterThan(String value) {
            addCriterion("SUPPLIERPORATIONPAPERNBR >", value, "supplierporationpapernbr");
            return (Criteria) this;
        }

        public Criteria andSupplierporationpapernbrGreaterThanOrEqualTo(String value) {
            addCriterion("SUPPLIERPORATIONPAPERNBR >=", value, "supplierporationpapernbr");
            return (Criteria) this;
        }

        public Criteria andSupplierporationpapernbrLessThan(String value) {
            addCriterion("SUPPLIERPORATIONPAPERNBR <", value, "supplierporationpapernbr");
            return (Criteria) this;
        }

        public Criteria andSupplierporationpapernbrLessThanOrEqualTo(String value) {
            addCriterion("SUPPLIERPORATIONPAPERNBR <=", value, "supplierporationpapernbr");
            return (Criteria) this;
        }

        public Criteria andSupplierporationpapernbrLike(String value) {
            addCriterion("SUPPLIERPORATIONPAPERNBR like", value, "supplierporationpapernbr");
            return (Criteria) this;
        }

        public Criteria andSupplierporationpapernbrNotLike(String value) {
            addCriterion("SUPPLIERPORATIONPAPERNBR not like", value, "supplierporationpapernbr");
            return (Criteria) this;
        }

        public Criteria andSupplierporationpapernbrIn(List<String> values) {
            addCriterion("SUPPLIERPORATIONPAPERNBR in", values, "supplierporationpapernbr");
            return (Criteria) this;
        }

        public Criteria andSupplierporationpapernbrNotIn(List<String> values) {
            addCriterion("SUPPLIERPORATIONPAPERNBR not in", values, "supplierporationpapernbr");
            return (Criteria) this;
        }

        public Criteria andSupplierporationpapernbrBetween(String value1, String value2) {
            addCriterion("SUPPLIERPORATIONPAPERNBR between", value1, value2, "supplierporationpapernbr");
            return (Criteria) this;
        }

        public Criteria andSupplierporationpapernbrNotBetween(String value1, String value2) {
            addCriterion("SUPPLIERPORATIONPAPERNBR not between", value1, value2, "supplierporationpapernbr");
            return (Criteria) this;
        }

        public Criteria andSupplierremarkIsNull() {
            addCriterion("SUPPLIERREMARK is null");
            return (Criteria) this;
        }

        public Criteria andSupplierremarkIsNotNull() {
            addCriterion("SUPPLIERREMARK is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierremarkEqualTo(String value) {
            addCriterion("SUPPLIERREMARK =", value, "supplierremark");
            return (Criteria) this;
        }

        public Criteria andSupplierremarkNotEqualTo(String value) {
            addCriterion("SUPPLIERREMARK <>", value, "supplierremark");
            return (Criteria) this;
        }

        public Criteria andSupplierremarkGreaterThan(String value) {
            addCriterion("SUPPLIERREMARK >", value, "supplierremark");
            return (Criteria) this;
        }

        public Criteria andSupplierremarkGreaterThanOrEqualTo(String value) {
            addCriterion("SUPPLIERREMARK >=", value, "supplierremark");
            return (Criteria) this;
        }

        public Criteria andSupplierremarkLessThan(String value) {
            addCriterion("SUPPLIERREMARK <", value, "supplierremark");
            return (Criteria) this;
        }

        public Criteria andSupplierremarkLessThanOrEqualTo(String value) {
            addCriterion("SUPPLIERREMARK <=", value, "supplierremark");
            return (Criteria) this;
        }

        public Criteria andSupplierremarkLike(String value) {
            addCriterion("SUPPLIERREMARK like", value, "supplierremark");
            return (Criteria) this;
        }

        public Criteria andSupplierremarkNotLike(String value) {
            addCriterion("SUPPLIERREMARK not like", value, "supplierremark");
            return (Criteria) this;
        }

        public Criteria andSupplierremarkIn(List<String> values) {
            addCriterion("SUPPLIERREMARK in", values, "supplierremark");
            return (Criteria) this;
        }

        public Criteria andSupplierremarkNotIn(List<String> values) {
            addCriterion("SUPPLIERREMARK not in", values, "supplierremark");
            return (Criteria) this;
        }

        public Criteria andSupplierremarkBetween(String value1, String value2) {
            addCriterion("SUPPLIERREMARK between", value1, value2, "supplierremark");
            return (Criteria) this;
        }

        public Criteria andSupplierremarkNotBetween(String value1, String value2) {
            addCriterion("SUPPLIERREMARK not between", value1, value2, "supplierremark");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table SUPPLIER
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}