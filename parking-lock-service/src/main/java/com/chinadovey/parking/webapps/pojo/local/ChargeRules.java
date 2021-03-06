package com.chinadovey.parking.webapps.pojo.local;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChargeRules {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column local_charge_rules.id
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column local_charge_rules.charge_rules_no
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    private String chargeRulesNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column local_charge_rules.begin_minute
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    private Integer beginMinute;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column local_charge_rules.end_minute
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    private Integer endMinute;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column local_charge_rules.charge_price
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    private Float chargePrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column local_charge_rules.time_span
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    private Integer timeSpan;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column local_charge_rules.rules_type
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    private Integer rulesType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column local_charge_rules.begin_time
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    private Date beginTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column local_charge_rules.end_time
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    private Date endTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column local_charge_rules.charge_rank
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    private Integer chargeRank;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column local_charge_rules.remark
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column local_charge_rules.park_id
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    private Integer parkId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column local_charge_rules.id
     *
     * @return the value of local_charge_rules.id
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column local_charge_rules.id
     *
     * @param id the value for local_charge_rules.id
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column local_charge_rules.charge_rules_no
     *
     * @return the value of local_charge_rules.charge_rules_no
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    public String getChargeRulesNo() {
        return chargeRulesNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column local_charge_rules.charge_rules_no
     *
     * @param chargeRulesNo the value for local_charge_rules.charge_rules_no
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    public void setChargeRulesNo(String chargeRulesNo) {
        this.chargeRulesNo = chargeRulesNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column local_charge_rules.begin_minute
     *
     * @return the value of local_charge_rules.begin_minute
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    public Integer getBeginMinute() {
        return beginMinute;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column local_charge_rules.begin_minute
     *
     * @param beginMinute the value for local_charge_rules.begin_minute
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    public void setBeginMinute(Integer beginMinute) {
        this.beginMinute = beginMinute;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column local_charge_rules.end_minute
     *
     * @return the value of local_charge_rules.end_minute
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    public Integer getEndMinute() {
        return endMinute;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column local_charge_rules.end_minute
     *
     * @param endMinute the value for local_charge_rules.end_minute
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    public void setEndMinute(Integer endMinute) {
        this.endMinute = endMinute;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column local_charge_rules.charge_price
     *
     * @return the value of local_charge_rules.charge_price
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    public Float getChargePrice() {
        return chargePrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column local_charge_rules.charge_price
     *
     * @param chargePrice the value for local_charge_rules.charge_price
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    public void setChargePrice(Float chargePrice) {
        this.chargePrice = chargePrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column local_charge_rules.time_span
     *
     * @return the value of local_charge_rules.time_span
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    public Integer getTimeSpan() {
        return timeSpan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column local_charge_rules.time_span
     *
     * @param timeSpan the value for local_charge_rules.time_span
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    public void setTimeSpan(Integer timeSpan) {
        this.timeSpan = timeSpan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column local_charge_rules.rules_type
     *
     * @return the value of local_charge_rules.rules_type
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    public Integer getRulesType() {
        return rulesType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column local_charge_rules.rules_type
     *
     * @param rulesType the value for local_charge_rules.rules_type
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    public void setRulesType(Integer rulesType) {
        this.rulesType = rulesType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column local_charge_rules.begin_time
     *
     * @return the value of local_charge_rules.begin_time
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column local_charge_rules.begin_time
     *
     * @param beginTime the value for local_charge_rules.begin_time
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column local_charge_rules.end_time
     *
     * @return the value of local_charge_rules.end_time
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column local_charge_rules.end_time
     *
     * @param endTime the value for local_charge_rules.end_time
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column local_charge_rules.charge_rank
     *
     * @return the value of local_charge_rules.charge_rank
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    public Integer getChargeRank() {
        return chargeRank;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column local_charge_rules.charge_rank
     *
     * @param chargeRank the value for local_charge_rules.charge_rank
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    public void setChargeRank(Integer chargeRank) {
        this.chargeRank = chargeRank;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column local_charge_rules.remark
     *
     * @return the value of local_charge_rules.remark
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column local_charge_rules.remark
     *
     * @param remark the value for local_charge_rules.remark
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column local_charge_rules.park_id
     *
     * @return the value of local_charge_rules.park_id
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    public Integer getParkId() {
        return parkId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column local_charge_rules.park_id
     *
     * @param parkId the value for local_charge_rules.park_id
     *
     * @mbggenerated Fri Oct 16 13:58:53 CST 2015
     */
    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

	public static List<ChargeRules> change(List<TBChargeRules> tbChargeRuless,Integer parkId) {
		List<ChargeRules> chargeRuless = new ArrayList<ChargeRules>();
		for(TBChargeRules tbChargeRules :tbChargeRuless){
			ChargeRules chargeRules = new ChargeRules();
			chargeRules.setId(tbChargeRules.getPkid().intValue());
			chargeRules.setBeginTime(tbChargeRules.getBegintime());
			chargeRules.setBeginMinute(tbChargeRules.getBeginminute());
			chargeRules.setChargePrice(tbChargeRules.getChargeprice().floatValue());
			chargeRules.setChargeRank(tbChargeRules.getChargerank());
			chargeRules.setChargeRulesNo(tbChargeRules.getChargerulesno());
			chargeRules.setEndMinute(tbChargeRules.getEndminute());
			chargeRules.setEndTime(tbChargeRules.getEndtime());
			chargeRules.setParkId(parkId);
			chargeRules.setRemark(tbChargeRules.getRemark().toString());
			chargeRules.setRulesType(tbChargeRules.getRulestype());
			chargeRules.setTimeSpan(tbChargeRules.getTimespan());
			chargeRuless.add(chargeRules);
		}
		return chargeRuless;
	}
}