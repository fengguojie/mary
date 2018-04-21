package com.chinadovey.parking.webapps.pojo.base;

import com.chinadovey.parking.webapps.pojo.Company;
import com.chinadovey.parking.webapps.pojo.Park;

public class ParkSubBase extends Park{

	private Company company;

	



	public Company getCompany() {
		return company;
	}



	public void setCompany(Company company) {
		this.company = company;
	}


	public static ParkSubBase change(Park park,Company company) {
		ParkSubBase parkSubBase = new ParkSubBase();
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
		parkSubBase.setType(park.getType());
		parkSubBase.setChargingRulesId(park.getChargingRulesId());
		
		
		if(company == null){
			company = new Company();
		}
		parkSubBase.setCompany(company);
		return parkSubBase;
	}
	
}
