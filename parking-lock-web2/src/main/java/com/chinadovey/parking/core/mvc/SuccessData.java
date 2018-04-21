package com.chinadovey.parking.core.mvc;

import java.util.HashMap;
import java.util.Map;

public class SuccessData extends DataResult {

	public SuccessData(String key, Object value) {
		super(true);
		addData(key, value);
	}
	
	public SuccessData(Map<String ,Object> data) {
		super(true);
		this.data.putAll(data);
	}

	private Map<String, Object> data = new HashMap<String, Object>();

	public Map<String, Object> getData() {
		return data;
	}

	public Object addData(String key, Object value) {
		return this.data.put(key, value);
	}
	
	
}
