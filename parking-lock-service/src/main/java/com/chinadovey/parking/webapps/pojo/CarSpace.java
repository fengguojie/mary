package com.chinadovey.parking.webapps.pojo;

import java.util.Date;

public class CarSpace {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_car_space.id
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_car_space.space_name
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    private String spaceName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_car_space.space_no
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    private String spaceNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_car_space.park_id
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    private Integer parkId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_car_space.parking_name
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    private String parkingName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_car_space.slave_id
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    private String slaveId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_car_space.car_user_id
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    private Integer carUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_car_space.is_empty
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    private Integer isEmpty;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_car_space.space_type
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    private Integer spaceType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_car_space.reservation_status
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    private Integer reservationStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_car_space.post_status
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    private Integer postStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_car_space.add_time
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    private Date addTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_car_space.remark
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    private String remark;
    private String carlockStatus;//车锁状态。非数据库字段
    private Float voltage;//车锁电量。非数据库字段
    private String gatewayNo;//网关编号。非数据库字段
    private String gatewayStatus;//网关状态。非数据库字段
    
    

    public String getGatewayStatus() {
		return gatewayStatus;
	}

	public void setGatewayStatus(String gatewayStatus) {
		this.gatewayStatus = gatewayStatus;
	}

	public String getGatewayNo() {
		return gatewayNo;
	}

	public void setGatewayNo(String gatewayNo) {
		this.gatewayNo = gatewayNo;
	}

	public Float getVoltage() {
		return voltage;
	}

	public void setVoltage(Float voltage) {
		this.voltage = voltage;
	}

	public String getCarlockStatus() {
		return carlockStatus;
	}

	public void setCarlockStatus(String carlockStatus) {
		this.carlockStatus = carlockStatus;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_car_space.id
     *
     * @return the value of parking_car_space.id
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_car_space.id
     *
     * @param id the value for parking_car_space.id
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_car_space.space_name
     *
     * @return the value of parking_car_space.space_name
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public String getSpaceName() {
        return spaceName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_car_space.space_name
     *
     * @param spaceName the value for parking_car_space.space_name
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_car_space.space_no
     *
     * @return the value of parking_car_space.space_no
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public String getSpaceNo() {
        return spaceNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_car_space.space_no
     *
     * @param spaceNo the value for parking_car_space.space_no
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public void setSpaceNo(String spaceNo) {
        this.spaceNo = spaceNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_car_space.park_id
     *
     * @return the value of parking_car_space.park_id
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public Integer getParkId() {
        return parkId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_car_space.park_id
     *
     * @param parkId the value for parking_car_space.park_id
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_car_space.parking_name
     *
     * @return the value of parking_car_space.parking_name
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public String getParkingName() {
        return parkingName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_car_space.parking_name
     *
     * @param parkingName the value for parking_car_space.parking_name
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_car_space.slave_id
     *
     * @return the value of parking_car_space.slave_id
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public String getSlaveId() {
        return slaveId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_car_space.slave_id
     *
     * @param slaveId the value for parking_car_space.slave_id
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public void setSlaveId(String slaveId) {
        this.slaveId = slaveId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_car_space.car_user_id
     *
     * @return the value of parking_car_space.car_user_id
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public Integer getCarUserId() {
        return carUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_car_space.car_user_id
     *
     * @param carUserId the value for parking_car_space.car_user_id
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public void setCarUserId(Integer carUserId) {
        this.carUserId = carUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_car_space.is_empty
     *
     * @return the value of parking_car_space.is_empty
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public Integer getIsEmpty() {
        return isEmpty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_car_space.is_empty
     *
     * @param isEmpty the value for parking_car_space.is_empty
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public void setIsEmpty(Integer isEmpty) {
        this.isEmpty = isEmpty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_car_space.space_type
     *
     * @return the value of parking_car_space.space_type
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public Integer getSpaceType() {
        return spaceType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_car_space.space_type
     *
     * @param spaceType the value for parking_car_space.space_type
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public void setSpaceType(Integer spaceType) {
        this.spaceType = spaceType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_car_space.reservation_status
     *
     * @return the value of parking_car_space.reservation_status
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public Integer getReservationStatus() {
        return reservationStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_car_space.reservation_status
     *
     * @param reservationStatus the value for parking_car_space.reservation_status
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public void setReservationStatus(Integer reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_car_space.post_status
     *
     * @return the value of parking_car_space.post_status
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public Integer getPostStatus() {
        return postStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_car_space.post_status
     *
     * @param postStatus the value for parking_car_space.post_status
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public void setPostStatus(Integer postStatus) {
        this.postStatus = postStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_car_space.add_time
     *
     * @return the value of parking_car_space.add_time
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_car_space.add_time
     *
     * @param addTime the value for parking_car_space.add_time
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_car_space.remark
     *
     * @return the value of parking_car_space.remark
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_car_space.remark
     *
     * @param remark the value for parking_car_space.remark
     *
     * @mbggenerated Wed Jul 05 13:24:24 CST 2017
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}