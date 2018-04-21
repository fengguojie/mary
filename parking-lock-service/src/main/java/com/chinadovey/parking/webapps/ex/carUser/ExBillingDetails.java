package com.chinadovey.parking.webapps.ex.carUser;

import com.chinadovey.parking.webapps.ex.Config;
import com.chinadovey.parking.webapps.pojo.ChargingRules;
import com.chinadovey.parking.webapps.pojo.Park;

public class ExBillingDetails {
	
	private Integer id;
	
	private String address ;
	
	private Integer empty;
	
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

	public Integer getEmpty() {
		return empty;
	}

	public void setEmpty(Integer empty) {
		this.empty = empty;
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

	public static ExBillingDetails change(Park park,ChargingRules chargingRules){
		ExBillingDetails ep = new ExBillingDetails();
		ep.setAddress(park.getAddress().replace("null", ""));
		ep.setEmpty(park.getEmpty());
		ep.setId(park.getId());
		ep.setName(park.getName());
		ep.setLatitude(park.getLatitude());
		ep.setLongitude(park.getLongitude());
		ep.setPicture(Config.pic_base_url+park.getPicture());
		int isfree = park.getIsFree()==null?1:park.getIsFree();
		if(chargingRules!=null){
			int type = chargingRules.getChargeType();//1：次；2：时段
			if(isfree==1){
				ep.setPrice("免费");
			}else{
				if(type==1){
					ep.setPrice(chargingRules.getDayTimesPrice()+"元/每次");
				}else{
					ep.setPrice(chargingRules.getDayRemainTimePrice()+"元/每"+chargingRules.getCycleTime()+"分钟");//列表显示剩余时段金额
				}
			}
		}else{
			ep.setPrice("免费");
		}
		
		
		return ep;
	}

}
