package com.chinadovey.parking.webapps.pojo;

public class CarUserAccount {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_car_user_account.id
     *
     * @mbggenerated Mon Sep 14 14:19:04 CST 2015
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_car_user_account.car_user_id
     *
     * @mbggenerated Mon Sep 14 14:19:04 CST 2015
     */
    private Integer carUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_car_user_account.mobile
     *
     * @mbggenerated Mon Sep 14 14:19:04 CST 2015
     */
    private String mobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_car_user_account.balance
     *
     * @mbggenerated Mon Sep 14 14:19:04 CST 2015
     */
    private Float balance;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_car_user_account.id
     *
     * @return the value of parking_car_user_account.id
     *
     * @mbggenerated Mon Sep 14 14:19:04 CST 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_car_user_account.id
     *
     * @param id the value for parking_car_user_account.id
     *
     * @mbggenerated Mon Sep 14 14:19:04 CST 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_car_user_account.car_user_id
     *
     * @return the value of parking_car_user_account.car_user_id
     *
     * @mbggenerated Mon Sep 14 14:19:04 CST 2015
     */
    public Integer getCarUserId() {
        return carUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_car_user_account.car_user_id
     *
     * @param carUserId the value for parking_car_user_account.car_user_id
     *
     * @mbggenerated Mon Sep 14 14:19:04 CST 2015
     */
    public void setCarUserId(Integer carUserId) {
        this.carUserId = carUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_car_user_account.mobile
     *
     * @return the value of parking_car_user_account.mobile
     *
     * @mbggenerated Mon Sep 14 14:19:04 CST 2015
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_car_user_account.mobile
     *
     * @param mobile the value for parking_car_user_account.mobile
     *
     * @mbggenerated Mon Sep 14 14:19:04 CST 2015
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_car_user_account.balance
     *
     * @return the value of parking_car_user_account.balance
     *
     * @mbggenerated Mon Sep 14 14:19:04 CST 2015
     */
    public Float getBalance() {
        return balance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_car_user_account.balance
     *
     * @param balance the value for parking_car_user_account.balance
     *
     * @mbggenerated Mon Sep 14 14:19:04 CST 2015
     */
    public void setBalance(Float balance) {
        this.balance = balance;
    }
}