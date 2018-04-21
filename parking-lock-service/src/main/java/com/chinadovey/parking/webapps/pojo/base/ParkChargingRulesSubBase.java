package com.chinadovey.parking.webapps.pojo.base;

import com.chinadovey.parking.webapps.pojo.ChargingRules;
import com.chinadovey.parking.webapps.pojo.Company;
import com.chinadovey.parking.webapps.pojo.Park;

public class ParkChargingRulesSubBase extends Park{

	private ChargingRules chargingRules;

	public ChargingRules getChargingRules() {
		return chargingRules;
	}

	public void setChargingRules(ChargingRules chargingRules) {
		this.chargingRules = chargingRules;
	}

	public static ParkChargingRulesSubBase change(Park park,ChargingRules chargingRules) {
		ParkChargingRulesSubBase parkSubBase = new ParkChargingRulesSubBase();
		parkSubBase.setId(park.getId());
		parkSubBase.setCompanyId(park.getCompanyId());
		parkSubBase.setClosingTime(park.getClosingTime());
		parkSubBase.setOpeningTime(park.getOpeningTime());
		parkSubBase.setName(park.getName());
		parkSubBase.setCode(park.getCode());
		parkSubBase.setAddress(park.getAddress());
		parkSubBase.setEmail(park.getEmail());
		parkSubBase.setIsFree(park.getIsFree());
		parkSubBase.setLatitude(park.getLatitude());
		parkSubBase.setLongitude(park.getLongitude());
		parkSubBase.setLinkman(park.getLinkman());
		parkSubBase.setParkType(park.getParkType());
		parkSubBase.setPicture(park.getPicture());
		parkSubBase.setTel(park.getTel());
		parkSubBase.setTotal(park.getTotal());
		parkSubBase.setEmpty(park.getEmpty());
		parkSubBase.setType(park.getType());
		
		if(chargingRules == null){
			chargingRules = new ChargingRules();
		}
		parkSubBase.setChargingRules(chargingRules);
		return parkSubBase;
	}
	
}
