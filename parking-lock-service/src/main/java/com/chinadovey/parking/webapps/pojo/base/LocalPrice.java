package com.chinadovey.parking.webapps.pojo.base;

import java.util.Date;

public class LocalPrice {

	private Integer id;
	private String pkid;
	private String ChargeRulesNO;
	private Integer BeginMinute;
	private Integer EndMinute;
	private Float ChargePrice;
	private Integer TimeSpan;
	private Integer RulesType;
	private Date BeginTime;
	private Date EndTime;
	private String ChargeRank;
	private String Remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPkid() {
		return pkid;
	}

	public void setPkid(String pkid) {
		this.pkid = pkid;
	}

	public String getChargeRulesNO() {
		return ChargeRulesNO;
	}

	public void setChargeRulesNO(String chargeRulesNO) {
		ChargeRulesNO = chargeRulesNO;
	}

	public Integer getBeginMinute() {
		return BeginMinute;
	}

	public void setBeginMinute(Integer beginMinute) {
		BeginMinute = beginMinute;
	}

	public Integer getEndMinute() {
		return EndMinute;
	}

	public void setEndMinute(Integer endMinute) {
		EndMinute = endMinute;
	}

	public Float getChargePrice() {
		return ChargePrice;
	}

	public void setChargePrice(Float chargePrice) {
		ChargePrice = chargePrice;
	}

	public Integer getTimeSpan() {
		return TimeSpan;
	}

	public void setTimeSpan(Integer timeSpan) {
		TimeSpan = timeSpan;
	}

	public Integer getRulesType() {
		return RulesType;
	}

	public void setRulesType(Integer rulesType) {
		RulesType = rulesType;
	}

	public Date getBeginTime() {
		return BeginTime;
	}

	public void setBeginTime(Date beginTime) {
		BeginTime = beginTime;
	}

	public Date getEndTime() {
		return EndTime;
	}

	public void setEndTime(Date endTime) {
		EndTime = endTime;
	}

	public String getChargeRank() {
		return ChargeRank;
	}

	public void setChargeRank(String chargeRank) {
		ChargeRank = chargeRank;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}
}
