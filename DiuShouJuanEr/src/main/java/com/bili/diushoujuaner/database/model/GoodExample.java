package com.bili.diushoujuaner.database.model;

import java.util.ArrayList;
import java.util.List;

public class GoodExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GoodExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andGoodNoIsNull() {
            addCriterion("GoodNo is null");
            return (Criteria) this;
        }

        public Criteria andGoodNoIsNotNull() {
            addCriterion("GoodNo is not null");
            return (Criteria) this;
        }

        public Criteria andGoodNoEqualTo(Long value) {
            addCriterion("GoodNo =", value, "goodNo");
            return (Criteria) this;
        }

        public Criteria andGoodNoNotEqualTo(Long value) {
            addCriterion("GoodNo <>", value, "goodNo");
            return (Criteria) this;
        }

        public Criteria andGoodNoGreaterThan(Long value) {
            addCriterion("GoodNo >", value, "goodNo");
            return (Criteria) this;
        }

        public Criteria andGoodNoGreaterThanOrEqualTo(Long value) {
            addCriterion("GoodNo >=", value, "goodNo");
            return (Criteria) this;
        }

        public Criteria andGoodNoLessThan(Long value) {
            addCriterion("GoodNo <", value, "goodNo");
            return (Criteria) this;
        }

        public Criteria andGoodNoLessThanOrEqualTo(Long value) {
            addCriterion("GoodNo <=", value, "goodNo");
            return (Criteria) this;
        }

        public Criteria andGoodNoIn(List<Long> values) {
            addCriterion("GoodNo in", values, "goodNo");
            return (Criteria) this;
        }

        public Criteria andGoodNoNotIn(List<Long> values) {
            addCriterion("GoodNo not in", values, "goodNo");
            return (Criteria) this;
        }

        public Criteria andGoodNoBetween(Long value1, Long value2) {
            addCriterion("GoodNo between", value1, value2, "goodNo");
            return (Criteria) this;
        }

        public Criteria andGoodNoNotBetween(Long value1, Long value2) {
            addCriterion("GoodNo not between", value1, value2, "goodNo");
            return (Criteria) this;
        }

        public Criteria andRecallNoIsNull() {
            addCriterion("RecallNo is null");
            return (Criteria) this;
        }

        public Criteria andRecallNoIsNotNull() {
            addCriterion("RecallNo is not null");
            return (Criteria) this;
        }

        public Criteria andRecallNoEqualTo(Long value) {
            addCriterion("RecallNo =", value, "recallNo");
            return (Criteria) this;
        }

        public Criteria andRecallNoNotEqualTo(Long value) {
            addCriterion("RecallNo <>", value, "recallNo");
            return (Criteria) this;
        }

        public Criteria andRecallNoGreaterThan(Long value) {
            addCriterion("RecallNo >", value, "recallNo");
            return (Criteria) this;
        }

        public Criteria andRecallNoGreaterThanOrEqualTo(Long value) {
            addCriterion("RecallNo >=", value, "recallNo");
            return (Criteria) this;
        }

        public Criteria andRecallNoLessThan(Long value) {
            addCriterion("RecallNo <", value, "recallNo");
            return (Criteria) this;
        }

        public Criteria andRecallNoLessThanOrEqualTo(Long value) {
            addCriterion("RecallNo <=", value, "recallNo");
            return (Criteria) this;
        }

        public Criteria andRecallNoIn(List<Long> values) {
            addCriterion("RecallNo in", values, "recallNo");
            return (Criteria) this;
        }

        public Criteria andRecallNoNotIn(List<Long> values) {
            addCriterion("RecallNo not in", values, "recallNo");
            return (Criteria) this;
        }

        public Criteria andRecallNoBetween(Long value1, Long value2) {
            addCriterion("RecallNo between", value1, value2, "recallNo");
            return (Criteria) this;
        }

        public Criteria andRecallNoNotBetween(Long value1, Long value2) {
            addCriterion("RecallNo not between", value1, value2, "recallNo");
            return (Criteria) this;
        }

        public Criteria andUserNoIsNull() {
            addCriterion("UserNo is null");
            return (Criteria) this;
        }

        public Criteria andUserNoIsNotNull() {
            addCriterion("UserNo is not null");
            return (Criteria) this;
        }

        public Criteria andUserNoEqualTo(Long value) {
            addCriterion("UserNo =", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoNotEqualTo(Long value) {
            addCriterion("UserNo <>", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoGreaterThan(Long value) {
            addCriterion("UserNo >", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoGreaterThanOrEqualTo(Long value) {
            addCriterion("UserNo >=", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoLessThan(Long value) {
            addCriterion("UserNo <", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoLessThanOrEqualTo(Long value) {
            addCriterion("UserNo <=", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoIn(List<Long> values) {
            addCriterion("UserNo in", values, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoNotIn(List<Long> values) {
            addCriterion("UserNo not in", values, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoBetween(Long value1, Long value2) {
            addCriterion("UserNo between", value1, value2, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoNotBetween(Long value1, Long value2) {
            addCriterion("UserNo not between", value1, value2, "userNo");
            return (Criteria) this;
        }

        public Criteria andGoodTimeIsNull() {
            addCriterion("GoodTime is null");
            return (Criteria) this;
        }

        public Criteria andGoodTimeIsNotNull() {
            addCriterion("GoodTime is not null");
            return (Criteria) this;
        }

        public Criteria andGoodTimeEqualTo(String value) {
            addCriterion("GoodTime =", value, "goodTime");
            return (Criteria) this;
        }

        public Criteria andGoodTimeNotEqualTo(String value) {
            addCriterion("GoodTime <>", value, "goodTime");
            return (Criteria) this;
        }

        public Criteria andGoodTimeGreaterThan(String value) {
            addCriterion("GoodTime >", value, "goodTime");
            return (Criteria) this;
        }

        public Criteria andGoodTimeGreaterThanOrEqualTo(String value) {
            addCriterion("GoodTime >=", value, "goodTime");
            return (Criteria) this;
        }

        public Criteria andGoodTimeLessThan(String value) {
            addCriterion("GoodTime <", value, "goodTime");
            return (Criteria) this;
        }

        public Criteria andGoodTimeLessThanOrEqualTo(String value) {
            addCriterion("GoodTime <=", value, "goodTime");
            return (Criteria) this;
        }

        public Criteria andGoodTimeLike(String value) {
            addCriterion("GoodTime like", value, "goodTime");
            return (Criteria) this;
        }

        public Criteria andGoodTimeNotLike(String value) {
            addCriterion("GoodTime not like", value, "goodTime");
            return (Criteria) this;
        }

        public Criteria andGoodTimeIn(List<String> values) {
            addCriterion("GoodTime in", values, "goodTime");
            return (Criteria) this;
        }

        public Criteria andGoodTimeNotIn(List<String> values) {
            addCriterion("GoodTime not in", values, "goodTime");
            return (Criteria) this;
        }

        public Criteria andGoodTimeBetween(String value1, String value2) {
            addCriterion("GoodTime between", value1, value2, "goodTime");
            return (Criteria) this;
        }

        public Criteria andGoodTimeNotBetween(String value1, String value2) {
            addCriterion("GoodTime not between", value1, value2, "goodTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}