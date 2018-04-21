package com.chinadovey.parking.webapps.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarUserRelCarSpace {
   private int id;
   private int carUserId;
   private int carSpaceId;
   private int ParkId;
   private int type;
   private int status;
   private Date startDate;
   private Date endDate;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getCarUserId() {
	return carUserId;
}
public void setCarUserId(int carUserId) {
	this.carUserId = carUserId;
}
public int getCarSpaceId() {
	return carSpaceId;
}
public void setCarSpaceId(int carSpaceId) {
	this.carSpaceId = carSpaceId;
}
public int getParkId() {
	return ParkId;
}
public void setParkId(int parkId) {
	ParkId = parkId;
}
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
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
   
}