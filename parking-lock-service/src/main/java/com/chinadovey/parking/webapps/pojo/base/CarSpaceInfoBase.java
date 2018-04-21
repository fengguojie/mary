package com.chinadovey.parking.webapps.pojo.base;

import com.chinadovey.parking.webapps.pojo.CarSpace;
import com.chinadovey.parking.webapps.pojo.Park;

public class CarSpaceInfoBase {

	private CarSpace carSpace;

	private Park park;

	public CarSpace getCarSpace() {
		return carSpace;
	}

	public void setCarSpace(CarSpace carSpace) {
		this.carSpace = carSpace;
	}

	public Park getPark() {
		return park;
	}

	public void setPark(Park park) {
		this.park = park;
	}
}
