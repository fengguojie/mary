package com.chinadovey.parking.webapps.ex.carUser;

import com.chinadovey.parking.webapps.ex.Config;
import com.chinadovey.parking.webapps.pojo.ChargingRules;
import com.chinadovey.parking.webapps.pojo.Park;

public class ExPark {
	
	private Integer id;
	
	private String address ;
	
	private Integer empty;
	
	private String price;
	
	private float prices;
	
	private String name;

	private Double latitude; //纬度
	 
	private Double longitude;//经度
	
	private String picture;
	
	private int type; //0-不是；1-只允需查看数据；2-在线缴费不可预约；3-在线缴费可预约
	
	public static ExPark change(Park park,ChargingRules chargingRules){
		ExPark ep = new ExPark();
		ep.setAddress(park.getAddress().replace("null", ""));
		ep.setEmpty(park.getEmpty());
		ep.setId(park.getId());
		ep.setName(park.getName());
		ep.setLatitude(park.getLatitude());
		ep.setLongitude(park.getLongitude());
		ep.setPicture(Config.pic_base_url+park.getPicture());
		ep.setType(park.getType());
		int isfree = park.getIsFree()==null?1:park.getIsFree();
		if(chargingRules!=null){
			int type = chargingRules.getChargeType();//1：次；2：时段
			if(isfree==1){
				ep.setPrice("免费");
				ep.setPrices(0);
			}else{
				if(type==1){
					ep.setPrice(chargingRules.getDayTimesPrice()+"元/次");
					ep.setPrices(chargingRules.getDayTimesPrice());
				}else{
					ep.setPrice(chargingRules.getDayRemainTimePrice()+"元/"+chargingRules.getCycleTime().intValue()+"分钟");//列表显示剩余时段金额
					ep.setPrices(chargingRules.getDayRemainTimePrice());
				}
				
			}
		}else{
			ep.setPrice("免费");
			ep.setPrices(0);
		}
		
		
		return ep;
	}
	
	
	public float getPrices() {
		return prices;
	}


	public void setPrices(float prices) {
		this.prices = prices;
	}


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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	

}
