package com.chinadovey.parking.webapps.pojo.wechat;

import java.util.List;

public class AuthorizeDevice {
	
	private String device_num;
	
	private String op_type;
	
	private List<DeviceInfo> device_list;
	
	private String product_id;

	public String getDevice_num() {
		return device_num;
	}

	public void setDevice_num(String device_num) {
		this.device_num = device_num;
	}

	public String getOp_type() {
		return op_type;
	}

	public void setOp_type(String op_type) {
		this.op_type = op_type;
	}

	public List<DeviceInfo> getDevice_list() {
		return device_list;
	}

	public void setDevice_list(List<DeviceInfo> device_list) {
		this.device_list = device_list;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	
}
