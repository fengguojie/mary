package com.chinadovey.parking.webapps.pojo.base;

import com.chinadovey.parking.webapps.pojo.CarSpace;
import com.chinadovey.parking.webapps.pojo.CarUser;
import com.chinadovey.parking.webapps.pojo.Park;

public class ParkCarSpaceBase {

	private Park park;
	
	private CarSpace carSpace;
	
	private CarUser carUser;

	public Park getPark() {
		return park;
	}

	public void setPark(Park park) {
		this.park = park;
	}

	public CarSpace getCarSpace() {
		return carSpace;
	}

	public void setCarSpace(CarSpace carSpace) {
		this.carSpace = carSpace;
	}

	public CarUser getCarUser() {
		return carUser;
	}

	public void setCarUser(CarUser carUser) {
		this.carUser = carUser;
	}
}
