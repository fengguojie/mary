package com.chinadovey.parking.webapps.pojo.local;

import java.util.Date;

public class LocalMember {
	private String id;
	private int localId;
	private String cardNo;
	private int cardType;
	private String localNo;
	private String localName;
	private String mobile;
	private int monthlyMode;
	private String carNo;
	private Date startDate;
	private Date endDate;
	private float storeValue;
	private Date registerTime;
	private String operator;
	private Integer parkId;
	private Integer status;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public int getLocalId() {
		return localId;
	}

	public void setLocalId(int localId) {
		this.localId = localId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public int getCardType() {
		return cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getLocalNo() {
		return localNo;
	}

	public void setLocalNo(String localNo) {
		this.localNo = localNo;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getMonthlyMode() {
		return monthlyMode;
	}

	public void setMonthlyMode(int monthlyMode) {
		this.monthlyMode = monthlyMode;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public float getStoreValue() {
		return storeValue;
	}

	public void setStoreValue(float storeValue) {
		this.storeValue = storeValue;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Integer getParkId() {
		return parkId;
	}

	public void setParkId(Integer parkId) {
		this.parkId = parkId;
	}
}
