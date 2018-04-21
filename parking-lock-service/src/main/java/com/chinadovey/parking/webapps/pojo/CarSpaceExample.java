package com.chinadovey.parking.webapps.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarSpaceExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table parking_car_space
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table parking_car_space
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table parking_car_space
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_car_space
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public CarSpaceExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_car_space
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_car_space
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_car_space
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_car_space
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_car_space
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_car_space
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_car_space
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_car_space
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
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
     * This method corresponds to the database table parking_car_space
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parking_car_space
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table parking_car_space
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSpaceNameIsNull() {
            addCriterion("space_name is null");
            return (Criteria) this;
        }

        public Criteria andSpaceNameIsNotNull() {
            addCriterion("space_name is not null");
            return (Criteria) this;
        }

        public Criteria andSpaceNameEqualTo(String value) {
            addCriterion("space_name =", value, "spaceName");
            return (Criteria) this;
        }

        public Criteria andSpaceNameNotEqualTo(String value) {
            addCriterion("space_name <>", value, "spaceName");
            return (Criteria) this;
        }

        public Criteria andSpaceNameGreaterThan(String value) {
            addCriterion("space_name >", value, "spaceName");
            return (Criteria) this;
        }

        public Criteria andSpaceNameGreaterThanOrEqualTo(String value) {
            addCriterion("space_name >=", value, "spaceName");
            return (Criteria) this;
        }

        public Criteria andSpaceNameLessThan(String value) {
            addCriterion("space_name <", value, "spaceName");
            return (Criteria) this;
        }

        public Criteria andSpaceNameLessThanOrEqualTo(String value) {
            addCriterion("space_name <=", value, "spaceName");
            return (Criteria) this;
        }

        public Criteria andSpaceNameLike(String value) {
            addCriterion("space_name like", value, "spaceName");
            return (Criteria) this;
        }

        public Criteria andSpaceNameNotLike(String value) {
            addCriterion("space_name not like", value, "spaceName");
            return (Criteria) this;
        }

        public Criteria andSpaceNameIn(List<String> values) {
            addCriterion("space_name in", values, "spaceName");
            return (Criteria) this;
        }

        public Criteria andSpaceNameNotIn(List<String> values) {
            addCriterion("space_name not in", values, "spaceName");
            return (Criteria) this;
        }

        public Criteria andSpaceNameBetween(String value1, String value2) {
            addCriterion("space_name between", value1, value2, "spaceName");
            return (Criteria) this;
        }

        public Criteria andSpaceNameNotBetween(String value1, String value2) {
            addCriterion("space_name not between", value1, value2, "spaceName");
            return (Criteria) this;
        }

        public Criteria andSpaceNoIsNull() {
            addCriterion("space_no is null");
            return (Criteria) this;
        }

        public Criteria andSpaceNoIsNotNull() {
            addCriterion("space_no is not null");
            return (Criteria) this;
        }

        public Criteria andSpaceNoEqualTo(String value) {
            addCriterion("space_no =", value, "spaceNo");
            return (Criteria) this;
        }

        public Criteria andSpaceNoNotEqualTo(String value) {
            addCriterion("space_no <>", value, "spaceNo");
            return (Criteria) this;
        }

        public Criteria andSpaceNoGreaterThan(String value) {
            addCriterion("space_no >", value, "spaceNo");
            return (Criteria) this;
        }

        public Criteria andSpaceNoGreaterThanOrEqualTo(String value) {
            addCriterion("space_no >=", value, "spaceNo");
            return (Criteria) this;
        }

        public Criteria andSpaceNoLessThan(String value) {
            addCriterion("space_no <", value, "spaceNo");
            return (Criteria) this;
        }

        public Criteria andSpaceNoLessThanOrEqualTo(String value) {
            addCriterion("space_no <=", value, "spaceNo");
            return (Criteria) this;
        }

        public Criteria andSpaceNoLike(String value) {
            addCriterion("space_no like", value, "spaceNo");
            return (Criteria) this;
        }

        public Criteria andSpaceNoNotLike(String value) {
            addCriterion("space_no not like", value, "spaceNo");
            return (Criteria) this;
        }

        public Criteria andSpaceNoIn(List<String> values) {
            addCriterion("space_no in", values, "spaceNo");
            return (Criteria) this;
        }

        public Criteria andSpaceNoNotIn(List<String> values) {
            addCriterion("space_no not in", values, "spaceNo");
            return (Criteria) this;
        }

        public Criteria andSpaceNoBetween(String value1, String value2) {
            addCriterion("space_no between", value1, value2, "spaceNo");
            return (Criteria) this;
        }

        public Criteria andSpaceNoNotBetween(String value1, String value2) {
            addCriterion("space_no not between", value1, value2, "spaceNo");
            return (Criteria) this;
        }

        public Criteria andParkIdIsNull() {
            addCriterion("park_id is null");
            return (Criteria) this;
        }

        public Criteria andParkIdIsNotNull() {
            addCriterion("park_id is not null");
            return (Criteria) this;
        }

        public Criteria andParkIdEqualTo(Integer value) {
            addCriterion("park_id =", value, "parkId");
            return (Criteria) this;
        }

        public Criteria andParkIdNotEqualTo(Integer value) {
            addCriterion("park_id <>", value, "parkId");
            return (Criteria) this;
        }

        public Criteria andParkIdGreaterThan(Integer value) {
            addCriterion("park_id >", value, "parkId");
            return (Criteria) this;
        }

        public Criteria andParkIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("park_id >=", value, "parkId");
            return (Criteria) this;
        }

        public Criteria andParkIdLessThan(Integer value) {
            addCriterion("park_id <", value, "parkId");
            return (Criteria) this;
        }

        public Criteria andParkIdLessThanOrEqualTo(Integer value) {
            addCriterion("park_id <=", value, "parkId");
            return (Criteria) this;
        }

        public Criteria andParkIdIn(List<Integer> values) {
            addCriterion("park_id in", values, "parkId");
            return (Criteria) this;
        }

        public Criteria andParkIdNotIn(List<Integer> values) {
            addCriterion("park_id not in", values, "parkId");
            return (Criteria) this;
        }

        public Criteria andParkIdBetween(Integer value1, Integer value2) {
            addCriterion("park_id between", value1, value2, "parkId");
            return (Criteria) this;
        }

        public Criteria andParkIdNotBetween(Integer value1, Integer value2) {
            addCriterion("park_id not between", value1, value2, "parkId");
            return (Criteria) this;
        }

        public Criteria andParkingNameIsNull() {
            addCriterion("parking_name is null");
            return (Criteria) this;
        }

        public Criteria andParkingNameIsNotNull() {
            addCriterion("parking_name is not null");
            return (Criteria) this;
        }

        public Criteria andParkingNameEqualTo(String value) {
            addCriterion("parking_name =", value, "parkingName");
            return (Criteria) this;
        }

        public Criteria andParkingNameNotEqualTo(String value) {
            addCriterion("parking_name <>", value, "parkingName");
            return (Criteria) this;
        }

        public Criteria andParkingNameGreaterThan(String value) {
            addCriterion("parking_name >", value, "parkingName");
            return (Criteria) this;
        }

        public Criteria andParkingNameGreaterThanOrEqualTo(String value) {
            addCriterion("parking_name >=", value, "parkingName");
            return (Criteria) this;
        }

        public Criteria andParkingNameLessThan(String value) {
            addCriterion("parking_name <", value, "parkingName");
            return (Criteria) this;
        }

        public Criteria andParkingNameLessThanOrEqualTo(String value) {
            addCriterion("parking_name <=", value, "parkingName");
            return (Criteria) this;
        }

        public Criteria andParkingNameLike(String value) {
            addCriterion("parking_name like", value, "parkingName");
            return (Criteria) this;
        }

        public Criteria andParkingNameNotLike(String value) {
            addCriterion("parking_name not like", value, "parkingName");
            return (Criteria) this;
        }

        public Criteria andParkingNameIn(List<String> values) {
            addCriterion("parking_name in", values, "parkingName");
            return (Criteria) this;
        }

        public Criteria andParkingNameNotIn(List<String> values) {
            addCriterion("parking_name not in", values, "parkingName");
            return (Criteria) this;
        }

        public Criteria andParkingNameBetween(String value1, String value2) {
            addCriterion("parking_name between", value1, value2, "parkingName");
            return (Criteria) this;
        }

        public Criteria andParkingNameNotBetween(String value1, String value2) {
            addCriterion("parking_name not between", value1, value2, "parkingName");
            return (Criteria) this;
        }

        public Criteria andSlaveIdIsNull() {
            addCriterion("slave_id is null");
            return (Criteria) this;
        }

        public Criteria andSlaveIdIsNotNull() {
            addCriterion("slave_id is not null");
            return (Criteria) this;
        }

        public Criteria andSlaveIdEqualTo(String value) {
            addCriterion("slave_id =", value, "slaveId");
            return (Criteria) this;
        }

        public Criteria andSlaveIdNotEqualTo(String value) {
            addCriterion("slave_id <>", value, "slaveId");
            return (Criteria) this;
        }
        /**
         * li 
         * @param value
         * @return
         */
        public Criteria andSpaceNoLikeOrSlaveIdLike(String value) {
        	addCriterion("(space_no like '%"+value+"%' or slave_id like '%"+value+"%')");
        	return (Criteria) this;
        }

        public Criteria andSlaveIdGreaterThan(String value) {
            addCriterion("slave_id >", value, "slaveId");
            return (Criteria) this;
        }

        public Criteria andSlaveIdGreaterThanOrEqualTo(String value) {
            addCriterion("slave_id >=", value, "slaveId");
            return (Criteria) this;
        }

        public Criteria andSlaveIdLessThan(String value) {
            addCriterion("slave_id <", value, "slaveId");
            return (Criteria) this;
        }

        public Criteria andSlaveIdLessThanOrEqualTo(String value) {
            addCriterion("slave_id <=", value, "slaveId");
            return (Criteria) this;
        }

        public Criteria andSlaveIdLike(String value) {
            addCriterion("slave_id like", value, "slaveId");
            return (Criteria) this;
        }

        public Criteria andSlaveIdNotLike(String value) {
            addCriterion("slave_id not like", value, "slaveId");
            return (Criteria) this;
        }

        public Criteria andSlaveIdIn(List<String> values) {
            addCriterion("slave_id in", values, "slaveId");
            return (Criteria) this;
        }

        public Criteria andSlaveIdNotIn(List<String> values) {
            addCriterion("slave_id not in", values, "slaveId");
            return (Criteria) this;
        }

        public Criteria andSlaveIdBetween(String value1, String value2) {
            addCriterion("slave_id between", value1, value2, "slaveId");
            return (Criteria) this;
        }

        public Criteria andSlaveIdNotBetween(String value1, String value2) {
            addCriterion("slave_id not between", value1, value2, "slaveId");
            return (Criteria) this;
        }

        public Criteria andCarUserIdIsNull() {
            addCriterion("car_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCarUserIdIsNotNull() {
            addCriterion("car_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCarUserIdEqualTo(Integer value) {
            addCriterion("car_user_id =", value, "carUserId");
            return (Criteria) this;
        }

        public Criteria andCarUserIdNotEqualTo(Integer value) {
            addCriterion("car_user_id <>", value, "carUserId");
            return (Criteria) this;
        }

        public Criteria andCarUserIdGreaterThan(Integer value) {
            addCriterion("car_user_id >", value, "carUserId");
            return (Criteria) this;
        }

        public Criteria andCarUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("car_user_id >=", value, "carUserId");
            return (Criteria) this;
        }

        public Criteria andCarUserIdLessThan(Integer value) {
            addCriterion("car_user_id <", value, "carUserId");
            return (Criteria) this;
        }

        public Criteria andCarUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("car_user_id <=", value, "carUserId");
            return (Criteria) this;
        }

        public Criteria andCarUserIdIn(List<Integer> values) {
            addCriterion("car_user_id in", values, "carUserId");
            return (Criteria) this;
        }

        public Criteria andCarUserIdNotIn(List<Integer> values) {
            addCriterion("car_user_id not in", values, "carUserId");
            return (Criteria) this;
        }

        public Criteria andCarUserIdBetween(Integer value1, Integer value2) {
            addCriterion("car_user_id between", value1, value2, "carUserId");
            return (Criteria) this;
        }

        public Criteria andCarUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("car_user_id not between", value1, value2, "carUserId");
            return (Criteria) this;
        }

        public Criteria andIsEmptyIsNull() {
            addCriterion("is_empty is null");
            return (Criteria) this;
        }

        public Criteria andIsEmptyIsNotNull() {
            addCriterion("is_empty is not null");
            return (Criteria) this;
        }

        public Criteria andIsEmptyEqualTo(Integer value) {
            addCriterion("is_empty =", value, "isEmpty");
            return (Criteria) this;
        }

        public Criteria andIsEmptyNotEqualTo(Integer value) {
            addCriterion("is_empty <>", value, "isEmpty");
            return (Criteria) this;
        }

        public Criteria andIsEmptyGreaterThan(Integer value) {
            addCriterion("is_empty >", value, "isEmpty");
            return (Criteria) this;
        }

        public Criteria andIsEmptyGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_empty >=", value, "isEmpty");
            return (Criteria) this;
        }

        public Criteria andIsEmptyLessThan(Integer value) {
            addCriterion("is_empty <", value, "isEmpty");
            return (Criteria) this;
        }

        public Criteria andIsEmptyLessThanOrEqualTo(Integer value) {
            addCriterion("is_empty <=", value, "isEmpty");
            return (Criteria) this;
        }

        public Criteria andIsEmptyIn(List<Integer> values) {
            addCriterion("is_empty in", values, "isEmpty");
            return (Criteria) this;
        }

        public Criteria andIsEmptyNotIn(List<Integer> values) {
            addCriterion("is_empty not in", values, "isEmpty");
            return (Criteria) this;
        }

        public Criteria andIsEmptyBetween(Integer value1, Integer value2) {
            addCriterion("is_empty between", value1, value2, "isEmpty");
            return (Criteria) this;
        }

        public Criteria andIsEmptyNotBetween(Integer value1, Integer value2) {
            addCriterion("is_empty not between", value1, value2, "isEmpty");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeIsNull() {
            addCriterion("space_type is null");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeIsNotNull() {
            addCriterion("space_type is not null");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeEqualTo(Integer value) {
            addCriterion("space_type =", value, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeNotEqualTo(Integer value) {
            addCriterion("space_type <>", value, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeGreaterThan(Integer value) {
            addCriterion("space_type >", value, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("space_type >=", value, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeLessThan(Integer value) {
            addCriterion("space_type <", value, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeLessThanOrEqualTo(Integer value) {
            addCriterion("space_type <=", value, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeIn(List<Integer> values) {
            addCriterion("space_type in", values, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeNotIn(List<Integer> values) {
            addCriterion("space_type not in", values, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeBetween(Integer value1, Integer value2) {
            addCriterion("space_type between", value1, value2, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("space_type not between", value1, value2, "spaceType");
            return (Criteria) this;
        }

        public Criteria andReservationStatusIsNull() {
            addCriterion("reservation_status is null");
            return (Criteria) this;
        }

        public Criteria andReservationStatusIsNotNull() {
            addCriterion("reservation_status is not null");
            return (Criteria) this;
        }

        public Criteria andReservationStatusEqualTo(Integer value) {
            addCriterion("reservation_status =", value, "reservationStatus");
            return (Criteria) this;
        }

        public Criteria andReservationStatusNotEqualTo(Integer value) {
            addCriterion("reservation_status <>", value, "reservationStatus");
            return (Criteria) this;
        }

        public Criteria andReservationStatusGreaterThan(Integer value) {
            addCriterion("reservation_status >", value, "reservationStatus");
            return (Criteria) this;
        }

        public Criteria andReservationStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("reservation_status >=", value, "reservationStatus");
            return (Criteria) this;
        }

        public Criteria andReservationStatusLessThan(Integer value) {
            addCriterion("reservation_status <", value, "reservationStatus");
            return (Criteria) this;
        }

        public Criteria andReservationStatusLessThanOrEqualTo(Integer value) {
            addCriterion("reservation_status <=", value, "reservationStatus");
            return (Criteria) this;
        }

        public Criteria andReservationStatusIn(List<Integer> values) {
            addCriterion("reservation_status in", values, "reservationStatus");
            return (Criteria) this;
        }

        public Criteria andReservationStatusNotIn(List<Integer> values) {
            addCriterion("reservation_status not in", values, "reservationStatus");
            return (Criteria) this;
        }

        public Criteria andReservationStatusBetween(Integer value1, Integer value2) {
            addCriterion("reservation_status between", value1, value2, "reservationStatus");
            return (Criteria) this;
        }

        public Criteria andReservationStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("reservation_status not between", value1, value2, "reservationStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusIsNull() {
            addCriterion("post_status is null");
            return (Criteria) this;
        }

        public Criteria andPostStatusIsNotNull() {
            addCriterion("post_status is not null");
            return (Criteria) this;
        }

        public Criteria andPostStatusEqualTo(Integer value) {
            addCriterion("post_status =", value, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusNotEqualTo(Integer value) {
            addCriterion("post_status <>", value, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusGreaterThan(Integer value) {
            addCriterion("post_status >", value, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("post_status >=", value, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusLessThan(Integer value) {
            addCriterion("post_status <", value, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusLessThanOrEqualTo(Integer value) {
            addCriterion("post_status <=", value, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusIn(List<Integer> values) {
            addCriterion("post_status in", values, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusNotIn(List<Integer> values) {
            addCriterion("post_status not in", values, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusBetween(Integer value1, Integer value2) {
            addCriterion("post_status between", value1, value2, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("post_status not between", value1, value2, "postStatus");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Date value) {
            addCriterion("add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterion("add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterion("add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterion("add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Date> values) {
            addCriterion("add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Date> values) {
            addCriterion("add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterion("add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table parking_car_space
     *
     * @mbggenerated do_not_delete_during_merge Wed Jul 05 13:24:24 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table parking_car_space
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
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