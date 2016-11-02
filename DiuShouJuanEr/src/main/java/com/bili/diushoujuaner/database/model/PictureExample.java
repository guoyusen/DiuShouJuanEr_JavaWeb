package com.bili.diushoujuaner.database.model;

import java.util.ArrayList;
import java.util.List;

public class PictureExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PictureExample() {
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

        public Criteria andPictureNoIsNull() {
            addCriterion("PictureNo is null");
            return (Criteria) this;
        }

        public Criteria andPictureNoIsNotNull() {
            addCriterion("PictureNo is not null");
            return (Criteria) this;
        }

        public Criteria andPictureNoEqualTo(Long value) {
            addCriterion("PictureNo =", value, "pictureNo");
            return (Criteria) this;
        }

        public Criteria andPictureNoNotEqualTo(Long value) {
            addCriterion("PictureNo <>", value, "pictureNo");
            return (Criteria) this;
        }

        public Criteria andPictureNoGreaterThan(Long value) {
            addCriterion("PictureNo >", value, "pictureNo");
            return (Criteria) this;
        }

        public Criteria andPictureNoGreaterThanOrEqualTo(Long value) {
            addCriterion("PictureNo >=", value, "pictureNo");
            return (Criteria) this;
        }

        public Criteria andPictureNoLessThan(Long value) {
            addCriterion("PictureNo <", value, "pictureNo");
            return (Criteria) this;
        }

        public Criteria andPictureNoLessThanOrEqualTo(Long value) {
            addCriterion("PictureNo <=", value, "pictureNo");
            return (Criteria) this;
        }

        public Criteria andPictureNoIn(List<Long> values) {
            addCriterion("PictureNo in", values, "pictureNo");
            return (Criteria) this;
        }

        public Criteria andPictureNoNotIn(List<Long> values) {
            addCriterion("PictureNo not in", values, "pictureNo");
            return (Criteria) this;
        }

        public Criteria andPictureNoBetween(Long value1, Long value2) {
            addCriterion("PictureNo between", value1, value2, "pictureNo");
            return (Criteria) this;
        }

