package com.chinadovey.parking.webapps.ex.manage;

import java.util.Date;

public class ExAbnormalOpenList{
	
	 private String id;
	 private String carNo;
	 private Date time;
	 private String operator;
	 private String parkName; 
		public String getId() {
		return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getCarNo() {
			return carNo;
		}
		public void setCarNo(String carNo) {
			this.carNo = carNo;
		}
		public Date getTime() {
			return time;
		}
		public void setTime(Date time) {
			this.time = time;
		}
		public String getOperator() {
			return operator;
		}
		public void setOperator(String operator) {
			this.operator = operator;
		}
		public String getParkName() {
			return parkName;
		}
		public void setParkName(String parkName) {
			this.parkName = parkName;
		}
		
	 
}
