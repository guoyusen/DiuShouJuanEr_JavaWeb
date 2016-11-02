package com.bili.diushoujuaner.database.model;

import java.util.ArrayList;
import java.util.List;

public class CommonInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CommonInfoExample() {
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

        public Criteria andCommonInfoNoIsNull() {
            addCriterion("CommonInfoNo is null");
            return (Criteria) this;
        }

        public Criteria andCommonInfoNoIsNotNull() {
            addCriterion("CommonInfoNo is not null");
            return (Criteria) this;
        }

        public Criteria andCommonInfoNoEqualTo(Long value) {
            addCriterion("CommonInfoNo =", value, "commonInfoNo");
            return (Criteria) this;
        }

        public Criteria andCommonInfoNoNotEqualTo(Long value) {
            addCriterion("CommonInfoNo <>", value, "commonInfoNo");
            return (Criteria) this;
        }

        public Criteria andCommonInfoNoGreaterThan(Long value) {
            addCriterion("CommonInfoNo >", value, "commonInfoNo");
            return (Criteria) this;
        }

        public Criteria andCommonInfoNoGreaterThanOrEqualTo(Long value) {
            addCriterion("CommonInfoNo >=", value, "commonInfoNo");
            return (Criteria) this;
        }

        public Criteria andCommonInfoNoLessThan(Long value) {
            addCriterion("CommonInfoNo <", value, "commonInfoNo");
            return (Criteria) this;
        }

        public Criteria andCommonInfoNoLessThanOrEqualTo(Long value) {
            addCriterion("CommonInfoNo <=", value, "commonInfoNo");
            return (Criteria) this;
        }

        public Criteria andCommonInfoNoIn(List<Long> values) {
            addCriterion("CommonInfoNo in", values, "commonInfoNo");
            return (Criteria) this;
        }

        public Criteria andCommonInfoNoNotIn(List<Long> values) {
            addCriterion("CommonInfoNo not in", values, "commonInfoNo");
            return (Criteria) this;
        }

        public Criteria andCommonInfoNoBetween(Long value1, Long value2) {
            addCriterion("CommonInfoNo between", value1, value2, "commonInfoNo");
            return (Criteria) this;
        }

        public Criteria andCommonInfoNoNotBetween(Long value1, Long value2) {
            addCriterion("CommonInfoNo not between", value1, value2, "commonInfoNo");
            return (Criteria) this;
        }

        public Criteria andOffMsgNoIsNull() {
            addCriterion("OffMsgNo is null");
            return (Criteria) this;
        }

        public Criteria andOffMsgNoIsNotNull() {
            addCriterion("OffMsgNo is not null");
            return (Criteria) this;
        }

        public Criteria andOffMsgNoEqualTo(Long value) {
            addCriterion("OffMsgNo =", value, "offMsgNo");
            return (Criteria) this;
        }

        public Criteria andOffMsgNoNotEqualTo(Long value) {
            addCriterion("OffMsgNo <>", value, "offMsgNo");
            return (Criteria) this;
        }

        public Criteria andOffMsgNoGreaterThan(Long value) {
            addCriterion("OffMsgNo >", value, "offMsgNo");
            return (Criteria) this;
        }

        public Criteria andOffMsgNoGreaterThanOrEqualTo(Long value) {
            addCriterion("OffMsgNo >=", value, "offMsgNo");
            return (Criteria) this;
        }

        public Criteria andOffMsgNoLessThan(Long value) {
            addCriterion("OffMsgNo <", value, "offMsgNo");
            return (Criteria) this;
        }

        public Criteria andOffMsgNoLessThanOrEqualTo(Long value) {
            addCriterion("OffMsgNo <=", value, "offMsgNo");
            return (Criteria) this;
        }

        public Criteria andOffMsgNoIn(List<Long> values) {
            addCriterion("OffMsgNo in", values, "offMsgNo");
            return (Criteria) this;
        }

        public Criteria andOffMsgNoNotIn(List<Long> values) {
            addCriterion("OffMsgNo not in", values, "offMsgNo");
            return (Criteria) this;
        }

        public Criteria andOffMsgNoBetween(Long value1, Long value2) {
            addCriterion("OffMsgNo between", value1, value2, "offMsgNo");
            return (Criteria) this;
        }

        public Criteria andOffMsgNoNotBetween(Long value1, Long value2) {
            addCriterion("OffMsgNo not between", value1, value2, "offMsgNo");
            return (Criteria) this;
        }

        public Criteria andToNoIsNull() {
            addCriterion("ToNo is null");
            return (Criteria) this;
        }

        public Criteria andToNoIsNotNull() {
            addCriterion("ToNo is not null");
            return (Criteria) this;
        }

        public Criteria andToNoEqualTo(Long value) {
            addCriterion("ToNo =", value, "toNo");
            return (Criteria) this;
        }

        public Criteria andToNoNotEqualTo(Long value) {
            addCriterion("ToNo <>", value, "toNo");
            return (Criteria) this;
        }

        public Criteria andToNoGreaterThan(Long value) {
            addCriterion("ToNo >", value, "toNo");
            return (Criteria) this;
        }

        public Criteria andToNoGreaterThanOrEqualTo(Long value) {
            addCriterion("ToNo >=", value, "toNo");
            return (Criteria) this;
        }

        public Criteria andToNoLessThan(Long value) {
            addCriterion("ToNo <", value, "toNo");
            return (Criteria) this;
        }

        public Criteria andToNoLessThanOrEqualTo(Long value) {
            addCriterion("ToNo <=", value, "toNo");
            return (Criteria) this;
        }

        public Criteria andToNoIn(List<Long> values) {
            addCriterion("ToNo in", values, "toNo");
            return (Criteria) this;
        }

        public Criteria andToNoNotIn(List<Long> values) {
            addCriterion("ToNo not in", values, "toNo");
            return (Criteria) this;
        }

        public Criteria andToNoBetween(Long value1, Long value2) {
            addCriterion("ToNo between", value1, value2, "toNo");
            return (Criteria) this;
        }

        public Criteria andToNoNotBetween(Long value1, Long value2) {
            addCriterion("ToNo not between", value1, value2, "toNo");
            return (Criteria) this;
        }

        public Criteria andIsReadIsNull() {
            addCriterion("IsRead is null");
            return (Criteria) this;
        }

        public Criteria andIsReadIsNotNull() {
            addCriterion("IsRead is not null");
            return (Criteria) this;
        }

        public Criteria andIsReadEqualTo(Boolean value) {
            addCriterion("IsRead =", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadNotEqualTo(Boolean value) {
            addCriterion("IsRead <>", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadGreaterThan(Boolean value) {
            addCriterion("IsRead >", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IsRead >=", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadLessThan(Boolean value) {
            addCriterion("IsRead <", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadLessThanOrEqualTo(Boolean value) {
            addCriterion("IsRead <=", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadIn(List<Boolean> values) {
            addCriterion("IsRead in", values, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadNotIn(List<Boolean> values) {
            addCriterion("IsRead not in", values, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadBetween(Boolean value1, Boolean value2) {
            addCriterion("IsRead between", value1, value2, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IsRead not between", value1, value2, "isRead");
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