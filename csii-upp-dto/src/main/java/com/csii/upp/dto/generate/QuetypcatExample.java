package com.csii.upp.dto.generate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuetypcatExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table QUETYPCAT
	 * @mbggenerated
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table QUETYPCAT
	 * @mbggenerated
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table QUETYPCAT
	 * @mbggenerated
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUETYPCAT
	 * @mbggenerated
	 */
	public QuetypcatExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUETYPCAT
	 * @mbggenerated
	 */
	protected QuetypcatExample(QuetypcatExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUETYPCAT
	 * @mbggenerated
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUETYPCAT
	 * @mbggenerated
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUETYPCAT
	 * @mbggenerated
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUETYPCAT
	 * @mbggenerated
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUETYPCAT
	 * @mbggenerated
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUETYPCAT
	 * @mbggenerated
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUETYPCAT
	 * @mbggenerated
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUETYPCAT
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
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUETYPCAT
	 * @mbggenerated
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table QUETYPCAT
	 * @mbggenerated
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table QUETYPCAT
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

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("condition", condition);
			map.put("value", value);
			criteriaWithSingleValue.add(map);
		}

		protected void addCriterion(String condition,
				List<? extends Object> values, String property) {
			if (values == null || values.size() == 0) {
				throw new RuntimeException("Value list for " + property
						+ " cannot be null or empty");
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("condition", condition);
			map.put("values", values);
			criteriaWithListValue.add(map);
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			List<Object> list = new ArrayList<Object>();
			list.add(value1);
			list.add(value2);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("condition", condition);
			map.put("values", list);
			criteriaWithBetweenValue.add(map);
		}

		public Criteria andQuetypcatcdIsNull() {
			addCriterion("QUETYPCATCD is null");
			return (Criteria) this;
		}

		public Criteria andQuetypcatcdIsNotNull() {
			addCriterion("QUETYPCATCD is not null");
			return (Criteria) this;
		}

		public Criteria andQuetypcatcdEqualTo(String value) {
			addCriterion("QUETYPCATCD =", value, "quetypcatcd");
			return (Criteria) this;
		}

		public Criteria andQuetypcatcdNotEqualTo(String value) {
			addCriterion("QUETYPCATCD <>", value, "quetypcatcd");
			return (Criteria) this;
		}

		public Criteria andQuetypcatcdGreaterThan(String value) {
			addCriterion("QUETYPCATCD >", value, "quetypcatcd");
			return (Criteria) this;
		}

		public Criteria andQuetypcatcdGreaterThanOrEqualTo(String value) {
			addCriterion("QUETYPCATCD >=", value, "quetypcatcd");
			return (Criteria) this;
		}

		public Criteria andQuetypcatcdLessThan(String value) {
			addCriterion("QUETYPCATCD <", value, "quetypcatcd");
			return (Criteria) this;
		}

		public Criteria andQuetypcatcdLessThanOrEqualTo(String value) {
			addCriterion("QUETYPCATCD <=", value, "quetypcatcd");
			return (Criteria) this;
		}

		public Criteria andQuetypcatcdLike(String value) {
			addCriterion("QUETYPCATCD like", value, "quetypcatcd");
			return (Criteria) this;
		}

		public Criteria andQuetypcatcdNotLike(String value) {
			addCriterion("QUETYPCATCD not like", value, "quetypcatcd");
			return (Criteria) this;
		}

		public Criteria andQuetypcatcdIn(List<String> values) {
			addCriterion("QUETYPCATCD in", values, "quetypcatcd");
			return (Criteria) this;
		}

		public Criteria andQuetypcatcdNotIn(List<String> values) {
			addCriterion("QUETYPCATCD not in", values, "quetypcatcd");
			return (Criteria) this;
		}

		public Criteria andQuetypcatcdBetween(String value1, String value2) {
			addCriterion("QUETYPCATCD between", value1, value2, "quetypcatcd");
			return (Criteria) this;
		}

		public Criteria andQuetypcatcdNotBetween(String value1, String value2) {
			addCriterion("QUETYPCATCD not between", value1, value2,
					"quetypcatcd");
			return (Criteria) this;
		}

		public Criteria andQuetypcatdescIsNull() {
			addCriterion("QUETYPCATDESC is null");
			return (Criteria) this;
		}

		public Criteria andQuetypcatdescIsNotNull() {
			addCriterion("QUETYPCATDESC is not null");
			return (Criteria) this;
		}

		public Criteria andQuetypcatdescEqualTo(String value) {
			addCriterion("QUETYPCATDESC =", value, "quetypcatdesc");
			return (Criteria) this;
		}

		public Criteria andQuetypcatdescNotEqualTo(String value) {
			addCriterion("QUETYPCATDESC <>", value, "quetypcatdesc");
			return (Criteria) this;
		}

		public Criteria andQuetypcatdescGreaterThan(String value) {
			addCriterion("QUETYPCATDESC >", value, "quetypcatdesc");
			return (Criteria) this;
		}

		public Criteria andQuetypcatdescGreaterThanOrEqualTo(String value) {
			addCriterion("QUETYPCATDESC >=", value, "quetypcatdesc");
			return (Criteria) this;
		}

		public Criteria andQuetypcatdescLessThan(String value) {
			addCriterion("QUETYPCATDESC <", value, "quetypcatdesc");
			return (Criteria) this;
		}

		public Criteria andQuetypcatdescLessThanOrEqualTo(String value) {
			addCriterion("QUETYPCATDESC <=", value, "quetypcatdesc");
			return (Criteria) this;
		}

		public Criteria andQuetypcatdescLike(String value) {
			addCriterion("QUETYPCATDESC like", value, "quetypcatdesc");
			return (Criteria) this;
		}

		public Criteria andQuetypcatdescNotLike(String value) {
			addCriterion("QUETYPCATDESC not like", value, "quetypcatdesc");
			return (Criteria) this;
		}

		public Criteria andQuetypcatdescIn(List<String> values) {
			addCriterion("QUETYPCATDESC in", values, "quetypcatdesc");
			return (Criteria) this;
		}

		public Criteria andQuetypcatdescNotIn(List<String> values) {
			addCriterion("QUETYPCATDESC not in", values, "quetypcatdesc");
			return (Criteria) this;
		}

		public Criteria andQuetypcatdescBetween(String value1, String value2) {
			addCriterion("QUETYPCATDESC between", value1, value2,
					"quetypcatdesc");
			return (Criteria) this;
		}

		public Criteria andQuetypcatdescNotBetween(String value1, String value2) {
			addCriterion("QUETYPCATDESC not between", value1, value2,
					"quetypcatdesc");
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
			addCriterion("DATELASTMAINT between", value1, value2,
					"datelastmaint");
			return (Criteria) this;
		}

		public Criteria andDatelastmaintNotBetween(Date value1, Date value2) {
			addCriterion("DATELASTMAINT not between", value1, value2,
					"datelastmaint");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table QUETYPCAT
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}