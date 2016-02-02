package com.bili.diushoujuaner.database.model;

import java.util.ArrayList;
import java.util.List;

public class CommentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CommentExample() {
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

        public Criteria andCommentNoIsNull() {
            addCriterion("CommentNo is null");
            return (Criteria) this;
        }

        public Criteria andCommentNoIsNotNull() {
            addCriterion("CommentNo is not null");
            return (Criteria) this;
        }

        public Criteria andCommentNoEqualTo(Long value) {
            addCriterion("CommentNo =", value, "commentNo");
            return (Criteria) this;
        }

        public Criteria andCommentNoNotEqualTo(Long value) {
            addCriterion("CommentNo <>", value, "commentNo");
            return (Criteria) this;
        }

        public Criteria andCommentNoGreaterThan(Long value) {
            addCriterion("CommentNo >", value, "commentNo");
            return (Criteria) this;
        }

        public Criteria andCommentNoGreaterThanOrEqualTo(Long value) {
            addCriterion("CommentNo >=", value, "commentNo");
            return (Criteria) this;
        }

        public Criteria andCommentNoLessThan(Long value) {
            addCriterion("CommentNo <", value, "commentNo");
            return (Criteria) this;
        }

        public Criteria andCommentNoLessThanOrEqualTo(Long value) {
            addCriterion("CommentNo <=", value, "commentNo");
            return (Criteria) this;
        }

        public Criteria andCommentNoIn(List<Long> values) {
            addCriterion("CommentNo in", values, "commentNo");
            return (Criteria) this;
        }

        public Criteria andCommentNoNotIn(List<Long> values) {
            addCriterion("CommentNo not in", values, "commentNo");
            return (Criteria) this;
        }

        public Criteria andCommentNoBetween(Long value1, Long value2) {
            addCriterion("CommentNo between", value1, value2, "commentNo");
            return (Criteria) this;
        }

        public Criteria andCommentNoNotBetween(Long value1, Long value2) {
            addCriterion("CommentNo not between", value1, value2, "commentNo");
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

        public Criteria andContentIsNull() {
            addCriterion("Content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("Content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("Content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("Content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("Content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("Content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("Content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("Content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("Content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("Content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("Content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("Content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("Content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("Content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("AddTime is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("AddTime is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(String value) {
            addCriterion("AddTime =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(String value) {
            addCriterion("AddTime <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(String value) {
            addCriterion("AddTime >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(String value) {
            addCriterion("AddTime >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(String value) {
            addCriterion("AddTime <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(String value) {
            addCriterion("AddTime <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLike(String value) {
            addCriterion("AddTime like", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotLike(String value) {
            addCriterion("AddTime not like", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<String> values) {
            addCriterion("AddTime in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<String> values) {
            addCriterion("AddTime not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(String value1, String value2) {
            addCriterion("AddTime between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(String value1, String value2) {
            addCriterion("AddTime not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andFromNoIsNull() {
            addCriterion("FromNo is null");
            return (Criteria) this;
        }

        public Criteria andFromNoIsNotNull() {
            addCriterion("FromNo is not null");
            return (Criteria) this;
        }

        public Criteria andFromNoEqualTo(Long value) {
            addCriterion("FromNo =", value, "fromNo");
            return (Criteria) this;
        }

        public Criteria andFromNoNotEqualTo(Long value) {
            addCriterion("FromNo <>", value, "fromNo");
            return (Criteria) this;
        }

        public Criteria andFromNoGreaterThan(Long value) {
            addCriterion("FromNo >", value, "fromNo");
            return (Criteria) this;
        }

        public Criteria andFromNoGreaterThanOrEqualTo(Long value) {
            addCriterion("FromNo >=", value, "fromNo");
            return (Criteria) this;
        }

        public Criteria andFromNoLessThan(Long value) {
            addCriterion("FromNo <", value, "fromNo");
            return (Criteria) this;
        }

        public Criteria andFromNoLessThanOrEqualTo(Long value) {
            addCriterion("FromNo <=", value, "fromNo");
            return (Criteria) this;
        }

        public Criteria andFromNoIn(List<Long> values) {
            addCriterion("FromNo in", values, "fromNo");
            return (Criteria) this;
        }

        public Criteria andFromNoNotIn(List<Long> values) {
            addCriterion("FromNo not in", values, "fromNo");
            return (Criteria) this;
        }

        public Criteria andFromNoBetween(Long value1, Long value2) {
            addCriterion("FromNo between", value1, value2, "fromNo");
            return (Criteria) this;
        }

        public Criteria andFromNoNotBetween(Long value1, Long value2) {
            addCriterion("FromNo not between", value1, value2, "fromNo");
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