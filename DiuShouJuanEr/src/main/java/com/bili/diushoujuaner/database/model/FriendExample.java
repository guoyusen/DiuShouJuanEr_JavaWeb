package com.bili.diushoujuaner.database.model;

import java.util.ArrayList;
import java.util.List;

public class FriendExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FriendExample() {
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

        public Criteria andFriendNoIsNull() {
            addCriterion("FriendNo is null");
            return (Criteria) this;
        }

        public Criteria andFriendNoIsNotNull() {
            addCriterion("FriendNo is not null");
            return (Criteria) this;
        }

        public Criteria andFriendNoEqualTo(Long value) {
            addCriterion("FriendNo =", value, "friendNo");
            return (Criteria) this;
        }

        public Criteria andFriendNoNotEqualTo(Long value) {
            addCriterion("FriendNo <>", value, "friendNo");
            return (Criteria) this;
        }

        public Criteria andFriendNoGreaterThan(Long value) {
            addCriterion("FriendNo >", value, "friendNo");
            return (Criteria) this;
        }

        public Criteria andFriendNoGreaterThanOrEqualTo(Long value) {
            addCriterion("FriendNo >=", value, "friendNo");
            return (Criteria) this;
        }

        public Criteria andFriendNoLessThan(Long value) {
            addCriterion("FriendNo <", value, "friendNo");
            return (Criteria) this;
        }

        public Criteria andFriendNoLessThanOrEqualTo(Long value) {
            addCriterion("FriendNo <=", value, "friendNo");
            return (Criteria) this;
        }

        public Criteria andFriendNoIn(List<Long> values) {
            addCriterion("FriendNo in", values, "friendNo");
            return (Criteria) this;
        }

        public Criteria andFriendNoNotIn(List<Long> values) {
            addCriterion("FriendNo not in", values, "friendNo");
            return (Criteria) this;
        }

        public Criteria andFriendNoBetween(Long value1, Long value2) {
            addCriterion("FriendNo between", value1, value2, "friendNo");
            return (Criteria) this;
        }

        public Criteria andFriendNoNotBetween(Long value1, Long value2) {
            addCriterion("FriendNo not between", value1, value2, "friendNo");
            return (Criteria) this;
        }

        public Criteria andUserANoIsNull() {
            addCriterion("UserANo is null");
            return (Criteria) this;
        }

        public Criteria andUserANoIsNotNull() {
            addCriterion("UserANo is not null");
            return (Criteria) this;
        }

        public Criteria andUserANoEqualTo(Long value) {
            addCriterion("UserANo =", value, "userANo");
            return (Criteria) this;
        }

        public Criteria andUserANoNotEqualTo(Long value) {
            addCriterion("UserANo <>", value, "userANo");
            return (Criteria) this;
        }

        public Criteria andUserANoGreaterThan(Long value) {
            addCriterion("UserANo >", value, "userANo");
            return (Criteria) this;
        }

        public Criteria andUserANoGreaterThanOrEqualTo(Long value) {
            addCriterion("UserANo >=", value, "userANo");
            return (Criteria) this;
        }

        public Criteria andUserANoLessThan(Long value) {
            addCriterion("UserANo <", value, "userANo");
            return (Criteria) this;
        }

        public Criteria andUserANoLessThanOrEqualTo(Long value) {
            addCriterion("UserANo <=", value, "userANo");
            return (Criteria) this;
        }

        public Criteria andUserANoIn(List<Long> values) {
            addCriterion("UserANo in", values, "userANo");
            return (Criteria) this;
        }

        public Criteria andUserANoNotIn(List<Long> values) {
            addCriterion("UserANo not in", values, "userANo");
            return (Criteria) this;
        }

        public Criteria andUserANoBetween(Long value1, Long value2) {
            addCriterion("UserANo between", value1, value2, "userANo");
            return (Criteria) this;
        }

        public Criteria andUserANoNotBetween(Long value1, Long value2) {
            addCriterion("UserANo not between", value1, value2, "userANo");
            return (Criteria) this;
        }

        public Criteria andUserBNoIsNull() {
            addCriterion("UserBNo is null");
            return (Criteria) this;
        }

        public Criteria andUserBNoIsNotNull() {
            addCriterion("UserBNo is not null");
            return (Criteria) this;
        }

        public Criteria andUserBNoEqualTo(Long value) {
            addCriterion("UserBNo =", value, "userBNo");
            return (Criteria) this;
        }

        public Criteria andUserBNoNotEqualTo(Long value) {
            addCriterion("UserBNo <>", value, "userBNo");
            return (Criteria) this;
        }

        public Criteria andUserBNoGreaterThan(Long value) {
            addCriterion("UserBNo >", value, "userBNo");
            return (Criteria) this;
        }

        public Criteria andUserBNoGreaterThanOrEqualTo(Long value) {
            addCriterion("UserBNo >=", value, "userBNo");
            return (Criteria) this;
        }

        public Criteria andUserBNoLessThan(Long value) {
            addCriterion("UserBNo <", value, "userBNo");
            return (Criteria) this;
        }

        public Criteria andUserBNoLessThanOrEqualTo(Long value) {
            addCriterion("UserBNo <=", value, "userBNo");
            return (Criteria) this;
        }

        public Criteria andUserBNoIn(List<Long> values) {
            addCriterion("UserBNo in", values, "userBNo");
            return (Criteria) this;
        }

        public Criteria andUserBNoNotIn(List<Long> values) {
            addCriterion("UserBNo not in", values, "userBNo");
            return (Criteria) this;
        }

        public Criteria andUserBNoBetween(Long value1, Long value2) {
            addCriterion("UserBNo between", value1, value2, "userBNo");
            return (Criteria) this;
        }

        public Criteria andUserBNoNotBetween(Long value1, Long value2) {
            addCriterion("UserBNo not between", value1, value2, "userBNo");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("Remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("Remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("Remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("Remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("Remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("Remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("Remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("Remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("Remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("Remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("Remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("Remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("Remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("Remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("StartTime is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("StartTime is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(String value) {
            addCriterion("StartTime =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(String value) {
            addCriterion("StartTime <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(String value) {
            addCriterion("StartTime >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("StartTime >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(String value) {
            addCriterion("StartTime <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(String value) {
            addCriterion("StartTime <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLike(String value) {
            addCriterion("StartTime like", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotLike(String value) {
            addCriterion("StartTime not like", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<String> values) {
            addCriterion("StartTime in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<String> values) {
            addCriterion("StartTime not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(String value1, String value2) {
            addCriterion("StartTime between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(String value1, String value2) {
            addCriterion("StartTime not between", value1, value2, "startTime");
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