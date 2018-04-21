package com.chinadovey.parking.webapps.pojo.base;

import java.util.Date;

public class CarUserCountBase {

	private Date registerTime;
	
	private Integer count;

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
