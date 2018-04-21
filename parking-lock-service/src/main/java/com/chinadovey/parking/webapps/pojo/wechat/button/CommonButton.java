package com.chinadovey.parking.webapps.pojo.wechat.button;

/** 
 * 普通按钮（子按钮） 
 *  
 */ 
public class CommonButton extends Button {
	private String key;
	private String type;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
