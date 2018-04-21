package com.chinadovey.parking.webapps.pojo;

import java.util.Date;

public class Gateway {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_gateway.id
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_gateway.gateway_no
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    private String gatewayNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_gateway.gateway_name
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    private String gatewayName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_gateway.gateway_status
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    private Integer gatewayStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_gateway.company_id
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    private String companyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_gateway.park_id
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    private Integer parkId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_gateway.add_time
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    private Date addTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_gateway.longitude
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    private Double longitude;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_gateway.latitude
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    private Double latitude;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_gateway.serialA
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    private String seriala;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_gateway.wireA
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    private String wirea;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_gateway.channelA
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    private String channela;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_gateway.serialA_status
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    private Integer serialaStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_gateway.serialB
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    private String serialb;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_gateway.wireB
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    private String wireb;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_gateway.channelB
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    private String channelb;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_gateway.serialB_status
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    private Integer serialbStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_gateway.remark
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_gateway.id
     *
     * @return the value of parking_gateway.id
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_gateway.id
     *
     * @param id the value for parking_gateway.id
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_gateway.gateway_no
     *
     * @return the value of parking_gateway.gateway_no
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public String getGatewayNo() {
        return gatewayNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_gateway.gateway_no
     *
     * @param gatewayNo the value for parking_gateway.gateway_no
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public void setGatewayNo(String gatewayNo) {
        this.gatewayNo = gatewayNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_gateway.gateway_name
     *
     * @return the value of parking_gateway.gateway_name
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public String getGatewayName() {
        return gatewayName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_gateway.gateway_name
     *
     * @param gatewayName the value for parking_gateway.gateway_name
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public void setGatewayName(String gatewayName) {
        this.gatewayName = gatewayName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_gateway.gateway_status
     *
     * @return the value of parking_gateway.gateway_status
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public Integer getGatewayStatus() {
        return gatewayStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_gateway.gateway_status
     *
     * @param gatewayStatus the value for parking_gateway.gateway_status
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public void setGatewayStatus(Integer gatewayStatus) {
        this.gatewayStatus = gatewayStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_gateway.company_id
     *
     * @return the value of parking_gateway.company_id
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_gateway.company_id
     *
     * @param companyId the value for parking_gateway.company_id
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_gateway.park_id
     *
     * @return the value of parking_gateway.park_id
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public Integer getParkId() {
        return parkId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_gateway.park_id
     *
     * @param parkId the value for parking_gateway.park_id
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_gateway.add_time
     *
     * @return the value of parking_gateway.add_time
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_gateway.add_time
     *
     * @param addTime the value for parking_gateway.add_time
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_gateway.longitude
     *
     * @return the value of parking_gateway.longitude
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_gateway.longitude
     *
     * @param longitude the value for parking_gateway.longitude
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_gateway.latitude
     *
     * @return the value of parking_gateway.latitude
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_gateway.latitude
     *
     * @param latitude the value for parking_gateway.latitude
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_gateway.serialA
     *
     * @return the value of parking_gateway.serialA
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public String getSeriala() {
        return seriala;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_gateway.serialA
     *
     * @param seriala the value for parking_gateway.serialA
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public void setSeriala(String seriala) {
        this.seriala = seriala;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_gateway.wireA
     *
     * @return the value of parking_gateway.wireA
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public String getWirea() {
        return wirea;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_gateway.wireA
     *
     * @param wirea the value for parking_gateway.wireA
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public void setWirea(String wirea) {
        this.wirea = wirea;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_gateway.channelA
     *
     * @return the value of parking_gateway.channelA
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public String getChannela() {
        return channela;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_gateway.channelA
     *
     * @param channela the value for parking_gateway.channelA
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public void setChannela(String channela) {
        this.channela = channela;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_gateway.serialA_status
     *
     * @return the value of parking_gateway.serialA_status
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public Integer getSerialaStatus() {
        return serialaStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_gateway.serialA_status
     *
     * @param serialaStatus the value for parking_gateway.serialA_status
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public void setSerialaStatus(Integer serialaStatus) {
        this.serialaStatus = serialaStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_gateway.serialB
     *
     * @return the value of parking_gateway.serialB
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public String getSerialb() {
        return serialb;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_gateway.serialB
     *
     * @param serialb the value for parking_gateway.serialB
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public void setSerialb(String serialb) {
        this.serialb = serialb;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_gateway.wireB
     *
     * @return the value of parking_gateway.wireB
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public String getWireb() {
        return wireb;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_gateway.wireB
     *
     * @param wireb the value for parking_gateway.wireB
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public void setWireb(String wireb) {
        this.wireb = wireb;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_gateway.channelB
     *
     * @return the value of parking_gateway.channelB
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public String getChannelb() {
        return channelb;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_gateway.channelB
     *
     * @param channelb the value for parking_gateway.channelB
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public void setChannelb(String channelb) {
        this.channelb = channelb;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_gateway.serialB_status
     *
     * @return the value of parking_gateway.serialB_status
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public Integer getSerialbStatus() {
        return serialbStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_gateway.serialB_status
     *
     * @param serialbStatus the value for parking_gateway.serialB_status
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public void setSerialbStatus(Integer serialbStatus) {
        this.serialbStatus = serialbStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_gateway.remark
     *
     * @return the value of parking_gateway.remark
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_gateway.remark
     *
     * @param remark the value for parking_gateway.remark
     *
     * @mbggenerated Thu Jun 29 14:31:14 CST 2017
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}