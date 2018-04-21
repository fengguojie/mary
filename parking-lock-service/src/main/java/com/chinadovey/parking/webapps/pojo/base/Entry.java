package com.chinadovey.parking.webapps.pojo.base;

public class Entry {

	public Entry() {
		super();
	}

	public Entry(String key, Object value) {
		super();
		this.key = key;
		this.value = value;
	}

	private String key;

	private Object value;
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