        public Criteria andPictureNoNotBetween(Long value1, Long value2) {
            addCriterion("PictureNo not between", value1, value2, "pictureNo");
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

        public Criteria andPicPathIsNull() {
            addCriterion("PicPath is null");
            return (Criteria) this;
        }

        public Criteria andPicPathIsNotNull() {
            addCriterion("PicPath is not null");
            return (Criteria) this;
        }

        public Criteria andPicPathEqualTo(String value) {
            addCriterion("PicPath =", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathNotEqualTo(String value) {
            addCriterion("PicPath <>", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathGreaterThan(String value) {
            addCriterion("PicPath >", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathGreaterThanOrEqualTo(String value) {
            addCriterion("PicPath >=", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathLessThan(String value) {
            addCriterion("PicPath <", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathLessThanOrEqualTo(String value) {
            addCriterion("PicPath <=", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathLike(String value) {
            addCriterion("PicPath like", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathNotLike(String value) {
            addCriterion("PicPath not like", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathIn(List<String> values) {
            addCriterion("PicPath in", values, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathNotIn(List<String> values) {
            addCriterion("PicPath not in", values, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathBetween(String value1, String value2) {
            addCriterion("PicPath between", value1, value2, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathNotBetween(String value1, String value2) {
            addCriterion("PicPath not between", value1, value2, "picPath");
            return (Criteria) this;
        }

        public Criteria andHeightIsNull() {
            addCriterion("Height is null");
            return (Criteria) this;
        }

        public Criteria andHeightIsNotNull() {
            addCriterion("Height is not null");
            return (Criteria) this;
        }

        public Criteria andHeightEqualTo(Integer value) {
            addCriterion("Height =", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotEqualTo(Integer value) {
            addCriterion("Height <>", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightGreaterThan(Integer value) {
            addCriterion("Height >", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("Height >=", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLessThan(Integer value) {
            addCriterion("Height <", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLessThanOrEqualTo(Integer value) {
            addCriterion("Height <=", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightIn(List<Integer> values) {
            addCriterion("Height in", values, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotIn(List<Integer> values) {
            addCriterion("Height not in", values, "height");
            return (Criteria) this;
        }

        public Criteria andHeightBetween(Integer value1, Integer value2) {
            addCriterion("Height between", value1, value2, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotBetween(Integer value1, Integer value2) {
            addCriterion("Height not between", value1, value2, "height");
            return (Criteria) this;
        }

        public Criteria andWidthIsNull() {
            addCriterion("Width is null");
            return (Criteria) this;
        }

        public Criteria andWidthIsNotNull() {
            addCriterion("Width is not null");
            return (Criteria) this;
        }

        public Criteria andWidthEqualTo(Integer value) {
            addCriterion("Width =", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotEqualTo(Integer value) {
            addCriterion("Width <>", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthGreaterThan(Integer value) {
            addCriterion("Width >", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthGreaterThanOrEqualTo(Integer value) {
            addCriterion("Width >=", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLessThan(Integer value) {
            addCriterion("Width <", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLessThanOrEqualTo(Integer value) {
            addCriterion("Width <=", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthIn(List<Integer> values) {
            addCriterion("Width in", values, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotIn(List<Integer> values) {
            addCriterion("Width not in", values, "width");
            return (Criteria) this;
        }

        public Criteria andWidthBetween(Integer value1, Integer value2) {
            addCriterion("Width between", value1, value2, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotBetween(Integer value1, Integer value2) {
            addCriterion("Width not between", value1, value2, "width");
            return (Criteria) this;
        }

        public Criteria andOffSetTopIsNull() {
            addCriterion("offSetTop is null");
            return (Criteria) this;
        }

        public Criteria andOffSetTopIsNotNull() {
            addCriterion("offSetTop is not null");
            return (Criteria) this;
        }

        public Criteria andOffSetTopEqualTo(Integer value) {
            addCriterion("offSetTop =", value, "offSetTop");
            return (Criteria) this;
        }

        public Criteria andOffSetTopNotEqualTo(Integer value) {
            addCriterion("offSetTop <>", value, "offSetTop");
            return (Criteria) this;
        }

        public Criteria andOffSetTopGreaterThan(Integer value) {
            addCriterion("offSetTop >", value, "offSetTop");
            return (Criteria) this;
        }

        public Criteria andOffSetTopGreaterThanOrEqualTo(Integer value) {
            addCriterion("offSetTop >=", value, "offSetTop");
            return (Criteria) this;
        }

        public Criteria andOffSetTopLessThan(Integer value) {
            addCriterion("offSetTop <", value, "offSetTop");
            return (Criteria) this;
        }

        public Criteria andOffSetTopLessThanOrEqualTo(Integer value) {
            addCriterion("offSetTop <=", value, "offSetTop");
            return (Criteria) this;
        }

        public Criteria andOffSetTopIn(List<Integer> values) {
            addCriterion("offSetTop in", values, "offSetTop");
            return (Criteria) this;
        }

        public Criteria andOffSetTopNotIn(List<Integer> values) {
            addCriterion("offSetTop not in", values, "offSetTop");
            return (Criteria) this;
        }

        public Criteria andOffSetTopBetween(Integer value1, Integer value2) {
            addCriterion("offSetTop between", value1, value2, "offSetTop");
            return (Criteria) this;
        }

        public Criteria andOffSetTopNotBetween(Integer value1, Integer value2) {
            addCriterion("offSetTop not between", value1, value2, "offSetTop");
            return (Criteria) this;
        }

        public Criteria andOffSetLeftIsNull() {
            addCriterion("offSetLeft is null");
            return (Criteria) this;
        }

        public Criteria andOffSetLeftIsNotNull() {
            addCriterion("offSetLeft is not null");
            return (Criteria) this;
        }

        public Criteria andOffSetLeftEqualTo(Integer value) {
            addCriterion("offSetLeft =", value, "offSetLeft");
            return (Criteria) this;
        }

        public Criteria andOffSetLeftNotEqualTo(Integer value) {
            addCriterion("offSetLeft <>", value, "offSetLeft");
            return (Criteria) this;
        }

        public Criteria andOffSetLeftGreaterThan(Integer value) {
            addCriterion("offSetLeft >", value, "offSetLeft");
            return (Criteria) this;
        }

        public Criteria andOffSetLeftGreaterThanOrEqualTo(Integer value) {
            addCriterion("offSetLeft >=", value, "offSetLeft");
            return (Criteria) this;
        }

        public Criteria andOffSetLeftLessThan(Integer value) {
            addCriterion("offSetLeft <", value, "offSetLeft");
            return (Criteria) this;
        }

        public Criteria andOffSetLeftLessThanOrEqualTo(Integer value) {
            addCriterion("offSetLeft <=", value, "offSetLeft");
            return (Criteria) this;
        }

        public Criteria andOffSetLeftIn(List<Integer> values) {
            addCriterion("offSetLeft in", values, "offSetLeft");
            return (Criteria) this;
        }

        public Criteria andOffSetLeftNotIn(List<Integer> values) {
            addCriterion("offSetLeft not in", values, "offSetLeft");
            return (Criteria) this;
        }

        public Criteria andOffSetLeftBetween(Integer value1, Integer value2) {
            addCriterion("offSetLeft between", value1, value2, "offSetLeft");
            return (Criteria) this;
        }

        public Criteria andOffSetLeftNotBetween(Integer value1, Integer value2) {
            addCriterion("offSetLeft not between", value1, value2, "offSetLeft");
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