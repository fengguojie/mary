package com.chinadovey.parking.webapps.ex.carUser;

import com.chinadovey.parking.webapps.ex.Config;
import com.chinadovey.parking.webapps.pojo.ChargingRules;
import com.chinadovey.parking.webapps.pojo.Park;

public class ExNearPark {
	
	private Integer id;
	
	private String address ;
	
	private Integer total;
	
	private String price;
	
	private String name;

	private Double latitude; //纬度
	 
	private Double longitude;//经度
	
	private String picture;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	
	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}


	public static ExNearPark change(Park park,int total){
		ExNearPark ep = new ExNearPark();
		ep.setAddress(park.getAddress().replace("null", ""));
		ep.setId(park.getId());
		ep.setName(park.getName());
		ep.setLatitude(park.getLatitude());
		ep.setLongitude(park.getLongitude());
		ep.setPicture(Config.pic_base_url+park.getPicture());
		ep.setTotal(total);
		return ep;
	}

}
