package com.chinadovey.parking.webapps.pojo.base;

import com.chinadovey.parking.webapps.pojo.CarUser;
import com.chinadovey.parking.webapps.pojo.Park;

public class CarSpaceBase {
	CarUser carUser;
	Park park;

	
	public Park getPark() {
		return park;
	}
	
	public void setPark(Park park) {
		this.park = park;
	}
}
