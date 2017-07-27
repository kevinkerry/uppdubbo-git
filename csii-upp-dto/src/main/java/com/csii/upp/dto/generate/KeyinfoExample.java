package com.csii.upp.dto.generate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class KeyinfoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table KEYINFO
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table KEYINFO
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table KEYINFO
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KEYINFO
     *
     * @mbggenerated
     */
    public KeyinfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KEYINFO
     *
     * @mbggenerated
     */
    protected KeyinfoExample(KeyinfoExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
        this.distinct = example.distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KEYINFO
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KEYINFO
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KEYINFO
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KEYINFO
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KEYINFO
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KEYINFO
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KEYINFO
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
     * This method corresponds to the database table KEYINFO
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
     * This method corresponds to the database table KEYINFO
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KEYINFO
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
     * This class corresponds to the database table KEYINFO
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andKeyidIsNull() {
            addCriterion("KEYID is null");
            return (Criteria) this;
        }

        public Criteria andKeyidIsNotNull() {
            addCriterion("KEYID is not null");
            return (Criteria) this;
        }

        public Criteria andKeyidEqualTo(String value) {
            addCriterion("KEYID =", value, "keyid");
            return (Criteria) this;
        }

        public Criteria andKeyidNotEqualTo(String value) {
            addCriterion("KEYID <>", value, "keyid");
            return (Criteria) this;
        }

        public Criteria andKeyidGreaterThan(String value) {
            addCriterion("KEYID >", value, "keyid");
            return (Criteria) this;
        }

        public Criteria andKeyidGreaterThanOrEqualTo(String value) {
            addCriterion("KEYID >=", value, "keyid");
            return (Criteria) this;
        }

        public Criteria andKeyidLessThan(String value) {
            addCriterion("KEYID <", value, "keyid");
            return (Criteria) this;
        }

        public Criteria andKeyidLessThanOrEqualTo(String value) {
            addCriterion("KEYID <=", value, "keyid");
            return (Criteria) this;
        }

        public Criteria andKeyidLike(String value) {
            addCriterion("KEYID like", value, "keyid");
            return (Criteria) this;
        }

        public Criteria andKeyidNotLike(String value) {
            addCriterion("KEYID not like", value, "keyid");
            return (Criteria) this;
        }

        public Criteria andKeyidIn(List<String> values) {
            addCriterion("KEYID in", values, "keyid");
            return (Criteria) this;
        }

        public Criteria andKeyidNotIn(List<String> values) {
            addCriterion("KEYID not in", values, "keyid");
            return (Criteria) this;
        }

        public Criteria andKeyidBetween(String value1, String value2) {
            addCriterion("KEYID between", value1, value2, "keyid");
            return (Criteria) this;
        }

        public Criteria andKeyidNotBetween(String value1, String value2) {
            addCriterion("KEYID not between", value1, value2, "keyid");
            return (Criteria) this;
        }

        public Criteria andKeynameIsNull() {
            addCriterion("KEYNAME is null");
            return (Criteria) this;
        }

        public Criteria andKeynameIsNotNull() {
            addCriterion("KEYNAME is not null");
            return (Criteria) this;
        }

        public Criteria andKeynameEqualTo(String value) {
            addCriterion("KEYNAME =", value, "keyname");
            return (Criteria) this;
        }

        public Criteria andKeynameNotEqualTo(String value) {
            addCriterion("KEYNAME <>", value, "keyname");
            return (Criteria) this;
        }

        public Criteria andKeynameGreaterThan(String value) {
            addCriterion("KEYNAME >", value, "keyname");
            return (Criteria) this;
        }

        public Criteria andKeynameGreaterThanOrEqualTo(String value) {
            addCriterion("KEYNAME >=", value, "keyname");
            return (Criteria) this;
        }

        public Criteria andKeynameLessThan(String value) {
            addCriterion("KEYNAME <", value, "keyname");
            return (Criteria) this;
        }

        public Criteria andKeynameLessThanOrEqualTo(String value) {
            addCriterion("KEYNAME <=", value, "keyname");
            return (Criteria) this;
        }

        public Criteria andKeynameLike(String value) {
            addCriterion("KEYNAME like", value, "keyname");
            return (Criteria) this;
        }

        public Criteria andKeynameNotLike(String value) {
            addCriterion("KEYNAME not like", value, "keyname");
            return (Criteria) this;
        }

        public Criteria andKeynameIn(List<String> values) {
            addCriterion("KEYNAME in", values, "keyname");
            return (Criteria) this;
        }

        public Criteria andKeynameNotIn(List<String> values) {
            addCriterion("KEYNAME not in", values, "keyname");
            return (Criteria) this;
        }

        public Criteria andKeynameBetween(String value1, String value2) {
            addCriterion("KEYNAME between", value1, value2, "keyname");
            return (Criteria) this;
        }

        public Criteria andKeynameNotBetween(String value1, String value2) {
            addCriterion("KEYNAME not between", value1, value2, "keyname");
            return (Criteria) this;
        }

        public Criteria andKeytypeIsNull() {
            addCriterion("KEYTYPE is null");
            return (Criteria) this;
        }

        public Criteria andKeytypeIsNotNull() {
            addCriterion("KEYTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andKeytypeEqualTo(String value) {
            addCriterion("KEYTYPE =", value, "keytype");
            return (Criteria) this;
        }

        public Criteria andKeytypeNotEqualTo(String value) {
            addCriterion("KEYTYPE <>", value, "keytype");
            return (Criteria) this;
        }

        public Criteria andKeytypeGreaterThan(String value) {
            addCriterion("KEYTYPE >", value, "keytype");
            return (Criteria) this;
        }

        public Criteria andKeytypeGreaterThanOrEqualTo(String value) {
            addCriterion("KEYTYPE >=", value, "keytype");
            return (Criteria) this;
        }

        public Criteria andKeytypeLessThan(String value) {
            addCriterion("KEYTYPE <", value, "keytype");
            return (Criteria) this;
        }

        public Criteria andKeytypeLessThanOrEqualTo(String value) {
            addCriterion("KEYTYPE <=", value, "keytype");
            return (Criteria) this;
        }

        public Criteria andKeytypeLike(String value) {
            addCriterion("KEYTYPE like", value, "keytype");
            return (Criteria) this;
        }

        public Criteria andKeytypeNotLike(String value) {
            addCriterion("KEYTYPE not like", value, "keytype");
            return (Criteria) this;
        }

        public Criteria andKeytypeIn(List<String> values) {
            addCriterion("KEYTYPE in", values, "keytype");
            return (Criteria) this;
        }

        public Criteria andKeytypeNotIn(List<String> values) {
            addCriterion("KEYTYPE not in", values, "keytype");
            return (Criteria) this;
        }

        public Criteria andKeytypeBetween(String value1, String value2) {
            addCriterion("KEYTYPE between", value1, value2, "keytype");
            return (Criteria) this;
        }

        public Criteria andKeytypeNotBetween(String value1, String value2) {
            addCriterion("KEYTYPE not between", value1, value2, "keytype");
            return (Criteria) this;
        }

        public Criteria andKeysnIsNull() {
            addCriterion("KEYSN is null");
            return (Criteria) this;
        }

        public Criteria andKeysnIsNotNull() {
            addCriterion("KEYSN is not null");
            return (Criteria) this;
        }

        public Criteria andKeysnEqualTo(String value) {
            addCriterion("KEYSN =", value, "keysn");
            return (Criteria) this;
        }

        public Criteria andKeysnNotEqualTo(String value) {
            addCriterion("KEYSN <>", value, "keysn");
            return (Criteria) this;
        }

        public Criteria andKeysnGreaterThan(String value) {
            addCriterion("KEYSN >", value, "keysn");
            return (Criteria) this;
        }

        public Criteria andKeysnGreaterThanOrEqualTo(String value) {
            addCriterion("KEYSN >=", value, "keysn");
            return (Criteria) this;
        }

        public Criteria andKeysnLessThan(String value) {
            addCriterion("KEYSN <", value, "keysn");
            return (Criteria) this;
        }

        public Criteria andKeysnLessThanOrEqualTo(String value) {
            addCriterion("KEYSN <=", value, "keysn");
            return (Criteria) this;
        }

        public Criteria andKeysnLike(String value) {
            addCriterion("KEYSN like", value, "keysn");
            return (Criteria) this;
        }

        public Criteria andKeysnNotLike(String value) {
            addCriterion("KEYSN not like", value, "keysn");
            return (Criteria) this;
        }

        public Criteria andKeysnIn(List<String> values) {
            addCriterion("KEYSN in", values, "keysn");
            return (Criteria) this;
        }

        public Criteria andKeysnNotIn(List<String> values) {
            addCriterion("KEYSN not in", values, "keysn");
            return (Criteria) this;
        }

        public Criteria andKeysnBetween(String value1, String value2) {
            addCriterion("KEYSN between", value1, value2, "keysn");
            return (Criteria) this;
        }

        public Criteria andKeysnNotBetween(String value1, String value2) {
            addCriterion("KEYSN not between", value1, value2, "keysn");
            return (Criteria) this;
        }

        public Criteria andKeypasswordIsNull() {
            addCriterion("KEYPASSWORD is null");
            return (Criteria) this;
        }

        public Criteria andKeypasswordIsNotNull() {
            addCriterion("KEYPASSWORD is not null");
            return (Criteria) this;
        }

        public Criteria andKeypasswordEqualTo(String value) {
            addCriterion("KEYPASSWORD =", value, "keypassword");
            return (Criteria) this;
        }

        public Criteria andKeypasswordNotEqualTo(String value) {
            addCriterion("KEYPASSWORD <>", value, "keypassword");
            return (Criteria) this;
        }

        public Criteria andKeypasswordGreaterThan(String value) {
            addCriterion("KEYPASSWORD >", value, "keypassword");
            return (Criteria) this;
        }

        public Criteria andKeypasswordGreaterThanOrEqualTo(String value) {
            addCriterion("KEYPASSWORD >=", value, "keypassword");
            return (Criteria) this;
        }

        public Criteria andKeypasswordLessThan(String value) {
            addCriterion("KEYPASSWORD <", value, "keypassword");
            return (Criteria) this;
        }

        public Criteria andKeypasswordLessThanOrEqualTo(String value) {
            addCriterion("KEYPASSWORD <=", value, "keypassword");
            return (Criteria) this;
        }

        public Criteria andKeypasswordLike(String value) {
            addCriterion("KEYPASSWORD like", value, "keypassword");
            return (Criteria) this;
        }

        public Criteria andKeypasswordNotLike(String value) {
            addCriterion("KEYPASSWORD not like", value, "keypassword");
            return (Criteria) this;
        }

        public Criteria andKeypasswordIn(List<String> values) {
            addCriterion("KEYPASSWORD in", values, "keypassword");
            return (Criteria) this;
        }

        public Criteria andKeypasswordNotIn(List<String> values) {
            addCriterion("KEYPASSWORD not in", values, "keypassword");
            return (Criteria) this;
        }

        public Criteria andKeypasswordBetween(String value1, String value2) {
            addCriterion("KEYPASSWORD between", value1, value2, "keypassword");
            return (Criteria) this;
        }

        public Criteria andKeypasswordNotBetween(String value1, String value2) {
            addCriterion("KEYPASSWORD not between", value1, value2, "keypassword");
            return (Criteria) this;
        }

        public Criteria andKeystateIsNull() {
            addCriterion("KEYSTATE is null");
            return (Criteria) this;
        }

        public Criteria andKeystateIsNotNull() {
            addCriterion("KEYSTATE is not null");
            return (Criteria) this;
        }

        public Criteria andKeystateEqualTo(String value) {
            addCriterion("KEYSTATE =", value, "keystate");
            return (Criteria) this;
        }

        public Criteria andKeystateNotEqualTo(String value) {
            addCriterion("KEYSTATE <>", value, "keystate");
            return (Criteria) this;
        }

        public Criteria andKeystateGreaterThan(String value) {
            addCriterion("KEYSTATE >", value, "keystate");
            return (Criteria) this;
        }

        public Criteria andKeystateGreaterThanOrEqualTo(String value) {
            addCriterion("KEYSTATE >=", value, "keystate");
            return (Criteria) this;
        }

        public Criteria andKeystateLessThan(String value) {
            addCriterion("KEYSTATE <", value, "keystate");
            return (Criteria) this;
        }

        public Criteria andKeystateLessThanOrEqualTo(String value) {
            addCriterion("KEYSTATE <=", value, "keystate");
            return (Criteria) this;
        }

        public Criteria andKeystateLike(String value) {
            addCriterion("KEYSTATE like", value, "keystate");
            return (Criteria) this;
        }

        public Criteria andKeystateNotLike(String value) {
            addCriterion("KEYSTATE not like", value, "keystate");
            return (Criteria) this;
        }

        public Criteria andKeystateIn(List<String> values) {
            addCriterion("KEYSTATE in", values, "keystate");
            return (Criteria) this;
        }

        public Criteria andKeystateNotIn(List<String> values) {
            addCriterion("KEYSTATE not in", values, "keystate");
            return (Criteria) this;
        }

        public Criteria andKeystateBetween(String value1, String value2) {
            addCriterion("KEYSTATE between", value1, value2, "keystate");
            return (Criteria) this;
        }

        public Criteria andKeystateNotBetween(String value1, String value2) {
            addCriterion("KEYSTATE not between", value1, value2, "keystate");
            return (Criteria) this;
        }

        public Criteria andKeydisableddateIsNull() {
            addCriterion("KEYDISABLEDDATE is null");
            return (Criteria) this;
        }

        public Criteria andKeydisableddateIsNotNull() {
            addCriterion("KEYDISABLEDDATE is not null");
            return (Criteria) this;
        }

        public Criteria andKeydisableddateEqualTo(Date value) {
            addCriterionForJDBCDate("KEYDISABLEDDATE =", value, "keydisableddate");
            return (Criteria) this;
        }

        public Criteria andKeydisableddateNotEqualTo(Date value) {
            addCriterionForJDBCDate("KEYDISABLEDDATE <>", value, "keydisableddate");
            return (Criteria) this;
        }

        public Criteria andKeydisableddateGreaterThan(Date value) {
            addCriterionForJDBCDate("KEYDISABLEDDATE >", value, "keydisableddate");
            return (Criteria) this;
        }

        public Criteria andKeydisableddateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("KEYDISABLEDDATE >=", value, "keydisableddate");
            return (Criteria) this;
        }

        public Criteria andKeydisableddateLessThan(Date value) {
            addCriterionForJDBCDate("KEYDISABLEDDATE <", value, "keydisableddate");
            return (Criteria) this;
        }

        public Criteria andKeydisableddateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("KEYDISABLEDDATE <=", value, "keydisableddate");
            return (Criteria) this;
        }

        public Criteria andKeydisableddateIn(List<Date> values) {
            addCriterionForJDBCDate("KEYDISABLEDDATE in", values, "keydisableddate");
            return (Criteria) this;
        }

        public Criteria andKeydisableddateNotIn(List<Date> values) {
            addCriterionForJDBCDate("KEYDISABLEDDATE not in", values, "keydisableddate");
            return (Criteria) this;
        }

        public Criteria andKeydisableddateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("KEYDISABLEDDATE between", value1, value2, "keydisableddate");
            return (Criteria) this;
        }

        public Criteria andKeydisableddateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("KEYDISABLEDDATE not between", value1, value2, "keydisableddate");
            return (Criteria) this;
        }

        public Criteria andKeyenableddateIsNull() {
            addCriterion("KEYENABLEDDATE is null");
            return (Criteria) this;
        }

        public Criteria andKeyenableddateIsNotNull() {
            addCriterion("KEYENABLEDDATE is not null");
            return (Criteria) this;
        }

        public Criteria andKeyenableddateEqualTo(Date value) {
            addCriterionForJDBCDate("KEYENABLEDDATE =", value, "keyenableddate");
            return (Criteria) this;
        }

        public Criteria andKeyenableddateNotEqualTo(Date value) {
            addCriterionForJDBCDate("KEYENABLEDDATE <>", value, "keyenableddate");
            return (Criteria) this;
        }

        public Criteria andKeyenableddateGreaterThan(Date value) {
            addCriterionForJDBCDate("KEYENABLEDDATE >", value, "keyenableddate");
            return (Criteria) this;
        }

        public Criteria andKeyenableddateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("KEYENABLEDDATE >=", value, "keyenableddate");
            return (Criteria) this;
        }

        public Criteria andKeyenableddateLessThan(Date value) {
            addCriterionForJDBCDate("KEYENABLEDDATE <", value, "keyenableddate");
            return (Criteria) this;
        }

        public Criteria andKeyenableddateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("KEYENABLEDDATE <=", value, "keyenableddate");
            return (Criteria) this;
        }

        public Criteria andKeyenableddateIn(List<Date> values) {
            addCriterionForJDBCDate("KEYENABLEDDATE in", values, "keyenableddate");
            return (Criteria) this;
        }

        public Criteria andKeyenableddateNotIn(List<Date> values) {
            addCriterionForJDBCDate("KEYENABLEDDATE not in", values, "keyenableddate");
            return (Criteria) this;
        }

        public Criteria andKeyenableddateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("KEYENABLEDDATE between", value1, value2, "keyenableddate");
            return (Criteria) this;
        }

        public Criteria andKeyenableddateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("KEYENABLEDDATE not between", value1, value2, "keyenableddate");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table KEYINFO
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}